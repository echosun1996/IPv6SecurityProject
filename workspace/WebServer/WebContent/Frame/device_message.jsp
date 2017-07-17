<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.echosun.database.*"%>
<jsp:useBean id="infos" class="com.echosun.database.Message"
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

<script type="text/javascript"
	src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>


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
					<li><a href="device_config.jsp" id="device_config"><span
							class="glyphicon glyphicon-cog" aria-hidden="true"></span>&nbsp;
							设备设置</a></li>
					<li class="active"><a href="device_message.jsp"
						id="device_message"><span class="glyphicon glyphicon-envelope"
							aria-hidden="true"></span>&nbsp; 设备消息 </a></li>
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
				<h2 class="text-center">设备消息</h2>

				<div class="table-responsive">
					<table class="table table-bordered" id="table">


						<thead>
							<tr>
								<th>设备编号</th>
								<th>消息发送时间</th>
								<th>消息内容</th>
								<th>状态</th>
							</tr>
						</thead>
						<tbody>

							<%
								int pageNow = 1;
								int pageSum = 0;
								String s_pageNow = request.getParameter("page");
								if (s_pageNow != null) {
									//接收到了pageNow
									try {
										pageNow = Integer.parseInt(s_pageNow);
									} catch (Exception e) {
									}
								}

								try {
									List<Message_Model> res = infos.Message_AfterSelALL(pageNow);
									List<Message_Model> ress = infos.Message_SelALL();
									if(res==null||ress==null)return;
									pageSum = (ress.size() % 8 == 0) ? (ress.size() / 8) : (ress.size() / 8 + 1);
									for (Message_Model i : res) {
							%>
							<tr class="cus-message-state<%=i.getStatus()%>">
								<td><%=i.getUID()%></td>
								<td><%=i.getTime()%></td>
								<td><%=i.getMessage()%></td>
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

					<%
						out.print("您当前在" + pageNow + "页	");
						out.print("	共" + pageSum + "页");
					%>
					<div class="pull-right">
						<a href="device_message.jsp?page=<%=pageNow + 1%>">下一页</a> <a
							href="device_message.jsp?page=<%=pageNow - 1%>">上一页</a> <a
							href="device_message.jsp?page=<%=pageSum%>">尾页</a> <a
							href="device_message.jsp?page=1">首页</a>
					</div>



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







	<!--


<script type="text/javascript">
    
    $(document).ready(function () {

        var html = '',
            $ele = $('#testTbody');
        for(var i = 0; i < 20; i++) {
            html += "<tr><td>123456</td><td>12345</td><td>1234</td><td>123</td><td>12</td></tr>";
        }
        $ele.empty().append(html);

        $('#goodsList').scroll(function() {
            var id = '#' + this.id;
            var scrollTop = $('id').scrollTop() || $(id).get(0).scrollTop,
                style = {
                    'position': 'absolute',
                    'left': '0',
                    'right': '0',
                    'top': scrollTop + 'px'
                };
            var th_width = [];
            $(id + ' .scrollTable th').each(function() {
                th_width.push(this.offsetWidth);
            });
            if ($(id + ' .fixTable') && $(id + ' .fixTable').length) {
                (scrollTop === 0) ? $(id + ' .fixTable').addClass('hidden') : $(id + ' .fixTable').removeClass('hidden');
                $(id + ' .fixTable').find('th').each(function(i) {
                    // $(this).css('width', th_width[i] + 'px');  //注释掉这行在火狐下就不会抖动了
                });
                $(id + ' .fixTable').css(style);
            } else {
                var html = $(id + ' .scrollTable thead').get(0).innerHTML;
                var table = $('<table class="table table-bordered fixTable"><thead>' + html + '</thead></table>');
                table.find('th').each(function(i) {
                    // $(this).css('width', th_width[i] + 'px');  //注释掉这行在火狐下就不会抖动了
                });
                table.css(style);
                $(id).append(table);
            }
        }); 
    })
</script>

-->


	<script src="../js/jquery-1.11.1.min.js"></script>

	<script src="../js/bootstrap.min.js"></script>
</body>
</html>