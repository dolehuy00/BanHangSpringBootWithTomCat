
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Quản lý màu của sản phẩm</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="../../css/admin.css" rel="stylesheet">
        <style>
            .title {
                font-size: 50px;
            }
            .btn-danger{
                margin-left: 10px
            }
            .input-group, .form-floating{
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="../index/header-admin-page.jsp"/>
        <main>
            <div class="container">
                <div class="row align-items-center">
                    <div class="col text-center title">Quản lý màu của sản phẩm</div>
                </div>
                <div class="row justify-content-md-center menu">
                    <div class="col text-center">
                        <a href="view">Danh sách màu của sản phẩm</a>
                    </div>
                </div>
                <div class="row align-items-center">
                    <div class="col text-center" style="font-size: 30px">
                        Thêm màu của sản phẩm
                    </div>
                </div>
                <form action="" method="post">
                    <div class="input-group flex-nowrap">
                        <span class="input-group-text" id="addon-wrapping">Tên</span>
                        <input type="text" class="form-control" id="name" name="name" value="${Color.name}" aria-describedby="addon-wrapping" required>
                    </div>
                    <p>${message}</p>
                    <button class="btn btn-primary" type="submit">Lưu lại</button>
                </form>
            </div>
        </main>
        <jsp:include page="../index/footer-admin-page.jsp"/> 
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
</html>
