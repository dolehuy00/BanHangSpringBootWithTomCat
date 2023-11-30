<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Thông tin tài khoản</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        main {
            margin-top: 5%;
        }

        .input-group {
            margin-top: 10px;
            margin-bottom: 10px;
        }

        .popupChangePassword {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 9999;
        }

        .popup-content {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: #fff;
            padding: 20px;
            text-align: center;
        }

        .close-popup-change-pass {
            position: absolute;
            top: 10px;
            right: 10px;
            cursor: pointer;
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
        <div class="container">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="profile-tab" data-bs-toggle="tab"
                        data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane"
                        aria-selected="false">Thông tin khách hàng</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="order-tab" data-bs-toggle="tab" data-bs-target="#order-tab-pane"
                        type="button" role="tab" aria-controls="order-tab-pane" aria-selected="true">Đơn hàng đã
                        đặt</button>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="profile-tab-pane" role="tabpanel"
                    aria-labelledby="profile-tab" tabindex="0">
                    <c:set var="customer" value="${sessionScope.CUSTOMER}"/>
                    <form action="profile/change-info" method="post">
                        <div class="input-group flex-nowrap">
                            <span class="input-group-text">Tên</span>
                            <input type="text" class="form-control" placeholder="Tên của bạn" aria-label="Name"
                                value="${customer.name}" name="name" aria-describedby="addon-wrapping" required>
                        </div>
                        <div class="input-group flex-nowrap">
                            <span class="input-group-text">Tài khoản</span>
                            <span class="input-group-text">${customer.username}</span>
                        </div>

                        <div class="input-group flex-nowrap">
                            <span class="input-group-text">Mật khẩu</span>
                            <span class="input-group-text">*********</span>
                            <button type="button" class="btn btn-info" id="buttonChangePass">Đổi</button>
                        </div>

                        <div class="input-group flex-nowrap">
                            <span class="input-group-text">Email</span>
                            <input type="text" class="form-control" placeholder="Email" aria-label="Email"
                                value="${customer.email}" name="email" aria-describedby="addon-wrapping" required>
                        </div>
                        <div class="input-group flex-nowrap">
                            <span class="input-group-text">Địa chỉ</span>
                            <input type="text" class="form-control" placeholder="Địa chỉ" aria-label="Username"
                                value="${customer.address}" name="address" aria-describedby="addon-wrapping">
                        </div>
                        <button class="btn btn-primary" type="submit">Lưu lại</button>
                    </form>
                </div>
                <div id="popupChangePassword" class="popupChangePassword">
                    <div class="popup-content">
                        <span class="close-popup-change-pass">&times;</span>
                        <form action="profile/change-pass" method="post">
                            <div class="container text-center">
                                <div class="row justify-content-md-center">
                                    <h5>Đổi mật khẩu</h5>
                                </div>
                                <p style="margin: 0px; color: green;" id="messageChangePass"></p>
                                <div class="input-group flex-nowrap">
                                    <span class="input-group-text">Mật khẩu cũ</span>
                                    <input type="password" id="oldPass" class="form-control" name="old-pass" required>
                                </div>
                                <p style="margin: 0px; color: red;" id="messageOldPass"></p>
                                <div class="input-group flex-nowrap">
                                    <span class="input-group-text">Mật khẩu mới</span>
                                    <input type="password" id="newPass" class="form-control" name="new-pass" required>
                                </div>
                                <p style="margin: 0px; color: red;" id="messageNotLong"></p>
                                <div class="input-group flex-nowrap">
                                    <span class="input-group-text">Xác nhận mật khẩu mới</span>
                                    <input type="password" id="confirmPass" class="form-control" name="confirm-pass" required>
                                </div>
                                <p style="margin: 0px; color: red;" id="messageNotMatch"></p>
                                <button type="button" class="btn btn-primary" id="btnChangePass">Xác nhận</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="tab-pane fade" id="order-tab-pane" role="tabpanel" aria-labelledby="order-tab" tabindex="1">
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">STT</th>
                                <th scope="col">Mã đơn hàng</th>
                                <th scope="col">Ngày đặt</th>
                                <th scope="col">Tổng tiền</th>
                                <th scope="col">Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="count" value="1"></c:set>
                            <c:forEach var="row" items="${ListOrder}">
                                <tr>
                                    <th scope="row">${count}</th>
                                    <td>${row.orderID}</td>
                                    <td>${row.date.dayOfMonth}/${row.date.monthValue}/${row.date.year}  
                                        ${row.date.hour}:${row.date.minute}:${row.date.second}</td>
                                    <td><fmt:formatNumber value="${row.totalPrice}" pattern="###,###,###"/></td>
                                    <td><a href="profile/view-order?id=${row.orderID}">Xem chi tiết</a></td>
                                </tr>
                            <c:set var="count" value="${count+1}"></c:set>    
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous">
    </script>
    <script>
        var popupButtonColor = document.getElementById("buttonChangePass");
        var popupColor = document.getElementById("popupChangePassword");
        var closeColor = document.getElementsByClassName("close-popup-change-pass")[0];

        popupButtonColor.addEventListener("click", function () {
            popupColor.style.display = "block";
        });

        closeColor.addEventListener("click", function () {
            popupColor.style.display = "none";
        });

        window.addEventListener("click", function (event) {
            if (event.target == popupColor) {
                popupColor.style.display = "none";
            }
        });
    </script>
    <script>
        const button = document.getElementById('btnChangePass');
        button.addEventListener('click', function(event) {
            const oldPass = document.getElementById('oldPass').value;
            const newPass = document.getElementById('newPass').value;
            const confirmPass = document.getElementById('confirmPass').value;

            fetch('profile/change-pass?old-pass='+oldPass+'&new-pass='+newPass+'&confirm-pass='+confirmPass, {
              method: 'POST',
              //body: JSON.stringify({ value: enteredValue })
            })
            .then(response => response.json())
            .then(data => {
                updateDataById('messageOldPass',data.MessageOldPassNotMatch);
                updateDataById('messageNotLong',data.MessageNotLong);
                updateDataById('messageNotMatch',data.MessageNotMatch);
                updateDataById('messageChangePass',data.MessageChangePass);
            })
            .catch(error => {
               console.error('Error:', error);
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
