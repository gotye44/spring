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
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.BoardVO;
import kr.or.ddit.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/main")
	public void main() {}
	
	@RequestMapping("/list")
	public ModelAndView list(SearchCriteria cri) throws SQLException {
		
		String url = "/board/list.open";
		ModelAndView mnv = new ModelAndView();
		
		Map<String, Object> dataMap = boardService.getBoardList(cri);
		
		mnv.addObject("dataMap",dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/registForm")
	public String registForm() {
		return "/board/regist.open";
	}
	
	@RequestMapping("/regist")
	public void regist(BoardVO board, HttpServletResponse response) throws Exception {
		
		
		boardService.write(board);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("window.opener.location.reload(true);window.close();");
		out.println("</script>");
	}
	
	@RequestMapping("/detail")
	public String detail(int bno,@RequestParam(defaultValue="")String from, Model model) throws SQLException {
		String url = "/board/detail.open";
		
		BoardVO board;
		if(from !=null && from.equals("modify")) {
			board = boardService.getBoardModify(bno);
		}else {
			board = boardService.getBoard(bno);
		}
		
		model.addAttribute("board", board);
		
		return url;
	}
	
	@RequestMapping("/modifyForm")
	public String modifyForm(int bno, Model model) throws SQLException {
		String url = "/board/modify.open";
		
		BoardVO board = boardService.getBoardModify(bno);
		
		model.addAttribute("board", board);
		
		return url;
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public void modify(BoardVO board, HttpServletResponse response) throws Exception{
		
		boardService.modify(board);
			
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String output = ""
				+"<script>"
				+"alert('수정되었습니다');"
				+"location.href='detail.do?bno="+board.getBno()+"&from=modify';"
				+"window.opener.parent.location.reload();"
				+"</script>";
		out.println(output);
		out.close();
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public void remove(int bno, HttpServletResponse response) throws Exception{
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		boardService.remove(bno);
			
		out.println("<script>");
		out.println("window.location.reload(true);");
		out.println("window.close();");
		out.println("</script>");
	}	
}
