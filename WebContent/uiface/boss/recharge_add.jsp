<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="ck" uri="http://ckeditor.com"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath() + "/uiface";
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

<title>趣豆充值添加</title>
<meta name="keywords"
	content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description"
	content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
	<article class="page-container">
		<form 
			class="form form-horizontal" id="form-article-add" name="ThisForm">  <%-- action="<%=path%>/ar?a=A-boss-add&b=vip_add" method="post" --%>
		<%-- <div class="row cl">
			   <input type="hidden" value="${reList[0]['id']}"  name="myid">
			
			</div> --%>
			
			<!-- 名称、价格、金额、是否推荐、操作 -->
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">趣豆数量:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="${reList[0]['bean_num']}" placeholder="请输入内容" 
						id="num" name="num" onkeyup="onlyNum(this)">
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">赠送数量:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="${reList[0]['give_num']}" placeholder="请输入内容" 
						id="num1" name="num1" onkeyup="onlyNum(this)">
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">当前价格:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="${reList[0]['bean_price']}" placeholder="请输入内容" 
						id="changeprice" name="changeprice">
				</div>
			</div>
			
		
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button class="btn btn-primary radius" onClick="go()">
						<i class="Hui-iconfont">&#xe632;</i> 保存并提交
					</button>
					<button onClick="layer_close();" class="btn btn-default radius"
						type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>
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
	function go(){
		
			$.ajax({
				type:'POST',
				url: '<%=path%>/ar?a=A-boss-add&b=recharge_add',
				data:{num:$("#num").val(),num1:$("#num1").val(),changeprice:$("#changeprice").val()},
				success: function(data){
					/*$(obj).parents("tr").remove();*/
					/* layer.msg('操作成功',{icon:1,time:1000});
					setTimeout(function () { 
						javascript:location.replace(location.href);
				    }, 1000); */
				    parent.location.reload();
				},
				error:function(data) {
					//parent.location.reload();
					alert('操作失败');
				},
			});		
		  
	}

$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	
});

function onlyNum(that){
    that.value=that.value.replace(/\D/g,"");
}

//表单验证
$("#form-article-add").validate({
	rules:{
		"babyname":{
			required:true,
		},
		"babyprice":{
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
		document.ThisForm.submit();
		//$("#form-article-add").ajaxSubmit();
		
		setTimeout(function () {
			var index = parent.layer.getFrameIndex(window.name);
			//parent.$('.btn-refresh').click();
			parent.location.reload();
			parent.layer.close(index);
	    }, 100);
	}
});



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