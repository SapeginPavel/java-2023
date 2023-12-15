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

<body style="margin-left: 15px">
<% if (departments != null) { %>
<a href="/">На главную</a>
<a href="/dep/create">Создать отдел</a>
<h1>Список отделов магазина:</h1>
<div>
    <% for (DepartmentItem departmentItem : departments) { %>
    <div>
        <div><%= "ID: " + departmentItem.getId() %></div>
        <div><%= "Название: " + departmentItem.getName() %></div>
        <div><%= "Время работы: " + departmentItem.getOpenTime() + " - " + departmentItem.getCloseTime()%></div>
        <a href="/departments/<%=departmentItem.getId()%>">Перейти в отдел</a>
    </div>
    <p></p>
    <% } %>
</div>
<% } %>
</body>
</html>
