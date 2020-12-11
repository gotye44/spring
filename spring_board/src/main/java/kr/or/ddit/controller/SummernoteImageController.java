package kr.or.ddit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class SummernoteImageController {
	
	@Resource(name="imgPath")
	private String imgPath;
	
	@RequestMapping("/uploadImg")
	public ResponseEntity<String> uploadImg(HttpServletRequest request, MultipartFile file) throws Exception{
		
		ResponseEntity<String> entity = null;
		
		int fileSize = 5*1024*1024;
		
		if(file.getSize() > fileSize) {
			return new ResponseEntity<String>("용량 초과입니다", HttpStatus.BAD_REQUEST);
		}
		
		String savePath = imgPath.replace("/", File.separator);
		String fileName = UUID.randomUUID().toString().replace("-", "");
		String fileFormat = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		fileName = fileName + fileFormat;
		
		File saveFile = new File(savePath, fileName);
		
		if(!saveFile.exists()) {
			saveFile.mkdirs();
		}
		
		try {
			file.transferTo(saveFile);
			entity = new ResponseEntity<>(request.getContextPath()+"/getImg.do?fileName="+fileName, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	@RequestMapping("/getImg")
	public ResponseEntity<byte[]> getImg(String fileName)throws IOException {
		
		ResponseEntity<byte[]> entity = null;
		
		String savePath = imgPath.replace("/", File.separator);
		File sendFile = new File(savePath, fileName);
		FileInputStream in = null;
		
		try {
			in = new FileInputStream(sendFile);
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			in.close();
		}
		return entity;
	}
	
	@RequestMapping("/deleteImg")
	public ResponseEntity<String> deleteImg(@RequestBody Map<String, String> data) throws Exception{
		
		ResponseEntity<String> result = null;
		String fileName = data.get("fileName");
		
		String savePath = imgPath.replace("/", File.separator);
		File target = new File(savePath, fileName);
		
		if(!target.exists()) {
			result = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} else {
			try {
				target.delete();
				result = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			} catch (Exception e) {
				result = new ResponseEntity<String>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		return result;
	}
}
