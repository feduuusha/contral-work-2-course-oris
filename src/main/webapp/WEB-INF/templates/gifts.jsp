<%@ page import="ru.itis.contralwork.entity.Gift"%>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Gifts</title>
</head>
<body>
<form action="/gifts" method="post">

    Input gift id:
    <label>
        <input type="number" name="id">
    </label>
    <button type="submit">Get gift by ID</button>
</form>
<table>
    <th>Gift ID</th>
    <th>Gift Name</th>
    <th>Gift Giver Name</th>
    <th>Gift Receiver Name</th>
    <th>Gift Date Of Given</th>

    <%
        List<Gift> gifts = (List<Gift>) request.getAttribute("gifts");
        for (var gift: gifts) {

    %>
    <tr>
        <td><%=gift.getId()%></td>
        <td><%=gift.getName()%></td>
        <td><%=gift.getGiverName()%></td>
        <td><%=gift.getReceiverName()%></td>
        <td><%=gift.getDateOfGiven()%></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>