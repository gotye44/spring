package kr.or.ddit.exception;

public class NotFoundIDException extends Exception {
	private static final long serialVersionUID = -5698399070421276564L;

	public NotFoundIDException() {
		super("아이디를 찾을 수가 없습니다.");
	}
}
