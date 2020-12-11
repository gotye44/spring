<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces="true" %>

<script>

	window.onload = function() {
		var imageURL = "getPicture.do?picture=${member.picture}"
		$("div#pictureView").css({
			"background-image":"url("+imageURL+")",
			"background-position":"center",
			"background-size":"cover",
			"background-repeat":"no-repeat"
		})
		
		$("#modifyBtn").on("click", function(e) {
			var form = $("form[role='form']")
			form.submit()
		})
		$("#cancleBtn").on("click", function(e) {
			history.go(-1)
		})
	}

	function imageChange_go() {
		var image = $("input#inputFile")[0]
		
		preViewPicture(image,$("div#pictureView"))
		// 이미지 변경 확인
		$("input[name='uploadPicture']").val(image.files[0].name)
	}
</script>