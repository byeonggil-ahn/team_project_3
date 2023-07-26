package com.example.perfume01.service;

import com.example.perfume01.mail.MailHandler;
import com.example.perfume01.mail.TempKey;
import mapperInterface.MemberMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class PasswordResetService {
/*
    private final JavaMailSender mailSender;
    private final MemberMapper memberMapper;
    private final SqlSession sqlSession;

    @Autowired
    public PasswordResetService(JavaMailSender mailSender, MemberMapper memberMapper, SqlSession sqlSession) {
        this.mailSender = mailSender;
        this.memberMapper = memberMapper;
        this.sqlSession = sqlSession;
    }

    @Transactional
    public void sendPasswordResetEmail(String userEmail) {
        try {
            // 이메일 전송 로직...


            // 비밀번호 변경 로직
            String resetKey = generateResetKey();
            String memberId = "your-member-id"; // 비밀번호를 변경할 사용자의 ID

            // 비밀번호 변경 SQL 실행
            Map<String, Object> params = new HashMap<>();
            params.put("member_pw", resetKey);
            params.put("member_id", memberId);
            memberMapper.changePassword(memberId);

            // 이메일 전송 완료 후 트랜잭션 커밋
            sqlSession.commit();
        } catch (Exception e) {
            // 예외 발생 시 트랜잭션 롤백
            sqlSession.rollback();
            throw e;
        }
    }

    private String generateResetKey() {
        TempKey tempKey = new TempKey();
        return tempKey.getKey(10, false);
    }

    // 이메일 전송 등의 코드...
    */
}