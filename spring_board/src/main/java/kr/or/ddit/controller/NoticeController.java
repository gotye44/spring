package kr.or.ddit.controller;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.NoticeVO;
import kr.or.ddit.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("/main")
	public String main() {
		return "/notice/main"; 
	}
	
	@RequestMapping("/list")
	public String list(SearchCriteria cri, Model model) throws SQLException {
		String url = "/notice/list.open";
		
		Map<String, Object> dataMap = noticeService.getNoticeList(cri);
		model.addAttribute("dataMap", dataMap);
		
		return url;
	}
	
	@RequestMapping("/detail")
	public String detail(int nno, @RequestParam(defaultValue="")String from, Model model) throws SQLException {
		String url = "/notice/detail.open";
		
		NoticeVO notice = new NoticeVO();
		if(from !=null && from.equals("modify")) {
			notice = noticeService.getNoticeModify(nno);
		}else {
			notice = noticeService.getNotice(nno);
		}
		model.addAttribute("notice", notice);
		
		return url;
	}
	
	@RequestMapping("/registForm")
	public String registForm() {
		return "/notice/regist.open";
	}
	
	@RequestMapping("/regist")
	public void regist(NoticeVO notice, HttpServletResponse response) throws Exception {
		
		
		noticeService.write(notice);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("window.opener.location.reload(true);window.close();");
		out.println("</script>");
	}
	
	@RequestMapping("/modifyForm")
	public String modifyForm(int nno, Model model) throws SQLException {
		String url = "/notice/modify.open";
		
		NoticeVO notice = noticeService.getNoticeModify(nno);
		
		model.addAttribute("notice", notice);
		
		return url;
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public void modify(NoticeVO notice, HttpServletResponse response) throws Exception{
		
		noticeService.modify(notice);
			
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String output = ""
				+"<script>"
				+"alert('수정되었습니다');"
				+"location.href='detail.do?nno="+notice.getNno()+"&from=modify';"
				+"window.opener.parent.location.reload();"
				+"</script>";
		out.println(output);
		out.close();
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public void remove(int nno, HttpServletResponse response) throws Exception{
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		noticeService.remove(nno);
			
		out.println("<script>");
		out.println("window.opener.location.reload(true);");
		out.println("window.close()");
		out.println("</script>");
	}
}
