<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin - Đăng nhập</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link href="css/admin.css" rel="stylesheet">
</head>

<body>
    <jsp:include page="../index/header-admin-page.jsp"></jsp:include>
    <main>
        <div class="container">
            <h1 class="text-center">Đăng nhập</h1>
            <form action="" method="post">
                <div class="vstack gap-2 col-md-5 mx-auto">
                    <div class="col">
                        <label for="username" class="form-label">Tài khoản</label>
                        <input type="text" name="username" class="form-control" id="username">
                    </div>
                    <div class="col">
                        <label for="password" class="form-label">Mật khẩu</label>
                        <input type="password" name="password" class="form-control" id="password">
                    </div>
                    <p class="message-error">${messageLogin}</p>
                    <a href="forgot">Quên mật khẩu?</a>
                    <button type="submit" class="btn btn-primary">Đăng nhập</button>
                </div>
            </form>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>

</html>
