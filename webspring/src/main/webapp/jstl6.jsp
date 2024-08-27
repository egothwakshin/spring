<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 외부페이지 로드</title>
</head>
<body>
<cr:set var="username" value="에이핑크"/>
<!-- 
import를 이용하여 외부의 jsp파일을 로드할 수 있습니다. 
단, 해당 페이지에 set태그를 이용하여 공유하는 데이터 방식은 작동 불가능
※ Controller에서 Model, ModelandView를 활용하면 외부 improt된
jsp에서 jstl로 결과값 출력 가능
-->
<cr:import url="./test.jsp"></cr:import>
</body>
</html>