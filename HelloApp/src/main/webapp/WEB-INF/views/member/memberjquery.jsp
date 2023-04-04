<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<script src="jquery/jquery-3.6.4.min.js"></script>
<script>
        //document 로딩 후 처리
        $(document).ready(function () {
            // fetch('url',{option})
            // 목록 출력 ajax
            $.ajax({
                url: "memberListJquery.do", // 클라이언트가 HTTP 요청을 보낼 서버의 URL 주소
                data: {
                    name: "홍길동", // request.getParameter('name'); 파라미터 정보
                    id: 'user01' // request.getParameter('id');
                }, // HTTP 요청과 함께 서버로 보낼 데이터
                method: "GET", // HTTP 요청 방식(GET, POST)
                dataType: "json", // 서버에서 보내줄 데이터의 타입: JSON.parse()실행
                success: function (result) { // 서버요청의 성공시 실행.
                    //JSON.parse(result); // jason 타입으로 parsing해줌
                    console.log(result);
                    $(result).each(function (idx, member) { // index, member요소의 값을 하나씩 담아두는 변수
                        console.log(idx, member);
                        $('#list').append(
                            //tr>td*4 생성
                            $('<tr id=' + member.memberId + ' />').append($('<td />').text(member.memberId),//id= 에서 띄어쓰기 하면 안 됨!!                           
                            	$('<td />').text(member.memberName),
                                $('<td />').text(member.memberAddr),
                                $('<td />').text(member.memberTel),
                                $('<td />').text(member.memberPw),
                                $('<td />').append($('<button />').text('삭제').on(
                                    'click', rowDeleteFnc)),
                                $('<td />').append($('<input />').attr('type', 'checkbox')) //아래와 같음
                            )
                        );

                    });
                },
                error: function (err) { // 서버요청의 실패시 실행.
                    console.error(err);
                }

            }) // $.ajax()

            // 추가버튼 이벤트 & 이벤트 핸들러
            $('#addBtn').on('click', function (e) {
                e.preventDefault();//전송기능 차단
                //사용자가 필수 입력값을 입력했는지 validation하고
                let isOK = true;
                isOK = isOK && $('#id').val(); 
                isOK = isOK && $('#name').val(); 
                isOK = isOK && $('#addr').val(); 
                isOK = isOK && $('#tel').val(); 
                isOK = isOK && $('#passwd').val(); 
                
                if(!isOK){
                	alert("필수항목 입력하세요!")
                	return;
                }
                //ajax 호출
                $.ajax({
    			url: "memberAddJquery.do", // 클라이언트가 HTTP 요청을 보낼 서버의 URL 주소
   				data: { id:$('#id').val() , name:$('#name').val(), tel: $('#tel').val(), addr: $('#addr').val(), pw: $('#passwd').val() },// HTTP 요청과 함께 서버로 보낼 데이터, 컨트롤러 넘어갈 때 파라미터 이름
    			method: "post",                                     // HTTP 요청 방식(GET, POST)
    			dataType: "json",                                   // 서버에서 보내줄 데이터의 타입
    			success: function (result){
					if (result.retCode == 'Success'){
						alert("성공")
				          $('#list').append(
				                    //tr>td*4 생성
				                    $('<tr />').append($('<td />').text($('#id').val()),
				                        $('<td />').text($('#name').val()),
				                        $('<td />').text($('#passwd').val()),
				                        $('<td />').text($('#addr').val()),
				                        $('<td />').text($('#tel').val()),
				                        $('<td />').append($('<button />').text('삭제').on(
				                            'click', rowDeleteFnc)),
				                        $('<td />').append($('<input type="checkbox" />'))
				                    )
				                );
					}else if (result.retCode == 'Fail'){
						//처리중 에러
						alert("실패");
					}else{
						//반환코드 확인
						alert("retCode 확인");
					}
				},
				error: function (reject){
					
				}
					
				})
					
	

            })

            // 전체선택 / 전체해제
            $('th>input[type="checkbox"]').on('change', function(){//값이 바뀌면 발생하는 이벤트 체크박스의 경우 click보다 change가 적합한 듯
                //attr() => html, prop() => DOM 속성 //TCPSchool 프로퍼티 설정 참고
                //$('td>input').attr('checked', 'checked') // attr로 하면 checked 동시에 하는게 불가능 prop 사용이 더 나음
                //attr은 id값을 주는 경우로 많이 사용됨
                //$('td>input').attr('id',344)
                //true, false 값을 주어야 할 경우는 prop 사용이 더 나음
                //console.log(this);
                //console.log(this.value);
                $('td>input').prop({
                    checked: this.checked // this.value 아닌 checked
                })
            })

            // th>input 과 td>input을 비교해서 전체선택이 되도록
            // 선택된 갯수를 비교?
            // ajax 호출의 결과로 만들어지는 부분. 이벤트 위임(ajax 호출은 비동기방식이므로 td로 호출하면 해당 td가 생성되지 않음)
            		
            $('#list').on('change','td>input[type="checkbox"]',function(){
                console.log(this);
                let checkCnt = $('td>input[type="checkbox"]:checked').length;
                let allCnt = $('td>input[type="checkbox"]').length;
                // 전체 갯수 vs 선택된 갯수 비교
                if (checkCnt == allCnt){
                	$('th>input[type="checkbox"]').prop({checked:true})
                }else {
                	$('th>input[type="checkbox"]').prop({checked:false})
                }
            })

            // 선택삭제버튼 이벤트 & 이벤트 핸들러
            $('#delSelected').on('click', function(e){
                e.preventDefault();
                let memberIdAry = '';// memberId=user01&memberId=user02&memberId=user03
                console.log($('#list input:checked').closest('tr'));
                //console.log($('#list input:checked'))
                //$('#list input:checked').parentsUntil('tbody').remove(); //가능
                $('#list input:checked').each(function(idx, item){
                	console.log($(item).parent().parent().attr('id'))
                	//memberIdAray.push({'memberId':$(item).parent().parent().attr('id')})
                    //memberIdAry.memberId = $(item).parent().parent().attr('id');
                	//$(item).parent().parent().remove();
                    //item은 input 태그
                    //$(item).closest('tr').remove(); //가장 가까운 요소를 지운다
                    memberIdAry += '&memberId=' + $(item).parent().parent().attr('id');
                })
                console.log(memberIdAry);
                
                //ajax호출
                $.ajax({
                	url:'memberRemoveJquery.do',//호출할 컨트롤
                	method:'post',
                	data: memberIdAry.substring(1), //memberId=user01&memberId=user02
                	success: function (result) {
                		if(result.retCode == 'Success')
                		$('#list input:checked').closest('tr').remove();
                		else
                			alert('error!!');
                	},
                	error: function (reject) {
                		console.log(reject)
                	}
                })
            })


            // 삭제버튼 이벤트 핸들러
            function rowDeleteFnc() {
                //parentsUntil - 조상요소 가지고 올 때 선택자에 해당하는 요소의 전까지의 요소 여기서는 tr
                //parent.parent 해도 무관
                $(this).parentsUntil('tbody').remove();
            }

        });
    </script>
</head>

<body>
	<div>
		<form>
			<table class="table" border="1">
				<tr>
					<td>Id:</td>
					<td><input type="text" id="id"></td>
				</tr>
				<tr>
					<td>Name:</td>
					<td><input type="text" id="name"></td>
				</tr>
				<tr>
					<td>Pass:</td>
					<td><input type="text" id="passwd"></td>
				</tr>
				<tr>
					<td>Addr:</td>
					<td><input type="text" id="addr"></td>
				</tr>
				<tr>
					<td>Tel:</td>
					<td><input type="text" id="tel"></td>
				</tr>
				<tr>
					<td colspan='2' align="center">
						<button id="addBtn">등록</button>
						<button id="delSelected">선택삭제</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div>
		<table class="table" border="1">
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Pass</th>
					<th>주소</th>
					<th>연락처</th>
					<th>삭제</th>
					<th><input type="checkbox"></th>
				</tr>
			</thead>
			<tbody id="list">
			</tbody>
		</table>
	</div>
</body>

</html>