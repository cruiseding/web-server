<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="ck" uri="http://ckeditor.com"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath() + "/uiface";


String adminname ="";
String username = "";
if(request.getSession().getAttribute("admin")!=null&&!"".equals(request.getSession().getAttribute("admin").toString())){

	adminname=request.getSession().getAttribute("admin").toString();

	username=request.getSession().getAttribute("admin_name")+"";

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
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico">
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="<%=path%>/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>趣豆减少</title>
<meta name="keywords"
	content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description"
	content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
	<article class="page-container">
		
	
			<!-- <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">礼物图片:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<div class="uploader-thum-container">
						<div id="fileList1" class="uploader-list"></div>
						<input type="file" id="fileimg" /> <input type="hidden"
							name="imgname" id="imges"  value="" />
						<button type="button" class="btn btn-primary radius"
							onclick=upimg() name="img">
							<i class="Hui-iconfont">&#xe642;</i>上传
						</button>
					</div>
				</div>
			</div> -->
			<!-- <div style="height:20px">
			</div> -->
			<!-- <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">用户id:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" 
						placeholder="用户id" id="onefee" name="onefee" readonly="readonly">
				</div>
			</div> -->
			<div style="height:20px">
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">用户id:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="${reList[0]['id']}"  readonly="readonly" 
						id="changeprice1" >		<!-- placeholder="请输入内容" -->
				</div>
			</div>
			<div style="height:20px">
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">用户昵称:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="${reList[0]['nickname']}"  readonly="readonly" 
						id="changeprice2" >		<!-- placeholder="请输入内容" -->
				</div>
			</div>
			<div style="height:20px">
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">减少趣豆:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="" placeholder="请输入金额" 
						id="changeprice3" name="changeprice1">
				</div>
			</div>

			<div style="height:20px">
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button class="btn btn-primary radius"   onClick="baocun();" >
						<i class="Hui-iconfont">&#xe632;</i> 充值趣豆
					</button>
					<button onClick="layer_close();" class="btn btn-default radius"
						type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		
	</article>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="<%=path%>/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="<%=path%>/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer /作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="<%=path%>/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="<%=path%>/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
	<script type="text/javascript"
		src="<%=path%>/lib/jquery.validation/1.14.0/validate-methods.js"></script>
	<script type="text/javascript"
		src="<%=path%>/lib/jquery.validation/1.14.0/messages_zh.js"></script>
	<script type="text/javascript"
		src="<%=path%>/lib/webuploader/0.1.5/webuploader.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/lib/ueditor/1.4.3/ueditor.config.js"></script>
	<script type="text/javascript"
		src="<%=path%>/lib/ueditor/1.4.3/ueditor.all.min.js"> </script>
	<script type="text/javascript"
		src="<%=path%>/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	
});



//表单验证
/*  $("#form-article-add").validate({
	rules:{
		"changeprice1":{
			required:true,
		},
		"changeprice":{
			required:true,
		},
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		//document.ThisForm.submit();
		$("#form-article-add").ajaxSubmit();
		
		setTimeout(function () {
			var index = parent.layer.getFrameIndex(window.name);
			//parent.$('.btn-refresh').click();
			parent.location.reload();
			parent.layer.close(index);
	    }, 100);
	}
}); */

function baocun(){
	
	var a=$("#changeprice1").val();
	var b=$("#changeprice3").val();
	var abc = "<%=username%>";
	layer.confirm('确认要减少'+b+'趣豆？',function(index){ 
	/*var c=$("#twofee").val(); */
	 $.ajax({ 
		url :"<%=path%>/ar?p0=A-boss-mod&p1=qd_mod_b&p2="+a+"&p3="+b+"&p4="+abc+"&p5=&p6", 
		type : 'post', 
		cache : false, 
		contentType : false, 
		processData : false, 
		success : function(data) { 
			var index = parent.layer.getFrameIndex(window.name);
			//parent.$('.btn-refresh').click();
			parent.location.reload();
			parent.layer.close(index);
		}
	})
	});
	
}


var flag="0";
function upimg(){
	
	var oFiles = document.querySelector("#fileimg").files;
	
	if(oFiles.length){
		var file=oFiles[0];
		console.log(file);
		var formdata = new FormData(); 
		formdata.append("imgFile", file); 
		$.ajax({ 
			url :"<%=path%>/JyFileUploadServlet", 
			type : 'post', 
			data : formdata, 
			cache : false, 
			contentType : false, 
			processData : false, 
			success : function(data) { 
				var img = data;
				document.getElementById("imges").value = img;
				alert("上传成功");
			}
		})
	flag="1";
	}  else{
		alert("请选择需要上传的图片");
	}
	
}
</script>
	<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>