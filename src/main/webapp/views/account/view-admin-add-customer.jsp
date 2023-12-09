
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
    <style>
        .title {
            font-size: 50px;
        }

        a {
            text-decoration: none;
        }

        td a {
            margin-left: 10px;
        }
    </style>
  </head>
  <body>
    <header>
        <div class="container">
            
            <div class="row align-items-center">
                <div class="col text-center title">
                  Quản lý khách hàng
                </div>
          </div>
          <div class="row justify-content-md-center menu">
            <div class="col col-lg-2">
              <a href="" >Danh sách khách hàng</a>
            </div>
            <div class="col col-lg-2">
                <a href="">Thêm khách hàng</a>
            </div>
          </div>
    </header>
    <main>
        <div class="container">
            <div class="row align-items-center">
                <div class="col text-center" style="font-size: 30px">
                    Chỉnh sửa thông tin khách hàng
                </div>
            </div>
            <form>
              <div class="input-group flex-nowrap">
                <span class="input-group-text" id="addon-wrapping">Tên khách hàng</span>
                <input type="text" class="form-control" placeholder="Tên khách hàng" aria-label="Name"
                  aria-describedby="addon-wrapping">
              </div>
              <div class="input-group flex-nowrap">
                <span class="input-group-text" id="addon-wrapping">Tài khoản</span>
                <input type="text" class="form-control" placeholder="Tên tài khoản" aria-label="Username"
                  aria-describedby="addon-wrapping">
              </div>
      
              <div class="input-group flex-nowrap">
                <span class="input-group-text" id="addon-wrapping">Mật khẩu</span>
                <input type="password" class="form-control" placeholder="Mật khẩu" aria-label="Password"
                  aria-describedby="addon-wrapping">
              </div>
                
              <div class="input-group flex-nowrap">
                <span class="input-group-text" id="addon-wrapping">Xác nhận mật khẩu</span>
                <input type="password" class="form-control" placeholder="Mật khẩu" aria-label="Password"
                  aria-describedby="addon-wrapping">
              </div>
                
              <div class="input-group flex-nowrap">
                <span class="input-group-text" id="addon-wrapping">Email</span>
                <input type="text" class="form-control" placeholder="Email" aria-label="Email"
                  aria-describedby="addon-wrapping">
              </div>
              <div class="input-group flex-nowrap">
                <span class="input-group-text" id="addon-wrapping">Địa chỉ</span>
                <input type="text" class="form-control" placeholder="Tên tài khoản" aria-label="Username"
                  aria-describedby="addon-wrapping">
              </div>
              <div class="input-group mb-3">
                <label class="input-group-text" for="inputGroupSelect01">Trạng thái</label>
                <select class="form-select" id="inputGroupSelect01">
                  <option selected>Chọn...</option>
                  <option value="1">One</option>
                </select>
              </div>
              <button class="btn btn-primary" type="submit">Lưu lại</button>
            </form>
        </div>
    </main>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
  </body>
</html>
