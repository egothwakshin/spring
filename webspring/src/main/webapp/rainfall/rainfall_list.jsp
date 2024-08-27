<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>강수량 통계차트 출력페이지 (SPA)</title>
    <script src="./jquery.js"></script>
    <script>
    $(document).ready(function(){
        //오늘날짜 알아오는 스크립트
        let $jdate = new Date();	//2024-07-23 이런식으로 찍혀야함
        let $y = $jdate.getFullYear();	//년
        let $m = $jdate.getMonth()+1; //월 (0부터시작하므로 +1)
        let $d = $jdate.getDate();	//일
        
        let $tostring1 = $jdate.toLocaleDateString();	//2024. 7. 23.
        let $tostring2 = $jdate.toLocaleString();		//2024. 7. 23. 오후 3:58:43
        let $tostring3 = $jdate.toISOString()			//2024-07-23T06:58:43.299Z 
        
        let $tostring4 = $jdate.toISOString().substring(0,10); //2024-07-23
        	
        	
        //'확장함수'를 이용하여 즉시실행 및 이벤트핸들링까지 모두 사용할수 있도록 함
        $.fn.dataload = function(ymd){	//Controller에서 주는 데이터를 바로 받는 형태
        	
        	$.ajax({
        		url: "./rainfall_list.do?sdate="+ymd,
        		cache:false,
        		dataType:"html",
        		type: "get",
        		success:function($result){
        			eval($result);	//backend에서 Storage를 작동시켜서 데이터를 등록시키기 위한 방식
        			
        			//Sotrage에 저장된 값을 가져와서 애니메이션 그래프 함수로 이관함
        			var $a = sessionStorage.getItem("area1");
        			var $b = sessionStorage.getItem("area2");
        			var $c = sessionStorage.getItem("area3");
        			var $d = sessionStorage.getItem("area4");
        			var $e = sessionStorage.getItem("area5");
        			
        			$.fn.gp($a,$b,$c,$d,$e);
        		},
        		error:function(){
        			console.log("Data error!!");
        		}    	
        	});
        	
        }
        
        //최초 페이지 접속시 DB에 저장된 날짜와 다른 경우 마지막으로 저장된 날짜 기준으로 ajax실행
        if($tostring4 != $("#select_day").val()){
        	$.fn.dataload($("#select_day").val());	//최초 페이지 접속시
        }else{	//저장된 날짜와 현재 날짜가 동일할 경우 현재 날짜를 ajax로 실행
        	$.fn.dataload($tostring4);
        }

        //일자별 강우량 날짜를 선택하는 핸들링 이벤트
        $("#select_day").change(function(){
        	var $ymds = $(this).val();
        	$.fn.dataload($ymds);	//해당 강수량 일자를 변경시 ajax 재호출
        });
        
        //Backend가 생성한 storage값을 이용하여 그래프로 출력하는 함수
        $.fn.gp = function($area1,$area2,$area3,$area4,$area5){     

	        var $w = 0;
	        var $time = 1000;    //시간
	        
	        //문자열로 만들어서 반복문으로 애니메이션을 구현하는 방식
	        var $width = $area1+"|"+$area2+"|"+$area3+"|"+$area4+"|"+$area5;
	        var $ani_width = $width.split("|");
	                                  
	        while($w<5){
	        	var $ids = "#box" + $w; 
	        	var $ani_time = $time * $w; 
	             $($ids).delay($ani_time).animate({
	             	 "width":$ani_width[$w]
	             },1000);          
	             $w++;
	         }
        }
        
        //강우량 등록 버튼
        $("#indata").click(function(){
        	location.href='./rainfall_write.jsp';
        });
                
        //강우량 수정 버튼
       	$("#modifydata").click(function(){
       		var $date = $("#select_day").val();
       		console.log($date);
        	location.href='./rainfall_modify.do?sdate='+$date;
        });
               
        //강우량 삭제 버튼
       	$("#deldata").click(function(){
       		var $date = $("#select_day").val();
       		console.log($date);
       		location.href='./rainfall_delete.do?sdate='+$date;
        });
        
        
                  	
    });
    </script>
    <style>
        .gp{
            width:800px;
            height: 400px;
            border: 1px solid black;
            border-right:none;
            border-top: none;
            position: relative;
            left: 150px;
        }
        ul { list-style: none; margin: 0; padding: 0; 
            padding-top: 40px; 
            box-sizing: border-box; float: left;
        }
        ul:nth-of-type(1){
            width:10%;
            position: relative;
            left: -60px;
        }
        ul:nth-of-type(2){
            width:90%;
            position: relative;
            left: -80px;
        }
        ul > li { height: 40px; margin-bottom: 40px; line-height: 40px; }
        .gp > ul:nth-of-type(2) > li { width: 0px;}
        .gp > ul:nth-of-type(2) > li:nth-of-type(1){
            background-color: red;
        }
        .gp > ul:nth-of-type(2) > li:nth-of-type(2){
            background-color: greenyellow;
        }
        .gp > ul:nth-of-type(2) > li:nth-of-type(3){
            background-color: orange;
        }
        .gp > ul:nth-of-type(2) > li:nth-of-type(4){
            background-color: purple;
        }
        .gp > ul:nth-of-type(2) > li:nth-of-type(5){
            background-color: blue;
        }
    </style>
</head>
<body>
	<p>일자별 강우량 :
	<select id="select_day">
	<cr:forEach var="ymd" items="${all}">
	<option value="${ymd.today}">${ymd.today}</option>
	</cr:forEach>
	</select>
	</p>
    <div class="gp">
        <ul>
            <li>서울</li>
            <li>경기도</li>
            <li>세종</li>
            <li>대전</li>
            <li>강원도</li>
        </ul>
        <ul>
        <li id="box0"></li>
        <li id="box1"></li>
        <li id="box2"></li>
        <li id="box3"></li>
        <li id="box4"></li>
        </ul>
    </div>
    <br><br><br>
    <input type="button" value="강수량등록" id="indata">
    <input type="button" value="강수량수정" id="modifydata">
    <input type="button" value="강수량삭제" id="deldata">
</body>
</html>