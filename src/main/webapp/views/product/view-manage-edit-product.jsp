<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Quản lý sản phẩm</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
        <link href="../../../css/manage-product.css" rel="stylesheet">
        <link href="../../../css/admin.css" rel="stylesheet">
        <style>
            .input-group, .form-floating{
                margin-bottom: 10px;
            }
        </style>
    </head>

    <body>
        <jsp:include page="../index/header-admin-page.jsp"/>
        <main>
            <div class="container">
                <div class="row align-items-center">
                    <div class="col text-center title">Quản lý sản phẩm</div>
                </div>
                <div class="row justify-content-md-center menu">
                    <div class="col col-lg-2">
                        <a href="../view">Danh sách sản phẩm</a>
                    </div>
                    <div class="col col-lg-2">
                        <a href="../add">Thêm sản phẩm</a>
                    </div>
                </div>
                <div class="row align-items-center">
                    <div class="col text-center" style="font-size: 30px">
                        Sửa thông tin sản phẩm
                    </div>
                </div>
                <form id="product-form">
                    <div class="input-group flex-nowrap">
                        <span class="input-group-text" id="addon-wrapping">Tên</span>
                        <input type="text" class="form-control" id="name" value="${Product.name}" aria-describedby="addon-wrapping" required>
                    </div>
                    <div class="input-group flex-nowrap">
                        <span class="input-group-text" id="addon-wrapping">Giá</span>
                        <input type="number" class="form-control no-spinners" id="price" value="${Product.price}" aria-describedby="addon-wrapping" required>
                    </div>
                    <div class="input-group">
                        <label class="input-group-text" for="supplier">Nhà cung cấp</label>
                        <select class="form-select" id="supplier" aria-label="Example select with button addon">
                            <c:forEach var="row" items="${ListSupplier}">
                                <c:choose>
                                    <c:when test="${row.supplierID==Product.supplier.supplierID}">
                                        <option value="${row.supplierID}" selected>${row.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${row.supplierID}">${row.name}</option>
                                    </c:otherwise>
                                </c:choose>     
                            </c:forEach>
                        </select>
<!--                        <button id="buttonInsertSupplier" class="btn btn-outline-secondary" type="button">
                            Thêm mới
                        </button>-->
                    </div>  


                    <div class="container container-color text-center" id="product-color-rows">
                        <c:forEach var="row" items="${Product.productColorList}">
                            <div class="row row-product-color">
                                <div class="col-sm-4">
                                    <div class="row">
                                        <div class="input-group">
                                            <label class="input-group-text" for="color">Màu</label>
                                            <select class="form-select" id="color"
                                                    aria-label="Example select with button addon">
                                                <c:forEach var="rowColor" items="${ListColor}">
                                                    <c:choose>
                                                        <c:when test="${rowColor.colorID==row.color.colorID}">
                                                            <option value="${rowColor.colorID}" selected>${rowColor.name}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${rowColor.colorID}">${rowColor.name}</option>
                                                        </c:otherwise>
                                                    </c:choose>     
                                                </c:forEach>     
                                            </select>
<!--                                            <button id="buttonInsertColor" class="btn btn-outline-secondary" type="button">
                                                Thêm màu
                                            </button>-->
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm input-group">
                                            <span class="input-group-text">Số lượng</span>
                                            <input type="number" min="0" id="quantity" class="form-control" value="${row.quantity}" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="col col-lg-2 container-image">
                                    <img style="width: 90px; height: 90px;" src="../../../${row.images}"/>
                                </div>
                                <div class="col-sm-4 d-flex align-items-center">
                                    <div class="input-group" style="margin-bottom: 0px;">
                                        <label class="input-group-text" for="image">Đổi ảnh</label>
                                        <input type="file" class="form-control" accept=".jpg, .png" id="image"/>
                                    </div>
                                </div>
                                <c:choose>
                                    <c:when test="${row.status.statusID == 1}">
                                        <div class="col-sm-2 container-btn-action d-flex align-items-center">
                                            <button type="button"
                                                    onclick="lockColor(${Product.productID},${row.color.colorID})" 
                                                    class="btn btn-danger">Ngừng kinh doanh
                                            </button>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="col-sm-2 container-btn-action d-flex align-items-center">
                                            <button type="button"
                                                    onclick="unlockColor(${Product.productID},${row.color.colorID})" 
                                                    class="btn btn-danger">Tiếp tục kinh doanh
                                            </button>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="row text-center">
                        <div class="col">
                            <button type="button" class="btn btn-primary" onclick="addNewRow()">
                                Thêm
                            </button>
                        </div>
                    </div>

                    <div class="form-floating">
                        <textarea class="form-control" placeholder="Mô tả" id="description"
                                  style="height: 100px; margin-bottom: 10px; margin-top: 20px" required>${Product.description}</textarea>
                        <label for="description">Mô tả</label>
                    </div>
                    <div class="form-floating">
                        <textarea class="form-control" placeholder="Thông số" id="specifications"
                                  style="height: 100px; margin-bottom: 10px; margin-top: 20px" required>${Product.specifications}</textarea>
                        <label for="specifications">Thông số</label>
                    </div>
                    <div class="input-group mb-3">
                        <label class="input-group-text" for="status">Trạng thái</label>
                        <select class="form-select" id="status" required>
                            <c:forEach var="row" items="${ListStatus}">
                                <c:choose>
                                    <c:when test="${row.statusID==Product.status.statusID}">
                                        <option value="${row.statusID}" selected>${row.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${row.statusID}">${row.name}</option>
                                    </c:otherwise>
                                </c:choose>     
                            </c:forEach>
                        </select>
                    </div>

                    <button class="btn btn-primary" type="submit">Lưu lại</button>
                </form>
                <form id="lock-color"></form>
            </div>
            <div id="popupInsertSupplier" class="popupInsertSupplier">
                <div class="popup-content-supplier">
                    <span class="close-supplier">&times;</span>
                    <div class="container text-center">
                        <div class="row justify-content-md-center">
                            <h5>Thêm nhà cung cấp</h5>
                        </div>
                        <div class="row">
                            <div class="input-group input-group-sm mb-3">
                                <span class="input-group-text" id="inputGroup-sizing-sm">Tên</span>
                                <input type="text" class="form-control" aria-label="Sizing example input"
                                       aria-describedby="inputGroup-sizing-sm">
                            </div>
                        </div>
                        <button type="button" class="btn btn-primary">Thêm</button>
                    </div>
                </div> 
            </div>    
            <div id="popupInsertColor" class="popupInsertColor">
                <div class="popup-content-color">
                    <span class="close-color">&times;</span>
                    <div class="container text-center">
                        <div class="row justify-content-md-center">
                            <h5>Thêm màu sắc</h5>
                        </div>
                        <div class="row">
                            <div class="input-group input-group-sm mb-3">
                                <span class="input-group-text" id="inputGroup-sizing-sm">Tên</span>
                                <input type="text" class="form-control"
                                       aria-label="Sizing example input"
                                       aria-describedby="inputGroup-sizing-sm">
                            </div>
                        </div>
                        <button type="button" class="btn btn-primary">Thêm</button>
                    </div>
                </div>
            </div>
        </main>              
        <jsp:include page="../index/footer-admin-page.jsp"/> 
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            function lockColor(proId, colorId) {
                var form = document.getElementById("lock-color");
                form.action = "../lock-color/" +proId;
                var input = document.createElement("input");
                input.type = "number";
                input.name = "id";
                input.value = colorId;
                form.appendChild(input);
                form.method = "post";
                form.submit();
                console.log(form);
            }
            function unlockColor(proId, colorId) {
                var form = document.getElementById("lock-color");
                form.action = "../unlock-color/" +proId;
                var input = document.createElement("input");
                input.type = "number";
                input.name = "id";
                input.value = colorId;
                form.appendChild(input);
                form.method = "post";
                form.submit();
            }
        </script>
        <script>
            function addNewRow() {
                // Sao chép row hiện tại
                var currentRow = document.querySelector(".row-product-color");
                var newRow = currentRow.cloneNode(true);
                console.log(newRow);
                // Xóa giá trị của các input/select trong row mới
                var inputs = newRow.querySelectorAll("input, select");
                inputs.forEach(function (input) {
                    input.value = "";
                });

                var containerImage = newRow.querySelector('.container-image');
                containerImage.innerHTML = '';
                
                var containerAction = newRow.querySelector('.container-btn-action');
                containerAction.innerHTML = '<button type="button" class="btn btn-danger" id="btn-delete-row">Xóa</button>';

                // Gắn sự kiện click cho tất cả các nút "Xóa"
                var deleteButton = newRow.querySelector('#btn-delete-row');
                deleteButton.addEventListener('click', function () {
                    // Lấy phần tử cha của nút "Xóa" là hàng chứa nút đó
                    var row = deleteButton.parentNode.parentNode;
                    // Xóa hàng khỏi DOM
                    row.remove();
                });

                // Thêm row mới vào phía dưới row hiện tại
                var productRows = document.getElementById("product-color-rows");
                productRows.appendChild(newRow);
            }
        </script>

        <script>
            var popupButtonSupplier = document.getElementById("buttonInsertSupplier");
            var popupSupplier = document.getElementById("popupInsertSupplier");
            var closeSupplier = document.getElementsByClassName("close-supplier")[0];

            popupButtonSupplier.addEventListener("click", function () {
                popupSupplier.style.display = "block";
            });

            closeSupplier.addEventListener("click", function () {
                popupSupplier.style.display = "none";
            });

            window.addEventListener("click", function (event) {
                if (event.target == popupSupplier) {
                    popupSupplier.style.display = "none";
                }
            });
        </script>
        <script>
            var popupButtonColor = document.getElementById("buttonInsertColor");
            var popupColor = document.getElementById("popupInsertColor");
            var closeColor = document.getElementsByClassName("close-color")[0];

            popupButtonColor.addEventListener("click", function () {
                popupColor.style.display = "block";
            });

            closeColor.addEventListener("click", function () {
                popupColor.style.display = "none";
            });

            window.addEventListener("click", function (event) {
                if (event.target == popupColor) {
                    popupColor.style.display = "none";
                }
            });
        </script>

        <script>
            document.getElementById("product-form").addEventListener("submit", function (event) {
                event.preventDefault();
                const name = document.getElementById('name').value;
                const price = document.getElementById('price').value;
                const supplier = document.getElementById('supplier').value;
                const status = document.getElementById('status').value;
                const description = document.getElementById('description').value;
                const specifications = document.getElementById('specifications').value;

                var rows = document.querySelectorAll(".row-product-color");
                var promises = [];

                rows.forEach((row) => {
                    var colorId = row.querySelector('#color').value;
                    var quantity = row.querySelector('#quantity').value;
                    var image = row.querySelector('#image').files[0];

                    var itemColor = {
                        "color": colorId,
                        "quantity": quantity,
                        "image": ''
                    };

                    if (image) {
                        promises.push(new Promise((resolve, reject) => {
                            fileToBase64(image, function (imageString) {
                                itemColor.image = imageString;
                                resolve(itemColor);
                            });
                        }));
                    } else {
                        promises.push(Promise.resolve(itemColor));
                    }
                });

                Promise.all(promises)
                .then((productColorList) => {
                    var product = {
                        "name": name,
                        "price": price,
                        "supplier": supplier,
                        "status": status,
                        "colorList": productColorList,
                        "description": description,
                        "specifications": specifications
                    };
                    fetch('', {
                        method: 'POST',
                        headers: {
                            "Content-Type": 'application/json',
                        },
                        body: JSON.stringify(product),
                    })
                    .then(response => response.json())
                    .then(data => {
                        if (data.Success) {
                            alert("Sửa sản phẩm thành công");
                            location.reload(true);
                        } else {
                            alert("Sửa sản phẩm thất bại");
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                    });
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
            });

            function fileToBase64(file, callback) {
                var reader = new FileReader();

                reader.onloadend = function () {
                    var base64String = reader.result.split(',')[1];
                    callback(base64String);
                };

                reader.readAsDataURL(file);
            }
            function updateDataById(id, value) {
                const td = document.getElementById(id);
                if (td) {
                    td.textContent = value;
                }
            }
        </script>

    </body>

</html>
