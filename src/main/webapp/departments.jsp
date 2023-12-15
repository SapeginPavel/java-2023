<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 14.12.2023
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Отделы</title>

</head>
<%@ page import="ru.vsu.cs.sapegin.service.DepartmentService" %>
<%@ page import="ru.vsu.cs.sapegin.dependencies.annotation.Inject" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.vsu.cs.sapegin.repository.item.DepartmentItem" %>
<%@ page import="java.io.PrintWriter" %>
<%
    List<DepartmentItem> departments = (List<DepartmentItem>) request.getAttribute("deps");
%>

<body>
<% if (departments != null) { %>
<ul>
    <% for (DepartmentItem departmentItem : departments) { %>
    <li><%= departmentItem.toString() %></li>
    <% } %>
</ul>
<% } %>
</body>
</html>
