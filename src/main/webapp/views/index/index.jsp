<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Huy Do</title>
    
    <!-- Plugins CSS File -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <!-- Main CSS File -->
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
    <div class="container">
        <header>
            <div class="header-top">
                <div class="row align-items-start">
                    <div class="col">
                      <a href="#" class="icon-phone">
                        <i class="fas fa-phone" ></i>
                        Call: +84 12345678</a>
                    </div>
                    <div class="col text-end">
                      <a href="#">Đăng nhập/Đăng ký</a> 
                    </div>
                  </div>
            </div><!--end header-top-->
            <hr>
            <div class="header-middle ">
                <div class="container text-center ">
                    <div class="row align-items-center">

                      <div class="col header-middle-left">
                        <img src="images/logo.png" alt="logo">
                      </div>

                      <div class="col header-middle-middle">
                        <form acction="" method="get">
                            <div class="search-bar">
                                <input type="text" placeholder="Tìm kiếm sản phẩm ...">
                                <button class="btn" type="submit">
                                    <i class="fas fa-search"></i>
                                </button>
                            </div>
                        </form>
                      </div>

                      <div class="col header-middle-right">  
                        <a href="#">
                            <i class="fa-regular fa-heart">
                                <span class="badge wishlist-count">3</span>
                            </i>
                        </a> 
                        <a href="#">
                            <i class="fas fa-shopping-cart">
                                <span class="badge wishlist-count">3</span>
                            </i>
                        </a>     
                      </div>
                    </div>
                </div>
            </div><!--end header-middle-->
            <div class="header-bottom">
                <div class="container text-center">
                    <div class="row align-items-center">
                        <div class="col text-start dropdown menu-category">
                            <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown">
                            Danh mục sản phẩm
                            </button>
                            <ul class="dropdown-menu ">
                            <li><a class="dropdown-item" href="#">Link 1</a></li>
                            <li><a class="dropdown-item" href="#">Link 2</a></li>
                            <li><a class="dropdown-item" href="#">Link 3</a></li>
                            </ul>
                        </div>
                        <div class="col menu-page">
                            <ul class="menu-page sf-arrows">
                                <li>
                                    <a href="index.html">Home</a>
                                </li>
                                <li>
                                    <a href="index.html">About Us</a>
                                </li>
                                <li>
                                    <a href="index.html">Contact</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                  </div>
            </div><!--end header-bottom-->
        </header>
        <main>
            <div class="intro-slider-container mb-5">
                <div class="intro-slider owl-carousel owl-theme owl-nav-inside owl-light" data-toggle="owl">     
                <jsp:include page="item-slider.jsp">
                    <jsp:param name="image" value="images/slide-1.png"/>
                    <jsp:param name="subTitle" value="Không khuyến mãi"/>
                    <jsp:param name="titleTop" value="Laptop MSI"/>
                    <jsp:param name="titleBottom" value="Katana"/>
                    <jsp:param name="oldPrice" value=""/>
                    <jsp:param name="newPrice" value="20.000.000"/>
                    <jsp:param name="subNewPrice" value=""/>
                    <jsp:param name="linkCategory" value=""/>
                </jsp:include>         
                </div>
            </div><!-- End .intro-slider-container -->
            <div class="container">
                <h2 class="title text-center mb-4">Danh mục loại sản phẩm</h2>
                <div class="cat-blocks-container">
                    <div class="row">
                        
                        <jsp:include page="item-category.jsp">
                            <jsp:param name="image" value="images/1.png"/>
                            <jsp:param name="title" value="Laptop ABC"/>
                            <jsp:param name="link" value=""/>
                        </jsp:include>   
                        
                    </div><!-- End .row -->
                </div><!-- End .cat-blocks-container -->
            </div> <!--end container danh mục loại sản phẩm-->
            <div class="container">
                <h2 class="title text-center mb-4">Gợi ý dành cho bạn</h2>
                <div class="tab-content tab-content-carousel just-action-icons-sm">
                    <div class="tab-pane p-0 fade show active" id="new-all-tab" role="tabpanel" aria-labelledby="new-all-link">
                        <div class="owl-carousel owl-full carousel-equal-height carousel-with-shadow" data-toggle="owl" 
                            data-owl-options='{
                                "nav": true, 
                                "dots": true,
                                "margin": 20,
                                "loop": false,
                                "responsive": {
                                    "0": {
                                        "items":2
                                    },
                                    "480": {
                                        "items":2
                                    },
                                    "768": {
                                        "items":3
                                    },
                                    "992": {
                                        "items":4
                                    },
                                    "1200": {
                                        "items":5
                                    }
                                }
                            }'>
                            <jsp:include page="item-product-2.jsp">
                                <jsp:param name="image" value="images/product-1.jpg" />
                                <jsp:param name="linkDetail" value="" />
                                <jsp:param name="category" value="Laptops" />
                                <jsp:param name="linkCategory" value="" />
                                <jsp:param name="title" value="MacBook Pro 13inch Display, i5" />
                                <jsp:param name="price" value="$1,199.99" />
                                <jsp:param name="reviewCount" value="(4 Reviews)" />
                                <jsp:param name="ratingCount" value="4.5" />
                            </jsp:include>
                        </div><!-- End .owl-carousel -->
                    </div><!-- .End .tab-pane -->  
                </div><!-- End .tab-content -->
            </div><!-- End .container gợi ý dành cho bạn-->


            <div class="container">
                <h2 class="title text-center mb-4">Sản phẩm mới</h2>
                <div class="tab-content tab-content-carousel just-action-icons-sm">
                    <div class="tab-pane p-0 fade show active" id="new-all-tab" role="tabpanel" aria-labelledby="new-all-link">
                        <div class="owl-carousel owl-full carousel-equal-height carousel-with-shadow" data-toggle="owl" 
                            data-owl-options='{
                                "nav": true, 
                                "dots": true,
                                "margin": 20,
                                "loop": false,
                                "responsive": {
                                    "0": {
                                        "items":2
                                    },
                                    "480": {
                                        "items":2
                                    },
                                    "768": {
                                        "items":3
                                    },
                                    "992": {
                                        "items":4
                                    },
                                    "1200": {
                                        "items":5
                                    }
                                }
                            }'>



                           



                        </div><!-- End .owl-carousel -->
                    </div><!-- .End .tab-pane -->  
                </div><!-- End .tab-content -->
            </div><!-- End .container sản phẩm mới-->
            <div class="container">
                <h2 class="title text-center mb-4">Sản phẩm khác</h2>
                <div class="tab-content tab-content-carousel just-action-icons-sm">
                    <div class="tab-pane p-0 fade show active" id="new-all-tab" role="tabpanel" aria-labelledby="new-all-link">
                        <div class="owl-carousel owl-full carousel-equal-height carousel-with-shadow" data-toggle="owl" 
                            data-owl-options='{
                                "nav": true, 
                                "dots": true,
                                "margin": 20,
                                "loop": false,
                                "responsive": {
                                    "0": {
                                        "items":2
                                    },
                                    "480": {
                                        "items":2
                                    },
                                    "768": {
                                        "items":3
                                    },
                                    "992": {
                                        "items":4
                                    },
                                    "1200": {
                                        "items":5
                                    }
                                }
                            }'>





                        </div><!-- End .owl-carousel -->
                    </div><!-- .End .tab-pane -->  
                </div><!-- End .tab-content -->
            </div><!-- End .container sản phẩm khác-->

        </main>
        <footer class="footer">
            <div class="footer-middle">
	            <div class="container">
	            	<div class="row">
	            		<div class="col-sm-6 col-lg-3">
	            			<div class="widget widget-about">
	            				<img src="images/logo.png" class="footer-logo" alt="Footer Logo" width="105" height="25">
	            				<p>Cảm ơn quý khách hàng đã ủng hộ sản phẩm của chúng tôi.</p>

	            				<div class="widget-call">
                                    <i class="fas fa-phone"></i>
                                    Hotline
                                    <a href="tel:#">+0123 456 789</a>         
                                </div><!-- End .widget-call -->
	            			</div><!-- End .widget about-widget -->
	            		</div><!-- End .col-sm-6 col-lg-3 -->

	            		<div class="col-sm-6 col-lg-3">
	            			<div class="widget">
	            				<h4 class="widget-title">Thông tin về chúng tôi</h4><!-- End .widget-title -->

	            				<ul class="widget-list">
	            					<li><a href="about.html">Về chúng tôi</a></li>
	            					<li><a href="contact.html">Liên hệ với chúng tôi</a></li>
	            				</ul><!-- End .widget-list -->
	            			</div><!-- End .widget -->
	            		</div><!-- End .col-sm-6 col-lg-3 -->

	            		<div class="col-sm-6 col-lg-3">
	            			<div class="widget">
	            				<h4 class="widget-title">Chăm sóc khách hàng</h4><!-- End .widget-title -->

	            				<ul class="widget-list">
	            					<li><a href="#">Phương thức thanh toán</a></li>
	            				</ul><!-- End .widget-list -->
	            			</div><!-- End .widget -->
	            		</div><!-- End .col-sm-6 col-lg-3 -->

	            		<div class="col-sm-6 col-lg-3">
	            			<div class="widget">
	            				<h4 class="widget-title">Tài khoản của bạn</h4><!-- End .widget-title -->

	            				<ul class="widget-list">
	            					<li><a href="#">Đăng ký</a></li>
	            					<li><a href="#">Giỏ hàng</a></li>
	            					<li><a href="#">Danh sách yêu thích</a></li>
	            					<li><a href="#">Trợ giúp</a></li>
	            				</ul><!-- End .widget-list -->
	            			</div><!-- End .widget -->
	            		</div><!-- End .col-sm-6 col-lg-3 -->
	            	</div><!-- End .row -->
	            </div><!-- End .container -->
	        </div><!-- End .footer-middle -->
        </footer>
    </div>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/index.js"></script>
</body>
</html>


