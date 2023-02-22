<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bMain</title>
</head>
<body>

	<%--

		# MVC2 데이터베이스 연동예시
	
	
		1. 해당 프로젝트 > WebContent > WEB-INF > lib폴더에 아래의 jar파일 추가
			
			commons-collections4-4.1.jar
			commons-dbcp2-2.2.0.jar
			commons-pool2-2.5.0.jar
			jstl-1.2.jar
			mysql-connector-java-8.0.15.jar
			
		
		2. 이클립스에서 Servers폴더에 있는 Context.xml파일에 아래의 내용 추가 
		
			<Resource 
				auth="Container" 
				driverClassName="com.mysql.cj.jdbc.Driver"
				loginTimeout="10" 
				maxWait="5000" 
				name="jdbc/pool" 
				username="root"
				password="1234" 
				type="javax.sql.DataSource"
				url="jdbc:mysql://localhost:3306/JSP_MVC2_EX?serverTimezone=UTC&amp;useSSL=false"
			/> 
	 
	 --%>
	<!-- WebContent 하위부터 이미지 경로를 작성한다. -->
	<div align="center">
	<img alt="jsp이미지" src="img/jsp.PNG">
	<input type="button" value="게시판 보기" onclick="location.href='bList'">
	</div>
</body>
</html>