<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>selectPageListBoard : Board</title>
	</head>
<body>
	<h2>게시판 전체 리스트 </h2>
	<table border="1">
		<tr align="center">
			<th width="80">번호</th>
			<th width="450">제목</th>
			<th width="100">글쓴이</th>
			<th width="150">날짜</th>
			<th width="80">조회수</th>
		</tr>
		<c:forEach items="${pagingList }" var="dto">
		<tr>
			<td align="center">${dto.seq }</td>
			<td ><a href="/board/selectBoard.do?seq=${dto.seq }" >${dto.title}</a></td>
			<td align="center">${dto.username }</td>
			<td align="center">${dto.create_date }</td>
			<td align="right">${dto.hits}</td>
		</tr>
		</c:forEach>
		
		
		<tr>
			<td colspan="4" align="center">
			<c:choose>
				<c:when test="${paging.page <= 1 }">
					<span>이전</span>
				</c:when>
				<c:otherwise>
					<a href="/board/selectPageListBoard.do?page=${paging.page -1 }">[이전]</a>
				</c:otherwise>
			</c:choose>
			 
			 <c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="i" step="1">
			 	<c:choose>
			 		<c:when test="${i eq paging.page }">
			 			<span>${i }</span>
			 		</c:when>
			 		
			 		<c:otherwise>
			 			<a href="/board/selectPageListBoard.do?page=${i }">${i }</a>
			 		</c:otherwise>
			 	</c:choose>
			 </c:forEach>
			 
			 <c:choose>
			 	<c:when test="${paging.page >= page.maxPage }">
			 		<span>[다음]</span>
			 	</c:when>
			 	<c:otherwise>
			 		<a href="/board/selectPageListBoard.do?page=${paging.page + 1 }">[다음]</a>
			 	</c:otherwise>
			 </c:choose>
			 
			
			</td>
			
			<td align="center">
			
				<a href="/board/insertBoard.do"><button>글쓰기</button></a>
			</td>
		</tr>
	</table>
</body>
</html>