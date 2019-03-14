<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<%--声明我要使用C标签--%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath()+"/uiface";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
<title>帮我的尽淘乐助力一下！</title>
<script type="text/javascript"
	src="<%=path %>/user/js/jquery.js"></script>
<script type="text/javascript">
$(function() { 
	$('#jiayou').click(function(){ 
		$.ajax({
			type: 'POST',
			url: '<%=path %>/share?mode=A-user-add&mode2=share_jiayou',
			data: {'userid':${reList[0]['id']},'ip':'','nhsy':''},
			dataType:'json',
			success:function(json){
				console.log(json);
				if(json.result == "success"){
					console.log("加油成功");
					location.reload();
				}else if(json.result == "fail"){
					console.log("加油失败");
				}
				else if(json.result == "cunzai"){
					console.log("已加油");
				}
			}
	});		
	}) ;
}) 
</script>
</head>
<style type="text/css">
html,body{
	padding:0;margin:0;background: #F4F4F4;
}
.bac_img{
	background: url('<%=path%>/user/img/beijing.jpg');
	height:270px;
}
.div_time{
    background: url('<%=path%>/user/img/nhsybac.png');
    width: 123px;
    height: 100px;
    background-size: 100% 100%;
    /*position: relative;
    top: 30px;*/
    margin: 0 auto;
}
.nhsy{
    color: #FEDE01;
    text-align: center;
    font-size: 21px;
    position: relative;
    top: 31px;
}
.nhsy_img{
    background: url('<%=path%>/user/img/nhsy.png');
    width: 71px;
    height: 19px;
    position: relative;
    top: 35px;
    left: 25px;
    background-size: 100% 100%;
}
.touxiang{
	background: url('<%=path%>/user/img/touxiang.png');
	width:30px;
	height:30px;
	background-size: 100% 100%;
}
.color_white{
    color: white;
    text-align: center;
    font-size: 13px;
    margin-top: 5px;
}
.div_bac_white{
    height: 100%;
    border-radius: 19px;
    background: #F4F4F4;
    margin-top: -16px;
}
</style>
<body>

<div class="bac_img">
<div style="padding-top:30px;">
	<div class="div_time">
		<div class="nhsy">${reList[0]['yield_rate']}%</div>
		<div class="nhsy_img"></div>
	</div>	
</div>
		<div style="text-align: center;color:white;">
			<!--div class="touxiang"></div-->	
			<img src='${reList[0]["photo"]==""?"<%=path%>/user/img/touxiang.png":reList[0]["photo"]}' style="width: 30px;position: relative;top: 8px;">
			<span>我是${reList[0]['nickname']}</span>
		</div>
		<div style="text-align: center;margin-top: 13px;"><img src="<%=path%>/user/img/tigao.png" style="width: 200px;"></div>	
		<div class="color_white">我通过尽淘乐去淘宝买了<span style="color: #FEDE01;">${reList[0]['spend_sum']}</span>元的宝贝<br/>每天在尽淘乐赚到<span style="color: #FEDE01;">${reList[0]['shengqian']}</span>元的收益~</div>
</div>
<div class="div_bac_white">
	<div style="text-align: center;">
	<c:if test="${json=='0'}">
		 <img src="<%=path%>/user/img/btn_jiayou.png" style="width: 150px;margin-top: 20px;" id="jiayou">
	</c:if>	 
	<c:if test="${json!='0'}">
		 <img src="<%=path%>/user/img/btn_jiayou2.png" style="width: 150px;margin-top: 20px;" id="no_jiayou">
	</c:if>	 	
		 <div style="color:#666666;font-size: 13px;">今天有<span style="color:#EF6112;">${reList[0]['renshu']}</span>位好友为TA加油，共提升了<span style="color:#EF6112;">${reList[0]['yield_rate1']}%</span>年化收益</div>
	</div>
</div>
<div style="position: absolute;bottom: 0px;background: white;"><a href="<%=request.getContextPath()+"/apks/jintaole.apk"%>"><img src="<%=path%>/user/img/xiazai.png" style="width:100%;"></a></div>
</body>
</html>