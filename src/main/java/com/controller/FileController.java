package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.exception.MyException;
import com.pojo.CdFile;
import com.pojo.Folder;
import com.service.FileService;
import com.service.UserService;


@Controller
@RequestMapping("/file")
public class FileController {	
	@Autowired
	private FileService fileService;
	@Autowired
	private UserService userService;
	private File file;
	private String filePath="http://localhost:8080/files";
	private String localPathInWin="D:\\CloudDiskFile";
	
	/* 搜索文件（文件夹，文件模糊搜索） */
	@RequestMapping("/searchFiles.action")
	public String searchFiles(String searchMessages,Model model,HttpServletRequest request) throws MyException {
		if(searchMessages.equals("统一异常处理")) {
			throw new MyException("这里是统一异常测试。");
		}else {
			List<CdFile> searchFilesList = fileService.searchFiles(searchMessages,(int)request.getSession().getAttribute("currentFolder"));
			List<CdFile> searchFoldersList = fileService.searchFolders(searchMessages, (int)request.getSession().getAttribute("currentFolder"));
			model.addAttribute("fileList", searchFilesList);
			model.addAttribute("currentFolderList", searchFoldersList);
			/* 刷新右上角当前目录 */
			model.addAttribute("currentFolder", fileService.currentFolderName((int)request.getSession().getAttribute("currentFolder")));
			return "index";
		}
	}

	/* 新建文件夹 */
	@RequestMapping("/newFolder.action")
	public String newFolder(String folderName,Model model,HttpServletRequest request) {
		int userId=(int)request.getSession().getAttribute("userId");
		int parentId=(int)request.getSession().getAttribute("currentFolder");	
		Date dd=new Date();
		SimpleDateFormat created=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String createTime=created.format(dd);
		String parentLocalPath=fileService.parentLocalPath(parentId);
		String newFolderLocalPath=parentLocalPath+"\\"+folderName;
		File newFolder=new File(newFolderLocalPath);
		if(newFolder.mkdir()) {
			Folder folderCreat = new Folder(folderName,newFolderLocalPath,userId,parentId,createTime);
			fileService.newFolder(folderCreat);
		}
		return "redirect:/user/pass.action";
	}
	
	/* 上传文件 */
	@RequestMapping("/uploadFile.action")
	public String uploadFile(@RequestParam("uploadFile")MultipartFile cdFile,Model model,HttpServletRequest request) throws IllegalStateException, IOException {
		int userId=(int)request.getSession().getAttribute("userId");
		String originalFilename = cdFile.getOriginalFilename();
		int typeIndex = originalFilename.lastIndexOf(".");
		String fileType = originalFilename.substring(typeIndex+1);
		double fileSize =(double)cdFile.getSize();
		Date dd=new Date();
		SimpleDateFormat created=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat created2=new SimpleDateFormat("yyyyMMddHHmmss");
		String createTime=created.format(dd);
		Double fileId =Double.valueOf(created2.format(dd));
		int parentId=(int)request.getSession().getAttribute("currentFolder");	
		String parentLocalPath=fileService.parentLocalPath(parentId);
		String localPath=parentLocalPath+"\\"+originalFilename;
		CdFile newFile = new CdFile(fileId,originalFilename,fileType,parentId,userId,createTime,localPath,fileSize);
		String path=filePath+"/"+originalFilename;
		file=new File(parentLocalPath,originalFilename);
		cdFile.transferTo(file);
		fileService.addFile(newFile);
		return "redirect:/user/pass.action";
	}
	
	/* 下载文件 */
	@RequestMapping("/downloadFile.action")
	public ResponseEntity downloadFile(String fileName,HttpServletRequest request) throws IOException {
		String parentLocalPath = fileService.parentLocalPath((int)request.getSession().getAttribute("currentFolder"));
		file = new File(parentLocalPath,fileName);
		FileInputStream fis = new FileInputStream(file);
		byte[] body=new byte[fis.available()];
		fis.read(body);		
		//设置头信息
		HttpHeaders headers = new HttpHeaders();
		//指定文件名,指定utf8，防止中文乱码
		headers.add("Content-DisPosition", "attchement;filename=" +new String(fileName.getBytes("UTF-8"),"ISO-8859-1"));
		//封装到ResponseEntity
		ResponseEntity entity = new ResponseEntity(body,headers,HttpStatus.OK);
		return entity;
	}
	
	/* 进入目录 */
	@RequestMapping("/enterFolder.action")
	public String enterFolder(String folderId,HttpServletRequest request) {
		request.getSession().setAttribute("currentFolder", Integer.valueOf(folderId));
		return "redirect:/user/pass.action";
	}
	
	/* 返回上一层目录 */
	@RequestMapping("lastTimeFolder.action")
	public String lastTimeFolder(String lastTimeFolder,HttpServletRequest request) {
		/*currentFolder=currentFolder.parentFolderId*/
		int folderId = (int)request.getSession().getAttribute("currentFolder");
		int folderParentId = fileService.folderParentId(folderId);
		request.getSession().setAttribute("currentFolder",folderParentId);
		return "redirect:/user/pass.action";
	}
	
	/* 删除文件 ,记录status0*/
	@RequestMapping("/alterFileStatus.action")
	public String alterFileStatus(double fileId) {
		fileService.alterFileStatus(fileId);
		return "redirect:/user/pass.action";
	}
	
	/* 删除文件夹 ,记录status0*/
	@RequestMapping("/alterFolderStatus.action")
	public String alterFolderStatus(int folderId) {
		System.out.println(folderId);
		fileService.alterFolerStatus(folderId);
		return "redirect:/user/pass.action";
	}
	
	/* 回收站永久删除文件 */
	@RequestMapping("/deleteFile.action")
	public String deleteFile(double fileId,Model model) {
		File deleteFile = new File(fileService.fileLocalPath(fileId));
		if(!(deleteFile.isDirectory())) {
			deleteFile.delete();
		}
		fileService.deleteInSql(fileId);
		return "redirect:/user/recycleWeb.action";
	}
	
	/* 回收站恢复文件，修改Status */
	@RequestMapping("/ruturnFileStatus.action")
	public String ruturnFileStatus(double fileId) {
		fileService.returnFileStatus(fileId);
		return "redirect:/user/recycleWeb.action";
	}
	
	
	/* 回收站永久删除文件夹 */
	@RequestMapping("/deleteFolder.action")
	public String deleteFolder(int folderId) {
		/* 获取文件夹本地磁盘地址，调用删除文件夹方法，本地递归删除文件 */
		String folderLocalPath = fileService.folderLocalPath(folderId);
		boolean deleteStatus = deleteFolder(folderLocalPath);
		if(deleteStatus) {
	        fileService.deleteFolderInSql(folderId);
	        fileService.deleteFolderFileSql(folderId);
			
		}
		return "redirect:/user/recycleWeb.action";

	}
	public static boolean deleteFolder(String url) {
        File file = new File(url);
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            file.delete();
            return true;
        } else {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                String root = files[i].getAbsolutePath();//得到子文件或文件夹的绝对路径
                deleteFolder(root);
            }
            file.delete();
            return true;
        }
    }
	
	/* 回收站恢复文件夹，修改Status */
	@RequestMapping("/ruturnFolderStatus.action")
	public String ruturnFolderStatus(int folderId) {
		/* 修改文件夹Status */
		fileService.returnFolderStatus(folderId);
		return "redirect:/user/recycleWeb.action";
	}
}
