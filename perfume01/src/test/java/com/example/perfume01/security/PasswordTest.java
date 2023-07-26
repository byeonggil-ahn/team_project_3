package com.example.perfume01.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTest {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void testEncoder() {
        String password = "asdf";
        String enPw = passwordEncoder.encode(password);

        System.out.println("인코딩된 비밀번호 : " + enPw);
        boolean matchResult = passwordEncoder.matches(password, enPw);
        System.out.println("비밀번호 확인 결과 : " + matchResult);
    }
}
