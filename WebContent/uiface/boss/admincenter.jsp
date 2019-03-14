<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%--声明我要使用C标签--%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% 
String basePath = request.getScheme()+"://"+request.getServerName();
	String path = request.getContextPath() + "/uiface";
String adminname ="";
String b="";
//id为1
String id = "";
if(request.getSession().getAttribute("admin")!=null&&!"".equals(request.getSession().getAttribute("admin").toString())){
	adminname=request.getSession().getAttribute("admin").toString();
	id = request.getSession().getAttribute("id").toString();
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
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico"/>
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=path%>/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path%>/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>趣缘后台管理系统</title>
<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="/aboutHui.shtml">后台管理系统</a> <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="/aboutHui.shtml">H-ui</a> 
			<!--<span class="logo navbar-slogan f-l mr-10 hidden-xs">v3.1</span> -->
			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
		
		<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
			<ul class="cl">
				<li>管理员</li>
				<li class="dropDown dropDown_hover">
					<a href="#" class="dropDown_A">admin <i class="Hui-iconfont">&#xe6d5;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>
						<!--  <li><a href="#">切换账户</a></li> -->
						<li><a href="<%=path%>/boss/adminLogin.jsp">退出</a></li>
					</ul>
				</li>
				<!--  <li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li> -->
				<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
						<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
						<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
						<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
						<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
						<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</div>
</div>
</header>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
	<%-- <% if(b.contains("用户管理")){%> --%>
		<%-- <dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe611;</i> 用户管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
				<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=memberbackstage&p2=1&p3=&p4=&p5=tojsp&p6=0" data-title="会员列表" href="javascript:void(0)">会员列表</a></li>
				
				<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=memberbackstage&p2=1&p3=&p4=&p5=tojsp&p6=0&p7=&p8=&p9=&p10=" data-title="会员列表" href="javascript:void(0)">会员列表</a></li>
				
				<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=anchor_search1&p2=1&p3=0&p4=tojsp&p5=0&p6=&p7=&p8=&p9=&p10=" data-title="女神列表" href="javascript:void(0)">女神列表</a></li>
				<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=main_user&p2=" data-title="首页" href="javascript:void(0)">首页</a></li>
				<li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=memberbackstage&p2=1&p3=&p4=&p5=0&p6=tojsp&p7=0" data-title="会员列表" href="javascript:void(0)">会员列表</a></li>
                <li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=anchor_search&p2=1&p3=0&p4=tojsp&p5=0&p6=&p7=&p8=&p9=0" data-title="未上传主播列表" href="javascript:void(0)">未上传主播列表</a></li>
                <li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=anchor_search1&p2=1&p3=0&p4=tojsp&p5=0&p6=&p7=&p8=&p9=0" data-title="主播列表" href="javascript:void(0)">主播列表</a></li>
                
				</ul>
			</dd>
		</dl>
		<% } %>
		<% if(b.contains("账务管理")){%>
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe63a;</i> 账务管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
																						
					<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=pay_table_search&p2=&p3=1&p4=&p5=&p6=tojsp&p7=0&p8=&p9=" data-title="充值记录表" href="javascript:void(0)">充值记录表</a></li>
					<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=income_table_search&p2=1&p3=&p4=&p5=tojsp&p6=0&p7=" data-title="收入明细" href="javascript:void(0)">收入明细</a></li>
					<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=income_table_search&p2=1&p3=tojsp&p4=&p5=&p6=&p7=&p8=&p9=&p10=" data-title="收入明细" href="javascript:void(0)">收入明细</a></li>
					<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=consumptionlist&p2=1&p3=tojsp&p4=&p5=&p6=&p7=&p8=&p9=&p10=" data-title="支出明细" href="javascript:void(0)">支出明细</a></li>
					
					<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=cash_withdrawal&p2=1&p3=&p4=&p5=tojsp&p6=&p7=0&p8=0&nc=&p10=" data-title="提现明细" href="javascript:void(0)">提现明细</a></li>
					
					<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=recharge_user&p2=&p3=1&p4=&p5=0&p6=tojsp" data-title="后台充值" href="javascript:void(0)">后台充值</a></li>
					<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=gift_record&p2=&p3=1&p4=&p5=&p6=tojsp&p7=" data-title="礼物明细" href="javascript:void(0)">礼物明细</a></li>
					<li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=income_table_search1&p2=1&p3=&p4=&p5=tojsp&p6=&p7=0&nc=" data-title="推广收入明细" href="javascript:void(0)">推广收入明细</a></li>
					
					<li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=cash_withdrawal&p2=1&p3=&p4=&p5=tojsp&p6=&p7=1&p8=0&nc=" data-title="推广提现明细" href="javascript:void(0)">推广提现明细</a></li>
				    
				</ul>
			</dd>
		</dl>
		<% } %>
		<% if(b.contains("认证管理")){%>
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe725;</i> 认证管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
				
					<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=renzheng_v&p2=1&p3=tojsp&p4=&p5=" data-title="待审核" href="javascript:void(0)">待审核</a></li>
					<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=renzheng_v_passed&p2=1&p3=tojsp&p4=&p5=" data-title="已通过" href="javascript:void(0)">已通过</a></li>
					<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=renzheng_v_no&p2=1&p3=tojsp&p4=&p5=" data-title="未通过" href="javascript:void(0)">未通过</a></li>
					
					
				</ul>
			</dd>
		</dl>
		<% } %>
		
		 
		
		 
		 <% if(b.contains("视频管理")){%>
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe72d;</i> 视频管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
				   <li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=video_manage&p2=0&p4=&p3=tojsp" data-title="短视频审核" href="javascript:void(0)">短视频审核</a></li> 
				</ul>
			</dd>
		</dl>
		<% } %>
		
		<% if(b.contains("用户信息修改审核")){%>
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe72d;</i> 用户信息修改审核<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
				   <li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=album_list&p2=1&p3=tojsp&p4=" data-title="用户信息修改审核" href="javascript:void(0)">用户信息修改审核</a></li> 
				</ul>
			</dd>
		</dl>
		<% } %>
		<% if(b.contains("举报管理")){%>
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe72d;</i> 举报管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
										
				  <li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=blacklist_manage&p2=1&p3=tojsp&p4=&p5=&p6=&p7=&p8=" data-title="举报管理" href="javascript:void(0)">举报管理</a></li> 
				</ul>
			</dd>
		</dl>
		<% } %>
		<% if(b.contains("设置")){%>
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe72d;</i> 设置<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
				
				<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=complaint_search&p2=1&p3=tojsp&p4=&p5=&p6=&p7=" data-title="意见反馈" href="javascript:void(0)">意见反馈</a></li>
				
				<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=fenxiao_search_set&p2=0&p4=&p3=tojsp" data-title="分销设置" href="javascript:void(0)">分销设置</a></li>
				<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=dashang_search_set&p2=0&p4=&p3=tojsp" data-title="打赏设置" href="javascript:void(0)">打赏设置</a></li>
				<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=notification_search&p2=0&p4=&p3=tojsp" data-title="系统通知" href="javascript:void(0)">系统通知</a></li>
				
				<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=vip_set&p2=0&p4=&p3=tojsp" data-title="VIP充值设置" href="javascript:void(0)">VIP充值设置</a></li> 
				<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=recycle_photo&p2=1&p3=tojsp" data-title="轮播图设置" href="javascript:void(0)">轮播图设置</a></li>
				<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=recharge_set&p2=0&p4=&p3=tojsp" data-title="趣豆充值设置" href="javascript:void(0)">趣豆充值设置</a></li>
				
				<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=cash_set&p2=0&p4=&p3=tojsp" data-title="提现相关设置" href="javascript:void(0)">提现相关设置</a></li>
				
				<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=getchatfilter" data-title="私信屏蔽关键词设置" href="javascript:void(0)">私信屏蔽关键词设置</a></li>
				<li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=fencheng_set&p2=0&p4=&p3=tojsp" data-title="主播分成相关设置" href="javascript:void(0)">主播分成相关设置</a></li> 
				<li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=recharge_set&p2=0&p4=&p3=tojsp" data-title="悦币充值设置" href="javascript:void(0)">悦币充值设置</a></li> 
				<li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=fenxiao_search_set&p2=0&p4=&p3=tojsp" data-title="分销设置" href="javascript:void(0)">分销设置</a></li> 
				<li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=notification_search&p2=0&p4=&p3=tojsp" data-title="系统通知" href="javascript:void(0)">系统通知</a></li> 
				<li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=automsg_search&p2=0&p4=&p3=tojsp" data-title="自动打招呼" href="javascript:void(0)">自动打招呼</a></li>
				
				<li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=chatset" data-title="私信收费设置" href="javascript:void(0)">私信收费设置</a></li>
				<li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=getchatfilter" data-title="私信屏蔽关键词设置" href="javascript:void(0)">私信屏蔽关键词设置</a></li>
				</ul>
			</dd>
		</dl>
		<% } %>
		<% if(b.contains("管理员管理")){%>
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe72d;</i> 管理员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
				<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=admin_list&p2=1&p3=&p4=tojsp" data-title="管理员管理" href="javascript:void(0)">管理员管理</a></li>
				</ul>
			</dd>
		</dl>
		<%  } %>  --%>
		
		<%-- <dl id="menu-product">
	            <dt><i class="Hui-iconfont">&#xe727;</i><a data-href="<%=path%>/ar?p0=A-boss-search&p1=main_user&p2=" data-title="首页"
	                           href="javascript:void(0)">首页</a><i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
	        </dl> --%>
	        
	        
	        
	    <c:if test="${id =='1'}">
		
		<dl id="menu-product">
	            <dt><i class="Hui-iconfont">&#xe727;</i><a data-href="<%=path%>/ar?p0=A-boss-search&p1=main_user&p2=" data-title="首页"
	                           href="javascript:void(0)">首页</a></dt>
	        </dl>
		<dl id="menu-product">
	            <dt><i class="Hui-iconfont">&#xe727;</i><a data-href="<%=path%>/ar?p0=A-boss-search&p1=log_search&p2=1&p3=tojsp&p4=&p5=&p6=&p7=" data-title="日志查询"
	                           href="javascript:void(0)">日志查询</a></dt>
	        </dl>
		
		</c:if>
	        
		
		
		
		<c:forEach var="menu" items="${menulist}" varStatus="index">
		<dl id="menu-product">
            <dt><i class="Hui-iconfont">&#xe686;</i>${menu.menu_name}<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            
            <dd>
                <ul>
                
                <c:forEach var="menu2" items="${menu.itemlist}" varStatus="idx2" >
                	<li><a data-href="<%=path%>${menu2.menu_link}" data-title="${menu2.menu_name}" href="javascript:void(0)">${menu2.menu_name }</a></li>
                </c:forEach>
                 </ul>
            </dd>
        </dl>
		</c:forEach>
		
		<c:if test="${id =='1'}">
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe72d;</i> 管理员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
				<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=admin_list&p2=1&p3=&p4=tojsp" data-title="管理员管理" href="javascript:void(0)">管理员管理</a></li>
				</ul>
			</dd>
		</dl>
		</c:if>
		
		
		
		<%-- <% if(b.contains("报表管理")){%>
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe72d;</i> 报表管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
				   <li><a data-href="<%=path%>/agent?p0=A-bigboss-search&p1=app_analyse&p2=1&p3=&p4=&p5=tojsp&p6=&p7=0" data-title="日报表" href="javascript:void(0)">日报表</a></li>
				</ul>
			</dd>
		</dl> 
		<% } %> --%>
</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active">
					<span title="欢迎页面" data-href="http://47.99.110.97:8090/uiface/welcome.html">欢迎页面</span>
					<em></em></li>
		</ul>
	</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="http://47.99.110.97:8090/uiface/welcome.html"></iframe>
	</div>
</div>
</section>

<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
</ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path%>/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=path%>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path%>/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path%>/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript">
$(function(){
	
});
/*个人信息*/
function myselfinfo(){
	layer.open({
		type: 1,
		area: ['300px','200px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: '查看信息',
		content: '<div>管理员信息</div>'
	});
}

/*资讯-添加*/
function article_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-添加*/
function picture_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*产品-添加*/
function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}


</script> 

<!--此乃百度统计代码，请自行删除-->
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
<!--/此乃百度统计代码，请自行删除-->
</body>
</html>