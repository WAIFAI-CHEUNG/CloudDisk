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
 Language: 
<a href="<%=basePath%>user/pass.action?locale=zh_CN"><spring:message
		code="language.cn" /></a> -
<a href="<%=basePath%>user/pass.action?locale=en_US"><spring:message
		code="language.en" /></a>
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
	var res = window.confirm("确定要删除该文件吗？");
	if (res) {
		//alert("删除了:" + fileId + "文件");
		window.location.href ="<%=basePath%>file/alterFileStatus.action?fileId="+fileId;
	}
	return res;
}

function deleteFolderFun(folderId) {
	var res = window.confirm("确定要删除该文件夹吗？");
	if (res) {
		//alert("删除了:" + fileId + "文件");
		window.location.href ="<%=basePath%>file/alterFolderStatus.action?fileId="+folderId;
	}
	return res;
}

function ifFather(lastTimeFolder) {
	if(lastTimeFolder==404){
		alert("我是有底线的，您已到达顶层目录！")
	}else{
		window.location.href="<%=basePath%>file/lastTimeFolder.action?lastTimeFolder="+lastTimeFolder
	}
}

</script>
</head>
<body>
	<div id="index">
		<div class="cloud">
			<div class="banner">
				<div class="bannerLeft">
					<span class="logoBg"></span> <span
						style="font-size: 22px; font-weight: bold"><spring:message
							code="myCloudDisk" /></span>
				</div>
				<div class="bannerCenter">
					<ul>
						<li class="active"><a href="<%=basePath%>user/pass.action"><spring:message
									code="cloudDisk" /></a></li>
						<li><a href="index.do"><spring:message code="share" /></a></li>
						<li><a href="<%=basePath%>user/fileuploadWeb.action"><spring:message
									code="uploading" /></a></li>
						<li><a href="<%=basePath%>user/fileuploadWeb.action"><spring:message
									code="downloading" /></a></li>
					</ul>
				</div>
				<div class="bannerRight">
					<span class="person"><img
						src="<%=request.getContextPath()%>/images/person.jpg"></span> <span>${userName }</span>
					<span style="margin-left: 10px; margin-right: 10px;">丨</span> <span><spring:message
							code="currentDirectory" />:${currentFolder }</span> <span
						style="margin-left: 10px; margin-right: 10px;">丨</span> <span><a
						href=""><spring:message code="clientDownload" /></a></span> <span
						class="center"><spring:message code="vip" /></span>
				</div>
			</div>
			<div class="content">
				<div class="contentLeft" id="contentLeft">
					<ul>
						<%-- 						<c:forEach var="rf" items="${sessionScope.sysFolderList }">
							<li
								<c:if test="${sessionScope.currentFolder.hdfsPath==rf.hdfsPath }">class="active"</c:if>>
								<a href="index.do?folderId=${rf.folderId}"> <c:out
										value="${rf.folderName }"></c:out></a>
							</li>
						</c:forEach> --%>
						<li><span class="contentBg bg2"></span><a href=""><spring:message
									code="myShare" /></a></li>
						<li><span class="contentBg bg3"></span><a
							href="<%=basePath%>user/recycleWeb.action"><spring:message
									code="recycleBin" /></a></li>
						<li><button onclick="ifFather(${lastTimeFolder})">返回上一层目录</button></li>
					</ul>
				</div>
				<div class="contentRight" id="contentRight">
					<div class="fixedBtn" id="fixedBtn" onclick="showFullPage()">显示全部</div>
					<div class="fixedBtn" id="hideBtn" onclick="hideFullPage()"
						style="display: none;">显示目录</div>
					<div class="operat">
						<div class="operatLeft">
							<div onclick="showFileUpload()">
								<i class="operatLeftBg bg1"></i> <a href="javascript:void(0)"><spring:message
										code="upload" /></a>
							</div>
							<div>
								<i class="operatLeftBg bg2"></i><a href="javascript:void(0)"
									onclick="$('#createFileWindow').window('open')"><spring:message
										code="newFolder" /></a>
							</div>
							<div>
								<i class="operatLeftBg bg3"></i>
								<spring:message code="offlineDownload" />
							</div>
							<div>
								<i class="operatLeftBg bg4"></i>
								<spring:message code="myDevice" />
							</div>
						</div>
						<form action="<%=basePath%>file/searchFiles.action" method="post">
							<div class="operatRight">
								<input type="text" name="searchMessages"
									placeholder="<spring:message code="searchYourFiles"/>" /> <input
									type="submit" value="搜索" class=""> <span
									class="searchBg"></span> <span class="sort"></span> <span
									class="sortTwo"></span>

							</div>
						</form>
						<div style="clear: both;"></div>
					</div>
					<div class="file">
						<table rules="rows" frame="below" bordercolor="#F2F6FD">
							<tr>
								<td colspan="2"><spring:message code="allDocuments" /></td>
								<td colspan="2" align="right"><spring:message
										code="allLoaded" />${fn:length(sessionScope.folderList)+fn:length(sessionScope.fileList) }</td>
							</tr>
							<tr>
								<td colspan="2" width="200px"><input type="checkbox"
									disabled="disabled" style="margin-right: 10px" /> <spring:message
										code="fileName" /></td>
								<td><spring:message code="type" /></td>
								<td><spring:message code="size" /></td>
								<td><spring:message code="modificationDate" /></td>
							</tr>
							<c:forEach var="rf" items="${currentFolderList }">
								<tr>
									<td><input type="checkbox" disabled="disabled"
										style="margin-right: 10px; margin-right: 50px" /> <a
										href="<%=basePath%>file/enterFolder.action?folderId=${rf.folderId}"><span
											class="folder"></span> <c:out value="${rf.folderName }"></c:out></a></td>
									<td class="hideArea"><a
										href="<%=basePath%>file/alterFolderStatus.action?folderId=${rf.folderId}"><span
											class="huishouq"></span></a></td>
									<td>目录</td>
									<td>-</td>
									<td><c:out value="${rf.createTime }"></c:out></td>
								</tr>
							</c:forEach>
							<c:forEach var="rf" items="${fileList}">
								<tr>
									<td><input type="checkbox" value="${rf.fileId}"
										style="margin-right: 10px; margin-right: 50px" /> <span
										class="myfile"></span> <c:out value="${rf.fileName}"></c:out></td>
									<td class="hideArea"><span class="huishouq"
										onclick="deleteFileFun(${rf.fileId})"></span> <a
										href="<%=basePath%>file/downloadFile.action?type=download&fileName=${rf.fileName}"><span
											class="download"></span></a></td>
									<td><c:out value="${rf.fileType}"></c:out></td>
									<td><fmt:formatNumber value="${rf.fileSize/1024}"
											pattern="0.00" /> kb</td>
									<td><c:out value="${rf.createTime}"></c:out></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="createFileWindow" class="easyui-window" title="Modal Window"
		data-options="modal:true,closed:true,iconCls:'icon-save'"
		style="width: 300px; height: 120px; padding: 10px;">
		<form action="<%=basePath%>file/newFolder.action" method="post">
			<table>
				<tr>
					<td><spring:message code="enterFileName" />:</td>
					<td><input id="folderName" name="folderName" type="text" /></td>
				</tr>
				<tr>
					<td></td>
					<td><button type="submit">
							<spring:message code="submit" />
						</button></td>
				</tr>
			</table>
		</form>
	</div>
	<form id="uploadForm" action="<%=basePath%>file/uploadFile.action"
		method="post" enctype="multipart/form-data">
		<input type="file" name="uploadFile" id="uploadFile"
			style="visibility: hidden; position: absolute; top: 0px; width: 0px" />
	</form>
</body>
</html>