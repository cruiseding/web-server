
<%@page import="java.util.*"%>
<%--声明我要使用C标签--%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% 
	String path = request.getContextPath() + "/uiface";
%>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />

<link rel="stylesheet" type="text/css" href="<%=path%>/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/lib/Hui-iconfont/1.0.8/iconfont.css" />
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!-- link<>链接并指定外部样式表 -->
<link rel="stylesheet" type="text/css" href="<%=path %>/boss/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/boss/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/boss/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/boss/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path %>/boss/static/h-ui.admin/css/style.css" />

<title>用户反馈列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>消息管理<span class="c-gray en">&gt;</span>意见反馈列表
<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
		<i class="Hui-iconfont">&#xe68f;</i>
	</a>
</nav>
<div class="page-container">

	
	<div class="mt-20">
			<div class="text-c">
				
				<input type="text" class="input-text" style="width: 100px;" placeholder="ID号" id="searchtxt" name="searchtext">
				<!-- <input type="text" class="input-text" style="width: 100px;" placeholder="手机号" id="phone" name="phone"> -->
				
						
				<span>&nbsp;&nbsp;&nbsp;&nbsp;提交时间:</span>        
				<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;" name="">
			
				- <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;" name=""> 
				<span>&nbsp;&nbsp;&nbsp;&nbsp;状态:</span>
	        	<select class="input-text" style="width:150px" id="result" name="result" >
					<option value="">全部</option>
					<option value="处理中">处理中</option>
					<option value="已处理">已处理</option>
				</select>
				
	        	     
	          
			<button type="submit" class="btn btn-success radius" id="searchbtn" name="" ><i class="Hui-iconfont"></i>搜用户</button>
				
			</div>	
		</div>
	



	<div class="mt-20">
	<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper ">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<!-- <th width="25"><input type="checkbox" name="" value=""></th> -->
				<th width="40">序号</th>
				<th width="40">用户ID</th>
				<th width="40">用户昵称</th>
				<th width="40">投诉内容</th>
				<th width="40">投诉时间</th>
				<th width="40">查看状态</th>
				<th width="40">联系方式</th>
				<th width="40">处理时间</th>
				<th width="40">操作</th>
				<!-- <th width="40">操作</th> -->
			</tr>
		</thead>
		<tbody id="list-content" >
		<c:forEach var="map" items="${reList}" varStatus="status">
			<tr class="text-c">
				<!-- <td><input type="checkbox" value="1" name="" ></td> -->
				<td>${status.count}</td>
				<td>${map['u_user_id']}</td>
				<td>${map['nickname']}</td>
				<td>${map['opinion_content']}</td>
				<td>${map['time']}</td>
				<td>${map['result']}</td>
				<td>${map['phone']}</td>
				<c:if test="${map['result']=='处理中' }">
					<td>${map['result']}</td>
					<td>
						<a href="javascript:;" onclick="com_mod(${map['id']})" style="text-decoration:none;color:red;" class="ml-5">标记处理</a>
						|
						<a hred="javascript:;" onclick="com_del(${map['id']})" style="text-decoration:none;color:red" class="ml-5">删除</a>
					</td>
				
				</c:if>
				<c:if test="${map['result']=='已处理' }">
					<td>${map['handle_time'] }</td>
					<td>
						<a href="javascript:;" onclick="com_del(${map['id']})" style="text-decoration:none;color:red" calss="ml-5">删除</a>
					</td>
				</c:if>
				
				<%-- <td>
					<a title="回复" href="javascript:;" 
						onclick="go(${map['id']},${map['user_id']})" 
						class="ml-5" style="text-decoration:none">
				<i class="Hui-iconfont">回复</i></a>
				</td> --%>
			</tr>
		</c:forEach>	
		</tbody>
	</table>
	<div class="dataTables_info" id="DataTables_Table_0_info" role="status" aria-live="polite">显示 <span id="pagefirst">${pageNo[2]+1}</span> 到 <span id="pagelast">${pageNo[3]}</span> ，共 <span id="total">${pageNo[1]}</span>条</div>
	<div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate" ><a class="paginate_button previous disabled" aria-controls="DataTables_Table_0" data-dt-idx="0" tabindex="0" id="DataTables_Table_0_previous">上一页</a><span><a class="paginate_button current" aria-controls="DataTables_Table_0" data-dt-idx="1" tabindex="0"><span id="currentpage">${pageNo[4]}</span></a></span><a class="paginate_button next disabled" aria-controls="DataTables_Table_0" data-dt-idx="2" tabindex="0" id="DataTables_Table_0_next">下一页</a></div>
	
	</div>
	</div>
</div>
<div >

    </div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path %>/boss/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=path %>/boss/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path %>/boss/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=path %>/boss/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path %>/boss/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=path%>/boss/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=path%>/boss/lib/laypage/1.2/laypage.js"></script>

<script type="text/javascript">
function proj_edit(id){
	/* layer_show(title,url,w,h); */
	
	$.ajax({
		type: 'POST',
		url: '<%=path%>/rp?p0=A-boss-mod&p1=complaint_back&p2='+id,
		success: function(data){
			alert('成功');
			/*$(obj).parents("tr").remove();*/
			setTimeout(function () { 
				javascript:location.replace(location.href);
		    },300);
		},
		error:function(data) {
			alert('提交失败');
		},
	});		
	
	
	
	
}
var totalpage = ${pageNo[0]};
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
		
		if(currentpage >= totalpage) {
			alert('当前已经是最后一页');
			return;
		}
		
		fresh_page(currentpage + 1);
	});
	
	$("#searchbtn").click(function(){
		fresh_page(1);
	});
	
	
});

/*刷新列表*/
var curUrl = "";
function fresh_page(pageIndex) {
	var searchtxt=$("#searchtxt").val();
	var startdate=$("#datemin").val();
	var enddate=$("#datemax").val();
	
	if(startdate=="" && enddate!=""){
		alert('请选择开始时间');
		return;
	}else if(enddate=="" && startdate!=""){
		alert('请选择结束时间');
		return;
	}
	var result=$("option:selected","#result").val();
	curUrl = "<%=path%>/ar?p0=A-boss-search&p1=complaint_search&p2="+pageIndex+"&p3=tojson&p4="+searchtxt+"&p5="+startdate+"&p6="+enddate+"&p7="+result;
	$.ajax({
		cache: true,
		type: "POST",
		url:curUrl,  
		async: true,
		error: function(request) {
			alert("提交失败 ");
		},
		success: function(data) {
			var json=eval("("+data+")");
			var content = '';
			for(var i = 0; i < json.length-1; i++) {
				var handle='';
				var control='';
				if(json[i].result=='处理中'){
					handle='<td>'+json[i].result+'</td>';
					control='<td><a href="javascript:;" onclick="com_mod('+json[i].id+')" style="text-decoration:none;color:red;" class="ml-5">标记处理</a>|<a hred="javascript:;" onclick="com_del('+json[i].id+')" style="text-decoration:none;color:red" class="ml-5">删除</a></td>';
				}else if(json[i].result=='已处理'){
					handle='<td>'+json[i].handle_time+'</td>';
					control='<td><a hred="javascript:;" onclick="com_del('+json[i].id+')" style="text-decoration:none;color:red" class="ml-5">删除</a></td>';
				}
				content +='<tr class="text-c">'
					+'<td>'+(Number(json[json.length-1].current)+1+i)+'</td>'
					+'<td>'+json[i].u_user_id+'</td>'
					+'<td>'+json[i].nickname+'</td>'
					+'<td>'+json[i].opinion_content+'</td>'
					+'<td>'+json[i].time+'</td>'
					+'<td>'+json[i].result+'</td>'
					+'<td>'+json[i].phone+'</td>'
					+handle
					+control
					;
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
function go(id,id1){
	age = prompt("请输入要回复的内容","");
	layer.confirm('确认回复该内容？',function(index){
		$.ajax({
			type: 'POST',
			url: '<%=path%>/rp?p0=A-boss-mod&p1=complaint_back&p2='+id+'&p4='+id1+'&p3='+age,
			success: function(data){
				/*$(obj).parents("tr").remove();*/
				layer.msg('回复完成',{icon:1,time:1000});
				setTimeout(function () { 
					javascript:location.replace(location.href);
			    }, 1000);
			},
			error:function(data) {
				alert('通过失败');
			},
		});		
	});
}
function com_mod(e){
	$.ajax({
		type:'POST',
		url:'<%=path %>/ar?p0=A-boss-mod&p1=com_mod&p2='+e,
		success:function(data){
			layer.msg('标记处理成功',{icon:1,time:1000});
			setTimeout(function(){
				if(curUrl == ""){
					javascript:location.replace(location.href);
				}else{
					javascript:location.replace(curUrl.replace('tojson','tojsp'));
				}
				
			},1000);
		},
		error:function(){
			alert("处理失败！");
		},
	});
}
function com_del(g){
	$.ajax({
		type:'POST',
		url:'<%=path %>/ar?p0=A-boss-delete&p1=com_del&p2='+g,
		success:function(data){
			layer.msg('删除成功',{icon:1,time:1000});
			setTimeout(function(){
				if(curUrl == ""){
					javascript:location.replace(location.href);
				}else{
					javascript:location.replace(curUrl.replace('tojson','tojsp'));
				}
				
			},1000);
		},
		error:function(data){
			alert('删除失败');
		}
		
	});
}
</script>
</body>
</html>