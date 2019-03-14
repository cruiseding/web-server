<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
   String path = request.getContextPath()+"/uiface";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="stylesheet" type="text/css" href="<%=path %>/boss/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/boss/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/boss/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/boss/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path %>/boss/static/h-ui.admin/css/style.css" />

<title>举报管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 举报管理 <span class="c-gray en">&gt;</span> <!-- 文章列表  --><a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">

	
	<div class="mt-20">
			<div class="text-c">
				
				<input type="text" class="input-text" style="width: 100px;" placeholder="举报者ID号" id="searchtxt" name="searchtext">
				<input type="text" class="input-text" style="width: 100px;" placeholder="被举报者ID号" id="searchtxt1" name="searchtext1">
				<!-- <input type="text" class="input-text" style="width: 100px;" placeholder="手机号" id="phone" name="phone"> -->
				
						
				<span>&nbsp;&nbsp;&nbsp;&nbsp;举报时间:</span>        
				<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;" name="">
			
				- <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;" name=""> 
				<span>&nbsp;&nbsp;&nbsp;&nbsp;审核状态:</span>
	        	<select class="input-text" style="width:150px" id="result" name="result" >
					<option value="">全部</option>
					<option value="待审核">待审核</option>
					<option value="已审核">已审核</option>
				</select>
				
	        	     
	          
			<button type="submit" class="btn btn-success radius" id="searchbtn" name="" ><i class="Hui-iconfont"></i>搜用户</button>
				
			</div>	
		</div>
	
	


	<div class="mt-20">
			<!-- <div class="text-c">	
				<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span>审核状态:</span>
				<select class="input-text" style="width:150px" id="searchname" name="searchname">
					<option></option>
					<option value="未通过">未通过</option>
					<option value="审核中">审核中</option>
					<option value="已通过">已通过</option>
				</select>
				<button type="submit" class="btn btn-success radius" id="searchbtn" name=""><i class="Hui-iconfont"></i>搜索</button>
			</div>	 -->
			
	<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper ">
	<form id="memberForm">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
			
			<!-- 序号、举报人ID、举报人昵称、举报人联系方式、被举报人ID、被举报人昵称、被举报人联系方式、举报原因 -->
				<th width="30">序号</th>
				<th width="40">举报人ID</th>
				<th width="40">举报人昵称</th>
				<th width="40">举报人联系方式</th>
				<th width="40">被举报人ID</th>
				<th width="40">被举报人昵称</th>
				<th width="40">被举报人联系方式</th>
				<th width="40">举报原因</th>
				<th width="40">其它原因</th>
				<th width="40">举报时间</th>
				<th width="40">审核状态</th>
				<th width="40">审核时间</th>
				<th width="40">操作</th>
			</tr>
		</thead>
		<tbody id="list-content" >
		<c:forEach var="map" items="${reList}" varStatus="status">
			<tr class="text-c">
				<td>${status.count}</td>
				<td>${map['ids1']}</td>
				<td>${map['name1'] }</td>
				<td>${map['phone1'] }</td>
				<td>${map['ids2'] }</td>
				<td>${map['name2'] }</td>
				<td>${map['phone2'] }</td>
				<td>${map['reason'] }</td>
				<td>${map['other_reason'] }</td>
				<td>${map['report_time'] }</td>
				<td>${map['result'] }</td>
				<td>
					<c:if test="${map['result']=='待审核' }">${map['result'] }</c:if>
					<c:if test="${map['result']=='已审核' }">${map['auditing_time'] }</c:if>
				</td>
				<td>
					<c:if test="${map['result']=='待审核' }">
						<a href="javascript:;" onclick="sign(${map['id']})" style="text-decoration: none;color:red;" class="ml-5">标记处理</a>
						|
						<a href="javascript:;" onclick="del(${map['id']})" style="text-decoration: none;color:red;" class="ml-5">删除</a>
					</c:if>
					<c:if test="${map['result']=='已审核' }">
						<a href="javascript:;" onclick="del(${map['id']})" style="text-decoration: none;color:red;" class="ml-5">删除</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>	
		</tbody>
	</table>
	</form>
	<div class="dataTables_info" id="DataTables_Table_0_info" role="status" aria-live="polite">显示 <span id="pagefirst">${pageNo[2]+1}</span> 到 <span id="pagelast">${pageNo[3]}</span> ，共 <span id="total">${pageNo[1]}</span>条</div>
	<div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate" ><a class="paginate_button previous disabled" aria-controls="DataTables_Table_0" data-dt-idx="0" tabindex="0" id="DataTables_Table_0_previous">上一页</a><span><a class="paginate_button current" aria-controls="DataTables_Table_0" data-dt-idx="1" tabindex="0"><span id="currentpage">${pageNo[4]}</span></a></span><a class="paginate_button next disabled" aria-controls="DataTables_Table_0" data-dt-idx="2" tabindex="0" id="DataTables_Table_0_next">下一页</a></div>
	</div>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path %>/boss/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=path %>/boss/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path %>/boss/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=path %>/boss/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path %>/boss/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=path%>/boss/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript">


 //刷新列表     
 var curUrl = "";
function fresh_page(pageIndex) {
	//var name = document.getElementById("searchname").value;
	//alert(name);
	//var name = $("#searchname").val();
	
	var searchtxt=$("#searchtxt").val();
	var searchtxt1=$("#searchtxt1").val();
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
	
	
	<%-- url: "<%=path %>/ar?a=A-boss-search&b=blacklist_manage&p3="+pageIndex+"&p4=&page=tojson", --%>
	curUrl = "<%=path%>/ar?p0=A-boss-search&p1=blacklist_manage&p2="+pageIndex+"&p3=tojson&p4="+searchtxt+"&p5="+searchtxt1+"&p6="+startdate+"&p7="+enddate+"&p8="+result;
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
					var auditing = '';
					var control = '';
					if(json[i].result=='待审核'){
						auditing='<td>'+json[i].result+'</td>';
						control='<td><a href="javascript:;" onclick="sign('+json[i].id+')" style="text-decoration: none;color:red;" class="ml-5">标记处理</a>|<a href="javascript:;" onclick="del('+json[i].id+')" style="text-decoration: none;color:red;" class="ml-5">删除</a></td>';
					}else if(json[i].result=='已审核'){
						auditing='<td>'+json[i].auditing_time+'</td>';
						control='<td><a href="javascript:;" onclick="del('+json[i].id+')" style="text-decoration: none;color:red;" class="ml-5">删除</a></td>';
					}
					
					content += '<tr class = "text-c">'
							+'<td>' +(Number(json[json.length-1].current)+1+i)+'</td>'
							
							+'<td>'+json[i].ids1+'</td>'
							+'<td>'+json[i].name1+'</td>'
						    +'<td>'+json[i].phone1+'</td>'
						    +'<td>'+json[i].ids2+'</td>'
						    +'<td>'+json[i].name2+'</td>'
						    +'<td>'+json[i].phone2+'</td>'
						    +'<td>'+json[i].reason+'</td>'
						    +'<td>'+json[i].other_reason+'</td>'
						    +'<td>'+json[i].report_time+'</td>'
						    +'<td>'+json[i].result+'</td>'
						    +auditing
						    +control
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

var bool;
//上一页
$("#DataTables_Table_0_previous").click(function() {
	var currentpage = Number($("#currentpage").html());
		if(currentpage <= 1) {
			alert('当前已经是第一页');
			return;
		}	
		fresh_page(currentpage-1);
	
});

// 下一页
$("#DataTables_Table_0_next").click(function() {
	var currentpage = Number($("#currentpage").html());
		var totalpage = ${pageNo[0]};
		if(currentpage >= totalpage) {
			alert('当前已经是最后一页');
			return;
		} 
		fresh_page(currentpage+1);
	
});

function client_del(obj,id){
	
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '<%=path%>/ep?p0=A-boss-delete&p1=del_baby&p2='+id,
			success: function(data){
				/*$(obj).parents("tr").remove();*/
				layer.msg('已删除!',{icon:1,time:1000});
				setTimeout(function() { 
					javascript:location.replace(location.href);
			    }, 1000);
			},
			error:function(data) {
				alert('提交失败');
			},
		});		
	});
}
//模糊查询
$("#searchbtn").click(function(){
	fresh_page(1)
	
	
	
	
	
	
});
function system_category_edit(title,url,w,h){
	layer_show(title,url,w,h);
}
function system_category_edit1(id){
	//layer_show(title,url,w,h);
	
	$.ajax({
		type: 'POST',
		url:'<%=path%>/rp?p0=A-boss-mod&p1=album_checkpass&p2='+id,
		success: function(data){
			/*$(obj).parents("tr").remove();*/
			layer.msg('操作成功',{icon:1,time:1000});
			setTimeout(function () { 
				javascript:location.replace(location.href);
		    }, 1000);
		},
		error:function(data) {
			alert('提交失败');
		},
	});			
}
function go(id,userid){
	age = prompt("请输入未通过原因","");
	layer.confirm('确认输入以上内容？',function(index){

		$.ajax({
			type:'POST',
			url: '<%=path%>/rp?p0=A-boss-mod&p1=album_checknopass&p2='+id+'&p4='+userid+'&p3='+age,
			success: function(data){
				/*$(obj).parents("tr").remove();*/
				layer.msg('操作成功',{icon:1,time:1000});
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
function sign(e){
	$.ajax({
		type:'POST',
		url:'<%=path %>/ar?p0=A-boss-mod&p1=bl_mod&p2='+e+'&p3=&p4=',
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
		error:function(data){
			alert('处理失败！');
		}
	});
}
function del(g){
	$.ajax({
		type:'POST',
		url:'<%=path %>/ar?p0=A-boss-delete&p1=bl_del&p2='+g,
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
			alert('删除失败！');
		}
	});
}
function system_category_add(title,url,w,h){
	layer_show(title,url,w,h);
}
</script> 
</body>
</html>