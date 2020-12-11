<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<body>


<!-- Content Wrapper. Contains page content -->
  <div >
   <jsp:include page="/WEB-INF/views/content_header.jsp">
   	<jsp:param value="자유게시판" name="subject"/>
   	<jsp:param value="수정" name="item"/>
   	<jsp:param value="modifyForm.do" name="url"/>
   </jsp:include>

    <!-- Main content -->
    <section class="content container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-outline card-info">
					<div class="card-header row">
						<h4 class="col-md-8">게시글 수정</h4>
						<div class="col-md-4">
							<div class="float-right">
								<button type="button" class="btn btn-warning" id="modifyBtn">수 정</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn btn-default " id="cancelBtn">취 소</button>
							</div>
						</div>
					</div><!--end card-header  -->
					<div class="card-body">
						<form role="form" method="post" action="modify.do" name="modifyForm">
							<input type="hidden" name="bno" value="${board.bno }">
							<div class="form-group">
								<label for="title">제 목</label> 
								<input type="text" class="form-control" value="${board.title}" id="title" name="title">
							</div>
							<div class="form-group">
								<label for="writer">작성자</label> 
								<input type="text" class="form-control" value="${board.writer}" readonly id="writer" name="writer">
							</div>
							<div class="form-group">
								<div class="form-group">
								<label for="content">내 용</label>
								<textarea class="textarea" name="content" id="content" rows="10"
									placeholder="1000자 내외로 작성하세요." style="display: none;">${fn:escapeXml(board.content)}</textarea>
								</div>
							</div>
						</form>
					</div><!--end card-body  -->
					
				</div><!-- end card -->				
			</div><!-- end col-md-12 -->
		</div><!-- end row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <script type="text/javascript">
  	window.onload = function() {
	  	$("#modifyBtn").on("click", function (e) {
			 var title = $("input[name='title']")
			 if(title.val()==""){
				 alert("제목은 필수입니다.")
				 title.focus()
				 return
			 }
			 
			 var content = $("textarea[name='content']")
			 if(content.val()==""){
				 alert("내용을 입력해주세요.")
				 content.focus()
				 return
			 }
			 var form = document.modifyForm
			 form.submit()
		})
	}
  </script>
   <%@ include file="/WEB-INF/views/common/summernote.jsp" %>
 </body>