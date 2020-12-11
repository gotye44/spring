package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.BoardVO;

public interface BoardDAO {
	List<BoardVO> selectSearchBoardList(SearchCriteria cri) throws SQLException;
	
	int selectSearchBoardListCount(SearchCriteria cri) throws SQLException;
	
	BoardVO selectBoardByBno(int bno) throws SQLException;
	
	void insertBoard(BoardVO board) throws SQLException;
	
	void deleteBoard(int bno) throws SQLException;
	
	void updateBoard(BoardVO board) throws SQLException;
	
	void increaseViewCount(int bno) throws SQLException;
	
	int selectBoardSequenceNextValue() throws SQLException;
	
}
