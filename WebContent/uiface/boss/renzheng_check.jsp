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

<title>认证进度查看</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 认证管理 <span class="c-gray en">&gt;</span> <!-- 文章列表  --><a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="mt-20">
	<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper ">
	<form id="memberForm">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="30">序号</th>
				<th width="40">用户ID</th>
			
				
				<th width="40">头像</th>
				<th width="40">昵称</th>
				<th width="40">年龄</th>
				<th width="40">城市</th>
				<th width="40">个性签名</th>
				<th width="40">相册</th>
			
				<th width="40">自拍照</th>
				
				<th width="40">审核状态</th>
				<!-- <th width="40">审核时间</th> -->
				<th width="40">操作</th>
			</tr>
		</thead>
		<tbody id="list-content" >
		<c:forEach var="map" items="${reList}" varStatus="status">
			<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;"><div id="innerdiv" style="position:absolute;"><img id="bigimg" style="border:5px solid #fff;" src="" /></div></div>
			<tr class="text-c">
				<td>${status.count}</td>
				<td>${map['u_user_id']}</td>
				
				<td><img class="pimg" alt="" src="${map['photo']}" style="width:80px"></td>
				<td>${map['nickname']}</td>
				<td>${map['age'] }</td>
				<td>${map['city'] }</td>
				<td>${map['signature'] }</td>
				<td><a href="javascript:;"
					onclick="system_category_edit('认证图片','<%=path %>/ar?p0=A-boss-search&p1=renzheng_photosearch&p2=${map['id'] }&p3=0','600','510')"
					style="text-decoration: none;color:blue;" class="ml-5">点击查看相册</a>
				</td>
				<td><img class="pimg" alt="" src="${map['selfie']}" style="width:80px"></td>
				<td>${map['result'] }</td>
				<td class="td-manage">
				   <a title="通过" href="javascript:;" onclick="system_category_edit1(${map['id']},${map['user_id']},'${map['nickname']}')" class="ml-5" style="text-decoration:none;color:green;">通过</a>  
				   <a title="不通过" href="javascript:;" onclick="go(${map['id']},${map['user_id']},'${map['nickname']}')" class="ml-5" style="text-decoration:none;color:red;">不通过</a>	 
				</td>
			</tr>
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
<script type="text/javascript">

var curUrl = "";
 //刷新列表     
function fresh_page(pageIndex) {
	//var name = document.getElementById("searchname").value;
		curUrl = "<%=path %>/ar?a=A-boss-search&b=renzheng_v&p3="+pageIndex+"&page=tojson";
		$.ajax({
			cache:true,
			type:"post",
			url: curUrl,
			async: true,
			error: function(request) {
				alert("提交失败 ");
			},
			success:function(data){
				var json=eval("("+data+")");
				var content = '';
				for(var i = 0;i<json.length-1;i++){
					
					/* +'<td><img alt="" src="'+json[i].user_photo+'" style="width:80px"></td>' */
						
					
					content +='<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;"><div id="innerdiv" style="position:absolute;"><img id="bigimg" style="border:5px solid #fff;" src="" /></div></div>' 
							+'<tr class = "text-c">'
							+'<td>' +(Number(json[json.length-1].current)+1+i)+'</td>'
							+'<td>'+json[i].u_user_id+'</td>'
							+'<td><img class="pimg" onclick="imgShow2(\''+json[i].photo+'\')" alt="" src="'+json[i].photo+'" style="width:80px"></td>'
				
							
							+'<td>'+json[i].nickname+'</td>'
							+'<td>'+json[i].age+'</td>'
							+'<td>'+json[i].city+'</td>'
							+'<td>'+json[i].signature+'</td>'
						    +'<td><a href="javascript:;" onclick="system_category_edit(\'认证图片\',\'<%=path %>/rp?p0=A-boss-search&p1=renzheng_photosearch&p2='+json[i].id+'&p3=0\',\'600\',\'510\')"  ><span style="color:blue;">点击查看相册</span></a></td>'
						    +'<td><img alt="" onclick="imgShow2(\''+json[i].selfie+'\')"  src="'+json[i].selfie+'" style="width:80px"></td>'
						    
						    
						    +'<td style="color:blue;">'+json[i].result+'</td>'
						    
						    +'<td class="td-manage">'
							+'<a title="通过" href="javascript:;" onclick="system_category_edit1('+json[i].id+','+json[i].user_id+',\''+json[i].nickname+'\') "  class="ml-5" style="text-decoration:none;color:green;">通过</a>'
							+'<a title="不通过" href="javascript:;" onclick="go('+json[i].id+','+json[i].user_id+',\''+json[i].nickname+'\')" class="ml-5" style="text-decoration:none;color:red;">未通过</a>'
							+'</td>'
						    ;
							
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

function client_del(obj,id){
	
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '<%=path%>/ep?p0=A-boss-delete&p1=del_baby&p2='+id,
			success: function(data){
				/*$(obj).parents("tr").remove();*/
				layer.msg('已删除!',{icon:1,time:1000});
				setTimeout(function() { 
					if(curUrl == "") {
						javascript:location.replace(location.href);
					} else {
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
//模糊查询
$("#btn-search").click(function(){
	fresh_page(1)
});
function system_category_edit(title,url,w,h){
	layer_show(title,url,w,h);
}
function system_category_edit1(id,userid,nickname){
	//layer_show(title,url,w,h);
	layer.confirm('确认用户      '+nickname+'  成为女神？',function(index){
	$.ajax({
		type: 'POST',
		url:'<%=path%>/ar?p0=A-boss-mod&p1=renzheng_checkpass&p2='+id+'&p3='+userid+"&p4=&p5=",
		type:'POST',
        dataType:'JSON',
        /* data:{'job':job}, */
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
			alert('提交失败');
		},
	});		
	});
	
	
}
function go(id,userid,nickname){
	
	age = prompt("请输入未通过原因","");
	if(age){
		layer.confirm('确认输入以上内容？',function(index){
			$.ajax({
				type:'POST',
				url: '<%=path%>/ar?p0=A-boss-mod&p1=renzheng_checknopass&p2='+id+'&p3='+userid+"&p4=&p5=",
				type:'POST',
	            dataType:'JSON',
	            data:{'age':age},
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
	}else if(age==""){
		alert("不能为空，请输入内容");
	}else{
		
	}

	
	 /* age = prompt("请输入未通过原因","");
	 if(age==""){
		 alert("未通过原因不能为空");
		 return;
	 } */
	<%-- layer.confirm('确认输入以上内容？',function(index){ 

		$.ajax({
			type:'POST',
			url: '<%=path%>/ar?p0=A-boss-mod&p1=renzheng_checknopass&p2='+id+'&p3='+userid,
			type:'POST',
            dataType:'JSON',
            data:{'age':age},
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
	 });  --%>
	
	
	
	
}

function system_category_add(title,url,w,h){
	layer_show(title,url,w,h);
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