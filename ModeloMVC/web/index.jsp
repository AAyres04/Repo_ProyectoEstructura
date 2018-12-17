<%-- 
    Document   : index
    Created on : 16-12-2018, 11:57:47
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contaminacion</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    </head>
    <body style="background-color:rgb(0, 255, 136);">
        <h1>Contaminación en Temuco</h1>
        <h3>Uno de los problemas que impactan a la sociedad, es la contaminación del aire.
            En particular Temuco, esta se caracteriza por tener uno de los niveles más altos en comparación con otras ciudades de Chile.
            Una consecuencia de esto, es el impacto negativo en la salud respiratoria de las personas que viven en la comuna</h3>
        <canvas id="myChart" width="50" height="10"></canvas>
        <script>
            var popCanvas = document.getElementById("myChart");

            var popData = {
                datasets: [{
                        label: ['Hospitalizados'],
                        data: [{
                                x: 100,
                                y: 0,
                                r: 10
                            }, {
                                x: 60,
                                y: 30,
                                r: 20
                            }, {
                                x: 40,
                                y: 60,
                                r: 25
                            }, {
                                x: 80,
                                y: 80,
                                r: 50
                            }, {
                                x: 20,
                                y: 30,
                                r: 25
                            }, {
                                x: 0,
                                y: 100,
                                r: 5
                            }],
                        backgroundColor: "#FF9966"
                    }]
            };

            var data = ${datos};
            var bubbleChart = new Chart(popCanvas, {
                type: 'bubble',
                data: popData
            });
        </script>
        <c:forEach var="temp" items="${datos}">
            ${temp}<br>
        </c:forEach>
    </body>
</html>
