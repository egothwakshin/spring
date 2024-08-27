<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cp" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠폰 리스트</title>
</head>
<body>
<p>쿠폰 등록 총 데이터: ${ctn} EA</p>
<table border="1" cellpaddiong="0" cellspaciong="0">
<thead>
	<tr>
		<th>번호</th>
		<th>쿠폰명</th>
		<th>할인율</th>
		<th>사용 유/무</th>
		<th>수정/삭제</th>
	</tr>
</thead>
<tbody>
<!-- 
varStatus="status" : forEach에 대한 정보값을 설정하는 코드임 
	.index : 노드번호 0부터 시작
	.count : 1부터 시작
	.last : 맨마지막에 있는 번호 (true: 마지막 데이터, false: 다음 데이터가 있는 상황)
	
	.begin : 시작번호	(속성값 필요)
	.end : 종료번호	(속성값 필요)
	.step : 증가값	(속성값 필요)
-->
<form id="frm">
<cp:forEach var="cpdata" items="${all_list}" varStatus="status">
	<tr>
		<td> ${ctn - status.index}
		<input type="checkbox" name="cidx" value="${cpdata.get(0)}">
		</td>
		<td>${cpdata.get(1)}</td>
		<td>${cpdata.get(2)}</td>
		<td>${cpdata.get(3)}</td>
		<td>
		<input type="button" value="수정" onclick="coupon_modify('${cpdata.get(0)}')">
		<input type="button" value="삭제" onclick="coupon_del('${cpdata.get(0)}')">
		</td>
	</tr>
</cp:forEach>	
</form>
</tbody>
</table>
<input type="button" value="선택 삭제" onclick="coupon_del_sel()">

</body>
<script>
function coupon_modify(no){
	
	location.href='./coupon_modify.do?cidx='+no;
	
	
}

function coupon_del_sel(){
	frm.method="post";
	frm.action="./coupon_del_sel.do";
	frm.submit();
}

function coupon_del(no){
	if(confirm("해당 쿠폰 삭제시 복구되지 않습니다.")){
		location.href='./coupon_del.do?cidx=' +no;
	}
}
</script>
</html>