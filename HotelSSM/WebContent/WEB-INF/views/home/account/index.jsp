<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />



<title>后台管理主页</title>
<link rel="stylesheet" type="text/css" href="../../resources/admin/layui-v2.5.5/layui/css/layui.css" />

<script type="text/javascript" src="../../resources/admin/layui-v2.5.5/layui/layui.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">个人中心</div>
    <ul class="layui-nav layui-layout-right">
    
      <li class="layui-nav-item"><a href="../account/logout">退出系统</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" >管理</a>
           <dl class="layui-nav-child">
            <dd><a href="../index">首页</a></dd>
            <dd><a href="../account/index/order" target="mainIframe">我的订单</a></dd>
            <dd><a href="../account/index/ziliao" target="mainIframe">我的资料</a></dd>
             <dd><a href="../account/index/changePwd" target="mainIframe">修改密码</a></dd>
          </dl> 
        </li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    
    
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