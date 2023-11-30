<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
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
</div>
