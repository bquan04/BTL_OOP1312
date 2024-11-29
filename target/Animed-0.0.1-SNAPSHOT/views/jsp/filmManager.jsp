<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/views/common/cssboostrap.jsp"%>
<link rel="stylesheet" href='<c:url value="/views/css/style.css"></c:url>'>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="apps" id="actionMovie" style="width: 100%; margin-top: 116px">
	<% response.setHeader("Cache-Control","no-cache");
	response.setHeader("Expires", "-1");
	response.setHeader("Pragma", "No-cache"); %>
		<div class="row" style="width: 80%; margin: 10px auto;">
			<c:url var="app" value="/app" />
			<h2>Quản lý Phim</h2>
			
			<hr>
			<div class="container styleChoose" style="width: 100%">
				<form action="${pageContext.request.contextPath}/film-manager" method="post"
					style="display: flex; justify-content: space-between;">
					<div class="form-group">
						<label for="exampleInputEmail1">Thể Loại</label> <br> <select
							name="filmGenre" id="" style="text-align: left;"
							class="btn btn-dark">
							<option value="-1" selected="selected">Tất Cả</option>
							<c:forEach var="item" items="${genreList}">
								<option value="${item.getGenreID()}"
									${item.getGenreID() == checkGenre?'selected':'' }>${item.getGenreName()}</option>
							</c:forEach>
						</select>
					</div>
					<div>
						<label for="">Tìm Kiếm</label> <br>
						<div style="display: flex; justify-content: space-between;">
							<input style="width: 65%;" type="search" name="searchFilm" value = "${inputTxt}" class="form-control">
							<button class="btn btn-dark">Tìm Kiếm</button>
						</div>
					</div>
					<div>
						<label for="">Phim</label> <br> 
						<a href="notactive-films"
							class="btn btn-light "> Phim Đã Ẩn <i class='bx bx-hide'></i>
						</a>
					</div>
					<div>
						<label for="">Phim</label> <br> <a href="film-manager"
							class="btn btn-light ">Phim Hiện Hành<i class='bx bx-film'></i>
						</a>
					</div>
					<div> 
						<label for="">Thêm Mới</label> <br> 
						<a href='<c:url value="/edit-film?action=new"></c:url>'
							class="btn btn-success">Thêm Phim Mới<i
							class='bx bxs-folder-plus'></i>
						</a>
					</div>
				</form>
			</div>
			<hr>
			<h3 style = "color: black;" class="">${searchRs}</h3>
			<div class="row adminrow" style="width: 100%; margin: 0 auto;">
				<c:forEach var="item" items="${filmList}">
					<c:choose>
						<c:when	test = "${item.getFilmID() > 0}">
							<div class="col-md-3">
								<div class="card" style="width: 100%; border-radius: 5px;">
									<img src="/Animed/images/${item.getPoster()}"
										class="card-img-top"
										style="border-radius: 5px 5px 0 0; height: 200px;" alt="...">
									<div class="card-body">
										<h3 class="card-title"
											style="white-space: nowrap; overflow: hidden;">${item.getFilmName() }
										</h3>
										
										<div
											style="display: flex; justify-content: space-around; margin-bottom: 10px; align-items: center;">
											<span style="font-weight: 200;"><i class='bx bxs-show'></i>${item.getTotalWatchs() }</span>
											<span><i class='bx bxs-heart'></i>${item.getTotalFavorites()}</span>
											<span><i class='bx bxs-share'></i>
												${item.getTotalShares()}</span>
										</div>
										<div style="display: flex; justify-content: space-around; margin-bottom: 10px; align-items: center;">
											
											<a href='<c:url value="/film-detail?action=watch&filmid=${item.getFilmID()}&episodeid=${item.getLastestEpID()}"></c:url>'
												class="btn btn-dark">  Chi Tiết
											</a>
											 
											<a href='<c:url value="/edit-film?action=update&id=${item.getFilmID()}"></c:url>'>
												<span aria-controls="v-pills-profile"><i
													class='bx bxs-edit'></i></span>
											</a>
											<c:choose>
												<c:when test="${item.getActive()}">
													<a style="color: red"
														href='<c:url value="/delete-film?id=${item.getFilmID()}"></c:url>'>
														<span><i class='bx bxs-trash-alt'></i></span>
													</a>
												</c:when>
												<c:otherwise>
													<a style="color: green"
														href='<c:url value="/restoreVideo?id=${item.getFilmID()}"></c:url>'>
														<span><i class='bx bxs-cylinder'></i></span>
													</a>
												</c:otherwise>
											</c:choose>
										</div>
		
									</div>
								</div>
							</div>
						</c:when>
					</c:choose>
				</c:forEach>
			</div>

		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>