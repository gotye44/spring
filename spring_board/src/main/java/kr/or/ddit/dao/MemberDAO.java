package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.MemberVO;

public interface MemberDAO {
	//회원 리스트
	List<MemberVO> selectMemberList(SearchCriteria cri) throws SQLException;
	
	//검색 결과의 전체 리스트 갯수
	int selectMemberListCount(SearchCriteria cri) throws SQLException;
	
	//회원정보 조회
	MemberVO selectMemberById(String id) throws SQLException;
	
	//회원정보 추가
	void insertMember(MemberVO member) throws SQLException;
	
	//회원정보 수정
	void updateMember(MemberVO member) throws SQLException;
	
	//회원정보 삭제
	void deleteMember(String id) throws SQLException;
	
	//회원정지
	void disabledMember(String id) throws SQLException;
	
	//회원 활성화
	void enabledMember(String id) throws SQLException;
	
}