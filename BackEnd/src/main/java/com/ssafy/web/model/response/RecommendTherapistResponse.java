package com.ssafy.web.model.response;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.IOUtils;

import com.ssafy.web.common.PathUtil;
import com.ssafy.web.db.entity.Therapist;
import com.ssafy.web.dto.Academy;
import com.ssafy.web.dto.Career;
import com.ssafy.web.dto.Licence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class RecommendTherapistResponse {
        String thera_id;
        String name;
        String email;
        byte[] profile_url;
        String thera_intro;
        List<Academy> academy;
    	List<Career> careers;
    	List<Licence> licence;
    	
    	public RecommendTherapistResponse(Therapist tr)  {
    		thera_id = tr.getUser().getUserId();
    		name = tr.getName();
    		email = tr.getEmail();
    		
    		thera_intro = tr.getTheraIntro();
    		
    		// -----------경력 불러와서 리스트 설정
    		List<Academy> acalist = new ArrayList<Academy>();
    		if (stringCheck(tr.getAcademicCareers())) {
    			String[] acaList = getString(tr.getAcademicCareers()); // a,b,c,d+e,f,g,h
    			for (int i = 0; i < acaList.length; i++) {
    				StringTokenizer st = new StringTokenizer(acaList[i], ",");
    				Academy academy = new Academy();
    				academy.setName(st.nextToken());
    				academy.setMajor(st.nextToken());
    				academy.setAdmin(st.nextToken());
    				academy.setGradu(st.nextToken());
    				acalist.add(academy);
    			}
    		}
    		// ------------------------
    		List<Career> carlist = new ArrayList<Career>();
    		if (stringCheck(tr.getCareers())) {
    			String[] carList = getString(tr.getCareers());
    			for (int i = 0; i < carList.length; i++) {
    				StringTokenizer st = new StringTokenizer(carList[i], ",");
    				Career career = new Career();
    				career.setName(st.nextToken());
    				career.setLevel(st.nextToken());
    				career.setDate(st.nextToken());
    				career.setRole(st.nextToken());
    				carlist.add(career);
    			}
    		}
    		// -------------------------
    		List<Licence> liclist = new ArrayList<Licence>();
    		if (stringCheck(tr.getLicences())) {
    			String[] licList = getString(tr.getLicences());
    			for (int i = 0; i < licList.length; i++) {
    				StringTokenizer st = new StringTokenizer(licList[i], ",");
    				//[asdf,null,2022-08-23,220720_출결확인서_배한빈[서울_6반]-1.pdf] 
    				Licence licence = new Licence();
    				licence.setName(st.nextToken());
    				licence.setPlace(st.nextToken());
    				licence.setDate(st.nextToken());
    				licence.setFile(st.nextToken());
    				liclist.add(licence);
    			}
    		}
    		if(tr.getProfileUrl() !=null && !"".equals(tr.getProfileUrl())) {
    			System.out.println("asdf");
    			try {
//    				InputStream resourceAsStream = this.getClass().getResourceAsStream(PathUtil.PROFILE_PATH+tr.getProfileUrl());
//					profile_url = IOUtils.toByteArray(resourceAsStream);
//					resourceAsStream.close();
					
					String url = PathUtil.PROFILE_UPLOAD_PATH+ tr.getProfileUrl();
					InputStream imageIS = new FileInputStream(url);
					profile_url = IOUtils.toByteArray(imageIS);
					
					imageIS.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		
    		
    		academy = acalist;
    		careers = carlist;
    		licence = liclist;
    		
    	}
    	// 약력 : 띄어쓰기 분리하고, [ ] 빼는 과정
    	public String[] getString(String data) {
    		String[] list = data.split("=");
    		String[] res = new String[list.length];
    		for (int i = 0; i < list.length; i++) {
    			res[i] = list[i].replace("[", "").replace("]", "");
    		}
    		return res;
    	}

    	public boolean stringCheck(String data) {
    		if (data != null && !"".equals(data)) {
    			return true;
    		}
    		return false;
    	}
}