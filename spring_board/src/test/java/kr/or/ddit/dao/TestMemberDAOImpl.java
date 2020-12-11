package kr.or.ddit.dao;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.dto.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/kr/or/ddit/context/root-context.xml")
public class TestMemberDAOImpl {

	@Autowired
	private MemberDAO memberDAO;
	
	@Test
	public void testSelectMember() throws SQLException{
		String id = "mimi";
		
		MemberVO member = memberDAO.selectMemberById(id);
		
		Assert.assertEquals(id, member.getId());
	}
	
	@Test
	@Transactional
	@Rollback
	public void testInsertMember() throws SQLException{
		MemberVO member = new MemberVO();
		member.setId("kaka");
		member.setName("kaka");
		member.setPwd("kaka");
		member.setEmail("kaka@kaka.cpm");
		member.setPhone("010-1111-4444");
		member.setPicture("test.jpg");
		memberDAO.insertMember(member);
	}
}
