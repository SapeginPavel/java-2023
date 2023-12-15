<%@ page import="ru.vsu.cs.sapegin.repository.item.ProductItem" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 16.12.2023
  Time: 0:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ProductItem productItem = (ProductItem) request.getAttribute("prod");%>
<html>
<head>
    <title><%=productItem.getName()%></title>
</head>
<body >
    <h2><%=productItem.getName()%></h2>
    <div style="margin-bottom: 10px">
        <div><%="ID: " + productItem.getId()%></div>
        <div><%="Стоимость: " + productItem.getCost()%></div>
    </div>
    <a href="<%=productItem.getId()%>/edit">Изменить</a>
    <a href="/products">Назад</a>
</body>
</html>
