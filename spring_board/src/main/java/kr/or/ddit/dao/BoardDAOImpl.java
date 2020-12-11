package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.BoardVO;

public class BoardDAOImpl implements BoardDAO{

	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<BoardVO> selectSearchBoardList(SearchCriteria cri) throws SQLException {
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<BoardVO> boardList = sqlSession.selectList("Board-Mapper.selectSearchBoardList", cri, rowBounds);
		
		return boardList;
	}

	@Override
	public int selectSearchBoardListCount(SearchCriteria cri) throws SQLException {
		int count = sqlSession.selectOne("Board-Mapper.selectSearchBoardListCount", cri);
		return count;
	}

	@Override
	public BoardVO selectBoardByBno(int bno) throws SQLException {
		BoardVO board = sqlSession.selectOne("Board-Mapper.selectBoardByBno", bno);
		return board;
	}

	@Override
	public void insertBoard(BoardVO board) throws SQLException {
		sqlSession.update("Board-Mapper.insertBoard", board);
	}

	@Override
	public void deleteBoard(int bno) throws SQLException {
		sqlSession.update("Board-Mapper.deleteBoard", bno);
	}

	@Override
	public void updateBoard(BoardVO board) throws SQLException {
		sqlSession.update("Board-Mapper.updateBoard", board);
	}

	@Override
	public void increaseViewCount(int bno) throws SQLException {
		sqlSession.update("Board-Mapper.increaseViewCount", bno);
	}

	@Override
	public int selectBoardSequenceNextValue() throws SQLException {
		int seq_num = sqlSession.selectOne("Board-Mapper.selectBoardSequenceNextValue");
		return seq_num;
	}


}
