

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="web.Model.User" %>
<header>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/banhang/admin">Trang quản lý</a>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" style="font-size: 20px" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Các chức năng
                        </a>
                        <ul class="dropdown-menu">
                            <c:choose>
                                <c:when test="${sessionScope.ADMIN.role.roleID==2}">
                                    <li><a class="dropdown-item" href="/banhang/admin/product-management/view">Quản lý sản phẩm</a></li>
                                    <li><a class="dropdown-item" href="/banhang/admin/color-management/view">Quản lý màu sản phẩm</a></li>
                                    <li><a class="dropdown-item" href="/banhang/admin/order-awaiting">Đơn hàng đang chờ</a></li>
                                    <li><a class="dropdown-item" href="/banhang/admin/order-confirmed">Đơn hàng đã tiếp nhận</a></li>
                                    <li><a class="dropdown-item" href="/banhang/admin/order-delivering">Đơn hàng đang giao</a></li>
                                    <li><a class="dropdown-item" href="/banhang/admin/order-finished">Đơn hàng đã hoàn thành</a></li>
                                    <li><a class="dropdown-item" href="/banhang/admin/review-management">Đánh giá sản phẩm chưa phản hồi</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a class="dropdown-item" href="/banhang/admin/order-awaiting">Đơn hàng đang chờ</a></li>
                                    <li><a class="dropdown-item" href="/banhang/admin/order-confirmed">Đơn hàng đã tiếp nhận</a></li>
                                    <li><a class="dropdown-item" href="/banhang/admin/order-delivering">Đơn hàng đang giao</a></li>
                                    <li><a class="dropdown-item" href="/banhang/admin/order-finished">Đơn hàng đã hoàn thành</a></li>
                                    <li><a class="dropdown-item" href="/banhang/admin/review-management">Đánh giá sản phẩm chưa phản hồi</a></li>
                                    <li><a class="dropdown-item" href="/banhang/admin/product-management/view">Quản lý sản phẩm</a></li>
                                    <li><a class="dropdown-item" href="/banhang/admin/color-management/view">Quản lý màu sản phẩm</a></li>
                                    <li><a class="dropdown-item" href="/banhang/admin/cart-management/view">Quản lý giỏ hàng</a></li>
                                    <li><a class="dropdown-item" href="/banhang/admin/manager-management/view">Quản lý nhân viên</a></li>
                                    <li><a class="dropdown-item" href="/banhang/admin/customer-management/view">Quản lý khách hàng</a></li>
                                    <li><a class="dropdown-item" href="/banhang/admin/order-management/view">Quản lý đơn hàng</a></li>
                                    <li><a class="dropdown-item" href="/banhang/admin/supplier-management/view">Quản lý nhà cung cấp</a></li>
                                    <li><a class="dropdown-item" href="/banhang/admin/statistic">Thống kê</a></li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </li>
                </ul>
                <%
                    User user = (User)session.getAttribute("ADMIN");
                    if(user != null){
                        out.print("<a href=\"#\" class=\"d-flex link-user\">"+user.getName()+"</a>\n"
                                + "<a href=\"/banhang/admin/logout\" class=\"d-flex link-login\">Đăng xuất</a>");
                    }else{
                        out.print("<a href=\"/banhang/admin/login\" class=\"d-flex link-login\">Đăng nhập</a>");
                    }
                %>    
            </div>
        </div>
    </nav>
</header>
