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
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class = "container-fluid" style = "margin-top: 60px">
		<h3>
			${searchRs}
		</h3>
		<div class = "row">
			<c:forEach var="item" items="${filmList}">
				<div class = "col-lg-3 col-md-4 col-xs-6 col-6 mb-3">
					<a href='<c:url value="/film-detail?action=watch&filmid=${item.getFilmID()}&episodeid=${item.getLastestEpID()}">
						</c:url>' class="card home-films__item"> 
						<div class="home-films__item-img">
							<img src="/Animed/images/episode_thumbnails/${item.getPoster()}" alt="...">
						</div>
						<div class="home-films__item-info">
							<p class="home-films__item-name">${item.getFilmName()}</p>
                            <div class="home-films__item-views align-middle">
                            	 <p style = "margin: 0"><i class = "ti-eye"></i>${item.getTotalViews()}</p>
                            </div>
                           
						</div>
						
					</a>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>