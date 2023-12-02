
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Giỏ hàng</title>
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
    </style>
</head>

<body>
    <header>
        <jsp:include page="../index/header-top.jsp"></jsp:include>
        <jsp:include page="../index/header-middle.jsp"></jsp:include>
    </header>
    <main>
        <div class="container">
            <h1 class="text-center">Giỏ hàng</h1>
            <table class="table text-center align-middle">
                <thead>
                    <tr>
                        <th scope="col">Mã SP</th>
                        <th scope="col">Tên SP</th>
                        <th scope="col">Màu sắc</th>
                        <th scope="col">Hình ảnh</th>
                        <th scope="col">Số lượng</th>
                        <th scope="col">Giá</th>
                        <th scope="col">Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="row" items="${cart.cartitemList}">
                    
                        <tr>
                            <td>${row.product.productID}</td>
                            <td>${row.product.name}</td>
                            <td>${row.color.name}</td>
                            <td><img src="${row.productColor.images}" /></td>
                            <td><form>
                                    <input class="quantity" type="number" min="1"
                                    max="${row.productColor.quantity}"
                                    id="${row.cartitemPK.cartID}-${row.cartitemPK.productID}-${row.cartitemPK.colorID}" 
                                    name="quantity" value="${row.quantity}" />
                                </form>
                            </td>
                            <td>${row.product.price}</td>
                            <td><a href="">Xóa</a></td>
                        </tr>
                        
                    </c:forEach>  
                </tbody>
            </table>
            <div class="row text-end">
                <div class="col total">
                    Tổng tiền: <fmt:formatNumber value="${cart.totalPrice}" pattern="###,###,###"/>
                </div>
            </div>
        </div>
    </main>
    <jsp:include page="../index/footer.jsp"></jsp:include>            
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    <!-- đặt về max khi nhập số lượng lớn hơn max    -->
    <script>
        var inputs = document.querySelectorAll('input[name="quantity"]');

        inputs.forEach((input) => {
            input.addEventListener('blur', function () {
                var value = parseInt(this.value);
                var max = parseInt(this.getAttribute('max'));

                if (value > max) {
                    this.value = max.toString();
                }
            });
        });
    </script>
</body>

</html>
