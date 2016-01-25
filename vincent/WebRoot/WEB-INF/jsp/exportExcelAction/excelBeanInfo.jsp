<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<title>学校_导出Excel</title>
</head>

<body>
	<div class="content" style="background-color:#fff">
		<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
		<p style="text-align:center"><img src="<%=rootPath %>/images/school_exportExcel.jpg" /></p>
			<table cellpadding="0" cellspacing="0" width="100%" style="border:solid 1px #ccc; border-collapse:collapse" border="1">
				<tr>
					<td style="height:30px; line-height:30px; text-align:center">作品名称</td>
					<td style='text-align:center'>学院</td>
					<td style='text-align:center'>推荐级别</td>
					<td style='text-align:center'>所属项目</td>
					<td style='text-align:center'>队长姓名</td>
					<td style='text-align:center'>队长学号</td>
					<td style='text-align:center'>项目其他成员信息</td>
					<td style='text-align:center'>指导老师信息</td>
					<td style='text-align:center'>项目简介</td>
				</tr>                                   
				
				<!-- 数据显示区域 -->           
					<s:iterator value="#teamList">
						<tr>
							<td style="height:30px; line-height:30px; text-align:center">${opus.opusName }</td>
							<td style='text-align:center'>${academy }</td>
							<td style='text-align:center'>${opus.level }</td>
							<td style='text-align:center'>${project.projectName }</td>
							<td style='text-align:center'>${captainName }</td>
							<td style='text-align:center'>${captainId }</td>
							<td style='text-align:center'>${teamMemberName }</td>
							<td style='text-align:center'>${conductTeacherName }</td>
							<td style='text-align:center'>${opus.opusSummary }</td>
						</tr>
					</s:iterator>
			</table>
			<div style="text-align:center; margin-top:10px; margin-bottom:10px">
				<input type="button" value="导出Excel" class="input-button" onclick="window.location.href='<%=rootPath %>/temp.xls';" />
			</div>
		<%@ include file="/WEB-INF/jsp/public/footer.jspf" %>
	</div>
</body>
</html>
