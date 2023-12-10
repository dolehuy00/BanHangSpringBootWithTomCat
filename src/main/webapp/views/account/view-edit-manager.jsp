
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Quản lý nhân viên</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
    <link href="../../../css/admin.css" rel="stylesheet">
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

        .input-group {
            padding-top: 20px;
        }

        td a {
            margin-left: 10px;
        }
        .no-spinners::-webkit-inner-spin-button,
        .no-spinners::-webkit-outer-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }
        .error-message{
            color: red;
            margin: 0;
        }
    </style>
</head>

<body>
    <jsp:include page="../index/header-admin-page.jsp"/>
    <main>
        <div class="container">
            <div class="row align-items-center">
                <div class="col text-center title">Quản lý nhân viên</div>
            </div>
            <div class="row justify-content-md-center menu">
                <div class="col col-lg-2">
                    <a href="../../manager-management">Danh sách nhân viên</a>
                </div>
                <div class="col col-lg-2">
                    <a href="../add">Thêm nhân viên</a>
                </div>
            </div>
            <div class="row align-items-center">
                <div class="col text-center" style="font-size: 30px">
                    Sửa thông tin nhân viên
                </div>
            </div>
            <form id="edit-form">
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping">Tên</span>
                    <input type="text" class="form-control" id="name" value="${User.name}" aria-label="Name" aria-describedby="addon-wrapping" required>
                </div>
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping">Tài khoản</span>
                    <span class="input-group-text" id="username">${User.username}</span>
                </div>
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping">Email</span>
                    <input type="text" class="form-control" id="email" value="${User.email}" aria-label="Email" aria-describedby="addon-wrapping" required>
                </div>
                <p class="error-message" id="error-email"></p>
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping">Số điện thoại</span>
                    <input type="number" class="form-control no-spinners" id="phone-number" value="${User.phoneNumber}" aria-label="PhoneNumber" aria-describedby="addon-wrapping" required>
                </div>
                <p class="error-message" id="error-phone-number"></p>
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping">Địa chỉ</span>
                    <input type="text" class="form-control" id="address" value="${User.address}" aria-label="Address" aria-describedby="addon-wrapping" required>
                </div>
                <div class="input-group mb-3">
                    <label class="input-group-text" for="inputGroupSelect01">Quyền</label>
                    <select class="form-select" id="role">
                        <c:forEach var="row" items="${ListRole}">
                            <c:choose>
                                <c:when test="${row.roleID==User.role.roleID}">
                                    <option value="${row.roleID}" selected>${row.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${row.roleID}">${row.name}</option>
                                </c:otherwise>
                            </c:choose> 
                        </c:forEach>
                    </select>
                </div>
                <p class="error-message" id="error-role"></p>
                <div class="input-group mb-3">
                  <label class="input-group-text" for="inputGroupSelect01">Trạng thái</label>
                  <select class="form-select" id="status">
                      <c:forEach var="row" items="${ListStatus}">
                          <c:choose>
                              <c:when test="${row.statusID==User.status.statusID}">
                                  <option value="${row.statusID}" selected>${row.name}</option>
                              </c:when>
                              <c:otherwise>
                                  <option value="${row.statusID}">${row.name}</option>
                              </c:otherwise>
                          </c:choose>     
                      </c:forEach>
                  </select>
                </div>
                <p class="error-message" id="error-status"></p>
                <button class="btn btn-primary" type="submit">Lưu lại</button>
            </form>
        </div>
    </main>
    <jsp:include page="../index/footer-admin-page.jsp"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous">  
    </script>
    <script>
        document.getElementById("edit-form").addEventListener("submit", function(event) {
            event.preventDefault(); 
            const name = document.getElementById('name').value;
            const email = document.getElementById('email').value;
            const phoneNumber = document.getElementById('phone-number').value;
            const address = document.getElementById('address').value;
            const role = document.getElementById('role').value;
            const status = document.getElementById('status').value;
 
            var account = {
                name: name,
                username: '',
                password: '',
                passwordConfirm: '',
                email: email,
                phoneNumber: phoneNumber,
                address: address,
                role: role,
                status: status
            };
            
            fetch('', {
                method: 'POST',
                headers: {
                  "Content-Type": 'application/json',
                },
                body: JSON.stringify(account),
            })
            .then(response => response.json())
            .then(data => {
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
                if(data.ErrorRole){
                    updateDataById('error-role', 'Vui lòng chọn quyền tài khoản!');
                }else{
                    updateDataById('error-role', '');
                }
                if(data.Success){
                    alert('Chỉnh sửa nhân viên thành công');
                    location.reload(true);
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
