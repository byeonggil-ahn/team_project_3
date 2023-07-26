$(document).ready(function () {
    function getRandomColor() {
        let letters = '0123456789ABCDEF';
        let color = '#';
        for (let i=0; i<6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
    }

    function drawGraph() {
        $.ajax({
            url: "rest/canvas",
            type: "GET",
            success: function (graph) {
                var labels = graph.map(function (i) {
                    return i.product_name;
                });
                var sellCounts = graph.map(function (i) {
                    return i.product_sellcount;
                });

                var ctx = document.getElementById('canvas').getContext('2d');

                // 랜덤한 컬러 생성 함수
                let gradient = ctx.createLinearGradient(0, 0, 0, 200);
                gradient.addColorStop(0, 'rgba(75, 192, 192, 0.2)');
                gradient.addColorStop(1, 'rgba(75, 192, 192, 0.7)');

                new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: labels,
                        datasets: [{
                            label: '상품 판매량',
                            data: sellCounts,
                            backgroundColor: gradient,
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            },
            error: function (xhr, status, error) {
                alert("에러났음 안됨. 망함 ㅅㄱ");
            }
        })
    }
    drawGraph();
});
