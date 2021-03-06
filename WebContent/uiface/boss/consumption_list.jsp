<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

<link rel="stylesheet" type="text/css" href="<%=path%>/boss/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/static/h-ui.admin/css/style.css" />

<title>主播收入提现</title>
</head>
<body>
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 
	<span class="c-gray en">&gt;</span> 财务管理
	<span class="c-gray en">&gt;</span> 支出明细
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
		<i class="Hui-iconfont">&#xe68f;</i>
	</a>
</nav>
<div class="page-container">
	  <div class="mt-20">
			<!-- <div class="text-c">	
			                日期范围：
				<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;" name="">
				
				- <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;" name="">
				<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<button type="submit" class="btn btn-success radius" id="searchbtn" name=""><i class="Hui-iconfont"></i>搜用户</button>
			</div>	 -->
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
		      <input type="text" class="input-text" style="width: 150px;" placeholder="请输入用户ID号" id="searchtxt" name="searchtext">	
		      <input type="text" class="input-text" style="width: 150px;" placeholder="请输入用户昵称" id="searchtxtnc" name="searchtextnc">	
		      
		      <select class="input-text" style="width:150px" id="consumption_type" name="">
					<option value="">支出类型</option>
					<option value="新注册提成">新注册提成</option>
					<option value="查看qq">查看qq</option>
					<option value="查看微信">查看微信</option>
					<option value="查看手机">查看手机</option>
					<option value="礼物打赏">礼物打赏</option>
					<option value="视频通话">视频通话</option>
					<option value="语音通话">语音通话</option>
					<option value="图片收入">图片收入</option>
					<option value="分享收入">分享收入</option>
				</select> 
		      
		      
				<button type="submit" class="btn btn-success radius" id="btn-search" name="search" onclick="seek()"><i class="Hui-iconfont"></i> 查询</button>
				<button type="submit" class="btn btn-success radius" id="btn-search2" name="search2" onclick="seek2()"><i class="Hui-iconfont"></i> 导出</button>
			</div> 
			<span id="sum1">总支出（趣豆）:${reList[0].sum}</span>
		</div>
		
	<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper ">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<tr class="text-c">
				<th width="80">序号</th>
				<th width="80">用户id</th>
				<th width="80">用户昵称</th>
				<th width="80">支出类型</th>
				<th width="80">支出数量</th>
				<th width="80">收入者ID</th>
				<th width="80">收入者昵称</th>
                <th width="80">时间</th>
			</tr>
			</tr>
		</thead>
		<tbody id="list-content" >
		<c:set var="nodeValue" scope="page" value="0"/>
		<c:forEach var="map" items="${reList}" varStatus="status">
			<tr class="text-c">
			<!-- 序号、用户ID、用户昵称、支出类型、支出数量、收入者ID、收入者昵称、时间 -->
				<td>${status.count}</td>			
				<td>${map['u_user_id']}</td>
				<td>${map['u_nickname']}</td>
				<td>${map['type']}</td>
				<td> <fmt:formatNumber type="number" value="${map['num']}" pattern="0.00" maxFractionDigits="2"/>(趣豆)</td>
				<td>${map['z_user_id']}</td>
				<%-- <td>${map['z_id']}</td> --%>
				<td>${map['z_nickname']}</td>
				<td>${map['time']}</td>
				
			</tr>
			<c:set var="nodeValue" scope="page" value="${nodeValue+map['num']}"/>
		</c:forEach>
		
		<tr>
		<td colspan="8">当页支出（趣豆）:${nodeValue}</td>
		</tr>
		</tbody>
	</table>
	
	<%-- <a title="编辑" href="javascript:;" 
						onclick="client_edit('编辑','<%=path%>/room?p0=A-boss-search&p1=room_id_search&p2=${map['id']}','','','510')" 
						class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> --%>
	
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
	<div class="dataTables_paginate paging_simple_numbers">
		<span>跳转到</span>
		<span>
			<input id="dirpage" type="text" class="input-text" style="width: 50px;"/>
		</span>
		<a id="pagejump" onClick="jumpdirpage()" class="paginate_button next disabled">跳转</a>
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
	
});

<%-- <td><a title="个人提现明细" href="javascript:;" onclick="client_geren('个人提现明细','<%=path%>/TCQOServlet?/p0=A-boss-search&p1=cashwithdrawal&p2=1&p3=${map['user_id']}&p4=tojsp','','','510')" style="color:blue">个人提现明细</a></td> --%>

function fresh_page(pageIndex) {
	var pp="";
	var startdate = $("#datemin").val();
	var enddate = $("#datemax").val();
	if(startdate=="" && enddate!=""){
		alert('请选择开始时间');
		return;
	}else if(enddate=="" && startdate!=""){
		alert('请选择结束时间');
		return;
	}
	if($("#check1").val() == '1'){
		pp=$("#d243").val();
		startdate="";
		enddate="";
	}else if($("#check1").val() == '2'){
		pp=$("#d244").val();
		startdate="";
		enddate="";
	}else{
		startdate=$("#datemin").val();
		enddate=$("#datemax").val();
	}
	
	//consumption_type
	var consumption_type=$("option:selected","#consumption_type").val();
	
	var searchtext = $("#searchtxt").val();
	var searchtxtnc = $("#searchtxtnc").val();
	
	$.ajax({
		cache: true,
		type: "POST",
		//p2开始时间 p3当前页数 p4结束时间 p5 会员 p6积分
		/* /rz?p0=A-boss-search&p1=cash_withdrawal&p2=1&p3=&p4=&p5=tojsp */
		url:"<%=path%>/ar?p0=A-boss-search&p1=consumptionlist&p2="+pageIndex+"&p3=tojson&p4=&p5="+searchtext+"&p6="+searchtxtnc+"&p7="+startdate+"&p8="+enddate+"&p9="+pp+"&p10="+consumption_type,
		async: true,
		error: function(request) {
			alert("提交失败 ");
		},
		success: function(data) {
			var json=eval("("+data+")");
			var content = '';
			var snum = 0;
			for(var i = 0; i < json.length-1; i++) {
				
				
				content +='<tr class="text-c">'
				+'<td>'+(Number(json[json.length-1].current)+1+i)+'</td>'
				+'<td>'+json[i].u_user_id+'</td>'
				+'<td>'+json[i].u_nickname+'</td>'
				+'<td>'+json[i].type+'</td>'
				+'<td>'+Number(json[i].num).toFixed(2)+'(趣豆)</td>'
				+'<td>'+json[i].z_user_id+'</td>'
				+'<td>'+json[i].z_nickname+'</td>'
				+'<td>'+json[i].time+'</td>'
				
				
				+'</tr>';
				
				var a = Number(json[i].num);
				snum = snum+a;
			}
			content +='<tr>'
					+'<td  colspan="8"> 当页支出（趣豆）:'+snum+'</td>'
					+'</tr>'; 
			$("#list-content").html(content);
			totalpage = Number(json[json.length-1].totlePage);
			$("#pagefirst").html(Number(json[json.length-1].current)+1);
			$("#pagelast").html(json[json.length-1].lastcount);
			$("#total").html(json[json.length-1].totle);
			$("#currentpage").html(json[json.length-1].pagenum);
			$("#sum1").html("总收入（趣豆）"+json[0].sum);
			
		}
	});
}

function response_money(obj,id){
	layer.confirm('确认用户已收款？',function(index){

		$.ajax({
			type: 'POST',
			url: '<%=path%>/rp?p0=A-boss-mod&p1=response_money&p2='+id,
			success: function(data){
				/*$(obj).parents("tr").remove();*/
				layer.msg(data,{icon:1,time:1000});
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
function seek(){
	fresh_page(1);
}
function seek2(){
	var pp="";
	var startdate = $("#datemin").val();
	var enddate = $("#datemax").val();
	if(startdate=="" && enddate!=""){
		alert('请选择开始时间');
		return;
	}else if(enddate=="" && startdate!=""){
		alert('请选择结束时间');
		return;
	}
	if($("#check1").val() == '1'){
		pp=$("#d243").val();
		startdate="";
		enddate="";
	}else if($("#check1").val() == '2'){
		pp=$("#d244").val();
		startdate="";
		enddate="";
	}else{
		startdate=$("#datemin").val();
		enddate=$("#datemax").val();
	}
	
	//consumption_type
	var consumption_type=$("option:selected","#consumption_type").val();
	
	var searchtext = $("#searchtxt").val();
	var searchtxtnc = $("#searchtxtnc").val();
	layer.confirm('确认要导出数据吗？', function(index) {
		window.open("<%=path%>/ar?p0=A-boss-search&p1=consumptionlist2&p2=1&p3=tojson&p4=&p5="+searchtext+"&p6="+searchtxtnc+"&p7="+startdate+"&p8="+enddate+"&p9="+pp+"&p10="+consumption_type);
		layer.closeAll('dialog');
	});
		 
};
function client_geren(title,url,id,w,h){
	layer_show(title,url,w,h);
}

function jumpdirpage() {
	var destIndex = $("#dirpage").val();
	if(Number(destIndex) > totalpage) {
		alert('最多 '+totalpage+' 页');
		return;
	}
	
	fresh_page(destIndex);
}
</script>
</body>
</html>