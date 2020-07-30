package com.kh.banzi.common;

public class Attachment {
	
	   private int fileNo;
	   private int parentBoardType;
	   private int parentBoardNo;
	   private String fileOriginName;
	   private String fileChangeName;
	   private String filePath;
	   private int fileLevel;
	   private String fileStatus;
	   
	   public Attachment() {
		
	}

	public Attachment(int fileNo, int parentBoardType, int parentBoardNo, String fileOriginName, String fileChangeName,
			String filePath, int fileLevel, String fileStatus) {
		super();
		this.fileNo = fileNo;
		this.parentBoardType = parentBoardType;
		this.parentBoardNo = parentBoardNo;
		this.fileOriginName = fileOriginName;
		this.fileChangeName = fileChangeName;
		this.filePath = filePath;
		this.fileLevel = fileLevel;
		this.fileStatus = fileStatus;
	}
	
	

	// 게시글에 포함된 이미지 조회용 매개변수 있는 생성자
	public Attachment(int fileNo, String fileChangeName, String filePath, int fileLevel) {
		super();
		this.fileNo = fileNo;
		this.fileChangeName = fileChangeName;
		this.filePath = filePath;
		this.fileLevel = fileLevel;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public int getParentBoardType() {
		return parentBoardType;
	}

	public void setParentBoardType(int parentBoardType) {
		this.parentBoardType = parentBoardType;
	}

	public int getParentBoardNo() {
		return parentBoardNo;
	}

	public void setParentBoardNo(int parentBoardNo) {
		this.parentBoardNo = parentBoardNo;
	}

	public String getFileOriginName() {
		return fileOriginName;
	}

	public void setFileOriginName(String fileOriginName) {
		this.fileOriginName = fileOriginName;
	}

	public String getFileChangeName() {
		return fileChangeName;
	}

	public void setFileChangeName(String fileChangeName) {
		this.fileChangeName = fileChangeName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}

	public String getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}

	@Override
	public String toString() {
		return "Attachment [fileNo=" + fileNo + ", parentBoardType=" + parentBoardType + ", parentBoardNo="
				+ parentBoardNo + ", fileOriginName=" + fileOriginName + ", fileChangeName=" + fileChangeName
				+ ", filePath=" + filePath + ", fileLevel=" + fileLevel + ", fileStatus=" + fileStatus + "]";
	}
	   

	
}
