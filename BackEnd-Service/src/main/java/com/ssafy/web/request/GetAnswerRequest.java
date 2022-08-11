package com.ssafy.web.request;

import lombok.Getter;

/**상담사가 문진표 보기 눌렀을 때 */
@Getter
public class GetAnswerRequest {
	String child_name;
	String parent_id;
}
