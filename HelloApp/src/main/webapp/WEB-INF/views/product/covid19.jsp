<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 시도 : <input type="text" id="sido"> -->
	시도선택: <select id="sidoList">
			  
			</select>
	
	<button id="sidoBtn">찾기</button>
	
	<h3>센터현황</h3>
	<table border="1">
	  <thead>
	    <tr><th>ID</th><th>센터명</th><th>주소</th><th>연락처</th><th>시도정보</th></tr>
	  </thead>
	  <tbody id="centerList"></tbody>
	</table>

	<script>
	  let showFields = ['id', 'centerName', 'address', 'phoneNumber', 'sido']
	  
	  // row 생성
	  function makeTr(center={}) {
		  // tr생성>td*여러개
		  let tr = document.createElement('tr');
		  tr.setAttribute('data-lng', center.lng); //tr에 위도값 넣기setAttribute=>(추가 정보 저장)
		  tr.setAttribute('data-lat', center.lat);
		  tr.setAttribute('data-name', center.centerName);
		  tr.addEventListener('click', openMapFnc);//tr 클릭시 지도가 열리도록
		  
		  // td생성
		  showFields.forEach(field => {
			let td = document.createElement('td');
			td.innerText = center[field];
			tr.append(td);
		  })
		  return tr;
	  }
	  
	  function openMapFnc(){
		  let tr = this; // event target
		  let lng = tr.dataset.lng; // dataset에 정보를 담아줌 //tr.getAttribute('data-lng');도 가능
		  let lat = tr.dataset.lat; // tr.getAttribute('data-lat');
		  let name = tr.dataset.name;
		  //location.href='map.do?lng='+lng+'&lat='+lat; 
		  window.open('map.do?lng='+lng+'&lat='+lat+'&name='+name+'_blank');
	  }
	  
	  //전체 목록
	  let totalList; //list를 다른 함수에서도 활용하기 위한 변수 선언
	  
	  let url = `https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=284&serviceKey=Zj%2BRsNhBysU0RNVFD0%2BKwtA4cBdNgIvVJVRW9ngktd5jRd8OZoOGZxWS5ZXjZobaop5GyxM9wxk7K2KEgpuHHw%3D%3D`;
	  fetch(url)
	  .then(resolve => resolve.json())
	  .then(result => {
		  console.log(result);
		  let aryData = result.data;
		  totalList = aryData; //처리결과를 활용해서 totalList 대입
		  aryData.forEach(function (center) {
			 let tr = makeTr(center);
			 document.querySelector('#centerList').append(tr);
		  });
		  // 시도 배열 
		  //비동기 api이기때문에 해당 코드가 밖에 있으면처리된 결과 이전에 실행이 되므로 totalList에는 값이 없음 따라서 api 안에 들어와있어야함
		  totalList; // {id, centerName, address, sido}
		  // push, pop, unsift, shift 등의 배열 메소드 활용
		  let sidoAry = [];
		  
		  totalList.forEach(function(list){
			  let sido = list.sido;
			  if(sidoAry.indexOf(sido) == -1){
				  sidoAry.push(sido);
			  }
		  })
		  
		  sidoAry.forEach(function (sido) {
			  let opt = document.createElement('option');
			  opt.value = sido;
			  opt.innerText = sido;
			  document.querySelector('#sidoList').append(opt);
		  });
		  
	
		  
	  })
	  .catch(reject => console.error(reject));
	  

	  
	  
	  // 찾기 버튼
	  document.querySelector('#sidoBtn').addEventListener('click', findSidoFnc);
	  function findSidoFnc(){
		  //전체목록에서 검색조건 filter한 결과를 tbody의 하위목록으로 출력
		  document.querySelector('#centerList').innerHTML = "";
		  
		  let searchWord = document.getElementById('sidoList').value;
		  let filterAry = totalList.filter(function(center){ // forEach와 비슷하지만 값을 출력해 새로운 배열 생성(return true일 경우))
			  console.log(center);
		  	  return center.sido == searchWord;
		  });
		  console.log(filterAry);
		  
		  filterAry.forEach(center => {
			  document.querySelector('#centerList').append(makeTr(center));
		  })
	  }
	  
	  </script>
</body>
</html>