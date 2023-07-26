<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Perfume 관리자 페이지</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <!-- chart.js cdn -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <!-- chart 비동기 스크립트 -->
    <script src="${path}/resources/js/graph.js"></script>
    <%--        <script src="${path}/resources/js/product-category-stat.js"></script>--%>

</head>
<body>
<h2>관리자 페이지</h2>
<div style="display: flex" class="container-1100">
    <div >
        <div>
            <c:if test="${not empty sessionScope.loginID}">
                ${sessionScope.loginID} 님 로그인 하셨습니다.&nbsp;<br>
                <a href="/member/logout">로그아웃</a>
            </c:if><br><hr>
            <a href="/admin/member/memberList">회원관리</a><br>
            <a href="/admin/productManage/productList">상품목록 출력</a><br>
            <a href="/admin/productManage/list">상품관리</a><br>
            <a>게시판 관리</a><br>
            <a href="/sell/list">판매 내역</a>
        </div>
        <br>
        <hr>
        <br>
    </div>
    <div>
        <h2>그래프 테스트</h2>
        <hr>
        <div class="w-100">
            <canvas id="canvas"></canvas>
        </div>
    </div>
</div>


</body>
</html>