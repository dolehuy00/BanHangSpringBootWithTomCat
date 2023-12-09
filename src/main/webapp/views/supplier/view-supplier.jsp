
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Quản lý nhà cung cấp</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        .container {
            background-color: antiquewhite;
        }

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

        .menu {
            padding-top: 20px;
            padding-bottom: 20px;
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
                    <a href="">Danh sách nhà cung cấp</a>
                </div>
                <div class="col col-lg-2">
                    <a href="">Thêm nhà cung cấp</a>
                </div>
            </div>
    </header>
    <main>
        <div class="container">
            <div class="row align-items-center">
                <div class="col text-center search">
                    <form action="" method="">
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="inputGroup-sizing-default">Tìm kiếm</span>
                            <input type="text" name="search" class="form-control" aria-label="Sizing example input"
                                aria-describedby="inputGroup-sizing-default">
                            <button type="">Tìm</button>
                        </div>
                </div>
                </form>
            </div>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Mã</th>
                        <th scope="col">Tên nhà cung cấp</th>
                        <th scope="col">Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>Mark</td>
                        <td>Otto</td>
                        <td>
                            <a href="">Sửa</a>
                            <a href="">Xóa</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>

</html>
