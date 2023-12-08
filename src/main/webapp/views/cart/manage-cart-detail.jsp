<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Chi tiết giỏ hàng</title>
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
    </style>
</head>

<body>
    <jsp:include page="../index/header-admin-page.jsp"/>
    <main>
        <h1 class="text-center">Quản lý giỏ hàng</h1>
        <h3 class="text-center title-order">Giỏ hàng #${Cart.cartID}</h3>
        <h5 class="text-center title-order">Khách hàng: ${Cart.customer.name} (#${Cart.customer.customerID})</h5>
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
                        <th scope="col">Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="count" value="1"></c:set>
                    <c:forEach var="row" items="${Cart.cartitemList}">
                        <tr id="row-${row.cartitemPK.productID}-${row.cartitemPK.colorID}">
                            <th scope="row">${count}</th>
                            <td>${row.product.productID}</td>
                            <td><a href="/banhang/product/${row.product.productID}">${row.product.name}</a></td>
                            <td>${row.color.name}</td>
                            <td><img src="/banhang/${row.productColor.images}" /></td>
                            <td><fmt:formatNumber value="${row.product.price}" pattern="###,###,###"/></td>
                            <td>${row.quantity}</td>
                            <td><fmt:formatNumber value="${row.product.price*row.quantity}" pattern="###,###,###"/></td>
                            <td><button
                                    type="button" class="btn btn-danger"
                                    name="delete-product-cart" 
                                    value="${row.cartitemPK.productID}-${row.cartitemPK.colorID}-${Cart.cartID}"
                                    >
                                    Xóa
                                </button>
                            </td>
                        </tr>
                    <c:set var="count" value="${count+1}"></c:set>    
                    </c:forEach>
                </tbody>
            </table>
            <div class="row text-end">
                <div class="col total" id="total-price">
                    Tổng tiền: <fmt:formatNumber value="${Cart.totalPrice}" pattern="###,###,###"/>
                </div>
            </div>
        </div>

    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous">
    </script>
    <script>
        const buttons = document.querySelectorAll('button[name="delete-product-cart"]');
        buttons.forEach((button) => {
            button.addEventListener('click', function(){
                const value  = button.value;
                const tokens = value.split("-");
                const productId = tokens[0];
                const colorId = tokens[1];
                const cartId = tokens[2];
                
                fetch('../delete?product='+productId+'&color='+colorId+'&cart='+cartId, {
                  method: 'POST'
                })
                .then(response => response.json())
                .then(data => {
                    if(data.Reusult===true){
                        var element = document.getElementById('row-'+value);
                        if (element) {
                          element.remove();
                        }
                        updateDataById('total-price','Tổng tiền: '+data.TotalPrice.toLocaleString());
                    }
                })
                .catch(error => {
                   console.error('Error:', error);
                });
            });
        });
        function updateDataById(id, value) {
            const td = document.getElementById(id);
            if (td) {
              td.textContent = value;
            }
        }
    </script>
</body>
</html>
