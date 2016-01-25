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
			userName : $('#userName').val(),
			projectName : $('#projectName').val()
		});
	}
	
	//禁用帐户
    function disableUser(){
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $.messager.confirm('Confirm','您确认要禁用选定的账户吗？',function(r){
                if (r){
                    $.post('account_disabled.action',{id:row.id},function(result){
                        if (result.success){
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
        } else {
        	$.messager.alert('Warning','请选择一个帐户！！！');
        }
    }
	
	//查看管理帐户修改作品的记录
    function showModifyRecord(){
        var row = $('#dg').datagrid('getSelected');
        if (row){
        	window.location = 'opus_showModifyRecordUI.action?id=' + row.id;
        } else {
        	$.messager.alert('Warning','请选择一个帐户！！！');
        }
    }
</script>
</head>

<body>
	<div class="content" style="background-color:#fff">
		<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
		<p style="text-align:center"><img src="<%=rootPath %>/images/school_accountList.jpg" /></p>
		<p style="text-align:center;color:red">友情提示:用户名是根据创建时间组成的，查询的时候可以利用这个特点</p>

		<table id="dg" class="easyui-datagrid"
			url="account_list.action" singleSelect="true" rownumbers="true" fitColumns="true"
			toolbar="#tb" title="管理账号列表" iconCls="icon-save" rownumbers="true"
			pagination="true">
			<thead>
				<tr>
					<th field="userName" width="180">用户名</th>
					<th field="projectName" width="180">工程名</th>
					<th field="status" width="180">账户状态</th>
				</tr>
			</thead>
		</table>
		
    <div id="tb" style="padding:5px;height:auto">
        <div style="margin-bottom:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-lock" plain="true" onclick="disableUser()">禁用帐户</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-print" plain="true" onclick="showModifyRecord()">查看修改记录</a>
    </div>
    
        <div>
			<span>用户名:</span>
	       	<input id="userName" style="line-height:26px;border:1px solid #ccc">
	        <span>工程名:</span>
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