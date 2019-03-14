<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<%--声明我要使用C标签--%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%   
	/* String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
	request.setAttribute("path", basePath);  
	//String path = request.getContextPath() + "/uiface";
	String path2 = request.getContextPath() + "/uiface1";
	String loginid=(String)session.getAttribute("id"); */
	
	
	String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
	request.setAttribute("path", path);  
	//String path = request.getContextPath() + "/uiface";
	String loginid=(String)session.getAttribute("id");
	
	
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
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link href="<%=path%>/uiface/boss/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/uiface/boss/static/h-ui.admin/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/uiface/boss/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/uiface/boss/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=path%>/uiface/boss/lib/zTree/v3/css/zTreeStyle/zTree1Style.css" type="text/css">
<!--[if IE 6]>		http://120.27.98.128:9810/uiface/boss/lib/zTree/v3/css/zTreeStyle/zTree1Style.css
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script><![endif]-->
<title>添加产品分类</title>
</head>
<body>
<div class="pd-20">
  <form  class="form form-horizontal" id="form-user-add">  <%-- action="<%=path%>/uiface/ar?p0=A-boss-mod&p1=logins_mod&p2=${param.p2}" method="post" --%>
    <%-- <div class="row cl">
      <label class="form-label col-xs-2 col-sm-2">代理商名称：</label>
      <div class="formControls col-xs-4 col-sm-4">
        ${reList[0].name }
        <input type="hidden" value="${reList[0].id }" name="id">
      </div>
      <label class="form-label col-xs-2 col-sm-2">上级代理商：</label>
      <div class="formControls col-xs-4 col-sm-4">
      <c:if test="${reList[0].id == 6 }">
      	<input type="hidden" value="0" name="parent_id">
      	当前已是顶级
      </c:if>
  		<c:if test="${reList[0].id != 6&&id==6 }">
         <select name="parent_id">
			<c:forEach var="item" items="${reList1}"  varStatus="index">
				<option value="${item.id }" ${reList[0].parent_id eq item.id ?"selected=\"selected\"":"" }>${item.name}</option>
			</c:forEach>
         </select>
 		</c:if>
 		<c:if test="${reList[0].id != 6&&id!=6 }">
      	<input type="hidden" value="${reList[0].parent_id}" name="parent_id">
      	${reList[0].parent_name}
      </c:if>
      </div>
    </div> --%>
   <%--  <div class="row cl">
      <label class="form-label col-xs-2 col-sm-2">登录名：</label>
      <div class="formControls col-xs-4 col-sm-4">
        <input type="text" value="${reList[0].username }" name="username">
      </div>
     <label class="form-label col-xs-2 col-sm-2">密码：</label>
      <div class="formControls col-xs-4 col-sm-4">
         <input type="text" value="${reList[0].password }" name="password">
      </div>
    </div> --%>
    <%-- <div class="row cl">
      <label class="form-label col-xs-2 col-sm-2">总代佣金比率(%)：</label>
      <div class="formControls col-xs-4 col-sm-4">
      	<c:if test="${id==6}">
        <input type="text" value="${reList[0].commission_rate1*100 }" id="commission_rate1" name="commission_rate1">
      </c:if>
      <c:if test="${id!=6}">
      	<input type="text" value="${reList[0].commission_rate1*100 }" id="commission_rate1" name="commission_rate1" readonly="readonly">
      	</c:if>
      </div>
     <label class="form-label col-xs-2 col-sm-2">区域代理佣金(%)：</label>
      <div class="formControls col-xs-4 col-sm-4">
         <input type="text" value="${reList[0].commission_rate2*100 }" id="commission_rate2" name="commission_rate2">
      </div>
    </div>
    <div class="row cl">
    <label class="form-label col-xs-3 col-sm-3">佣金比率设置说明<font color="red">*</font></label>
      <div class="formControls col-xs-9 col-sm-9">1.订单的佣金结算金额并扣除技术服务费作为一个整体算作100%,按照订单的产生会员所属的代理商和一定的百分比分配给总代理、区域代理、和普通代理。
      <br/>2.区域代理的会员产生的订单只扣除本页面设置的总代理的抽成比率,剩余的佣金归入区域代理的账号
      <br/>3.普通代理的会员产生的订单需要扣本页面设置的除总代理的抽成比率和区域代理的抽成比率,剩余的佣金归入普通代理的账号
      <br/><font color="red">4.总代理佣金比率和区域代理佣金比率的和必须小于100%</font></div>
    </div> --%>
    
    
    
    
    <div class="row cl">
    
    	<div style="width:200px; ">
    	<span class="c-red">*</span>菜单权限：
    	</div>
    	    	<!-- <label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>菜单权限：</label> -->
      
      <div class="formControls col-xs-8 col-sm-8">
       <div><ul id="treeMenu" class="ztree1"></ul></div>
       <input type="hidden" id="menuids" name="menuid" value="${reList[0].menuids}">
        <input type="hidden" value="${reList[0].name }" name="agent_name" id="agent_name">
        <input type="hidden" value="${reList[0].parent_id }" name="pid" id="pid">
        
      </div>
    </div>
    <div class="row cl">
      <div class="col-9 col-offset-2">
        <!-- <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;"> -->
      <a onclick="submit(${param.p2});"  class="btn btn-primary radius">提交</a>
      </div>
    </div>
  </form>
</div>
<!--_footer 作为公共模版分离出去--> 
<script type="text/javascript" src="<%=path%>/uiface/boss/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=path%>/uiface/boss/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path%>/uiface/boss/static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="<%=path%>/uiface/boss/static/h-ui.admin/js/H-ui.admin.page.js"></script> 
<!--/_footer /作为公共模版分离出去--> 

<!--请在下方写此页面业务相关的脚本--> 
<script type="text/javascript" src="<%=path%>/uiface/boss/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="<%=path%>/uiface/boss/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="<%=path%>/uiface/boss/lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript" src="<%=path%>/uiface/boss/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/uiface/boss/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=path%>/uiface/boss/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="<%=path%>/uiface/boss/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
function submit(e){
	$.ajax({ 
		url :"<%=path%>/uiface/ar?p0=A-boss-mod&p1=logins_mod&p2="+e, 
		type : 'post',
		data:{menuids:$("#menuids").val(),agent_name:$("#agent_name").val(),pid:$("#pid").val()},
		success : function(data) { 
			//var index = parent.layer.getFrameIndex(window.name);
			//parent.$('.btn-refresh').click();
			parent.location.reload();
			//parent.layer.close(index);
		},
		error:function(data){
			parent.location.reload();
		}
	}) 
}
var zTree1;//树
var setting1 = {
	view: {
		dblClickExpand: true,
		showLine: true,
		selectedMulti: true
	},
	check: {
		enable: true,
		autoCheckTrigger: true
	},
	async: {
        enable: true,
        url:'<%=path%>/uiface/ar?p0=A-boss-search&p1=admin_quanxian1&p2=${param.p2}&p3=<%=loginid%>',
        autoParam:[],
        contentType: "application/json",
        otherParam:{},
        dataFilter: filter1
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
		onCheck: zTreeOnCheck
	}
};

function zTreeOnCheck(){
	var nodes = zTree1.getCheckedNodes(true);
	var checkids="";
	for(var i=0;i<nodes.length;i++){
		checkids+="|"+nodes[i].id;
	}
	$("#menuids").val(checkids);
}
function filter1(treeId, parentNode, childNodes) {
    if (!childNodes) return null;
    for (var i=0, l=childNodes.length; i<l; i++) {
        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
    }
    return childNodes;
}

$(document).ready(function(){
	zTree1= $.fn.zTree.init($("#treeMenu"), setting1);
});

</script>
</body>
</html>