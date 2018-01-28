<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();

	/* String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	 */
	String basePath = request.getScheme() + "://" + "localhost" + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>课程增、删、查</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
<link rel="stylesheet" type="text/css" href="styles.css">
-->
<script type="text/javascript"
	src="js/jquery-2.1.4.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	getObject();
			}); 
function getObject(){
	$("#tbody").empty()
	$.ajax({
		url:"<%=basePath%>publishInterface/fitObjectInt.eo",
							method : "post",
							dataType : "json",
							success : function(res) {
								$.each(res, function(i, n) {
									$("#tbody").append(
											"<tr><td>" + (i + 1) + ":" + n+ "</td>"+
											"<td><button  onclick=objectDelete('"+n+"') >删除</button></td></tr>")
								});
							}
						})
}
	 function objectAdd() { 
		 if($("#object").val().length>0){
	        $.ajax({  
	                cache: true,  
	                type: "POST",  
	                url:"<%=basePath%>publishInterface/addFitObjectInt.eo",  
	                data:$('#form1').serialize(),// 你的formid  
	                async: false,  
	                error: function(request) {  
	                    alert("Connection error:"+request.error);  
	                },  
	                success: function(data) {  
	                	window.location.href='<%=basePath%>hello.jsp';
	                    //alert("SUCCESS!"+data);  
	                }  
	            });
		 }else{
			 alert("请输入课程")
		 }
	    } 
	 function objectDelete(object) {  
	        $.ajax({  
	                cache: true,  
	                type: "POST",  
	                url:"<%=basePath%>publishInterface/deleteFitObjectInt.eo",  
	                data:{'object':object},// 你的formid  
	                async: false,  
	                error: function(request) {  
	                    alert("Connection error:"+request.error);  
	                },  
	                success: function(data) {  
	                	
	                	getObject();

			}
		});
	}
</script>
</head>


<body>
	<table border="1">
		<thead>
			<tr>
				<th>当前可选择课程</th>
				<th>删除按钮</th>
			</tr>
		</thead>
		<tbody id="tbody"></tbody>
	</table>
	<form id="form1" method="post" onsubmit="return objectAdd();">
		<h1>增加课程</h1>
		<INPUT id="object" TYPE=TEXT NAME="json">
		<h6>增加课程说明：请在输入框中输入要增加的课程，输入多个课程请用 英文逗号 隔开，然后点击submit</h6>
		<h6>若您添加的课程没有被添加，则说明您输入的课程与已有课程重复</h6>
		<INPUT TYPE=button value="增加课程" onclick="objectAdd();">
	</form>


</body>
</html>
