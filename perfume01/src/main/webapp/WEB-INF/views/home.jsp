<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<jsp:include page="/WEB-INF/views/template/adminHeader.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<!-- chart.js cdn -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<!-- chart 비동기 스크립트 -->
<script src="${path}/resources/js/graph.js"></script>
<%--        <script src="${path}/resources/js/product-category-stat.js"></script>--%>


</style>
<script type="text/javascript">

</script>

<div class="w-100">
	<div class="row center pb-30">
		<h1>상품별 판매통계</h1>
	</div>
<%--	<div class="row">--%>
<%--		<h2>카테고리별 상품 개수</h2>--%>
<%--	</div>--%>
<%--	<div class="row pb-50">--%>
<%--		<canvas id="categoryCount"></canvas>--%>
<%--	</div>--%>
<%--	<div class="row">--%>
<%--		<h2>카테고리별 판매량</h2>--%>
<%--	</div>--%>
<%--	<div class="row pb-50">--%>
<%--		<canvas id="categorySellCount"></canvas>--%>
<%--	</div>--%>
<%--	<div class="row">--%>
<%--		<h2>카테고리별 가격</h2>--%>
<%--	</div>--%>
<%--	<div class="row">--%>
<%--		<canvas id="categoryPrice"></canvas>--%>
<%--	</div>--%>
	<div class="w-100">
		<canvas id="canvas"></canvas>
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>