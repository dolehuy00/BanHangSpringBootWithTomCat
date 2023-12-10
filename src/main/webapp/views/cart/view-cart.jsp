
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
        #total-price{
            font-weight: bold;
            font-size: 25px;
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
        <div class="container">
            <h1 class="text-center">Giỏ hàng</h1>
            <form action="pre-checkout" method="post" id="cart-form">
            <table class="table text-center align-middle">
                <thead>
                    <tr>
                        <th scope="col">Chọn</th>
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
                        <tr id="row-${row.cartitemPK.productID}-${row.cartitemPK.colorID}">
                            <td><input class="form-check-input" type="checkbox"
                                       value="${row.cartitemPK.productID}-${row.cartitemPK.colorID}"
                                       id="check-${row.cartitemPK.productID}-${row.cartitemPK.colorID}"
                                       name="choose-product">
                            </td>
                            <td>${row.product.productID}</td>
                            <td><a href="/banhang/product/${row.product.productID}">${row.product.name}</a></td>
                            <td>${row.color.name}</td>
                            <td><img src="${row.productColor.images}" /></td>
                            <td>
                                    <input class="quantity" type="number" min="1"
                                    max="${row.productColor.quantity}"
                                    id="quantity-${row.cartitemPK.productID}-${row.cartitemPK.colorID}" 
                                    name="quantity" value="${row.quantity}" />
                                
                            </td>
                            <td><fmt:formatNumber value="${row.product.price}" pattern="###,###,###"/></td>
                            <td><button 
                                    type="button" class="btn btn-danger"
                                    name="delete-product-cart" 
                                    value="${row.cartitemPK.productID}-${row.cartitemPK.colorID}"
                                    >
                                    Xóa
                                </button>
                            </td>
                        </tr>
                    </c:forEach> 
                        
                </tbody>
                
            </table>
            
            <div class="row text-end">
                <div class="col total">
                    <p id="total-price">Tổng tiền: <fmt:formatNumber value="${cart.totalPrice}" pattern="###,###,###"/></p>
                </div>
            </div>
            <button type="submit" class="btn btn-primary btn-lg float-end" id="btn-check-out">Mua hàng</button>    
        </form>
        </div>
        <div class="toast-container position-fixed bottom-0 end-0 p-3">
            <div id="messageCheckItem" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
              <div class="toast-header">
                <strong class="me-auto">Thông báo</strong>
                <small>now</small>
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
              </div>
                <div class="toast-body" id="messageQuantity">Bạn chưa chọn sản phẩm nào!</div>
            </div>
        </div>
    </main>
    <jsp:include page="../index/footer.jsp"></jsp:include>  
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    <!-- đặt về max khi nhập số lượng lớn hơn max -->
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
        
        //Chỉnh số lượng sản phẩm
        inputs.forEach(input => {
            input.addEventListener('change', function() {
                const inputId = input.id;
                const enteredValue = input.value;
                const tokens = inputId.split("-");
                const productId = tokens[1];
                const colorId = tokens[2];
                
                
                fetch('cart/change?product='+productId+'&color='+colorId+'&quantity='+enteredValue, {
                  method: 'POST'
                })
                .then(response => response.json())
                .then(data => {
                    updateDataById('total-price','Tổng tiền: '+data.TotalPrice.toLocaleString());
                    if(data.Quantity===0){
                        //Xử lý hết hàng
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
    <!--Xóa sản phẩm-->
    <script>
        const buttons = document.querySelectorAll('button[name="delete-product-cart"]');
        buttons.forEach((button) => {
            button.addEventListener('click', function(){
                const value  = button.value;
                const tokens = value.split("-");
                const productId = tokens[0];
                const colorId = tokens[1];
                
                fetch('cart/delete?product='+productId+'&color='+colorId, {
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
                        updateDataById('total-quantity',data.TotalQuantity);
                    }
                })
                .catch(error => {
                   console.error('Error:', error);
                });
            });
        });
    </script>
    <script>
        document.getElementById("cart-form").addEventListener("submit", function(event) {
            event.preventDefault(); 
            const productsChecked = document.querySelectorAll('input[name="choose-product"]:checked');
            if(productsChecked.length===0){
                const toastMessageAddCart = document.getElementById('messageCheckItem');
                const toast = bootstrap.Toast.getOrCreateInstance(toastMessageAddCart);
                toast.show();
                return;
            }
            var cartItemsChecked = [];
            var i = 0
            productsChecked.forEach((checkbox) => {
                const tokens = checkbox.value.split('-');
                const productId  = parseInt(tokens[0]);
                const colorId = parseInt(tokens[1]);
                const quantity = parseInt(document.getElementById('quantity-'+checkbox.value).value);
                var item = {
                    product: productId,
                    color: colorId,
                    quantity:quantity
                };
                cartItemsChecked[i] = item;
                i++;
            });
            fetch('pre-checkout', {
                method: 'POST',
                headers: {
                  "Content-Type": 'application/json',
                },
                body: JSON.stringify(cartItemsChecked),
            })
            .then(response => {
                window.location.href = "view-checkout";
            })
            .catch(error => {
                console.error('Error:', error);
            });
      });
    </script>
</body>
</html>
