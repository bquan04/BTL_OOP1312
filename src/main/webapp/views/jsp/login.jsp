<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<%@ include file="/views/common/cssboostrap.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Animed</title>
<script src="https://kit.fontawesome.com/273264477c.js" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class = "container" style = "margin-top: 60px">
		<h1>Đăng Nhập</h1>
		<form action = "${pageContext.request.contextPath}/login" method = "post">
			<div class="form-group valid">
				<label for="exampleInputEmail1" >Tên
					Tài Khoản</label> <input type="text" class="form-control" value="${username}" name="username"
					id="exampleInputEmail1" placeholder="Nhập tên tài khoản" required>
				<span></span>

			</div>
			<div class="form-group valid pass-control">
				<label >Mật Khẩu</label>
				<input id = "password" type="password" class="form-control" value="${password}" name="password"
					aria-label="Recipient's username" id="password-field" required="required"
					aria-describedby="button-addon2 " placeholder="Nhập mật khẩu">
				<i class="fa-regular fa-eye-slash showPassBtn" style = "top: 40%;" onclick = "togglePassword('password', this)"></i>
			</div>
			<div class="form-group form-check">
				<input type="checkbox" name="rememberLogin" value="true" class="form-check-input">
				<label>Nhớ tài khoản?</label>
			</div>
			<hr>

			<div class="controller" style="text-align: center;">
				<input class="btn btn-dark" type = "submit" value = "Login"/>
			</div>
		</form>
		<p style="text-align: center; color: red">${check}</p>
		<p style="text-align: center; color: red">${message}</p>
	</div>
	<script>
	function togglePassword(inputId, button) {
           const passwordField = document.getElementById(inputId);
           if (passwordField.type === "password") {
               passwordField.type = "text";
               button.classList.remove("fa-eye-slash");
               button.classList.add("fa-eye");
           } else {
               passwordField.type = "password";
               button.classList.remove("fa-eye");
               button.classList.add("fa-eye-slash");
           }
       }
	</script>
</body>
</html>