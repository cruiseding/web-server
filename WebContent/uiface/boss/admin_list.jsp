<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath()+"/uiface";


request.setAttribute("path", path);  
//String path = request.getContextPath() + "/uiface";

String loginid=(String)session.getAttribute("id");


%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<style>
	.td-manage .ml-5 .Hui-iconfont {
		font-size: 18px;
	}
</style>
<title>管理员列表</title>
</head>
<body>
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 
	<span class="c-gray en">&gt;</span> 管理员管理
	<span class="c-gray en">&gt;</span> 管理员列表
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
		<i class="Hui-iconfont">&#xe68f;</i>
	</a>
</nav>
<div class="page-container">
	<div class="text-c">
		
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"> 
	
	<%-- <a href="javascript:;" onclick="system_category_edit1('成员添加','<%=path %>/boss/admin_add1.jsp','600','600')"  class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 成员添加</a></span> </div> --%>
	<a href="javascript:;" onclick="system_category_edit('添加分类','<%=path%>/ar?p0=A-boss-add&p1=admin_add','600','600')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加分类</a>
		</span>
		</div>
		</div>
	<div class="mt-20">
	<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper ">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width=20>序号</th>
				<th width="20">ID</th>
				<th width="40">用户名</th>
				<th width="40">密码</th>
				<th width="40">职务</th>
				<th width="40">权限修改</th>
				<th width="40">密码修改</th>
			</tr>
		</thead>
		<tbody id="list-content">
			<c:forEach var = "map" items="${reList }" varStatus="status">
				<tr class="text-c">
					<td>${status.count}</td>
					<td>${map['id']}</td>
					<td>${map['username']}</td>
					<%-- <td>${map['password']}</td> --%>
					
					<!-- 查看密码部分 -->
					<td><a href="javascript:;"
					onclick="system_category_edit('管理员密码','<%=path %>/ar?p0=A-boss-search&p1=admin_password&p2=${map['id'] }&p3=0','600','510')"
					style="text-decoration: none;color:blue;" class="ml-5">点击查看管理员密码</a>
					</td>
					<td>${map['job']}</td>
					
					<!-- 修改权限部分 -->
					<td>
						<a href="javascript:;"
					onclick="system_category_edit1('修改权限','<%=path %>/ar?p0=A-boss-search&p1=admin_quanxian&p2=${map['id'] }&p3=0','600','600')"
					style="text-decoration: none;color:blue;" class="ml-5">修改权限</a>					
					</td>
					
					<!-- 修改账户部分 -->
					<td><a href="javascript:;"
						onclick="system_category_edit1('修改管理员账户','<%=path %>/ar?p0=A-boss-mod&p1=admin_mod&p2=${map['id'] }&p3=0','600','600')"
						style="text-decoration: none;color:blue;" class="ml-5">修改账户</a>
						
						
						
						
						<c:choose>
							<c:when test="${map['job']=='总管理' }"></c:when>
							<c:when test="${map['job']!='总管理' }">
								<a href="javascript:;"
									onclick="go(${map['id']})"
									style="text-decoration: none;color:blue;" class="ml-5">删除账户</a>
							</c:when>
						</c:choose>
						
						
					</td>
				</tr>
			</c:forEach>
			
		</tbody>
	</table>
	<div class="dataTables_info" id="DataTables_Table_0_info" role="status" aria-live="polite">显示 <span id="pagefirst">${pageNo[2]+1}</span> 到 <span id="pagelast">${pageNo[3]}</span> ，共 <span id="total">${pageNo[1]}</span>条</div>
	
	<div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate">
		<a class="paginate_button previous disabled" aria-controls="DataTables_Table_0" data-dt-idx="0" tabindex="0" id="DataTables_Table_0_previous">上一页</a>
		<span>
			<a class="paginate_button current" aria-controls="DataTables_Table_0" data-dt-idx="1" tabindex="0">   
				<span id="currentpage">${pageNo[4]}</span>
			</a>
		</span>
		<a class="paginate_button next disabled" aria-controls="DataTables_Table_0" data-dt-idx="2" tabindex="0" id="DataTables_Table_0_next">下一页</a>
	</div>
	
	</div>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path%>/boss/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=path%>/boss/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path%>/boss/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=path%>/boss/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path%>/boss/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<!-- <script type="text/javascript" src="<%=path%>1/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="<%=path%>/static/lib/jquery.validation/1.14.0/validate-methods.js"></script>  -->
<script type="text/javascript" src="<%=path%>/boss/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">

var totalpage = Number('${pageNo[0]}');
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
	
	//搜索符合条件的所有的记录显示出来，用ajax
	$("#searchbtn").click(function(){
		/* var startdate = $("#datemin").val();
		var enddate = $("#datemax").val(); */
		var sele_condition = $("#searchname").val();
		var mil_id = $("#searchtxt").val();
		/* 1:获取输入的性别 */
		/* 2:获取输入的起始时间和结束时间 */
		/* 固定死判断条件，如性别，时间段：开始时间---结束时间 */
		//2种搜索条件,第一种满足就用第一种刷新；第二种满足就用第二种刷新
		if(mil_id != ''){
			fresh_page(1);
		}else if(sele_condition != ''){
			fresh_page(1);
		}
		else if(sele_condition==''){
			fresh_page(1);
		} 
	});
});

<%-- +'<td><a href="javascript:;" onclick="system_category_edit1(\'修改管理员账户\',\'<%=path %>/ar?p0=A-boss-mod&p1=admin_mod&p2='+json[i].id+'&p3=0\',\'600\',\'600\')" style="text-decoration: none;color:blue;" class="ml-5">修改账户</a> <a href="javascript:;" onclick="go('+json[i].id+')" style="text-decoration: none;color:blue;" class="ml-5">删除账户</a></td>' --%>

/*刷新列表*/
function fresh_page(pageIndex){
	
	$.ajax({
		cache: true,
		type: "POST",
		url: "<%=path%>/ar?p0=A-boss-search&p1=admin_list&p2="+pageIndex+"&p4=&p3=tojson" ,
		async: true,
		error: function(request) {
			alert("提交失败 ");
		},
		success: function(data){
			var json=eval("("+data+")");
			var content = '';
			var power="";
			var admin = '';
			for(var i = 0; i < json.length-1; i++) {
				if(json[i].job=='总管理'){
					admin='<a href="javascript:;" onclick="system_category_edit1(\'修改管理员账户\',\'<%=path %>/ar?p0=A-boss-mod&p1=admin_mod&p2='+json[i].id+'&p3=0\',\'600\',\'600\')" style="text-decoration: none;color:blue;" class="ml-5">修改账户</a>';
				}else{
					admin='<a href="javascript:;" onclick="system_category_edit1(\'修改管理员账户\',\'<%=path %>/ar?p0=A-boss-mod&p1=admin_mod&p2='+json[i].id+'&p3=0\',\'600\',\'600\')" style="text-decoration: none;color:blue;" class="ml-5">修改账户</a> <a href="javascript:;" onclick="go('+json[i].id+')" style="text-decoration: none;color:blue;" class="ml-5">删除账户</a>';
				}
				 content +='<tr class="text-c">'
					+'<td>'+(Number(json[json.length-1].current)+1+i)+'</td>'
					+'<td>'+json[i].id+'</td>'
					+'<td>'+json[i].username+'</td>'
					/* +'<td>'+json[i].password+'</td>' */
					+'<td><a href="javascript:;" onclick="system_category_edit(\'管理员密码\',\'<%=path %>/ar?p0=A-boss-search&p1=admin_password&p2='+json[i].id+'&p3=0\',\'600\',\'600\')"  ><span style="color:blue;">点击查看管理员密码</span></a></td>'
					+'<td>'+json[i].job+'</td>'
					+'<td><a href="javascript:;" onclick="system_category_edit1(\'修改权限\',\'<%=path %>/ar?p0=A-boss-search&p1=admin_quanxian&p2='+json[i].id+'&p3=0\',\'600\',\'600\')" style="text-decoration: none;color:blue;" class="ml-5">修改权限</a></td>'
					
					+'<td>'+admin+'</td>'
					
					
					;
					
			}
			
			
			$("#list-content").html(content);
			totalpage = Number(json[json.length-1].totlePage);
			$("#pagefirst").html(Number(json[json.length-1].current)+1);
			$("#pagelast").html(json[json.length-1].lastcount);
			$("#total").html(json[json.length-1].totle);
			$("#currentpage").html(json[json.length-1].pagenum);
		},
	});
}

function system_category_edit1(title,url,w,h){
	layer_show(title,url,w,h);
}

function system_category_edit(title,url,w,h){
	layer_show(title,url,w,h);
}

function go(id){
	/* age = prompt("请输入未通过原因","");
	layer.confirm('确认输入以上内容？',function(index){ */
		/* alert("删除"); */
		layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type:'POST',
			url: '<%=path%>/ar?p0=A-boss-mod&p1=admin_del&p2='+id,
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

/**查看用户相册*/
function client_xiangce(title,url,id,w,h){
	layer_show(title,url,w,h);	
}
/**查看用户相册*/
function client_shipin(title,url,id,w,h){
	layer_show(title,url,w,h);	
}
</script> 
</body>
</html>