<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces="true"%>    


<!-- 파일 업로드시 method="post" enctype="multipart/form-data 적어야함 -->
<form role="imageForm" action="upload/picture.do" method="post" enctype="multipart/form-data">
	<input id="inputFile" name="pictureFile" type="file" class="form-control" style="display:none;"
		onchange = "imageChange_go();">
	<input id="oldFile" name="oldPicture" type="hidden" value=""/>
	<input name="checkUpload" type="hidden" value="0"/>
</form>

<script>

	function imageChange_go(){
		$('input[name="checkUpload"]').val(0);
		preViewPicture($('input#inputFile')[0],$("div#pictureView"));
	}

	
		function upload_go(){
			if($('input[name="pictureFile"]').val()==""){
				alert("사진을 선택 하세요");
				$('input[name="pictureFile"]').click();
				return;
			};	
		
		
		//form 태그 양식을 객체화
		var form = new FormData($('form[role="imageForm"]')[0]);
		
		$.ajax({
			
			url : "<%=request.getContextPath()%>/member/picture.do",
			data : form,
			type : 'post',
			processData : false,
			contentType:false,
			success:function(data){
				//업로드 확인 변수 세팅
				$('input[name="checkUpload"]').val(1);
				
				//저장된 파일명 저장
				$('input#oldFile').val(data); //변경시 삭제될 파일명
				$('form[role="form"] input[name="picture"]').val(data);
				
				alert("사진이 업로드 되었습니다");
			},
			
			error:function(error){
				alert("현재 사진 업로드가 불가합니다.\n 관리자에게 연락 바랍니다");
			}
			
		})
		}
		
	
</script>