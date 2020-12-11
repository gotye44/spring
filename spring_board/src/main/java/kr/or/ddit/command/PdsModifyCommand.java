package kr.or.ddit.command;

import kr.or.ddit.dto.PdsVO;

public class PdsModifyCommand extends PdsRegistCommand{

	private int pno;
	private int[] deleteFile;
	
	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public void setDeleteFile(int[] deleteFile) {
		this.deleteFile = deleteFile;
	}
	
	public int[] getDeleteFile() {
		return deleteFile;
	}
	
	@Override
	public PdsVO toPdsVO() {
		PdsVO pds = super.toPdsVO();
		pds.setPno(this.pno);
		return pds;
	}

}
