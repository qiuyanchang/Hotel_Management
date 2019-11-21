<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>用户中心</title>

<link rel="stylesheet" type="text/css" href="../../../resources/admin/layui-v2.5.5/layui/css/layui.css" />

<script type="text/javascript" src="../../../resources/admin/layui-v2.5.5/layui/layui.js"></script>
  <!--<link rel="stylesheet" href="css/myOrder.css"/>-->
  <style>
    #contain{
      padding: 0px 0;
    }
    .tabs{
      width:162px;
      float: left;
      padding: 10px 0;
 /*      text-align: center; */
      background: #EDEDED;
    }
/*     .tabs li{
      line-height: 36px;
      color:"black";
    } */
    .content{
      padding: 0 10px;
      float:left;
      width: 1000px;
    }
    .content>div{
      display: none;
      width: 1000px;
    }
    .content .details{
      float:left;
      display: block;
      width: 1000px;
      /*padding:0  10px;*/
    }
    table{
      border: 1px solid #dddddd;
      border-collapse: collapse;
      width: 100%;
      margin-bottom: 20px;
    }
    table th{
      background: #F2F2F2;
    }
    #contain .content table td{
      /*width:163px;*/
      border: 1px solid #dddddd;
      text-align: center;
    }
    .details table td{
      width: 163px;
    }
    table td input[type='checkbox']{
      float:left;
    }
    table td div{
      display: inline-block;
      border:1px solid #dddddd;
      padding: 5px;
    }
    table img{
      width: 100px;
      height: 100px;
      vertical-align: middle;
    }
    h3{
      margin:10px 0;
    }
    .total{
      text-align: right;
      padding: 10px 0;
    }
    .totalPrice{
      color: #DD4C40;
      font-size: 18px;
      font-weight: bold;
      margin-right: 10px;
    }
    .price{
      color:#DD4C40;
      font-weight: bold;
    }
    .count{
      width: 30px;
      text-align: center;
    }
    .title{
      font-weight: bold;
    }
    .msg{
      color:#DD4C40;
      min-height: 200px;
      line-height: 200px;
      font-weight: bold;
      text-align: center;
      display: none;
    }
    .tabs>li>a.active{
      color: #dd0000;
    }
    .order>table th{
      text-align: left;
      padding:5px ;
    }

    #contain .content .order .product{
      max-width:110px;
    }
    #contain .content table th{
      text-align: center;
    }
    #contain .content tr.title td{
      height: 25px;
      text-align: left;
    }
    #contain .content table td{
      max-width:220px ;
    }
    #contain .content td.productImg{
      text-align: left;
    }
	.form-control {
		display: block;
		width: 700px;
		height: 20px;
		padding: 6px 12px;
		font-size: 14px;
		line-height: 1.42857143;
		color: #555;
		background-color: #fff;
		background-image: none;
		border: 1px solid #ccc;
		border-radius: 4px;
		-webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
		box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
		-webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
		-o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
		transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}
.btn-success {
    color: #fff;
    background-color: #5cb85c;
    border-color: #4cae4c;
}

.btn {
    display: inline-block;
    padding: 6px 12px;
    margin-bottom: 0;
    font-size: 14px;
    font-weight: 400;
    line-height: 1.42857143;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    -ms-touch-action: manipulation;
    touch-action: manipulation;
    cursor: pointer;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    background-image: none;
    border: 1px solid transparent;
    border-radius: 4px;
}

  </style>
</head>
<body>
    <!--头部-->
  <!--   <div id="c_header"></div> -->
    <!--主体-->
    <div id="contain">
        <!--tab选项卡-->
        <ul class="tabs">
       
    

      </ul> 
      <div class="content">
        
 </div>
		<div class="pwd" >
          <table style="border:0px;cellspacing:0px;">
            <tbody>
               <tr>
					<td style="border:0px;">原密码：</td><td style="float:left;width:400px;max-width: 420px;border:0px;"><input class="form-control" type="password" id="old-password" /></td>
               </tr>
			   <tr style="border:0px;">
					<td style="border:0px;">新密码：</td><td style="float:left;width:400px;max-width: 820px;border:0px;"><input class="form-control" type="password" id="new-password" /></td>
               </tr>
			   <tr>
					<td style="border:0px;">重复密码：</td><td style="float:left;width:400px;max-width: 820px;border:0px;"><input class="form-control" type="password" id="renew-password" /></td>
               </tr>
			   
			   <tr>
					<td style="border:0px;"></td><td style="float:left;margin-top:15px;width:400px;max-width: 820px;border:0px;"><button type="button" class="btn btn-success" id="update-password-btn" style="width:100px;">提交</button></td>
               </tr>
            </tbody>
          </table>
          
        </div>
      </div>

    <!--底部-->
    <div id="c_footer"></div>
   <script src="../../../resources/home/js/jquery-1.11.3.js"></script>
 <script>
 $("#update-password-btn").click(function(){
 	var oldPassword = $("#old-password").val();
 	var newPassword = $("#new-password").val();
 	var renewPassword = $("#renew-password").val();
 	if(oldPassword == ''){
 		alert('请填写原密码！');
 		return;
 	}
 	if(newPassword == ''){
 		alert('请填写新密码！');
 		return;
 	}
 	if(newPassword != renewPassword){
 		alert('两次密码不一致！');
 		return;
 	}
 	
 	var truthBeTold = window.confirm("是否确认修改");
 	if (truthBeTold==false) {
 		alert("那好吧，再见");
 		return;
 	} 
 	
 	$.ajax({
 		url:'update_pwd',
 		type:'post',
 		dataType:'json',
 		data:{oldPassword:oldPassword,newPassword:newPassword},
 		success:function(data){
 			alert(data.msg)
 		}
 	});
 });
	
</script>
    
</body>
</html>