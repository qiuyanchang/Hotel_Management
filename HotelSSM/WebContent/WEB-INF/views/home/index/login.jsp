<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <title>Mju酒店管理系统登录页面</title>
  <link href="../resources/home/css/index.css" type="text/css" rel="Stylesheet" />
  <link href="../resources/home/css/login.css" type="text/css" rel="Stylesheet" />
  
  
  <style type="text/css">
	body{background:url(../resources/admin/login/images/bg1111.jpg) top left;
background-size:100%;}
	 div {
          
            top: 80%;
            left: 30%;
         
           
        }
</style>
  
  
 </head>
 <body style="background:url(../resources/admin/login/images/bg41.jpg) top left;
background-size:80%;">

       <header>
	          <div>
							<a href="index.html">首页!</a> <span>用户登录!</span>
			  </div>

	   </header>
       <section>
			<div class="login">
				 <div id="normal">
						 <ul id="nor_log">
							<li class="name" style="margin-top:25px;">
							   <input id="name" name="uname"type="text" placeholder="请输入用户名">
						<!-- 	   <span class="icon"></span> -->
							</li>
							<li class="pwd" style="margin-top:25px;">
							   <input id="password" name="upwd" type="password" placeholder="密码">
							  <!--  <span class="icon"></span> -->
							</li>
						 </ul>
		
					<!--    <div class="codes" style="margin-top:25px;">
							 <input id="vcode" maxlength="4" type="text" class="blur" placeholder="请输入验证码"/>
							 <img id="cpacha-img" src="../system/get_cpacha?vl=4&w=173&h=33&type=accountLoginCpacha" onclick="changeVcode()" class="code" style="cursor:pointer;"/>
						 </div> -->
						 
				 </div>
				
				 <div class="log" id="bt_login" style="margin-top:25px;cursor:pointer;background:#000; color:#FFF;">登 录</div>
				 <div class="log"  style="background:#000; color:#FFF; margin-top:25px;cursor:pointer;" onclick="window.open('../system/login','_self')">管理员</div>
			</div>
		
	   </section>
       <%@include file="../common/footer.jsp"%>
	  <script src="../resources/home/js/jquery-1.11.3.js"></script>
<script>
function changeVcode(){
	$("#cpacha-img").attr("src",'../system/get_cpacha?vl=4&w=173&h=33&type=accountLoginCpacha&t=' + new Date().getTime());
}
$(document).ready(function(){
	$("#bt_login").click(function(){
		var name = $("#name").val();
		var password = $("#password").val();
	/* 	var vcode = $("#vcode").val(); */
		if(name == '' || password == ''){
			alert('请填入用户信息！！');
			return;
		}
		$.ajax({
			url:'login',
			type:'post',
			dataType:'json',
			data:{name:name,password:password},
			success:function(data){
				if(data.type == 'success'){
					window.location.href = 'index';
				}else{
					alert(data.msg)
					changeVcode();
				}
			}
		});
	})
});
</script>
 </body>
</html>
