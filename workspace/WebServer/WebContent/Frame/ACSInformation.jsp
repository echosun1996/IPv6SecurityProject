<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.echosun.database.*"%>
<jsp:useBean id="infos" class="com.echosun.database.ACSInformation"
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
<link href="../css/bootstrap-switch.css" rel="stylesheet">
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
					<li><a href="main.jsp" target="main"><span
							class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;
							面板首页</a></li>
					<li><a href="device_config.jsp" target="main"
						id="device_config"><span class="glyphicon glyphicon-cog"
							aria-hidden="true"></span>&nbsp; 设备设置</a></li>
					<li><a href="device_message.jsp" target="main"
						id="device_message"><span class="glyphicon glyphicon-envelope"
							aria-hidden="true"></span>&nbsp; 设备消息 </a></li>
					<li class="active"><a href="ACSInformation.jsp" target="main"
						id="ACSInformation"><span class="glyphicon glyphicon-wrench"
							aria-hidden="true"></span>&nbsp; 门禁设置</a></li>

					<li><a href="user_config.jsp" target="main" id="user_config"><span
							class="glyphicon glyphicon-user"></span>&nbsp; 用户设置</a></li>
					<li><a href="/WebServer/loginout.jsp" id="loginout"><span
							class="glyphicon glyphicon-log-out" aria-hidden="true"></span>&nbsp;
							用户登出</a></li>
				</ul>
			</div>

			<div class="col-xs-12 col-sm-9 col-md-10">
				<h2 class="text-center">门禁消息</h2>
				<div style="overflow-x: auto; overflow-y: auto; height: 380px;"
					class="table-responsive">
					<table class="table  table-striped table-bordered " id="table">

						<thead>
							<tr>
								<th>门禁卡编号</th>
								<th>名称</th>
								<th>状态</th>
								<th>录入时间</th>
							</tr>
						</thead>
						<tbody>
							<%
								try {
									List<ACSInformation_Model> res = infos.ACSInformation_SelALL();
									for (ACSInformation_Model i : res) {
							%>
							<tr>
								<td><%=i.getID()%></td>
								<td>


									<form action="ACSInformation_change.jsp" method="post"
										class="form-inline">
										<input type="hidden" class="form-control" name="cname_id"
											value="<%=i.getID()%>">

										<div class="form-group">
											<input type="text" name="name" value="<%=i.getName()%>"
												placeholder="名称">

										</div>

										<button type="submit" class="btn btn-default" name="name">更新</button>
									</form>


								</td>
								<td><input onclick="switchs(this)" id="<%=i.getID()%>"
									type="checkbox" checked></td>
								<td><%=i.getTime()%></td>
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












	<script src="../js/jquery.min.js"></script>
	<script src="../js/highlight.js"></script>
	<script src="../js/bootstrap-switch.js"></script>
	<script src="../js/main.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script type="text/javascript">
	function switchs(obj) {
		alert(obj)
	} 
	

	</script>


</body>
</html>