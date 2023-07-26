<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Test</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/layout.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/commons.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/test.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
    <script src="${path}/resources/js/admin-edit.js"></script>

</head>
<body>
<h2 class="center">상품 목록 출력 테스트</h2>
<hr>
<br>
<div class="container-1000 center">
    <div class="row pb-20">
        <a class="link" href="${pageContext.request.contextPath}/product/productList">
            <h2>상품 목록 리스트</h2>
        </a>
    </div>
    <div class="row flex w-100 center">
        <!-- 검색창 -->
        <div id="searchBar" class="row">
            <select name="searchType" id="searchType"  class="form-input small me-5 qty-selector" onchange="keywordClear()">
                <option value="all" ${pageMaker.criteria.searchType==null ? 'selected' : ''}>전체</option>
                <option value="member_id" ${pageMaker.criteria.searchType == 'product_name' ? 'selected' : ''}>상품명</option>
                <option value="member_name" ${pageMaker.criteria.searchType == 'product_brand' ? 'selected' : ''}>브랜드</option>
            </select>
            <input type="text" name="keyword" id="keyword" class="form-input small w-35 me-5" value="${pageMaker.criteria.keyword}">
            <button id="searchBtn" class="form-btn small positive" onclick="searchDB()">검색</button>
        </div>
    </div>
</div>
<br>
<div>
    <table class="container-750 table table-border center">
        <thead>
        <tr>
            <th>상품번호</th>
            <th>상품명</th>
            <th>브랜드</th>
            <th>가 격</th>
            <th>재 고</th>
            <th>판매량</th>
            <th>등록일</th>
            <th class="w-15">관 리</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty requestScope.plist}">
            <tr>
                <td colspan="5">출력할 내용이 없습니다.</td>
            </tr>
        </c:if>
        <c:if test="${not empty requestScope.plist}">
            <c:forEach var="p" items="${requestScope.plist}">
                <tr>
                    <td>${p.product_no}</td>
                    <td>${p.product_name}</td>
                    <td>${p.product_brand}</td>
                    <td>${p.product_price}</td>
                    <td>${p.product_stock}</td>
                    <td>${p.product_sellcount}</td>
                    <td>${p.product_regdate}</td>
                    <td>
                        <a class="btn-border" href="${pageContext.request.contextPath}/admin/productManage/productUpdate?product_no=${p.product_no}">수정</a>
                        <a class="btn-border" href="">삭제</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>
<br>
<hr>
<br>
<a href="javascript:history.go(-1)">뒤로</a>
<a href="/home">HOME 으로</a>
</body>
</html>
