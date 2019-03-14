<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath()+"/uiface";

response.setHeader("refresh", "60");
//response.setHeader("refresh", "3;url=/ResponseDemo/ResponseDemo10");


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
<title>鉴黄管理</title>
</head>
<body>
<audio autoplay="autoplay" controls="controls"loop="loop" preload="auto"
            	src="<%=path%>/img/fengkong.mp3" style="display:none;" id="myaudio" ></audio>
            	
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 
	<span class="c-gray en">&gt;</span> 鉴黄管理 
	<span class="c-gray en">&gt;</span> 鉴黄列表
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
		<i class="Hui-iconfont">&#xe68f;</i>
	</a>
</nav>
<div class="page-container">
	<div class="text-c">
		 <div class="mt-20">
			<div class="text-c">
			
			
					ID：
				<input type="text" class="input-text" style="width:250px"  placeholder="请输用户的ID号" id="searchnum" name="searchnum">	
					昵称：
				<input type="text" class="input-text" style="width:250px"  placeholder="请输用户的昵称" id="searchtxt" name="searchtext">	
				
				
				<select class="input-text" style="width:150px" id="searchname1" name="">
					
					<option value="">鉴黄评定建议</option>
					<option value="pass">正常</option>
					<option value="review">涉黄</option>
				</select>
				
				<select class="input-text" style="width:150px" id="searchname2" name="">
					
					<option value="">暴恐评定建议</option>
					<option value="pass">正常</option>
					<option value="review">涉恐</option>
				</select>
				
				<button type="submit" class="btn btn-success radius" id="searchbtn" name=""><i class="Hui-iconfont"></i>搜索</button>
			</div>	
		</div> 
		
	<div class="mt-20">
	<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper ">
	<!-- <span>注:财富记录为男用户充值记录，魅力记录为女用户收礼记录</span> -->
	<table class="table table-border table-bordered table-hover table-bg table-sort" id="tab">
		<thead>
		
			<tr class="text-c">
				<th width=20>序号</th>
				<th width="20">用户ID</th>
				<th width="40">用户昵称</th>
				<th width="40">联系方式</th>
				<th width="40">时间</th>
				<th width="40">图片</th>
				<th width="40">鉴黄分类指数</th>
				<th width="40">鉴黄评定建议</th>
				<th width="40">鉴黄评定分类</th>
				<th width="40">暴恐分类指数</th>
				<th width="40">暴恐评定建议</th>
				<th width="40">暴恐评定分类</th>
				<th width="40" style="display:none;">违规</th>
				<th width="40">处理时间</th>
				<th width="40">操作(标记处理)</th>
				<th width="40">操作(封禁)</th>
			</tr>
		</thead>
		<tbody id="list-content">
			<c:forEach var = "map" items="${reList }" varStatus="status">
				<tr class="text-c">
				<!-- 字段：序号、ID、头像、用户名、昵称、性别、年龄、身高、体重、城市、微信、微博、qq、支付宝、账户余额、操作(封禁、解封) -->
					<td>${status.count}</td>
					<td>${map['u_user_id']}</td>
					<td id="nc">${map['nickname']}</td>
					<td>${map['phone']}</td>		
										
					<td>${map['time']}</td>			
					
					<td><img class="pimg" alt="" src="${map['img']}" style="width:80px"></td>
					
					<td>${map['porn_rate']}</td>
					<td id="sh">
					<c:choose>
							<c:when test="${map['porn_suggestion']=='pass' }">正常</c:when>
							<c:when test="${map['porn_suggestion']=='review' }">涉黄</c:when>
					</c:choose>
					</td>
					
					<td>
					<c:choose>
							<c:when test="${map['porn_label']=='normal' }">正常图片</c:when>
							<c:when test="${map['porn_label']=='sexy' }">性感图片</c:when>
							<c:when test="${map['porn_label']=='porn' }">色情图片</c:when>
					</c:choose>
					</td>
					
					
					<td>${map['terrorism_rate']}</td>
					
					
					<td id="sk">
					<c:choose>
							<c:when test="${map['terrorism_suggestion']=='pass' }">正常</c:when>
							<c:when test="${map['terrorism_suggestion']=='review' }">涉恐</c:when>
					</c:choose>
					</td>
					<td>
					<c:choose>
							<c:when test="${map['terrorism_label']=='normal' }">正常图片</c:when>
							<c:when test="${map['terrorism_label']=='bloody' }">血腥</c:when>
							<c:when test="${map['terrorism_label']=='explosion' }">爆炸烟光</c:when>
							<c:when test="${map['terrorism_label']=='outfit' }">特殊装束</c:when>
							<c:when test="${map['terrorism_label']=='logo' }">特殊标识</c:when>
							<c:when test="${map['terrorism_label']=='weapon' }">武器</c:when>
							<c:when test="${map['terrorism_label']=='politics' }">渉政</c:when>
							<c:when test="${map['terrorism_label']=='others' }">其它</c:when>
					</c:choose>
					</td>
					<td style="display:none;" id="wg">${map['sh']}</td>
					<td>
						<c:if test="${map['result']=='处理中' }">${map['result'] }</c:if>
						<c:if test="${map['result']=='已处理' }">${map['auditing_time'] }</c:if>
					</td>
					<td>
						<c:if test="${map['result']=='处理中' }">
							<a href="javascript:;" onclick="sign(${map['id']})" style="text-decoration: none;color:red;" class="ml-5">标记处理</a>
							|
							<a href="javascript:;" onclick="del(${map['id']})" style="text-decoration: none;color:red;" class="ml-5">删除</a>
						</c:if>
						<c:if test="${map['result']=='已处理' }">
							<a href="javascript:;" onclick="del(${map['id']})" style="text-decoration: none;color:red;" class="ml-5">删除</a>
						</c:if>
					</td>
					<td class="td-manage">
							<a href="javascript:;"
							onclick="banned(${map['id']},${map['u_user_id']},'${map['nickname']}')" 
							style="text-decoration: none;color:blue;" class="ml-5">封禁</a>
							
							<a href="javascript:;"
							onclick="no_banned(${map['u_user_id']},'${map['nickname']}')" 
							style="text-decoration: none;color:blue;" class="ml-5">解禁</a>
					</td>
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
		var mil_num = $("#searchnum").val();
		var mil_txt = $("#searchtxt").val();
		/* 1:获取输入的性别 */
		/* 2:获取输入的起始时间和结束时间 */
		/* 固定死判断条件，如性别，时间段：开始时间---结束时间 */
		//2种搜索条件,第一种满足就用第一种刷新；第二种满足就用第二种刷新
		/* alert("提交失败 "); */
		fresh_page(1);
		
	});
});

/*刷新列表*/

 var curUrl = "";
function fresh_page(pageIndex){
	/* var mil_id = $("#searchtxt").val(); */
		var mil_num = $("#searchnum").val();
		var mil_txt = $("#searchtxt").val();
		
		var searchname1=$("option:selected","#searchname1").val();
		var searchname2=$("option:selected","#searchname2").val();
		
		
		
		
		/* alert("进入搜索 "); */
	/* 获取输入的性别 */
	/* 获取输入的起始时间和结束时间 */
	/* 固定死判断条件，如性别，时间段：开始时间---结束时间 */
	<%-- url: "<%=path%>/rp?p0=A-boss-search&p1=memberbackstage&p2="+pageIndex+"&p3=&p4=&p5="+sele_condition+"&p6=tojson&p7="+mil_id , --%>
	<%-- url: "<%=path%>/rp?p0=A-boss-search&p1=ar&p2="+pageIndex+"&p3="+mil_id+"&p4="+sele_condition+"&p6=tojson , --%>
	<%-- url: "<%=path%>/ar?p0=A-boss-search&p1=jianhuang_list&p2="+pageIndex+"&p3=tojson&p4="+mil_num+"&p5="+mil_txt , --%>
	
	curUrl = "<%=path%>/ar?p0=A-boss-search&p1=jianhuang_list&p2="+pageIndex+"&p3=tojson&p4="+mil_num+"&p5="+mil_txt+"&p6="+searchname1+"&p7="+searchname2;
	
	
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
			var jianhuang1 = '';
			var jianhuang2 = '';
			var baokong1 = '';
			var baokong2 = '';
			var fengjin = '';
			var sh = '';
			
			var auditing = '';
			var control = '';
			
			for(var i = 0; i < json.length-1; i++) {
				
				
				if(json[i].porn_suggestion=='review'){
					jianhuang1='涉黄';
				}else{
					jianhuang1='正常';
				}
				/* if(jianhuang1=='涉黄'&&json[i].result=='处理中'){
					alert("用户"+json[i].nickname+"色情警报");
				} */
				
				
				if(json[i].porn_label=='sexy'){
					jianhuang2='性感图片';
				}else if(json[i].porn_label=='porn'){
					jianhuang2='色情图片';
				}else{
					jianhuang2='正常图片';
				}
				
				if(json[i].terrorism_suggestion=='review'){
					baokong1='涉恐';
				}else{
					baokong1='正常';
				}
				
				if(json[i].terrorism_label=='bloody'){
					baokong2='血腥';
				}else if(json[i].terrorism_label=='explosion'){
					baokong2='爆炸烟光';
				}else if(json[i].terrorism_label=='outfit'){
					baokong2='特殊装束';
				}else if(json[i].terrorism_label=='logo'){
					baokong2='特殊标识';
				}else if(json[i].terrorism_label=='weapon'){
					baokong2='武器';
				}else if(json[i].terrorism_label=='politics'){
					baokong2='渉政';
				}else if(json[i].terrorism_label=='others'){
					baokong2='其它';
				}else{
					baokong2='正常图片';
				}
				sh = '<td style="display:none;" id="wg">'+json[i].sh+'</td>';
				
				fengjin = '<td><a href="javascript:;" onclick="banned('+json[i].id+','+json[i].u_user_id+',\''+json[i].nickname+'\')"  ><span style="color:blue;">封禁</span></a>  <a href="javascript:;" onclick="no_banned('+json[i].u_user_id+',\''+json[i].nickname+'\')"  ><span style="color:blue;">解封</span></a></td>';
				
				if(json[i].result=='处理中'){
					auditing='<td>'+json[i].result+'</td>';
					control='<td><a href="javascript:;" onclick="sign('+json[i].id+')" style="text-decoration: none;color:red;" class="ml-5">标记处理</a>|<a href="javascript:;" onclick="del('+json[i].id+')" style="text-decoration: none;color:red;" class="ml-5">删除</a></td>';
				}else if(json[i].result=='已处理'){
					auditing='<td>'+json[i].auditing_time+'</td>';
					control='<td><a href="javascript:;" onclick="del('+json[i].id+')" style="text-decoration: none;color:red;" class="ml-5">删除</a></td>';
				}
				
				var src = json[i].img;
				 content +='<tr class="text-c">'
					+'<td>'+(Number(json[json.length-1].current)+1+i)+'</td>'
					
					+'<td>'+json[i].u_user_id+'</td>'
					+'<td>'+json[i].nickname+'</td>'
					+'<td>'+json[i].phone+'</td>'
					+'<td>'+json[i].time+'</td>'
					/* +'<td>'+json[i].img+'</td>' */
					+'<td><img onclick="imgShow2(\''+src+'\')" id="img" alt="" src="'+json[i].img+'" style="width:80px"></td>'
					+'<td>'+json[i].porn_rate+'</td>'
					
					+'<td>'+jianhuang1+'</td>'
					
					+'<td>'+jianhuang2+'</td>'
					
					
					
					
					+'<td>'+json[i].terrorism_rate+'</td>'
					+'<td>'+baokong1+'</td>'
					+'<td>'+baokong2+'</td>'
					+sh
					+auditing
				    +control
					/* +'<td>'+json[i].terrorism_suggestion+'</td>'
					+'<td>'+json[i].terrorism_label+'</td>' */
					
					+fengjin
					
					; 
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

<%-- function system_category_edit1(id,userid){
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
} --%>
function system_category_edit1(title,url,id,w,h){
	layer_show(title,url,w,h);
}

function go(id){
	/* age = prompt("请输入未通过原因","");
	layer.confirm('确认输入以上内容？',function(index){ */

		$.ajax({
			type:'POST',
			url: '<%=path%>/ar?p0=A-boss-del&p1=user_del&p2='+id,
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

function autoPlay(){
	var myAuto = document.getElementById('myaudio');
	myAuto.play();
	}

function closePlay() {
    var myAuto = document.getElementById('myaudio');
    
    myAuto.pause();
    myAuto.load();
}


$(function(){
	//setInterval("myInterval()",3000);
	var sh = document.getElementById("sh").innerText;
	var sk = document.getElementById("sk").innerText;
	var nc = document.getElementById("nc").innerText;
	var wg = document.getElementById("wg").innerText;
	//alert("123"+wg);
	
	var tableId = document.getElementById("tab"); 		
	var str = "";		
	var name = "";
	var sh = "";
	for(var i=1;i<tableId.rows.length;i++){
		sh = tableId.rows[i].cells[12].innerHTML;
		name = tableId.rows[i].cells[2].innerHTML;
		//alert("涉黄"+sh);
		//alert("涉黄"+name);
		 if("t_sh"==tableId.rows[i].cells[12].innerHTML){
			autoPlay();
			alert("用户"+name+"涉嫌色情违规,请注意查看");
			
			
			//setTimeout(function () { 
				closePlay();
		    //}, 2000);
			
			return;
		}
			
	}

	
	
	/* var sh = $("#sh").val();
	var sk = $("#sk").val();
	var sk = $("#nc").val(); */
	//alert('sk'+sk);
	
	
}); 

$(".pimg").click(function(){
	//alert("1");
	var _this = $(this);//将当前的pimg元素作为_this传入函数
	imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
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






function go(id,userid){
	/* age = prompt("请输入未通过原因","");
	layer.confirm('确认输入以上内容？',function(index){ */

		$.ajax({
			type:'POST',
			url: '<%=path%>/anran?p0=A-boss-mod&p1=zhubo_nopass&p2='+id,
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

/**查看用户相册*/
function client_xiangce(title,url,id,w,h){
	layer_show(title,url,w,h);	
}
/**查看用户相册*/
function client_shipin(title,url,id,w,h){
	layer_show(title,url,w,h);	
}


<%-- 
function banned(id){
	/* age = prompt("请输入未通过原因","");
	layer.confirm('确认输入以上内容？',function(index){ */

		$.ajax({
			type:'POST',
			url: '<%=path%>/ar?p0=A-boss-mod&p1=anchor_banned&p2='+id,
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
function no_banned(id){
	/* age = prompt("请输入未通过原因","");
	layer.confirm('确认输入以上内容？',function(index){ */

		$.ajax({
			type:'POST',
			url: '<%=path%>/ar?p0=A-boss-mod&p1=banned_cancel&p2='+id,
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
} --%>

function xiangqing(id){
	/* age = prompt("请输入未通过原因","");
	layer.confirm('确认输入以上内容？',function(index){ */

		$.ajax({
			type:'POST',
			url: '<%=path%>/anran?p0=A-boss-search&p1=xiangqing&p2='+id,
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

function sign(e){
	closePlay();
	$.ajax({
		type:'POST',
		url:'<%=path %>/ar?p0=A-boss-mod&p1=jianhuang_cl&p2='+e+'&p3=&p4=',
		success:function(data){
			layer.msg('标记处理成功',{icon:1,time:1000});
			setTimeout(function(){
				if(curUrl == ""){
					javascript:location.replace(location.href);	
				}else{
					javascript:location.replace(curUrl.replace('tojson','tojsp'));
				}
				
			},1000);
		},
		error:function(data){
			alert('处理失败！');
		}
	});
}

function del(g){
	closePlay();
	$.ajax({
		type:'POST',
		url:'<%=path %>/ar?p0=A-boss-delete&p1=jianhuang_sc&p2='+g,
		success:function(data){
			layer.msg('删除成功',{icon:1,time:1000});
			setTimeout(function(){
				if(curUrl == ""){
					javascript:location.replace(location.href);
				}else{
					javascript:location.replace(curUrl.replace('tojson','tojsp'));
				}
				
			},1000);
		},
		error:function(data){
			alert('删除失败！');
		}
	});
}

function banned(id,user_id,nickname){
	closePlay();
	layer.confirm('确认封禁用户      '+nickname+'？',function(index){
		$.ajax({
			type:'POST',
			url: '<%=path%>/ar?p0=A-boss-mod&p1=anchor_banned_jh&p2='+id+'&p3='+user_id,
			success: function(data){
				
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
function no_banned(id,nickname){
	/* age = prompt("请输入未通过原因","");
	layer.confirm('确认输入以上内容？',function(index){ */
		layer.confirm('确认解封用户      '+nickname+'？',function(index){
		$.ajax({
			type:'POST',
			url: '<%=path%>/ar?p0=A-boss-mod&p1=banned_cancel_jh&p2='+id,
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



function system_category_edit(title,url,w,h){
	layer_show(title,url,w,h);
}





</script> 
</body>
</html>