<body>
<jsp:useBean id="login" class="com.echosun.User" scope="request"></jsp:useBean>
<jsp:setProperty name="login" property="*"/>
<p><%=login.getUsername()%></p>
<p><%=login.getPassword() %></p>
</body>