<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<title>评审_作品列表</title>
<script type="text/javascript">
	//评分
	function remark(){
	    var row = $('#dg').datagrid('getSelected');
	    if (row){
	    	window.location = 'opus_remarkScoreUI.action?id=' + row.id;
	    } else {
	    	$.messager.alert('Warning','请选择一个帐户！！！');
	    }
	}
</script>
</head>

<body>
<div class="content" style="background-color:#fff">
	<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
	<p style="text-align:center"><img src="<%=rootPath %>/images/reviewer_opus_list.jpg" /></p>
	
	
	<table id="dg" class="easyui-datagrid"
			url="opus_list.action" singleSelect="true" rownumbers="true" fitColumns="true"
			toolbar="#tb" title="管理账号列表" iconCls="icon-save" rownumbers="true"
			pagination="true">
			<thead>
				<tr>
					<th field="opusName" width="180">作品名称</th>
					<th field="opusCategory" width="180">作品类型</th>
					<th field="appraisedNumber" width="180">作品被评次数</th>
				</tr>
			</thead>
		</table>
		
    <div id="tb" style="padding:5px;height:auto">
        <div style="margin-bottom:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="remark()">评分</a>
    </div>
    
</div>
    
	
	<div style="text-align:center; margin-top:10px; margin-bottom:10px">
		<input type="button" value="返回" class="input-button" onclick="window.location.href='<%=rootPath %>/reviewer/functionlist_reviewerList.action';" />
	</div>
	
	<%@ include file="/WEB-INF/jsp/public/footer.jspf" %>
</div>
</body>
</html>