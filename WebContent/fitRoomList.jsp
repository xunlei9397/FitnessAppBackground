<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%	String path = request.getContextPath();	/* String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()			+ path + "/";	 */	String basePath = request.getScheme() + "://" + "localhost" + ":" + request.getServerPort() + path + "/";%><html ><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><script type="text/javascript" src="js/area.js" type="text/javascript"></script><script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<head>

<title>全国健身房后台管理页面</title>
<script  type="text/css">
body{ background:#EEEEEE;margin:0; padding:0; font-family:"微软雅黑", Arial, Helvetica, sans-serif; }
a{ color:#006600; text-decoration:none;}
a:hover{color:#990000;}
.top{ margin:5px auto; color:#990000; text-align:center;}
.info select{ border:1px #993300 solid; background:#FFFFFF;}
.info{ margin:5px; text-align:center;}
.info #show{ color:#3399FF; }
.bottom{ text-align:right; font-size:12px; color:#CCCCCC; width:1000px;}
</script>
</head>

	<h1 class="top">全国健身房后台管理页面</h1>


<script type="text/javascript">
//获取省市县的地址function getAddress(){	alert(Gid('s_province').value);	$("#province").val(Gid('s_province').value);		$("#city").val(Gid('s_city').value);	$("#county").val(Gid('s_county').value);}function getList() {	var province=$("#s_province").val();	var city=$("#s_city").val();	var county=$("#s_county").val();	alert("province"+province);    $.ajax({              cache: true,              type: "POST",              url:"<%=basePath%>publishInterface/searchFitRoomListInt.eo",            data:{'province':province,'city':city,'county':county},            async: false,              error: function(request) {                  alert("Connection error:"+request.error);              },              success : function(res) {				$.each(res, function(i, n) {					$("#tbody").append(							"<tr><td>" + (i + 1) + ":" + n.fitRoomName									+ "</td><td>"+ n.addressRemarks+"</td></tr>")				});				//window.location.href='<%=basePath%>fitRoomList.jsp';			}        });  } function addList() {	var province=$("#s_province").val();	var city=$("#s_city").val();	var county=$("#s_county").val();	var fitRoomName=$("#fitRoomName").val();	$.ajax({              cache: true,              type: "POST",              url:"<%=basePath%>publishInterface/addFitRoomListInt.eo",              data:{'province':province,'city':city,'county':county,'fitRoomName':fitRoomName},// 你的formid              async: false,            error: function(request) {            	alert("form2的信息为："+$('#form2').serialize())                alert("Connection error:"+request.error);              },              success: function(data) {             	//window.location.href='<%=basePath%>fitRoomList.jsp';                alert("SUCCESS!"+data);              }          });  } 
</script>	<form id="form1" method="post">	<h4>各地健身房搜索</h4>	<div class="info">	<div>	<select id="s_province" name="province"></select>      <select id="s_city" name="city" ></select>      <select id="s_county" name="county"></select>    <script type="text/javascript">_init_area();</script>    </div>    <div id="show"></div></div>		<h6>请依次选择省、市、县,点击search返回搜索结果</h6>		<INPUT TYPE=button value="search" onclick="getList()">	</form><table border="1">		<thead>			<tr>				<th>当前可选择健身房</th>				<th>健身房地址</th>			</tr>		</thead>		<tbody id="tbody"></tbody></table><form id="form2" method="post">	<h4>各地健身房添加</h4>	<div >	<div>	健身房名称：<INPUT TYPE=TEXT id="fitRoomName" NAME="fitRoomName">		健身房地址备注：<INPUT TYPE=TEXT id="addressRemarks" NAME="addressRemarks">    <script type="text/javascript">_init_area();</script>    </div>    <div id="show"></div></div>		<h6>请依次选择省、市、县,点击search返回搜索结果</h6>		<INPUT TYPE=button value="add" onclick="addList()">	</form>
</html>

