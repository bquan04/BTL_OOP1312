<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<%@ include file="/views/common/cssboostrap.jsp"%>
<%@ include file="/views/common/jsboostrap.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Animed</title>
<script src="https://kit.fontawesome.com/273264477c.js" crossorigin="anonymous"></script>
<style>
.pass-control {
	position: relative;
	padding-bottom: 30px;
}

.pass-control .showPassBtn {
	position: absolute;
    top: 26%;
    right: 4px;
    color: black;
    cursor: pointer;
    padding: 8px;
    transition: .3s ease all;
}

.pass-control .showPassBtn:hover {
	color: #fa6675;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="apps" id="Animed" style="width: 100%; margin-top: 60px">
		<div class="container " id="register" ng-app="myApp"
			ng-controller="registerCtrl" style="width: 80%; margin: 30px auto;">
			<h2 style="margin-left: -10px">Đăng Ký</h2>
			<hr>
			<form action="register" method="post" name="form1" class="forms">
				<div class="form-group valid">
					<label for="exampleInputEmail1">Nhập Họ Và Tên</label> <input value="${person.getFullName()}"
						type="text" class="form-control" name="fullName" required
						placeholder="Nhập họ và tên">

				</div>
				<div class="form-group valid">
					<label for="exampleInputEmail1">Tên Tài Khoản</label> <input value="${person.getUsername()}"
						type="text" class="form-control" name="username" required
					 	placeholder="Nhập tên tài khoản"> 
				</div>

				<div class="form-group valid pass-control" style = "padding: 0;">
					<label>Mật Khẩu</label>
					<input type="password" class="form-control pass" name="password" value="${person.getPass()}"
						required aria-label="Recipient's username" id="password-field"
						aria-describedby="button-addon2 " placeholder="Nhập mật khẩu">
					<i class="fa-regular fa-eye-slash showPassBtn" style = 'top: 43%;' onclick = "togglePassword('password-field', this)"></i>
				</div>
				<div class="form-group valid pass-control" style = "padding: 0;">
					<label>Xác Nhận Mật Khẩu</label>
					<input type="password" class="form-control pass" name="confirm"
						required aria-label="Recipient's username"
						id="confirm-field" aria-describedby="button-addon3"
						placeholder="Xác nhận mật khẩu">
					<i class="fa-regular fa-eye-slash showPassBtn" style = 'top: 43%;' onclick = "togglePassword('confirm-field', this)"></i>
				</div>

				<div class="form-group valid">
					<label for="exampleInputEmail1">Ngày Sinh</label> 
					<input ng-model="birthday" type="date" class="form-control" value="<fmt:formatDate
						value="${person.getDateOfBirth()}" pattern="yyyy-MM-dd" />"
						name="birthday" required> 

				</div>
				<div class="form-group valid">
					<label for="exampleInputEmail1">Email</label> 
					<input type="email" required ng-model="email" class="form-control" name="email" 
						value="${person.getEmail() }" placeholder="Nhập email "> 
				</div>
				<p>${errorUsername }</p>
				<div class="controller" style="text-align: center;">
					<button ng-disabled="form1.$invalid " class="btn btn-dark "
						style="width: 200px;" type="submit">Đăng Ký</button>
				</div>

			</form>

		</div>
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
