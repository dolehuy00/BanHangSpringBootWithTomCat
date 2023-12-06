<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Chi tiết đơn hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        main {
            margin-top: 5%;
        }
        .total{
            margin-right: 5%;
            font-weight: bold;
        }
        .title-order{
            color: black;
        }
        td img{
            height: 80px;
            width: 80px;
        }
        td a{
            color: inherit;
            font-family: inherit;
            font-weight: inherit;
        }
    </style>
</head>

<body>
    <header>
        <jsp:include page="../index/header-top.jsp"></jsp:include>
        <jsp:include page="../index/header-middle.jsp"></jsp:include>
    </header>
    <main>
        <h1 class="text-center">Thông tin tài khoản</h1>
        <h3 class="text-center title-order">Đơn hàng #${Order.orderID}</h3>
        <div class="container">
            <table class="table text-center align-middle">
                <thead>
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Mã SP</th>
                        <th scope="col">Tên SP</th>
                        <th scope="col">Màu</th>
                        <th scope="col">Hình ảnh</th>
                        <th scope="col">Giá</th>
                        <th scope="col">Số lượng</th>
                        <th scope="col">Tổng giá</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="count" value="1"></c:set>
                    <c:forEach var="row" items="${OrderItems}">
                        <tr>
                            <th scope="row">${count}</th>
                            <td>${row.product.productID}</td>
                            <td><a href="/banhang/product/${row.product.productID}">${row.product.name}</a></td>
                            <td>${row.color.name}</td>
                            <td><fmt:formatNumber value="${row.product.price}" pattern="###,###,###"/></td>
                            <td><img src="/banhang/${row.productColor.images}" /></td>
                            <td>${row.quantity}</td>
                            <td><fmt:formatNumber value="${row.product.price*row.quantity}" pattern="###,###,###"/></td>
                        </tr>
                    <c:set var="count" value="${count+1}"></c:set>    
                    </c:forEach>
                </tbody>
            </table>
            <div class="row text-end">
                <div class="col total">
                    Tổng tiền: <fmt:formatNumber value="${Order.totalPrice}" pattern="###,###,###"/>
                </div>
            </div>
        </div>

    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous">
        </script>
</body>
</html>
