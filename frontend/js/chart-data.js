var randomScalingFactor = function () {
    return Math.round(Math.random() * 1000)
};

//var url = "https://api.load.plebian.nl";
var url = "http://localhost:8080"
$.ajax({
    url: url + "/stats/total", success: function (result) {
        $("#alltime").html(result.total + " GB");
        $("#alltimelabel").css('visibility', 'visible');
        $("#alltimeup").html(result.up + " GB");
        $("#alltimeuplabel").css('visibility', 'visible');
        $("#alltimedown").html(result.down + " GB");
        $("#alltimedownlabel").css('visibility', 'visible');
    }
});

$.ajax({
    url: url + "/scrape/week", success: function (result) {
        $("#thisweek").html(result.total + " GB");
        $("#thisweeklabel").css('visibility', 'visible');
    }
});

function getDoughnutData() {
    return $.getJSON(url + "/scrape/week").then(function (week) {

        $("#thisweek").html(week.total + " GB");
        $("#thisweeklabel").css('visibility', 'visible');

        $("#thisweekDoughnut").html("");

        var free = 100 - week.total;

        return [
            {
                value: week.total,
                color: "#f9243f",
                highlight: "#f6495f",
                label: "Used"
            },
            {
                value: free,
                color: "#30a5ff",
                highlight: "#62b9fb",
                label: "Free"
            }
        ];
    });
}

function getPiData() {
    return $.getJSON(url + "/stats/days").then(function (week) {
        $("#thisweekPi").html("");

        return [
            {
                value: 300,
                color: "#30a5ff",
                highlight: "#62b9fb",
                label: "Blue"
            },
            {
                value: 50,
                color: "#ffb53e",
                highlight: "#fac878",
                label: "Orange"
            }
        ];
    });
}

function getChartData() {
    return $.getJSON(url + "/stats/days").then(function (allDays) {
        var labels = [];
        allDays.forEach(function (element) {
            labels.push(new Date(element.datum).toISOString().substring(0, 10));
        });

        var dataUp = [];
        allDays.forEach(function (element) {
            dataUp.push(element.up);
        });

        var dataDown = [];
        allDays.forEach(function (element) {
            dataDown.push(element.down);
        });

        return {
            labels: labels,
            datasets: [
                {
                    label: "Upload",
                    fillColor: "rgba(220,220,220,0.2)",
                    strokeColor: "rgba(220,220,220,1)",
                    pointColor: "rgba(220,220,220,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(220,220,220,1)",
                    data: dataUp
                },
                {
                    label: "Download",
                    fillColor: "rgba(48, 164, 255, 0.2)",
                    strokeColor: "rgba(48, 164, 255, 1)",
                    pointColor: "rgba(48, 164, 255, 1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(48, 164, 255, 1)",
                    data: dataDown
                }
            ]

        };
    });
}

function getBarData() {
    return $.getJSON(url + "/stats/weeks").then(function (allDays) {
        var labels = [];
        allDays.forEach(function (element) {
            labels.push("Week " + element.weekNumber);
        });

        var dataUp = [];
        allDays.forEach(function (element) {
            dataUp.push(element.up);
        });

        var dataDown = [];
        allDays.forEach(function (element) {
            dataDown.push(element.down);
        });

        return {
            labels: labels,
            datasets: [
                {
                    fillColor: "rgba(220,220,220,0.5)",
                    strokeColor: "rgba(220,220,220,0.8)",
                    highlightFill: "rgba(220,220,220,0.75)",
                    highlightStroke: "rgba(220,220,220,1)",
                    data: dataUp
                },
                {
                    fillColor: "rgba(48, 164, 255, 0.2)",
                    strokeColor: "rgba(48, 164, 255, 0.8)",
                    highlightFill: "rgba(48, 164, 255, 0.75)",
                    highlightStroke: "rgba(48, 164, 255, 1)",
                    data: dataDown
                }
            ]
        }
    });
}

$(document).ready(function(){
    getChartData().then(function (data) {
        var chart1 = document.getElementById("line-chart").getContext("2d");
        window.myLine = new Chart(chart1).Line(data, {
            responsive: true
        });
    });

    getBarData().then(function (data) {
        var chart2 = document.getElementById("bar-chart").getContext("2d");
        window.myBar = new Chart(chart2).Bar(data, {
            responsive: true
        });
    });

    getDoughnutData().then(function (data) {
        var chart3 = document.getElementById("doughnut-chart").getContext("2d");
        window.myDoughnut = new Chart(chart3).Doughnut(data, {
            responsive: true
        });
    });

    getPiData().then(function (data) {
        var chart4 = document.getElementById("pie-chart").getContext("2d");
        window.myPie = new Chart(chart4).Pie(data, {
            responsive: true
        });
    });
});