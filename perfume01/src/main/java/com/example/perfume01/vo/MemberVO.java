package com.example.perfume01.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.security.config.web.server.ServerHttpSecurity;

@Data
public class MemberVO {

    private String member_id;
    private String member_name;
    private String member_nick;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String member_pw;
    private String member_phone;
    private String member_email;
    private String member_post;
    private String member_basic_addr;
    private String member_detail_addr;
    private String member_joindate;
    private String member_role;
    private int member_point;
}
