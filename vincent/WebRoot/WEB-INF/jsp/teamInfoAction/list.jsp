<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<title>学校_团队信息管理列表</title>
<script type="text/javascript">
	//查询
	function doSearch() {
		$('#dg').datagrid('load', {
			captainName : $('#captainName').val(),
			opusName : $('#opusName').val()
		});
	}
	
</script>
</head>

<body>
	<div class="content" style="background-color:#fff">
		<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
		<p style="text-align:center"><img src="<%=rootPath %>/images/school_teaminfoAdmin.jpg" /></p>
		
		<table id="dg" class="easyui-datagrid"
			url="team_teamInfoAdminList.action" singleSelect="true" rownumbers="true" fitColumns="true"
			toolbar="#tb" title="团队信息列表" iconCls="icon-save" rownumbers="true"
			pagination="true">
			<thead>
				<tr>
					<th field="captainName" width="180">队长名</th>
					<th field="opusName" width="180">作品名称</th>
					<th field="bdAddress" width="180">作品百度云地址</th>
					<th field="captainPhone" width="180">队长手机</th>
					<th field="captainMail" width="180">队长邮箱</th>
				</tr>
			</thead>
		</table>
		
    <div id="tb" style="padding:5px;height:auto">
        <div>
			<span>队长名:</span>
	       	<input id="captainName" style="line-height:26px;border:1px solid #ccc">
	        <span>作品名称:</span>
	        <input id="opusName" style="line-height:26px;border:1px solid #ccc">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">查询</a>
        </div>
    </div>
		
		<div style="text-align:center; margin-top:10px; margin-bottom:10px">
			<input type="button" value="返回" class="input-button" onclick="window.location.href='<%=rootPath %>/school/functionlist_schoolList.action';" />
		</div>
		
		<%@ include file="/WEB-INF/jsp/public/footer.jspf" %>
	</div>
</body>
</html>
