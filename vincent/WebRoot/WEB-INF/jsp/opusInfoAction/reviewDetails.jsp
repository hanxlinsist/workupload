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
					<td style="height:30px; line-height:30px; text-align:center">评审姓名</td>
					<td style='text-align:center'>工号</td>
					<td style='text-align:center'>打出的分数</td>
					<td style='text-align:center'>是否推荐</td>
				</tr>                                   
				
				<!-- 数据显示区域 -->           
					<s:iterator value="#reviewList">
						<tr>
							<td style="height:30px; line-height:30px; text-align:center">${reviewer.name }</td>
							<td style='text-align:center'>${reviewer.jobNumber }</td>
							<td style='text-align:center'>${score }</td>
							<td style='text-align:center'>
								<s:if test="%{isRecommend == 1}">
									推荐
								</s:if>
								<s:if test="%{isRecommend == 0}">
									不推荐
								</s:if>
							</td>
						</tr>
					</s:iterator>
			</table>
			<div style="text-align:center; margin-top:10px; margin-bottom:10px">
				<input type="button" value="返回" class="input-button" onclick="window.location.href='<%=rootPath %>/school/opus_opusInfoAdminListUI.action';" />
			</div>
		<%@ include file="/WEB-INF/jsp/public/footer.jspf" %>
	</div>
</body>
</html>
