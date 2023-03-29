<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Product section-->
<section class="py-5">
    <div class="container px-4 px-lg-5 my-5">
        <div class="row gx-4 gx-lg-5 align-items-center">
            <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0"
                    src="https://dummyimage.com/600x700/dee2e6/6c757d.jpg" alt="..." /></div>
            <div class="col-md-6">
                <div class="small mb-1">SKU: BST-498</div>
                <h1 class="display-5 fw-bolder">Shop item template</h1>
                <div class="fs-5 mb-5">
                    <span class="text-decoration-line-through">$45.00</span>
                    <span>$40.00</span>
                </div>
                <p class="lead">Lorem ipsum dolor sit amet consectetur adipisicing elit. Praesentium at dolorem quidem
                    modi. Nam sequi consequatur obcaecati excepturi alias magni, accusamus eius blanditiis delectus
                    ipsam minima ea iste laborum vero?</p>
                <div class="d-flex">
                    <input class="form-control text-center me-3" id="inputQuantity" type="num" value="1"
                        style="max-width: 3rem" />
                    <button class="btn btn-outline-dark flex-shrink-0" type="button">
                        <i class="bi-cart-fill me-1"></i>
                        Add to cart
                    </button>
                </div>
            </div>
        </div>
    </div>
</section>

<input type="text" id="writer" value="user02">
<input type="text" id="content">
<button id="addContent">댓글등록</button>
<br>

<table class="table">
    <thead>
        <tr>
            <th>댓글번호</th>
            <th>작성자</th>
            <th>댓글내용</th>
            <th>삭제</th>
        </tr>
    </thead>
    <tbody id="list">
        <!-- <tr>
        	     <td>1</td>
        	    <td>user01</td>
        	    <td>reply test</td>
        	    <td><button>삭제</button></td>
        	  </tr>-->
    </tbody>
</table>

<div style="display: none">
    <table>
        <tr id="template">
            <td class="replyNo"></td>
            <td class="replyWriter"></td>
            <td><input class="replyContent" type="text"></td>
            <td><button onclick='updateReply(this)'>저장</button></td>
        </tr>
    </table>
</div>

<script>
    //목록데이터
    //let promiseResult = fetch('')
    //promiseResult.then(function(){});
    fetch('replyListAjax.do?code=${code.productCode}') //json 문자열 반환
        //.then(function (resolve){
        //	return resolve.json();
        //})
        .then(resolve => resolve.json()) //[{"id:":"user1","name":"hong"}]->자바스크립트 object[{id:'user1',name:'hong'}]
        .then(result => {
            console.log(result); //result는 배열을 의미
            //값을 이용해서 tr 생성
            result.forEach(function (reply) {
                let tr = makeRow(reply);
                document.querySelector('#list').append(tr); //tr을 tbody의 하위요소로 붙여줌
            });
        })
        .catch(reject => console.log(reject));

    let showProps = ['replyNo', 'replyWriter', 'replyContent'];

    function makeRow(reply = {}) { //reply={} object가 들어오겠다는 의미
        //tr>td*4
        let tr = document.createElement('tr');
        //tr에 이벤트 달기
        tr.addEventListener('dblclick', modifyTr);
        tr.id = reply.replyNo; //tr의 attribute에 아이디 값을 부여(댓글번호), setAttribute도 동일
        //td생성
        showProps.forEach(function (prop) { //shwProps를 반복해서 돌리겠다는 뜻
            let td = document.createElement('td');
            td.innerText = reply[prop]; //console.log(reply[prop]);
            tr.append(td);
        })
        // (showProps)항목 외 추가하는 기능
        let td = document.createElement('td');
        let btn = document.createElement('button');
        btn.addEventListener('click', removeReply); //삭제처리 기능 부여 (이벤트 유형, 함수) //removeReply()하면 이벤트 발생 전에 함수가 실행되어버림
        btn.innerText = '삭제';
        td.append(btn);
        tr.append(td);

        //document.querySelector('#list').append(tr);//tr을 tbody의 하위요소로 붙여줌
        //makeRow를 호출하는 영역에서 처리하도록 tr을 반환
        return tr;
    }

    // 댓글 수정할 수 있도록 처리하는 부분
    function modifyTr() {
        console.log(this);
        let id = this.id;
        let oldTr = this;
        //데이터 가지고 오는 fetch api사용
        //댓글 한 건 조회
        fetch('replySearchAjax.do?replyNo=' + id)
            .then(resolve => resolve.json()) //값을 제이슨 포맷으로 바꿔줌
            .then(result => {
                console.log(result);
                let data = result;
                //oldTr.innerHTML = makeEditRow2(data); //oldTr에 넣을것이므로 tr 제외하고 td부터 만듦
                let tr = makeEditRow3(data);
                document.getElementById('list').replaceChild(tr, oldTr);

            }) 
            .catch(reject => console.error(reject));

    }
    


    function makeEditRow3(data) {
    	let cloneTr = document.getElementById('template').cloneNode(true);
		cloneTr.id = data.replyNo;
		cloneTr.querySelector('.replyNo').innerText = data.replyNo;
		cloneTr.querySelector('.replyWriter').innerText = data.replyWriter;
		cloneTr.querySelector('.replyContent').value = data.replyContent;
		console.log(cloneTr);
		return cloneTr;
    }

    
    function makeEditRow2(data) {
        console.log(data);
        let str = ""
        str += "<td>" + data.replyNo + "</td>";
        str += "<td>" + data.replyWriter + "</td>";
        str += "<td><input value='" + data.replyContent + "'></td>";
        str += "<td><button onclick='updateReply()'>저장</button></td>";

        return str;
    }

    function makeEditRow() {
        let newTr = document.createElement('tr');
        let td = document.createElement('td');
        td.innerText = data.replyNo;
        newTr.append(td);

        td = document.createElement('td');
        td.innerText = data.replyWriter;
        newTr.append(td);

        td = document.createElement('td');
        let input = document.createElement('input');
        input.value = data.replyContent; // input태그는 innerText아닌 value 속성 사용
        td.append(input);
        newTr.append(td);

        td = document.createElement('td');
        let btn = document.createElement('button');
        btn.innerText = '저장';
        td.append(btn);
        newTr.append(td);


        console.log(newTr);
        document.getElementById('list').replaceChild(newTr, oldTr);
    }
	
    // 댓글 수정 업데이트
    function updateReply(elem){
    	console.log(elem)

        let tr = elem.parentElement.parentElement;
        //let no = tr.id;
        let no = tr.querySelector('.replyNo').innerText;
        let content = tr.querySelector('.replyContent').value;

        //세개의 데이터를 하나의 객체로 만들어주기
        //let data = {replyNo: no, replyWriter: writer, replyContent: content}
        //let newTr = makeRow(data); //tr,td 만들어줌(새로운 줄 생성)
    	console.log(no,content)
        
    	fetch('replyModifyAjax.do',{
    		method:'post',
    		headers:{'Content-Type': 'application/x-www-form-urlencoded'},
    		body: 'no=' + no + '&content=' + content
            //컨트롤러에 정한 파라미터 부분이 들어감
    	})
    	.then(resolve => resolve.json())
    	.then(result => {
    		console.log(result)
    		let newtr = makeRow(result.reply)
    		document.querySelector('#list').replaceChild(newtr, tr);
    	})
    	.catch(reject => console.error(reject))
    	
    	// let newTr = makeRow2(data);

       
    	
    }
    
    //댓글번호() 데이터를 삭제하는 기능(컨트롤) + 화면에서도 삭제(elem.remove())
    function removeReply() {
        console.log(this.parentElement.parentElement.id); // this 키워드: event에 등록된 콜백 함수일 경우, 이벤트를 받는 대상
        let id = this.parentElement.parentElement.id;

        fetch('replyRemoveAjax.do?replyId=' + id) //get방식
            .then(resolve => resolve.json()) //리턴값을 자바스크립트의 오브젝트 타입으로 바꿈
            .then(result => { //retCode의 값이 넘어옴
                console.log(result);
                if (result.retCode == 'Success') {
                    alert('성공');
                    document.getElementById(id).remove();
                } else if (result.retCode == 'Fail') {
                    alert('실패');
                } else {
                    alert('retCode값을 확인해주세요!')
                }
            })
            .catch(reject => console.error(reject)); //console.log는 까맣게 나옴 error는 빨갛게 나옴
    }

    // 댓글 등록
    document.querySelector('#addContent').addEventListener('click', addReply); //요소를 찾아오는 방식 중 하나 querySelector

    function addReply() {
        // writer, content의 value.(input태그에는 값을 입력한 정보들이 value 속성에 들어있음)
        let writer = document.querySelector('#writer').value;
        let content = document.querySelector('#content').value;

        //post방식으로 넘기는 방법. 객체를 넘겨준다 (자바스크립트에서는 중괄호가 객체)
        fetch('replyAddAjax.do', {
                method: 'post',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: 'writer=' + writer + '&content=' + content + '&pcode=${code.productCode}'
            })
            .then(resolve => resolve.json())
            .then(result => {
                console.log(result);
                if (result.retCode == 'Success') {
                    alert('성공');
                    let tr = makeRow(result.reply);
                    document.querySelector('#list').append(tr);

                    //댓글 입력부분 초기화
                    document.querySelector('#content').value = '';

                } else if (result.retCode == 'Fail') {
                    alert('실패');
                } else {
                    alert("retCode를 확인하세요.")
                }
            })
            .catch(reject => console.error(reject))
    }
</script>

<!-- Related items section-->
<section class="py-5 bg-light">
    <div class="container px-4 px-lg-5 mt-5">
        <h2 class="fw-bolder mb-4">Related products</h2>
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <div class="col mb-5">
                <div class="card h-100">
                    <!-- Product image-->
                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                    <!-- Product details-->
                    <div class="card-body p-4">
                        <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Fancy Product</h5>
                            <!-- Product price-->
                            $40.00 - $80.00
                        </div>
                    </div>
                    <!-- Product actions-->
                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">View options</a></div>
                    </div>
                </div>
            </div>
            <div class="col mb-5">
                <div class="card h-100">
                    <!-- Sale badge-->
                    <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale
                    </div>
                    <!-- Product image-->
                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                    <!-- Product details-->
                    <div class="card-body p-4">
                        <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Special Item</h5>
                            <!-- Product reviews-->
                            <div class="d-flex justify-content-center small text-warning mb-2">
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                            </div>
                            <!-- Product price-->
                            <span class="text-muted text-decoration-line-through">$20.00</span>
                            $18.00
                        </div>
                    </div>
                    <!-- Product actions-->
                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                    </div>
                </div>
            </div>
            <div class="col mb-5">
                <div class="card h-100">
                    <!-- Sale badge-->
                    <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale
                    </div>
                    <!-- Product image-->
                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                    <!-- Product details-->
                    <div class="card-body p-4">
                        <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Sale Item</h5>
                            <!-- Product price-->
                            <span class="text-muted text-decoration-line-through">$50.00</span>
                            $25.00
                        </div>
                    </div>
                    <!-- Product actions-->
                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                    </div>
                </div>
            </div>
            <div class="col mb-5">
                <div class="card h-100">
                    <!-- Product image-->
                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                    <!-- Product details-->
                    <div class="card-body p-4">
                        <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Popular Item</h5>
                            <!-- Product reviews-->
                            <div class="d-flex justify-content-center small text-warning mb-2">
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                            </div>
                            <!-- Product price-->
                            $40.00
                        </div>
                    </div>
                    <!-- Product actions-->
                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>