
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Thống kê</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link href="../css/admin.css" rel="stylesheet">
    <style>
        @import url("https://code.highcharts.com/css/highcharts.css");

        .highcharts-figure,
        .highcharts-data-table table {
            min-width: 310px;
            max-width: 100%;
            margin: 1em auto;
        }

        .highcharts-data-table table {
            font-family: Verdana, sans-serif;
            border-collapse: collapse;
            border: 1px solid #ebebeb;
            margin: 10px auto;
            text-align: center;
            width: 100%;
            max-width: 500px;
        }

        .highcharts-data-table caption {
            padding: 1em 0;
            font-size: 1.2em;
            color: #555;
        }

        .highcharts-data-table th {
            font-weight: 600;
            padding: 0.5em;
        }

        .highcharts-data-table td,
        .highcharts-data-table th,
        .highcharts-data-table caption {
            padding: 0.5em;
        }

        .highcharts-data-table thead tr,
        .highcharts-data-table tr:nth-child(even) {
            background: #f8f8f8;
        }

        .highcharts-data-table tr:hover {
            background: #f1f7ff;
        }

        .highcharts-yaxis .highcharts-axis-line {
            stroke-width: 2px;
        }

        /* Link the series colors to axis colors */
        .highcharts-color-0 {
            fill: #185996;
            stroke: #185996;
        }

        .highcharts-axis.highcharts-color-0 .highcharts-axis-line {
            stroke: black;
        }

        .highcharts-axis.highcharts-color-0 text {
            fill: black;
        }

        .highcharts-color-1 {
            fill: #29761a;
            stroke: black;
        }

        .highcharts-axis.highcharts-color-1 .highcharts-axis-line {
            stroke: black;
        }

        .highcharts-axis.highcharts-color-1 text {
            fill: black;
        }
    </style>
    <style>
        .row {
            margin-top: 40px;
        }

        .col {
            margin-right: 20px;
            margin-left: 20px;
        }

        h1 {
            margin-top: 30px;
        }
        a{
            text-decoration: none;
            color: inherit;
        }
        .title-chart{
            margin-left: 20px;
        }
    </style>
</head>

<body>
    <jsp:include page="../index/header-admin-page.jsp"/>
    <main>
        <div class="container">
            <h1 class="text-center">Top 5 nhân viên bán hàng đạt doanh thu cao nhất</h1>
            <figure class="highcharts-figure">
                <div class="row">
                    <div id="container-chart-seller"></div>
                </div>
                <div class="row">
                    <form>
                            <table class="table text-center align-middle">
                                <thead>
                                    <tr>
                                        <th scope="col">STT</th>
                                        <th scope="col">Mã NV</th>
                                        <th scope="col">Tên nhân viên</th>
                                        <th scope="col">Doanh thu</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="count" value="1"/>
                                    <c:forEach var="row" items="${ListSeller}">
                                        <tr>
                                            <th scope="row">${count}</th>
                                            <td>${row.seller}</td>
                                            <td>${row.name}</td>
                                            <td><fmt:formatNumber value="${row.total}" pattern="###,###,###"/></td>
                                        </tr>
                                        <c:set var="count" value="${count+1}"/>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </form>
                </div>
            </figure>
        </div>
    </main>
    <jsp:include page="../index/footer-admin-page.jsp"/>   
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="https://code.highcharts.com/modules/exporting.js"></script>
    <script src="https://code.highcharts.com/modules/export-data.js"></script>
    <script src="https://code.highcharts.com/modules/accessibility.js"></script>
    <script>
        var sellers = [];
        var totals = [];
        <c:forEach var="i" begin="0" end="5" step="1">
            totals[${i}] = ${totals[i]}
            sellers[${i}] = '${sellers[i]}'
        </c:forEach> 
            
        draw5Seller(sellers, totals);
        function draw5Seller(sellers, totals){
            Highcharts.chart('container-chart-seller', {

                chart: {
                    type: 'column',
                    styledMode: true
                },

                title: {
                    text: 'Top 5 nhân viên bán nhiều nhất',
                    align: 'center',
                    useHTML: true
                },

                xAxis: {
                    categories: sellers
                },

                yAxis: [{ // Primary axis
                    className: 'highcharts-color-0',
                    title: {
                        text: 'Doanh thu (VND)'
                    }
                }],

                plotOptions: {
                    column: {
                        borderRadius: 5
                    }
                },

                series: [{
                    name: 'Tổng doanh thu đạt được',
                    data: totals,
                    tooltip: {
                        valueSuffix: ' VND'
                    }
                }]
            });
        };
    </script>
</body>

</html>
