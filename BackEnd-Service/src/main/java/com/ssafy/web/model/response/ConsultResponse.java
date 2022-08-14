package com.ssafy.web.model.response;

import java.util.Date;

import com.ssafy.web.db.entity.Consult;
import com.ssafy.web.request.ConsultRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultResponse {
	private int consultNo;
	private Date startedTime;
	private Date closedTime;
	private String theraId;
	private String childId;
	private String parentId;
	private String memo;
	private String record;
	
	public static ConsultResponse fromEntity(Consult consult) {
		ConsultResponse consultResponse = ConsultResponse.builder()
				.consultNo(consult.getConsultNo())
				.startedTime(consult.getStartedTime())
				.closedTime(consult.getClosedTime())
				.theraId(consult.getTheraId())
				.childId(consult.getChildId())
				.parentId(consult.getParentId())
				.memo(consult.getMemo())
				.record(consult.getRecord()).build();
		
		return consultResponse;
	}
}
