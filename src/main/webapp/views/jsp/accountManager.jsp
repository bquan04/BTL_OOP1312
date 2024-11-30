<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<%@ include file="/views/common/cssboostrap.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Animed</title>
<%@ include file="/views/common/cssboostrap.jsp"%>
<script src="https://kit.fontawesome.com/273264477c.js" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="apps container-fluid" id="actionMovie" style="width: 100%; margin-top: 60px">
		<h2>
			Danh Sách Người Dùng <i class='bx bxs-user-circle'></i>
		</h2>
		<hr>
		<div class=" styleChoose" style="width: 100%; ">
			
			<form action="account-manager" method="post" 
				style="display: flex; justify-content: space-between;">
				<div>
					<label for="">Tìm Kiếm</label> <br>
					<div style="display: flex; justify-content: space-between;">
						<input style="width: 65%;" type="search" name="searchAccount"
							class="form-control" placeholder="username,fullname,email" value="${searchInput}">
						<button class="btn btn-dark">Tìm Kiếm</button>
					</div>
				</div>
				<div>
					<label for="">Tài Khoản</label> <br> <a href="active-acc"
						style="background-color: green;" class="btn btn-light "> Đang
						Sử Dụng<i class='bx bxs-user-check'></i>
					</a>
				</div>
				<div>
					<label for="">Tài Khoản</label> <br> <a
						href="disabled-acc" style="background-color: #B0A4A4;"
						class="btn btn-light ">Đã Khóa<i class='bx bxs-user-x'></i>
					</a>
				</div>
				<div>
					<label for="">Export</label> <br> <a href="/Animed/export/account.xlsx"
						style="background-color: #7AA874;" class="btn btn-light ">Excel<i class="fas fa-file-excel"></i>
					</a>
				</div>
		
			</form>
		</div>

		<table class="table"
				style="width: 100%; margin: 0 auto; padding: 5px; border-radius: 5px;">
				<thead>
					<tr class = "text-start">
						<th scope="col">STT</th>
						<th scope="col">ID</th>
						<th scope="col">Username</th>
						<th scope="col">Họ Và Tên</th>
						<th scope="col">Ngày Sinh</th>
						<th scope="col">Avatar</th>
						<th scope="col">Thời Gian Đăng Ký</th>
						<th scope="col">Email</th>
						<th scope="col">Role</th>
						<th scope="col">Tình trạng</th>
						<th scope="col">Khóa tài khoản</th>
						<th scope="col">Xóa</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty personList}">
							<c:forEach items="${personList}" var="item" varStatus="status">
								<tr class = "text-start">
									<th>${status.index}</th>
									<td>${item.getPersonID()}</td>
									<td>${item.getUsername()}</td>
									<td>${item.getFullName()}</td>
									<td><fmt:formatDate value="${item.getDateOfBirth()}"
											pattern="dd/MM/yyyy" /></td>
									<td><img src="/Animed/images/${item.getAvatar()}"
										alt="" style="width: 40px; height: 40px; border-radius: 50%"></td>
									<td><fmt:formatDate value="${item.getRegisterDay()}"
											pattern="dd/MM/yyyy" /></td>
									<td>${item.getEmail()}</td>
									<td><c:choose>
											<c:when test="${item.getAdminRole() }">
												Admin
											</c:when>
											<c:otherwise>
												User
											</c:otherwise>
										</c:choose></td>
									<td><c:choose>
											<c:when test="${item.getActive()}">
												Đang Sử Dụng
											</c:when>
											<c:otherwise>
												Đã Khóa
											</c:otherwise>
										</c:choose></td>
								
									<c:choose>
										<c:when test="${item.getActive()}">
											<td><a style="color: red;"
												href='<c:url value="/deactive?id=${item.getPersonID()}"></c:url>'>
													<i class="fa-solid fa-lock"></i></a></td>
										</c:when>
										<c:otherwise>
											<td><a style="color: green;"
												href='<c:url value="/restore-acc?id=${item.getPersonID()}"></c:url>'>
												<i class="fa-solid fa-unlock"></i></a></td>
										</c:otherwise>
									</c:choose>
									
									<td>
										<span style = "color: red; font-size: 16px; cursor: pointer" aria-controls="v-pills-profile" data-bs-toggle="modal" data-bs-target="#deleteAccModal${item.getPersonID()}">
											<i class="fa-solid fa-trash"></i>
										</span>
									</td>
								</tr>
								<div class="modal fade" id="deleteAccModal${item.getPersonID()}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
								  <div class="modal-dialog">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h1 class="modal-title fs-5" id="exampleModalLabel">Xóa Tài Khoản</h1>
								        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								      </div>
								      <div class="modal-body">
								         Bạn có chắc muốn xóa không?
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Không</button>
								        <a href = '<c:url value = "/delete-acc?id=${item.getPersonID()}"></c:url>' class="btn btn-primary">Có</a>
								      </div>
								    </div>
								  </div>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<p>Danh Sách Trống</p>
						</c:otherwise>
					</c:choose>
				</tbody>
		</table>
	</div>
</body>
</html>