
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<%@ include file="/views/common/cssboostrap.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit Account</title>
<%@ include file="/views/common/cssboostrap.jsp"%>
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

.err-warning {
	display: block;
    position: absolute;
	top: 68%;
    left: 0;
    color: red;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container-fluid" style="width: 60%; margin-top: 60px;">
			<h2>Đổi Mật Khẩu</h2>
			<hr>
			<form action="change-pass" method="post">
				<div class="form-group pass-control">
					<label for="InputEmail1">Mật Khẩu Hiện Tại</label> 
					<input id = "current-password" type="password" class="form-control show_password" required="required"
							value="${oldPass}"name="oldPass">
					<i class="fa-regular fa-eye-slash showPassBtn" onclick = "togglePassword('current-password', this)"></i>
					<span class = "err-warning">${oldPassErr}</span>
				</div>
				<div class="form-group pass-control">
					<label for="exampleInputEmail1">Mật Khẩu Mới</label> 
					<input id = "new-password" type="password" class="form-control show_password" required="required"
							value="${newPass}" name="newPass"> 
					<i class="fa-regular fa-eye-slash showPassBtn" onclick = "togglePassword('new-password', this)"></i>
					<span class = "err-warning">${newPassErr}</span>
				</div>
				<div class="form-group pass-control">
					<label for="exampleInputEmail1">Xác Nhận Mật Khẩu Mới</label> 
					<input id = "comfirm-password" type="password" class="form-control show_password" required="required"
							value="${confirmPass}" name="comfirmPass">
					<i class="fa-regular fa-eye-slash showPassBtn" onclick = "togglePassword('comfirm-password', this)"></i>
					<span class = "err-warning">${comfirmPassErr}</span>
				</div>
				<div style="display: flex; justify-content: end; margin-top: 8px">
					<button class="btn btn-dark">Lưu Thay Đổi</button>
				</div>
			</form>
			<p style="color: #D21312">${error}</p>
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