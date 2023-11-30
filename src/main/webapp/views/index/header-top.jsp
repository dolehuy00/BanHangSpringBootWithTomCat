<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="web.Model.Customer" %>
<div class="header-top">
    <div class="row align-items-start">
        <div class="col"></div>
        <div class="col text-end">
          <%
            Customer customer = (Customer) session.getAttribute("CUSTOMER");
            if(customer !=null){
                out.print("<a href=\"/banhang/profile\">"+customer.getName()+"</a>"
                        + "<a href=\"/banhang/logout\">Đăng xuất</a> ");   
            }else{
                out.print("<a href=\"/banhang/login\">Đăng nhập/Đăng ký</a>");  
            }
          %>
        </div>
      </div>
</div>
<hr>
