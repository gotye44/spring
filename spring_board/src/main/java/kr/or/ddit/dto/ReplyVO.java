package kr.or.ddit.dto;

import java.util.Date;

public class ReplyVO {
	private int rno;
	private int bno;
	private String replyer;
	private String replytext;
	private Date regdate;
	private Date updatedate;
	private String picture;
	
	private int replycnt;
	public int getReplycnt() {
		return replycnt;
	}



	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
	}



	public ReplyVO() {}
	
	
	
	public ReplyVO(int rno, int bno, String replyer, String replytext, Date regdate, Date updatedate, String picture) {
		super();
		this.rno = rno;
		this.bno = bno;
		this.replyer = replyer;
		this.replytext = replytext;
		this.regdate = regdate;
		this.updatedate = updatedate;
		this.picture = picture;
	}



	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public String getReplytext() {
		return replytext;
	}
	public void setReplytext(String replytext) {
		this.replytext = replytext;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}



	public String getPicture() {
		return picture;
	}



	public void setPicture(String picture) {
		this.picture = picture;
	}
}
