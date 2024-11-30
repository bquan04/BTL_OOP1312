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
	<div class = "container-fluid" style="margin-top: 60px">
		<h2>Phim Đã Xem</h2>
		<table class="table table-striped align-middle">
		  <thead>
		    <tr class = "text-start">
		      <th scope="col">Hình</th>
		      <th scope="col">Tên</th>
		      <th scope="col"></th>
		      <th scope="col"></th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach var = "item" items="${filmList}">
		  		<tr class = "text-start">
			      <td>
			      	 <a href='<c:url value="/film-detail?action=watch&filmid=${item.getFilmID()}&episodeid=${item.getLastestEpID()}"></c:url>' style = "width: 100px; height: 60px; display: block;">
			      	 	<img style = "max-width: 100%; max-height: 100%; object-fit: cover" alt="" src="/Animed/images/film_posters/${item.getPoster()}"/>
			      	 </a>
			      </td>
			      <td>${item.getFilmName()}</td>
			      <td>
			      	 <a href='<c:url value="/film-detail?action=watch&filmid=${item.getFilmID()}&episodeid=${item.getLastestEpID()}"></c:url>'>
			      	 	<button class = "btn btn-primary">Xem Ngay</button>
			      	 </a>
			      </td>
			      <td>
			      	<a style = "color: red; text-decoration: none;" href = '<c:url value="/delete-history?filmid=${item.getFilmID()}&personid=${currentPerson.personID}"></c:url>'>
			      		<i class = "ti-trash"></i>
			      	</a>
			      </td>
			    </tr>
		  	</c:forEach>
		  </tbody>
		</table>
	</div>
</body>
</html>