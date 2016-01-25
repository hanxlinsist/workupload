<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<title>团队系统管理</title>
</head>

<body>
	<div class="content" style="background-color:#fff">
		<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
		<p style="text-align:center"><img src="<%=rootPath %>/images/assignAccountUI.png" /></p>
		<p style="text-align:center;color:red">注意:　系统关闭以后，系统内所有账号将不能登录</p>
		<s:form action="account_modify%{role == 1 ? 'Team' : 'Reviewer'}SystemStatus" method="post" onsubmit="return validate()">
			<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">
						<s:if test="%{role == 1}">
							团队系统： <s:select name="teamSystemStatus" list="#{0:'关闭系统',1:'开放系统'}"></s:select> <br/>
						</s:if>
						<s:else>
							评审系统：<s:select name="reviewerSystemStatus" list="#{0:'关闭系统',1:'开放系统'}"></s:select> <br/>
						</s:else>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="height:50px; line-height:50px; padding-left:400px">
						<s:submit value="提交" cssClass="input-button"></s:submit>
					</td>
				</tr>
			</table>
		</s:form>
		<%@ include file="/WEB-INF/jsp/public/footer.jspf" %>
	</div>
</body>
</html>