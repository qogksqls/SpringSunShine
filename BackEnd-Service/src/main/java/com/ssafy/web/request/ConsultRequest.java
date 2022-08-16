package com.ssafy.web.request;

import java.util.Date;

import com.ssafy.web.db.entity.Consult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultRequest {
	private int consultNo;
	private Date startedTime;
	private Date closedTime;
	private String theraId;
	private String childId;
	private String parentId;
	private String memo;
	private String record;
	
	public Consult toCreateEntity() {
		Date now = new Date();
		return new Consult(now, theraId, childId, parentId);
	}
	
}
