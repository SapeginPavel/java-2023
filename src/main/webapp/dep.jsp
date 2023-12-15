<%@ page import="ru.vsu.cs.sapegin.repository.item.DepartmentItem" %><%--
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
    <h2 style="margin-left: 20px"><%=departmentItem.getName()%></h2>
    <div>
        <p><%="ID: " + departmentItem.getId()%></p>
        <p><%="Время работы: " + departmentItem.getOpenTime() + " - " + departmentItem.getCloseTime()%></p>
    </div>
    <div style="margin-top: 30px;">
        <div>

        </div>
    </div>
</body>
</html>
