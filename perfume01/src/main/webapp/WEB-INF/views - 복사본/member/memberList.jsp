<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script type="text/javascript">
        function searchDB() {
            self.location = 'memberList'
                +'${pageMaker.makeQuery(1)}'
                +'&searchType='+document.getElementById('searchType').value
                +'&keyword='+document.getElementById('keyword').value;
        }

        function keywordClear() {
            if (document.getElementById('searchType').value == 'all') {
                document.getElementById('keyword').value = '';
            }
        }
    </script>
</head>
<body>
<h2>맴버 목록 출력 테스트</h2>
<br>
<hr>
<!-- 회원 검색창 -->
<div id="searchBar" class="row center">
    <select name="searchType" id="searchType"  class="form-input small me-5 qty-selector" onchange="keywordClear()">
        <option value="all" ${pageMaker.criteria.searchType==null ? 'selected' : ''}>전체</option>
        <option value="member_id" ${pageMaker.criteria.searchType == 'member_id' ? 'selected' : ''}>아이디</option>
        <option value="member_name" ${pageMaker.criteria.searchType == 'member_name' ? 'selected' : ''}>이 름</option>
        <option value="member_nick" ${pageMaker.criteria.searchType == 'member_nick' ? 'selected' : ''}>닉네임</option>
        <option value="member_phone" ${pageMaker.criteria.searchType == 'member_phone' ? 'selected' : ''}>연락처</option>
    </select>
    <input type="text" name="keyword" id="keyword" class="form-input small w-35 me-5" value="${pageMaker.criteria.keyword}">
    <button id="searchBtn" class="form-btn small positive" onclick="searchDB()">검색</button>
</div>
<br>
<table class="container-750 table table-border">
    <thead>
    <tr>
        <th>아이디</th>
        <th>닉네임</th>
        <th>연락처</th>
        <th class="w-15">등 급</th>
        <th>포인트</th>
        <th class="w-20">관 리</th>
    </tr>
    </thead>
    <tbody class="center">
    <c:if test="${not empty requestScope.mlist}">
        <c:forEach var="m" items="${requestScope.mlist}">
            <tr>
                <td>${m.member_id}</td>
                <td>${m.member_nick}</td>
                <td>${m.member_phone}</td>
                <td>${m.member_role}</td>
                <td>${m.member_point}</td>
                <td>
                    <a class="btn-border" href="${pageContext.request.contextPath}/admin/member/memberDetail?member_id=${m.member_id}">상세(수정)</a>
<%--                    <a class="btn-border" href="${pageContext.request.contextPath}/member/memberUpdate?member_id=${m.member_id}&jCode=U">수정</a>--%>
                    <a class="btn-border" href="${pageContext.request.contextPath}/admin/member/mdelete?member_id=${m.member_id}" onclick="return confirm('정말로 탈퇴 처리 하겠습니까?')">탈퇴</a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
<br>
<!-- 페이지 번호 부여 -->
<div class="row center pt-20 pb-20">
    <div class="row pagination">
        <!-- 첫페이지, 이전 -->
        <c:choose>
            <c:when test="${pageMaker.prev && pageMaker.spageNo > 1}">
                <a href="/admin/member/memberList${pageMaker.searchQuery(1)}">처음</a>
                <a href="/admin/member/memberList${pageMaker.searchQuery(pageMaker.spageNo-1)}">&LT;</a>
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
                <a href="/admin/member/memberList${pageMaker.searchQuery(no)}">${no}</a>
            </c:if>
        </c:forEach>

        <c:choose>
            <c:when test="${pageMaker.next && pageMaker.epageNo>0}">
                <a href="/admin/member/memberList${pageMaker.searchQuery(pageMaker.epageNo+1)}">&GT;</a>
                <a href="/admin/member/memberList${pageMaker.searchQuery(pageMaker.lastPageNo)}">마지막</a>
            </c:when>
            <c:otherwise>
                &nbsp;&GT;&nbsp;마지막
            </c:otherwise>
        </c:choose>
    </div>
</div>
<hr>
<br>
<a href="javascript:history.go(-1)">뒤로</a>
<a href="/home">HOME 으로</a>
</body>
</html>
