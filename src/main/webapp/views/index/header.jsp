<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header>
    <div class="header-top">
        <div class="row align-items-start">
            <div class="col">
            </div>
            <div class="col text-end">
              <a href="/banhang/login">
                  <c:set var="customer" value="${sessionScope.CUSTOMER}" />
                  <c:choose>
                    <c:when test="${not empty customer}">
                        <c:out value="${customer.name}"/>
                    </c:when>
                    <c:otherwise>Đăng nhập/Đăng ký</c:otherwise>
                </c:choose>
              </a> 
            </div>
          </div>
    </div><!--end header-top-->
    <hr>
    <div class="header-middle ">
        <div class="container text-center ">
            <div class="row align-items-center">

              <div class="col header-middle-left">
                <img src="images/logo.png" alt="logo">
              </div>

              <div class="col header-middle-middle">
                <form acction="" method="get">
                    <div class="search-bar">
                        <input type="text" placeholder="Tìm kiếm sản phẩm ...">
                        <button class="btn" type="submit">
                            <i class="fas fa-search"></i>
                        </button>
                    </div>
                </form>
              </div>

              <div class="col header-middle-right">  
                <a href="#">
                    <i class="fa-regular fa-heart">
                        <span class="badge wishlist-count">3</span>
                    </i>
                </a> 
                <a href="#">
                    <i class="fas fa-shopping-cart">
                        <span class="badge wishlist-count">3</span>
                    </i>
                </a>     
              </div>
            </div>
        </div>
    </div><!--end header-middle-->
    <div class="header-bottom">
        <div class="container text-center">
            <div class="row align-items-center">
                <div class="col text-start dropdown menu-category">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown">
                    Danh mục sản phẩm
                    </button>
                    <ul class="dropdown-menu ">
                        <c:forEach var="row" items="${listSupplier}">
                            <li><a class="dropdown-i tem" href="#">${row.name}</a></li>
                        </c:forEach> 
                    </ul>
                </div>
                <div class="col menu-page">
                    <ul class="menu-page sf-arrows">
                        <li>
                            <a href="/banhang">Home</a>
                        </li>
                    </ul>
                </div>
            </div>
          </div>
    </div><!--end header-bottom-->
</header>
