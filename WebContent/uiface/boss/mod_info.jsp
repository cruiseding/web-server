<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath()+"/uiface";

String b="";
String adminname ="";
if(request.getSession().getAttribute("admin")!=null&&!"".equals(request.getSession().getAttribute("admin").toString())){

	adminname=request.getSession().getAttribute("admin").toString();

	b=request.getSession().getAttribute("power")+"";

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
<title>用户信息修改审核</title>
</head>
<body>
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 
	<span class="c-gray en">&gt;</span> 用户信息修改审核
	<span class="c-gray en">&gt;</span> 用户信息修改审核
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
		<i class="Hui-iconfont">&#xe68f;</i>
	</a>
</nav>
<div class="page-container">
	<div class="text-c">
		<div class="mt-20">
			
			<div class="mt-20">
			<div class="text-c">
				<select class="input-text" style="width:150px" id="shenhe" name="">
					<option value="">审核状态</option>
					<option value="审核中">审核中</option>
					<option value="已通过">已通过</option>
					<option value="未通过">未通过</option>
				</select>
			<!-- <select id="check2" name="check2">
		      
	          </select> -->
				<button type="submit" class="btn btn-success radius" id="searchbtn" name=""><i class="Hui-iconfont"></i>搜用户</button>
			</div>	
		</div>
			
			
		</div>
		
	<div class="mt-20">
	<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper ">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="30">序号</th>
				<th width="40">用户ID</th>
				<th width="40">昵称</th>
				<th width="40">相册</th>
			
				<th width="40">年龄</th>
				<th width="40">所在城市</th>
				<th width="40">个性签名</th>
				<th width="40">原因</th>
				
				<!-- <th width="40">审核状态</th> -->
				<!-- <th width="40">审核时间</th> -->
				<th width="40">操作</th> 
			</tr>
		</thead>
		<tbody id="list-content">
			<c:forEach var = "map" items="${reList }" varStatus="status">
			<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;"><div id="innerdiv" style="position:absolute;"><img id="bigimg" style="border:5px solid #fff;" src="" /></div></div>
				<tr class="text-c">
					<td>${status.count}</td>
				<td>${map['ids']}</td>
				<td>${map['nickname']}</td>
				<td><a href="javascript:;"
					onclick="system_category_edit('认证图片','<%=path %>/ar?p0=A-boss-search&p1=shenhe_photo&p2=${map['cid'] }&p3=0','600','510')"
					style="text-decoration: none;color:blue;" class="ml-5">相册</a>
				</td>
				<td>${map['age'] }</td>
				<td>${map['city'] }</td>
				<td>${map['signature'] }</td>
				
				<td>${map['refusal'] }</td>
				<td class="td-manage">
				<c:if test="${map['result'] == '未通过' }">
					<span style="color:blue;">未通过</span>
				</c:if>
				<c:if test="${map['result'] == '已通过' }">
					<span style="color:blue;">已通过</span>
				</c:if>
				<c:if test="${map['result'] == '审核中' }">
					<a title="通过" href="javascript:;" onclick="system_category_edit1(${map['cid']},${map['user_id']},'${map['nickname']}')" class="ml-5" style="text-decoration:none;color:green;">通过</a>  
				   <a title="不通过" href="javascript:;" onclick="go(${map['cid']},${map['user_id']})" class="ml-5" style="text-decoration:none;color:red;">不通过</a>
				</c:if>
				
				</td>
				<%-- <td style="color:blue;">${map['result'] }</td>
				<td class="td-manage">
				   <a title="通过" href="javascript:;" onclick="system_category_edit1(${map['id']},${map['user_id']},${map['myjob']})" class="ml-5" style="text-decoration:none;color:green;">通过</a>  
				   <a title="不通过" href="javascript:;" onclick="go(${map['id']},${map['user_id']})" class="ml-5" style="text-decoration:none;color:red;">不通过</a>	 
				</td>
				 --%>
				
				
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

/*刷新列表*/
 
var curUrl = "";
function fresh_page(pageIndex){
	
	var shenhe=$("option:selected","#shenhe").val();
	

	<%-- url:"<%=path%>/ar?p0=A-boss-search&p1=cash_withdrawal&p2="+pageIndex+"&p3="+startdate+"&p4="+enddate+"&p5=tojson&p6="+pp+"&p7=0&p8="+searchtext+"&p9="+searchtxtnc+"&p10="+tixian, --%>
	<%-- url: "<%=path %>/ar?p0=A-boss-search&p1=album_list&p2="+pageIndex+"&p3=&tojson&p4="+shenhe, --%>
	curUrl = "<%=path %>/ar?p0=A-boss-search&p1=album_list&p2="+pageIndex+"&p3=tojson&p4="+shenhe;
	$.ajax({
		cache: true,
		type: "POST",
		url: curUrl,
		async: true,
		error: function(request) {
			alert("提交失败 ");
		},
		success: function(data){
			var json=eval("("+data+")");
			var content = '';
			var str1 = '';
			for(var i = 0; i < json.length-1; i++) {
				
				if(json[i].result=='审核中'){
					str1 = '<a title="通过" href="javascript:;" onclick="system_category_edit1('+json[i].cid+','+json[i].user_id+',\''+json[i].nickname+'\') "  class="ml-5" style="text-decoration:none;color:green;">通过</a> <a title="不通过" href="javascript:;" onclick="go('+json[i].cid+','+json[i].user_id+')" class="ml-5" style="text-decoration:none;color:red;">未通过</a>';
				}else if(json[i].result=='已通过'){
					str1 = '<span style="color:blue;">已通过</span>';
				}else if(json[i].result=='未通过'){
					str1 = '<span style="color:blue;">未通过</span>';
				}
				
				 content +='<tr class="text-c">'
					 +'<td>' +(Number(json[json.length-1].current)+1+i)+'</td>'
						+'<td>'+json[i].ids+'</td>'
						+'<td>'+json[i].nickname+'</td>'
						
						
						
					    +'<td><a href="javascript:;" onclick="system_category_edit(\'认证图片\',\'<%=path %>/ar?p0=A-boss-search&p1=shenhe_photo&p2='+json[i].cid+'&p3=0\',\'600\',\'510\')"  ><span style="color:blue;">相册</span></a></td>'
					  
					    +'<td>'+json[i].age+'</td>'
					    +'<td>'+json[i].city+'</td>'
					    +'<td>'+json[i].signature+'</td>'
					    +'<td>'+json[i].refusal+'</td>'
					    
					    
					    +'<td class="td-manage">'
					    +str1
					    +'</td>'
					    /* +'<td style="color:blue;">'+json[i].result+'</td>' */
					    /* 
					    +'<td class="td-manage">'
						+'<a title="通过" href="javascript:;" onclick="system_category_edit1('+json[i].id+','+json[i].user_id+') "  class="ml-5" style="text-decoration:none;color:green;">通过</a>'
						+'<a title="不通过" href="javascript:;" onclick="go('+json[i].id+','+json[i].user_id+')" class="ml-5" style="text-decoration:none;color:red;">未通过</a>'
						+'</td>' */
						+'</tr>'
						; 
				 /* +'<a title="封禁" href="javascript:;" onclick="banned('+json[i].id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">封禁</i></a>' */
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


function qd_mod(title,url,w,h){
	layer_show(title,url,w,h);
}

function system_category_edit(title,url,w,h){
	layer_show(title,url,w,h);
}



function system_category_edit1(id,userid,nickname){
	//layer_show(title,url,w,h);
	layer.confirm('确认通过用户      '+nickname+'  修改个人信息？',function(index){
	$.ajax({
		type: 'POST',
		url:'<%=path%>/ar?p0=A-boss-mod&p1=album_checkpass&p2='+id+'&p3='+userid+'&p4=&p5=',
		success: function(data){
			/*$(obj).parents("tr").remove();*/
			layer.msg('操作成功',{icon:1,time:1000});
			setTimeout(function () { 
				if(curUrl == ""){
					javascript:location.replace(location.href);
					
				}else{
					javascript:location.replace(curUrl.replace('tojson','tojsp'));
				}
		    }, 1000);
		},
		error:function(data) {
			alert('提交失败');
		},
	});		
	
	});
}
function go(id,userid){
	//age = prompt("请输入未通过原因","");
	 age = prompt("请输入未通过原因","");
	 
	 
		if(age){
			layer.confirm('确认输入以上内容？',function(index){
				$.ajax({
					type:'POST',
					url: '<%=path%>/ar?p0=A-boss-mod&p1=album_checknopass&p2='+id+'&p3='+userid+'&p4='+age+'&p5=',
					type:'POST',
		            dataType:'JSON',
		            data:{'age':age},
					success: function(data){
						layer.msg('操作成功',{icon:1,time:1000});
						setTimeout(function () { 
							if(curUrl == ""){
								javascript:location.replace(location.href);
								
							}else{
								javascript:location.replace(curUrl.replace('tojson','tojsp'));
							}
					    }, 1000);
					},
					error:function(data) {
						alert('通过失败');
					},
				});
			 }); 
		}else if(age==""){
			alert("不能为空，请输入原因");
		}else{
			
		}

	 
	<%--  if(age==""){
		 alert("未通过原因不能为空");
		 return;
	 }
	
	layer.confirm('确认输入以上内容？',function(index){

		$.ajax({
			type:'POST',
			url: '<%=path%>/ar?p0=A-boss-mod&p1=album_checknopass&p2='+id+'&p4='+userid,
			type:'POST',
            dataType:'JSON',
            data:{'age':age},
			success: function(data){
				layer.msg('操作成功',{icon:1,time:1000});
				setTimeout(function () { 
					javascript:location.replace(location.href);
			    }, 1000);
			},
			error:function(data) {
				alert('通过失败');
			},
		});		
	 }); --%>
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