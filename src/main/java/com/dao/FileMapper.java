package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pojo.CdFile;
import com.pojo.Folder;

@Mapper
public interface FileMapper {
	public List<CdFile> showFileList(int parentId);//显示文件列表信息
	public String currentFolderName(int parentId);/* 当前目录名称 */
	public int folderParentId(int folderId);/* 获取该文件夹的父文件夹id */
	public String folderParentName(int parentId);/* 获取该文件夹的父文件夹名称 */
	public String parentLocalPath(int parentId);/* 获取父目录本地磁盘路径 */
	public String folderLocalPath(int folderId);/* 获取文件夹本地磁盘路径 */
	public List<CdFile> searchFiles(@Param("searchMessages")String searchMessages,@Param("parentId")int parentId);/* 用户搜索文件 */
	public List<CdFile> searchFolders(@Param("searchMessages")String searchMessages,@Param("parentId")int parentId);/* 用户搜索文件文件夹 */
	public void addFile(CdFile newFile);/* 创新新文件夹，上传文件，返回文件信息列表 */
	public List<Folder> currentFolderList(int currentFolderId);/* 遍历当前文件夹里的所有文件夹 */
	public void addFolder(Folder folder);/* 新建文件夹 */
	public void alterFileStatus(double fileId);/* 删除文件，修改文件status， 放入回收站 */
	public void alterFolderStatus(int folderId);/* 删除文件夹，修改文件夹status，放入回收站 */
	public List<CdFile> showRecycleBinFile();/* 获取准备删除的文件：status=0 */
	public List<Folder> showRecycleBinFolder();/* 获取准备删除的文件夹：status=0 */
	public int fileParentId(double fileId);/* 获取该文件的父目录id */
	public String fileLocalPath(double fileId);/* 获取文件本地磁盘地址 */
	public void deleteInSql(double fileId);/* 删除该文件数据库信息 */
	public void returnFileStatus(double fileId);/* 恢复文件 */
	public void deleteFolderInSql(int folderId);/* 删除文件夹数据库信息 */
	public void deleteFolderFileSql(int folderId);/* 删除该文件夹所有文件数据库信息 */
	public void returnFolderStatus(int folerId);/* 恢复文件夹 */

}
