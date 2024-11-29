<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<%@ include file="/views/common/cssboostrap.jsp"%>
<%@ include file="/views/common/jsboostrap.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<script src="https://kit.fontawesome.com/273264477c.js" crossorigin="anonymous"></script>
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

				<div class="form-group valid">
					<label>Mật Khẩu</label>
					<div class="controller">
						<div class="input-group mb-3" style="margin: 0; padding: 0">
							<input type="password" class="form-control pass" name="password" value="${person.getPass()}"
								required aria-label="Recipient's username" id="password-field"
								aria-describedby="button-addon2 " placeholder="Nhập mật khẩu"
								>
							<div class="input-group-append">
								<button class="btn btn-light" type="button" id="button-addon2">
									<i class='bx bx-show-alt'></i>
								</button>
							</div>

						</div>
						
					</div>

				</div>
				<div class="form-group valid">
					<label>Xác Nhận Mật Khẩu</label>
					<div class="controller">
						<div class="input-group mb-3" style="margin: 0; padding: 0">
							<input type="password" class="form-control pass" name="confirm"
								required aria-label="Recipient's username"
								id="confirm-field" aria-describedby="button-addon3"
								placeholder="Nhập xác nhận mật khẩu">
							<div class="input-group-append">
								<button class="btn btn-light" type="button" id="button-addon3">
									<i class='bx bx-show-alt'></i>
								</button>
							</div>
						</div>
					</div>
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
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
