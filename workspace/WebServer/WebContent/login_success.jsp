<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/x-icon"  href="/WebServer/Files/favicon.ico" >
<title>控制台主面板</title>
<script type="text/javascript">
	function change() {
		{
			alert(222);
			var header = document.getElementsByName('header');
			alert(123);
			header.src = "123.jsp";
		}
		//document.frames("header").location.reload();
	}
</script>
</head>
<frameset rows="10%,90%"  border="1px">
	<frame src="Frame/header.jsp" noresize="noresize" name="header" frameborder="0">
	<frameset cols="10%,*">
		<frame src="Frame/left.jsp" noresize="noresize" name="left" frameborder="0">
		<frame src="Frame/main.jsp" noresize="noresize" name="main" frameborder="0">
	</frameset>
</frameset>
</html>