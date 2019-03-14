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

<title>充值记录表 </title>
</head>
<body>
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 
	<span class="c-gray en">&gt;</span> 财务管理
	<span class="c-gray en">&gt;</span> 礼物明细 
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
		<i class="Hui-iconfont">&#xe68f;</i>
	</a>
</nav>
<div class="page-container">
	<div class="text-c">	
		<div class="mt-20">
		    <input type="text" class="input-text" style="width: 100px;" placeholder="会员ID" id="searchtext1" name="searchtext1">
		    <input type="text" class="input-text" style="width: 100px;" placeholder="女神ID" id="searchtext2" name="searchtext2">
		    <!-- <input type="text" class="input-text" style="width: 100px;" placeholder="礼物ID" id="searchtext3" name="searchtext3"> -->
		    <!-- <input type="text" class="input-text" style="width: 150px;" placeholder="昵称" id="searchtxtnc" name="searchtxtnc"> -->
		    <!-- <input type="text" class="input-text" style="width: 150px;" placeholder="管理员" id="admins" name="admins"> -->
		    赠送时间
			<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;" name="">
			
			- <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;" name=""> 
			<!-- <input type="text" class="input-text" style="width: 150px;" placeholder="充值方式" id="fangshi" name="fangshi">	 -->
			
			<!-- <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
		    <select class="input-text" style="width:150px" id="fangshi" name="">
				<option value="">充值方式</option>
				<option value="微信">微信</option>
				<option value="支付宝">支付宝</option>
			</select> -->
			
			<!-- <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
		    <select class="input-text" style="width:150px" id="searchname" name="">
				<option value="">充值状态</option>
				<option value="1">充值成功</option>
				<option value="0">充值失败</option>
			</select>  -->
			<!-- <select id="check2" name="check2">
		      
	          </select> -->
	        
	      <!--   <input type="text" class="input-text" style="width: 180px;" placeholder="订单号" id="searchtxt1" name="searchtext1"> -->		    
	          
			<button type="submit" class="btn btn-success radius" id="searchbtn" name=""><i class="Hui-iconfont"></i>搜索礼物记录</button>
			<button type="submit" class="btn btn-success radius" id="searchbtn2" name=""><i class="Hui-iconfont"></i>导出</button>
		</div>
	</div>
	<div id="Tables_Table_0_wrapper" class="Tables_wrapper ">
	<p><%-- 总消费:${reList[0].sum} --%></p>
	<span id="sum1">礼物总额:${reList[0].sum}</span>
	<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper ">
	
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="80">序号</th>
				<th width="80">会员ID</th>
				<th width="80">会员昵称</th>
				<th width="80">女神ID</th>
				<th width="80">女神昵称</th>
				<th width="80">礼物名称</th>
				<th width="80">礼物价格</th>
			</tr>
		</thead>
		<tbody id="list-content" >
		<c:set var="nodeValue" scope="page" value="0"/>
		<%-- <c:set var="nodeValue" scope="page" value="${nodeValue+map['price']}"/> --%>
		<c:forEach var="map" items="${reList}" varStatus="status">
			<tr class="text-c">
			<!-- 序号、用户ID、用户昵称、充值方式、充值金额、爱豆数量、充值状态、充值时间、个人充值记录（个人充值记录：序号、用户ID、充值类型、充值金额、充值状态、时间） -->
				<td>${status.count}</td>
				<td>${map['user_id1']}</td>
				<td>${map['nickname1']}</td>
				<td>${map['user_id2']}</td>
				<td>${map['nickname2']}</td>
				<td>${map['name']}</td>
				<td>${map['price']}</td>
				
				
				
				
			</tr>
			<c:set var="nodeValue" scope="page" value="${nodeValue+map['recharge_num']}"/>
		</c:forEach>
		<%-- <tr>
		<td colspan="12">当页收入:${nodeValue}</td>
		</tr> --%>
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
<!-- <script type="text/javascript" src="<%=path%>/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
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
	$("#searchbtn").click(function() {
		//var startdate = $("#datemin").val();
		//var enddate = $("#datemax").val();
		//var searchname = $("#searchname").val();
			fresh_page(1);
	});
	$("#searchbtn2").click(function() {
		var startdate=$("#datemin").val();
		var enddate=$("#datemax").val();
		
		if(startdate=="" && enddate!=""){
			alert('请选择开始时间');
			return;
		}else if(enddate=="" && startdate!=""){
			alert('请选择结束时间');
			return;
		}
		
		
		var searchtext1=$("#searchtext1").val();
		var searchtext2=$("#searchtext2").val();
		layer.confirm('确认要导出数据吗？', function(index) {
			window.open("<%=path%>/ar?p0=A-boss-search&p1=gift_record2&p2="+startdate+"&p3=1&p4="+enddate+"&p5="+searchtext1+"&p6=tojson&p7="+searchtext2);
			layer.closeAll('dialog');
		});
		
	});
	/* $("#Vip").click(function() {
		$("#vi").html("aaa");
		$("#je").html("");
		fresh_page(1);
	});
	$("#jewel").click(function() {
		$("#je").html("aaa");
		$("#vi").html("");
		fresh_page(1);
	});
	$("#all").click(function() {
		$("#datemin").html("");
		$("#datemax").html("");
		$("#je").html("");
		$("#vi").html("");
		fresh_page(1);
	}); */
	
	<%-- $.ajax({
		type: 'POST',
		url:'<%=path%>/agent?p0=A-bigboss-search&p1=selagentlist',
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
	});	 --%>
	
});

<%-- <td><a title="个人充值记录" href="javascript:;" onclick="client_geren('个人充值记录','<%=path%>/rz?p0=A-boss-search&p1=pay_list&p2=1&p3=${map['user_id']}&p4=tojsp','','','510')" class="ml-5" style="text-decoration:none">个人充值记录</a></td> --%>
function fresh_page(pageIndex) {
	var startdate=$("#datemin").val();
	var enddate=$("#datemax").val();
	
	if(startdate=="" && enddate!=""){
		alert('请选择开始时间');
		return;
	}else if(enddate=="" && startdate!=""){
		alert('请选择结束时间');
		return;
	}
	
	/* var agentcode=$("option:selected","#check2").val(); */
	/* var searchname =$("option:selected","#searchname").val();
	var fangshi =$("option:selected","#fangshi").val(); */
	//会员id		主播id
	var searchtext1=$("#searchtext1").val();
	var searchtext2=$("#searchtext2").val();
	/* if(searchtext == undefined){ //返回true 
		searchtext=0;
		
	} 
	
	 if(searchtext == ''){
		searchtext=0;
		
	} */
	/* var searchtxt1=$("#searchtxt1").val();
	if(searchtxt1==''){
		searchtxt1=0;
	}
	
	 var searchtxtnc=$("#searchtxtnc").val(); 
	  */
	 // alert("1");
	$.ajax({
		cache: true,
		type: "POST",
		//p2开始时间 p3当前页数 p4结束时间 p5 会员 p6积分				+"&agentcode="+agentcode+"&searchtxtnc="+searchtxtnc
		//				ar?p0=A-boss-search&p1=pay_table_search&p2=&p3=1&p4=&p5=&p6=tojsp&searchtxt=0&searchtxt1=0&agentcode=0&nc=
						//p2开始时间  p3页码 p4结束时间 p5 会员id p6tojsp p7主播id
		url:"<%=path%>/ar?p0=A-boss-search&p1=gift_record&p2="+startdate+"&p3="+pageIndex+"&p4="+enddate+"&p5="+searchtext1+"&p6=tojson&p7="+searchtext2,
		async: true,
		error: function(request) {
			alert("提交失败 ");
		},
		success: function(data) {
			//alert("2");
			var json=eval("("+data+")");
			var content = '';
			
			var sum = 0;
			for(var i = 0; i < json.length-1; i++) {
				
				content +='<tr class="text-c">'
					+'<td>'+(Number(json[json.length-1].current)+1+i)+'</td>'
					
					+'<td>'+json[i].user_id1+'</td>'
					+'<td>'+json[i].nickname1+'</td>'
					+'<td>'+json[i].user_id2+'</td>'
					+'<td>'+json[i].nickname2+'</td>'
					
					+'<td>'+json[i].name+'</td>'
					+'<td>'+json[i].price+'</td>'
					
					+'</tr>';
					var a = Number(json[i].recharge_num);
					sum = sum+a;
			}
			/* content +='<tr>'
					+'<td  colspan="12"> 当页收入:'+sum+'</td>'
						+'</tr>'; */
			$("#list-content").html(content);
			totalpage = Number(json[json.length-1].totlePage);
			$("#pagefirst").html(Number(json[json.length-1].current)+1);
			$("#pagelast").html(json[json.length-1].lastcount);
			$("#total").html(json[json.length-1].totle);
			$("#currentpage").html(json[json.length-1].pagenum);
			
			$("#sum1").html("礼物总额:"+json[0].sum);
		}
	});
}

function client_geren(title,url,id,w,h){
	layer_show(title,url,w,h);
}
</script>
</body>
</html>