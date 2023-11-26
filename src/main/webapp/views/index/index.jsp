<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
</head>
<body>
    <div class="container">
        <jsp:include page="header.jsp">
            <jsp:param name="" value=""/>
        </jsp:include>
        <main>
            <div class="intro-slider-container mb-5">
                <div class="intro-slider owl-carousel owl-theme owl-nav-inside owl-light" data-toggle="owl">     
                    <c:forEach var="row"  items="${listSlider}">
                        <fmt:formatNumber value="${row.product.price}" pattern="###,###,###" var="formattedPrice" />
                        <jsp:include page="item-slider.jsp">
                            <jsp:param name="image" value="${row.image}"/>
                            <jsp:param name="subTitle" value="Hàng mới về"/>
                            <jsp:param name="titleTop" value="${row.product.name}"/>
                            <jsp:param name="titleBottom" value=""/>
                            <jsp:param name="oldPrice" value=""/>
                            <jsp:param name="newPrice" value="${formattedPrice}"/>
                            <jsp:param name="subNewPrice" value=""/>
                            <jsp:param name="linkCategory" value=""/>
                        </jsp:include>  
                    </c:forEach>     
                </div>
            </div><!-- End .intro-slider-container -->
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
                            <c:forEach var="row" items="${listNewestProduct}">
                                <fmt:formatNumber value="${row.price}" pattern="###,###,###" var="formattedPrice" />
                                <jsp:include page="item-product-2.jsp">
                                    <jsp:param name="image" value="${row.productColorList[0].images}" />
                                    <jsp:param name="linkDetail" value="" />
                                    <jsp:param name="title" value="${row.name}" />
                                    <jsp:param name="price" value="${formattedPrice}" />
                                    <jsp:param name="reviewCount" value="(4 Reviews)" />
                                    <jsp:param name="ratingCount" value="4.5" />
                                </jsp:include>
                            </c:forEach>
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
        <jsp:include page="footer.jsp">
            <jsp:param name="" value=""/>
        </jsp:include>
    </div>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>


