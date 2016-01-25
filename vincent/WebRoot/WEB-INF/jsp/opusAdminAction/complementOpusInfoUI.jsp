<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<title>团队_作品上传界面</title>
	<script type="text/javascript">
		function validate() {
			var opusSummary = $("#opusSummary").val();
			if (opusSummary.length < 400) {
				return true;
			}
			alert("作品简介的字数不对");
			return false;
		}
	</script>
</head>
<body>
	<div class="content">
		<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
		<p style="text-align:center"><img src="<%=rootPath %>/images/opusAdmin.jpg" /></p>

		<s:form action="opus_complementOpusInfo" method="post" onsubmit="return validate()">
			<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">作品类别</td>
					<td>
						<s:select name="opusCategory" list="#worktypes" listKey="name" listValue="name"></s:select>
					</td>
				</tr>
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">作品名称</td>
					<td><s:textfield name="opusName" cssStyle="width:300px; height:22px"></s:textfield></td>
				</tr>
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">作品简介</td>
					<td>
						<s:textarea name="opusSummary" cssStyle="width:500px; height:150px" id="opusSummary"></s:textarea>
						<span style="color: red">作品简介不能超过400个字</span>
					</td>
				</tr>
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">作品百度云地址</td>
					<td><s:textfield name="bdAddress" cssStyle="width:300px; height:22px" ></s:textfield></td>
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
