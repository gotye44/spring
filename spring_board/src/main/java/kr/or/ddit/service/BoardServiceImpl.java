package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.command.PageMaker;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dao.BoardDAO;
import kr.or.ddit.dao.ReplyDAO;
import kr.or.ddit.dto.BoardVO;

public class BoardServiceImpl implements BoardService{

	private BoardDAO boardDAO;
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	private ReplyDAO replyDAO;
	
	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}
	@Override
	public Map<String, Object> getBoardList(SearchCriteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<>();
		
		List<BoardVO> boardList = boardDAO.selectSearchBoardList(cri);
		
		for(BoardVO board : boardList) {
			int replycnt = replyDAO.countReply(board.getBno());
			board.setReplycnt(replycnt);
		}
		
		
		int totalCount = boardDAO.selectSearchBoardListCount(cri);
		
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		dataMap.put("boardList",boardList);
		dataMap.put("pageMaker",pageMaker);
		return dataMap;
	}

	@Override
	public BoardVO getBoard(int bno) throws SQLException {
		BoardVO board = boardDAO.selectBoardByBno(bno);
		boardDAO.increaseViewCount(bno);
		return board;
	}

	@Override
	public BoardVO getBoardModify(int bno) throws SQLException {
		BoardVO board = boardDAO.selectBoardByBno(bno);
		return board;
	}

	@Override
	public void write(BoardVO board) throws SQLException {
		int bno = boardDAO.selectBoardSequenceNextValue();
		board.setBno(bno);
		boardDAO.insertBoard(board);
	}

	@Override
	public void modify(BoardVO board) throws SQLException {
		boardDAO.updateBoard(board);
	}

	@Override
	public void remove(int bno) throws SQLException {
		boardDAO.deleteBoard(bno);
	}

}
