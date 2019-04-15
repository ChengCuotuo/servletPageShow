<%--
  Created by IntelliJ IDEA.
  User: lei02
  Date: 2019/4/15
  Time: 7:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>submitInfo</title>
  <style type="text/css">
    body{
      color:#AAFFEE;
      font-size:14px;
    }
  </style>
  </head>
  <body>
    <form action="queryAllServlet" method="post">
      <b>数据库:<input type="text" name="dataBase" value="chapter7"/>
        <br />表名:<input type="text" name="tableName" value="produce"/>
        <br />用户名（默认）:<input type="text" name="user" value="root" />
        <br />密码：<input type="password" name="password" value="021191" />
        <br /><input type="submit" value="提交"/>
        <input type="hidden" name="pageSize" value="1"/>
      </b>
    </form>
  </body>
</html>
