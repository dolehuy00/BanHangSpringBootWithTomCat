
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Quản lý nhà cung cấp</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link href="../../css/admin.css" rel="stylesheet">
    <style>
        main {
            margin-top: 30px;
        }
        
        .title {
            font-size: 50px;
        }

        .input-group {
            margin-top: 10px;
            margin-bottom: 10px;
        }
        
        .no-spinners::-webkit-inner-spin-button,
        .no-spinners::-webkit-outer-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }
        .error-message{
            color: red;
        }
    </style>
  </head>
  <body>
    <jsp:include page="../index/header-admin-page.jsp"/>
    <main>
        <div class="container">
            <div class="row align-items-center">
                <div class="col text-center title">Quản lý khách hàng</div>
            </div>
            <div class="row text-center">
                <div class="col">
                    <a href="view">Danh sách khách hàng</a>
                </div>
            </div>
            <div class="row align-items-center">
                <div class="col text-center" style="font-size: 30px">
                    Thêm khách hàng
                </div>
            </div>
            <form id="add-form">
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping">Tên</span>
                    <input type="text" class="form-control" id="name" value="${Customer.name}" aria-label="Name" aria-describedby="addon-wrapping" required>
                </div>
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping">Tài khoản</span>
                    <input type="text" class="form-control" id="username" value="${Customer.username}" aria-label="Username" aria-describedby="addon-wrapping" required>
                </div>
                <p class="error-message" id="error-username"></p>
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping">Mật khẩu</span>
                    <input type="password" class="form-control" id="password" aria-label="Password" aria-describedby="addon-wrapping" required>
                </div>
                <p class="error-message" id="error-password"></p>
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping">Xác nhân mật khẩu</span>
                    <input type="password" class="form-control" id="password-confirm" aria-label="PasswordConfirm" aria-describedby="addon-wrapping" required>
                </div>
                <p class="error-message" id="error-password-confirm"></p>
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping">Email</span>
                    <input type="text" class="form-control" id="email" value="${Customer.email}" aria-label="Email" aria-describedby="addon-wrapping" required>
                </div>
                <p class="error-message" id="error-email"></p>
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping">Số điện thoại</span>
                    <input type="number" class="form-control no-spinners" id="phone-number" value="${Customer.phoneNumber}" aria-label="PhoneNumber" aria-describedby="addon-wrapping" required>
                </div>
                <p class="error-message" id="error-phone-number"></p>
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping">Địa chỉ</span>
                    <input type="text" class="form-control" id="address" value="${Customer.address}" aria-label="Address" aria-describedby="addon-wrapping" required>
                </div>
                <div class="input-group mb-3">
                  <label class="input-group-text" for="inputGroupSelect01">Trạng thái</label>
                  <select class="form-select" id="status" required>
                      <option value="" selected>Chọn...</option>
                      <c:forEach var="row" items="${ListStatus}">
                          <option value="${row.statusID}">${row.name}</option>   
                      </c:forEach>
                  </select>
                </div>
                <p class="error-message" id="error-status"></p>
                <button class="btn btn-primary" type="submit">Thêm khách hàng</button>
            </form>
        </div>   
    </main>
    <jsp:include page="../index/footer-admin-page.jsp"/>            
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"> 
    </script>
    <script>
        document.getElementById("add-form").addEventListener("submit", function(event) {
            event.preventDefault(); 
            const name = document.getElementById('name').value;
            const username = document.getElementById('username').value;
            const password = document.getElementById('password').value;
            const passwordConfirm = document.getElementById('password-confirm').value;
            const email = document.getElementById('email').value;
            const phoneNumber = document.getElementById('phone-number').value;
            const address = document.getElementById('address').value;
            const status = document.getElementById('status').value;
 
            var account = {
                name: name,
                username: username,
                password: password,
                passwordConfirm: passwordConfirm,
                email: email,
                phoneNumber: phoneNumber,
                address: address,
                status: status
            };
            
            fetch('add', {
                method: 'POST',
                headers: {
                  "Content-Type": 'application/json',
                },
                body: JSON.stringify(account),
            })
            .then(response => response.json())
            .then(data => {
                if(data.ExitsUsername){
                    updateDataById('error-username', 'Tên tài khoản đã tồn tại!');
                }else{
                    updateDataById('error-username', '');
                }
                if(data.PasswordNotLong){
                    updateDataById('error-password', 'Mật khẩu phải từ 6 ký tự!');
                }else{
                    updateDataById('error-password', '');
                }
                if(data.PasswordNotEquals){
                    updateDataById('error-password-confirm', 'Hai mật khẩu không khớp nhau!');
                }else{
                    updateDataById('error-password-confirm', '');
                }
                if(data.ExitsEmail){
                    updateDataById('error-email', 'Email đã được sử dụng!');
                }else{
                    updateDataById('error-email', '');
                }
                if(data.ExitsPhoneNumber){
                    updateDataById('error-phone-number', 'Số điện thoại đã được sử dụng!');
                }else{
                    updateDataById('error-phone-number', '');
                }
                if(data.ErrorStatus){
                    updateDataById('error-status', 'Vui lòng chọn trạng thái tài khoản!');
                }else{
                    updateDataById('error-status', '');
                }
                if(data.Success){
                    alert('Thêm khách hàng thành công');
                    window.location.replace("add");
                }
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
