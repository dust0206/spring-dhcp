<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>글보기  | board</title>
	</head>
<body>
	<h2>글보기 폼</h2>
		<table border="1">
			<tr>
				<th align="center" width="120">제 목</th>
				<td width="500" colspan="3">
					${dto.title }
				</td>
			</tr>
			<tr>
				<th align="center">이 름</th>
				<td colspan="3">${dto.username }
				</td>
			</tr>
			<tr>
				<th align="center">작성일</th>
				<td width="300">${dto.create_date }
				</td>
				<th width="100">조회수</th>
				<td align="right" width="80">${dto.hits }</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td colspan="3">
					<textarea name="contents"  rows="7" cols="60" >${dto.contents }</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="right">
					<a href="/board/selectPageListBoard.do"><button type="button">목록</button></a>
					<a href="/board/updateBoard.do?seq=${dto.seq }"><button type="button">수정</button></a>
<!-- 					<a href="/board/deleteBoard.do"><button type="button">삭제</button></a> -->
				</td>
			</tr>
		</table>
</body>
</html>