<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<title>修改密码界面</title>
	<script type="text/javascript">
		function checkPwd() {
			var passwords = $(":password");
			var oldpwd = passwords[0].value;
			var newpwd = passwords[1].value;
			var againpwd = passwords[2].value;
			
			if(oldpwd == '' || oldpwd == null || newpwd == '' || newpwd == null || againpwd == '' || againpwd == null) {
				alert('密码不能为空');
				return false;
			}
			
			if(newpwd != againpwd) {
				alert('新密码两次输入不一致');
				return false;
			}
			return true;
		}
	</script>
</head>

<body>
	<div class="content" style="background-color:#fff">
		<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
		<p style="text-align:center"><img src="<%=rootPath %>/images/modifyAccountPwd.png" /></p>
		<p style="text-align:center;color:red">注意:如果您想更改密码,请先输入旧密码,然后再输入新密码及确认密码。新密码和确认密码必须一致。</p>
		<span style="font-weight: bold;font-size: 15px; color: red; text-align: center;"><s:fielderror name="errMsg"/></span>
		<s:form action="modiftPwd_modifyPwd" method="post" onsubmit="return checkPwd()">
			<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">旧密码</td>
					<td><s:password name="password" style="width:300px; height:22px"></s:password></td>
				</tr>
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">新密码</td>
					<td><s:password name="newPassword" style="width:300px; height:22px"></s:password></td>
				</tr>
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">确认密码</td>
					<td><s:password style="width:300px; height:22px"></s:password></td>
				</tr>
				<tr>
					<td colspan="2" style="height:50px; line-height:50px; padding-left:400px">
						<s:submit value="修改" cssClass="input-button"></s:submit>&nbsp;&nbsp;
						<input type="reset" value="重置" class="input-button" />&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</s:form>
		<%@ include file="/WEB-INF/jsp/public/footer.jspf" %>
	</div>
</body>
</html>