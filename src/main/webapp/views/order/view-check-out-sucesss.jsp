
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Đặt hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="css/style.css">
    <style>
        a {
            text-decoration: none;
        }
        main {
            margin: 40px 40px;
        }
        h3{
            margin-bottom: 20px;
        }
        .link-go-home{
            color: white;
        }
        .link-go-home:hover{
            color: black;
        }
    </style>
</head>

<body>
    <header>
        <jsp:include page="../index/header-top.jsp"></jsp:include>
        <jsp:include page="../index/header-middle.jsp"></jsp:include>
    </header>
    <main>
        <div class="container text-center">
            <h3 class="text-center">Đặt hàng thành công, đơn hàng đang được xử lý!</h3>
            <button type="button" class="btn btn-primary"><a class="link-go-home" href="/banhang">Về trang chủ</a></button>
        </div>
    </main>
    <jsp:include page="../index/footer.jsp"></jsp:include>  
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>   
</body>
</html>
