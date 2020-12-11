<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces="true" %>

<script>

window.addEventListener('load', function() {
	$("#content").summernote({
		placeholder:"여기에 내용을 적으세요.",
		height:250,
		disableResizeEditor:true,
		callbacks:{
			onImageUpload : function(files, editor, welEditable){
				// file size validation
				for(var i=files.length-1; i>-1; i--){
					if(files[i].size > 1024*1024*5){
						alert("이미지는 5MB 미만입니다.")
						return
					}
				}
				
				// file sending
				for(var i=files.length-1; i>-1; i--){
					sendFile(files[i], this)
				}
			},
			onMediaDelete : function(target){
				var answer = confirm("정말 이미지를 삭제하시겠습니까?")
				
				if(answer){
					deleteFile(target[0].src)
				}
			}
		}
	})
});
	function sendFile(file, el) {
		var form_data = new FormData()
		form_data.append("file", file)
		$.ajax({
			data : form_data,
			type : "post",
			url : "<%=request.getContextPath()%>/uploadImg.do",
			contentType : false,
			processData : false,
			success : function(img_url) {
				$(el).summernote("editor.insertImage", img_url)
			}
		})
	}
		  	
	function deleteFile(src) {
		var splitSrc = src.split("=")
		var fileName = splitSrc[splitSrc.length-1]
		
		// alert(fileName)
		var fileData = {
			"fileName":fileName
		}
		
		$.ajax({
			url : "<%=request.getContextPath()%>/deleteImg.do",
			data : JSON.stringify(fileData),
			type : "post",
			contentType : "application/json",
			success : function(res) {
			console.log(res)
			}
		})
	}
</script>