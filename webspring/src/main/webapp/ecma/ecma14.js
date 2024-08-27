var g = document.querySelector("#dataview");	//작성된 코드를 출력할 html id값
var html = "";	//html 코드를 작성하기 위한 변수 
var n = "";

export class data_load{
	
	json_data(){
		fetch("./ecma14ok.do", {
			method:"get",
			cache:"no-cache"
		})
		.then(function(a){
			return a.json();	//JSON 배열을 미리 설정한 후 출력
		})
		.then(function(b){
			//JSON.parse 사용시 외부 함수로 전달
			//console.log(b[0]["cpname"]);
			g.innerHTML = ""; //새롭게 데이터 로드시 해당 출력된 HTML 초기화
			html="";
			n = b.length;	//배열 개수만큼 숫자가 출력
			
			//배열값 반복문을 통해서 HTML 코드를 작성함
			b.forEach(function(arr,node,idx){
				html += `<tr>
				<td>`+(n-node)+ `</td>
				<td>`+ b[node]["cpname"] + `</td>
				<td>`+ b[node]["cprate"] + `%</td>
				<td>`+ b[node]["cpdate"] + `</td>
				</tr>`;
			});
			//해당 html 오브젝트에 출력
			g.innerHTML = html;
		})
		.catch(function(error){
			console.log("api server error");
		});
	}
	
}