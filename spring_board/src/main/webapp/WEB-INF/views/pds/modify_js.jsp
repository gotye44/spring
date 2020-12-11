<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces="true" %>

<script>
	window.onload = function() {
		$("div.fileInput a[name='attachedFile'] > button").on("click", function(e) {
			
			var parent = $(this).parent("a[name='attachedFile']")
			if(!confirm(parent.attr("attach-fileName")+"파일을 삭제하시겠습니까?")) return false
			
			var ano = parent.attr("attach-no")
			
			$(this).parents("li.attach-item").remove()
			
			var input = $("<input>").attr({
				"type" : "hidden",
				"name" : "deleteFile",
				"value" : ano
			})
			
			$("form[role='form']").prepend(input)
			return false
		})
		
		$("#addFileBtn").on("click", function(e) {
			var attachedFile = $("a[name='attachedFile']").length
			var inputFile = $("input[name='uploadFile']").length
			var attachCount = attachedFile + inputFile
			
			if(attachCount >=5){
				alert("파일추가는 5개까지만 가능합니다")
				return
			}
			
			var input = $("<input>").attr({
				"type" : "file",
				"name" : "uploadFile"
				}).css("display", "inline")
			var div = $("<div>").addClass("inputRow")
			div.append(input).append("<button style='border:0;outline:0;' class='badge bg-red' type='button'>X</button>")
			div.appendTo(".fileInput")
		})
		
		$(".fileInput").on("change", "input[type='file']", function(){
			if(this.files[0].size>1024*1024*40){
				alert("파일 용량이 40MB를 초과하였습니다.")
				this.value="";
				$(this).focus()
				return false
			}
		})
		
		$("#modifyBtn").on("click", function(e) {
			var form = document.modifyForm
			
			if($("input[name='title']").val() == ""){
				alert("제목은 필수입니다.")
				$("input[name='title']").focus()
				return
			}
			
			var files = $("input[name='uploadFile']")
			
			for(var file of files){
				console.log(file.name + " : " + file.value)
				if(file.value==""){
					alert("파일을 선택하세요")
					file.focus()
					return false
				}
			}
			
			form.submit()
		})
		
		$("#cancelBtn").on("click", function(e) {
			history.go(-1)
		})
	}
	
</script>