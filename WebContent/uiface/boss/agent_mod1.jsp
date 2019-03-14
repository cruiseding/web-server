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

<title>娃娃增加</title>
<meta name="keywords"
	content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description"
	content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
	<article class="page-container">
		<form 
			class="form form-horizontal" id="form-article-add" name="ThisForm"> <%-- action="<%=path%>/ar?a=A-boss-mod&b=agent_mod1" method="post" --%>
		
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">用户名:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						 placeholder="请输入内容"  value="${reList[0]['agent_username']}"
						id="changeprice1" name="changeprice1">
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">密码:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						 placeholder="请输入内容"   value="${reList[0]['agent_password']}"
						id="changeprice2" name="changeprice2">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">渠道号:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						 placeholder="请输入内容"   value="${reList[0]['agent_channelcode']}"
						id="changeprice3" name="changeprice3">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">渠道名称:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						 placeholder="请输入内容"   value="${reList[0]['agent_name']}"
						id="changeprice4" name="changeprice4">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">渠道链接:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						 placeholder="请输入内容"   value="${reList[0]['agent_url']}"
						id="changeprice5" name="changeprice5">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">提现账户:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						 placeholder="请输入内容"   value="${reList[0]['agent_pay']}"
						id="changeprice6" name="changeprice6">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">提现姓名:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						 placeholder="请输入内容"   value="${reList[0]['agent_payname']}"
						id="changeprice7" name="changeprice7">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">用户充值分成:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						 placeholder="请输入内容"   value="${reList[0]['fencheng_u']}"
						id="changeprice8" name="changeprice8">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">主播提现分成:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						 placeholder="请输入内容"   value="${reList[0]['fencheng_v']}"
						id="changeprice9" name="changeprice9">
				</div>
			</div>
			<input type="hidden" class="input-text"
						  value="${reList[0]['id']}"
						id="changeprice10" name="changeprice10">
			
			<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button  class="btn btn-primary radius"  type="submit" onclick="go()"><i class="Hui-iconfont">&#xe632;</i> 保存并提交</button>
				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button> 
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
		//alert("123");
		$.ajax({
			type:'POST',			
			url: '<%=path%>/ar?a=A-boss-mod&b=agent_mod1',
			data:{changeprice1:$("#changeprice1").val(),changeprice2:$("#changeprice2").val(),changeprice3:$("#changeprice3").val(),changeprice4:$("#changeprice4").val(),changeprice5:$("#changeprice5").val(),changeprice6:$("#changeprice6").val(),changeprice7:$("#changeprice7").val(),changeprice8:$("#changeprice8").val(),changeprice9:$("#changeprice9").val(),changeprice10:$("#changeprice10").val()},
			success: function(data){
				/*$(obj).parents("tr").remove();*/
				/* layer.msg('操作成功',{icon:1,time:1000});
				setTimeout(function () { 
					javascript:location.replace(location.href);
			    }, 1000); */
			    parent.location.reload();
			},
			 error:function(data) {
				 parent.location.reload();
			}, 
		});		
	  
	}
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	//表单验证
	 $("#form-article-add").validate({
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
			document.ThisForm.submit();
			//$("#form-article-add").ajaxSubmit();
			
			setTimeout(function () {
				var index = parent.layer.getFrameIndex(window.name);
				//parent.$('.btn-refresh').click();
				parent.location.reload();
				parent.layer.close(index);
		    }, 1000);
		}
	});
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