<%@ page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <li><a class="dropdown-item" href="/banhang/admin/cart-management">Quản lý giỏ hàng</a></li>
                        <li><a class="dropdown-item" href="#">Quản lý sản phẩm</a></li>
                        <li><a class="dropdown-item" href="/banhang/admin/manager-management">Quản lý nhân viên</a></li>
                        <li><a class="dropdown-item" href="#">Quản lý khách hàng</a></li>
                        <li><a class="dropdown-item" href="#">Quản lý đơn hàng</a></li>
                        <li><a class="dropdown-item" href="#">Thống kê</a></li>
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
