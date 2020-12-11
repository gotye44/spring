package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.Map;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.ReplyVO;

public interface ReplyService {
	
	Map<String, Object> getReplyList(int bno, SearchCriteria cri) throws SQLException;
	
	void registReply(ReplyVO reply) throws SQLException;
	
	void modifyReply(ReplyVO reply) throws SQLException;
	
	void removeReply(int rno) throws SQLException;
}
