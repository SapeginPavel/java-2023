<%@ page import="ru.vsu.cs.sapegin.repository.item.DepartmentItem" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.vsu.cs.sapegin.repository.item.ProductItem" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 15.12.2023
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%DepartmentItem departmentItem = (DepartmentItem) request.getAttribute("dep");%>
    <title><%=departmentItem.getName()%></title>
</head>
<body>
    <h2><%=departmentItem.getName()%></h2>
    <div style="margin-bottom: 10px">
        <div><%="ID: " + departmentItem.getId()%></div>
        <div><%="Время работы: " + departmentItem.getOpenTime() + " - " + departmentItem.getCloseTime()%></div>
    </div>
    <a href="<%=departmentItem.getId()%>/edit">Изменить</a>
    <a href="/departments">Назад</a>

    <div style="margin-top: 55px;">
        <h4>Продукты отдела:</h4>
        <% List<ProductItem> products = (List<ProductItem>) request.getAttribute("prods"); %>
        <div>
            <% for (ProductItem p : products) { %>
            <div style="margin-top: 20px">
                <div><%= "id: " + p.getId() %></div>
                <div><%= "Название: " + p.getName() %></div>
                <div><%= "Стоимость: " + p.getCost() %></div>
            </div>
            <% } %>
        </div>
    </div>
</body>
</html>
