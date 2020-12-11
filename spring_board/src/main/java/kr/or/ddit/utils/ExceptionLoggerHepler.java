package kr.or.ddit.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.dto.MemberVO;

public class ExceptionLoggerHepler {
	
	public static void write(HttpServletRequest request, Exception e, Object res) {
		
		String savePath = GetUploadPath.getUploadPath("error.log").replace("/", File.separator);
		
		String url = request.getRequestURI().replace(request.getContextPath(), "");
		
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
		
		String loginUserName = ((MemberVO)request.getSession().getAttribute("loginUser")).getName();
		
		String exceptionType = e.getClass().getName();
		
		String happenObject = res.getClass().getName();
		
		String log = "[Error : "+exceptionType + "]"
					+url+","+date+","+loginUserName+","+happenObject
					+"\n"+e.getMessage()+"\n";
		
		// 로그 파일 생성
		
		File file = new File(savePath);
		if(!file.exists()) {
			file.mkdirs();
		}
		try {
			String logFilePath = savePath+File.separator+"system_exception_log.txt";
			
			BufferedWriter out = new BufferedWriter(new FileWriter(logFilePath, true));
			
			// 로그 기록
			out.write(log);
			
			// 줄바꿈
			out.newLine();
			
			out.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
}
