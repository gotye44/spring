package kr.or.ddit.command;


import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.dto.MemberVO;

public class MemberModifyCommand {
	private String id;				// 아이디
	private String pwd;				// 패스워드
	private String name;			// 이름
	private String phone;			// 전화번호
	private String email;			// 이메일
	private MultipartFile picture;			// 사진파일 경로/파일명
	private String oldPicture;		// 이전 사진 파일명
	private String uploadPicture;	// 변경된 사진 파일명
	
	public MemberVO toParseMember() {
		MemberVO member = new MemberVO();
		member.setId(this.id);
		member.setPwd(this.pwd);
		member.setName(this.name);
		member.setPhone(this.phone);
		member.setEmail(this.email);
		
		
		return member;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public MultipartFile getPicture() {
		return picture;
	}
	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}
	public String getOldPicture() {
		return oldPicture;
	}
	public void setOldPicture(String oldPicture) {
		this.oldPicture = oldPicture;
	}
	public String getUploadPicture() {
		return uploadPicture;
	}
	public void setUploadPicture(String uploadPicture) {
		this.uploadPicture = uploadPicture;
	}
}
