package com.kh.banzi.review.model.vo;

public class Attachment {
	private int fileNo;
	private String fileOriginName;
	private String fileChangeName;
	private String filePath;
	private int fileLevel;
	private String fileStatus;
	private int parentBoardNo;
	private int parentBoardType;

	public Attachment() {}

	public Attachment(int fileNo, int parentBoardNo, String fileChangeName, String filePath, int fileLevel) {
		super();
		this.fileNo = fileNo;
		this.parentBoardNo = parentBoardNo;
		this.fileChangeName = fileChangeName;
		this.filePath = filePath;
		this.fileLevel = fileLevel;
	}

	public Attachment(int fileNo, int parentBoardNo, String fileOriginName, String fileChangeName, String filePath,
			int fileLevel, String fileStatus, int parentBoardType) {
		super();
		this.fileNo = fileNo;
		this.parentBoardNo = parentBoardNo;
		this.fileOriginName = fileOriginName;
		this.fileChangeName = fileChangeName;
		this.filePath = filePath;
		this.fileLevel = fileLevel;
		this.fileStatus = fileStatus;
		this.parentBoardType = parentBoardType;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
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
	
	

	public int getParentBoardType() {
		return parentBoardType;
	}


	public void setParentBoardType(int parentBoardType) {
		this.parentBoardType = parentBoardType;
	}


	@Override
	public String toString() {
		return "Attachment [fileNo=" + fileNo + ", parentBoardNo=" + parentBoardNo + ", fileOriginName="
				+ fileOriginName + ", fileChangeName=" + fileChangeName + ", filePath=" + filePath + ", fileLevel="
				+ fileLevel + ", fileStatus=" + fileStatus + ", parentBoardType=" + parentBoardType + "]";
	}


	

}