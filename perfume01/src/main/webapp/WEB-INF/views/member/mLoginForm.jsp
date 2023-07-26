<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring Boot Member Login **</title>
<link rel="stylesheet" type="text/css" href="/resources/myLib/myStyle.css" >
</head>
<body>
<h2>** Spring Boot Login **</h2>
<form action="/admin/member/login" method="post">
<table>
	<tr height="40">
		<td bgcolor="Aqua" ><label for="member_id">I D</label></td>
		<td><input type="text" id="member_id" name="member_id"></td>
	</tr>
	<tr height="40">
		<td><label for="member_pw">Password</label></td>
		<td><input type="password" id="member_pw" name="member_pw"></td>
	</tr>
	<tr height="40"><td></td>
		<td><input type="submit" value="로그인">&nbsp;&nbsp;
			<input type="reset" value="취소">&nbsp;&nbsp;
		</td>
	</tr>
</table>
</form>
<hr>
<span id="message"></span>
<c:if test="${not empty requestScope.msg}">
	<b>=> ${requestScope.msg}</b><br>
</c:if>
<hr>
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
&nbsp;<a href="/home" >[Home]</a>&nbsp;
</body>
</html>