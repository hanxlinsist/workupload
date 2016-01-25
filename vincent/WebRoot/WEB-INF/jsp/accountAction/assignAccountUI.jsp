<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<title>分配账户界面</title>
	<script type="text/javascript">
		function validate() {
			var accountNumber = $("input[name='accountNumber']").val();
			var reg = /^\d+$/;
			if(accountNumber.match(reg) && accountNumber > 0) {
				return true;
			} else {
				alert("帐户数量格式不对，请认真核实数据！！！");
				return false;
			}
		}
	</script>
</head>
<body>
	<div class="content" style="background-color:#fff">
		<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
		<p style="text-align:center"><img src="<%=rootPath %>/images/assignAccountUI.png" /></p>
		<p style="text-align:center;color:red">注意:分配的账户数量必须是大于0的整数</p>
		<span style="font-weight: bold;font-size: 15px; color: red; text-align: center;"><s:fielderror name="errMsg"/></span>
		<s:form action="account_assignAccount" method="post" onsubmit="return validate()">
			<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">分配的帐户数量</td>
					<td><s:textfield name="accountNumber"></s:textfield></td>
				</tr>
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">所属学院</td>
					<td>
						<s:select name="academy" list="#academyList" listKey="name" listValue="name"></s:select>
					</td>
				</tr>
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">所属角色</td>
					<td>
						<s:select name="role" list="#{1:'团队',2:'评审'}"></s:select>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="height:50px; line-height:50px; padding-left:400px">
						<s:submit value="分配账号" cssClass="input-button"></s:submit>
					</td>
				</tr>
			</table>
		</s:form>
		<%@ include file="/WEB-INF/jsp/public/footer.jspf" %>
	</div>
</body>
</html>
