package com.ssafy.web.model.response;


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
        String profile_url;
        String thera_intro;
}