<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<title>学校_作品排序</title>
	
<script type="text/javascript">
	//查询
	function doSearch() {
		$('#dg').datagrid('load', {
			opusName : $('#opusName').val(),
			captainName : $('#captainName').val(),
			conductTeacherName : $('#conductTeacherName').val()
		});
	}
	//查看评审细节
	function checkDetails(){
		var row = $('#dg').datagrid('getSelected');
        if (row)
        	window.location = 'opus_showReviewDetails.action?id=' + row.id;
        else
        	$.messager.alert('Warning','请选择一个帐户！！！');
	}
	
	var url;
	function modifyLevel(){
		var row = $('#dg').datagrid('getSelected');
		if(row) {
			$('#level').combobox({
				url:'opus_takeRecommendedLevel.action'
			});
			$('#dlg').dialog('open').dialog('setTitle','修改推荐级别');
			url = 'opus_modifyOpusLevel.action?id=' + row.id;
			$("#level").combobox("setValue", row.level);
		} else 
			$.messager.alert('Warning','请选择一个帐户！！！');
		
	}
	
	function modify(){
        $.messager.confirm('Confirm','您确认要修改吗？您所做的修改系统将记录下来！！！',function(r){
            if (r){
                $.post(url,{level:$("#level").combobox("getValue")},function(result){
                    if (result.success){
                    	$('#dlg').dialog('close');		// close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                    } else {
                        $.messager.show({    // show error message
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    }
                },'json');
            }
        });
	}
	
</script>
</head>

<body>
	<div class="content" style="background-color:#fff">
		<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
		<p style="text-align:center"><img src="<%=rootPath %>/images/school_opusSort.jpg" /></p>
		
		<table id="dg" class="easyui-datagrid"
			url="opus_opusInfoAdminList.action" singleSelect="true" rownumbers="true" fitColumns="true"
			toolbar="#tb" title="管理账号列表" iconCls="icon-save" rownumbers="true"
			pagination="true">
			<thead>
				<tr>
					<th field="opusName" width="180">作品名称</th>
					<th field="level" width="180">作品推荐级别</th>
					<th field="averageScore" width="180">作品平均分</th>
					<th field="captainName" width="180">队长姓名</th>
					<th field="captainPhone" width="180">队长手机</th>
					<th field="conductTeacherName" width="180">指导老师姓名</th>
				</tr>
			</thead>
		</table>
		
    <div id="tb" style="padding:5px;height:auto">
        <div style="margin-bottom:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="modifyLevel()">修改推荐级别</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-print" plain="true" onclick="checkDetails()">查看评审细节</a>
    </div>
    <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
			closed="true" buttons="#dlg-buttons">
		<div class="ftitle">修改推荐级别</div>
		<input class="easyui-combobox" id="level"  data-options="valueField:'id',textField:'text'"
	        		name="level" style="width:100%" >
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="modify()" style="width:90px">Save</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancel</a>
	</div>
	
        <div>
			<span>作品名称:</span>
	       	<input id="opusName" style="line-height:26px;border:1px solid #ccc">
	        <span>队长姓名:</span>
	        <input id="captainName" style="line-height:26px;border:1px solid #ccc">
	        <span>指导老师姓名:</span>
	        <input id="conductTeacherName" style="line-height:26px;border:1px solid #ccc">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">查询</a>
        </div>
    </div>
    
    
		<%@ include file="/WEB-INF/jsp/public/footer.jspf" %>
	</div>
	
	
	
	<style type="text/css">
		#level{
			margin:0;
			padding:10px 30px;
		}
		.ftitle{
			font-size:14px;
			font-weight:bold;
			padding:5px 0;
			margin-bottom:10px;
			border-bottom:1px solid #ccc;
		}
		.fitem{
			margin-bottom:5px;
		}
		.fitem label{
			display:inline-block;
			width:80px;
		}
		.fitem input{
			width:160px;
		}
	</style>
</body>
</html>
