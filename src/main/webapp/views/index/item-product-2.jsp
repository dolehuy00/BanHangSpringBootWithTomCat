<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="product product-2">
    <figure class="product-media">  
        <a href="${param.linkDetail}">
            <img src="${param.image}" alt="Product image" class="product-image">
        </a>
        <div class="product-action-vertical">
            <a href="#" class="btn-product-icon fa-regular fa-heart" title="Add to wishlist"></a>
        </div><!-- End .product-action -->
        <div class="product-action">
            <a href="#" class="btn-product fas fa-shopping-cart" title="Add to cart"><span>add to cart</span></a>
        </div><!-- End .product-action -->
    </figure><!-- End .product-media -->
    <div class="product-body">
        <div class="product-cat">
            <a href="${param.linkCategory}">${param.category}</a>
        </div><!-- End .product-cat -->
        <h3 class="product-title"><a href="product.html">${param.title}</a></h3><!-- End .product-title -->
        <div class="product-price">
            ${param.price}
        </div><!-- End .product-price -->
        <div class="ratings-container">
            <div class="ratings">
                <c:set var="ratingCount" value="${param.ratingCount*10}" />
                <c:forEach begin="0" end="${ratingCount}" step="10" varStatus="i">
                    <c:set var="currentValue" value="${ratingCount-(i.index - 10)-10}"/>
                    <c:if test="${currentValue >= 10}">
                        <div class="fa-solid fa-star"></div>
                    </c:if> 
                    <c:if test="${currentValue < 10 && currentValue > 0}">
                        <div class="fa fa-star-half fa-star"></div>
                    </c:if>    
                </c:forEach>
            </div><!-- End .ratings -->
            <span class="ratings-text">${param.reviewCount}</span>
        </div><!-- End .rating-container -->
    </div><!-- End .product-body -->
</div><!-- End .product -->



