
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Quản lý đánh giá sản phẩm</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="../css/admin.css" rel="stylesheet">
        <style>
            .title {
                font-size: 50px;
            }

            a {
                text-decoration: none;
            }

            .search {
                margin-left: 20%;
                margin-right: 20%;
            }

            .menu {
                padding-top: 20px;
                padding-bottom: 20px;
            }

            td a {
                margin-left: 10px;
            }
            .btn-danger{
                margin-left: 10px
            }
        </style>
    </head>

    <body>
        <jsp:include page="../index/header-admin-page.jsp"/>
        <main>
            <div class="container">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col text-center title">
                            Quản lý đánh giá sản phẩm
                        </div>
                    </div>
                    <div class="row align-items-center">
                        <div class="col text-center" style="font-size: 30px">
                            Danh sách các đánh giá chưa có phản hồi
                        </div>
                    </div>
                    <form>
                        <table class="table text-center align-middle">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Sản phẩm</th>
                                    <th scope="col">Khách hàng</th>
                                    <th scope="col">Nội dung</th>
                                    <th scope="col">Điểm</th>
                                    <th scope="col">Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="row" items="${ListReview}">
                                    <tr>
                                        <th scope="row">${row.reviewID}</th>
                                        <td>${row.productID.name} #${row.productID.productID}</td>
                                        <td>${row.cutomerID.name} #${row.cutomerID.customerID}</td>
                                        <td>${row.substance}</td>
                                        <td>${row.star}</td>
                                        <td><a href="/banhang/product/${row.productID.productID}">Trả lời</a></td>
                                    </tr>
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
