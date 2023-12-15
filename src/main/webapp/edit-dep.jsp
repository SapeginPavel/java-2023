<%@ page import="ru.vsu.cs.sapegin.repository.item.DepartmentItem" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 15.12.2023
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% DepartmentItem department = (DepartmentItem) request.getAttribute("dep"); %>
<html>
<head>
    <title>Редактирование</title>
</head>
<body>
    <form method="post" action="<%=department.getId()%>">
        <label>Название</label>
        <input name="name" value="<%=department.getName()%>"><br><br>
        <label>Время открытия</label>
        <input name="open_time" value="<%=department.getOpenTime()%>"><br><br>
        <label>Время закрытия</label>
        <input name="close_time" value="<%=department.getCloseTime()%>"><br><br>
        <input type="submit" value="Сохранить">
    </form>
    <form method="post" action="<%=department.getId()%>">
        <input name="delete" hidden>
        <input type="submit" value="Удалить отдел">
    </form>
    <br>
    <a href="/departments/<%=department.getId()%>">Назад</a>
</body>
</html>
