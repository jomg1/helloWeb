<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="//cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css" rel="stylesheet" />
	<script src="jquery/jquery-3.6.4.min.js"></script>
	<script src="//cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
	<script>
		$(document).ready(function () {
			var t = $('#example').DataTable({
				ajax: 'dataTableAjax.do',
			});
			//var counter = 1;
			$('#addRow').on('click', function () {
				$.ajax({
					url: "employeeAdd.do",
					data: {
						firstName: $('#first').val(),
						lastName: $('#last').val(),
						email: $('#email').val(),
						hireDate: $('#hireDate').val(),
						salary: $('#salary').val()
					},
					method: "post",
					dataType: "json",
					success: function (result) {
						if (result.retCode == 'Success') {
							alert("성공")
							t.row.add([result.emp.employeeId, $('#first').val(), $('#last').val(),
								$('#email').val(), $('#hireDate').val(), $('#salary')
								.val()
							]).draw(false);
							
						} else if (result.retCode == 'Fail') {
							alert("실패");
						}
					},
					error: function (reject) {
						console.error(reject)
					}

				})

				//insert할 때 select 키 써서 시퀀스 값도 넣어줘야함
				//counter++;
			});

			// Automatically add a first row of data	
			// $('#addRow').click();
			 $('#example tbody').on('click', 'tr', function () {//선택하는 기능
			        if ($(this).hasClass('selected')) {
			            $(this).removeClass('selected');
			        } else {
			            t.$('tr.selected').removeClass('selected');
			            $(this).addClass('selected');
			        }
			    });
			 
			    $('#delBtn').click(function () {//삭제하는 기능 이니까 여기에 아작스 넣어서 db에서 삭제 되었을때~ remove시키는 형태로 만들면 되게ㅐㅆ죠
			    	
			        t.row('.selected').remove().draw(false);
			    });

		});
	</script>
</head>

<body>
	<p>dataTable</p>
	<input type="text" id="first">
	<br>
	<!-- required="required" 폼태그에서 서브밋 이벤트 발생 시 값 체크  -->
	<input type="text" id="last">
	<br>
	<input type="email" id="email">
	<br>
	<input type="date" id="hireDate">
	<br>
	<input type="number" id="salary">
	<br>
	<button id="addRow">등록</button>
	<br>
	<br>
	<table id="example" class="display" style="width: 100%">
		<thead>
			<tr>
				<th>사원번호</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>Email</th>
				<th>HireDate</th>
				<th>Salary</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th>사원번호</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>Email</th>
				<th>HireDate</th>
				<th>Salary</th>
			</tr>
		</tfoot>
	</table>
	<button id="delBtn">삭제</button>
</body>

</html>