<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<title>评审_作品浏览与评分</title>
</head>

<body>
	<div class="content">
		<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
		<p style="text-align:center"><img src="<%=rootPath %>/images/opusViewAndReviewer.jpg" /></p>
		<table cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td width="300" style="height:30px; line-height:30px; text-align:center">用户名</td>
				<td>${account.userName }</td>
			</tr>
			<tr>
				<td width="300" style="height:30px; line-height:30px; text-align:center">队长</td>
				<td>${captainName }</td>
			</tr>
			<tr>
				<td width="300" style="height:30px; line-height:30px; text-align:center">其他成员</td>
				<td>${teamMemberName }</td>
			</tr>
			<tr>
				<td width="300" style="height:30px; line-height:30px; text-align:center">指导老师</td>
				<td>${conductTeacherName }</td>
			</tr>
			<tr>
				<td width="300" style="height:30px; line-height:30px; text-align:center">队长手机</td>
				<td>${captainPhone }</td>
			</tr>
			<tr>
				<td width="300" style="height:30px; line-height:30px; text-align:center">队长邮箱</td>
				<td>${captainMail }</td>
			</tr>
			<tr>
				<td width="300" style="height:30px; line-height:30px; text-align:center">团队类别</td>
				<td>
					<s:if test="%{teamCategory == 0}">
						本科生
					</s:if>
					<s:else>
						研究生
					</s:else>
				</td>
			</tr>
			<tr>
				<td width="300" style="height:30px; line-height:30px; text-align:center">作品类别</td>
				<td>${opus.opusCategory }</td>
			</tr>
			<tr>
				<td width="300" style="height:30px; line-height:30px; text-align:center">作品名称</td>
				<td>
					${opus.opusName }
				</td>
			</tr>
			<tr>
				<td width="300" style="height:30px; line-height:30px; text-align:center">作品简介</td>
				<td>
					${opus.opusSummary }
				</td>
			</tr>
			<tr>
				<td width="300" style="height:30px; line-height:30px; text-align:center">作品百度云地址</td>
				<td>
					${opus.bdAddress }
				</td>
			</tr>
		</table>
		
		<!-- 作品评分表 -->
		<p style="text-align:center"><img src="<%=rootPath %>/images/opusReviewerForm.jpg" /></p>
		<p style="text-align:center"><img id="indexImage" src="<%=rootPath %>/${project.ironAddress }"/></p>
		<p style="text-align:center;color:red">提示:根据上图的指标体系，给出您的打分</p>
		<s:form action="review" method="post">
			<p style="text-align:center">
				分数：<input name="score" type="text" value="0" /><br />
				推荐<input type="radio" name="isRecommend" value="1" checked="checked" />
				不推荐<input type="radio" name="isRecommend" value="0" /><br />
				评审意见：<textarea rows="7" cols="80" name="appraisedAdvice"></textarea>
				<s:hidden name="opusid" value="%{opus.id }"></s:hidden><br />
				<s:submit value="提交" cssClass="input-button"></s:submit>
			</p>
		</s:form>
	</div>

	<%@ include file="/WEB-INF/jsp/public/footer.jspf" %>
</body>
</html>