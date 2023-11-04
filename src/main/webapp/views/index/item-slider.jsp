<%-- 
    Document   : item-slider
    Created on : Nov 4, 2023, 7:48:11 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="intro-slide" style="background-image: url(${param.image});">
    <div class="container intro-content">
        <div class="row justify-content-end">
            <div class="col-auto col-sm-7 col-md-6 col-lg-5">
                <h3 class="intro-subtitle text-third">${param.subTitle}</h3><!-- End .h3 intro-subtitle -->
                <h1 class="intro-title">${param.titleTop}</h1>
                <h1 class="intro-title">${param.titleBottom}</h1><!-- End .intro-title -->
                <div class="intro-price">
                    <sup class="intro-old-price">${param.oldPrice}</sup>
                    <span class="text-third">
                        ${param.newPrice}<sup>${param.subNewPrice}</sup>
                    </span>
                </div><!-- End .intro-price -->
                <a href="${param.linkCategory}" class="btn btn-primary btn-round">
                    <span>Mua ngay</span>
                    <i class="icon-long-arrow-right"></i>
                </a>
            </div><!-- End .col-lg-11 offset-lg-1 -->
        </div><!-- End .row -->
    </div><!-- End .intro-content -->
</div><!-- End .intro-slide -->
