package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.command.PageMaker;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dao.ReplyDAO;
import kr.or.ddit.dto.ReplyVO;

public class ReplyServiceImpl implements ReplyService{
	
	private ReplyDAO replyDAO;
	
	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}
	

	@Override
	public Map<String, Object> getReplyList(int bno, SearchCriteria cri) throws SQLException {
		
		Map<String, Object> dataMap = new HashMap<>();
		
		List<ReplyVO> replyList = replyDAO.selectReplyList(bno, cri);
		
		int totalCount = replyDAO.countReply(bno);
		
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		dataMap.put("replyList", replyList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}

	@Override
	public void registReply(ReplyVO reply) throws SQLException {
		int rno = replyDAO.selectReplySeqNextValue();
		reply.setRno(rno);
		replyDAO.insertReply(reply);
	}

	@Override
	public void modifyReply(ReplyVO reply) throws SQLException {
		replyDAO.updateReply(reply);
	}

	@Override
	public void removeReply(int rno) throws SQLException {
		replyDAO.deleteReply(rno);
	}

}
