<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<title>学校_账号列表</title>
	
	<script type="text/javascript">
	//查询
	function doSearch() {
		$('#dg').datagrid('load', {
			name : $('#name').val(),
			jobNumber : $('#jobNumber').val(),
			projectName : $('#projectName').val(),
		});
	}
	
    //评审数据分析
    function dataAnalysis(){
        var row = $('#dg').datagrid('getSelected');
        if (row)
        	window.location = 'data_analysisUI.action?id=' + row.id;
        else
        	$.messager.alert('Warning','请选择一个帐户！！！');
    }
	
</script>
</head>

<body>
	<div class="content" style="background-color:#fff">
		<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
		<p style="text-align:center"><img src="<%=rootPath %>/images/school_accountList.jpg" /></p>
		
		<table id="dg" class="easyui-datagrid"
			url="reviewerAnalysis_reviewerList.action" singleSelect="true" rownumbers="true" fitColumns="true"
			toolbar="#tb" title="评审数据分析列表" iconCls="icon-save" rownumbers="true"
			pagination="true">
			<thead>
				<tr>
					<th field="name" width="180">姓名</th>
					<th field="jobNumber" width="180">工号</th>
					<th field="projectName" width="180">参与项目</th>
				</tr>
			</thead>
		</table>
		
    <div id="tb" style="padding:5px;height:auto">
        <div style="margin-bottom:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-tip" plain="true" onclick="dataAnalysis()">分析数据</a>
    </div>
        <div>
			<span>姓名:</span>
	       	<input id="name" style="line-height:26px;border:1px solid #ccc">
	        <span>工号:</span>
	        <input id="jobNumber" style="line-height:26px;border:1px solid #ccc">
	        <span>参与项目:</span>
	        <input id="projectName" style="line-height:26px;border:1px solid #ccc">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">查询</a>
        </div>
    </div>
    
		
		<div style="text-align:center; margin-top:10px; margin-bottom:10px">
			<input type="button" value="返回" class="input-button" onclick="window.location.href='<%=rootPath %>/root/functionlist_rootList.action';" />
		</div>
		
		<%@ include file="/WEB-INF/jsp/public/footer.jspf" %>
	</div>
</body>
</html>

