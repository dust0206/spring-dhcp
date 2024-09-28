<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>글수정 폼 | board</title>
	</head>
<body>
	<h2>글수정 폼</h2>
	<form action="/board/updateBoard.do" method="post">
		<table border="1">
			<tr>
				<th align="center" width="120">제 목</th>
				<td width="500">
					<input type="text" name="title"  value="${dto.title }" size="50 "placeholder="2자이상 100자이하">
					<input type="hidden" name="seq" value="${dto.seq }" >
				</td>
			</tr>
			<tr>
				<th align="center">이 름</th>
				<td>
					<input type="text" name="username" value="${dto.username }" size="50"  placeholder="2자이상 5자이하" readonly>
				</td>
			</tr>
			<tr>
				<th align="center">비밀번호
				<td>
					<input type="password" name="passwd" value="" placeholder="4자">
				</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td>
					<textarea name="contents" rows="7" cols="60" >${dto.contents }</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<a href="/board/selectPageListBoard.do"><button type="button">취소</button></a>
					<button type="submit" formaction="/board/deleteBoard.do">삭제</button>
					<input type="submit" value="저장">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>