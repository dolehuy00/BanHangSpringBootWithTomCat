<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!--Danh sách sản phẩm-->
<div class="row" id="container-product">
    <c:forEach var="row" items="${ListProduct}">
        <fmt:formatNumber value="${row.price}" pattern="###,###,###" var="formattedPrice" />
        <jsp:include page="item-product.jsp">
            <jsp:param name="image" value="${row.productColorList[0].images}" />
            <jsp:param name="linkDetail" value="product/${row.productID}" />
            <jsp:param name="title" value="${row.name}" />
            <jsp:param name="price" value="${formattedPrice}" />
            <jsp:param name="reviewCount" value="(${row.reviewList.size()} Đánh giá)" />
            <jsp:param name="ratingCount" value="${row.getAverageStar()}" />
        </jsp:include>
    </c:forEach>
</div>
<!--Số lượng sản phẩm trả về-->
<label class="text-start col" id="quantity-result">Tìm thấy ${CountProduct} kết quả</label>
<!--Số trang-->
<ul class="pagination" id="quantity-page">
    <c:forEach begin="1" end="${CountPage}" step="1" var="number">
        <li class="page-item"><button class="page-link" name="page" type="button" value="${number}">${number}</button></li>
    </c:forEach>
</ul>