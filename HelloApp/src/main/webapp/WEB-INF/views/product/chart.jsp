<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    google.charts.load('current', {
      'packages': ['corechart']
    });
    google.charts.setOnLoadCallback(drawChart);
	
    //동기방식으로 구현(순서대로 실행)
    async function drawChart() {
      //[{'Admin':1}.{'Accounting':2}.....]
      console.log("1");
      let outAry = [];
      outAry.push(['dept', 'cnt per dept']); // 모양에 따라 다르게 !!
      let promise1 = await fetch('chartAjax.do') // promise객체받아옴 , await은 다음코드로 넘어가지 않고 대기
      console.log(promise1);
      let promise2 = await promise1.json(); // [{},{},{}]
      console.log(promise2);
      console.log("1-1");
      promise2.forEach(dept => {
    	  let ary = [];
    	  for (let prop in dept){
    		  ary[0] = prop;
    		  ary[1] = dept[prop];
    	  }
    	  outAry.push(ary);
      })
        console.log("1-2");
      var data = google.visualization.arrayToDataTable(outAry);

      var options = {
        title: 'Person By Department'
      };

      var chart = new google.visualization.PieChart(document.getElementById('piechart'));

      chart.draw(data, options); //실제 차트를 그리는 부분
      console.log("2");
    }
  </script>
</head>

<body>
  <div id="piechart" style="width: 900px; height: 500px;"></div>
</body>

</html>