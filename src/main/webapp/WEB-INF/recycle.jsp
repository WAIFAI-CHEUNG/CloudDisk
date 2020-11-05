<%@ page import="com.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>云端网盘</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/index.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/jquery/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/jquery/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/jquery/demo.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/index.js"></script>
<script type="text/javascript">
function deleteFileFun(fileId) {
	var res = window.confirm("确定要永久该文件吗（不可恢复）？");
	if (res) {
		//alert("删除了:" + fileId + "文件");
		window.location.href ="<%=basePath%>file/deleteFile.action?fileId="+fileId;
	}
	return res;
}
</script>
</head>
<body>
	<div id="index">
		<div class="cloud">
			<div class="banner">
				<div class="bannerLeft">
					<span class="logoBg"></span> <span
						style="font-size: 22px; font-weight: bold">云端网盘</span>
				</div>
				<div class="bannerCenter">
					<ul>
						<li class="active"><a href="<%=basePath%>user/pass.action"><spring:message
									code="cloudDisk" /></a></li>
						<li><a href="index.do">分享</a></li>
						<li><a href="<%=basePath%>user/fileuploadWeb.action">正在上传</a></li>
						<li><a href="<%=basePath%>user/filedownloadWeb.action">正在下载</a></li>
					</ul>
				</div>
				<div class="bannerRight">
					<span class="person"><img
						src="<%=request.getContextPath()%>/images/person.jpg"></span> <span>${userName }</span>
					<span style="margin-left: 10px; margin-right: 10px;">丨</span> <span>当前目录:回收站</span>
					<span style="margin-left: 10px; margin-right: 10px;">丨</span> <span><a
						href="">客户端下载</a></span> <span class="center">会员中心</span>
				</div>
			</div>
			<div class="content">
				<div class="contentLeft" id="contentLeft">
					<ul>
						<li><a href="<%=basePath%>user/pass.action">返回网盘主页</a></li>
					</ul>
				</div>
				<div class="contentRight" id="contentRight" style="width: 95%">
					<div class="fixedBtn" id="fixedBtn" onclick="showFullPage()">显示全部</div>
					<div class="fixedBtn" id="hideBtn" onclick="hideFullPage()"
						style="display: none;">显示目录</div>
					<div class="operat">
						<div class="operatLeft">
						</div>
						<div style="clear: both;"></div>
					</div>
					<div class="file">
						<table rules="rows" frame="below" bordercolor="#F2F6FD">
							<tr>
								<td colspan="2">全部文件</td>
								<td colspan="2" align="right">已加载全部</td>
							</tr>
							<tr>
								<td colspan="2" width="200px"><input type="checkbox"
									disabled="disabled" style="margin-right: 10px" />文件名</td>
								<td>类型</td>
								<td>大小</td>
								<td>创建日期</td>
							</tr>
							<c:forEach var="rf" items="${recycleBinFolderList }">
								<tr>
									<td> <input type="checkbox" value="${rf.folderId }"
										style="margin-right: 10px; margin-right: 50px" /> <span
										class="folder"></span> <c:out value="${rf.folderName}"></c:out></td>
									<td class="hideArea"><a
										href="<%=basePath%>file/deleteFolder.action?folderId=${rf.folderId}"><span
											class="huishouq"></span></a></td>
										
									<td>目录</td>
									<td>-</td>
									<td><c:out value="${rf.createTime }"></c:out></td>
									<td><a
										href="<%=basePath%>file/ruturnFolderStatus.action?folderId=${rf.folderId}"><img
											src="<%=request.getContextPath()%>/images/return.png"></a></td>
								</tr>
							</c:forEach>
							<c:forEach var="rf" items="${recycleBinFileList}">
								<tr>
									<td><input type="checkbox" value="${rf.fileId }"
										style="margin-right: 10px; margin-right: 50px" /> <span
										class="myfile"></span> <c:out value="${rf.fileName}"></c:out></td>
									<td class="hideArea"><span class="huishouq"
										onclick="deleteFileFun(${rf.fileId})"></span> <a
										href="<%=basePath%>file/downloadFile.action?type=download&fileName=${rf.fileName}"><span
											class="contentBg bg3"></span></a></td>
									<td><c:out value="${rf.fileType}"></c:out></td>
									<td><fmt:formatNumber value="${rf.fileSize/1024}"
											pattern="0.00" /> kb</td>
									<td><c:out value="${rf.createTime}"></c:out></td>
									<td><a
										href="<%=basePath%>file/ruturnFileStatus.action?fileId=${rf.fileId}"><img
											src="<%=request.getContextPath()%>/images/return.png"></a></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>