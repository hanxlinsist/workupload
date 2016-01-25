<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<title>团队_作品状态界面</title>
</head>
<body>
	<div class="content">
		<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
		<p style="text-align:center"><img src="<%=rootPath %>/images/opusstatus.jpg" /></p>
		<p style="text-align:center;color:red;font-weight: bold;">注意:由于各个评审时间不一致，以下的评审结果可能随时发生改变，敬请谅解！！！</p>
		<table cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td width="300" style="height:30px; line-height:30px; text-align:center">用户名</td>
				<td>${account.userName }</td>
			</tr>
			<tr>
				<td width="300" style="height:30px; line-height:30px; text-align:center">作品所属学院</td>
				<td>${account.academy }</td>
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
				<td width="300" style="height:30px; line-height:30px; text-align:center">作品名称</td>
				<td>${opus.opusName }</td>
			</tr>
			<tr>
				<td width="300" style="height:30px; line-height:30px; text-align:center">作品推荐级别</td>
				<td>${opus.level }</td>
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
				<td>
					<s:if test="%{opus.opusCategory == 0}">
						科技作品 
					</s:if>
					<s:else>
						社会实践调查
					</s:else>
				</td>
			</tr>
			<tr>
				<td width="300" style="height:30px; line-height:30px; text-align:center">作品简介</td>
				<td>${opus.opusSummary }</td>
			</tr>
			<tr>
				<td width="300" style="height:30px; line-height:30px; text-align:center">作品百度云地址</td>
				<td>${opus.bdAddress }</td>
			</tr>
			<tr>
				<td width="300" style="height:30px; line-height:30px; text-align:center">作品平均分</td>
				<td>
					<s:if test="%{opus.averageScore > 0}">
						${opus.averageScore }
					</s:if>
					<s:else>
						亲，还没评审呢！！！
					</s:else>
				</td>
			</tr>
			<tr>
				<td width="300" style="height:30px; line-height:30px; text-align:center">评委对作品的建议</td>
				<td>
					<s:if test="%{opus.appraisedAdvice != null}">
						<s:iterator value="#appraisedAdviceList" status="status">
							<s:iterator value="#appraisedAdviceList[#status.index]">
								评委${status.count }：<s:property/><br>
							</s:iterator>
						</s:iterator>
					</s:if>
					<s:else>
						亲，还没有评委对你的作出评价哦！！！
					</s:else>
				</td>
			</tr>
		</table>
		<%@ include file="/WEB-INF/jsp/public/footer.jspf" %>
   </div>
</body>
</html>
