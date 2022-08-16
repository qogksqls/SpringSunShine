package com.ssafy.web.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import com.ssafy.web.common.PathUtil;
import com.ssafy.web.db.entity.Therapist;
import com.ssafy.web.db.repository.TheraRepository;
import com.ssafy.web.model.response.RecommendTherapistResponse;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RecommendServiceImpl implements RecommendService{
	private final TheraRepository threpo;
	private final ServletContext servletContext;

	@Override
	public List<RecommendTherapistResponse> findByUser_UserIdIn(String[] thera_id){
		List<Therapist> list = threpo.findByUser_UserIdIn(thera_id);
		List<RecommendTherapistResponse> rlist = new ArrayList<RecommendTherapistResponse>();
		
		list.stream().forEach((e)->{
			try {
				rlist.add(new RecommendTherapistResponse(e));
				RecommendTherapistResponse r = rlist.get(rlist.size()-1);
				if (e.getProfileUrl() != null) {
					String str = servletContext.getRealPath(PathUtil.PROFILE_PATH);
	    			String url = str+e.getProfileUrl();
	    			System.out.println(url);
	    			InputStream imageIS = new FileInputStream(url);
	    			byte[] imageByteArray = IOUtils.toByteArray(imageIS);
	    			r.setProfile_url(imageByteArray);
	    			imageIS.close();

	    		}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		return rlist;
	}

	@Override
	public List<RecommendTherapistResponse> findAll(){
		List<Therapist> list = threpo.findAll();
		List<RecommendTherapistResponse> rlist = new ArrayList<RecommendTherapistResponse>();
		
		list.stream().forEach(e->{
			try {
				rlist.add(new RecommendTherapistResponse(e));
				RecommendTherapistResponse r = rlist.get(rlist.size()-1);
				if (e.getProfileUrl() != null) {
					String str = servletContext.getRealPath(PathUtil.PROFILE_PATH);
	    			String url = str+e.getProfileUrl();
	    			System.out.println(url);

	    			InputStream imageIS = new FileInputStream(url);
	    			byte[] imageByteArray = IOUtils.toByteArray(imageIS);
	    			r.setProfile_url(imageByteArray);
	    			imageIS.close();

	    		}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
