package com.ssafy.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.web.db.entity.BExpertiseChild;
import com.ssafy.web.db.entity.Expertise;
import com.ssafy.web.db.entity.questions.Answer;
import com.ssafy.web.db.repository.AnswerRepository;
import com.ssafy.web.db.repository.BExpertiseChildRepository;
import com.ssafy.web.db.repository.ExpertiseRepository;
import com.ssafy.web.dto.Answerlist;
import com.ssafy.web.dto.ChildData;
import com.ssafy.web.dto.Question;
import com.ssafy.web.model.response.ChildAnswerResponse;
import com.ssafy.web.request.AnswerRequest;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class AnswerServiceImpl implements AnswerService{

	@Autowired
	AnswerRepository answerRepo;
	
	@Autowired
	WebClient webClient; 
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	ExpertiseRepository expertise;
	
	@Autowired
	BExpertiseChildRepository childExp;
	
	@Override
	public int registAnswer(AnswerRequest answerReq) {
		String childId = answerReq.getChild_id();
		log.debug("문진표 저장 아동 아이디 : "+childId);
		/*등록하지 않은 아동*/
		if(childId == null) {
			log.debug("아동 아이디 없음");
			return 0; 
		}
		String res = webClient.put().uri("/child/surveyFlag/"+childId).retrieve().bodyToMono(String.class)
				.block();
		
		if(res.equals("fail")) { // 없는 아동 or 이미 응답한 아동
			log.debug("아동 아이디 오류");
			return 0; }
		
		
		Answer ans = new Answer();
		ans.setChildId(childId);
		//응답문항, 점수 
		List<Answerlist> ansLis = answerReq.getAnswer();
		String str="";
		int score1=0;
		int score2=0;
		int score3=0;
		for(int i=0; i<ansLis.size(); i++) {
			int score = ansLis.get(i).getScore();
			str+=ansLis.get(i).getOptions();
			str+=",";
			score1+=score;
			//13~20번 점수
			if(i>=12 && i<=19) score2+=score;
			if(i>=20 && i<=22) score3+=score;
		}
		ans.setAnswer(str);
		ans.setScore1(score1);
		ans.setScore2(score2);
		ans.setScore3(score3);
		
		answerRepo.save(ans);
		
		//점수 합산하여, b_expertise_child 테이블에 데이터 저장 
		int expNo = registChildExp(childId, score1, score2, score3);
		Expertise childE = expertise.findByExpertiseNo(expNo);
		Expertise exp = new Expertise(); 
		exp.setExpertiseNo(expNo);
		exp.setIsKind(childE.getIsKind());
		
		
		BExpertiseChild childexp = new BExpertiseChild();
		childexp.setChildId(childId);
		childexp.setExpertise(exp);
		
		childExp.save(childexp);
		
		
		return 1;
	}
	
	//childId:아동 아이디, score1:총점 , score2:13~20번 , score3:21~23 
	private int registChildExp(String childId, int score1, int score2, int score3) {
		ChildData res = webClient.get().uri("/child/getchild/"+childId).retrieve().bodyToMono(ChildData.class)
				.block();
		System.out.println(res);
		int age= res.getAge(); // 아이 나이 
		String gender=  res.getGender(); // 아이 성별 
		System.out.println("나이 : "+age+" , 성별 :"+gender);
		//치료사 추천을 위한 점수 로직 
		if(score1 > 60) {
			return 1; // 60점 이상, 자폐증 
		}
		if(score1 < 40) return 6; // 정상
		if(age<=3) return 5; //아동기 붕괴성 
		else if(score1 >=40 && score3 >= 10 && gender.equals("여자")) { // 총합 40점 이상, 21~ 10점이상, 여자아이 
			return 4;
		}
		else if(score1 >=40 &&  score1<=60) { //40점 이상 60점 이하 
			if(score2>=20) {
				return  2; //13~20번 20점 이상 , 아스퍼거 증후군 
			}
			else return 3; //전반적 발달장애 
		}
		else return 6; //특이사항 없음 컬럼 추가 
		
		
	}

	@Override
	public ChildAnswerResponse getAnswer(String child_id) {
		log.debug("문진표 응답 아동 아이디 : "+child_id);
		//레디스에 질문 저장되있는가 ? 
		//레디스에 질문 저장 안되있으면 , 저장해주어야 해 
		if(redisService.getQuestions().size() == 0) {
			log.debug("redis에 질문 저장");
			// 저장해주고, 
			redisService.setQuestions();
		}
		List<String> questions = redisService.getQuestions();
		
		
		Answer ans = answerRepo.findAnswerByChildId(child_id);
		StringTokenizer st= new StringTokenizer(ans.getAnswer(),",");
		
		//질문(questions) 과, 응답(st.nextToken) 담을 리스트 
		List<Question> answer = new ArrayList<Question>();
		
		for(int i =0 ; i<questions.size(); i++) {
			Question q = new Question();
			q.setQuestion(questions.get(i));
			q.setAnswer(st.nextToken());
			answer.add(q);
		}
		
		ChildAnswerResponse car = new ChildAnswerResponse();
		car.setAnswers(answer);
		
		BExpertiseChild bexp = childExp.findByChildId(child_id);
		Expertise bexpchild = bexp.getExpertise();
		car.setExpertise(bexpchild.getIsKind());
		
		return car;
		
		
	}

	
}
