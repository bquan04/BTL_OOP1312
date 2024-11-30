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
<title>Animed</title>
<%@ include file="/views/common/cssboostrap.jsp"%>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="apps" id="actionMovie"
		style="width: 100%; margin: 20px auto;">
		<div class="container" style="margin-top: 60px;">
			<h2>Chỉnh Sửa Thông Tin</h2>
			<hr>
			<form action="edit-account" class="formeditaccount row" method="post"  enctype="multipart/form-data">
				<div class="col-md-3">
					<img id="avatar" src="images/users_avt/${person.getAvatar()}"
						style="width: 100%; margin-bottom: 10px; height: 250px; border-radius: 50%"
						alt="" name="avatar">
					<div style="margin: 0 auto; width: 80%;">
						<input id="avatar-input" type="file" value="Chọn Ảnh Đại Diện"
							accept=".jpg,.png" onchange="loadFile()" name="imgAvt">
					</div>
				</div>


				<div class="col-md-9 editinfor">
					<div class="form-group row">
						<label for="inputPassword3" class="col-sm-2 col-form-label">Họ
							Và Tên</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="fullName"
								value="${person.getFullName()}" required>
 
						</div>
					</div>
					<div class="form-group row">
						<label for="inputPassword3" class="col-sm-2 col-form-label">Tên Tài Khoản</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="username"
								value="${person.getUsername()}" required>
 
						</div>
					</div>
					<div class="form-group row">
						<label for="inputPassword3" class="col-sm-2 col-form-label">Email</label>
						<div class="col-sm-10">
							<input type="email" class="form-control" name="email"
								value="${person.getEmail()}">
						</div>
					</div>

					<div class="form-group row">
						<label for="inputPassword3" class="col-sm-2 col-form-label">Ngày
							Sinh</label>
						<div class="col-sm-10">
							<input type="date" class="form-control" name="birth" required
								value="<fmt:formatDate
						value="${person.getDateOfBirth()}" pattern="yyyy-MM-dd" />">
						</div>
					</div>
					<div style="display: flex; justify-content: end;">
						<button class="btn btn-dark" type="submit">Lưu Thay Đổi</button>
					</div>
				</div>
			</form>
			<p>${errorUsername}</p>
		</div>
	</div>
	<%@ include file="/views/common/jsboostrap.jsp"%>
	<script type="text/javascript">
		var defaultAvatar = "images/${user.getAvatar()}";
		var id = document.getElementById('avatar-input');
		var avatarImg = document.getElementById('avatar');

		function loadFile() {
			var file = id.files[0];
			var reader = new FileReader();
			reader.onload = function() {
				avatarImg.src = reader.result;
			}

			if (file
					&& (file.name.endsWith('.jpg') || file.name
							.endsWith('.png'))) {
				reader.readAsDataURL(file);
			} else {
				id.value = ''; // reset value của input file
				avatarImg.src = defaultAvatar;
				alert('Vui lòng chọn ảnh định dạng .jpg hoặc .png');
			}

		}
	</script>
</body>
</html>
