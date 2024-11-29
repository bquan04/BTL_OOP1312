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
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container-fluid" style = "margin-top: 100px">
        <div class="row">
            <!-- Khu vực xem phim -->
            <div class="col-md-9 col-8">
                <div class="video-container">
                    <iframe src="https://www.youtube.com/embed/${episode.getLink()}?autoplay=1" allowfullscreen></iframe>
                </div>
                <div class="mt-3 text-center action-buttons">
                    <button>Chia sẻ</button>
                    <button>Yêu thích</button>
                </div>
            </div>

            <!-- Danh sách tập phim -->
            <div class="col-md-3 col-4">
                <ul class="list-group">
                    <c:forEach var="item" items="${episodeList}">
                    	<li>
                    		<a href='<c:url value="/film-detail?action=watch&filmid=${film.getFilmID()}&episodeid=${item.getEpisodeID()}"></c:url>' 
                    			class="list-group-item list-group-item-action" data-video-url="https://www.youtube.com/embed/VIDEO_ID_1">
		                        <img class = "episode-thumbnail" src="/Animed/images/episode_thumbnails/${item.getThumbnail()}" alt="${item.getEpisodeName()}">
		                        <p>${item.getEpisodeName()}</p>
		                    </a>
                    	</li>
                    </c:forEach>
                    
                </ul>
            </div>
        </div>
    </div>

    <!--NỘI DUNG PHIM-->
    <!-- Thêm vào ngay phía dưới phần hiện tại -->
    <div class="movie-description my-4">
        <h3>Nội dung phim</h3>
        <p>
            Yoichi Isagi đã bỏ lỡ cơ hội tham dự giải Cao Trung toàn quốc do đã chuyền cho đồng đội thay vì tự thân mình dứt điểm.
            Cậu là một trong 300 chân sút U-18 được tuyển chọn bởi Jinpachi Ego, người đàn ông được Hiệp Hội Bóng Đá Nhật Bản thuê sau hồi FIFA World Cup năm 2018, nhằm dẫn dắt Nhật Bản vô địch World Cup bằng cách tiêu diệt nền bóng đá Nhật Bản.
            Kế hoạch của Ego gồm việc cô lập 300 tay sút trong một nhà ngục - dưới một tổ chức với tên gọi là "Blue Lock", với mục tiêu là tạo ra chân sút "tự phụ" nhất thế giới, điều mà nền bóng đá Nhật Bản còn thiếu.
        </p>
    </div>


</body>
</html>