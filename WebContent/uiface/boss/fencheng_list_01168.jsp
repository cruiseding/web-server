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
<title>会员管理</title>
</head>
<body>
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 
	<span class="c-gray en">&gt;</span> 分成管理 
	<span class="c-gray en">&gt;</span> 分成列表
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
		<i class="Hui-iconfont">&#xe68f;</i>
	</a>
</nav>
<div class="page-container">
	<div class="text-c">
		<div class="mt-20">
			<div class="text-c">
					ID：
				<input type="text" class="input-text" style="width:250px"  placeholder="请输用户的ID号" id="searchnum" name="searchnum">	
					昵称：
				<input type="text" class="input-text" style="width:250px"  placeholder="请输用户的昵称" id="searchtxt" name="searchtext">	
				
				<input type="text" class="input-text" style="width:250px"  placeholder="请输推荐人的ID" id="tjr_id" name="tjr_id">
				<span>用户性别:</span>
				<select class="input-text" style="width:150px" id="gender" name="gender">
					<option></option>
					<option value="男">男</option>
					<option value="女">女</option>
				</select>
				<button type="submit" class="btn btn-success radius" id="searchbtn" name=""><i class="Hui-iconfont"></i>搜用户</button>
			</div>	
		</div>
		
	<div class="mt-20">
	<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper ">
	<!-- <span>注:财富记录为男用户充值记录，魅力记录为女用户收礼记录</span> -->
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
		
			<tr class="text-c">
				<th width=20>序号</th>
				<th width="20">用户ID</th>
				<th width="40">用户昵称</th>
				<th width="40">用户联系方式</th>
				<th width="40">用户性别</th>
				<th width="40">推荐人ID</th>
				<th width="40">推荐人昵称</th>
				<th width="40">推荐人联系方式</th>
				<th width="40">一级下属人数</th>
				
				
			</tr>
		</thead>
		<tbody id="list-content">
			<c:forEach var = "map" items="${reList }" varStatus="status">
				<tr class="text-c">
				<!-- 字段：序号、ID、头像、用户名、昵称、性别、年龄、身高、体重、城市、微信、微博、qq、支付宝、账户余额、操作(封禁、解封) -->
					<td>${status.count}</td>
					<td>${map['u_user_id']}</td>
					<td>${map['nickname1']}</td>
					<td>${map['phone']}</td>		
					<td>${map['gender']}</td>			
					<td>${map['promoter_id']}</td>			
					<td>${map['nicknames']}</td>
					<td>${map['phones']}</td>
					
					<td>
					<a href="<%=path%>/ar?p0=A-boss-search&p1=fencheng_yiji&p2=1&p3=tojsp&p4=${map['id']}"
                                   target="_self" data-title="一级下属人数" href="javascript:void(0)"
                                   style="color: #0e90d2">${map['yiji']}</a>
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
		var mil_num = $("#searchnum").val();
		var mil_txt = $("#searchtxt").val();
		/* 1:获取输入的性别 */
		/* 2:获取输入的起始时间和结束时间 */
		/* 固定死判断条件，如性别，时间段：开始时间---结束时间 */
		//2种搜索条件,第一种满足就用第一种刷新；第二种满足就用第二种刷新
		/* alert("提交失败 "); */
		fresh_page(1);
		
	});
});

/*刷新列表*/
function fresh_page(pageIndex){
	/* var mil_id = $("#searchtxt").val(); */
		var mil_num = $("#searchnum").val();
		var mil_txt = $("#searchtxt").val();
		
		var tjr_id = $("#tjr_id").val();
		var gender =$("option:selected","#gender").val();
		/* alert("进入搜索 "); */
	/* 获取输入的性别 */
	/* 获取输入的起始时间和结束时间 */
	/* 固定死判断条件，如性别，时间段：开始时间---结束时间 */
	<%-- url: "<%=path%>/rp?p0=A-boss-search&p1=memberbackstage&p2="+pageIndex+"&p3=&p4=&p5="+sele_condition+"&p6=tojson&p7="+mil_id , --%>
	<%-- url: "<%=path%>/rp?p0=A-boss-search&p1=ar&p2="+pageIndex+"&p3="+mil_id+"&p4="+sele_condition+"&p6=tojson , --%>
	$.ajax({
		cache: true,
		type: "POST",
		<%-- url: "<%=path%>/ar?p0=A-boss-search&p1=user_list_pt&p2="+pageIndex+"&p3=tojson&p4="+mil_id , --%>
		url: "<%=path%>/ar?p0=A-boss-search&p1=fenxiao_liebiao&p2="+pageIndex+"&p3=tojson&p4="+mil_num+"&p5="+mil_txt+"&p6="+tjr_id+"&p7="+gender,
		async: true,
		error: function(request) {
			alert("提交失败 ");
		},
		success: function(data){
			var json=eval("("+data+")");
			var content = '';
			var	yiji = '';
			
			for(var i = 0; i < json.length-1; i++) {
																		
			<%-- var	yiji = '<a data-href="<%=path%>/anran?p0=A-boss-search&p1=fencheng_yiji&p2=1&p3=tojsp&p4=' + json[i].id + '" data-title="一级下属人数" href="javascript:void(0)" style="color: #0e90d2">' + json[i].yiji + '</a>'; --%>
			<%-- yiji = '<td><a data-href="<%=path%>/anran?p0=A-boss-search&p1=fencheng_yiji&p2=1&p3=tojsp&p4='+json[i].id+'" data-title="一级下属人数" href="javascript:void(0)" style="color: #0e90d2">' + json[i].yiji + '</a></td>'; --%>
		
			yiji = '<td><a href="<%=path%>/ar?p0=A-boss-search&p1=fencheng_yiji&p2=1&p3=tojsp&p4='+json[i].id+'" target="_self" data-title="一级下属人数" href="javascript:void(0)" style="color: #0e90d2">' + json[i].yiji + '</a></td>';
			
			
			<%-- erji = '<a data-href="<%=path%>/anran?p0=A-boss-search&p1=fencheng_erji&p2=1&p3=tojsp&p4=' + json[i].id + '" data-title="二级下属人数" href="javascript:void(0)" style="color: #0e90d2">' + json[i].erji + '</a>';	
			sanji = '<a data-href="<%=path%>/anran?p0=A-boss-search&p1=fencheng_sanji&p2=1&p3=tojsp&p4=' + json[i].id + '" data-title="三级下属人数" href="javascript:void(0)" style="color: #0e90d2">' + json[i].sanji + '</a>'; --%>			<%-- y = '<td><a href="<%=path%>/anran?p0=A-boss-search&p1=xiangqing&p2='+json[i].id+'" target="_self" data-title="查看详情" href="javascript:void(0)" style="color: #0e90d2"><input  type="button" href="javascript:;" class="btn btn-warning radius" value="查看详情"></a></td>'; --%>
			
				 content +='<tr class="text-c">'
					+'<td>'+(Number(json[json.length-1].current)+1+i)+'</td>'
					+'<td>'+json[i].u_user_id+'</td>'
					+'<td>'+json[i].nickname1+'</td>'
					+'<td>'+json[i].phone+'</td>'
					+'<td>'+json[i].gender+'</td>'
					+'<td>'+json[i].promoter_id+'</td>'
					+'<td>'+json[i].nicknames+'</td>'
					+'<td>'+json[i].phones+'</td>'
					+yiji
					
					
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

<%-- function system_category_edit1(id,userid){
	//layer_show(title,url,w,h);
	
	$.ajax({
		type: 'POST',
		url:'<%=path%>/rp?p0=A-boss-mod&p1=zhubo_pass&p2='+id,
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
} --%>
function system_category_edit1(title,url,id,w,h){
	layer_show(title,url,w,h);
}

function go(id){
	/* age = prompt("请输入未通过原因","");
	layer.confirm('确认输入以上内容？',function(index){ */

		$.ajax({
			type:'POST',
			url: '<%=path%>/ar?p0=A-boss-del&p1=user_del&p2='+id,
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
	/* }); */
}




function banned(id){
	/* age = prompt("请输入未通过原因","");
	layer.confirm('确认输入以上内容？',function(index){ */
		/* alert('进入banned'); */
		
		$.ajax({
			type:'POST',
			url: '<%=path%>/anran?p0=A-boss-mod&p1=anchor_banned&p2='+id,
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
	/* }); */
}

function no_banned(id){
	/* age = prompt("请输入未通过原因","");
	layer.confirm('确认输入以上内容？',function(index){ */
		/* alert('进入no_banned'); */
		$.ajax({
			type:'POST',
			url: '<%=path%>/anran?p0=A-boss-mod&p1=banned_cancel&p2='+id,
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
	/* }); */
}


function go(id,userid){
	/* age = prompt("请输入未通过原因","");
	layer.confirm('确认输入以上内容？',function(index){ */

		$.ajax({
			type:'POST',
			url: '<%=path%>/anran?p0=A-boss-mod&p1=zhubo_nopass&p2='+id,
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
	/* }); */
}

/**查看用户相册*/
function client_xiangce(title,url,id,w,h){
	layer_show(title,url,w,h);	
}
/**查看用户相册*/
function client_shipin(title,url,id,w,h){
	layer_show(title,url,w,h);	
}
<%-- 
function banned(id){
	/* age = prompt("请输入未通过原因","");
	layer.confirm('确认输入以上内容？',function(index){ */

		$.ajax({
			type:'POST',
			url: '<%=path%>/ar?p0=A-boss-mod&p1=anchor_banned&p2='+id,
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
	/* }); */
}
function no_banned(id){
	/* age = prompt("请输入未通过原因","");
	layer.confirm('确认输入以上内容？',function(index){ */

		$.ajax({
			type:'POST',
			url: '<%=path%>/ar?p0=A-boss-mod&p1=banned_cancel&p2='+id,
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
	/* }); */
} --%>

function xiangqing(id){
	/* age = prompt("请输入未通过原因","");
	layer.confirm('确认输入以上内容？',function(index){ */

		$.ajax({
			type:'POST',
			url: '<%=path%>/anran?p0=A-boss-search&p1=xiangqing&p2='+id,
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
	/* }); */
}




function system_category_edit(title,url,w,h){
	layer_show(title,url,w,h);
}





</script> 
</body>
</html>