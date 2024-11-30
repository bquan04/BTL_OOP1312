<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<%@ include file="/views/common/cssboostrap.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Animed</title>
<link rel="stylesheet" type="text/css" href="/Animed/src/main/webapp/views/css/style.css">
<%@ include file="/views/common/cssboostrap.jsp"%>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container-fluid" style = "margin-top: 60px">
        <div class="row">
            <!-- Khu vực xem phim -->
            <div class="col-md-9 col-8 col-sm-12">
                <div class="video-container">
                    <iframe src="https://www.youtube.com/embed/${episode.getLink()}?autoplay=1" allowfullscreen></iframe>
                </div>
                <div class="mt-3 action-buttons">
	                <c:choose>
	                	<c:when test = "${not empty sessionScope.currentPerson}">
	                		<c:choose>
	                			<c:when test = "${checkFav == false}">
	                				<a href = '<c:url value="/film-detail?action=favorite&filmid=${film.getFilmID()}&episodeid=${episode.getEpisodeID()}"></c:url>' class = "btn btn-primary">
	                					Yêu Thích
	                				</a>
	                			</c:when>
	                			<c:otherwise>
	                				<a href = '<c:url value="/film-detail?action=unFavorite&filmid=${film.getFilmID()}&episodeid=${episode.getEpisodeID()}"></c:url>' class = "btn btn-primary">
	                					Bỏ Yêu Thích
	                				</a>
	                			</c:otherwise>
	                		</c:choose>
	                	</c:when>
	                	<c:otherwise>
	                		
	                	</c:otherwise>
	                </c:choose>
                </div>
            </div>

            <!-- Danh sách tập phim -->
            <div class="col-md-3 col-4 col-sm-12">
                <ul class="list-group overflow-auto">
                    <c:forEach var="item" items="${episodeList}">
                    	<li>
	                    	<c:choose>
	                    		<c:when test = "${item.getEpisodeID() == watchingEpID}">
	                    			<a style = "background-color: #ffcccc; color: #fa6675; border-color: red;" href='<c:url value="/film-detail?action=watch&filmid=${film.getFilmID()}&episodeid=${item.getEpisodeID()}"></c:url>' 
		                    			class="list-group-item list-group-item-action">
				                        <img class = "episode-thumbnail" src="/Animed/images/episode_thumbnails/${item.getThumbnail()}" alt="${item.getEpisodeName()}">
				                        <p>${item.getEpisodeName()}</p>
				                    </a>
	                    		</c:when>
	                    		<c:otherwise>
		                    		<a href='<c:url value="/film-detail?action=watch&filmid=${film.getFilmID()}&episodeid=${item.getEpisodeID()}"></c:url>' 
		                    			class="list-group-item list-group-item-action" data-video-url="https://www.youtube.com/embed/VIDEO_ID_1">
				                        <img class = "episode-thumbnail" src="/Animed/images/episode_thumbnails/${item.getThumbnail()}" alt="${item.getEpisodeName()}">
				                        <p>${item.getEpisodeName()}</p>
				                    </a>
	                    		</c:otherwise>
	                    	</c:choose>
                    	</li>
                    </c:forEach>
                    
                </ul>
            </div>
        </div>
        
         <!--NỘI DUNG PHIM-->
         <div class = "row">
         	<div class="col-md-9 col-8 col-sm-12">
		        <h3>Nội dung phim</h3>
		        <p>
		            ${film.getDescriptions()}
		        </p>
		    </div>
         </div>
	        
    </div>

</body>
</html>