package kr.or.ddit.exception;

public class NotMultipartFormDataException extends Exception{
	
	private static final long serialVersionUID = 1305587523053704735L;

	public NotMultipartFormDataException() {
		super("multipart 형식이 아닙니다.");
	}
}
