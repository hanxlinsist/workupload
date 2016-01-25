<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<title>评审_功能列表</title>
</head>

<body>
	<div class="content">
		<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
		<table cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td align="center">
					<s:a action="modiftPwd_modifyPwdUI"><img src="<%=rootPath %>/images/modifyPassword.png" border="0" /></s:a>
				</td>
				<td align="center">
					<s:a action="opus_complementInformationUI"><img src="<%=rootPath %>/images/complementInfo.jpg" border="0" /></s:a>
				</td>
				<td align="center">
					<s:a action="opus_listUI"><img src="<%=rootPath %>/images/opusReviewer.jpg" border="0" /></s:a>
				</td>
			</tr>
		</table>
		<div style=" height:300px"></div>
	</div>
</body>
</html>

