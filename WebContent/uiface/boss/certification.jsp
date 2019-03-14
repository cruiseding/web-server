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

<link rel="stylesheet" type="text/css" href="<%=path %>/boss/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/boss/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/boss/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/boss/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path %>/boss/static/h-ui.admin/css/style.css" />

<title>实名认证管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 实名认证管理 <span class="c-gray en">&gt;</span> <!-- 文章列表  --><a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">

	
	<div class="mt-20">
			<div class="text-c">
				
				<input type="text" class="input-text" style="width: 100px;" placeholder="ID号" id="searchtxt" name="searchtext">
				<input type="text" class="input-text" style="width: 100px;" placeholder="昵称" id="searchtxt1" name="searchtext1">
				<!-- <input type="text" class="input-text" style="width: 100px;" placeholder="手机号" id="phone" name="phone"> -->
				
						
				<span>&nbsp;&nbsp;&nbsp;&nbsp;认证时间:</span>
				<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;" name="">
			
				- <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;" name=""> 
				<span>&nbsp;&nbsp;&nbsp;&nbsp;审核状态:</span>
	        	<select class="input-text" style="width:150px" id="result" name="result" >
					<option value="">全部</option>
					<option value="审核中">审核中</option>
					<option value="已通过">已通过</option>
					<option value="未通过">未通过</option>
				</select>
				
			<button type="submit" class="btn btn-success radius" id="searchbtn" name="" ><i class="Hui-iconfont"></i>搜用户</button>
				
			</div>
		</div>
	
	


	<div class="mt-20">
			<!-- <div class="text-c">	
				<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span>审核状态:</span>
				<select class="input-text" style="width:150px" id="searchname" name="searchname">
					<option></option>
					<option value="未通过">未通过</option>
					<option value="审核中">审核中</option>
					<option value="已通过">已通过</option>
				</select>
				<button type="submit" class="btn btn-success radius" id="searchbtn" name=""><i class="Hui-iconfont"></i>搜索</button>
			</div>	 -->
			
	<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper ">
	<form id="memberForm">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
			
			<!-- 序号、举报人ID、举报人昵称、举报人联系方式、被举报人ID、被举报人昵称、被举报人联系方式、举报原因 -->
				<th width="30">序号</th>
				<th width="40">ID</th>
				<th width="40">用户昵称</th>
				<th width="40">手持身份证正面照</th>
				<th width="40">身份证正面照</th>
				<th width="40">提交时间</th>
				<th width="40">审核状态</th>
				<th width="40">审核时间</th>
				<th width="40">不通过理由</th>
				<th width="40">操作</th>
			</tr>
		</thead>
		<tbody id="list-content" >
		<c:forEach var="map" items="${reList}" varStatus="status">
			<tr class="text-c">
				<td>${status.count}</td>
				<td>${map['u_user_id']}</td>
				<td>${map['nickname'] }</td>
				<td><img class="pimg1" onclick="" style="width:80px" src="${map['p_card_photo'] } "/></td>
				<td><img calss="pimg2" onclick="imgShow2(src)" style="width:80px" src="${map['card_photo'] }"/></td>
				<td>${map['time'] }</td>
				<td>${map['stat'] }</td>
				<td>${map['auditing_time'] }</td>
				<td>${map['reason'] }</td>
				<td>
					<c:if test="${map['stat']=='审核中' }">
						<a href="javascript:;" onclick="yes(${map['id']},${map['user_id'] })" style="text-decoration: none;color:green;" class="ml-5">通过</a>
						|
						<a href="javascript:;" onclick="no(${map['id']},${map['user_id'] })" style="text-decoration: none;color:red;" class="ml-5">不通过</a>
					</c:if>
					<c:if test="${map['stat']=='已通过' }">
						<a href="javascript:;" onclick="" style="text-decoration: none;color:green;" class="ml-5">${map['stat'] }</a>
					</c:if>
					<c:if test="${map['stat']=='未通过' }">
						<a href="javascript:;" onclick="" style="text-decoration: none;color:red;" class="ml-5">${map['stat'] }</a>
					</c:if>
				</td>
			</tr>
			<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;"><div id="innerdiv" style="position:absolute;"><img id="bigimg" style="border:5px solid #fff;" src="" /></div></div>
		</c:forEach>	
		</tbody>
	</table>
	</form>
	<div class="dataTables_info" id="DataTables_Table_0_info" role="status" aria-live="polite">显示 <span id="pagefirst">${pageNo[2]+1}</span> 到 <span id="pagelast">${pageNo[3]}</span> ，共 <span id="total">${pageNo[1]}</span>条</div>
	<div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate" ><a class="paginate_button previous disabled" aria-controls="DataTables_Table_0" data-dt-idx="0" tabindex="0" id="DataTables_Table_0_previous">上一页</a><span><a class="paginate_button current" aria-controls="DataTables_Table_0" data-dt-idx="1" tabindex="0"><span id="currentpage">${pageNo[4]}</span></a></span><a class="paginate_button next disabled" aria-controls="DataTables_Table_0" data-dt-idx="2" tabindex="0" id="DataTables_Table_0_next">下一页</a></div>
	</div>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path %>/boss/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=path %>/boss/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path %>/boss/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=path %>/boss/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path %>/boss/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=path%>/boss/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript">

var curUrl="";
 //刷新列表
function fresh_page(pageIndex) {
	//var name = document.getElementById("searchname").value;
	//alert(name);
	//var name = $("#searchname").val();
	
	var searchtxt=$("#searchtxt").val();
	var searchtxt1=$("#searchtxt1").val();
	var startdate=$("#datemin").val();
	var enddate=$("#datemax").val();
	
	if(startdate=="" && enddate!=""){
		alert('请选择开始时间');
		return;
	}else if(enddate=="" && startdate!=""){
		alert('请选择结束时间');
		return;
	}
	var result=$("option:selected","#result").val();
	
	curUrl = "<%=path%>/ar?p0=A-boss-search&p1=certification_manage&p2="+pageIndex+"&p3=tojson&p4="+searchtxt+"&p5="+searchtxt1+"&p6="+startdate+"&p7="+enddate+"&p8="+result;
	<%-- url: "<%=path %>/ar?a=A-boss-search&b=blacklist_manage&p3="+pageIndex+"&p4=&page=tojson", --%>
		$.ajax({
			cache:true,
			type:"post",
			
			url:curUrl,
			async: true,
			error: function(request) {
				alert("提交失败 ");
			},
			success:function(data){
				var json=eval("("+data+")");
				var content = '';
				
				for(var i = 0;i<json.length-1;i++){
					var src1=json[i].p_card_photo;
					var src2=json[i].card_photo;
					var auditing = '';
					var control = '';
					if(json[i].stat=='审核中'){
						control='<td><a href="javascript:;" onclick="yes('+json[i].id+','+json[i].user_id+')" style="text-decoration: none;color:green;" class="ml-5">通过</a>|<a href="javascript:;" onclick="no('+json[i].id+','+json[i].user_id+')" style="text-decoration: none;color:red;" class="ml-5">不通过</a></td>';
					}else if(json[i].stat=='已通过'){
						control='<td><a href="javascript:;" onclick="" style="text-decoration: none;color:green;" class="ml-5">'+json[i].stat+'</a></td>';
					}else if(json[i].stat=='未通过'){
						control='<td><a href="javascript:;" onclick="" style="text-decoration: none;color:red;" class="ml-5">'+json[i].stat+'</a></td>';
					}
					
					content += '<tr class = "text-c">'
							+'<td>' +(Number(json[json.length-1].current)+1+i)+'</td>'
							
							+'<td>'+json[i].u_user_id+'</td>'
							+'<td>'+json[i].nickname+'</td>'
						    +'<td><img class="pimg1" onclick="imgShow2(\''+src1+'\')" style="width:80px"  src="'+json[i].p_card_photo+'"/></td>'
						    +'<td><img class="pimg2" onclick="imgShow2(\''+src2+'\')" style="width:80px"  src="'+json[i].card_photo+'"/></td>'
						    +'<td>'+json[i].time+'</td>'
						    +'<td>'+json[i].stat+'</td>'
						    +'<td>'+json[i].auditing_time+'</td>'
						    +'<td>'+json[i].reason+'</td>'
						    +control
						    +'<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;"><div id="innerdiv" style="position:absolute;"><img id="bigimg" style="border:5px solid #fff;" src="" /></div></div>'
						    ;
							/* +'<td>'+json[i].exchange_price+'</td>' */
							/* +'<td class="td-manage">'
							+'<a title="通过" href="javascript:;" onclick="system_category_edit1('+json[i].id1+','+json[i].id+') "  class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">通过</i></a>'
							+'<a title="不通过" href="javascript:;" onclick="go('+json[i].id1+','+json[i].id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">未通过</i></a>'
							+'</td>' */
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

var bool;
//上一页
$("#DataTables_Table_0_previous").click(function() {
	var currentpage = Number($("#currentpage").html());
		if(currentpage <= 1) {
			alert('当前已经是第一页');
			return;
		}	
		fresh_page(currentpage-1);
	
});

// 下一页
$("#DataTables_Table_0_next").click(function() {
	var currentpage = Number($("#currentpage").html());
		var totalpage = ${pageNo[0]};
		if(currentpage >= totalpage) {
			alert('当前已经是最后一页');
			return;
		} 
		fresh_page(currentpage+1);
	
});

//模糊查询
$("#searchbtn").click(function(){
	fresh_page(1)
	
});
function system_category_edit(title,url,w,h){
	layer_show(title,url,w,h);
}
function system_category_edit1(id){
	//layer_show(title,url,w,h);
	
	$.ajax({
		type: 'POST',
		url:'<%=path%>/rp?p0=A-boss-mod&p1=album_checkpass&p2='+id,
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
function go(id,userid){
	age = prompt("请输入未通过原因","");
	layer.confirm('确认输入以上内容？',function(index){

		$.ajax({
			type:'POST',
			url: '<%=path%>/rp?p0=A-boss-mod&p1=album_checknopass&p2='+id+'&p4='+userid+'&p3='+age,
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
	});
}
function yes(e,f){
	$.ajax({
		type:'POST',
		url:'<%=path %>/ar?p0=A-boss-mod&p1=c_mod1&p2='+e+'&p3='+f+'&p4=&p5=',
		success:function(data){
			layer.msg('审核通过成功',{icon:1,time:1000});
			
			setTimeout(function(){
				if(curUrl == ""){
					//alert("location.href");	
					javascript:location.replace(location.href);
				} else {
					//alert("curUrl.replace('tojson','tojsp')");
					javascript:location.replace(curUrl.replace('tojson','tojsp'));
				}
				//javascript:location.replace(location.href);
			},1000);
		},
		error:function(data){
			alert('审核通过失败！');
		}
	});
}
function no(g,h){
	age = prompt("请输入未通过原因","");
	if(age){
		layer.confirm('确认输入以上内容？',function(index){
			
			$.ajax({
				type:'POST',
				url:'<%=path %>/ar?p0=A-boss-mod&p1=c_mod2&p2='+g+'&p3='+age+'&p4='+h+'&p5=&p6=',
				success:function(data){
					layer.msg('审核不通过成功',{icon:1,time:1000});
					setTimeout(function(){
						if(curUrl == "") {
							javascript:location.replace(location.href);
						} else {
							javascript:location.replace(curUrl.replace('tojson','tojsp'));
						}
						//javascript:location.replace(location.href);
					},1000);
				},
				error:function(data){
					alert('审核不通过失败！');
				}
			});
		
		
	});
	}else if(age==""){
		alert("原因不能为空！");
	}else{
		
	}
	
}
function system_category_add(title,url,w,h){
	layer_show(title,url,w,h);
}

$(function(){
	$(".pimg1").click(function(){
		//alert("1");
		var _this = $(this);//将当前的pimg元素作为_this传入函数
		imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
	});
}); 
$(function(){
	$(".pimg2").click(function(){
		//alert("1");
		var _this = $(this);//将当前的pimg元素作为_this传入函数
		imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
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