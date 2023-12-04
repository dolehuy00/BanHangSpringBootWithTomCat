<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <jsp:include page="header-top.jsp"></jsp:include>
            <jsp:include page="header-middle.jsp"></jsp:include>
            <jsp:include page="header-bottom.jsp"></jsp:include>
        </header>
        <main>
            <div class="intro-slider-container mb-5">
                <div class="intro-slider owl-carousel owl-theme owl-nav-inside owl-light" data-toggle="owl">     
                    <c:forEach var="row"  items="${listSlider}">
                        <fmt:formatNumber value="${row.product.price}" pattern="###,###,###" var="formattedPrice" />
                        <jsp:include page="item-slider.jsp">
                            <jsp:param name="image" value="${row.image}"/>
                            <jsp:param name="subTitle" value="${row.title}"/>
                            <jsp:param name="titleTop" value="${row.product.name}"/>
                            <jsp:param name="newPrice" value="${formattedPrice}"/>
                            <jsp:param name="link" value=""/>
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
                                "dots":false,
                                "margin": 10,
                                "loop": false,
                                "responsive": {
                                    "0": {
                                        "items":1
                                    },
                                    "270": {
                                        "items":2
                                    },
                                    "790": {
                                        "items":3
                                    },
                                    "1060": {
                                        "items":4
                                    }
                                }
                            }'>
                            <c:forEach var="row" items="${listRecommend}">
                                <fmt:formatNumber value="${row.price}" pattern="###,###,###" var="formattedPrice" />
                                <jsp:include page="../product/item-product.jsp">
                                    <jsp:param name="image" value="${row.productColorList[0].images}" />
                                    <jsp:param name="linkDetail" value="product/${row.productID}" />
                                    <jsp:param name="title" value="${row.name}" />
                                    <jsp:param name="price" value="${formattedPrice}" />
                                    <jsp:param name="reviewCount" value="(4 Reviews)" />
                                    <jsp:param name="ratingCount" value="4.5" />
                                </jsp:include>
                            </c:forEach> 
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
                                "dots": false,
                                "margin": 20,
                                "loop": false,
                                "responsive": {
                                    "0": {
                                        "items":1
                                    },
                                    "480": {
                                        "items":2
                                    },
                                    "768": {
                                        "items":3
                                    },
                                    "992": {
                                        "items":4
                                    }
                                }
                            }'>
                            <c:forEach var="row" items="${listNewestProduct}">
                                <fmt:formatNumber value="${row.price}" pattern="###,###,###" var="formattedPrice" />
                                <jsp:include page="../product/item-product.jsp">
                                    <jsp:param name="image" value="${row.productColorList[0].images}" />
                                    <jsp:param name="linkDetail" value="product/${row.productID}" />
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
        </main>
        <jsp:include page="footer.jsp">
            <jsp:param name="" value=""/>
        </jsp:include>
    </div>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>


