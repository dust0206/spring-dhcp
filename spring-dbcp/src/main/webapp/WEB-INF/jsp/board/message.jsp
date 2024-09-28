<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Insert title here</title>
	</head>
<body>
	<h2>메시지 페이지</h2>
	<table border="1">
		<tr align="center" >
			<td width="100">${field }  </td>
			<td width="400">${message }</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
			<a href="${prevUrl }">이전페이지</a></td> 
		</tr>
	</table>
	
</body>
</html>