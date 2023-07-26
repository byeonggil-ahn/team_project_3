package com.example.perfume01.service;

import com.example.perfume01.criteria.SearchCriteria;
import com.example.perfume01.dao.MemberDAO;
import com.example.perfume01.mail.TempKey;
import com.example.perfume01.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberDAO dao;

    @Autowired
    JavaMailSender mailSender;

    @Override
    public List<MemberVO> selectList() {
        return dao.selectList();
    }

    @Override
    public MemberVO selectOne(MemberVO vo) {
        return dao.selectOne(vo);
    }

    @Override
    public MemberVO selectUser(String loginID) {
        return dao.selectUser(loginID);
    }

    @Override
    public MemberVO myinfo(String member_id) {
        return dao.myinfo(member_id);
    }

    @Override
    public int changeInfo(MemberVO vo) {
        return dao.changeInfo(vo);
    }


    @Override
    public int minsert(MemberVO vo) {
        return dao.minsert(vo);
    }




    @Override
    public int mupdate(MemberVO vo) {
        return dao.mupdate(vo);
    }

    @Override
    public int pointupdate(MemberVO vo) {
        return dao.pointupdate(vo);
    }

    @Override
    public int mdelete(MemberVO vo) {
        return dao.mdelete(vo);
    }

    @Override
    public List<MemberVO> searchList(SearchCriteria criteria) {
        return dao.searchList(criteria);
    }

    @Override
    public int searchTotalCount(SearchCriteria criteria) {
        return dao.searchTotalCount(criteria);
    }

    @Override
    public int changePassword(String member_id, String member_pw) {
        return dao.changePassword(member_id, member_pw);
    }

    @Override
    public int updateMailKey(MemberVO vo) {
        return dao.updateMailKey(vo);
    }

    @Override
    public int updateMailAuth(MemberVO vo) {
        return dao.updateMailAuth(vo);
    }

    @Override
    public int emailAuthFail(String member_id) {
        return dao.emailAuthFail(member_id);
    }

    @Override
    public int findPw(String member_id, String member_email) {
        return dao.findPw(member_id, member_email);
    }


}
