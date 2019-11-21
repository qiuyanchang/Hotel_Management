<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ page contentType="text/html;charset=UTF-8" language="java" %> --%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>酒店</title>
    <style>
        /*菜单*/
        .menu {
            border: 1px solid black;
            margin-top: 20px;
            background-color: lightseagreen;
        }
        .table-menu{
            height: 20px;
            border-spacing: 30px;
            border-collapse: separate;
            box-sizing: border-box;
        }

        #m-one {
            border: 3px solid gainsboro;
            width: 100px;
            height: 50px;
        }

        #m-two {
            border: 3px solid gainsboro;
            width: 100px;
            height: 50px;
        }

        #m-four {
            border: 3px solid gainsboro;
            width: 100px;
            height: 50px;
        }

        #m-five {
            border: 3px solid gainsboro;
            width: 100px;
            height: 50px;
        }

        #m-six {
            border: 3px solid gainsboro;
            width: 100px;
            height: 50px;
        }

        #m-eight {
            border: 3px solid gainsboro;
            width: 100px;
            height: 50px;
        }

        #m-nine {
            border: 3px solid gainsboro;
            width: 100px;
            height: 50px;
        }
        /*搜索框*/
        .search{
            border-style: solid;
            border-color: gainsboro;
            background-size: 300px 500px;
            width: 300px;
            height: 500px;
            margin-top: 50px;
            background-color: lightseagreen;
            float: left;
        }
        .t-ser{
            margin-top: 30px;
            background-color: white;
            width: 280px;
        }
        .td-left {
            text-align: right;
            height: 50px;
            color: black;
        }


        #checkin {
            width: 120px;
            height: 25px;
            border: 1px #888888 solid;
            padding-left: 15px;
            border-radius: 10px;
        }

        #checkout {
            width: 120px;
            height: 25px;
            border: 1px #888888 solid;
            padding-left: 15px;
            border-radius: 10px;
        }


        /*精选主题*/
        .d-jingxuan{;
            float:left;
        }
        .t-jingxuan{
            border-spacing: 30px;
            border-collapse: separate;
            margin-left: 15px;
        }
        #jx-01{
            background: url("/images/double.jpg");
            background-size: 250px 400px;
            width: 250px;
            height: 400px;
            border-radius: 20px;
            border: 3px solid gainsboro;
            color: white;
            box-sizing: border-box;
        }
        #jx-02{
            background: url("/images/jiudian5.jpg");
            background-size: 250px 400px;
            width: 250px;
            height: 400px;
            border-radius: 20px;
            border: 3px solid gainsboro;
        }
        #jx-03{
            background: url("/images/jiudian4.jpg");
            background-size: 250px 400px;
            width: 250px;
            height: 400px;
            border-radius: 20px;
            border: 3px solid gainsboro;
        }
        #jx-04{
            background: url("/images/jiudian10.jpg");
            background-size: 250px 400px;
            width: 250px;
            height: 400px;
            border-radius: 20px;
            border: 3px solid gainsboro;
        }

    </style>
    <link rel="stylesheet" href="../resources/admin/static/plugins/layui/css/layui.css" />
    <link rel="stylesheet" type="text/css" href="../resources/admin/layui-v2.5.5/layui/css/layui.css" />

<script type="text/javascript" src="../resources/admin/layui-v2.5.5/layui/layui.js"></script>
<% request.setCharacterEncoding( "UTF-8" ); %>

</head>

<body style="background:url(../resources/admin/login/images/bg2.jpg) top left;
background-size:80%;">
<!--菜单-->
<div class="menu" style="margin: 0 10% ;background:#4682b4;">
 <!--    <form action="#" method="post" > -->
        <table class="table-menu"  >
            <tr>
            <td>
        <div class="layui-input-inline">
        <input type="text" placeholder="关键字" value="${kw }" id="kw" class="layui-input"/></div>
        <div class="layui-input-inline">
        <input type="button" value="搜索" id="search-btn" class="layui-btn" style="margin: 0 10% ;background:#93bbdc;" />
      	</div>
      			<form style="display:none;" action="index" method="get" id="search-form"><input type="hidden" name="name" id="search-name"></form>
            </td>
            
              	<c:if test="${account == null }">
	
				 <td id="m-eight" align="center">
                   <a href="../home/login">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
                <td id="m-nine" align="center">
                   <a href="../home/reg">注册</a>&nbsp;
                </td>
				</c:if>
              
          	<c:if test="${account != null }">
          	<td id="m-six" align="center">
		    		<font color="red">欢迎您：${account.name }&nbsp;&nbsp;&nbsp;&nbsp;</font>
		   </td>
		   
		   
		  
		
		   <td id="m-six" align="center">
		    		<a href="account/index">个人中心</a>&nbsp;&nbsp;&nbsp;&nbsp; 
		   </td>
		   
		  <td id="m-six" align="center">
		  	<a href="../home/logout">退出登录</a>&nbsp;
		   </td>

	</c:if>
            </tr>
        </table>
  <!--   </form> -->
</div>



<!--精选主题 -->
	
<div class="layui-form"  style="margin: 0 10%">
  <table class="layui-table" style="table-layout:fixed;">
    <colgroup>
    <%--   <col width="150">
      <col width="150">
      <col width="200">
      <col> --%>
    </colgroup>
    <thead>
      <tr>
        <th width='45%' >缩略图</th>
        <th width='40%'>详细信息</th>
        <th width='5%'>价格</th>
        <th width='5%'>状态</th>
         <th width='5%'>预定</th>
      </tr> 
    </thead>
    <tbody>
       <c:forEach items="${roomTypeList }" var="roomType">
        <tr >
          <td><a href="#"><img src="${roomType.photo }" alt=""></a>
          </td>
          <td >
            <p>${roomType.name }</p>
            <p class="sub_txt">${roomType.remark }</p>
          </td>
          <td >${roomType.price }</td>
          <td >
          	<c:if test="${roomType.status == 0 }">
          		已满房
          	</c:if>
          	<c:if test="${roomType.status == 1 }">
          		可预订
          	</c:if>
          </td>
          <td >
          	<c:if test="${roomType.status == 0 }">
          		<input type="button" class="disable" value="满房"  class="layui-btn layui-btn-disabled">
          	</c:if>
          	<c:if test="${roomType.status == 1 }">
          		<input type="button" value="预订" onclick="window.location.href='account/book_order?roomTypeId=${roomType.id }'"  class="layui-btn layui-btn-normal">
          	</c:if>
          </td>
        </tr>
		</c:forEach>
    </tbody>
  </table>
</div>
<script src="../resources/home/js/jquery-1.11.3.js"></script>
<script>

$(document).ready(function(){
	$("#search-btn").click(function(){
		var a=$("#kw").val();
		$("#search-name").val(a);
		$("#search-form").submit();
	})
});

</script>

</body>


</html>
