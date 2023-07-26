<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>회원 상세 정보</title>
        <link rel="stylesheet" type="text/css" href="${path}/resources/css/reset.css">
        <link rel="stylesheet" type="text/css" href="${path}/resources/css/layout.css">
        <link rel="stylesheet" type="text/css" href="${path}/resources/css/commons.css">
        <link rel="stylesheet" type="text/css" href="${path}/resources/css/test.css">
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    </head>
    <body>

    <!-- 해더 추가부분 -->
    <c:if test="${empty requestScope.mlist}">
        <h2>불러올 데이터가 진짜 없는디?</h2>
    </c:if>
    <c:if test="${not empty requestScope.mlist}">
        <div class="row center">
            <h2>${requestScope.mlist.member_id} 님 회원 정보</h2>
        </div>
        <div class="container-650 ms-100 center">
            <div class="row">
            </div>
            <div class="w-70">
                <table class="table table-border">
                    <tr>
                        <th class="w-25">아이디</th>
                        <td>${requestScope.mlist.member_id}</td>
                    </tr>
                    <tr>
                        <th>닉네임</th>
                        <td>${requestScope.mlist.member_nick}</td>
                    </tr>
                    <tr>
                        <th>전화번호</th>
                        <td>${requestScope.mlist.member_phone}</td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td>${requestScope.mlist.member_email}</td>
                    </tr>
                    <tr>
                        <th>주소</th>
                        <td>${requestScope.mlist.member_basic_addr}</td>
                    </tr>
                    <tr>
                        <th>회원등급</th>
                        <td>${requestScope.mlist.member_role}</td>
                    </tr>
                    <tr>
                        <th>포인트</th>
                        <td>${requestScope.mlist.member_point}</td>
                    </tr>
                    <tr>
                        <th>가입일</th>
                        <td>${requestScope.mlist.member_joindate}</td>
                    </tr>

                </table>
                <div class="w-100 flex">
                    <div class="row w-50 pe-5">
                        <a href="/admin/member/memberDetail?member_id=${requestScope.mlist.member_id}&jCode=U" class="form-btn neutral small w-100">개인정보변경</a>
                    </div>
                    <div class="row w-50 center">
                        <a href="/admin/member/mdelete?member_id=${requestScope.mlist.member_id}" class="form-btn neutral small w-100" onclick="return confirm('정말 탈퇴시키겠습니까?')">회원 탈퇴</a>
                    </div>
                </div>
                <div>
                    <a href="javascript:history.go(-1)" class="form-btn positive small w-100">뒤로</a>
                    <hr>
                    <a href="/home" class="form-btn positive small w-100">HOME</a>
                </div>
            </div>
        </div>
    </c:if>
    </body>
</html>
