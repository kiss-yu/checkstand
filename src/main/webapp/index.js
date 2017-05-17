/**
 * Created by 11723 on 2017/5/11.
 */
var wsHost = "ws://localhost:80";
var webSocket = null;
if ("WebSocket" in window) {
    webSocket = new WebSocket(wsHost + "/webSocketServer.do");
    webSocket.onerror = function(event) {
    };
    webSocket.onopen = function(event) {

    };
    webSocket.onmessage = function(event) {
        var data = eval('(' + event.data + ')');
        console.log(data);
        setting(data.type,data.msg);
    };
    webSocket.onclose = function(event) {
    };
    window.onbeforeunload = function(event) {
        webSocket.close();
    };
}
function send(type) {
    webSocket.send(type);
}

function sendSales_statistics() {
    send("Sales_statistics")
}
function sendGoods_percentage() {
    send("Goods_percentage")
}
function sendGoods_sort() {
    send("Goods_sort")
}
function sendInventory_warring() {
    send("Inventory_warring")
}
function setting(type,msg) {
    switch (type){
        case "Sales_statistics":changSales_statistics(msg);break;
        case "Goods_percentage":changGoods_percentage(msg);break;
        case "Goods_sort":changGoods_sort(msg);break;
        case "Inventory_warring":changInventory_warring(msg);break;
        default:break;
    }
    type = null;
}

var Sales_statistics = echarts.init(document.getElementById('Sales_statistics'));
var goods_sort = echarts.init(document.getElementById('goods_sort'));
var inventory_warring = echarts.init(document.getElementById('inventory_warring'));
var goods_percentage = echarts.init(document.getElementById('goods_percentage'));
var Sales_statistics_option = {
    title: {
        text: '销售状态',
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#283b56'
            }
        }
    },
    legend: {
        data:['当天成交价', '当天销售数量']
    },
    toolbox: {
        show: true,
        feature: {
            dataView: {readOnly: false},
            restore: {},
            saveAsImage: {}
        }
    },
    dataZoom: {
        show: false,
        start: 0,
        end: 100
    },
    xAxis: [
        {
            type: 'category',
            boundaryGap: true,
            data:['05/01','05/02','05/03','05/04','05/05','05/06','05/07']
        },
        {
            type: 'category',
            boundaryGap: true,
            data:['05/01','05/02','05/03','05/04','05/05','05/06','05/07']
        }
    ],
    yAxis: [
        {
            type: 'value',
            scale: true,
            name: '价格',
            max: 30000,
            min: 0,
            boundaryGap: [0.2, 0.2]
        },
        {
            type: 'value',
            scale: true,
            name: '销售量',
            max: 1200,
            min: 0,
            boundaryGap: [0.2, 0.2]
        }
    ],
    series: [
        {
            name:'当天销售数量',
            type:'line',
            xAxisIndex: 1,
            yAxisIndex: 1,
            data:[600,400,952,845,255,795,1100,]
        },
        {
            name:'当天成交价',
            type:'line',
            data:[20300,21005,21005,20000,10400,13220,25125]
        }
    ]
};

var goods_sort_option = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            crossStyle: {
                color: '#999'
            }
        }
    },
    toolbox: {
        feature: {
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    legend: {
        data:['销售量','库存剩余']
    },
    xAxis: [
        {
            type: 'category',
            data: ['1月','2月','3月','4月','5月'],
            axisPointer: {
                type: 'shadow'
            }
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: '销售量',
            min: 0,
            max: 2000,
            interval: 200,

        },
        {
            type: 'value',
            name: '库存剩余',
            min: 0,
            max: 2000,
            interval: 200,
        }
    ],
    series: [
        {
            name:'销售量',
            type:'bar',
            data:[200, 400, 900, 2000, 1000]
        },
        {
            name:'库存剩余',
            type:'bar',
            data:[600, 700, 400, 200, 650]
        }
    ]
};
var inventory_warring_option = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            crossStyle: {
                color: '#999'
            }
        }
    },
    toolbox: {
        feature: {
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    legend: {
        data:['剩余库存']
    },
    xAxis: [
        {
            type: 'category',
            data: ['1月','2月','3月','4月','5月'],
            axisPointer: {
                type: 'shadow'
            }
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: '库存剩余',
            min: 0,
            max: 100,
            interval: 10,

        }
    ],
    series: [
        {
            name:'库存剩余',
            type:'bar',
            data:[5, 10, 10, 7, 20]
        }
    ]
};

var goods_percentage_option = {
    title : {
        text: '热门商品比例',
        subtext: '按售出数量排行',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ['商品1','商品2','商品3','商品4','商品5']
    },
    series : [
        {
            name: '详细信息',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:335, name:'商品1'},
                {value:310, name:'商品2'},
                {value:234, name:'商品3'},
                {value:135, name:'商品4'},
                {value:1548, name:'商品5'}
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};



function changSales_statistics(data) {
    /*
    {msg:[
     {"date":"05/01",'count':989,'pace':20653.00},
     {"date":"05/02",'count':1105,'pace':26458.50},
     {"date":"05/03",'count':952,'pace':21560.00},
     {"date":"05/04",'count':560,'pace':13261.00},
     {"date":"05/05",'count':420,'pace':9563.50},
     {"date":"05/06",'count':620,'pace':16230.50},
     {"date":"05/07",'count':305,'pace':6354.00},
    ]}
    * */
    var axisData = Sales_statistics_option.xAxis[0].data;
    var data0 = Sales_statistics_option.series[0].data;
    var data1 = Sales_statistics_option.series[1].data;
    data0.shift();
    data1.shift();
    axisData.shift();
    for(var i = 0;i < data.msg.length;i ++){
        axisData[i] = data.msg[i].date;
        data0[i] = data.msg[i].count;
        data1[i] = data.msg[i].pace;
    }
    Sales_statistics.setOption(Sales_statistics_option);
}
function changGoods_sort(data) {
    /*
     {goods:[{"title":"测试商品1","soldNumber":1000,"inventory":500},
     {"title":"测试商品2","soldNumber":1500,"inventory":200},
     {"title":"测试商品3","soldNumber":1650,"inventory":156},
     {"title":"测试商品4","soldNumber":890,"inventory":265},
     {"title":"测试商品5","soldNumber":1188,"inventory":260}]
     }
    * */
    var x_data = goods_sort_option.xAxis[0].data;
    var series1 = goods_sort_option.series[0].data;
    var series2 = goods_sort_option.series[1].data;
    x_data.shift();
    series1.shift();
    series2.shift();
    for (var i = 0;i < data.goods.length;i ++){
        x_data[i] = data.goods[i].title;
        series1[i] = data.goods[i].soldNumber;
        series2[i] = data.goods[i].inventory;
    }
    goods_sort.setOption(goods_sort_option);
}
function changInventory_warring(data) {
    /*
    {goods:[{"title":"测试商品1","inventory":20},
            {"title":"测试商品2","inventory":66},
            {"title":"测试商品3","inventory":85},
            {"title":"测试商品4","inventory":46},
            {"title":"测试商品5","inventory":29}]
         }
     */

    var x_data = inventory_warring_option.xAxis[0].data;
    var series = inventory_warring_option.series[0].data;
    x_data.shift();
    series.shift();
    for(var i = 0;i < data.goods.length;i ++){
        x_data[i] = data.goods[i].title;
        series[i] = data.goods[i].inventory;
    }
    inventory_warring.setOption(inventory_warring_option);
}
function changGoods_percentage(data) {
    /*
    {goods:[
     {"title":"商品1","soldNumber":200},
     {"title":"商品2","soldNumber":50},
     {"title":"商品3","soldNumber":196},
     {"title":"商品4","soldNumber":154},
     {"title":"商品5","soldNumber":296},
    ],
    sum:2000
    }
    */
    var legend = goods_percentage_option.legend.data;
    var value = goods_percentage_option.series[0].data;
    legend.shift();
    value.shift();
    var sum = 0;
    var i = 0;
    for(;i < data.goods.length;i ++){
        legend[i] = data.goods[i].title;
        value[i] = {value:data.goods[i].soldNumber,name:data.goods[i].title};
        sum += data.goods[i].soldNumber;
    }
    legend[i] = "其他";
    value[i] = {value:data.sum - sum,name:"其他"};
    goods_percentage.setOption(goods_percentage_option);
}
changInventory_warring({goods:[
    {"title":"测试商品1","inventory":20},
    {"title":"测试商品2","inventory":66},
    {"title":"测试商品3","inventory":85},
    {"title":"测试商品4","inventory":46},
    {"title":"测试商品5","inventory":29}
    ]})
changGoods_sort({goods:[{"title":"测试商品1","soldNumber":1800,"inventory":500},
    {"title":"测试商品2","soldNumber":1500,"inventory":200},
    {"title":"测试商品3","soldNumber":1350,"inventory":156},
    {"title":"测试商品4","soldNumber":1200,"inventory":265},
    {"title":"测试商品5","soldNumber":1188,"inventory":260}]
})
changSales_statistics({msg:[
    {"date":"05/01",'count':989,'pace':20653.00},
    {"date":"05/02",'count':1105,'pace':26458.50},
    {"date":"05/03",'count':952,'pace':21560.00},
    {"date":"05/04",'count':560,'pace':13261.00},
    {"date":"05/05",'count':420,'pace':9563.50},
    {"date":"05/06",'count':620,'pace':16230.50},
    {"date":"05/07",'count':305,'pace':6354.00},
]})
changGoods_percentage( {goods:[
    {"title":"商品1","soldNumber":200},
    {"title":"商品2","soldNumber":50},
    {"title":"商品3","soldNumber":196},
    {"title":"商品4","soldNumber":154},
    {"title":"商品5","soldNumber":296},
], sum:3000
})