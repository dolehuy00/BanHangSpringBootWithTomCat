
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng ký</title>
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
                            <a class="nav-link" id="register-tab" href="/banhang/login" role="tab" aria-controls="register" aria-selected="true">Đăng nhập</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" id="signin-tab" href="/banhang/register" role="tab" aria-controls="signin" aria-selected="false">Đăng ký</a>
                        </li> 
                    </ul>
                    <form action="register" method="post">
                        <div class="form-group">
                            <label for="register-email">Tên <span style="color: red">*</span></label>
                            <input type="text" class="form-control" id="register-name" name="register-name" value="${name}" required>
                        </div><!-- End .form-group -->
                        <br>
                        <div class="form-group">
                            <label for="register-email">Tên đăng nhập <span style="color: red">*</span></label>
                            <input type="text" class="form-control" id="register-username" name="register-username" value="${username}" required>
                        </div><!-- End .form-group -->
                        
                        <p class="message-error">${messageUsedUsername}</p>
                        
                        <div class="form-group">
                            <label for="register-password"> Mật khẩu <span style="color: red">*</span></label>
                            <input type="password" class="form-control" id="register-password" name="register-password" value="${password}" required>
                        </div><!-- End .form-group -->
                        
                        <p class="message-error">${messageNotLong}</p>
                        
                        <div class="form-group">
                            <label for="register-password">Xác nhận mật khẩu <span style="color: red">*</span></label>
                            <input type="password" class="form-control" id="register-password-confirm" name="register-password-confirm" value="${confirm}" required>
                        </div><!-- End .form-group -->
                        
                        <p class="message-error">${messageNotMatch}</p>
                        
                        <div class="form-group">
                            <label for="register-password">Email <span style="color: red">*</span></label>
                            <input type="email" class="form-control" id="register-email" name="register-email" value="${email}" required>
                        </div><!-- End .form-group -->
                        
                        <p class="message-error">${messageUsedEmail}</p>
                        
                        <div class="form-group">
                            <label for="register-password">Address</label>
                            <input type="text" class="form-control" id="register-address" name="register-address" value="${address}">
                        </div><!-- End .form-group -->
                        <br>    
                        <div class="form-footer">
                            <button type="submit" class="btn btn-primary">Đăng ký</button>

                        </div><!-- End .form-footer -->
                    </form>
               </div><!-- .End .tab-pane -->
            </div>
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
