package com.ssafy.web.model.response;

import java.util.Date;

import com.ssafy.web.db.entity.Consult;
import com.ssafy.web.db.entity.ConsultPersonName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultTotalResponse {
	private int consultNo;
	private Date startedTime;
	private Date closedTime;
	private String theraId;
	private String childId;
	private String parentId;
	private String memo;
	private String record;
	private String theraName;
	private String childName;
	private String parentName;
	
	public ConsultTotalResponse(ConsultResponse cr, ConsultPersonName cpn) {
		consultNo = cr.getConsultNo();
		startedTime=cr.getStartedTime();
		closedTime=cr.getClosedTime();
		theraId=cr.getTheraId();
		childId=cr.getChildId();
		parentId=cr.getParentId();
		memo=cr.getMemo();
		record=cr.getRecord();
		theraName=cpn.getTheraName();
		childName=cpn.getChildName();
		parentName=cpn.getParentName();
	}
	
}
