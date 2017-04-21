<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.echosun.database.*"%>
<jsp:useBean id="infos" class="com.echosun.database.Hardware"
	scope="page" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>安全控制台</title>
<link rel="icon" href="/WebServer/Files/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="/WebServer/Files/favicon.ico"
	type="image/x-icon" />
<link rel="bookmark" href="/WebServer/Files/favicon.ico"
	type="image/x-icon" />


<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">


<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->



</head>
<body>
	<div class="container-fluid">
		<h1 class="text-center">安全控制台</h1>
	</div>
	<hr
		style="height: 3px; border: none; border-top: 3px groove deepskyblue;" />

	<div class="container-fluid">
		<div class="row">
			<div class="span2  col-xs-12 col-sm-3 col-md-2">
				<ul class="nav nav-pills nav-stacked demo-nav side-bar">
					<li><a href="main.jsp"><span
							class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;
							面板首页</a></li>
					<li class="active"><a href="device_config.jsp"
						id="device_config"><span class="glyphicon glyphicon-cog"
							aria-hidden="true"></span>&nbsp; 设备设置</a></li>
					<li><a href="device_message.jsp" id="device_message"><span
							class="glyphicon glyphicon-envelope" aria-hidden="true"></span>&nbsp;
							设备消息 </a></li>
					<li><a href="ACSInformation.jsp" id="ACSInformation"><span
							class="glyphicon glyphicon-wrench" aria-hidden="true"></span>&nbsp;
							门禁设置</a></li>

					<li><a href="user_config.jsp" id="user_config"><span
							class="glyphicon glyphicon-user"></span>&nbsp; 用户设置</a></li>
					<li><a href="/WebServer/loginout.jsp" id="loginout"><span
							class="glyphicon glyphicon-log-out" aria-hidden="true"></span>&nbsp;
							退出登录</a></li>
				</ul>
			</div>

			<div class="col-xs-12 col-sm-9 col-md-10">
				<h2 class="text-center">设备设置</h2>
				<div style="overflow-x: auto; overflow-y: auto; height: 380px;"
					class="table-responsive">
					<table class="table table-bordered" id="table" border="1">

						<thead>
							<tr>
								<th>设备编号</th>
								<th>设备备注</th>
								<th>设备类别</th>
								<th>设备状态</th>
							</tr>
						</thead>
						<tbody>
							<%
								try {
									List<Hardware_Model> res = infos.Hardware_Sel();
									for (Hardware_Model i : res) {
							%>
							<tr class="cus-congif-state<%=i.getStatus()%>">
								<td><%=i.getUID()%></td>
								<td>

									<form action="device_change.jsp" method="post"
										class="form-inline">
										<input type="hidden" name="uid" value="<%=i.getUID()%>">


										<input type="text" class="form-control" name="name"
											value="<%=i.getName()%>" placeholder="设备备注">

										<button type="submit" class="btn btn-default">更新</button>
									</form>
								</td>
								<td><%=i.getCategory()%></td>
								<td><%=i.getStatus()%></td>
							</tr>

							<%
								}
								} catch (Exception e) {
									out.print("<script>alert('数据库连接异常！请联系管理员');window.location.href='main.jsp';</script>");
								}
							%>



						</tbody>
					</table>
				</div>
			</div>

		</div>
		<hr class="divider">
		<footer>
			<p class="pull-right">
				<a href="#top">回到顶部</a>
			</p>
			<p>Copyright 2017 IPv6SecurityProject. All Rights Reserved.</p>
		</footer>


	</div>









	<script src="../js/jquery-1.11.1.min.js"></script>

	<script src="../js/bootstrap.min.js"></script>
</body>
</html>