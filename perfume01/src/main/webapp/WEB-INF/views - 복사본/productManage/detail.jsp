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
    <div class="row center">
        <h2>${requestScope.plist} 상세 정보</h2>
        <c:forEach var="p" items="${plist}">
            <pre>
                ${p.product_no}
                ${p.product_name}
                ${p.product_brand}
            </pre>
        </c:forEach>
    </div>
    <div class="container-650 ms-100 center">
        <div class="row">
        </div>
        <div class="w-70">
            <table class="table table-qna mb-30">
                <tr>
                    <th class="w-25">상품명</th>
                    <td>${requestScope.plist.product_name}</td>
                </tr>
                <tr>
                    <th>브랜드</th>
                    <td>${requestScope.tag.tag_no}</td>
                </tr>
                <tr>
                    <th>가격</th>
                    <td>${requestScope.plist.product_price}</td>
                </tr>
                <tr>
                    <th>재고</th>
                    <td>${requestScope.plist.product_stock}</td>
                </tr>
                <tr>
                    <th>배송비</th>
                    <td>${requestScope.plist.product_delivery_price}</td>
                </tr>
                <tr>
                    <th>누적판매수</th>
                    <td>${requestScope.plist.product_sellcount}</td>
                </tr>
                <tr>
                    <th>등록일</th>
                    <td>${requestScope.plist.product_regdata}</td>
                </tr>
                <tr>
                    <th>대표이미지</th>
                    <td>${requestScope.plist.product_mainimg}</td>
                </tr>
                <tr>
                    <th>상세이미지</th>
                    <td>${requestScope.plist.product_subimg}</td>
                </tr>
                <tr>
                    <th>상품 설명</th>
                    <td>${requestScope.plist.product_content}</td>
                </tr>

            </table>
            <div class="w-100 flex">
                <div class="row w-50 pe-5">
                    <a href="/productManage/detail?product_no=${requestScope.plist.product_no}&jCode=edit" class="form-btn neutral small w-100">상품정보 수정</a>
                </div>
            </div>
            <div>
                <a href="javascript:history.go(-1)" class="form-btn positive small w-100">뒤로</a>
                <hr>
                <a href="/home" class="form-btn positive small w-100">HOME</a>
            </div>
        </div>
    </div>
</body>
</html>
