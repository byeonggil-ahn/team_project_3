package com.example.perfume01.dao;

import com.example.perfume01.criteria.SearchCriteria;
import com.example.perfume01.vo.MemberVO;

import java.util.List;

public interface MemberDAO {

    List<MemberVO> selectList();

    MemberVO selectOne(MemberVO vo);

    MemberVO selectUser(String loginID);

    // 내정보 보기
    MemberVO myinfo(String member_id);

    int changeInfo(MemberVO vo);

    int minsert(MemberVO vo);

    int mupdate(MemberVO vo);

    int pointupdate(MemberVO vo);

    int mdelete(MemberVO vo);


    // === 맴버 검색 항목 ===
    List<MemberVO> searchMember(String column, String keyword, int begin, int end);

    List<MemberVO> searchList(SearchCriteria criteria);
    int searchTotalCount(SearchCriteria criteria);


    // 이메일 인증 관련
    int updateMailKey(MemberVO vo);

    int updateMailAuth(MemberVO vo);

    int emailAuthFail(String member_id);

    int changePassword(String member_id, String member_pw);

    int findPw(String member_id, String member_email);
}
