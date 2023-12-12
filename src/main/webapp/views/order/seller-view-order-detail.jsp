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
        <link href="../../../css/admin.css" rel="stylesheet">
        <style>
            main {
                margin-top: 10px;
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
            .info{
                margin-left: 10%;
            }
        </style>
    </head>

    <body>
        <jsp:include page="../index/header-admin-page.jsp"/>
        <main>
            <h1 class="text-center">Chi tiết đơn hàng</h1>
            <h3 class="text-center title-order">Đơn hàng #${Order.orderID}</h3>
            <h5 class="title-order info">- Khách hàng: ${Order.customerID.name} (#${Order.customerID.customerID})</h5>
            <h5 class="title-order info">- Nhân viên: ${Order.seller.name} (#${Order.seller.userID})</h5>
            <h5 class="title-order info">- Ngày đặt: ${Order.date.dayOfMonth}/${Order.date.monthValue}/${Order.date.year}  
                ${Order.date.hour}:${Order.date.minute}:${Order.date.second}</h5>
            <div class="container">
                <table class="table text-center align-middle">
                    <thead>
                        <tr>
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
                        <c:forEach var="row" items="${Order.orderItemList}">
                            <tr id="row-${row.orderItemPK.productID}-${row.orderItemPK.colorID}">
                                <td scope="row">${row.product.productID}</td>
                                <td><a href="/banhang/product/${row.product.productID}">${row.product.name}</a></td>
                                <td>${row.color.name}</td>
                                <td><img src="/banhang/${row.productColor.images}" /></td>
                                <td><fmt:formatNumber value="${row.product.price}" pattern="###,###,###"/></td>
                                <td>${row.quantity}</td>
                                <td><fmt:formatNumber value="${row.product.price*row.quantity}" pattern="###,###,###"/></td>
                            </tr>  
                        </c:forEach>
                    </tbody>
                </table>
                <div class="row text-end">
                    <div class="col total" id="total-price">
                        Tổng tiền: <fmt:formatNumber value="${Order.totalPrice}" pattern="###,###,###"/>
                    </div>
                </div>
                    <div class="row d-flex justify-content-end" style="margin-top: 20px">
                    <div class="col col-md-2 text-end">
                        <c:choose>
                            <c:when test="${view == 'awaiting'}">
                                <form action="../confirm" method="post">
                                    <button type="submit" name="id" value="${Order.orderID}" class="btn btn-success">Tiếp nhận</button> 
                                </form>
                            </c:when>
                            <c:when test="${view == 'confirmed'}">
                                <form action="../deliver" method="post">
                                    <button type="submit" name="id" value="${Order.orderID}" class="btn btn-success">Giao hàng</button> 
                                </form>
                            </c:when>
                            <c:when test="${view == 'delivering'}">
                                <form action="../finish" method="post">
                                    <button type="submit" name="id" value="${Order.orderID}" class="btn btn-success">Hoàn thành</button> 
                                </form>
                            </c:when>
                        </c:choose> 
                    </div>
                    <div class="col col-md-2 text-center">
                        <c:if test="${view == 'awaiting' || view == 'delivering' || view == 'confirmed'}">
                            <form action="/banhang/admin/order/cancel" method="post">
                                <button type="submit" name="id" value="${Order.orderID}" class="btn btn-danger">Hủy đơn</button> 
                            </form>
                        </c:if>
                    </div>    
                </div>   
            </div>
        </main>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous">
        </script>
    </body>
</html>
