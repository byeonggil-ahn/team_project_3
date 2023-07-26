package com.example.perfume01.controller;

import com.example.perfume01.criteria.PageMaker;
import com.example.perfume01.criteria.SearchCriteria;
import com.example.perfume01.dao.MemberDAO;
import com.example.perfume01.service.MemberService;
import com.example.perfume01.vo.MemberVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/member")
@Log4j2
@AllArgsConstructor

public class AdminMemberController {

    MemberService service;
    MemberDAO dao;

    @GetMapping("/mLoginForm")
    public void mLoginForm() {}

    /*
    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model, MemberVO vo) {
        String pw = vo.getMember_pw();
        String uri = "/redirect:home";

        vo = service.selectOne(vo);
        if (vo != null) {
            if (passwordEncoder.matches(pw, vo.getMember_pw())) {
                request.getSession().setAttribute("loginID", vo.getMember_id());
                request.getSession().setAttribute("loginName", vo.getMember_name());
            } else {
                model.addAttribute("msg", "패스워드 오류");
                uri = "/member/mLoginForm";
            }
        } else {
            model.addAttribute("msg", "아이디 오류");
            uri = "/member/mLoginForm";
        }
        return uri;
    }

     */
    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model, MemberVO vo) {
        String pw = vo.getMember_pw();
        String uri = "redirect:/home";

        vo = service.selectOne(vo);
        if (vo != null) {
            if (pw.equals(vo.getMember_pw())) { // 비밀번호 직접 비교
                request.getSession().setAttribute("loginID", vo.getMember_id());
                request.getSession().setAttribute("loginName", vo.getMember_name());
            } else {
                model.addAttribute("msg", "패스워드 오류");
                uri = "/member/mLoginForm";
            }
        } else {
            model.addAttribute("msg", "아이디 오류");
            uri = "/member/mLoginForm";
        }
        return uri;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpSession session) {
        System.out.println("로그아웃");
        session.invalidate();
        return "redirect:/home";
    }


    @GetMapping("/memberList")
    public String memberList(Model model, SearchCriteria criteria, PageMaker pageMaker) {
        criteria.setSnoEno();

        if (criteria.getSearchType() != null && criteria.getSearchType().length() < 1) {
            criteria.setSearchType(null);
        }
        model.addAttribute("mlist", service.searchList(criteria));

        pageMaker.setCriteria(criteria);
        pageMaker.setTotalRowsCount(service.searchTotalCount(criteria));
        model.addAttribute("pageMaker", pageMaker);

        return "/member/memberList";
    }


    @GetMapping("/memberDetail")
    public String memberDetail(HttpServletRequest request, Model model, MemberVO vo) {
        // id확인 로직은 나중에 구현

        if (vo.getMember_id() == null || vo.getMember_id().length() < 1) {
            vo.setMember_id((String) request.getSession().getAttribute("loginID"));
        }

        String uri = "/member/memberDetail";

        // service 처리
        model.addAttribute("mlist", service.selectOne(vo));
        if ("U".equals(request.getParameter("jCode"))) {
            uri = "/member/memberUpdate";
        }
        return uri;
    }



    @PostMapping("/memberUpdate")
    public String mupdate(HttpServletRequest request, Model model,MemberVO vo) throws Exception {
        model.addAttribute("mlist", vo);
        String uri = "/member/memberDetail";

        vo.setMember_pw(null);
        
        //회원 정보 업데이트
        service.mupdate(vo);
        return uri;
    }


    @GetMapping("/list")
    public String memberList(Model model, @RequestParam(name = "column", defaultValue = "member_id") String column,
                             @RequestParam(name = "keyword", defaultValue = "") String keyword,
                             @RequestParam(name = "begin", defaultValue = "1") int begin,
                             @RequestParam(name = "end", defaultValue = "10") int end) {

        List<MemberVO> members = dao.searchMember(column, keyword, begin, end);
        model.addAttribute("members", members);

        return "/member/memberList";
    }

    // ====== 검색 관련 컨트롤러 ======


    @GetMapping("/mdelete")
    public String mdelete(HttpServletRequest request, MemberVO vo, RedirectAttributes rttr) {
        // 로그인 한 유저가 탈퇴되게 하는 코드
        // vo.setMember_id((String) request.getSession().getAttribute("loginID"));
        
        // vo에서 클릭한 member_id를 가져오게 하는 코드
        // 대신 프론트에서 member_id를 쏴줄 필요성이 있음
        vo.setMember_id(vo.getMember_id());
        String uri = "redirect:memberList";
        if (service.mdelete(vo) > 0) {
            request.getSession().invalidate();
            rttr.addFlashAttribute("msg", "회원 삭제에 성공하였습니다.");
        } else {
            rttr.addFlashAttribute("msg", "회원 삭제에 실패하였습니다.");
        }
        return uri;
    }
}
