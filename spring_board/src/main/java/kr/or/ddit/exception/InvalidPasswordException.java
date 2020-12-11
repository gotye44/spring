package kr.or.ddit.exception;

public class InvalidPasswordException extends Exception {
	
	private static final long serialVersionUID = -457805567556578064L;

	public InvalidPasswordException() {
		super("패스워드가 일치하지 않습니다");
	}
}
