$(function() {
    $('#datepicker').datepicker({
        autoclose: true,
        beforeShowDay: $.noop,
        calendarWeeks: true,
        clearBtn: false,
        daysOfWeekDisabled: [],
        endDate: Infinity,
        forceParse: true,
        format: 'yyyy-mm-dd',
        keyboardNavigation: true,
        language: 'zh-CN',
        minViewMode: 0,
        multidate: false,
        multidateSeparator: ',',
        orientation: "auto",
        rtl: false,
        startDate: -Infinity,
        startView: 0,
        todayBtn: false,
        todayHighlight: true,
        weekStart: 0
    });


    var myChart = echarts.init(document.getElementById('chartmain'));

// 异步加载数据
    $.get('/kpi/asns').done(function (data) {
        // 填入数据
        myChart.setOption({

            legend: {},
            tooltip: {},
            dataset: {
                // 这里指定了维度名的顺序，从而可以利用默认的维度到坐标轴的映射。
                // 如果不指定 dimensions，也可以通过指定 series.encode 完成映射，参见后文。
                dimensions: ['lastModifyDate',  'ordUnitQty'],
                source: data
            },
            xAxis: {type: 'category'},
            yAxis: {},
            series: [
                {type: 'bar'},
                {type: 'bar'},
            ]
        });
    })

})


