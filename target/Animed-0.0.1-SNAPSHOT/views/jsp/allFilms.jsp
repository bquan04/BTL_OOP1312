<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<%@ include file="/views/common/cssboostrap.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Animed/src/main/webapp/views/css/style.css">
<%@ include file="/views/common/cssboostrap.jsp"%>
</head>
<body>
	<div class = "container-fluid" style = "margin-top: 120px">
		<div class = "row" style="height: 360px"> 
			<div style="height: 100%; width: 100%" id="carouselExampleInterval" class="carousel slide col-12 col-sm-12" data-bs-ride="carousel">
			  <div class="carousel-inner" style="width: 100%">
			    <c:forEach items = "${recentFilmList}" var = "item" begin = "1" end = "5">
			    	<div class="carousel-item active" >
				      <img src="/Animed/images/${item.getPoster()}" alt="..." style = "display: block; height:100%; width: 100%; max-height: 100%; max-width: 100%; object-fit: cover;">
				    </div>
			    </c:forEach>
			  </div>
			  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Previous</span>
			  </button>
			  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Next</span>
			  </button>
			</div>
		</div>
		
		<h2 id="action">
			<a href="HomePageController?page=1&genre=1" style = "text-decoration: none; display: block;">Phim Mới Cập Nhật</a>
		</h2>
		<div class = "row">
			<c:forEach var="item" items="${lastestEpUploadedList}">
				<div class = "col-lg-3 col-md-4 col-xs-6 col-6 mb-3">
					<a href='<c:url value="/film-detail?action=watch&filmid=${item.getFilmID()}&episodeid=${item.getEpisodeID()}">
						</c:url>' class="card home-films__item"> 
						<div class="home-films__item-img">
							<img src="/Animed/images/episode_thumbnails/${item.getThumbnail()}" alt="...">
						</div>
						<div class="home-films__item-info">
							<p class="home-films__item-name">${item.getFilmName()}</p>
                            <p class="home-films__item-episode">${item.getEpisodeName()}</p>
                            <p class="home-films__item-views">0</p>

						</div>
						
					</a>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>