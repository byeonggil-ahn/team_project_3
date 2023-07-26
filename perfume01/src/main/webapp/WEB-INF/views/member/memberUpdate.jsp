<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회정 정보 수정 페이지</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/layout.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/commons.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/test.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="${path}/resources/js/find-address.js"></script>
<%--    <script src="${path}/resources/js/admin-edit.js"></script>--%>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
        const contextPath = "${pageContext.request.contextPath}";
        var checkUnload = true;
        $(window).on("beforeunload", function(){
            if(checkUnload) return "이 페이지를 벗어나면 작성된 내용은 저장되지 않습니다.";
        });
    </script>
</head>
<body>
<form action="memberUpdate" method="Post" class="content admin-edit-form">
    <input type="hidden" name="member_id" value="${requestScope.mlist.member_id}">
    <div class="container-600">
        <div class="row center">
            <h2>회원 정보 변경</h2>
        </div>
        <div class="row">
            <label>닉네임</label>
            <input type="text" name="member_nick" class="form-input w-100 medium light" value="${requestScope.mlist.member_id}">
            <div class="valid-message">사용 가능한 닉네임입니다</div>
            <div class="invalid-message">닉네임은 한글 또는 숫자 2~10자로 작성하세요</div>
            <div class="invalid-message2">이미 사용중인 닉네임입니다</div>
        </div>
        <div class="row">
            <label>전화번호</label>
            <input type="tel" name="member_phone" required class="form-input w-100 medium light"  value="${requestScope.mlist.member_phone}">
            <div class="valid-message">사용 가능한 전화번호입니다</div>
            <div class="invalid-message">올바른 전화번호가 아닙니다</div>
        </div>
        <div class="row">
            <label>이메일</label>
            <input type="email" name="member_email" required class="form-input w-100 medium light" value="${requestScope.mlist.member_email}">
            <div class="valid-message">사용 가능한 이메일 입니다</div>
            <div class="invalid-message">올바른 이메일 형식을 입력해주세요</div>
            <div class="invalid-message2">이미 사용중인 이메일입니다</div>
        </div>
        <div class="row">
            <label>주소</label><div></div>
            <input type="text" name="member_post" class="form-input medium light" placeholder="우편번호" readonly value="${requestScope.mlist.member_post}">
            <button type="button" class="form-btn neutral find-address-btn medium light">검색</button>
        </div>
        <div class="row">
            <input type="text" name="member_basic_addr" class="form-input w-100 medium light" placeholder="기본주소" readonly value="${requestScope.mlist.member_basic_addr}">
        </div>
        <div class="row">
            <input type="text" name="member_detail_addr" required class="form-input w-100 medium light"  placeholder="상세주소" value="${requestScope.mlist.member_detail_addr}">
            <div class="invalid-message">주소를 작성해주세요</div>
        </div>

        <div class="row">
            <label class="form-label w-100">회원등급</label>
            <c:choose>
                <c:when test="${requestScope.mlist.member_role == '일반회원'}">
                    <select name="member_role" class="form-input small light w-20">
                        <option selected>일반회원</option>
                        <option>관리자</option>
                    </select>
                </c:when>
                <c:otherwise>
                    <select name="member_role" class="form-input small light w-20">
                        <option>관리자</option>
                        <option selected>일반회원</option>
                    </select>
                </c:otherwise>
            </c:choose>

            (현재 등급 : ${requestScope.mlist.member_role})
        </div>

        <div class="row">
            <label class="form-label w-100 me-15">포인트</label>
            <input type="text" name="member_point" value="${requestScope.mlist.member_point}" required class="form-input w-20 small light">
            (현재 포인트 : ${requestScope.mlist.member_point} point)
        </div>

        <div class="row right">
            <a href="${pageContext.request.contextPath}/admin/member/memberList" class="form-btn neutral medium">목록</a>
<%--            <button type="submit" id="saveBtn" class="form-btn positive medium">변경</button>--%>
            <input type="submit" id="saveBtn" class="form-btn positive medium" value="변경">
        </div>
        <script>
            $("#saveBtn").on("click", function(){
                checkUnload = false;
                $("#saveForm").submit();
            });
        </script>
    </div>
</form>
</body>
</html>
