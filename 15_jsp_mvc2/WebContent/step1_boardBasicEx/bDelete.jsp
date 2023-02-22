<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>delete</title>
</head>
<body>

	<h1>게시글 삭제</h1>
	<form action="bDelete" method="post">
		<table border="1">
			<tr>
				<td>작성자</td>
				<td>${boardDTO.writer }</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td>${boardDTO.enrollDt }</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>${boardDTO.subject }</td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr align="right">
				<td colspan="4">
					<input type="hidden" name="num" value="${boardDTO.num }">
					<input type="submit" value="글삭제">
					<input type="button" onclick="location.href='bList'" value="목록보기">
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>