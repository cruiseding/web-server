<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath()+"/uiface";
%>
<%!
Boolean bool = true;
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="stylesheet" type="text/css"
	href="<%=path %>/boss/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path %>/boss/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path %>/boss/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path %>/boss/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="<%=path %>/boss/static/h-ui.admin/css/style.css" />

<title>日志查询</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		日志查询
		<a class="btn btn-success radius r btn-refresh"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="Hui-iconfont">&#xe68f;</i>
		</a>
	</nav>
	<div class="page-container">
		<div class="mt-20">
			<div class="text-c">
				<input type="text" class="input-text" style="width: 100px;" placeholder="用户名" id="searchtxt" name="searchtext">
				<input type="text" class="input-text" style="width: 100px;" placeholder="IP" id="searchtxt1" name="searchtext1">
				
				<span>&nbsp;&nbsp;&nbsp;&nbsp;时间:</span>        
				<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;" name="">
			
				- <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;" name=""> 
				
			<button type="submit" class="btn btn-success radius" id="searchbtn" name="" ><i class="Hui-iconfont"></i>搜索</button>
				
			</div>	
		</div>
		
		<%-- <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"> <a href="javascript:;" onclick="system_category_edit1('Vip充值添加','<%=path %>/boss/vip_add.jsp','600','600')"  class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> Vip充值添加</a></span> </div> --%>
		<div class="mt-20">
			<div class="dataTables_wrapper">
				<table
					class="table table-border table-bordered table-hover table-bg">
					<thead>
					<tr class="text-c">
					
					<th width="30">序号</th>
					
					<!-- 字段：序号、名称、价格、金额、是否推荐、操作 -->
				    <th width="30">id</th>
				    <th width="30">用户名</th> 
				    <th width="30">IP</th> 
					<th width="40">行为</th>
					<th width="40">时间</th>
					
					</tr>
					</thead>
					<tbody id="list-content">
						<c:forEach var="map" items="${reList}" varStatus="status">
							<tr class="text-c">
								<td>${status.count}</td>
								<!-- ID、爱豆数量、赠送数量、价格、操作 -->
								 <td>${map['id']}</td>
								<td>${map['username']}</td>
								<td>${map['ip']}</td>
								<td>${map['action']}</td>
								<td>${map['time']}</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="dataTables_info" id="DataTables_Table_0_info"
					role="status" aria-live="polite">
					显示 <span id="pagefirst">${pageNo[2]+1}</span> 到 <span id="pagelast">${pageNo[3]}</span>
					，共 <span id="total">${pageNo[1]}</span>条
				</div>
				<div class="dataTables_paginate paging_simple_numbers"
					id="DataTables_Table_0_paginate">
					<a class="paginate_button previous disabled"
						aria-controls="DataTables_Table_0" data-dt-idx="0" tabindex="0"
						id="DataTables_Table_0_previous">上一页 </a><span><a
						class="paginate_button current" aria-controls="DataTables_Table_0"
						data-dt-idx="1" tabindex="0"><span id="currentpage">${pageNo[4]}</span></a></span>
					<a class="paginate_button next disabled"
						aria-controls="DataTables_Table_0" data-dt-idx="2" tabindex="0"
						id="DataTables_Table_0_next">下一页</a>
				</div>
			</div>
		</div>
	</div>
	<div></div>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="<%=path %>/boss/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/boss/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="<%=path %>/boss/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="<%=path %>/boss/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="<%=path %>/boss/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/boss/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="<%=path%>/lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript">
	$("#searchbtn").click(function(){
		fresh_page(1)
		
	});
var totalpage =1;
$(function(){
	
	// 上一页
	$("#DataTables_Table_0_previous").click(function() {
		var currentpage = Number($("#currentpage").html());
		if(currentpage <= 1) {
			alert('当前已经是第一页');
			return;
		}
		fresh_page(currentpage - 1);
	});
	
	// 下一页
	$("#DataTables_Table_0_next").click(function() {
		var currentpage = Number($("#currentpage").html());
		var totalpage = Number($("#totalpage").html());
		if(currentpage >= totalpage) {
			alert('当前已经是最后一页');
			return;
		}
		
		fresh_page(currentpage + 1);
	});

});

function system_category_edit1(title,url,w,h){
	layer_show(title,url,w,h);
}

function proj_edit(title,url,w,h){
	layer_show(title,url,w,h);
}
/*查询娃娃详情*/
function client_search(title,url,w,h){
	layer_show(title,url,w,h);
}



/*用户-删除*/
function client_del(id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '<%=path%>/ar?p0=A-boss-delete&p1=vip_del&p2='+id,
			success: function(data){
				/*$(obj).parents("tr").remove();*/
				layer.msg('已删除!',{icon:1,time:1000});
				setTimeout(function () { 
					javascript:location.replace(location.href);
			    }, 1000);
			},
			error:function(data) {
				alert('提交失败');
			},
		});		
	});
}

//刷新列表     
var curUrl = "";
function fresh_page(pageIndex) {
	var startdate=$("#datemin").val();
	var enddate=$("#datemax").val();
	var searchtxt = $("#searchtxt").val();
	var searchtxt1 = $("#searchtxt1").val();
	
	if(startdate=="" && enddate!=""){
		alert('请选择开始时间');
		return;
	}else if(enddate=="" && startdate!=""){
		alert('请选择结束时间');
		return;
	}
	
	
	curUrl = "<%=path%>/ar?p0=A-boss-search&p1=log_search&p2="+pageIndex+"&p3=tojson&p4="+searchtxt+"&p5="+searchtxt1+"&p6="+startdate+"&p7="+enddate+"&p8=";
		$.ajax({
			cache:true,
			type:"post",
			
			url:curUrl,
			async: true,
			error: function(request) {
				alert("提交失败 ");
			},
			success:function(data){
				var json=eval("("+data+")");
				var content = '';
				for(var i = 0;i<json.length-1;i++){
					
					
					content += '<tr class = "text-c">'
							+'<td>' +(Number(json[json.length-1].current)+1+i)+'</td>'
							
							+'<td>'+json[i].id+'</td>'
							+'<td>'+json[i].username+'</td>'
							+'<td>'+json[i].ip+'</td>'
						    +'<td>'+json[i].action+'</td>'
						    +'<td>'+json[i].time+'</td>'
						    ;
							/* +'<td>'+json[i].exchange_price+'</td>' */
							/* +'<td class="td-manage">'
							+'<a title="通过" href="javascript:;" onclick="system_category_edit1('+json[i].id1+','+json[i].id+') "  class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">通过</i></a>'
							+'<a title="不通过" href="javascript:;" onclick="go('+json[i].id1+','+json[i].id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">未通过</i></a>'
							+'</td>' */
			}
				$("#list-content").html(content);
				totalpage = Number(json[json.length-1].totlePage);
				$("#pagefirst").html(Number(json[json.length-1].current)+1);
				$("#pagelast").html(json[json.length-1].lastcount);
				$("#total").html(json[json.length-1].totle);
				$("#currentpage").html(json[json.length-1].pagenum);
		  }
		});
}
</script>
</body>
</html>