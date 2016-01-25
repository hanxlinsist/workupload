<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<title>评审资料完善界面</title>
</head>

<body>
	<div class="content">
		<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
		<p style="text-align:center"><img src="<%=rootPath %>/images/teamUserInfoCompliment.jpg" /></p>

		<s:form action="opus_complementInformation" method="post">
			<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">姓名</td>
					<td><s:textfield name="name" style="width:300px; height:22px" ></s:textfield></td>
				</tr>
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">工号</td>
					<td><s:textfield name="jobNumber" style="width:300px; height:22px" ></s:textfield></td>
				</tr>
				<tr>
					<td colspan="2" style="height:50px; line-height:50px; padding-left:400px">
						<s:submit value="保存" cssClass="input-button"></s:submit>
					</td>
				</tr>
			</table>
		</s:form>
		
		<%@ include file="/WEB-INF/jsp/public/footer.jspf" %>
   </div>
	
</body>
</html>