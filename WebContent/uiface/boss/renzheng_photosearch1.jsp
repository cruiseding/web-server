<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%--声明我要使用C标签--%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% 
	String path = request.getContextPath() + "/uiface";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp"/>
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/static/h-ui.admin/css/style.css" />
<link href="<%=path%>/boss/lib/lightbox2/2.8.1/css/lightbox.css" rel="stylesheet" type="text/css"/>
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

<title>用户相册</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 会员管理 <span class="c-gray en">&gt;</span> 相册 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="portfolio-content">
		<ul class="cl portfolio-area">
			<c:set var="a" value="${reList[0].personal_album}" ></c:set>
			<c:forEach var="map" items= "${fn:split(a,',')}" varStatus="status">
			<li class="item"  style="height:188px">
				<div class="portfoliobox">
					<div class="picbox">
						<img src="${map}">
					</div>
					
					<button type="button" class="btn btn-danger radius" onclick="shanchu('0','${reList[0].id}','${status.count}')" name="" style="margin-top: 10px;margin-left:  30px;"><i class="Hui-iconfont"></i>删除</button>
					
				</div>
			</li>
			</c:forEach>
		</ul>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path%>/boss/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=path%>/boss/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path%>/boss/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=path%>/boss/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<!--  <script type="text/javascript" src="<%=path%>/boss/static/lib/My97DatePicker/4.8/WdatePicker.js"></script> -->
<!-- <script type="text/javascript" src="<%=path%>/boss/static/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>  -->
<script type="text/javascript" src="<%=path%>/boss/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
function shanchu(type,id,number){
	
	$.ajax({
		type:'POST',
		url: '<%=path%>/ar?p0=A-boss-mod&p1=picture_del&p2='+type+'&id='+id+'&number='+number,
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
	
}
</script>
</body>
</html>