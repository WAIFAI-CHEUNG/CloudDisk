package com.pojo;

import java.io.Serializable;

public class Folder implements Serializable {

	private static final long serialVersionUID = 2636367112801060878L;
	private int folderId;
	private String folderName;
	private String localPath;
	private int userId;
	private int parentId;
	private String createTime;
	private int status;
	private String mark;

	
	public int getFolderId() {
		return folderId;
	}
	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	public String getLocalPath() {
		return localPath;
	}
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
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
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Folder() {
		super();
	}
	public Folder(String folderName, String localPath, int userId, int parentId, String createTime) {
		super();
		this.folderName = folderName;
		this.localPath = localPath;
		this.userId = userId;
		this.parentId = parentId;
		this.createTime = createTime;
	}
	
	

}
