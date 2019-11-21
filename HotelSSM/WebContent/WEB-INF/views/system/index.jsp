<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />



<title>后台管理主页</title>
<link rel="stylesheet" type="text/css" href="../resources/admin/layui-v2.5.5/layui/css/layui.css" />

<script type="text/javascript" src="../resources/admin/layui-v2.5.5/layui/layui.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">后台管理主页</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
<!--     <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="">控制台</a></li>
      <li class="layui-nav-item"><a href="">商品管理</a></li>
      <li class="layui-nav-item"><a href="">用户</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
        </dl>
      </li>
    </ul> -->
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
     		 	<p><strong>${admin.username}</strong>，欢迎您！</p>
      </li>
      <li class="layui-nav-item"><a href="logout">退出系统</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="../admin/room/list">管理</a>
           <dl class="layui-nav-child">
            <dd><a href="../admin/room/list" target="mainIframe">客房管理</a></dd>
            <dd><a href="../admin/room_type/list" target="mainIframe">客房类型管理</a></dd>
            <dd><a href="../admin/checkin/list" target="mainIframe">入住管理</a></dd>
            <dd><a href="../admin/book_order/list" target="mainIframe">预定管理</a></dd>
             <dd><a href="../admin/account/list" target="mainIframe">用户管理</a></dd>
              <dd><a href="../admin/settle/list" target="mainIframe">结账管理</a></dd>
             <!--   <dd><a href="../admin/stats/stats" target="mainIframe">统计图表</a></dd> -->
            <dd><a href="../system/edit_password" target="mainIframe">修改密码</a></dd>
          </dl> 
        </li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
<!--     内容主体区域
    <div style="padding: 15px;"></div> -->
    <iframe name="mainIframe" frameborder="0" scrolling="no" width="100%" height="100%"></iframe>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
  </div>
</div>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
});
</script>
</body>
</html>