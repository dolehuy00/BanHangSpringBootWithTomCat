<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/login-register.css"> 
    </head>
    <body> 
        <header>
            <jsp:include page="../index/header-top.jsp"></jsp:include>
            <jsp:include page="../index/header-middle.jsp"></jsp:include>
        </header>
        <main>
            <div class="container container-login">
                <div class="form-tab">
                    <ul class="nav nav-pills nav-fill" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="signin-tab" href="/banhang/login" role="tab" aria-controls="signin" aria-selected="true">Đăng nhập</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="register-tab" href="/banhang/register" role="tab" aria-controls="register">Đăng ký</a>
                        </li>
                    </ul>
                    <div class="tab-content" id="tab-content-5">
                        <div class="tab-pane fade show active" id="signin" role="tabpanel" aria-labelledby="signin-tab">
                            <form action="login" method="POST">
                                <br>
                                <div class="form-group">
                                    <label for="singin-email">Tên đăng nhập <span style="color: red">*</span></label>
                                    <input type="text" class="form-control" id="singin-username" name="singin-username" required>
                                </div><!-- End .form-group -->
                                <br>
                                <div class="form-group">
                                    <label for="singin-password">Mật khẩu <span style="color: red">*</span></label>
                                    <input type="password" class="form-control" id="singin-password" name="singin-password" required>
                                </div><!-- End .form-group -->

                                <div class="form-footer"> 
                                    <br>
                                    <a href="/banhang/forgot" class="forgot-link">Quên mật khẩu?</a>
                                    <br>
                                    <p class="message-error">${messageLogin}</p>
                                    <button type="submit" class="btn btn-primary">Đăng nhập</button>
                                </div><!-- End .form-footer -->
                            </form>
                        </div><!-- .End .tab-pane -->
                    </div><!-- End .tab-content -->
                </div><!-- End .form-tab -->
            </div><!-- End .form-box -->
        </main>
        <hr>                            
        <jsp:include page="../index/footer.jsp"></jsp:include>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
