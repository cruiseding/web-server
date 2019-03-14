<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="ck" uri="http://ckeditor.com"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName();
int webPort = request.getServerPort();
if(webPort != 80) {
	basePath = basePath+":"+webPort;
}
String path = basePath+"/uiface";
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
	href="<%=basePath%>/uiface/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/uiface/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/uiface/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/uiface/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/uiface/static/h-ui.admin/css/style.css" />
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
			class="form form-horizontal" id="form-article-add" name="ThisForm">  <%-- action="<%=path%>/ar?a=A-boss-mod&b=photo_mod&p2=${reList[0]['id']}" method="post" --%>
			
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">排序:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="${reList[0]['serial_number']}" placeholder="轮播图序号"
						id="changeprice1" name="changeprice1" onkeyup="onlyNum(this)">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">轮播标题:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="${reList[0]['title']}" placeholder="轮播标题"
						id="changeprice3" name="changeprice3" >
				</div>
			</div>
			<%-- value="${reList[0]['carousel_photo']}" --%>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">轮播图片:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<div class="uploader-thum-container">
						<div id="fileList1" class="uploader-list"></div>
						<input type="file" id="fileimg" /> <input type="hidden"
							name="imgname" id="imges"   />
						<button type="button" class="btn btn-primary radius"
							onclick=upimg() name="img">
							<i class="Hui-iconfont">&#xe642;</i>上传
						</button>
					</div>
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">轮播图地址:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="${reList[0]['chained_address']}" placeholder="点击轮播图跳转地址"
						id="changeprice2" name="changeprice2">
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">分属页面:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<%-- <input type="text" class="input-text" value="${reList[0]['is_bluetooth']}"placeholder="是否蓝牙" id="changeprice3" name="changeprice3"> --%>
					<select class="input-text" style="width:150px" id="searchname1" name="searchname1">
						<option value="聊场">聊场</option>
						<option value="消息">消息</option>
						<%-- <c:if test="${reList[0]['type']=='聊场'}">
								<option value="聊场">聊场</option>
								<option value="消息">消息</option>
						</c:if>
						<c:if test="${reList[0]['type'] == '消息' }">
								<option value="消息">消息</option>
								<option value="聊场">聊场</option>
						</c:if> --%>
						
						
						<!-- <option value="发现">发现</option> -->
					</select>
					
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">状态:</label>
				<div class="formControls col-xs-8 col-sm-9">
					<%-- <input type="text" class="input-text" value="${reList[0]['is_bluetooth']}"placeholder="是否蓝牙" id="changeprice3" name="changeprice3"> --%>
					<select class="input-text" style="width:150px" id="searchname2" name="searchname2">
						<option value="显示">显示</option>
						<option value="隐藏">隐藏</option>
						
						<!-- <option value="发现">发现</option> -->
					</select>
					
				</div>
			</div>
			
			
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button class="btn btn-primary radius" onclick="go(${reList[0]['id']})">
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
		src="<%=basePath%>/uiface/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/uiface/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/uiface/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/uiface/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer /作为公共模版分离出去-->
	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="<%=basePath%>/uiface/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/uiface/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/uiface/lib/jquery.validation/1.14.0/validate-methods.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/uiface/lib/jquery.validation/1.14.0/messages_zh.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/uiface/lib/webuploader/0.1.5/webuploader.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/uiface/lib/ueditor/1.4.3/ueditor.config.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/uiface/lib/ueditor/1.4.3/ueditor.all.min.js"> </script>
	<script type="text/javascript"
		src="<%=basePath%>/uiface/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
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


});

/* function layer_close11(){
	
	var btn = document.getElementById("descs");
	
	alert(btn.value);
	
	
	alert($("#descs").val());
	$("#referral").val($("#descs").val());
} */

function onlyNum(that){
    that.value=that.value.replace(/\D/g,"");
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
				/* alert(img); */
				alert("上传成功");
			}
		})
	flag="1";
	}  else{
		alert("请选择需要上传的图片");
	}
	
}


var flags="0";
function upicon(){
	var oFiles = document.querySelector("#fileicon").files;
	
	
	
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
				var icon = data;
				document.getElementById("icon").value = icon;
				/* alert(icon); */
				alert("上传成功");
			}
		})
	flags="1";
	}  else{
		alert("请选择需要上传的图片");
	}
	
}
function go(id){
	
	$.ajax({
		type:'POST',
		url: '<%=path%>/ar?a=A-boss-mod&b=photo_mod&p2='+id,
		data:{changeprice1:$("#changeprice1").val(),changeprice3:$("#changeprice3").val(),imges:$("#imges").val(),changeprice2:$("#changeprice2").val(),searchname1:$("#searchname1").val(),searchname2:$("#searchname2").val()},
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
			//alert('操作失败');
		},
	});		
  
}
//表单验证

</script>
	<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>