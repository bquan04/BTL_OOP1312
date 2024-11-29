<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<%@ include file="/views/common/cssboostrap.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="apps container-fluid" id="actionMovie" style="width: 100%; margin-top: 120px">
		<h2>
			Danh Sách Người Dùng <i class='bx bxs-user-circle'></i>
		</h2>
		<hr>
		<div class=" styleChoose" style="width: 100%; ">
			
			<form action="MangerAccount" method="post"
				style="display: flex; justify-content: space-between;">
				<div class="form-group">
					<label >Năm Đăng Ký</label> <br> <select
						name="year" id="" style="text-align: left;" class="btn btn-dark">
						<option value="-1" selected="selected">Tất Cả</option>
						<c:forEach var="item" items="${years }">
							<option value="${item }" ${item == check?'selected':'' }>${item}</option>
						</c:forEach>
					</select>
				</div>
				<div>
					<label for="">Tìm Kiếm</label> <br>
					<div style="display: flex; justify-content: space-between;">
						<input style="width: 65%;" type="search" name="searchAccount"
							class="form-control" placeholder="username,fullname,email" value="${key }">
						<button class="btn btn-dark">Tìm Kiếm</button>
					</div>
				</div>
				<div>
					<label for="">Tài Khoản</label> <br> <a href="AccountActive"
						style="background-color: green;" class="btn btn-light "> Đang
						Sử Dụng<i class='bx bxs-user-check'></i>
					</a>
				</div>
				<div>
					<label for="">Tài Khoản</label> <br> <a
						href="AccountUnActive" style="background-color: #B0A4A4;"
						class="btn btn-light ">Vô Hiệu Hóa<i class='bx bxs-user-x'></i>
					</a>
				</div>
				<div>
					<label for="">Export</label> <br> <a href="/Web-OOP1312/Export/Account.xlsx"
						style="background-color: #7AA874;" class="btn btn-light ">Excel<i class="fas fa-file-excel"></i>
					</a>
				</div>
		
			</form>
		</div>

		<table class="table"
				style="width: 100%; margin: 0 auto; padding: 5px; border-radius: 5px;">
				<thead>
					<tr>
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
								<tr>
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
												href='<c:url value="/DeleteAccount?id=${item.getPersonID()}"></c:url>'><i
													class='bx bxs-trash-alt'></i></a></td>
										</c:when>
										<c:otherwise>
											<td><a style="color: green;"
												href='<c:url value="/restoreAccount?id=${ item.getId()}"></c:url>'><i
													class='bx bxs-data'></i></a></td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<p>Danh Sách Trống</p>
						</c:otherwise>
					</c:choose>
				</tbody>
		</table>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>