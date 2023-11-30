<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="header-middle ">
    <div class="container text-center ">
        <div class="row align-items-center">

          <div class="col header-middle-left">
              <a href="/banhang"><img src="/banhang/images/logo.png" alt="logo"></a>
          </div>

          <div class="col header-middle-middle">
            <form action="/banhang/search-product" method="get">
                <div class="search-bar">
                    <input type="text" name="keyword" placeholder="Tìm kiếm sản phẩm ...">
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
</div>
<hr>
