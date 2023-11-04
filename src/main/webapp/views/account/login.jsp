<%-- 
    Document   : login
    Created on : Nov 4, 2023, 7:15:23 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
        
        <!-- Plugins CSS File -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="css/owl.carousel.css">
        <!-- Main CSS File -->
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/login-register.css"> 
    </head>
    <body> 
        <jsp:include page="../index/header.jsp">
            <jsp:param name="" value=""/>
        </jsp:include>
        <hr>
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
                                <div class="form-group">
                                    <label for="singin-email">Tên đăng nhập *</label>
                                    <input type="text" class="form-control" id="singin-username" name="singin-username" required>
                                </div><!-- End .form-group -->

                                <div class="form-group">
                                    <label for="singin-password">Mật khẩu *</label>
                                    <input type="password" class="form-control" id="singin-password" name="singin-password" required>
                                </div><!-- End .form-group -->

                                <div class="form-footer">
                                    <p class="message-error">${messageLogin}</p>
                                    <button type="submit" class="btn btn-primary">Đăng nhập</button>

                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="signin-remember">
                                        <label class="custom-control-label" for="signin-remember">Ghi nhớ</label>
                                    </div><!-- End .custom-checkbox -->

                                    <a href="#" class="forgot-link">Quên mật khẩu?</a>
                                </div><!-- End .form-footer -->
                            </form>
                        </div><!-- .End .tab-pane -->
                    </div><!-- End .tab-content -->
                </div><!-- End .form-tab -->
            </div><!-- End .form-box -->
        </main>
        <hr>                            
        <jsp:include page="../index/footer.jsp">
            <jsp:param name="" value=""/>
        </jsp:include>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
