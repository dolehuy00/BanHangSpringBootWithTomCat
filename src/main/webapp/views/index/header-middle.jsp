
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="header-middle ">
    <div class="container text-center ">
        <div class="row align-items-center">

          <div class="col header-middle-left">
              <a href="/banhang"><img src="/banhang/images/logo.png" alt="logo"></a>
          </div>

          <div class="col header-middle-middle">
            <form action="/banhang/search-product" method="get">
                <div class="search-bar">
                    <input type="text" name="keyword" id="keyword" value="${keyword}" placeholder="Tìm kiếm tên sản phẩm ...">
                    <button class="btn" type="submit">
                        <i class="fas fa-search"></i>
                    </button>
                </div>
            </form>
          </div>

          <div class="col header-middle-right">  
            <a href="/banhang/cart">
                <i class="fas fa-shopping-cart">
                    <span class="badge wishlist-count" id="total-quantity">
                        <c:choose>
                            <c:when test="${sessionScope.CUSTOMER.cart!=null}">
                                ${sessionScope.CUSTOMER.cart.totalQuantity}
                            </c:when>
                            <c:otherwise>
                                0
                            </c:otherwise>
                        </c:choose>        
                    </span>
                </i>
            </a>     
          </div>
        </div>
    </div>
</div>
<hr>
