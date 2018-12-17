<%-- 
    Document   : index
    Created on : 09-11-2018, 14:56:15
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <script src="https://d3js.org/d3.v3.min.js"></script>
    </head>
    <body>
        <h1>Estadisticas de Furgones</h1>
        <script>
            /// d3 actionsssss		
            var baseData = ${datos};

            var data = d3.nest().key(function (d) {
                return d.marca;
            })
                    .rollup(function (leaves) {
                        return d3.sum(leaves, function (d) {
                            return d.nProblemas;
                        });
                    }).entries(baseData)
                    .map(function (d) {
                        return {marca: d.key, nProblemas: d.values};
                    });
            
            var width = 800,
                    height = 250,
                    radius = Math.min(width, height) / 2;

            var color = d3.scale.ordinal()
                    .range(["#98abc5", "#8a89a6", "#7b6888", "#6b486b", "#a05d56", "#d0743c", "#ff8c00"]);

            var arc = d3.svg.arc()
                    .outerRadius(radius - 10)
                    .innerRadius(radius - 70);

            var pie = d3.layout.pie()
                    .sort(null)
                    .value(function (d) {
                        return d.nProblemas;
                    });



            var svg = d3.select("body").append("svg")
                    .attr("width", width)
                    .attr("height", height)
                    .append("g")
                    .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

            var g = svg.selectAll(".arc")
                    .data(pie(data))
                    .enter().append("g")
                    .attr("class", "arc");

            g.append("path")
                    .attr("d", arc)
                    .style("fill", function (d) {
                        return color(d.data.marca);
                    });

            g.append("text")
                    .attr("transform", function (d) {
                        return "translate(" + arc.centroid(d) + ")";
                    })
                    .attr("dy", ".20em")
                    .style("text-anchor", "middle")
                    .text(function (d) {
                        return d.data.marca;
                    });
        </script>
    </body>
</html>
