<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Quản lý giỏ hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link href="../css/admin.css" rel="stylesheet">
    <style>
      .search {
        margin-left: 20%;
        margin-right: 20%;
      }

      a {
        text-decoration: none;
      }

      td a{
        margin-left: 10px;
      }
    </style>
</head>
  <body>
    <jsp:include page="../index/header-admin-page.jsp"/>
    <main>
        <div class="container">
            <h3 class="text-center">Quản lý giỏ hàng</h3>
            <div class="row align-items-center">
              <div class="col text-center search">
                <form action="" method="">
                  <div class="input-group mb-3">
                    <span class="input-group-text" id="inputGroup-sizing-default">Tìm kiếm</span>
                    <input type="text" name="search" class="form-control" aria-label="Sizing example input"
                      aria-describedby="inputGroup-sizing-default">
                    <button type="submit">Tìm</button>
                  </div>
              </div>
              </form>
            </div>
            <table class="table text-center align-middle">
              <thead>
                <tr>
                  <th scope="col">STT</th>
                  <th scope="col">Mã</th>
                  <th scope="col">Khách hàng</th>
                  <th scope="col">Tổng giá</th>
                  <th scope="col">Tổng SL</th>
                  <th scope="col">Thao tác</th>
                </tr>
              </thead>
              <tbody>
                  <c:set var="count" value="1"/>
                  <c:forEach var="row" items="${ListCart}">
                      <tr>
                        <th scope="row">${count}</th>
                        <td>${row.cartID}</td>
                        <td><a href="">${row.customer.name}</a></td>
                        <td>${row.totalPrice}</td>
                        <td>${row.totalQuantity}</td>
                        <td>
                            <a href="cart-management/view/${row.cartID}">Chi tiết</a>
                            <a href="cart-management/empty/${row.cartID}" style="color: red; margin-left: 50px">Làm rỗng</a>
                        </td>
                      </tr>
                      <c:set var="count" value="${count+1}"/>
                  </c:forEach>
              </tbody>
            </table>
          </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
  </body>
</html>