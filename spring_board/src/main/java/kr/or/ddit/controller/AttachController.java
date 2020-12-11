package kr.or.ddit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.dao.AttachDAO;
import kr.or.ddit.dto.AttachVO;

@Controller
@RequestMapping("/attach")
public class AttachController {
	
	@Autowired
	private AttachDAO attachDAO;
	public void setAttachDAO(AttachDAO attachDAO) {
		attachDAO = this.attachDAO;
	}
	
	@Resource(name="fileUploadPath")
	private String fileUploadPath;
	
	
	@RequestMapping("/getFile")
	public ResponseEntity<byte[]> getFile(int ano) throws Exception{
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		AttachVO attach = attachDAO.selectAttachByAno(ano);
		
		String fileUploadPath = this.fileUploadPath;
		String fileName = attach.getFileName();
		
		try {
			in = new FileInputStream(fileUploadPath + File.separator + fileName);
			
			fileName = fileName.substring(fileName.indexOf("$$") + 2);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition", "attachment;filename=\""+new String(fileName.getBytes("utf-8"), "ISO-8859-1") + "\"");
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			in.close();
		}
		return entity;
	}
	
}
