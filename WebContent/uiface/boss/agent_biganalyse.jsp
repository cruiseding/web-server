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
<title>渠道商报表</title>
</head>
<body>
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 
	<span class="c-gray en">&gt;</span> 渠道商管理
	<span class="c-gray en">&gt;</span> 渠道商报表
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
		<i class="Hui-iconfont">&#xe68f;</i>
	</a>
</nav>
<div class="page-container">
	<div class="text-c">
		<div class="mt-20">
			<div class="text-c">
	          <span>查询方式</span>
	          <select id="check1" name="check1" >
	            <option value="0" >时间段查询</option>
	            <option value="1" >按月查询</option>
	            <option value="2" >按年查询</option> 
	          </select>
	       </div>
			<div class="text-c" id="xx1">	
			<span>开始日期:</span><input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;" name="">
		    <span>结束日期:</span><input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;" name="">
			</div>
			<div class="text-c" id="xx2">	
			<input type="text" id="d243" onclick="WdatePicker({dateFmt:'yyyy-MM'})" class="input-text Wdate" style="width:120px;"  />
			</div>
			<div class="text-c" id="xx3">	
			<input type="text" id="d244" onclick="WdatePicker({dateFmt:'yyyy'})" class="input-text Wdate" style="width:120px;"  />
			</div>
		      <div class="text-c" style="margin-top:10px;">		
		      
		      <select id="check2" name="check2">
		      
	          </select>
		      	
				<button type="submit" class="btn btn-success radius" id="btn-search" name="search" onclick="seek()"><i class="Hui-iconfont"></i> 查询</button>
			</div> 
			
		</div>
	
	<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper ">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width=20>序号</th>
				<th width="20">时间</th>
				<th width="40">渠道商名称</th>
				<th width="40">渠道商编号</th>
				<th width="40">注册人数(个)</th>
				<th width="40">新充值人数(个)</th>
				<th width="40">新充值次数(次)</th>
				<th width="40">新充值金额(元)</th>
				<th width="40">充值人数(个)</th>
				<th width="40">充值次数(次)</th>
				<th width="40">充值金额(元)</th>
				<th width="40">提现成功金额(元)</th>
			</tr>
		</thead>
		<tbody id="list-content">
			<c:forEach var = "map" items="${reList }" varStatus="status">
				<tr class="text-c">
					<td>${status.count}</td>
					<td>${map['time_riqi']}</td>
					<td>${map['agent_name']}</td>
					<td>${map['agent_channelcode']}</td>
					<td>${map['xinzhucenum']}</td>
					<td>${map['xinchongzhirennum']}</td>
					<td>${map['xinchongzhinum']}</td>
					<td>${map['xinchongzhimoney']}</td>
					<td>${map['chongzhirennum']}</td>
					<td>${map['chongzhisumnum']}</td>
					<td>${map['chongzhisummoney']}</td>
					<td>${map['tixiansummoney']}</td>
					
					<%-- <td><a style="color: blue;" href="<%=path%>/agent?p0=A-boss-search&p1=pay_search&p2=1&p5=tojsp&p7=${map['id']}" >查看详情</a></td> --%>
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
	
	$("#xx2").hide();
	$("#xx3").hide();
	
	
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
	
	$("#searchbtn").click(function() {
		var startdate = $("#datemin").val();
		var enddate = $("#datemax").val();
		if(startdate=="" && enddate!=""){
			alert('请选择开始时间');
		}else if(enddate=="" && startdate!=""){
			alert('请选择结束时间时间');
		}
		fresh_page(1);
	});
	
	$("#check1").on("change",function(){
		//alert('执行');
		if ($("option:selected",this).val()==2) {
			//alert('执行1');
		    //var a=$("#xx1").html();
			//alert(a);
			$("#xx1").hide();
			$("#xx2").hide();
			$("#xx3").show();
			//pp="年";
		}else if($("option:selected",this).val() == '1'){
			//alert('执行2');
			//var c='<input type="text" id="d243" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})" class="Wdate"/>';
			//$("#xx1").html();
			$("#xx1").hide();
			$("#xx2").show();
			$("#xx3").hide();
			//pp=$("#d243").val();
		}else{
			//alert('执行3');
			$("#xx1").show();
			$("#xx2").hide();
			$("#xx3").hide();
		}
	});
	
	
	$.ajax({
		type: 'POST',
		url:'<%=path%>/ar?p0=A-boss-search&p1=selagentlist',
		success: function(data){
			
			$("#check2").append("<option value='0'>渠道商名称</option>");
			
			var json=eval("("+data+")");
			for(var i = 0; i < json.length; i++) {
				$("#check2").append("<option value='"+json[i].agent_channelcode+"'>"+json[i].agent_name+"</option>");
			}
		},
		error:function(data) {
			alert('提交失败');
		},
	});	
	
	
});

/*刷新列表*/
function fresh_page(pageIndex){
	
	
	var pp="";
	var startdate = $("#datemin").val();
	var enddate = $("#datemax").val();
	if($("option:selected","#check1").val() == '1'){
		pp=$("#d243").val();
		startdate="";
		enddate="";
	}else if($("option:selected","#check1").val() == '2'){
		pp=$("#d244").val();
		startdate="";
		enddate="";
	}else{
		startdate=$("#datemin").val();
		enddate=$("#datemax").val();
	}
	
	var agentcode=$("option:selected","#check2").val();
	
	
	$.ajax({
		cache: true,
		type: "POST",
		url: "<%=path%>/ar?p0=A-boss-search&p1=agent_analyse&p2="+pageIndex+"&p3="+startdate+"&p4="+enddate+"&p5=tojson&p6="+pp+"&p7="+agentcode ,
		async: true,
		error: function(request) {
			alert("提交失败 ");
		},
		success: function(data){
			var json=eval("("+data+")");
			var content = '';
			
			for(var i = 0; i < json.length-1; i++) {
			   
				 content +='<tr class="text-c">'
					+'<td>'+(Number(json[json.length-1].current)+1+i)+'</td>'
					+'<td>'+json[i].time_riqi+'</td>'
					+'<td>'+json[i].agent_name+'</td>'
					+'<td>'+json[i].agent_channelcode+'</td>'
					+'<td>'+json[i].xinzhucenum+'</td>' 
					+'<td>'+json[i].xinchongzhirennum+'</td>'
					+'<td>'+json[i].xinchongzhinum+'</td>'
					+'<td>'+json[i].xinchongzhimoney+'</td>'
					+'<td>'+json[i].chongzhirennum+'</td>'
					+'<td>'+json[i].chongzhisumnum+'</td>' 
					+'<td>'+json[i].chongzhisummoney+'</td>'
					+'<td>'+json[i].tixiansummoney+'</td>'
					/*+'<td><a  style="color: blue;" href="http://116.62.220.67:8091/uiface/agent?p0=A-boss-search&p1=pay_search&p2=1&p5=tojsp&p7='+json[i].id+'" >查看详情</a></td>'
					 +'<td class="td-manage">'
					+shezhi1+'</td>' */; 
					
					
					
			}
			$("#list-content").html(content);
			totalpage = Number(json[json.length-1].totlePage);
			$("#pagefirst").html(Number(json[json.length-1].current)+1);
			$("#pagelast").html(json[json.length-1].lastcount);
			$("#total").html(json[json.length-1].totle);
			$("#currentpage").html(json[json.length-1].pagenum);
			//$("#sum1").html("总人数:"+json[json.length-1].totle+"人");
		},
	});
}

function seek(){
	fresh_page(1);
}

function system_category_edit1(id,userid){
	//layer_show(title,url,w,h);
	
	$.ajax({
		type: 'POST',
		url:'<%=path%>/ar?p0=A-boss-mod&p1=zhubo_pass&p2='+id,
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
	/* age = prompt("请输入未通过原因","");
	layer.confirm('确认输入以上内容？',function(index){ */

		$.ajax({
			type:'POST',
			url: '<%=path%>/ar?p0=A-boss-mod&p1=zhubo_nopass&p2='+id,
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
</script> 
</body>
</html>