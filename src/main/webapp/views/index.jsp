<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<body>
    <img src="images/iphone.png">
    <c:forEach var="item" items="${items}">
        <br>
        Tên: ${item.name}
        <br>
    </c:forEach>
</body>

