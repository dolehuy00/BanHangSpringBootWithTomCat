
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Tìm kiếm sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
    <link href="https://cdn.jsdelivr.net/npm/nouislider/distribute/nouislider.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        #input-number-lower,
        #input-number-upper {
            padding: 7px;
            margin: 5px 5px;
            text-align: center; 
        }
        main{
            margin-top: 30px;
            margin-bottom: 30px;
        }
        .aside-title{
            margin-top: 20px
        }
        .lable-input-upper, .lable-input-lower{
            color: black;
            margin-top: 5px;
        }
        #btnFilterPrice{
            margin-top: 10px;
            margin-left: 30px;
            width: 100px;
        }
    </style>

</head>

<body>
    <header>
        <jsp:include page="../index/header-top.jsp"></jsp:include>
        <jsp:include page="../index/header-middle.jsp"></jsp:include>
    </header>
    <main>
        <div class="container">
            <div class="row"> 
               
                <div id="aside" class="col-md-2">
                    <form method="get">
                        <h3>Tong ${CountProduct}</h3>
                        <h3>Trang ${CountPage}</h3>
                    <h3 class="aside-title">Nhà cung cấp</h3>
                    <c:forEach var="row" items="${ListSupplier}">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" name="suppliers" value="${row.supplierID}" id="flexCheckDefault">
                            <label class="form-check-label" for="flexCheckDefault">
                                ${row.name}
                            </label>
                        </div>
                    </c:forEach>
                    <h3 class="aside-title">Giá</h3>
                    <div class="text-center">
                        <div id="slider"></div>
                        <label class="lable-input-lower" for="input-number-lower">Từ</label>
                        <input id="input-number-lower" name="lower"/>
                        <label class="lable-input-upper" for="input-number-upper">Đến</label>
                        <input id="input-number-upper" name="upper"/> 
                    </div>
                    
                    
                    <h3 class="aside-title">Màu</h3>
                    <c:forEach var="row" items="${ListColor}">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" name="colors" value="${row.colorID}" id="flexCheckDefault">
                            <label class="form-check-label" for="flexCheckDefault">
                                ${row.name}
                            </label>
                        </div>
                    </c:forEach>  
                    <button class="btn btn-success" type="button" id="btnFilterPrice">Lọc</button>
                    </form>
                </div>   
            
                <div id="store" class="col-md-10">
                    <div class="store-filter text-end">
                        <div class="store-sort">
                            <label>
                                Sắp xếp theo:
                                <select class="input-select">
                                    <option value="0">Giá</option>
                                    <option value="1">Tên</option>
                                </select>
                            </label>
                        </div>
                    </div>

                    <div class="row" id="container-product">
                        <!-- product -->
                        <c:forEach var="row" items="${ListProduct}">
                            <fmt:formatNumber value="${row.price}" pattern="###,###,###" var="formattedPrice" />
                            <jsp:include page="item-product.jsp">
                                <jsp:param name="image" value="${row.productColorList[0].images}" />
                                <jsp:param name="linkDetail" value="" />
                                <jsp:param name="title" value="${row.name}" />
                                <jsp:param name="price" value="${formattedPrice}" />
                                <jsp:param name="reviewCount" value="(4 Reviews)" />
                                <jsp:param name="ratingCount" value="4.5" />
                            </jsp:include>
                        </c:forEach>
                        <!-- /product -->
                    </div>

                    <div class="d-flex flex-row-reverse">
                        <nav aria-label="Page navigation example" style="max-width: fit-content;">
                            <ul class="pagination">
                              <li class="page-item">
                                <a class="page-link" href="#" aria-label="Previous">
                                  <span aria-hidden="true">&laquo;</span>
                                </a>
                              </li>
                              <li class="page-item"><button class="page-link" name="page" type="button" value="1">1</button></li>
                              <li class="page-item"><button class="page-link" name="page" type="button" value="2">2</button></li>
                              <li class="page-item"><button class="page-link" name="page" type="button" value="3">3</button></li>
                              <li class="page-item">
                                <a class="page-link" href="#" aria-label="Next">
                                  <span aria-hidden="true">&raquo;</span>
                                </a>
                              </li>
                            </ul>
                          </nav>
                    </div>
                    
                </div>
            </div>
        </div>
    </main>
    <jsp:include page="../index/footer.jsp"></jsp:include>
    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/nouislider/distribute/nouislider.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    <!--setup slider price-->
    <script>
        var html5Slider = document.getElementById('slider');
        var max = ${MaxPrice};
        var min = ${MinPrice};
        noUiSlider.create(html5Slider, {
            start: [min, max],
            connect: true,
            range: {
                'min': min,
                'max': max
            }
        });
    </script>
    <!--update input slider price-->
    <script>
        var inputNumberUpper = document.getElementById('input-number-upper');
        var inputNumberLower= document.getElementById('input-number-lower')

        html5Slider.noUiSlider.on('update', function (values, handle) {

            var value = values[handle];

            if (handle) {
                inputNumberUpper.value = Math.round(value);
            } else {
                inputNumberLower.value = Math.round(value);
            }
        });

        inputNumberLower.addEventListener('change', function () {
            html5Slider.noUiSlider.set([this.value, null]);
        });

        inputNumberUpper.addEventListener('change', function () {
            html5Slider.noUiSlider.set([null, this.value]);
        });
    </script>
    <!--ajax search product-->
    <script>
        const button = document.getElementById('btnFilterPrice');
        button.addEventListener('click', function(event) {
            const keyword = document.getElementById('keyword').value;
            const inputNumberUpper = document.getElementById('input-number-upper').value;
            const inputNumberLower = document.getElementById('input-number-lower').value;
            const suppliers = document.querySelectorAll('input[name="suppliers"]:checked');
            const colors = document.querySelectorAll('input[name="colors"]:checked');
            var querysupplier = '';
            var querycolor = '';
            suppliers.forEach((checkbox) => {
                querysupplier += '&suppliers='+checkbox.value;
            });
            
            colors.forEach((checkbox) => {
                querycolor += '&colors='+checkbox.value;
            });
            
            fetch('search-product-ajax?keyword='+ keyword +
                    querysupplier + querycolor + '&lower='+
                    inputNumberLower + '&upper=' + inputNumberUpper, {
              method: 'GET',
            })
            .then(response => response.text())
            .then(data => {
                const responseContainer = document.createElement('div');
                responseContainer.innerHTML = data;
                
                const specificElement = responseContainer.querySelector('#container-product');
                
                const containerProduct = document.querySelector('#container-product');
                containerProduct.innerHTML = specificElement.innerHTML;
            })
            .catch(error => {
               console.error('Error:', error);
            });
        });
    </script>
    <!--click button page-->
    <script>
        var buttons = document.querySelectorAll('.page-link');

        buttons.forEach(function(button) {
          button.addEventListener('click', function() {
            var value = this.value;
            console.log(value);
            // Thực hiện các thao tác khác với giá trị đã lấy được
          });
        });
    </script>
</body>

</html>
