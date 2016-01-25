<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<script type="text/javascript" src="<%=rootPath%>/script/hanxl.js"></script>
	<title>学校_账号列表</title>
	<script type="text/javascript">
		$(document).ready(function () {
			var id = getUrlParameter('id');
			$('#dg').datagrid({
				url:'opus_showModifyRecord.action?id=' + id
			});
			
			$('#level').combobox({
				url:'opus_takeRecommendedLevel.action?id=' + id
			});
		}); 
	
	
	//查询
	function doSearch() {
		var f1 = validTotalScore();
		var f2 = validAverageScore();
		if (f1 && f2) {
			$('#dg').datagrid('load', {
				opusName : $('#opusName').val(),
				totalScore : $('#totalScore').val(),
				averageScore : $('#averageScore').val(),
				level : $("#level").combobox("getValue")
			});
		} else
			$.messager.alert('Warning','请输入浮点数！！！'); 
	}
	
	//校验总分是否为浮点数
	function validTotalScore() {
		var totalScore = $.trim($('#totalScore').val());
		return validate(totalScore);
	}
	
	//校验平均分是否为浮点数
	function validAverageScore() {
		var averageScore = $.trim($('#averageScore').val());
		return validate(averageScore);
	}
	
	function validate(score) {
		var re1='^([+-]?\\d*\\.\\d+)(?![-+0-9\\.])$';	// match double
		var p = new RegExp(re1, ["i"]);
		if ( score == "" || (score != "" && p.exec(score) != null) )
			return true;
		else
			return false;
	}
	</script>
</head>

<body>
	<div class="content" style="background-color:#fff">
		<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
		<p style="text-align:center"><img src="<%=rootPath %>/images/school_accountList.jpg" /></p>
		<p style="text-align:center;color:red">注意:如果是整数，加上小数点（例如：123,则输入123.0）</p>
		
		<table id="dg" class="easyui-datagrid"
			singleSelect="true" rownumbers="true" fitColumns="true"
			toolbar="#tb" title="修改记录列表" iconCls="icon-save" rownumbers="true"
			pagination="true">
			<thead>
				<tr>
					<th field="opusName" width="100">作品名称</th>
					<th field="totalScore" width="100">作品总分</th>
					<th field="averageScore" width="100">作品平均分</th>
					<th field="level" width="100">作品当前推荐级别</th>
					<th field="levelModifyRecord" width="400">作品被修改记录</th>
				</tr>
			</thead>
		</table>
		
    <div id="tb" style="padding:5px;height:auto">
        <div>
			<span>作品名称:</span>
	       	<input id="opusName" name="opusName" style="line-height:26px;border:1px solid #ccc">
	        <span>总分:</span>
	        <input id="totalScore" name="totalScore" style="line-height:26px;border:1px solid #ccc">
	        <span>平均分:</span>
	        <input id="averageScore" name="averageScore" style="line-height:26px;border:1px solid #ccc">
	        <span>推荐级别:</span>
	        <input class="easyui-combobox" id="level"  data-options="valueField:'id',textField:'text'"
	        		name="level" style="width:18%" >
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">查询</a>
        </div>
    </div>
		
		<div style="text-align:center; margin-top:10px; margin-bottom:10px">
			<input type="button" value="返回" class="input-button" onclick="window.location.href='<%=rootPath %>/root/account_listUI.action';" />
		</div>
		
		<%@ include file="/WEB-INF/jsp/public/footer.jspf" %>
	</div>
		
</body>
</html>