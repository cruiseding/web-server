<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%--声明我要使用C标签--%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
	String path = request.getContextPath() + "/uiface";
	String path2 = request.getContextPath() + "/uiface1";
	String uid=request.getParameter("uid");
%>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="favicon.ico" >
<link rel="Shortcut Icon" href="favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=path%>/uiface/boss/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/uiface/boss/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/uiface/boss/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/uiface/boss/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path%>/uiface/boss/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script><![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>代理商管理</title>
<link rel="stylesheet" href="<%=path%>/uiface/boss/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<style type="text/css">
    div#rMenu {position:absolute;visibility:hidden; top:0;background-color: #999;text-align: left;padding: 2px;}
    div#rMenu a{
        cursor: pointer;
        list-style: none outside none;
    }
</style>
</head>
<body>

 <section class="Hui-article-box1">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 代理商管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="Hui-article" >
	
		<article class="cl pd-20">
			<table class="table" >
				<tr>
					<td width="200" class="va-t">
					<div style="border-radius:5px;overflow:auto;">
					<div id="rMenu">
				     <ul>
				         <li id="m_del" class="btn btn-danger radius" onclick="del();">删除</li>
				         <li id="m_up_level" class="btn btn-danger radius" onclick="up_level();">升级</li>
				     </ul>
				 </div>
				 <ul id="treeDemo" class="ztree"></ul>
				 </div>
					</td>
					<td class="va-t"><iframe ID="testIframe" Name="testIframe" FRAMEBORDER=0 SCROLLING=AUTO width=100%  height=1000 SRC=""></iframe></td>
				</tr>
			</table>
		</article>
	</div>
</section>

	<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=basePath%>/uiface1/boss/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/uiface1/boss/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>/uiface1/boss/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="<%=basePath%>/uiface1/boss/static/h-ui.admin/js/H-ui.admin.page.js"></script>
<!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=basePath%>/uiface1/boss/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/uiface1/boss/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/uiface1/boss/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="<%=basePath%>/uiface1/boss/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
var rMenu;//右键菜单元素
var zTree;//树

var treeObj;
//在ztree上的右击事件
function OnRightClick(event, treeId, treeNode) {
	if (treeNode) {
		treeObj=zTree.selectNode(treeNode);
        if (treeNode.getParentNode()) {
            if(treeNode.getParentNode().getParentNode()){//第三级别
            	 showRMenu("secondNode", event.clientX, event.clientY);
            }else{//第二级别
            	showRMenu("firstNode", event.clientX+50, event.clientY+30);//处理位置，使用的是绝对位置
            }
        } else {
            showRMenu("root", event.clientX, event.clientY);//根节点
        }
    }
}
//显示右键菜单
function showRMenu(type, x, y) {
	 $("#rMenu ul").show();
     if (type == "root") {
         $("#m_del").hide();
         $("#m_up_level").hide();
     } else if(type == "firstNode"){
         $("#m_del").show();
         $("#m_up_level").hide();
     }else if(type == "secondNode"){
         $("#m_del").show();
         $("#m_up_level").show();
     }
     
     rMenu.css({
         "top" : y,
         "left" : x,
         "visibility" : "visible"
     });

     //在当前页面绑定 鼠标事件
     $(document).bind("mousedown", onBodyMouseDown);
}
//隐藏右键菜单
function hideRMenu() {
	 if(rMenu){
         rMenu.css({
             "visibility" : "hidden"
         });
     }
     //取消绑定
     $(document).unbind("mousedown", onBodyMouseDown);
}
//鼠标按下事件
function onBodyMouseDown(event){
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
        rMenu.css({
            "visibility" : "hidden"
        });
    }
}

//提升代理商等级
function up_level() {
	var selectedNode = zTree.getSelectedNodes()[0];
	if(confirm('确实要提升该代理商为区域代理吗?')){
		$.ajax({
			cache: true,
			type: "POST",
			url:"<%=path%>/agent?p0=A-boss-mod&p1=update_agent_level&p2="+selectedNode.id+"&p3=<%=uid%>",
			async: true,
			error: function(request) {
				alert("提交失败 ");
			},
			success: function(data) {
				alert(data);
				setTimeout('document.location.href=document.location.href;',2000);  
			}
		})
	}
}
//删除代理商
function del() {
	var selectedNode = zTree.getSelectedNodes()[0];
	if(confirm('确实要删除该代理商吗?')){
		$.ajax({
			cache: true,
			type: "POST",
			url:"<%=path%>/agent?p0=A-boss-mod&p1=update_agent_del&p2="+selectedNode.id+"&p3=<%=uid%>",
			async: true,
			error: function(request) {
				alert("删除失败 ");
			},
			success: function(data) {
				alert(data);
				location.href=location.href;
			}
		})
	}
}

var setting = {
	view: {
		dblClickExpand: false,
		showLine: true,
		selectedMulti: false
	},
	async: {
        enable: true,
        url:'<%=path %>/agent?p1=A-boss-search&p2=agent_power_list&p3=<%=uid%>',
        autoParam:[],
        contentType: "application/json",
        otherParam:{},
        dataFilter: filter
    },
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "parent_id",
			rootPId: "0"
		}
	},
	callback: {
		onClick: function(treeId, treeNode) {
			var treeObj = $.fn.zTree.getZTreeObj(treeNode);  
            var selectedNode = treeObj.getSelectedNodes()[0];  
            $("#testIframe").attr("src",encodeURI(encodeURI("<%=path %>/agent?p0=A-boss-search&p1=agent_power_edit&uid="+selectedNode.id)));
        },
        onRightClick: OnRightClick
	}
};
function filter(treeId, parentNode, childNodes) {
    if (!childNodes) return null;
    for (var i=0, l=childNodes.length; i<l; i++) {
        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
    }
    return childNodes;
}

$(document).ready(function(){
	zTree= $.fn.zTree.init($("#treeDemo"), setting);
	 rMenu = $("#rMenu");
});  
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>