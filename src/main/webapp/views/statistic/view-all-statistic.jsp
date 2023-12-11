
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <h1 class="text-center">Thống kê</h1>
            <figure class="highcharts-figure">
                <div class="row">
                    <div class="col">
                        <div id="container-chart-customer"></div>
                    </div>
                    <div class="col">
                        <div id="container-chart-seller"></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div id="container-chart-revenue-in-year"></div>
                    </div>
                    <div class="col">
                        <div id="container-chart-hot-product"></div>
                    </div>
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
        fetch('statistic-5-customer', {
                  method: 'GET'
        })
        .then(response => response.json())
        .then(data => {
            draw5Customer(data.customers, data.totals);
        })
        .catch(error => {
           console.error('Error:', error);
        });
        function draw5Customer(customers, totals){
            Highcharts.chart('container-chart-customer', {
            chart: {
                type: 'column',
                styledMode: true
            },

            title: {
                text: '<a href="#">Top 5 khách hàng mua nhiều nhất</a>',
                align: 'center',
                useHTML: true
            },

            xAxis: {
                categories: customers
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
                name: 'Tổng tiền khách hàng đã mua',
                data: totals,
                tooltip: {
                    valueSuffix: ' VND'
                }
            }]
        });
        };
    </script>
    <script>
        fetch('statistic-5-seller', {
            method: 'GET'
        })
        .then(response => response.json())
        .then(data => {
            draw5Seller(data.sellers, data.totals);
        })
        .catch(error => {
           console.error('Error:', error);
        });
        function draw5Seller(sellers, totals){
            Highcharts.chart('container-chart-seller', {

                chart: {
                    type: 'column',
                    styledMode: true
                },

                title: {
                    text: '<a href="#">Top 5 nhân viên bán nhiều nhất</a>',
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
    <script>
        fetch('statistic-revenue-in-year', {
            method: 'GET'
        })
        .then(response => response.json())
        .then(data => {
            drawRevenueInYear(data.totals);
        })
        .catch(error => {
           console.error('Error:', error);
        });
        function drawRevenueInYear(totals){
            Highcharts.chart('container-chart-revenue-in-year', {

                title: {
                    text: '<a href="#">Doanh thu theo tháng trong năm</a>',
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
        };
    </script>
    <script>
        fetch('statistic-10-product', {
            method: 'GET'
        })
        .then(response => response.json())
        .then(data => {
            drawHotProduct(data.products, data.totals, data.quantity);
        })
        .catch(error => {
           console.error('Error:', error);
        });
        function drawHotProduct(products, totals, quantity){
        Highcharts.chart('container-chart-hot-product', {
            chart: {
                type: 'column',
                styledMode: true
            },

            title: {
                text: '<a href="#">Top 10 sản phẩm bán chạy nhất</a>',
                align: 'center',
                useHTML: true
            },

            xAxis: {
                categories: products
            },

            yAxis: [{ // Primary axis
                className: 'highcharts-color-0',
                title: {
                    text: 'Số lượng sản phẩm đã bán'
                }
            }, { // Secondary axis
                className: 'highcharts-color-1',
                opposite: true,
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
                name: 'Số lượng sản phẩm đã bán',
                data: quantity
            }, {
                name: 'Doanh thu',
                data: totals,
                yAxis: 1,
                tooltip: {
                    valueSuffix: ' VND'
                }
            }]

        });
    };
    </script>
</body>

</html>
