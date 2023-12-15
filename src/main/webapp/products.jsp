<%@ page import="java.util.List" %>
<%@ page import="ru.vsu.cs.sapegin.repository.item.ProductItem" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 16.12.2023
  Time: 0:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<ProductItem> products = (List<ProductItem>) request.getAttribute("prods"); %>
<html>
<head>
    <title>Товары</title>
</head>
<body style="margin-left: 15px">
    <% if (products != null) { %>
    <a href="/">На главную</a>
    <a href="/dep/create">Создать товар</a>
    <h1>Список товаров магазина:</h1>
    <div>
        <% for (ProductItem productItem : products) { %>
        <div>
            <div><%= "ID: " + productItem.getId() %></div>
            <div><%= "Название: " + productItem.getName() %></div>
            <div><%= "Стоимость: " + productItem.getCost()%></div>
            <a href="/products/<%=productItem.getId()%>">Перейти к товару</a>
        </div>
        <p></p>
        <% } %>
    </div>
    <% } %>

</body>
</html>
