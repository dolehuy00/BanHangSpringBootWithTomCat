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
    </style>
</head>

<body>
    <jsp:include page="../index/header-admin-page.jsp"/>
    <main>
        <div class="container">
            <div class="row align-items-center">
                <div class="col text-center title">Quản lý nhân viên</div>
            </div>
            <div class="row text-center">
                <div class="col">
                    <a href="">Danh sách nhân viên</a>
                </div>
            </div>
            <div class="row align-items-center">
                <div class="col text-center" style="font-size: 30px">
                    Thêm nhân viên
                </div>
            </div>
            <form>
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping">Tên</span>
                    <input type="text" class="form-control" name="name" value="${User.name}" aria-label="Name" aria-describedby="addon-wrapping" required>
                </div>
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping">Tài khoản</span>
                    <input type="text" class="form-control" name="username" value="${User.username}" aria-label="Username" aria-describedby="addon-wrapping" required>
                </div>
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping">Mật khẩu</span>
                    <input type="password" class="form-control" name="password" value="${User.username}" aria-label="Password" aria-describedby="addon-wrapping" required>
                </div>
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping">Xác nhân mật khẩu</span>
                    <input type="password" class="form-control" name="password-confirm" value="${User.username}" aria-label="PasswordConfirm" aria-describedby="addon-wrapping" required>
                </div>
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping">Email</span>
                    <input type="text" class="form-control" name="email" value="${User.email}" aria-label="Email" aria-describedby="addon-wrapping" required>
                </div>
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping">Số điện thoại</span>
                    <input type="number" class="form-control no-spinners" name="phone-number" value="${User.phoneNumber}" aria-label="PhoneNumber" aria-describedby="addon-wrapping" required>
                </div>
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping">Địa chỉ</span>
                    <input type="text" class="form-control" name="address" value="${User.address}" aria-label="Address" aria-describedby="addon-wrapping" required>
                </div>
                <div class="input-group mb-3">
                    <label class="input-group-text" for="inputGroupSelect01">Quyền</label>
                    <select class="form-select" id="inputGroupSelect01" name="role" required>
                        <c:forEach var="row" items="${ListRole}">
                            <option value="${row.roleID}">${row.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="input-group mb-3">
                  <label class="input-group-text" for="inputGroupSelect01">Trạng thái</label>
                  <select class="form-select" id="inputGroupSelect01" name="status" required>
                      <c:forEach var="row" items="${ListStatus}">
                          <option value="${row.statusID}">${row.name}</option>   
                      </c:forEach>
                  </select>
                </div>

                <button class="btn btn-primary" type="submit">Thêm nhân viên</button>
            </form>
        </div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>

</html>
