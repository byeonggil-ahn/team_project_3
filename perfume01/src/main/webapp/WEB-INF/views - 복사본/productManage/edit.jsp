<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>상품 정보 수정</title>
        <link rel="stylesheet" type="text/css" href="${path}/resources/css/reset.css">
        <link rel="stylesheet" type="text/css" href="${path}/resources/css/load.css">
        <link rel="stylesheet" type="text/css" href="${path}/resources/css/layout.css">
        <link rel="stylesheet" type="text/css" href="${path}/resources/css/commons.css">
        <link rel="stylesheet" type="text/css" href="${path}/resources/css/test.css">
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
                integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
                integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
                crossorigin="anonymous"></script>
        <script src="${path}/resources/js/product-edit-checker.js"></script>
        <!-- LightPick -->
        <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/lightpick@1.6.2/css/lightpick.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/lightpick@1.6.2/lightpick.min.js"></script>
        <script src="${path}/resources/js/date-picker.js"></script>

        <style>
            label, .invalid-message {
                padding-left: 20px
            }
            textarea {
                min-height: 200px;
                resize: none;
                border-radius: 25px;
                outline: none;
                padding-left: 1.3em;
                padding-right: 1.3em;
                border-color: #776BFF;
                font-size: 15px;
                padding-top: 1em;
                padding-bottom: 1em;
                border-width: 2px;
                border-style: solid;
            }
            .category-label {
                padding: 0;
                flex-grow: 1;
                display: flex;
                align-items: center;
                justify-content: center;
            }
            .category-label > label {
                padding-left: 5px;
            }
            .category-title:not(:last-child) {
                margin-right: 60px;
            }
        </style>
        <script type="text/javascript">
            var backButton = document.querySelector(".back-btn");
            backButton.addEventListener("click", function() {
                var result = confirm("되돌아가면 수정하던 내용이 저장되지 않습니다.\n정말 돌아가시겠습니까?");
                if (!result) return false;
            });
        </script>
    </head>
    <body>
        <div class="container-1000">
            <!-- 상품목록 -->
            <div class="row pb-20">
<%--                <form action="${pageContext.request.contextPath}/productManage/edit"  method="Post" class="content admin-edit-form">--%>
                <form action="edit" method="Post" class="content admin-edit-form">
                    <div class="row pb-20">
                        <h1>상품 정보 수정</h1>
                    </div>
                    <div>
                        <h2>상품 정보 테스트</h2>
                        <c:if test="${not empty requestScope.productDTO}">
                            <p>클릭한 상품 정보가 제대로 들어오는지 태스트중</p>
                            <p>${requestScope.productDTO}</p><br>
                        </c:if>
                    </div>
                    <div class="row">
                        <label>상품번호</label>
                        <input type="text" class="form-input medium w-100" name="product_no" value="${requestScope.productDTO.product_no}" readonly>
                    </div>
                    <div class="row">
                        <label>상품명</label>
                        <input type="text" class="form-input medium w-100" name="product_name" value="${requestScope.productDTO
                        .product_name}">
                        <div class="invalid-message">브랜드명은 한글, 영어, 숫자, 특수문자를 포함한 1~10자 입니다.</div>
                    </div>
                    <div class="row">
                        <label>브랜드</label>
                        <input type="text" class="form-input medium w-100" name="product_brand" value="${requestScope.productDTO
                        .product_brand}">
                        <div class="invalid-message">브랜드명은 한글, 영어, 숫자, 특수문자를 포함한 1~10자 입니다.</div>
                    </div>
                    <div class="row pb-5">
                        <div class="w-100 ps-20">카테고리</div>
                        <c:choose>
                            <c:when test="${productDTO.tag_no == 0}">
                                <div class="w-100 category-label invalid">
                                    <div class="category-label flex">
                                        <input type="radio" id="tag1" name="tag_no" value=1><label class="category-title" for="tag1">아쿠아마린</label>
                                        <input type="radio" id="tag2" name="tag_no" value=2><label class="category-title" for="tag2">아로마틱</label>
                                        <input type="radio" id="tag3" name="tag_no" value=3><label class="category-title" for="tag3">앰버</label>
                                        <input type="radio" id="tag4" name="tag_no" value=4><label class="category-title" for="tag4">우디</label>
                                        <input type="radio" id="tag5" name="tag_no" value=5><label class="category-title" for="tag5">달콤</label>
                                        <input type="radio" id="tag6" name="tag_no" value=6><label class="category-title" for="tag6">스모키</label>
                                        <input type="radio" id="tag7" name="tag_no" value=7><label class="category-title" for="tag7">과일</label>
                                        <input type="radio" id="tag8" name="tag_no" value=8><label class="category-title" for="tag8">그린</label>
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${productDTO.tag_no == 1}">
                                <div class="w-100 category-label">
                                    <div class="category-label flex">
                                        <input type="radio" id="tag1" name="tag_no" value=1><label class="category-title" for="tag1">아쿠아마린</label>
                                        <input type="radio" id="tag2" name="tag_no" value=2><label class="category-title" for="tag2">아로마틱</label>
                                        <input type="radio" id="tag3" name="tag_no" value=3><label class="category-title" for="tag3">앰버</label>
                                        <input type="radio" id="tag4" name="tag_no" value=4><label class="category-title" for="tag4">우디</label>
                                        <input type="radio" id="tag5" name="tag_no" value=5><label class="category-title" for="tag5">달콤</label>
                                        <input type="radio" id="tag6" name="tag_no" value=6><label class="category-title" for="tag6">스모키</label>
                                        <input type="radio" id="tag7" name="tag_no" value=7><label class="category-title" for="tag7">과일</label>
                                        <input type="radio" id="tag8" name="tag_no" value=8><label class="category-title" for="tag8">그린</label>
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${productDTO.tag_no == 2}">
                                <div class="w-100 category-label">
                                    <div class="category-label flex">
                                        <input type="radio" id="tag1" name="tag_no" value=1><label class="category-title" for="tag1">아쿠아마린</label>
                                        <input type="radio" id="tag2" name="tag_no" value=2><label class="category-title" for="tag2">아로마틱</label>
                                        <input type="radio" id="tag3" name="tag_no" value=3><label class="category-title" for="tag3">앰버</label>
                                        <input type="radio" id="tag4" name="tag_no" value=4><label class="category-title" for="tag4">우디</label>
                                        <input type="radio" id="tag5" name="tag_no" value=5><label class="category-title" for="tag5">달콤</label>
                                        <input type="radio" id="tag6" name="tag_no" value=6><label class="category-title" for="tag6">스모키</label>
                                        <input type="radio" id="tag7" name="tag_no" value=7><label class="category-title" for="tag7">과일</label>
                                        <input type="radio" id="tag8" name="tag_no" value=8><label class="category-title" for="tag8">그린</label>
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${productDTO.tag_no == 3}">
                                <div class="w-100 category-label">
                                    <div class="category-label flex">
                                        <input type="radio" id="tag1" name="tag_no" value=1><label class="category-title" for="tag1">아쿠아마린</label>
                                        <input type="radio" id="tag2" name="tag_no" value=2><label class="category-title" for="tag2">아로마틱</label>
                                        <input type="radio" id="tag3" name="tag_no" value=3><label class="category-title" for="tag3">앰버</label>
                                        <input type="radio" id="tag4" name="tag_no" value=4><label class="category-title" for="tag4">우디</label>
                                        <input type="radio" id="tag5" name="tag_no" value=5><label class="category-title" for="tag5">달콤</label>
                                        <input type="radio" id="tag6" name="tag_no" value=6><label class="category-title" for="tag6">스모키</label>
                                        <input type="radio" id="tag7" name="tag_no" value=7><label class="category-title" for="tag7">과일</label>
                                        <input type="radio" id="tag8" name="tag_no" value=8><label class="category-title" for="tag8">그린</label>
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${productDTO.tag_no == 4}">
                                <div class="w-100 category-label">
                                    <div class="category-label flex">
                                        <input type="radio" id="tag1" name="tag_no" value=1><label class="category-title" for="tag1">아쿠아마린</label>
                                        <input type="radio" id="tag2" name="tag_no" value=2><label class="category-title" for="tag2">아로마틱</label>
                                        <input type="radio" id="tag3" name="tag_no" value=3><label class="category-title" for="tag3">앰버</label>
                                        <input type="radio" id="tag4" name="tag_no" value=4><label class="category-title" for="tag4">우디</label>
                                        <input type="radio" id="tag5" name="tag_no" value=5><label class="category-title" for="tag5">달콤</label>
                                        <input type="radio" id="tag6" name="tag_no" value=6><label class="category-title" for="tag6">스모키</label>
                                        <input type="radio" id="tag7" name="tag_no" value=7><label class="category-title" for="tag7">과일</label>
                                        <input type="radio" id="tag8" name="tag_no" value=8><label class="category-title" for="tag8">그린</label>
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${productDTO.tag_no == 5}">
                                <div class="w-100 category-label">
                                    <div class="category-label flex">
                                        <input type="radio" id="tag1" name="tag_no" value=1><label class="category-title" for="tag1">아쿠아마린</label>
                                        <input type="radio" id="tag2" name="tag_no" value=2><label class="category-title" for="tag2">아로마틱</label>
                                        <input type="radio" id="tag3" name="tag_no" value=3><label class="category-title" for="tag3">앰버</label>
                                        <input type="radio" id="tag4" name="tag_no" value=4><label class="category-title" for="tag4">우디</label>
                                        <input type="radio" id="tag5" name="tag_no" value=5><label class="category-title" for="tag5">달콤</label>
                                        <input type="radio" id="tag6" name="tag_no" value=6><label class="category-title" for="tag6">스모키</label>
                                        <input type="radio" id="tag7" name="tag_no" value=7><label class="category-title" for="tag7">과일</label>
                                        <input type="radio" id="tag8" name="tag_no" value=8><label class="category-title" for="tag8">그린</label>
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${productDTO.tag_no == 6}">
                                <div class="w-100 category-label">
                                    <div class="category-label flex">
                                        <input type="radio" id="tag1" name="tag_no" value=1><label class="category-title" for="tag1">아쿠아마린</label>
                                        <input type="radio" id="tag2" name="tag_no" value=2><label class="category-title" for="tag2">아로마틱</label>
                                        <input type="radio" id="tag3" name="tag_no" value=3><label class="category-title" for="tag3">앰버</label>
                                        <input type="radio" id="tag4" name="tag_no" value=4><label class="category-title" for="tag4">우디</label>
                                        <input type="radio" id="tag5" name="tag_no" value=5><label class="category-title" for="tag5">달콤</label>
                                        <input type="radio" id="tag6" name="tag_no" value=6><label class="category-title" for="tag6">스모키</label>
                                        <input type="radio" id="tag7" name="tag_no" value=7><label class="category-title" for="tag7">과일</label>
                                        <input type="radio" id="tag8" name="tag_no" value=8><label class="category-title" for="tag8">그린</label>
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${productDTO.tag_no == 7}">
                                <div class="w-100 category-label">
                                    <div class="category-label flex">
                                        <input type="radio" id="tag1" name="tag_no" value=1><label class="category-title" for="tag1">아쿠아마린</label>
                                        <input type="radio" id="tag2" name="tag_no" value=2><label class="category-title" for="tag2">아로마틱</label>
                                        <input type="radio" id="tag3" name="tag_no" value=3><label class="category-title" for="tag3">앰버</label>
                                        <input type="radio" id="tag4" name="tag_no" value=4><label class="category-title" for="tag4">우디</label>
                                        <input type="radio" id="tag5" name="tag_no" value=5><label class="category-title" for="tag5">달콤</label>
                                        <input type="radio" id="tag6" name="tag_no" value=6><label class="category-title" for="tag6">스모키</label>
                                        <input type="radio" id="tag7" name="tag_no" value=7><label class="category-title" for="tag7">과일</label>
                                        <input type="radio" id="tag8" name="tag_no" value=8><label class="category-title" for="tag8">그린</label>
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${productDTO.tag_no == 8}">
                                <div class="w-100 category-label">
                                    <div class="category-label flex">
                                        <input type="radio" id="tag1" name="tag_no" value=1><label class="category-title" for="tag1">아쿠아마린</label>
                                        <input type="radio" id="tag2" name="tag_no" value=2><label class="category-title" for="tag2">아로마틱</label>
                                        <input type="radio" id="tag3" name="tag_no" value=3><label class="category-title" for="tag3">앰버</label>
                                        <input type="radio" id="tag4" name="tag_no" value=4><label class="category-title" for="tag4">우디</label>
                                        <input type="radio" id="tag5" name="tag_no" value=5><label class="category-title" for="tag5">달콤</label>
                                        <input type="radio" id="tag6" name="tag_no" value=6><label class="category-title" for="tag6">스모키</label>
                                        <input type="radio" id="tag7" name="tag_no" value=7><label class="category-title" for="tag7">과일</label>
                                        <input type="radio" id="tag8" name="tag_no" value=8><label class="category-title" for="tag8">그린</label>
                                    </div>
                                </div>
                            </c:when>
                        </c:choose>
                        <div class="invalid-message pt-10">올바른 카테고리를 1개 선택해주세요.</div>
                        <div class="row">
                            <label>가격</label>
                            <input type="text" class="form-input medium w-100" name="product_price" value="${requestScope.productDTO.product_price}">
                            <div class="invalid-message">0에서 2,100,000,000 이하의 숫자를 입력해주세요.</div>
                        </div>
                        <div class="row">
                            <label>재고</label>
                            <input type="text" class="form-input medium w-100" name="product_stock" value="${requestScope.productDTO.product_stock}">
                            <div class="invalid-message">0에서 2,100,000,000 이하의 숫자를 입력해주세요.</div>
                        </div>
                        <div class="row">
                            <label>배송비</label>
                            <input type="text" class="form-input medium w-100" name="product_delivery_price" value="${requestScope.productDTO.product_delivery_price}">
                            <div class="invalid-message">0에서 2,100,000,000 이하의 숫자를 입력해주세요.</div>
                        </div>
                        <div class="row">
                            <label>누적판매량</label>
                            <input type="text" class="form-input medium w-100" name="product_sellcount" value="${requestScope.productDTO.product_sellcount}">
                            <div class="invalid-message">0에서 2,100,000,000 이하의 숫자를 입력해주세요.</div>
                        </div>
                        <div class="row">
                            <label>등록일</label>
                            <input type="text" class="form-input medium w-100" name="product_regdate" value="${requestScope.productDTO.product_regdate}" readonly>
                            <div class="invalid-message">0에서 2,100,000,000 이하의 숫자를 입력해주세요.</div>
                        </div>
                        <div class="row">
                            <label class="w-100 flex">
                                <div class="w-50">
                                    대표이미지
                                </div>
                                <div class="w-50 right pe-20">
                                    기존 파일: ${requestScope.productDTO
                                        .product_mainimg} /
<%--                                    <fmt:formatNumber pattern="#,##0" value="${requestScope.productDTO--%>
<%--                                        .product_mainimg/10240}"></fmt:formatNumber>kb--%>
                                </div>
                            </label>
                            <input type="file" class="form-input medium w-100" name="img1" accept=".png, .gif, .jpg, .webp">
                            <div class="invalid-message">10mb 이하의 이미지만 업로드할 수 있습니다.</div>
                        </div>
                        <div class="row">
                            <label class="w-100 flex">
                                <div class="w-50">
                                    상세이미지
                                </div>
                                <div class="w-50 right pe-20">
                                    기존 파일: <img src="/resources/static/${requestScope.productDTO
                                        .product_subimg}">
                                    ${requestScope.productDTO
                                        .product_subimg} /
<%--                                    <fmt:formatNumber pattern="#,##0" value="${requestScope.productDTO--%>
<%--                                        .product_subimg/10240}"></fmt:formatNumber>kb--%>
                                </div>
                            </label>
                            <input type="file" class="form-input medium w-100" name="img2" accept=".png, .gif, .jpg, .webp">
                            <div class="invalid-message">10mb 이하의 이미지만 업로드할 수 있습니다.</div>
                        </div>
                        <div class="row">
                            <label class="w-100">상품 설명</label>
                            <textarea class="w-100" name="productContent">${requestScope.productDTO.product_content}</textarea>
                            <div class="invalid-message">상품 설명은 1자 이상, 1,000자 이하로 입력할 수 있습니다.</div>
                        </div>
                        <div class="row">
                            <button type="submit" class="form-btn medium positive w-100">수정하기</button>
                        </div>
                        <div class="row">
                            <a class="form-btn medium neutral w-100 back-btn" href="${pageContext.request.contextPath}/admin/productManage/list">돌아가기</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
    <footer>

    </footer>
</html>