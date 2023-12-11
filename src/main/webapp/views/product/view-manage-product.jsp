
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Quản lý sản phẩm</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="../css/manage-product.css" rel="stylesheet">
        <link href="../css/admin.css" rel="stylesheet">
    </head>

    <body>
        <jsp:include page="../index/header-admin-page.jsp"/>
        <main>
            <div class="container">
                <div class="row align-items-center">
                    <div class="col text-center title">
                        Quản lý sản phẩm
                    </div>
                </div>
                <div class="row text-center menu">
                    <div class="col">
                        <a href="product-management/add">Thêm sản phẩm</a>
                    </div>
                </div>
                <div class="row align-items-center">
                    <div class="col text-center search">
                        <form action="" method="">
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="inputGroup-sizing-default">Tìm kiếm</span>
                                <input type="text" name="search" class="form-control" aria-label="Sizing example input"
                                       aria-describedby="inputGroup-sizing-default">
                                <button type="">Tìm</button>
                            </div>
                        </form>
                    </div>
                </div>
                <form>
                <table class="table text-center align-middle">
                    <thead>
                        <tr>
                            <th scope="col">STT</th>
                            <th scope="col">Mã SP</th>
                            <th scope="col">Tên SP</th>
                            <th scope="col">Nhà cung cấp</th>
                            <th scope="col">Giá</th>
                            <th scope="col">Trạng thái</th>
                            <th scope="col">Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="count" value="1"/>
                        <c:forEach var="row" items="${ListProduct}">
                            <tr>
                              <th scope="row">${count}</th>
                              <td>${row.productID}</td>
                              <td>${row.name}</td>
                              <td>${row.supplier.name}</td>
                              <td><fmt:formatNumber value="${row.price}" pattern="###,###,###"/></td>
                              <td>${row.status.name}</td>
                              <td>
                                  <a href="product-management/edit/${row.productID}"><button type="button" class="btn btn-info">Sửa</button></a>
                                  <c:choose>
                                      <c:when test="${row.status.statusID == 1}">
                                        <button type="submit" formaction="product-management/lock" 
                                          formmethod="post" value="${row.productID}" 
                                          name="id" class="btn btn-danger">Ngừng kinh doanh
                                        </button>
                                      </c:when>
                                      <c:otherwise>
                                        <button type="submit" formaction="product-management/unlock" 
                                          formmethod="post" value="${row.productID}" 
                                          name="id" class="btn btn-danger">Tiếp tục kinh doanh
                                        </button>
                                      </c:otherwise>
                                  </c:choose>
                              </td>
                            </tr>
                            <c:set var="count" value="${count+1}"/>
                        </c:forEach>
                    </tbody>
                </table>
                </form>        
            </div>
        </main>
        <jsp:include page="../index/footer-admin-page.jsp"/>   
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    </body>

</html>