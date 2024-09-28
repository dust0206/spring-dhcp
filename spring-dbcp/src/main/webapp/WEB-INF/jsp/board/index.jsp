<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title> index | board</title>
	</head>
<body>
	<h2>Board index.do</h2>
	<a href="/board/selectPageListBoard.do"><button>전체 리스트</button></a><br>
	<a href="/board/insertBoard.do"><button>글쓰기</button></a><br>
	<a href="/board/pagingBoard.do"><button>페이징 목록</button></a>

</body>
</html>