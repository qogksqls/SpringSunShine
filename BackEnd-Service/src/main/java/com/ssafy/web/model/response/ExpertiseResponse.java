package com.ssafy.web.model.response;

import com.ssafy.web.db.entity.Expertise;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExpertiseResponse {
	private int expertise_no;
	private String kind;
	
	public Expertise toEntity() {
		return Expertise.builder().expertiseNo(expertise_no).kind(kind).build();
	}

	public static ExpertiseResponse fromEntity(Expertise expertise) {
		return ExpertiseResponse.builder().expertise_no(expertise.getExpertiseNo()).kind(expertise.getKind()).build();
	}

	@Builder
	public ExpertiseResponse(int expertise_no, String kind) {
		super();
		this.expertise_no = expertise_no;
		this.kind = kind;
	}
}
