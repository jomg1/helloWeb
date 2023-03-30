<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<script src='full/dist/index.global.js'></script>
<script>

  document.addEventListener('DOMContentLoaded',async function() {
	  // fetch api를 사용
	  let events = [];
	  let promise1 = await fetch('schedule.do');
	  let promise2 = await promise1.json();
	  
    	//events = promise2;
    	promise2.forEach(cal => {
    		let obj = {title: cal.title, start: cal.startDate, end: cal.endDate}
    		events.push(obj);
    	})
	  console.log(events)
	  
	  
	  var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },
      initialDate: new Date(),
      navLinks: true, // can click day/week names to navigate views
      selectable: true,
      selectMirror: true,
      select: function(arg) {
        var title = prompt('일정등록:');
        if (title) {
        	console.log(arg)
        	fetch('scheduleAdd.do',{
        		method: 'post',
        		headers:{'Content-Type': 'application/x-www-form-urlencoded'},
        		body: 'title='+title+'&start='+arg.startStr+'&end='+arg.endStr
        		//body에 있는 부분은 파라미터가 들어감 파라미터 값이랑 같은지 꼭 확인할 것!!!
        	})
        	.then(resolve => resolve.json())
        	.then(result => {
        		console.log(result);
        		
        	})
        	.catch(reject => console.error(reject));
        	//화면에 이벤트 등록
          calendar.addEvent({
            title: title,
            start: arg.start,
            end: arg.end,
            allDay: arg.allDay
          })
        }
        calendar.unselect()
      },
      eventClick: function(arg) {
        if (confirm('이벤트를 삭제하시겠습니까?')) {
          arg.event.remove()
        }
      },
      editable: true,
      dayMaxEvents: true, // allow "more" link when too many events
      events: events
    });

    calendar.render();
  });

</script>
<style>

  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 1100px;
    margin: 0 auto;
  }

</style>
</head>
<body>

  <div id='calendar'></div>

</body>
</html>
