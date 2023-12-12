
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
                <h1 class="text-center">Thống kê doanh thu theo tháng trong năm</h1>
                <figure class="highcharts-figure">
                    <div class="row">
                        <div id="container-chart-revenue-in-year"></div>
                    </div>
                    <div class="row">
                        <form>
                            <table class="table align-middle text-center">
                                <thead>
                                    <tr>
                                        <th scope="col">Tháng</th>
                                        <th scope="col">Doanh thu</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="row" items="${ListRevenue}">
                                        <tr>
                                            <th scope="row">${row.month}</th>
                                            <td><fmt:formatNumber value="${row.total}" pattern="###,###,###"/></td>
                                        </tr>
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
            var totals = [];
            <c:forEach var="i" begin="0" end="12" step="1">
                totals[${i}] = ${totals[i]}
            </c:forEach>
            drawRevenueInYear(totals);
            function drawRevenueInYear(totals) {
                Highcharts.chart('container-chart-revenue-in-year', {

                    title: {
                        text: 'Doanh thu theo tháng trong năm',
                        align: 'center',
                        useHTML: true
                    },
                    yAxis: {
                        title: {
                            text: 'Doanh thu (VND)'
                        }
                    },

                    xAxis: {
                        accessibility: {
                            rangeDescription: 'Từ tháng 1 đến tháng 12'
                        }
                    },

                    legend: {
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'middle'
                    },

                    xAxis: {
                        categories: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', ' Tháng 11', 'Tháng 12']
                    },

                    series: [{
                            name: 'Doanh thu',
                            data: totals
                        }],

                    responsive: {
                        rules: [{
                                condition: {
                                    maxWidth: 500
                                },
                                chartOptions: {
                                    legend: {
                                        layout: 'horizontal',
                                        align: 'center',
                                        verticalAlign: 'bottom'
                                    }
                                }
                            }]
                    }
                });
            }
        </script>
    </body>

</html>
