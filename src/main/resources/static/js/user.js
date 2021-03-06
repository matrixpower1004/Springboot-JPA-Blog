let index = {
	init: function() {
		$("#btn-save").on("click", () => { // function을 쓰지 않고 화살표 함수를 쓰는 이유는 this를 바안딩 하기 위해서
			this.save();
		});
	},

	save: function() {
		// alert("usr의 save함수 호출됨")
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		// console.log(data);

		// ajax호출시 default가 비동기호출
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
		// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해주네요.
		$.ajax({
			// 회원가입 수행 요청
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
			dataType: "json" // 서버로 요청을 해서 응답이 왔을 때 기본적으로 문자열(생긴게 jason이라면) => javascript 오브젝트로 변경
		}).done(function(resp) {
			// console.log(resp);
			alert("회원가입이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
}

index.init();
