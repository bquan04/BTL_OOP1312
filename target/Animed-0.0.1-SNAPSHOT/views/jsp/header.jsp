<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>

<div class="onhead">
	<c:url var="app" value="/app"></c:url>
	<div class="header fixed-top"
		style="background-color: rgba(255, 255, 255, 1); height: auto;">
		<div class="container d-flex flex-wrap"
			style="justify-content: space-between; text-align: center; margin: 0px auto; padding: 10px;">
			<a href="home" class="d-flex align-items-center mb-3 mb-lg-0 me-lg-auto text-dark text-decoration-none">
				<img alt="" src="/Animed/views/img/logo__img.png"
				style="width: 180px">
			</a>
			
			<!-- Header search -->
			<div class = "header-search" style="display: flex; width: 400px; height:40px">
				<form action="HomeAllController" 
					style="display: flex; justify-content: space-around; margin: 0 auto;">
					<div style="display: flex; width: 25%; justify-content: center;">
						<select class="btn btn-dark" name="genres"
							style="width: 100%; text-align: left;">
							<option value="0">Tất Cả</option>
							<option value="1">Phim Hành Động</option>
							<option value="2">Phim Tình Cảm</option>
							<option value="3">Phim Kinh Dị</option>
							<option value="4">Phim Khoa Học Viễn Tưởng</option>
							<option value="5">Phim Hoạt Hình</option>
						</select>
					</div>
					<div style="display: flex; justify-content: center;">
						<input type="search" placeholder="Search" class="form-control" margin-right: 10px;" name="search">
						<button class="btn btn-dark"">Tìm</button>
					</div>
				</form>
			</div>
			<!-- End Header search -->
			<div
				style="display: flex; justify-content: space-between; align-items: center; cursor: pointer;">
				<c:choose>
					<c:when test="${ not empty sessionScope.currentPerson.avatar }">
						<img src="images/${sessionScope.currentPerson.avatar }"
							alt="" style="border-radius: 50%; width: 40px; height: 40px">
					</c:when>
					<c:otherwise>
						<img src="/Animed/views/img/avt.png" alt=""
							style="border-radius: 50%; width: 40px;">
					</c:otherwise>
				</c:choose>
				<h5
					style="margin: 0 0 0 10px; font-style: italic; font-family: 'Times New Roman', Times, serif; color: aliceblue; font-weight: bold;">
					<c:choose>
						<c:when test="${ not empty sessionScope.currentPerson }">
										Welcome, 	${sessionScope.currentPerson.fullName}
								</c:when>
						<c:otherwise>
										Đăng Nhập
								</c:otherwise>
					</c:choose>
				</h5>
			</div>
		</div>
		<div class="rows bg-light">
			<nav class="navbar navbar-expand-lg navbar-light bg-light"
				style="margin-left: 10%;">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/home"><span><i
						class='bx bxs-home'></i></span> Trang Chủ</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mr-auto navs">
						<c:choose>
							<c:when test="${sessionScope.currentPerson.adminRole==false}">
								<li class="nav-item"><a class="nav-link" href="Favorite"
									style="color: #D21312; cursor: pointer;" class='bx bxs-heart'><i
										class='bx bxs-heart'></i>Yêu Thích</a></li>
								<li class="nav-item"><a class="nav-link" href="logout"
									style="color: #D21312; cursor: pointer;" class='bx bxs-heart'>
										<i class='bx bxs-log-out'></i>Đăng Xuất
								</a></li>
								<li class="nav-item"><a class="nav-link" href="index"
									href="index"><span><i class='bx bxs-film'></i></span>Phim Truyện </a>
								</li>
							</c:when>
							<c:when test="${sessionScope.currentPerson.adminRole == true}">
								<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/film-manager"
									style="color: green; cursor: pointer;" class='bx bxs-heart'><i
										class='bx bxs-book-content'></i>Quản Lý Phim</a></li>
								<li class="nav-item"><a class="nav-link" href="account-manager"
									style="color: #5F264A; cursor: pointer;" class='bx bxs-heart'><i class='bx bxs-user-account'></i>Quản Lý Tài Khoản</a></li>
								<li class="nav-item"><a class="nav-link"
									href="VideoFavorite" style="color: #F7D060; cursor: pointer;"
									class='bx bxs-heart'>
									<i class='bx bxs-book-content'></i>Thống Kê</a>
								</li>
								<li class="nav-item"><a class="nav-link" href="Favorite"
									style="color: #D21312; cursor: pointer;" class='bx bxs-heart'><i
										class='bx bxs-heart'></i>Yêu Thích</a>
								</li>
								<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout"
									style="cursor: pointer;" class='bx bxs-heart'> <i
										class='bx bxs-log-out'></i>Đăng Xuất
									</a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login"><span><i
											class='bx bxs-contact'></i></span>Đăng Nhập</a></li>
								<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/register"
									href=""><span><i class='bx bxs-log-in'></i></span>Đăng Ký</a></li>
								<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/home"
									href="index"><span><i class='bx bxs-film'></i></span>Phim
										Truyện </a></li>
							</c:otherwise>
						</c:choose>

					</ul>
				</div>
			</nav>

		</div>
	</div>
	
</div>

