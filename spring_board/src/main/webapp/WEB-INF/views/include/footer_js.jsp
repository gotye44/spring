<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces="true" %>

<!-- handlebars -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.6/handlebars.min.js"></script>

<script type="text/x-handlebars-template"  id="subMenu-list-template" >
{{#each .}}
	<li class="nav-item subMenu" >
      	<a href="javascript:goPage('{{murl }}','{{mcode}}');" class="nav-link">
          <i class="{{micon }}"></i>
             <p>{{mname }}</p>
        </a>
	</li>
{{/each}}
</script>
<script>
	var printData=function(subMenuArr,target,templateObject){
		var template = Handlebars.compile(templateObject.html())
		var html = template(subMenuArr)
		$(".subMenu").remove();
		target.append(html)
	}
	function subMenu(mCode){
		if(mCode!="M00"){
			$.getJSON("<%=request.getContextPath()%>/common/subMenu.do?mCode="+mCode,function(data){
				printData(data,$(".subMenuList"),$("#subMenu-list-template"))
			})
		}else{
			$(".subMenuList").html("")
		}
	}
	function goPage(url,mCode){
		// HTML5 지원브라우저에서 사용 가능
		if(typeof(history.pushState) == 'function'){
			// 현재 주소를 가져온다.
			var renewURL = location.href
			// 현재 주소 중 .do 뒷 부분이 잇다면 날려버린다.
			renewURL = renewURL.substring(0, renewURL.indexOf(".do")+3);
			
			if(mCode != 'M00'){
				renewURL += "?mCode="+mCode
			}
			
			// 페이지를 리로드하지 않고 페이지 주소만 변경할 때 사용
			history.pushState(mCode, null, renewURL)
		}else{
			location.href = "#"+mCode
		}
		
		$("#if_list").attr("src", "<%=request.getContextPath()%>"+url)
	}
	
	goPage("${menu.murl}","${menu.mcode}")
	subMenu("${menu.mcode}".substring(0,3))
</script>