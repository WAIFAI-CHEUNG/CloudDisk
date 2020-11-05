package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.CdFile;
import com.pojo.Folder;
import com.pojo.User;
import com.service.FileService;
import com.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private FileService fileService;

	/* 登录密码校验,登录拦截存值,处理结果并转发请求 */
	@RequestMapping("/loginCheck.action")
	public String LoginCheck(User user, HttpServletRequest request) {
		String userName = user.getUserName();
		String password = user.getPassword();
		int loginCheck = userService.loginCheck(userName, password);
		if (loginCheck == 1) {
			/* 获取用户id存session，并且记录登陆成功session */
			int userId = userService.getUserId(userName);
			request.getSession().setAttribute("userId", userId);
			request.getSession().setAttribute("userName", userName);
			request.getSession().setAttribute("loginStatus", "true");
			/* 主页右上角初始化当前目录（首次登路初始化为根目录） */
			request.getSession().setAttribute("currentFolder", 1001);
			/* 初始化上级目录，避免返回目录session没有变化 */
			request.getSession().setAttribute("lastTimeFolder", 1001);
			return "redirect:pass.action";
		}
		return "loginFail";
	}

	/* 跳转主页，获取数据,返回文件列表信息（文件夹，文件） */
	@RequestMapping("/pass.action")
	public String pass(Model model,HttpServletRequest request) {
		/* 获取文件列表，并返回数据 */
		int parentId=(int)request.getSession().getAttribute("currentFolder");
		/* 当前目录名称 */
		String currentFolderName = fileService.currentFolderName(parentId);
		/* 返回当前目录所有文件 */
		model.addAttribute("fileList", fileService.showFileList(parentId));
		/* 返回当前目录所有文件夹 */
		model.addAttribute("currentFolderList", fileService.currentFolderList(parentId));
		model.addAttribute("userName", request.getSession().getAttribute("userName"));
		model.addAttribute("currentFolder", currentFolderName);
		/* 该目录上一级目录=foler.parentId */
		model.addAttribute("lastTimeFolder",fileService.folderParentId(parentId));	
		return "index";
	}

	/* 注册 */
	@RequestMapping("/register.action")
	public String userRegister(User user) {
		userService.UserRegister(user);
		return "registerSuccess";
	}
	
	/* 跳转回收站页面 */
	@RequestMapping("/recycleWeb.action")
	public String recycleWeb(Model model) {
		List<CdFile> recycleBinFileList = fileService.showRecycleBinFile();
		model.addAttribute("recycleBinFileList", recycleBinFileList);
		List<Folder> recycleBinFolderList = fileService.showRecycleBinFolder();
		model.addAttribute("recycleBinFolderList", recycleBinFolderList);
		return "recycle";
	}
	/* 跳转正在上传页面 */
	@RequestMapping("/fileuploadWeb.action")
	public String fileuploadWeb() {
		return "fileupload";
	}
	/* 跳转正在下载页面 */
	@RequestMapping("/filedwonloadWeb.action")
	public String filedownloadWeb() {
		return "filedownload";
	}
	

}
