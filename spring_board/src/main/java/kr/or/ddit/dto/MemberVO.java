package kr.or.ddit.dto;

import java.util.Date;

public class MemberVO {
	private String id       ; //아이디
	private String pwd      ; //패스워드
	private String email    ; //이메일
	private String picture  ; //사진파일 경로/파일명
	private int    enabled  ; //사용 여부
	private Date   regDate  ; //등록일
	private String phone    ; //전화번호
	private String name     ; //이름
	private String register ; //등록자
	private String address  ; //주소
	private String authority; //권한
	
	public MemberVO() {}
	
	public MemberVO(String id, String pwd, String email, String phone, String name) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.phone = phone;
		this.name = name;
	}
	public MemberVO(String id, String pwd, String email, String picture, int enabled, Date regDate, String phone,
			String name, String register, String address, String authority) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.picture = picture;
		this.enabled = enabled;
		this.regDate = regDate;
		this.phone = phone;
		this.name = name;
		this.register = register;
		this.address = address;
		this.authority = authority;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pwd=" + pwd + ", email=" + email + ", picture=" + picture + ", enabled="
				+ enabled + ", regdate=" + regDate + ", phone=" + phone + ", name=" + name + ", register=" + register
				+ ", address=" + address + ", authority=" + authority + "]";
	}
	
	
}
