<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath()+"/uiface";
response.setHeader("refresh", "3");

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
<title>私信屏蔽关键词</title>
</head>
<body>
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 
	<span class="c-gray en">&gt;</span>	首页
	<!-- <span class="c-gray en">&gt;</span> 私信屏蔽关键词 -->
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
		<i class="Hui-iconfont">&#xe68f;</i>
	</a>
</nav>
<!-- <div class="page-container"> -->
<!-- <div class="cl pd-5 bg-1 bk-gray mt-20">
		     <a href="javascript:;" onclick="go()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>添加关键词</a> 
		     
		     
		</div> -->
	<!-- <div class="text-c"> -->

<div class="mt-20">
	<div style="float: left;width: 20%;margin:50px;height: 120px;">
            <table class="table-detail" cellpadding="10px" cellspacing="1" border="1" type="main" width="20%" bordercolor="#D3D7D9">    
            	<c:forEach var="map" items="${list0}" varStatus="status">
	                <tr height="40">
	                    <td style="text-align: left;font-size:16px;border-width: 1px;width: 10%;background:#F9F9FB">&nbsp;&nbsp;会员统计</td>
	                </tr>
	               	
	                <tr height="40">
	                    <td style="text-align: left;font-size:15px;border-width: 1px;width: 10%">&nbsp;&nbsp;注册数量：<span style="color:red">${map['register_number'] }</span></td>
	                </tr>
	                <tr height="40">
	                    <td style="text-align: left;font-size:15px;border-width: 1px;width: 10%">&nbsp;&nbsp;主播数量：<span style="color:red">${map['goddess_number'] }</span></td>
	                </tr>
	                <tr height="40">
	                    <td style="text-align: left;font-size:15px;border-width: 1px;width: 10%">&nbsp;&nbsp;正在直播的主播：<span style="color:red">${map['online4_number'] }</span></td>
	                </tr>
	                <tr height="40">
	                    <td style="text-align: left;font-size:15px;border-width: 1px;width: 10%">&nbsp;&nbsp;</td>
	                </tr>
	             </c:forEach>
            </table>
            
     </div>
     <div style="float: left;width: 20%;margin:50px;height: 120px;">
            <table class="table-detail" cellpadding="10px" cellspacing="1" border="1" type="main" width="20%" bordercolor="#D3D7D9">   
            	
	                <tr height="40">
	                    <td style="text-align: left;font-size:16px;border-width: 1px;width: 10%;background:#F9F9FB">&nbsp;&nbsp;充值数据</td>
	                </tr>
	               	<c:forEach var="map" items="${list1 }" varStatus="status" end='0'>
	                <tr height="40">
	                    <td style="text-align: left;font-size:15px;border-width: 1px;width: 10%">&nbsp;&nbsp;今日线上充值金额：<span style="color:red"><c:if test="${list1[0]['order_price']==null }">0.00</c:if><c:if test="${list1[0]['order_price']!=null }">${list1[0]['order_price']}</c:if></span>共<span style="color:red">${list1[0]['order_number'] }</span>单</td>
	                </tr>
	                <tr height="40">
	                    <td style="text-align: left;font-size:15px;border-width: 1px;width: 10%">&nbsp;&nbsp;今日手动充值点数：<span style="color:red"><c:if test="${list1[1]['recharge_num']==null }">0.00</c:if><c:if test="${list1[1]['recharge_num']!=null }">${list1[1]['recharge_num']}</c:if></span>共<span style="color:red">${list1[1]['recharge_number'] }</span>单</td>
	                </tr>
	                <tr height="40">
	                    <td style="text-align: left;font-size:15px;border-width: 1px;width: 10%">&nbsp;&nbsp;</td>
	                </tr>
	                <tr height="40">
	                    <td style="text-align: left;font-size:15px;border-width: 1px;width: 10%">&nbsp;&nbsp;</td>
	                </tr>
	                </c:forEach>
	             
            </table>
            
     </div>
     <div style="float: left;width: 20%;margin:50px;height: 120px;">
            <table class="table-detail" cellpadding="10px" cellspacing="1" border="1" type="main" width="20%" bordercolor="#D3D7D9">   
            	<c:forEach var="map" items="${list2 }" varStatus="status">
	                <tr height="40">
	                    <td style="text-align: left;font-size:16px;border-width: 1px;width: 10%;background:#F9F9FB">&nbsp;&nbsp;充值来源</td>
	                </tr>
	               	
	                <tr height="40">
	                    <td style="text-align: left;font-size:15px;border-width: 1px;width: 10%">&nbsp;&nbsp;支付宝：<span style="color:red">${map['alipay_price'] }</span></td>
	                </tr>
	                <tr height="40">
	                    <td style="text-align: left;font-size:15px;border-width: 1px;width: 10%">&nbsp;&nbsp;微信：<span style="color:red">${map['wechat_price'] }</span></td>
	                </tr>
	                <tr height="40">
	                    <td style="text-align: left;font-size:15px;border-width: 1px;width: 10%">&nbsp;&nbsp;苹果支付：<span style="color:red">${map['iphone_price'] }</span></td>
	                </tr>
	                <tr height="40">
	                   <td style="text-align: left;font-size:15px;border-width: 1px;width: 10%">&nbsp;&nbsp;<span style="color:red"></span></td>
	                </tr>
                </c:forEach> 
            </table>
     </div>
</div>

<div class="mt-20">
	<div style="float: left;width: 20%;margin:50px;height: 190px;">
            <table class="table-detail" cellpadding="10px" cellspacing="1" border="1" type="main" width="20%" bordercolor="#D3D7D9">    
            	<c:forEach var="map" items="${list3 }" varStatus="status">
	                <tr height="40">
	                    <td style="text-align: left;font-size:16px;border-width: 1px;width: 10%;background:#F9F9FB">&nbsp;&nbsp;主播审核</td>
	                </tr>
	               	
	                <tr height="40">
	                    <td style="text-align: left;font-size:15px;border-width: 1px;width: 10%">&nbsp;&nbsp;待审核的主播：<span style="color:red">${map['upzhu_notyet'] }</span></td>
	                </tr>
	                <tr height="40">
	                   <td style="text-align: left;font-size:15px;border-width: 1px;width: 10%">&nbsp;&nbsp;</td>
	                </tr>
	                <tr height="40">
	                    <td style="text-align: left;font-size:15px;border-width: 1px;width: 10%">&nbsp;&nbsp;</td>
	                </tr>
	                <tr height="40">
	                    <td style="text-align: left;font-size:15px;border-width: 1px;width: 10%">&nbsp;&nbsp;</td>
	                </tr>
                </c:forEach>
            </table>
            
     </div>
     <div style="float: left;width: 20%;margin:50px;height: 190px;">
            <table class="table-detail" cellpadding="10px" cellspacing="1" border="1" type="main" width="20%" bordercolor="#D3D7D9"> 
            	<c:forEach var="map" items="${list4 }" varStatus="status">
	                <tr height="40">
	                    <td style="text-align: left;font-size:16px;border-width: 1px;width: 10%;background:#F9F9FB">&nbsp;&nbsp;提现数据</td>
	                </tr>
	               	
	                <tr height="40">
	                    <td style="text-align: left;font-size:15px;border-width: 1px;width: 10%">&nbsp;&nbsp;提现金额：<span style="color:red">${map['cash_price'] }</span></td>
	                </tr>
	                <tr height="40">
	                    <td style="text-align: left;font-size:15px;border-width: 1px;width: 10%">&nbsp;&nbsp;已成功提现金额：<span style="color:red">${map['cash_success'] }</span></td>
	                </tr>
	                <tr height="40">
	                    <td style="text-align: left;font-size:15px;border-width: 1px;width: 10%">&nbsp;&nbsp;待处理提现主播：<span style="color:red">${map['cash_upzhu_notyet'] }</span></td>
	                </tr>
	                <tr height="40">
	                    <td style="text-align: left;font-size:15px;border-width: 1px;width: 10%">&nbsp;&nbsp;待处理提现金额：<span style="color:red">${map['cash_notyet'] }</span></td>
	                </tr>
                </c:forEach>   	
            </table>
     </div>
     <div style="float: left;width: 20%;margin:50px;height: 190px;">
            <table class="table-detail" cellpadding="10px" cellspacing="1" border="1" type="main" width="20%" bordercolor="#D3D7D9">  
            	<tr height="40">
	                <td style="text-align: left;font-size:16px;border-width: 1px;width: 10%;;border:0;background:#F9F9FB">&nbsp;&nbsp;热门主播</td>
	            </tr>  
            	<c:forEach var="map" items="${list5 }" varStatus="status">
	                
	                
	                <tr height="52">
	                    <td style="text-align: left;font-size:15px;width: 10%;border:0">&nbsp;&nbsp;<img style="width:35px;height:35px;border-radius: 45px;" src="${map['user_photo'] }"/>&nbsp;&nbsp;<span>${map['nickname'] }</span></td>
	                </tr>
	                
	                <%-- <tr height="53">
	                	<td style="text-align: left;font-size:15px;width: 10%;border:0">&nbsp;&nbsp;<img style="width:35px;height:35px;border-radius: 45px;" src="${map['user_photo'] }" />&nbsp;&nbsp;<span>${map['nickname'] }</span></td>
	                </tr>
	                <tr height="53">
	                	<td style="text-align: left;font-size:15px;width: 10%;border:0">&nbsp;&nbsp;<img style="width:35px;height:35px;border-radius: 45px;" src="${map['user_photo'] }"/>&nbsp;&nbsp;<span>${map['nickname'] }</span></td>
	                </tr> --%>
                </c:forEach>
            </table>
            
     </div>
</div>
	<%-- <div id="DataTables_Table_0_wrapper" class="dataTables_wrapper ">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width=20>序号</th>
				<th width="20">ID</th>
				<th width="40">关键词</th>
				<th width="40">操作</th>
			</tr>
		</thead>
		<tbody id="list-content">
			<c:forEach var = "map" items="${reList }" varStatus="status">
				<tr class="text-c">
					<td>${status.count}</td>
					<td>${map['id']}</td>
					<td>${map['content']}</td>
					<td><a title="删除" href="javascript:;" onclick="client_del(${map['id']})" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
				</tr>
			</c:forEach>
			
		</tbody>
	</table>
	
	</div> --%>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path%>/boss/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=path%>/boss/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path%>/boss/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=path%>/boss/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path%>/boss/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<!-- <script type="text/javascript" src="<%=path%>1/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="<%=path%>1/static/lib/jquery.validation/1.14.0/validate-methods.js"></script>  -->
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

/*刷新列表*/
function fresh_page(pageIndex){
	$.ajax({
		cache: true,
		type: "POST",
		url: "<%=path%>/rp?p0=A-boss-search&p1=automsg_search&p2="+pageIndex+"&p4=&p3=tojson" ,
		async: true,
		error: function(request) {
			alert("提交失败 ");
		},
		success: function(data){
			var json=eval("("+data+")");
			var content = '';
			var z='';
			
			for(var i = 0; i < json.length-1; i++) {
				
				 content +='<tr class="text-c">'
					+'<td>'+(Number(json[json.length-1].current)+1+i)+'</td>'
					+'<td>'+json[i].id+'</td>'
					+'<td>'+json[i].msg+'</td>'
					+'<td><a title="删除" href="javascript:;" onclick="client_del('+json[i].id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>'
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

function system_category_edit1(title,url,id,w,h){
	layer_show(title,url,w,h);
	
	
}

function go(){
	//age = prompt("请输入要添加的关键词","");
	var age = window.prompt("请输入要添加的关键词：", "");
	if(age==""){
		alert("关键词不能为空");
	}
	if(age){
		
	layer.confirm('确认输入以上内容？',function(index){ 
	
		$.ajax({
			type:'POST',
			url: '<%=path%>/ar?p0=A-boss-add&p1=addchatfilter&p2='+age,
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
	
	}else{
		return;
	}
	
}
function proj_edit(){
	age = prompt("请输入主播打招呼个数","");
	layer.confirm('确认输入以上内容？',function(index){ 
	
		$.ajax({
			type:'POST',
			url: '<%=path%>/rp?p0=A-boss-mod&p1=automsg_num&p2='+age,
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
function client_del(id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '<%=path%>/ar?mode1=A-boss-delete&mode2=delchatfilter&p2='+id,
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