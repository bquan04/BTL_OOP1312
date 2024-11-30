<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<%@ include file="/views/common/cssboostrap.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Animed</title>
<%@ include file="/views/common/cssboostrap.jsp"%>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="apps" id="actionMovie" style="width: 100%; margin-top: 60px">
		<div class="container" style="margin: 10px auto;">
			<h2>${ editTitle }</h2>
			<hr>
			<form action="${pageContext.request.contextPath}/edit-film" method="post"  class="formaddvideo" enctype = "multipart/form-data">
				
				<c:set value="${film.getPoster() }" var="img" scope="session"></c:set>
				<div class="form-group">
					<label for="exampleInputEmail1">Tên Phim</label> 
					<input type="text" name="filmName" class="form-control" placeholder="Nhập tên phim"
						value="${film.getFilmName()}">
				</div>
				
				<div class="form-group">
					<label for="exampleInputPassword1">Ảnh Bìa</label> <br> 
					<img id="avatar" src="/Animed/images/film_posters/${film.getPoster()}" alt=""
						style="width: 150px; height: 100px; box-shadow: rgba(0, 0, 0, 0.4) 0px 0px 10px;">
					<br> <br> 
					<input id="poster-input" type="file" value="Chọn Ảnh" accept=".jpg,.png" 
						onchange="loadFile()" name="imgPoster">
				</div>

				<div class="form-group">
					<label for="exampleInputEmail1">Chất Lượng</label> <br> 
					<select name="quality" id="" style="text-align: left;"
						class="btn btn-dark">
						<option value="HD">HD</option>
						<option value="FULLHD">FULL HD</option>
						<option value="2K">2K</option>
						<option value="2K HDR">2K HDR</option>
						<option value="4K">4K</option>
					</select>
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">Thể Loại</label> <br> 
					<select name="genreID" id="" style="text-align: left;" class="btn btn-dark">
						<c:forEach var="item" items="${genreList}">
							<option value="${item.getGenreID() }"
								${item.getGenreID() == checkGenre?'selected':'' }>${item.getGenreName() }
							</option>
						</c:forEach>
					</select>
				</div>
     				<div class="form-group">
					<label for="" class="badge badge-success">Kích Hoạt Video</label> <br>
					<input type="radio" value="true" name="active"
						${film.getActive()?'checked':'' }> 
					<label for="">Kích hoạt </label> <br> 
					<input ${film.getActive()?'':'checked' }
						type="radio" name="active" value="false"> 
					<label for="">Không Kích Hoạt</label>

				</div>
				<p>${film.getDescriptions() }</p>
				<div class="form-group">
					<label for="">Mô Tả</label>
					<textarea id="" cols="10" rows="5" name="descriptions"
						class="form-control">
					</textarea>
				</div>
				<button type="submit" class="btn btn-success">${editBtnName}</button>
			</form>
			<p>${message}</p>
			<div class = "formaddvideo">
				<!-- Button trigger modal -->
				
				<a href='<c:url value="/edit-episode?episodeAction=new"></c:url>'
					class="btn btn-success" data-bs-toggle="modal" data-bs-target="#episodeModal">
					Thêm Tập Phim Mới
				</a>
				
				
				<!-- Modal -->
				<div class="modal fade" id="episodeModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h1 class="modal-title fs-5" id="exampleModalLabel">${ editEpisodeTitle }</h1>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
						  <form action="${pageContext.request.contextPath}/edit-episode" method="post" enctype = "multipart/form-data">
						  	<div class="form-group">
								<label for="exampleInputEmail1">Tên Tập Phim</label> 
								<input type="text" name="episodeName" class="form-control" placeholder="Nhập tên tập phim"
									value="${episode.getEpisodeName()}">
							</div>
							
							<div class="form-group">
								<label for="exampleInputPassword1">Ảnh Bìa</label> <br> 
								<img id="avatar" src="/Animed/images/episode_thumbnails/${episode.getThumbnail()}" alt=""
									style="width: 150px; height: 100px; box-shadow: rgba(0, 0, 0, 0.4) 0px 0px 10px;">
								<br> <br> 
								<input id="thumbnail-input" type="file" value="Chọn Ảnh Đại Diện" accept=".jpg,.png" 
									onchange="" name="episodeThumbnail">
							</div>
							
							<div class="form-group">
								<label for="exampleInputEmail1">Link Youtube</label> 
								<input type="text" name="episodeLink" class="form-control" placeholder="Nhập link"
									value="${episode.getLink()}">
							</div>
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
				        	<button type="submit" class="btn btn-primary">Lưu</button>
						  </form>
				      </div>
						
				    </div>
				  </div>
				</div>
				<table class="table">
				  <thead>
				    <tr>
				      <th scope="col">ID</th>
				      <th scope="col">Tên Tập Phim</th>
				      <th scope="col">Thumbnail</th>
				      <th scope="col">Link Youtube</th>
				      <th scope="col">Chỉnh sửa</th>
				      <th scope="col">Xóa</th>
				    </tr>
				  </thead>
				  <tbody>
				    <c:forEach var = "item" items = "${episodeList}">
					    <tr>
					      <th scope="row">${item.getEpisodeID()}</th>
					      <td>${ item.getEpisodeName() }</td>
					      <td><img src="/Animed/images/episode_thumbnails/${item.getThumbnail()}"
								style="height: 80px;" alt="..."></td>
					      <td>${item.getLink()}</td>
					      <td>
							<span aria-controls="v-pills-profile" data-bs-toggle="modal" data-bs-target="#updateEpisodeModal${item.getEpisodeID()}"><i
								class='bx bxs-edit'></i>
							</span>
					      </td>
					      <td>
							<span aria-controls="v-pills-profile" data-bs-toggle="modal" data-bs-target="#deleteEpisodeModal${item.getEpisodeID()}">
								<i class='bx bxs-trash-alt'></i>
							</span>
					      </td>
					    </tr>
					    <!-- Update Episode Modal -->
						<div class="modal fade" id="updateEpisodeModal${item.getEpisodeID()}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h1 class="modal-title fs-5" id="exampleModalLabel">Chỉnh Sửa Tập Phim</h1>
						        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						      </div>
						      <div class="modal-body">
								  <form action="${pageContext.request.contextPath}/update-episode" method="post" enctype = "multipart/form-data">
								  	<div class="form-group" hidden = "true">
										<label for="exampleInputEmail1">ID Tập Phim</label> 
										<input type="text" name="episodeID" class="form-control" placeholder="ID"
											value="${item.getEpisodeID()}">
									</div>
								  	
								  	<div class="form-group">
										<label for="exampleInputEmail1">Tên Tập Phim</label> 
										<input type="text" name="episodeName" class="form-control" placeholder="Nhập tên tập phim"
											value="${item.getEpisodeName()}">
									</div>
									
									<div class="form-group">
										<label for="exampleInputPassword1">Ảnh Bìa</label> <br> 
										<img id="avatar" src="/Animed/images/episode_thumbnails/${item.getThumbnail()}" alt=""
											style="width: 150px; height: 100px; box-shadow: rgba(0, 0, 0, 0.4) 0px 0px 10px;">
										<br> <br> 
										<input id="thumbnail-input" type="file" value="Chọn Ảnh" accept=".jpg,.png" 
											onchange="" name="episodeThumbnail">
									</div>
									
									<div class="form-group">
										<label for="exampleInputEmail1">Link Youtube</label> 
										<input type="text" name="episodeLink" class="form-control" placeholder="Nhập link"
											value="${item.getLink()}">
									</div>
									<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
						        	<button type="submit" class="btn btn-primary">Lưu</button>
								  </form>
						      </div>
								
						    </div>
						  </div>
						</div>
				    	<!-- Delete Episode Modal -->
						<div class="modal fade" id="deleteEpisodeModal${item.getEpisodeID()}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h1 class="modal-title fs-5" id="exampleModalLabel">Xóa Tập Phim</h1>
						        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						      </div>
						      <div class="modal-body">
						        Xóa tập phim không thể khôi phục lại. Bạn có chắc muốn xóa không?
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Không</button>
						        <a href = '<c:url value = "/delete-episode?id=${item.getEpisodeID()}"></c:url>' class="btn btn-primary">Có</a>
						      </div>
						    </div>
						  </div>
						</div>
				    </c:forEach>
				  </tbody>
				</table>
			</div>
			<c:if test="${not empty addEpError}">
			    <script>
			    window.addEventListener("load",function(){
			         alert(${addEpError});
			    }
			    </script>
			</c:if>
		</div>
	</div>
	<script type="text/javascript">
		var defaultAvatar = "/Animed/src/main/webapp/images/${film.getPoster()}";
		var id = document.getElementById("poster-input");
		var avatarImg = document.getElementById('avatar');
		function loadFile() {
			var file = id.files[0]; 
			var reader = new FileReader();
			reader.onload = function() {
				avatarImg.src = reader.result;
			}
			if (file && (file.name.endsWith('.jpg') || file.name
							.endsWith('.png'))) {
				reader.readAsDataURL(file);
			} else {
				id.value = ''; // reset value của input file
				avatarImg.src = defaultAvatar;
				alert('Vui lòng chọn ảnh định dạng .jpg hoặc .png');
			}
		}
	</script>
	<c:if test="${not empty addEpError}">
	  <script>alert("Bạn chưa thêm tập phim nào. Vui lòng thêm ít nhất một tập!");
	</script></c:if>
</body>
</html>
