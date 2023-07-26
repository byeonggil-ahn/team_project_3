<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품목록</title>
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
    <script src="${path}/resources/js/admin-edit.js"></script>
    <style>
        .register-btn {
            vertical-align: top;
            height: 3.1em;
            width: 7.2em;
        }

        .qty-selector {
            width: 100px;
            text-align: left;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            // 체크박스 처리
            $(".check-all").change(function () {
                var isChecked = $(this).prop("checked");
                $(".check-all, .check-unit").prop("checked", isChecked);
            })
            $(".check-unit").change(function () {
                var checkboxCount = $(".check-unit").length;
                var checkedCount = $(".check-unit:checked").length;
                var isAllChecked = checkboxCount == checkedCount;
                $(".check-all").prop("checked", isAllChecked);
            })
            // 선택 상품 삭제버튼 경고창
            $(".delete-btn").click(function () {
                var checkedCount = $(".check-unit:checked").length;
                if (checkedCount == 0) {
                    alert("삭제할 상품을 선택해주세요.");
                    return false;
                }
                var result = confirm("상품을 삭제하시겠습니까?");
                if (!result) return false;
            })
            // 개별 상품 삭제버튼 경고창
            $(".single-delete-btn").click(function () {
                var result = confirm("상품을 삭제하시겠습니까?");
                if (!result) return false;
            })
        })
    </script>

</head>
<body>
<div class="container-1000">
    <div class="row pb-20">
        <a class="link" href="${pageContext.request.contextPath}/admin/productManage/list">
            <h1>상품 목록</h1>
        </a>
    </div>

    <div class="row flex w-100">
        <!-- 검색창 구현 Start -->
        <form action="${pageContext.request.contextPath}/admin/productManage/list" method="get" class="row w-100 search-form">
            <div id="searchBar" class="flex row center">
                <select name="searchType" id="searchType" class="form-input small me-5 qty-selector"
                        onchange="keywordClear()">
                    <option value="all" ${pageMaker.criteria.searchType==null ? 'selected' : ''}>전체</option>
                    <option value="product_name" ${pageMaker.criteria.searchType == 'product_name' ? 'selected' : ''}>
                        상품명
                    </option>
                    <option value="product_no" ${pageMaker.criteria.searchType == 'product_no' ? 'selected' : ''}>품 번
                    </option>
                    <option value="product_brand" ${pageMaker.criteria.searchType == 'product_brand' ? 'selected' : ''}>
                        브랜드
                    </option>
                </select>
                <input type="text" name="keyword" id="keyword" class="form-input small w-35 me-5"
                       value="${pageMaker.criteria.keyword}">
                <button id="searchBtn" class="form-btn small positive" onclick="searchDB()">검색</button>
            </div>
        </form>
        <!-- 검색창 구현 End -->
    </div>
    <div class="row">
        <form action="${pageContext.request.contextPath}/admin/productManage/deleteProduct" method="post"
              class="delete-form w-100">
            <div class="row right">
                <button type="submit" class="form-btn small neutral delete-btn">상품삭제</button>
                <a class="form-btn small positive register-btn"
                   href="${pageContext.request.contextPath}/admin/productManage/insertProduct">상품등록</a>
            </div>
            <div class="row">
                <c:choose>
                    <c:when test="${empty requestScope.plist}">
                        <h3 class="mt-50 mb-50 center c-p100">검색 결과가 존재하지 않습니다</h3>
                    </c:when>
                    <c:otherwise>
                        <table class="table table-qna mb-30">
                            <thead>
                            <tr>
                                <th class="w-5">
                                    <input type="checkbox" class="check-all">
                                </th>
                                <th>품 번</th>
                                <th>상품명</th>
                                <th>가 격</th>
                                <th>재 고</th>
                                <th>판매량</th>
                                <th>출시일</th>
                                <th>관 리</th>
                            </tr>
                            </thead>
                            <tbody class="center">
                            <c:forEach var="p" items="${requestScope.plist}">
                                <!-- 서버에서 목록을 불러와야함. 상품 MVC 구현 이후에 주석 풀고 수정해볼것 -->

                                <tr>
                                    <td>
                                        <input type="checkbox" class="check-unit" name="product_no"
                                               value="${p.product_no}">
                                    </td>
                                    <td title="${p.product_no}">${p.product_no}</td>
                                    <td style="text-align: left">
                                        <!-- href 로 상품수정 페이지 연결 -->
                                        <a class="link" href="" title="[${p.product_brand}] ${p.product_name}">
                                            [${p.product_brand}] ${p.product_name}
                                                <%--                                            /product/detail?productNo=${productDto.productNo} 이쪽으로 데이터를 넘겨줭--%>
                                        </a>
                                    </td>
                                        <%--                                    <td>${tagTitle}</td>--%>
                                    <td class="center" title="${p.product_price}">
                                        <fmt:formatNumber pattern="#,##0" value="${p.product_price}"></fmt:formatNumber>
                                    </td>
                                    <td class="center" title="${p.product_stock}">
                                        <fmt:formatNumber pattern="#,##0" value="${p.product_stock}"></fmt:formatNumber>
                                    </td>
                                    <td class="center" title="${p.product_sellcount}">
                                        <fmt:formatNumber pattern="#,##0"
                                                          value="${p.product_sellcount}"></fmt:formatNumber>
                                    </td>
                                    <td title="${p.product_regdate}">${p.product_regdate}</td>
                                    <td>
                                        <a class="link"
                                           href="${pageContext.request.contextPath}/admin/productManage/edit?product_no=${p.product_no}">수정 </a>
                                        <span>| </span>
                                        <a class="link single-delete-btn"
                                           href="${pageContext.request.contextPath}/admin/productManage/deleteEachProduct?product_no=${p.product_no}">삭제</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
            </div>
        </form>
    </div>
    <!-- 페이지 번호 부여 -->
    <div class="row center">
        <div class="row pagination mb-40">
            <!-- 첫페이지, 이전 -->
            <c:choose>
                <c:when test="${pageMaker.prev && pageMaker.spageNo > 1}">
                    <a href="/admin/productManage/list${pageMaker.searchQuery(1)}">처음</a>
                    <a href="/admin/productManage/list${pageMaker.searchQuery(pageMaker.spageNo-1)}">&LT;</a>
                </c:when>
                <c:otherwise>
                    처음&nbsp;&LT;&nbsp;&nbsp;
                </c:otherwise>
            </c:choose>

            <!-- 화면 페이지 숫자 -->
            <c:forEach var="no" begin="${pageMaker.spageNo}" end="${pageMaker.epageNo}">
                <c:if test="${no == pageMaker.criteria.currPage}">
                    ${no}
                </c:if>
                <c:if test="${no != pageMaker.criteria.currPage}">
                    <a href="/admin/productManage/list${pageMaker.searchQuery(no)}">${no}</a>
                </c:if>
            </c:forEach>

            <c:choose>
                <c:when test="${pageMaker.next && pageMaker.epageNo>0}">
                    <a href="/admin/productManage/list${pageMaker.searchQuery(pageMaker.epageNo+1)}">&GT;</a>
                    <a href="/admin/productManage/list${pageMaker.searchQuery(pageMaker.lastPageNo)}">마지막</a>
                </c:when>
                <c:otherwise>
                    &nbsp;&GT;&nbsp;마지막
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</div>


<br>
<hr>
<br>
<a href="javascript:history.go(-1)">뒤로</a>
<a href="/home">HOME 으로</a>
</body>
</html>
