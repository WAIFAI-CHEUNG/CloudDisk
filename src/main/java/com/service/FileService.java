package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.FileMapper;
import com.pojo.CdFile;
import com.pojo.Folder;

@Service
public class FileService {
	@Autowired
	private FileMapper fileMapper;
	
	/* 获取当前目录名称 */
	public String currentFolderName(int parentId) {
		return fileMapper.currentFolderName(parentId);
	}
	
	/* 获取当前目录的父目录id */
	public int folderParentId(int folderId) {
		return fileMapper.folderParentId(folderId);
	}
	
	/* 获取当前目录的父目录名称 */
	public String folderParentName(int parentId) {
		return fileMapper.folderParentName(parentId);
	}
	
	/* 获取父目录本地磁盘地址 */
	public String parentLocalPath(int parentId) {
		return fileMapper.parentLocalPath(parentId);
	}
	
	/* 当前目录文件列表 */
	public List<CdFile> showFileList(int parentId) {
		List<CdFile> fileList = fileMapper.showFileList(parentId);
		return fileList;
	}
	
	/* 搜索文件 */
	public List<CdFile> searchFiles(String searchMessages,int parentId){
		List<CdFile> searchFileList = fileMapper.searchFiles(searchMessages,parentId);
		return searchFileList;
	}
	
	/* 搜索文件夹 */
	public List<CdFile> searchFolders(String searchMessages,int parentId){
		List<CdFile> searchFolderList = fileMapper.searchFolders(searchMessages, parentId);
		return searchFolderList;
	}
	
	/* 新建文件夹 */
	public void newFolder(Folder newFolder) {
		fileMapper.addFolder(newFolder);
	}
	
	/* 上传文件 */
	public void addFile(CdFile newFile) {
		fileMapper.addFile(newFile);
	}
	
	/* 遍历当前文件夹里的所有文件夹 */
	public List<Folder> currentFolderList(int currentFolderId){
		List<Folder> currentFolderList = fileMapper.currentFolderList(currentFolderId);
		return currentFolderList;
	}
	
	/* 删除文件，只记录status0，放入回收站 */
	public void alterFileStatus(double fileId) {
		fileMapper.alterFileStatus(fileId);
	}
	
	/* 删除文件夹，只记录status0，放入回收站 */
	public void alterFolerStatus(int folderId) {
		fileMapper.alterFolderStatus(folderId);
	}
	
	/* 获取回收站文件 */
	public List<CdFile> showRecycleBinFile(){
		return fileMapper.showRecycleBinFile();
	}
	
	/* 获取回收站文件夹 */
	public List<Folder> showRecycleBinFolder(){
		return fileMapper.showRecycleBinFolder();
	}
	/* 获取文件父目录id */
	public int fileParentId(double fileId) {
		return fileMapper.fileParentId(fileId);
	}
	
	/* 获取文件本地磁盘地址 */
	public String fileLocalPath(double fileId) {
		return fileMapper.fileLocalPath(fileId);
	}
	
	/* 获取文件夹本地磁盘地址 */
	public String folderLocalPath(int folderId) {
		return fileMapper.folderLocalPath(folderId);
	}
	
	/* 删除该文件数据库信息 */
	public void deleteInSql(double fileId) {
		fileMapper.deleteInSql(fileId);
	}
	
	/* 恢复文件，修改Status */
	public void returnFileStatus(double fileId) {
		fileMapper.returnFileStatus(fileId);
	}
	
	/* 删除文件夹数据库信息 */
	public void deleteFolderInSql(int folderId) {
		fileMapper.deleteFolderInSql(folderId);
	}
	
	/* 删除该文件夹所有文件数据库信息 */
	public void deleteFolderFileSql(int folderId) {
		fileMapper.deleteFolderFileSql(folderId);
	}
	
	/* 恢复文件，修改Status */
	public void returnFolderStatus(int folderId) {
		fileMapper.returnFolderStatus(folderId);
	}
}
