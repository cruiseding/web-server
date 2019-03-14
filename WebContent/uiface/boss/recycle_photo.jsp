<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName();
int webPort = request.getServerPort();
if(webPort != 80) {
	basePath = basePath+":"+webPort;
}
String path = basePath+"/uiface";
%>
<%!
Boolean bool = true;
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

<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/uiface/boss/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/uiface/boss/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/uiface/boss/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/uiface/boss/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/uiface/boss/static/h-ui.admin/css/style.css" />



<title>图片轮播设置</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		设置
		<span class="c-gray en">&gt;</span> 广告轮播图设置
		<a class="btn btn-success radius r btn-refresh"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="Hui-iconfont">&#xe68f;</i>
		</a>
	</nav>
	<div class="page-container">
		
		<div class="cl pd-5 bg-1 bk-gray mt-20">
		     <a href="javascript:;" onclick="proj_edit('添加轮播图','<%=basePath%>/uiface/boss/photo_add.jsp','600','510')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加图片</a>
		     
			<span class="r">共有数据：<strong>${pageNo[1]}</strong> 条
			</span>
		</div>
		<div class="mt-20">
			<div class="dataTables_wrapper">
				<table
					class="table table-border table-bordered table-hover table-bg">
					<thead>
					<tr class="text-c">
					<th width="30">序号</th>
					<th width="40">排序</th>
					<th width="30">轮播标题</th>
					<th width="40">轮播图内容</th>
					<th width="30">轮播图片</th>
					<th width="40">位置</th>
					<th width="40">状态</th>
					<th width="40">操作</th>
					</tr>
					</thead>
					<tbody id="list-content">
						<c:forEach var="map" items="${reList}" varStatus="status">
							<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;"><div id="innerdiv" style="position:absolute;"><img id="bigimg" style="border:5px solid #fff;" src="" /></div></div>
							<tr class="text-c">
								<td>${status.count}</td>
								<td>${map['serial_number']}</td>
								<td>${map['title']}</td>
								<td>${map['chained_address']}</td>
								<td>
								<%-- <a href="javascript:;" onclick="proj_edit('查看轮播图','<%=path%>/ar?p0=A-boss-search&p1=get_photo&p2=${map['id'] }',900','600')" style="text-decoration:none;color:green;" class="ml-5">查看</a> --%>
								<a href="javascript:;" onclick="system_category_edit('查看轮播图','<%=path%>/ar?p0=A-boss-search&p1=get_photo&p2=${map['id'] }','1500','800')" style="text-decoration: none;color:blue;" class="ml-5">查看</a>
								</td>
								
								<td>${map['type']}</td>
								<td>${map['result']}</td>
								<c:if test="${map['result']=='显示' }">
									<td class="td-manage"><a title="编辑" href="javascript:;"
										onclick="proj_edit('编辑','<%=path%>/ar?p0=A-boss-mod&p1=photo2&p2=${map['id']}','600','510')"
										class="ml-5" style="text-decoration: none"><i
											class="Hui-iconfont">&#xe6df;</i></a> <a title="删除"
										href="javascript:;"
										onclick="client_del(this,${map['id']})" class="ml-5"
										style="text-decoration: none"><i class="Hui-iconfont">&#xe6e2;</i></a>
										<a href="javascript:;" onclick="hide(${map['id']})" style="text-decoration:none;color:green;">隐藏</a>
									</td>
								</c:if>
								<c:if test="${map['result']=='隐藏' }">
									<td class="td-manage"><a title="编辑" href="javascript:;"
										onclick="proj_edit('编辑','<%=path%>/ar?p0=A-boss-mod&p1=photo2&p2=${map['id']}','600','510')"
										class="ml-5" style="text-decoration: none"><i
											class="Hui-iconfont">&#xe6df;</i></a> <a title="删除"
										href="javascript:;"
										onclick="client_del(this,${map['id']})" class="ml-5"
										style="text-decoration: none"><i class="Hui-iconfont">&#xe6e2;</i></a>	
										<a href="javascript:;" onclick="appear(${map['id']})" style="text-decoration:none;color:green;">显示</a>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="dataTables_info" id="DataTables_Table_0_info"
					role="status" aria-live="polite">
					显示 <span id="pagefirst">${pageNo[2]+1}</span> 到 <span id="pagelast">${pageNo[3]}</span>
					，共 <span id="total">${pageNo[1]}</span>条
				</div>
				<div class="dataTables_paginate paging_simple_numbers"
					id="DataTables_Table_0_paginate">
					<a class="paginate_button previous disabled"
						aria-controls="DataTables_Table_0" data-dt-idx="0" tabindex="0"
						id="DataTables_Table_0_previous">上一页 </a><span><a
						class="paginate_button current" aria-controls="DataTables_Table_0"
						data-dt-idx="1" tabindex="0"><span id="currentpage">${pageNo[4]}</span></a></span>
					<a class="paginate_button next disabled"
						aria-controls="DataTables_Table_0" data-dt-idx="2" tabindex="0"
						id="DataTables_Table_0_next">下一页</a>
				</div>
			</div>
		</div>
	</div>
	<div></div>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="<%=basePath%>/uiface/boss/lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript"
		src="<%=basePath%>/uiface/boss/lib/jquery/1.9.1/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>/uiface/boss/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/uiface/boss/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/uiface/boss/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="<%=basePath%>/uiface/boss/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/uiface/boss/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/uiface/lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript">
var totalpage = ${pageNo[0]};
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
	$("#searchbtn").click(function() {
		var currentpage = Number($("#currentpage").html());
		var a = $("#searchtxt").val();
		if(a=="") {
			alert('请输入会员id');
			return;
		}
		fresh_page(1);
	});
});
function fresh_page(pageIndex) {
	var a = $("#searchtxt").val();
	
	$.ajax({
		cache: true,
		type: "POST",
		//arg[2]会员id arg[3]当前页数
		//ar?p0=A-boss-search&p1=recycle_photo&p2=1&p3=tojsp
		url:"<%=path%>/ar?p0=A-boss-search&p1=recycle_photo&p2="+pageIndex+"&p3=tojson",
		
		async: true,
		error: function(request) {
			alert("提交失败 ");
		},
		success: function(data) {
			var json=eval("("+data+")");
			var content = '';
			var control = '';
			for(var i = 0; i < json.length-1; i++) {
				
				if(json[i].result=='显示'){
					control = '<td class="td-manage"><a title="编辑" href="javascript:;" onclick="proj_edit(\'编辑\',\'<%=path%>/ar?p0=A-boss-mod&p1=photo2&p2='+json[i].id+'\',\'600\',\'510\')" class="ml-5" style="text-decortation: none"><i class="Hui-iconfont">&#xe6df;</i></a> <a title="删除" href="javascript:;" onclick="client_del(this,'+json[i].id+')" class="ml-5" style="text-decoration: none"><i class="Hui-iconfont">&#xe6e2;</i></a><a href="javascript:;" onclick="hide('+json[i].id+')" style="text-decoraion:none;color:green;">隐藏</a></td>';
				}else if(json[i].result=='隐藏'){
					control = '<td class="td-manage"><a title="编辑" href="javascript:;" onclick="proj_edit(\'编辑\',\'<%=path%>/ar?p0=A-boss-mod&p1=photo2&p2='+json[i].id+'\',\'600\',\'510\')" class="ml-5" style="text-decortation: none"><i class="Hui-iconfont">&#xe6df;</i></a> <a title="删除" href="javascript:;" onclick="client_del(this,'+json[i].id+')" class="ml-5" style="text-decoration: none"><i class="Hui-iconfont">&#xe6e2;</i></a>	<a href="javascript:;" onclick="appear('+json[i].id+')" style="text-decoraion:none;color:green;">显示</a></td>';
				}
				content +='<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;"><div id="innerdiv" style="position:absolute;"><img id="bigimg" style="border:5px solid #fff;" src="" /></div></div>'
					+'<tr class="text-c">'
					+'<td>'+(Number(json[json.length-1].current)+1+i)+'</td>'
					+'<td>'+json[i].serial_number+'</td>'
					/* +'<td><img class="pimg" onclick="imgShow2(\''+json[i].photo+'\')" alt="" src="'+json[i].carousel_photo+'" style="width:80px"></td>' */
					+'<td>'+json[i].chained_address+'</td>'
					+'<td><a href="javascript:;" onclick="system_category_edit(\'查看轮播图\',\'<%=path%>/ar?p0=A-boss-search&p1=get_photo&p2='+json[i].id+'\',\'1500\',\'800\')" style="text-decoration:none;color:green;" class="ml-5">查看</a></td>'
					+'<td>'+json[i].type+'</td>'
					+'<td>'+json[i].result+'</td>'
					+control
					
					
					
					+'</tr>';
				
				
				
				
				
				
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
function proj_edit(title,url,w,h){
	layer_show(title,url,w,h);
}
/*查询娃娃详情*/
function client_search(title,url,w,h){
	layer_show(title,url,w,h);
}
function system_category_edit(title,url,w,h){
	layer_show(title,url,w,h);
}
function proj_editor(title,url,w,h){
	layer_show(title,url,w,h);
}
/*用户-删除*/
function client_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '<%=path%>/ar?p0=A-boss-delete&p1=photo_del&p2='+id,
			success: function(data){
				/*$(obj).parents("tr").remove();*/
				layer.msg('已删除!',{icon:1,time:1000});
				setTimeout(function () { 
					javascript:location.replace(location.href);
			    }, 1000);
			},
			error:function(data) {
				alert('提交失败');
			},
		});		
	});
}
function hide(id){
		$.ajax({
			type: 'POST',
			url: '<%=path%>/ar?p0=A-boss-mod&p1=recycle_hide&p2='+id,
			success: function(data){
				/*$(obj).parents("tr").remove();*/
				layer.msg('隐藏成功!',{icon:1,time:1000});
				setTimeout(function () { 
					javascript:location.replace(location.href);
			    }, 1000);
			},
			error:function(data) {
				alert('隐藏失败！');
			},
		});		
}
function appear(id){
	$.ajax({
		type: 'POST',
		url: '<%=path%>/ar?p0=A-boss-mod&p1=recycle_appear&p2='+id,
		success: function(data){
			/*$(obj).parents("tr").remove();*/
			layer.msg('显示成功!',{icon:1,time:1000});
			setTimeout(function () { 
				javascript:location.replace(location.href);
		    }, 1000);
		},
		error:function(data) {
			alert('显示失败！');
		},
	});		
}
$(function(){
	$(".pimg").click(function(){
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