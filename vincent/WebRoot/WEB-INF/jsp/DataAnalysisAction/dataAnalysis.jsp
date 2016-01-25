<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="<%=rootPath%>/script/canvasjs.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/script/hanxl.js"></script>

<script type="text/javascript">

$(document).ready(function () {
	var id = getUrlParameter('id');
	var uri = "data_analysis.action?id=" + id;
	var dps = []; //声明一个数组，在下面的chart中使用

	//将异步Ajax取消，否则图表数据还没有取出就会绘制图表
	$.ajaxSetup({
		async : false
	});

	$.getJSON(uri, function(data) {
		$.each(data, function(key, val) {
			for ( var key in val) {
				var data = new Object();
				data.y = val[key];
				data.indexLabel = key;
				dps.push(data);
			}
		});
	});

	var chart = new CanvasJS.Chart("chartContainer", {
		theme : "theme1",
		title : {
			text : "评审者的数据分析"
		},
		data : [ {
			type : "pie",
			showInLegend : true,
			toolTipContent : "#percent %",
			legendText : "{indexLabel}",
			dataPoints : dps
		} ]
	});
	chart.render();

}); 
</script>
</head>

<body>
	<div class="content">
		<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
		<div id="chartContainer" style="height: 300px; width: 100%;"></div>
		
		<div style="text-align:center; margin-top:10px; margin-bottom:10px">
			<input type="button" value="返回" class="input-button" onclick="window.location.href='<%=rootPath %>/root/reviewerAnalysis_reviewerListUI.action';" />
		</div>
		<%@ include file="/WEB-INF/jsp/public/footer.jspf" %>
    </div>
</body>
</html>
