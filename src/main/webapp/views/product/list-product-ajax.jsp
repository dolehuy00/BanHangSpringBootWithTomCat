<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row" id="container-product">
    <!-- product -->
    <c:forEach var="row" items="${ListProduct}">
        <fmt:formatNumber value="${row.price}" pattern="###,###,###" var="formattedPrice" />
        <jsp:include page="item-product.jsp">
            <jsp:param name="image" value="${row.productColorList[0].images}" />
            <jsp:param name="linkDetail" value="" />
            <jsp:param name="title" value="${row.name}" />
            <jsp:param name="price" value="${formattedPrice}" />
            <jsp:param name="reviewCount" value="(4 Reviews)" />
            <jsp:param name="ratingCount" value="4.5" />
        </jsp:include>
    </c:forEach>
    <!-- /product -->
</div>
