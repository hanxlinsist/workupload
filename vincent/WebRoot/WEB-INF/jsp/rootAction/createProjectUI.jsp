<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<title>团队_资料完善界面</title>
</head>

<body>
	<div class="content">
		<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
		<p style="text-align:center"><img src="<%=rootPath %>/images/teamUserInfoCompliment.jpg" /></p>
		<p style="text-align:center;color:red;font-weight: bold;">提示:保存以后系统会自动生成一个管理这个项目的账号，初始密码为1234！！！</p>

		<s:form action="project_create" method="post" onsubmit="return validate()" enctype="multipart/form-data">
			<table cellpadding="0" cellspacing="0" width="100%">
			<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">项目名称</td>
					<td>
						<s:textfield name="projectName" style="width:300px; height:22px" ></s:textfield>
						<span style="color:red">格式：xxxx年大创</span>
					</td>
				</tr>
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">包含作品类型</td>
					<td>
						<s:textfield name="worktypes" style="width:300px; height:22px" ></s:textfield>
						<span style="color:red">格式：科技、社会实践　----中间用顿号隔开</span>
					</td>
				</tr>
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">包含推荐级别</td>
					<td>
						<s:textfield name="relevels" style="width:300px; height:22px" ></s:textfield>
						<span style="color:red">格式：省甲、校乙　----中间用顿号隔开</span>
					</td>
				</tr>
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">上传指标图片</td>
					<td>
						<s:file name="indexImage" label="File"/>
					</td>
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