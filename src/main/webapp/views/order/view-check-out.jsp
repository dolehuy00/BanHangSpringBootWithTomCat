
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Thông tin đơn hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="css/style.css">
    <style>
        a {
            text-decoration: none;
        }

        .quantity {
            max-width: 60px;
        }

        td img {
            height: 100px;
            width: 100px;
        }

        main {
            margin: 40px 40px;
        }
        h1{
            margin-bottom: 20px;
        }
        .total{
            font-weight: bold;
        }
        #total-price{
            font-weight: bold;
            font-size: 25px;
        }
        .no-spinners::-webkit-inner-spin-button,
        .no-spinners::-webkit-outer-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }
    </style>
</head>

<body>
    <header>
        <jsp:include page="../index/header-top.jsp"></jsp:include>
        <jsp:include page="../index/header-middle.jsp"></jsp:include>
    </header>
    <main>
        <div class="container">
            <h1 class="text-center">Thông tin đơn hàng</h1>
            <form action="checkout" method="post">
            <table class="table text-center align-middle">
                <thead>
                    <tr>
                        <th scope="col">Mã SP</th>
                        <th scope="col">Tên SP</th>
                        <th scope="col">Màu sắc</th>
                        <th scope="col">Hình ảnh</th>
                        <th scope="col">Số lượng</th>
                        <th scope="col">Giá</th>
                        <th scope="col">Tổng</th>
                    </tr>
                </thead>
                
                <tbody>
                    <c:forEach var="row" items="${sessionScope.NewOrder.orderItemList}">
                        <tr>
                            <td>${row.product.productID}</td>
                            <td>${row.product.name}</td>
                            <td>${row.color.name}</td>
                            <td><img src="${row.productColor.images}" /></td>
                            <td>${row.quantity}</td>
                            <td><fmt:formatNumber value="${row.product.price}" pattern="###,###,###"/></td>
                            <td><fmt:formatNumber value="${row.product.price*row.quantity}" pattern="###,###,###"/></td>
                        </tr>
                    </c:forEach> 
                </tbody>
            </table>
            <div class="row text-end">
                <div class="col total">
                    <p id="total-price">Tổng tiền: <fmt:formatNumber value="${sessionScope.NewOrder.totalPrice}" pattern="###,###,###"/></p>
                </div>
            </div>
            <div class="input-group flex-nowrap">
                <span class="input-group-text" id="addon-wrapping">Số điện thoại</span>
                <input name="phone-number" type="number" class="form-control no-spinners" aria-label="PhoneNumber" aria-describedby="addon-wrapping" required>
            </div>    
            <br>
            <div class="form-floating">
                <textarea name="address" class="form-control" 
                          placeholder="Leave a comment here" 
                    id="floatingTextarea2" style="height: 100px" required>${sessionScope.CUSTOMER.address}</textarea>
                <label for="floatingTextarea2">Địa chỉ</label>
            </div>
            <br>
            <button type="submit" class="btn btn-primary btn-lg float-end" id="btn-check-out">Xác nhận đặt hàng</button>    
        </form>
        </div>
    </main>
    <jsp:include page="../index/footer.jsp"></jsp:include>  
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>   
</body>
</html>
