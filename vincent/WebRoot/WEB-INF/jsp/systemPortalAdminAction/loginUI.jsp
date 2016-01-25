<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<title>登录界面</title>
</head>

<body>
	<div class="content" style="background-color:#fff">
		<div style="height:200px"></div>
		<div style="margin:0 auto; width:350px; height:200px;  border:solid 1px #999">
			<div style="height:30px; line-height:30px; text-align:center; background-color:#999; font-size:18px; color:#fff">用户登录</div>
			<form action="<%=rootPath %>/system_login.action" method="post">
				<table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td colspan="2" style="height:10px"><font color="red"><s:fielderror/></font></td>
					</tr>
					<tr>
						<td style=" height:30px; line-height:30px; width:100px; text-align:right; padding-right:5px">用户名:</td>
						<td style="padding-left:10px"><input type="text" name="userName" /></td>
					</tr>
					<tr>
						<td style="height:30px; line-height:30px; width:100px; text-align:right; padding-right:5px">密码:</td>
						<td style="padding-left:10px"><input type="password" name="password" /></td>
					</tr>
					<tr>
						<td style="height:30px; line-height:30px; width:100px; text-align:right; padding-right:5px">角色:</td>
						<td style="padding-left:10px">
							<input type="radio" name="role" value="1" />团队&nbsp;&nbsp;
							<input type="radio" name="role" value="0" checked="checked" />学校&nbsp;&nbsp;
							<input type="radio" name="role" value="2" />评审&nbsp;&nbsp;
							<input type="radio" name="role" value="3" />root
						</td>
					</tr>
					<tr>
						<td colspan="2" style="height:50px; line-height:50px; text-align:center">
							<input type="submit" value="登录" />&nbsp;&nbsp;
							<input type="reset" value="重置" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>