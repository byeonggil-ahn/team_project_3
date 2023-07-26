package com.example.perfume01.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
//    private String token;
    private String loginID;
    private String loginName;
//    private String email;
//    private String password;
}
