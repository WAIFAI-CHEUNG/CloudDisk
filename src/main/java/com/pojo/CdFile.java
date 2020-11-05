package com.pojo;

import java.io.Serializable;

public class CdFile implements Serializable {

	private static final long serialVersionUID = -6446936696907562299L;

	private Double fileId;
	private String fileName;
	private String fileType;
	private int folderId;
	private int userId;
	private String createTime;
	private int status;
	private String localPath;
	private Double fileSize;
	private String mark;
	public Double getFileId() {
		return fileId;
	}
	public void setFileId(Double fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public int getFolderId() {
		return folderId;
	}
	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLocalPath() {
		return localPath;
	}
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	public Double getFileSize() {
		return fileSize;
	}
	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public CdFile() {
		super();
	}
	public CdFile(Double fileId, String fileName, String fileType, int folderId, int userId, String createTime,
			String localPath, Double fileSize) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.fileType = fileType;
		this.folderId = folderId;
		this.userId = userId;
		this.createTime = createTime;
		this.localPath = localPath;
		this.fileSize = fileSize;
	}
	public CdFile(String fileName, int userId) {
		super();
		this.fileName = fileName;
		this.userId = userId;
	}



}
