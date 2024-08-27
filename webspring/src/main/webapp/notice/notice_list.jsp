<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
</head>
<body>
<form id="frm_select">
<table border="1" cellpadding="0" cellspacing="0">
	<thead>
		<tr height="30">
			<th><input type="checkbox" id="all" onclick="checkAll(this)"></th>
			<th width="500">제목</th>
			<th width="100">글쓴이</th>
			<th width="100">입력일자</th>
		</tr>
	</thead>
	<tbody>
	<cr:forEach var="data" items="${result}">
		<tr height="30" align="center">
			<td><input type="checkbox" name="ck" value="${data.nidx}" onclick="checkItem(this)"></td>
			<td align="left" title="${data.n_text}">${data.n_subject}</td>
			<td>${data.n_title}</td>
			<td>${data.n_date.substring(0,10)}</td>
		</tr>
	</cr:forEach>	
	</tbody>
</table>
<br><br><br>
<input type="button" value="선택삭제" onclick="select_del()">
</form>

<form id="frm" onsubmit="return search_gopage()">
검색 :
<select name="search_part">
	<option value="1">제목</option>
	<option value="2">글쓴이</option>
	<option value="3">제목+내용</option>
</select>
<input type="text" name="search_word" value="${search_word}">
<input type="submit" value="검색">
</form>
</body>
<script>
//원시배열 형태 [] 
var arr = new Array(5);
arr[0] = "test1";
arr[1] = "test2";
arr[2] = "test3";
arr[3] = "test4";
//console.log(arr);	// ['test1', 'test2', 'test3', 'test4', empty]

//키배열 형태 {} 
var arr2 = new Object();
arr2.name="홍길동";
arr2.id="hong";
arr2.tel="01022223333";
//console.log(arr2); //{name: '홍길동', id: 'hong', tel: '01022223333'}

var ck = "${search_part}";
if(ck==""){
	ck="1";
}
frm.search_part.value == ck;

	function search_gopage(){
		if(frm.search_word.value==""){
			alert("검색할 단어를 입력하세요.");
			return false;
		}
		else{
			frm.method="get";
			frm.action="./notice_list.do";
			return true;
		}
	}
	

	function select_del(){
		var ck = document.getElementsByName('ck');
		
		//frm_select.method="post";
		frm_select.method="get";
		frm_select.action="./notice_delete.do";
		frm_select.submit();
	}
	
	
	
	
	function checkAll(checkbox) {
	    var checkboxes = document.getElementsByName('ck');
	    for (var i = 0; i < checkboxes.length; i++) {
	        checkboxes[i].checked = checkbox.checked;
	    }
	}
	
	function checkItem(checkbox) {
	    var allCheckbox = document.getElementById('all');
	    if (!checkbox.checked) {
	        allCheckbox.checked = false;
	    } else {
	        var checkboxes = document.getElementsByName('ck');
	        var allChecked = true;
	        for (var i = 0; i < checkboxes.length; i++) {
	            if (!checkboxes[i].checked) {
	                allChecked = false;
	                break;
	            }
	        }
	        allCheckbox.checked = allChecked;
	    }
	}
</script>
</html>