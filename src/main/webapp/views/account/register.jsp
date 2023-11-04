<%-- 
    Document   : register
    Created on : Nov 4, 2023, 7:15:39 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng ký</title>
        
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
        <main>
            <hr>
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
                            <label for="register-email">Tên*</label>
                            <input type="text" class="form-control" id="register-name" name="register-name" required>
                        </div><!-- End .form-group -->

                        <div class="form-group">
                            <label for="register-email">Tên đăng nhập *</label>
                            <input type="text" class="form-control" id="register-username" name="register-username" required>
                        </div><!-- End .form-group -->

                        <div class="form-group">
                            <label for="register-password"> Mật khẩu *</label>
                            <input type="password" class="form-control" id="register-password" name="register-password" required>
                        </div><!-- End .form-group -->
                        
                        <p class="message-error">${messageNotLong}</p>
                        
                        <div class="form-group">
                            <label for="register-password">Xác nhận mật khẩu *</label>
                            <input type="password" class="form-control" id="register-password-confirm" name="register-password-confirm" required>
                        </div><!-- End .form-group -->
                        
                        <p class="message-error">${messageNotMatch}</p>
                        
                        <div class="form-group">
                            <label for="register-password">Email *</label>
                            <input type="email" class="form-control" id="register-email" name="register-email" required>
                        </div><!-- End .form-group -->

                        <div class="form-group">
                            <label for="register-password">Address</label>
                            <input type="text" class="form-control" id="register-address" name="register-address">
                        </div><!-- End .form-group -->
                            
                        <div class="form-footer">
                            <button type="submit" class="btn btn-primary">Đăng ký</button>

                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="register-policy" required>
                                <label class="custom-control-label" for="register-policy">I agree to the <a href="#">privacy policy</a> *</label>
                            </div><!-- End .custom-checkbox -->
                        </div><!-- End .form-footer -->
                    </form>
               </div><!-- .End .tab-pane -->
            </div>
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
