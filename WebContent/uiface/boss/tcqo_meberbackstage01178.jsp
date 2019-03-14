<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath()+"/uiface";

String adminname ="";

String b="";
if(request.getSession().getAttribute("admin")!=null&&!"".equals(request.getSession().getAttribute("admin").toString())){

	adminname=request.getSession().getAttribute("admin").toString();

	//b=request.getSession().getAttribute("power")+"";
	b = request.getSession().getAttribute("id").toString();

 }else{

	response.sendRedirect(path+"/boss/adminLogin.jsp"); 

 }

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
	<span class="c-gray en">&gt;</span> 会员管理 
	<span class="c-gray en">&gt;</span> 会员列表 
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
		<i class="Hui-iconfont">&#xe68f;</i>
	</a>
</nav>
<div class="page-container">
	<div class="text-c">
		<div class="mt-20">
			<div class="text-c">
				<!-- 	ID号：
				<input type="text" class="input-text" style="width:250px"  placeholder="请输入用户ID号" id="searchtxt" name="searchtext">	
			                日期范围：
				<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;" name="">
				
				- <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;" name="">
				<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span>性别:</span>
				<select class="input-text" style="width:150px" id="searchname" name="">
					<option></option>
					<option value="男">男</option>
					<option value="女">女</option>
				</select>
				<button type="submit" class="btn btn-success radius" id="searchbtn" name=""><i class="Hui-iconfont"></i>搜用户</button> -->
				
				
				<input type="text" class="input-text" style="width: 100px;" placeholder="ID号" id="searchtxt" name="searchtext">
				<input type="text" class="input-text" style="width: 100px;" placeholder="昵称" id="searchtxt1" name="searchtext1">
				<input type="text" class="input-text" style="width: 100px;" placeholder="手机号" id="phone" name="phone">
				
						
				<span>&nbsp;&nbsp;&nbsp;&nbsp;注册时间:</span>        
			<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;" name="">
			
			- <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;" name=""> 
			
			<!-- <select id="check2" name="check2">
		      
	          </select>
	        	     -->
	        	     <div style="height:10px;">
	        	     </div>
	        	   <!-- 性别 --> 
	        	<!-- <select class="input-text" style="width:150px" id="gender" name="gender" >
					<option value="">性别</option>
					<option value="男">男</option>
					<option value="女">女</option>
				</select> -->
				<!-- 是否会员 -->
				
				<!-- <input type="text" class="input-text" style="width: 100px;" placeholder="推荐人ID" id="tjr_id" name="tjr_id"> -->
				<!-- <input type="text" class="input-text" style="width: 100px;" placeholder="推荐人昵称" id="tjr_name" name="tjr_name"> -->
				
				<select class="input-text" style="width:150px" id="member" name="member">
					<option value="">是否VIP</option>
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
				<!-- 是否推荐 -->
				<!-- <select class="input-text" style="width:150px" id="recommand" name="recommand">
					<option value="">是否推荐</option>
					<option value="0">否</option>
					<option value="1">是</option>
				</select> -->
				<!-- 封禁和解封 -->
				<select class="input-text" style="width:150px" id="sealing" name="sealing">
					<option value="">账号状态</option>
					<option value="0">正常</option>
					<option value="1">封禁</option>
				</select> 
	        	     
	          
			<button type="submit" class="btn btn-success radius" id="searchbtn" name="" ><i class="Hui-iconfont"></i>搜用户</button>
				
			</div>	
		</div>
		
	<div class="mt-20">
	<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper ">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width=20>序号</th>
				<th width="20">ID</th>
				
				
				
			
				<th width="40">昵称</th>
				
				<!-- <th width="40">推荐人ID</th> -->
				<th width="40">推荐人昵称</th>
				<th width="40">头像</th>
				
				
				<th width="40">手机号码</th>
				<th width="40">账户余额</th>
				<th width="40">分享收入</th>
				<th width="40">注册时间</th>
				<th width="40">最后登录时间</th>
				<th width="40">是否会员</th>
				
				<!-- <th width="40">是否推荐</th> -->
				
				<th width="40">账号状态</th>
				
				<th width="40">操作</th>
				
				<%-- <% if(b.contains("管理员管理")){%>
				<th width="40">趣豆充值</th>
				<% } %> --%>
				<% if(b.equals("1")){ %>
				<%-- <c:if test="${b =='1'}"> --%>
				<th width="40">趣豆充值</th>
				<% } %>
				<%-- </c:if> --%>
				
			</tr>
		</thead>
		<tbody id="list-content">
			<c:forEach var = "map" items="${reList }" varStatus="status">
				
				<tr class="text-c">
					<td>${status.count}</td>
					<%-- <td>${map['id']}</td> --%>
					<td>${map['user_id']}</td>
					<td >${map['nickname']}</td>
					
					<%-- <td >${map['promoter_id']}</td> --%>
					<td >${map['tjr_name']}</td>
					
					<td><img class="pimg" alt="" src="${map['user_photo']}" style="width:80px"></td>
					
					
					<%-- <td>${map['gender']}</td> --%>
					<td>${map['phone']}</td>
					<td>${map['balance']}</td>
					
					<!-- <td>分享收入</td> -->
					<td>
					<a href="<%=path%>/ar?p0=A-boss-search&p1=fencheng_yiji&p2=1&p3=tojsp&p4=${map['id']}"
                                   target="_self" data-title="一级下属人数" href="javascript:void(0)"
                                   style="color: #0e90d2">分享收入</a>
					</td>
					
					<td>${map['registered_time']}</td>
					<td>${map['last_login_time']}</td>
					<c:choose>
							<c:when test="${map['is_member']=='0' }"><td>否</td></c:when>
							<c:when test="${map['is_member']=='1' }"><td>是</td></c:when>
					</c:choose>
					<%-- <c:choose>
							<c:when test="${map['is_recommand']=='0' }"><td>否</td></c:when>
							<c:when test="${map['is_recommand']=='1' }"><td>是</td></c:when>
					</c:choose> --%>
					
					<%-- 
					<td><a style="color: blue;"  href="<%=path%>/ar?p0=A-boss-search&p1=income_table_search1&amp;p2=1&amp;p3=&amp;p4=&amp;p5=tojsp&amp;p6=&amp;p7=${map['id']}&amp;nc=" >查看详情</a></td>
				<td><a style="color: blue;" href="<%=path%>/ar?p0=A-boss-search&p1=xiaofei_table_search2&p2=1&p3=&p4=&p5=tojsp&p6=&p7=${map['id']}" >查看详情</a></td> --%>
				<c:choose>
							<c:when test="${map['is_sealing']!='1' }"><td>正常</td></c:when>
							<c:when test="${map['is_sealing']=='1' }"><td>封禁</td></c:when>
					</c:choose>
					<td class="td-manage">
						<%-- <a title="封禁" onclick="banned(${map['id']},'${map['nickname']}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">封禁</i></a><br/>  
						<a title="解封" onclick="no_banned(${map['id']},'${map['nickname']}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">解封</i></a> --%>  
						<a href="javascript:;"
						onclick="banned(${map['id']},'${map['nickname']}')"
						style="text-decoration: none;color:blue;" class="ml-5">封禁</a>
						
						<a href="javascript:;"
						onclick="no_banned(${map['id']},'${map['nickname']}')"
						style="text-decoration: none;color:blue;" class="ml-5">解封</a>
					</td>
					
					<% if(b.equals("1")){ %>
				<td>					
						<a href="javascript:;"
						onclick="qd_mod('增加余额','<%=path %>/ar?p0=A-boss-mod&p1=qd_search_a&p2=${map['id'] }&p3=0&p4=&p5=&p6=','600','600')"
						style="text-decoration: none;color:blue;" class="ml-5">增加</a>
						
						<a href="javascript:;"
						onclick="qd_mod('减少余额','<%=path %>/ar?p0=A-boss-mod&p1=qd_search_b&p2=${map['id'] }&p3=0&p4=&p5=&p6=','600','600')"
						style="text-decoration: none;color:blue;" class="ml-5">减少</a>
					</td>	
					<% } %>	
					<%-- </c:if>	 --%>				
					
					
				</tr>
				<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;"><div id="innerdiv" style="position:absolute;"><img id="bigimg" style="border:5px solid #fff;" src="" /></div></div>
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
<script type="text/javascript" src="<%=path%>/boss/lib/jquery/1.9.1/jquery.js"></script> 
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

var curUrl = "";

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
		fresh_page(1);
	});
	
	
	<%-- $.ajax({
		type: 'POST',
		url:'<%=path%>/ar?p0=A-bigboss-search&p1=selagentlist',
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

function qd_mod(title,url,w,h){
	layer_show(title,url,w,h);
}


/* +'<td class="td-manage">'
+'<a title="封禁" href="javascript:;" onclick="banned('+json[i].id+',\''+json[i].nickname+'\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">封禁</i></a><br/>'
+'<a title="解封" href="javascript:;" onclick="no_banned('+json[i].id+',\''+json[i].nickname+'\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">解封</i></a>'
+'</td>' */



/*刷新列表*/
function fresh_page(pageIndex){
	var startdate=$("#datemin").val();
	var enddate=$("#datemax").val();
/* 	var agentcode=$("option:selected","#check2").val(); */
	var abc = "<%=b%>";
	/* var gl = "管理员管理";
	var no = abc.indexOf(gl); */
	var searchtxt=$("#searchtxt").val();
	
	if(searchtxt==''){
		searchtxt=0;
	}
	var searchtxt1=$("#searchtxt1").val();
	
	if(startdate=="" && enddate!=""){
		alert('请选择开始时间');
		return;
	}else if(enddate=="" && startdate!=""){
		alert('请选择结束时间');
		return;
	}
	
	/* var tjr_id = $('#tjr_id').val(); */
	/* var tjr_name = $('#tjr_name').val(); */
	
	var phone =  $('#phone').val();
	/* var gender=$("option:selected","#gender").val(); */
	var member=$("option:selected","#member").val();
	/* var recommand=$("option:selected","#recommand").val(); */
	var sealing=$("option:selected","#sealing").val();
	
	/* $("#searchbtn").click(function() {
		
		if(startdate=="" && enddate!=""){
			alert('请选择开始时间');
		}else if(enddate=="" && startdate!=""){
			alert('请选择结束时间');
		}
		fresh_page(1);
	}); */
	
	
	curUrl = "<%=path%>/ar?p0=A-boss-search&p1=memberbackstage&p2="+pageIndex+"&p3="+startdate+"&p4="+enddate+"&p5=tojson&p6="+searchtxt+"&p7="+phone+"&p8="+member+"&p9=&p10="+sealing+"&p11="+searchtxt1;
	$.ajax({
		cache: true,
		type: "POST",
		url: curUrl ,
		async: true,
		error: function(request) {
			alert("提交失败 ");
		},
		success: function(data){
			var json=eval("("+data+")");
			var content = '';
			var yiji = '';
			var quanxian = '';
			var jiefeng = '';
			for(var i = 0; i < json.length-1; i++) {
			   var huiyuan="";
			   var online="";
				/* var no = str1.indexOf(str2);
			   no大于-1包含，等于-1不包含 */
			  /*  var no = abc.indexOf(gl); */
			   var src = json[i].user_photo;
			   yiji = '<td><a href="<%=path%>/ar?p0=A-boss-search&p1=fencheng_yiji&p2=1&p3=tojsp&p4='+json[i].id+'" target="_self" data-title="一级下属人数" href="javascript:void(0)" style="color: #0e90d2">分享收入</a></td>';
			   <%-- if(no>-1){
					quanxian = '<td><a href="javascript:;" onclick="qd_mod(\'增加余额\',\'<%=path %>/ar?p0=A-boss-mod&p1=qd_search_a&p2='+json[i].id+'&p3=0\',\'600\',\'600\')"  ><span style="color:blue;">增加</span></a>  <a href="javascript:;" onclick="qd_mod(\'减少余额\',\'<%=path %>/ar?p0=A-boss-mod&p1=qd_search_b&p2='+json[i].id+'&p3=0\',\'600\',\'600\')"  ><span style="color:blue;">减少</span></a></td>';
				}else{
					quanxian = ''; 
				} --%>
				
				if(abc=='1'){
					quanxian = '<td><a href="javascript:;" onclick="qd_mod(\'增加余额\',\'<%=path %>/ar?p0=A-boss-mod&p1=qd_search_a&p2='+json[i].id+'&p3=0\',\'600\',\'600\')"  ><span style="color:blue;">增加</span></a>  <a href="javascript:;" onclick="qd_mod(\'减少余额\',\'<%=path %>/ar?p0=A-boss-mod&p1=qd_search_b&p2='+json[i].id+'&p3=0\',\'600\',\'600\')"  ><span style="color:blue;">减少</span></a></td>';
				}else{
					quanxian = ''; 
				}
				
				jiefeng = '<td><a href="javascript:;" onclick="banned('+json[i].id+',\''+json[i].nickname+'\')"  ><span style="color:blue;">封禁</span></a>  <a href="javascript:;" onclick="no_banned('+json[i].id+',\''+json[i].nickname+'\')"  ><span style="color:blue;">解封</span></a></td>';
					/* quanxian = ''; */
				
				if(json[i].is_member=='1'){
					huiyuan='是';
				   }else{
					   huiyuan='否';
				}  
				/* if(json[i].is_recommand=='1'){
					tuijian='是';
				   }else{
					tuijian='否';
				}  */
				
				var banned="";
				if(json[i].is_sealing=='1'){
					banned='封禁';
				}else{
					banned='正常';
				}
				 content+='<tr class="text-c">'
					+'<td>'+(Number(json[json.length-1].current)+1+i)+'</td>'
					
					+'<td>'+json[i].user_id+'</td>'
					+'<td id="username">'+json[i].nickname+'</td>'
					/* +'<td>'+json[i].promoter_id+'</td>' */
					+'<td>'+json[i].tjr_name+'</td>'
					+'<td><img onclick="imgShow2(\''+src+'\')" id="img" alt="" src="'+json[i].user_photo+'" style="width:80px"></td>'
					+'<td>'+json[i].phone+'</td>'
					+'<td>'+json[i].balance+'</td>'
					+yiji
					+'<td>'+json[i].registered_time+'</td>'
					+'<td>'+json[i].last_login_time+'</td>'
					
					
					+'<td>'+huiyuan+'</td>'
					/* +'<td>'+tuijian+'</td>' */
					/* +'<td>'+json[i].upusername+'</td>'
					+'<td><a style="color: blue;"  href="http://116.62.220.67:8091/uiface/rp?p0=A-boss-search&p1=income_table_search1&amp;p2=1&amp;p3=&amp;p4=&amp;p5=tojsp&amp;p6=&amp;p7='+json[i].id+'&amp;nc=" >查看详情</a></td>'
					+'<td><a  style="color: blue;" href="http://116.62.220.67:8091/uiface/rp?p0=A-boss-search&p1=xiaofei_table_search2&p2=1&p3=&p4=&p5=tojsp&p6=&p7='+json[i].id+'" >查看详情</a></td>' */
					+'<td>'+banned+'</td>'
					+jiefeng
					+quanxian
					+'<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;"><div id="innerdiv" style="position:absolute;"><img id="bigimg" style="border:5px solid #fff;" src="" /></div></div>'
				 	
					<%-- +'<td><a href="javascript:;" onclick="qd_mod(\'增加余额\',\'<%=path %>/ar?p0=A-boss-mod&p1=qd_search_a&p2='+json[i].id+'&p3=0\',\'600\',\'600\')"  ><span style="color:blue;">增加</span></a>  <a href="javascript:;" onclick="qd_mod(\'减少余额\',\'<%=path %>/ar?p0=A-boss-mod&p1=qd_search_b&p2='+json[i].id+'&p3=0\',\'600\',\'600\')"  ><span style="color:blue;">减少</span></a></td>' --%>
					
					
					
					<%-- +'<input  type="button" href="javascript:;" class="btn btn-success radius" onclick="qd_mod(\'增加趣豆\',\'<%=path %>/ar?p0=A-boss-mod&p1=qd_mod_a&p2='+json[i].id+'&p3=0\',\'600\',\'600\')" value="增加">'
					+'<input  type="button" href="javascript:;" class="btn btn-success radius" onclick="qd_mod(\'减少趣豆\',\'<%=path %>/ar?p0=A-boss-mod&p1=qd_mod_b&p2='+json[i].id+'&p3=0\',\'600\',\'600\')" value="减少">' --%>
							/* +'<td class="td-manage">'
					+shezhi1+'</td>' */;
					
			
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

/* function seek(){
	var startdate=$("#datemin").val();
	var enddate=$("#datemax").val();
	if(startdate=="" && enddate!=""){
		alert('请选择开始时间');
	}else if(enddate=="" && startdate!=""){
		alert('请选择结束时间');
	}
	
} */
function con(){
	alert("input");
}
function system_category_edit1(id,userid){
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
}

function qd_mod(title,url,w,h){
	layer_show(title,url,w,h);
}

function qd_mod_a(title,url,w,h){
	layer_show(title,url,w,h);
}

function qd_mod_b(title,url,w,h){
	layer_show(title,url,w,h);
}

function go(id,userid){
	/* age = prompt("请输入未通过原因","");
	layer.confirm('确认输入以上内容？',function(index){ */

		$.ajax({
			type:'POST',
			url: '<%=path%>/rp?p0=A-boss-mod&p1=zhubo_nopass&p2='+id,
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

 function banned(id,node){ 
	/* function banned(id){ */
		/* var tr1 = node.parentNode.parentNode; */
		/* alert(node); */
		 
		 layer.confirm('确认封禁用户      '+node+'？',function(index){ 
		$.ajax({
			type:'POST',
			url: '<%=path%>/ar?p0=A-boss-mod&p1=anchor_banned&p2='+id,
			success: function(data){
				/*$(obj).parents("tr").remove();*/
				layer.msg('操作成功',{icon:1,time:1000});
				setTimeout(function () { 
					
					if(curUrl == "") {
						javascript:location.replace(location.href);
					} else {
						javascript:location.replace(curUrl.replace('tojson','tojsp'));
						/* javascript:location.replace(curUrl.replace('tojsp','tojson')); */
					}
			    }, 1000);
			},
			error:function(data) {
				alert('通过失败');
			},
		});		
	 });
}
function no_banned(id,node){
	/* age = prompt("请输入未通过原因","");
	layer.confirm('确认输入以上内容？',function(index){ */
		
		layer.confirm('确认解封用户      '+node+'？',function(index){
		$.ajax({
			type:'POST',
			url: '<%=path%>/ar?p0=A-boss-mod&p1=banned_cancel&p2='+id,
			success: function(data){
				/*$(obj).parents("tr").remove();*/
				layer.msg('操作成功',{icon:1,time:1000});
				setTimeout(function () { 
					if(curUrl == "") {
						javascript:location.replace(location.href);
					} else {
						javascript:location.replace(curUrl.replace('tojson','tojsp'));
					}
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


/*$(document).ready(function(){
	$(".pimg").click(function(){
		alert("1");
		var _this = $(this);//将当前的pimg元素作为_this传入函数
		imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
	});
});*/
$(function(){
	$(".pimg").click(function(){
		//alert("1");
		var _this = $(this);//将当前的pimg元素作为_this传入函数
		imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
	});
}); 
$(function(){
	$(".pimg2").on('click', function(){
		alert("2");
	});
});
function imgShow(outerdiv, innerdiv, bigimg, _this){  
	
    var src = _this.attr("src");//获取当前点击的pimg元素中的src属性  
    $(bigimg).attr("src", src);//设置#bigimg元素的src属性  
  
        /*获取当前点击图片的真实大小，并显示弹出层及大图*/  
    $("<img/>").attr("src", src).load(function(){  
        var windowW = $(window).width();//获取当前窗口宽度  
        var windowH = $(window).height();//获取当前窗口高度  
        var realWidth = this.width;//获取图片真实宽度  
        var realHeight = this.height;//获取图片真实高度  
        var imgWidth, imgHeight;  
        var scale = 1.0;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放  
        
        if(realHeight>windowH*scale) {//判断图片高度  
            imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放  
            imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度  
            if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度  
                imgWidth = windowW*scale;//再对宽度进行缩放  
            }
        } else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度  
            imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放  
                        imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度  
        } else {//如果图片真实高度和宽度都符合要求，高宽不变  
            imgWidth = realWidth;  
            imgHeight = realHeight;  
        }  
                $(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放  
        
        var w = (windowW-imgWidth)/2;//计算图片与窗口左边距  
        var h = (windowH-imgHeight)/2;//计算图片与窗口上边距  
        $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性  
        $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg  
    });  
      
    $(outerdiv).click(function(){//再次点击淡出消失弹出层  
        $(this).fadeOut("fast");  
    });
}

function imgShow2(src){
	
	//alert(src);
	
	var outerdiv = document.getElementById("outerdiv");
	//alert(outerdiv);
	var innerdiv = document.getElementById("innerdiv");
	//alert(innerdiv);
	var bigimg = document.getElementById("bigimg");
	//alert(bigimg);
	
    $(bigimg).attr("src", src);//设置#bigimg元素的src属性  
  	
    /*获取当前点击图片的真实大小，并显示弹出层及大图*/
    $("<img/>").attr("src", src).load(function(){  
        var windowW = $(window).width();//获取当前窗口宽度  
        var windowH = $(window).height();//获取当前窗口高度  
        var realWidth = this.width;//获取图片真实宽度  
        var realHeight = this.height;//获取图片真实高度  
        var imgWidth, imgHeight;  
        var scale = 1.0;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放  
        
        if(realHeight>windowH*scale) {//判断图片高度  
            imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放  
            imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度  
            if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度  
                imgWidth = windowW*scale;//再对宽度进行缩放  
            }
        } else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度  
            imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放  
                        imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度  
        } else {//如果图片真实高度和宽度都符合要求，高宽不变  
            imgWidth = realWidth;  
            imgHeight = realHeight;  
        }  
                $(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放  
        
        var w = (windowW-imgWidth)/2;//计算图片与窗口左边距  
        var h = (windowH-imgHeight)/2;//计算图片与窗口上边距  
        $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性  
        $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg  
    });  
      
    $(outerdiv).click(function(){//再次点击淡出消失弹出层  
        $(this).fadeOut("fast");  
    });
}
</script> 
</body>
</html>