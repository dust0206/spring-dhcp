<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>글쓰기 폼 | board</title>
	</head>
<body>
	<h2>글쓰기 폼</h2>
	<form action="/board/insertBoard.do" method="post">
		<table border="1">
			<tr>
				<th align="center" width="120">제 목</th>
				<td width="500">
					<input type="text" name="title" size="50 "placeholder="2자이상 100자이하">
				</td>
			</tr>
			<tr>
				<th align="center">이 름</th>
				<td>
					<input type="text" name="username" size="50"  placeholder="2자이상 5자이하">
				</td>
			</tr>
			<tr>
				<th align="center">비밀번호</th>
				<td>
					<input type="password" name="passwd" placeholder="4자">
				</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td>
					<textarea name="contents" placeholder="1자이상 300자 이하" rows="7" cols="60" ></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<a href="/board/selectPageListBoard.do"><button type="button">취소</button></a>
					<input type="submit" value="저장">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>