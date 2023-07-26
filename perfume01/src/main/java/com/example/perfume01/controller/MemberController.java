package com.example.perfume01.controller;

        import com.example.perfume01.criteria.PageMaker;
        import com.example.perfume01.criteria.SearchCriteria;
        import com.example.perfume01.dao.MemberDAO;
        import com.example.perfume01.dto.ResponseDTO;
        import com.example.perfume01.dto.UserDTO;
        import com.example.perfume01.mail.MailHandler;
        import com.example.perfume01.mail.RandomComponent;
        import com.example.perfume01.service.MemberService;
        import com.example.perfume01.vo.MemberVO;
        import lombok.AllArgsConstructor;
        import lombok.NoArgsConstructor;
        import lombok.extern.log4j.Log4j2;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.mail.SimpleMailMessage;
        import org.springframework.mail.javamail.JavaMailSender;
        import org.springframework.mail.javamail.JavaMailSenderImpl;
        import org.springframework.security.authentication.AuthenticationManager;
        import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
        import org.springframework.security.core.Authentication;
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.security.crypto.password.PasswordEncoder;
        import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.servlet.mvc.support.RedirectAttributes;

        import javax.servlet.http.Cookie;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.util.List;
        import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/member")
@Log4j2
@AllArgsConstructor @NoArgsConstructor
public class MemberController {

    MemberVO vo;

    @Autowired
    MemberService service;

    @Autowired
    MemberDAO dao;

    @Autowired
    PasswordEncoder passwordEncoder;

    AuthenticationManager authenticationManager;

    @Autowired
    RandomComponent randomComponent;

    MailHandler mailHandler;

    // 관리자페이지 로그인 테스트용 (더미)
    @PostMapping("/loginRest")
    public String login(HttpServletRequest request, Model model) {
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

    @GetMapping("/logoutRest")
    public String logout(HttpServletRequest request, HttpSession session) {
        System.out.println("로그아웃");
        session.invalidate();
        return "redirect:/home";
    }


    // ====== 여기부터 실제로 사용할 앤드 포인트 코드 ==========

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }

        // 클라이언트 측에서 쿠키 삭제
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    cookie.setMaxAge(0); // 쿠키 만료 시간을 0으로 설정하여 삭제
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/memberListRest")
    public List<MemberVO> memberList(SearchCriteria criteria, PageMaker pageMaker) {
        //criteria.setSnoEno();

        if (criteria.getSearchType() != null && criteria.getSearchType().length() < 1) {
            criteria.setSearchType(null);
        }

        List<MemberVO> rtn;
        rtn = service.searchList(criteria);

        return rtn;
    }

    @GetMapping("/memberDetailRest")
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


    @PostMapping("/memberUpdateRest")
    public String mupdate(Model model,MemberVO vo) throws Exception {
        model.addAttribute("mlist", vo);
        String uri = "/member/memberDetail";
        vo.setMember_pw(null);

        //회원 정보 업데이트
        service.mupdate(vo);
        return uri;
    }


    @GetMapping("/listRest")
    public String memberList(Model model, @RequestParam(name = "column", defaultValue = "member_id") String column,
                             @RequestParam(name = "keyword", defaultValue = "") String keyword,
                             @RequestParam(name = "begin", defaultValue = "1") int begin,
                             @RequestParam(name = "end", defaultValue = "10") int end) {

        List<MemberVO> members = dao.searchMember(column, keyword, begin, end);
        model.addAttribute("members", members);

        return "/member/memberList";
    }

    // ====== 검색 관련 컨트롤러 ======

    // mypage 내정보 보기
    @GetMapping("/myinfo")
     public MemberVO myinfo(@RequestParam("member_id") String member_id) {
        MemberVO rtn = service.myinfo(member_id);
        return rtn;
    }


    @GetMapping("/mdeleteRest")
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

    //회원 가입
    @PostMapping("/reg_user")
    public ResponseDTO regUser(MemberVO memberVO){
        ResponseDTO rtn = new ResponseDTO();
        try {
            log.info(rtn);
            memberVO.setMember_pw(passwordEncoder.encode(memberVO.getMember_pw()));
            int result = service.minsert(memberVO);

            if (result == 1) {
                rtn.setErr_code(1);
                rtn.setMsg("정상등록");
            } else {
                rtn.setErr_code(0);
            }

        } catch (Exception e){
            rtn.setErr_code(0);
            rtn.setMsg("500!!!!");
            e.printStackTrace();
        }
        return rtn;
    }

    // 일반 로그인(암호화)
    @PostMapping("/login")
    public ResponseEntity<?> memberlogin(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                              @RequestBody MemberVO memberVO
                             ) {

        String pw = memberVO.getMember_pw();
//        String uri = "/";

        memberVO = service.selectOne(memberVO);
        if (memberVO != null && passwordEncoder.matches(pw,memberVO.getMember_pw())) {
                request.getSession().setAttribute("loginID", memberVO.getMember_id());
                request.getSession().setAttribute("loginName", memberVO.getMember_name());
//                session.setAttribute("loginID", memberVO.getMember_id());
//                session.setAttribute("loginName",memberVO.getMember_name());
                System.out.println("로그인에서 세션확인 : " + request.getSession().getAttribute("loginID"));

                // 쿠키 설정 (사실 자동생성이어서 필요 없는 코드라고 판단됨)
//                Cookie sessionCookie = new Cookie("JSESSIONID", request.getSession().getId());
//                sessionCookie.setPath("/");
//                response.addCookie(sessionCookie);

                UserDTO dto = UserDTO.builder().
                        loginID(memberVO.getMember_id()).
                        loginName(memberVO.getMember_name()).
                        build();
                return ResponseEntity.ok().body(dto);

            } else {
//                uri = "member/login";
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("로그인 오류");
        } // if
    }

    // 회원 정보 변경하기
    @PostMapping("/changeInfo")
    public ResponseEntity<?> changeInfo(@RequestBody MemberVO vo) {
        try {
            vo.setMember_pw(null);
            System.out.println("에러확인1, 맴버 아이디 : " + vo);
            int result = service.changeInfo(vo);
            System.out.println("에러확인2");
            if (result > 0) {
                return ResponseEntity.status(HttpStatus.OK).body("수정성공");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수정실패1");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수정실패2");
        }
    }

    @PostMapping("/pointupdate")
    public ResponseEntity<?> pointUpdate(@RequestBody MemberVO vo) {
        try {
            int result = service.pointupdate(vo);
            System.out.println("에러확인 = 멤버아이디 : " + vo);
            if (result > 0) {
                return ResponseEntity.status(HttpStatus.OK).body("포인트 수정성공");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("포인트 수정실패1");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("포인트 수정실패2");
        }
    }
    
//    @PostMapping("/changeInfo")
//    public String changeInfo(MemberVO vo) {
//        vo.setMember_pw(null);
//        service.changeInfo(vo);
//
//        return "정보변경완료";
//    }


    // 아이디 찾기
    // 완성해야될것
    @PostMapping("/findId")
    public String findId() {
        try {

        } catch (Exception e) {

        }
        return "null";
    }

    // 이메일로 비밀번호 재설정
    @PostMapping("/findpw")
    public ResponseEntity<?> findPw(@RequestParam("member_id") String member_id,
                                    @RequestParam("member_email") String member_email) {
        vo = service.myinfo(member_id);
        
        System.out.println("비번찾기 맴버아이디출력 " + vo.getMember_id());
        System.out.println("비번찾기 맴버이메일출력" + vo.getMember_email());
        
        if (vo == null || !vo.getMember_email().equals(member_email)) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("정보 불일치");
        }
        // 이메일이 일치하면 임시 비밀번호 생성
        String tempPW = randomComponent.generateString();
        service.changePassword(vo.getMember_id(), tempPW);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(vo.getMember_email());
        msg.setSubject("[Ateam Perfume] 임시 비밀번호 발급");
        msg.setText("발급된 비밀번호는 " + tempPW + "입니다. 로그인 후 비밀번호 변경을 해주시길 바랍니다.");
        JavaMailSender sender = new JavaMailSenderImpl();
        sender.send(msg);

        return ResponseEntity.ok("비밀번호 재설정 성공");
    }


    @GetMapping("/selectLoginMemberId")
    public String selectLoginMemberId(HttpServletRequest request, HttpSession session) {
        session = request.getSession();
        String memberId = (String) session.getAttribute("loginID");
        System.out.println(memberId);
        return memberId;
    }


    @GetMapping("/userinfo")
    public MemberVO selectUser(@RequestParam String member_id) {
        //String loginID = (String) session.getAttribute("loginID");
        /*if (loginID != null) {
            return service.selectUser(loginID);
        }*/
        return service.selectUser(member_id);
    }


    //스프링 시큐리티 로그인
//    @PostMapping("/login")
//    public String login(HttpSession session, @RequestBody MemberVO memberVO) {
//        MemberVO dbMember = service.selectOne(memberVO);
//        if (dbMember != null && passwordEncoder.matches(memberVO.getMember_pw(), dbMember.getMember_pw())) {
//            Authentication authentication = new UsernamePasswordAuthenticationToken(dbMember, null, null);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
//                    SecurityContextHolder.getContext());
//
//            // 사용자 정보 확인
//            String userName = dbMember.getMember_name();
//            String userId = dbMember.getMember_id();
//            String userTel = dbMember.getMember_phone();
//
//            System.out.printf("유저 네임 : %s, 유저 아이디 : %s, 유저 연락처 : %s", userName, userId, userTel);
//
//            return "Login success";
//        } else {
//            return "Login faild";
//        }
//    }

    @GetMapping("/result")
    public String result(HttpSession session) {
        String rtn = session.getId();
        return rtn;
    }

    @GetMapping("/selectlist")
    public List<MemberVO> selectList() {
        List<MemberVO> rtn = service.selectList();
        return rtn;
    }



}

