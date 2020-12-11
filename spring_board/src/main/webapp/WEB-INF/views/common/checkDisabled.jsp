<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces="true" %>

<script>
	alert("정지된 계정입니다.\n사용제한으로 불가능합니다.");
	var answer = confirm("창을 닫으시려면 확인을 누르세요.");
	if(answer) window.close()
	history.go(-1)
</script>