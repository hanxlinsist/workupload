<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<title>团队_资料完善界面</title>
	<script type="text/javascript">
		//校验队长手机
		function validateCaptainPhone(captainPhone) {
			var reg = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
			if (captainPhone.match(reg)) {
				return true;
			} else {
				alert('您的手机号格式不正确');
				return false;
			}
		}
		
		//校验队长邮箱
		function validateCaptainMail(captainMail) {
			var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
			if (captainMail.match(reg)) {
				return true;
			} else {
				alert('您的邮箱格式不正确');
				return false;
			}
		}
		
		//校验团队其他队员姓名
		function validateTeamMemberName(teamMemberName) {
			var reg = /^([\u4e00-\u9fa5]{2,3}\(\d+\)、)+$/;
			var newTeamMemberName = teamMemberName + "、";
			if (newTeamMemberName.match(reg)) {
				return true;
			} else {
				alert('团队其他队员姓名格式不正确');
				return false;
			}
		}
		
		//校验指导老师姓名
		function validateConductTeacherName(conductTeacherName) {
			var reg = /^([\u4e00-\u9fa5]{2,3}#[\u4e00-\u9fa5]{1,}、)+$/;
			var newConductTeacherName = conductTeacherName + "、";
			if (newConductTeacherName.match(reg)) {
				return true;
			} else {
				alert('指导老师姓名格式不正确');
				return false;
			}
		}
		
		function validate() {
			var texts = $(":text");
			return validateCaptainPhone(texts[2].value) && validateCaptainMail(texts[3].value) && validateTeamMemberName(texts[4].value) && validateConductTeacherName(texts[5].value);
		}
	</script>
</head>

<body>
	<div class="content">
		<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
		<p style="text-align:center"><img src="<%=rootPath %>/images/teamUserInfoCompliment.jpg" /></p>

		<s:form action="team_complementInformation" method="post" onsubmit="return validate()">
			<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">用户名</td>
					<td>${account.userName }</td>
				</tr>
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">团队类别</td>
					<td>
						<s:select name="teamCategory" list="#{0:'本科生',1:'研究生'}" ></s:select>
					</td>
				</tr>
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">队长姓名</td>
					<td><s:textfield name="captainName" style="width:300px; height:22px" ></s:textfield></td>
				</tr>
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">队长学号</td>
					<td><s:textfield name="captainId" style="width:300px; height:22px" ></s:textfield></td>
				</tr>
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">队长手机</td>
					<td><s:textfield name="captainPhone" style="width:300px; height:22px" ></s:textfield></td>
				</tr>
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">队长邮箱</td>
					<td><s:textfield name="captainMail" style="width:300px; height:22px" ></s:textfield></td>
				</tr>
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">团队其他队员姓名</td>
					<td>
						<s:textfield name="teamMemberName" style="width:300px; height:22px" ></s:textfield>
						<span style="color:red">格式：小明(学号)、小美(学号)　----中间用顿号隔开，括号中为学生学号</span>
					</td>
				</tr>
				<tr>
					<td width="300" style="height:30px; line-height:30px; text-align:center">指导老师</td>
					<td>
						<s:textfield name="conductTeacherName" style="width:300px; height:22px" ></s:textfield>
						<span style="color:red">格式：小明#教授、小美#讲师　----中间用顿号隔开，姓名和职称用#隔开</span>
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