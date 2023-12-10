
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Admin - Đăng nhập</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="../css/admin.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="../index/header-admin-page.jsp"></jsp:include>
        <main style="margin-top: 5%; margin-bottom: 5%">
        <h3 class="text-center">Quên mật khẩu</h3>
        <div class="container">
            <form method="post" >
            <div class="row g-3">
                    <div class="col-sm text-end">
                        <label for="colFormLabelSm" class="col-sm-1 col-form-label-sm">Email</label>
                    </div>
                    <div class="col-sm-4">
                        <input type="email" name="email" class="form-control form-control-sm txt-email" id="colFormLabelSm">
                    </div>
                    <div class="col-sm">
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                
            </div>
                </form>  
                <div class="row text-center">
                    <p>${message}</p>
                </div>    
          </div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
    </body>
</html>