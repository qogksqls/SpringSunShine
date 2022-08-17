package com.ssafy.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.web.db.entity.Therapist;
import com.ssafy.web.db.repository.TheraRepository;
import com.ssafy.web.model.response.RecommendTherapistResponse;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RecommendServiceImpl implements RecommendService{
	private final TheraRepository threpo;

	@Override
	public List<RecommendTherapistResponse> findByUser_UserIdIn(String[] thera_id){
		List<Therapist> list = threpo.findByUser_UserIdIn(thera_id);
		List<RecommendTherapistResponse> rlist = new ArrayList<RecommendTherapistResponse>();
		
		list.stream().forEach((e)->{
				rlist.add(new RecommendTherapistResponse(e));
		});
		
		return rlist;
	}

	@Override
	public List<RecommendTherapistResponse> findAll(){
		List<Therapist> list = threpo.findAll();
		List<RecommendTherapistResponse> rlist = new ArrayList<RecommendTherapistResponse>();
		list.stream().forEach(e->{
				rlist.add(new RecommendTherapistResponse(e));
		});
		
		return rlist;
	}
	
//	@Override
//	public List<RecommendTherapistResponse> findByUser_UserIdIn(String[] thera_id) {
//		List<RecommendTherapistResponse> list = threpo.findByUser_UserIdIn(thera_id);
//		return list;
//	}
//
//	@Override
//	public List<RecommendTherapistResponse> findAlljpql() {
//		List<RecommendTherapistResponse> list = threpo.findAlljpql();
//		return list;
//	}
	
}
