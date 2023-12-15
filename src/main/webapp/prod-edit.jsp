<%@ page import="ru.vsu.cs.sapegin.repository.item.ProductItem" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 16.12.2023
  Time: 1:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ProductItem product = (ProductItem) request.getAttribute("prod"); %>
<html>
<head>
    <title>Редактирование товара</title>
</head>
<body>
    <form method="post" action="<%=product.getId()%>">
        <label>Название</label>
        <input name="name" value="<%=product.getName()%>"><br><br>
        <label>Стоимость</label>
        <input name="open_time" value="<%=product.getCost()%>"><br><br>
        <label>Время закрытия</label>
    </form>
    <form method="post" action="<%=product.getId()%>">
        <input name="delete" hidden>
        <input type="submit" value="Удалить товар">
    </form>
    <br>
    <a href="/products/<%=product.getId()%>">Назад</a>
</body>
</html>
