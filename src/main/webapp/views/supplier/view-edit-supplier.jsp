
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
  <link href="qlncc.css" rel="stylesheet">
</head>
  <body>
    <header>
        <div class="container">
            
            <div class="row align-items-center">
                <div class="col col-lg-2"></div>
                <div class="col text-center title">
                  Quản lý nhà cung cấp
                </div>
                <div class="col col-lg-2 text-center">
                    <a class="login" href="#">Đăng nhập</a>
                </div>
          </div>
          <div class="row justify-content-md-center menu">
            <div class="col col-lg-2">
              <a href="" >Danh sách nhà cung cấp</a>
            </div>
            <div class="col col-lg-2">
                <a href="" >Thêm nhà cung cấp</a>
            </div>
          </div>
    </header>
    <main>
        <div class="container">
            <div class="row align-items-center">
                <div class="col text-center" style="font-size: 30px">
                    Chỉnh sửa nhà cung cấp
                </div>
            </div>
            <form>
                <div class="mb-3">
                    <input type="text" class="form-control" id="productName" placeholder="Tên nhà cung cấp" />
                </div>
                <button class="btn btn-primary" type="submit">Lưu lại</button>
            </form>
        </div>
    </main>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
  </body>
</html>
