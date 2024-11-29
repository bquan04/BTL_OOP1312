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
	<div class="header fixed-top">
	    <nav class="navbar navbar-expand-lg navbar-light bg-light">
	        <div class="container-fluid">
	            <a href="home" class="navbar-brand">
					<img alt="" src="views/img/logo__img.png"
						style="width: 140px">
				</a>
	            <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
	                <span class="navbar-toggler-icon"></span>
	            </button>
	            <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
	                <div class="navbar-nav">
	                    <a href="home" class="nav-item nav-link active">Trang Chủ</a>
	                    <c:choose>
	                    	<c:when test = "${not empty sessionScope.currentPerson}">
	                    		<a href="${pageContext.request.contextPath}/history" class="nav-item nav-link">Phim Đã Xem</a>
	                    		<a href="${pageContext.request.contextPath}/favorite" class="nav-item nav-link">Phim Đã Thích</a>
	                    	</c:when>
	                    </c:choose>
	                    
	                    <c:choose>
	                    	<c:when test = "${sessionScope.currentPerson.adminRole == true}">
	                    		<div class="nav-item dropdown">
			                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Admin</a>
			                        <div class="dropdown-menu">
			                            <a href="${pageContext.request.contextPath}/film-manager" class="dropdown-item">Quản Lý Phim</a>
			                            <a href="${pageContext.request.contextPath}/account-manager" class="dropdown-item">Quản Lý Người Dùng</a>
			                        </div>
			                    </div>
	                    	</c:when>
	                    </c:choose>
	                </div>
	                <form class="d-flex" action = "${pageContext.request.contextPath}/home" method = "post">
	                	<div class="form-group" style = "margin-right: 10px">
							<select name="filmGenre" style="text-align: left;" class="btn btn-dark">
								<option value="-1" selected="selected">Tất Cả</option>
								<c:forEach var="item" items="${genreList}">
									<option value="${item.getGenreID()}"
										${item.getGenreID() == checkGenre?'selected':'' }>${item.getGenreName()}</option>
								</c:forEach>
							</select>
						</div>
	                    <div class="input-group">
	                        <input type="text" class="form-control" placeholder="Search" name = "searchFilm" value = "${inputTxt}">
	                        <button type="submit" class="btn btn-dark"><i class="ti-search"></i></button>
	                    </div>
	                </form>
	                <c:choose>
                    	<c:when test = "${not empty sessionScope.currentPerson}">
                    		<a type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasScrolling" aria-controls="offcanvasScrolling" >
                    			<img src="/Animed/images/users_avt/${sessionScope.currentPerson.avatar}"
									alt="" style="border-radius: 50%; width: 40px; height: 40px">
                    		</a>
                    	</c:when>
                    	<c:otherwise>
                    		<div class="navbar-nav">
			                    <a href="${pageContext.request.contextPath}/login" class="nav-item nav-link">Đăng Nhập</a>
			                    <a href="${pageContext.request.contextPath}/register" class="nav-item nav-link">Đăng Ký</a>
			                </div>
                    	</c:otherwise>
                    </c:choose>
	                
	            </div>
	        </div>
	    </nav>
	    <div class="offcanvas offcanvas-end" data-bs-scroll="true" data-bs-backdrop="false" tabindex="-1" id="offcanvasScrolling" aria-labelledby="offcanvasScrollingLabel">
		  <div class="offcanvas-header">
		    <img src="/Animed/images/users_avt/${sessionScope.currentPerson.avatar}"
									alt="" style="border-radius: 50%; width: 40px; height: 40px">
			<h5 class="offcanvas-title" id="offcanvasScrollingLabel">${sessionScope.currentPerson.fullName}</h5>
		    <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
		  </div>
		  <div class="offcanvas-body">
		    <ul class="list-group list-group-flush" style="background-color: white; border: 0; padding: 0px;">
		    	<li class="list-group-item">
		    		<a class="nav-link" href='<c:url value="/edit-account?id=${currentPerson.getPersonID()}"></c:url>'
						style="cursor: pointer;" class='bx bxs-heart'> 
						<i class='ti-user' style = "margin-right: 5px"></i>Chỉnh Sửa Thông Tin
					</a>
		    	</li>
		    	<li class="list-group-item">
		    		<a class="nav-link" href="${pageContext.request.contextPath}/change-pass" 
						style="cursor: pointer;" class='bx bxs-heart'> 
						<i class='ti-lock' style = "margin-right: 5px"></i>Đổi Mật Khẩu
					</a>
		    	</li>
		    	<li class="list-group-item">
		    		<a class="nav-link" href="logout"
						style="cursor: pointer;" class='bx bxs-heart'> 
						<i class='ti-power-off' style = "margin-right: 5px"></i>Đăng Xuất
					</a>
		    	</li>
		    </ul>
		  </div>
		</div>
	</div>
</body>
</html>