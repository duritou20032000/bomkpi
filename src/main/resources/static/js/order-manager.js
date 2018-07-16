$.ajaxSetup({headers: {'X-CSRF-TOKEN': $("#csrf_token").attr("content")}});

$(function () {
    var $wrapper = $('#div-table-container');
    var $table = $('#table-counter');
    initData($wrapper,$table,counterManage);
    getWhseCode();
});

function initData($wrapper,$table,counterManage) {
    var _table = $table.dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
        "ajax": function(data,callback,settings){
            //手动控制遮罩
            // $wrapper.spinModal();
            var param = counterManage.getQueryCondition(data);
            $.ajax({
                    url: "/order/getOrders",
                    data: param,
                    type: "POST",
                    dataType: "json",
                    success: function (result) {
                        if (result.errorCode) {
                            alert("查询失败。错误码:" + result.errorCode);
                            return;
                        }
                        var returnData = {};
                        returnData.draw = result.data.length;//这里直接自行返回了draw计数器,应该由后台返回
                        returnData.recordsTotal = result.data.length;
                        returnData.recordsFiltered = result.data.length;//后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.data;
                        //关闭遮罩
                        // $wrapper.spinModal(false);
                        //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                        //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                        callback(returnData);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        alert("查询失败");
                        // $wrapper.spinModal(false);
                    }
            });
        },
        "columns": [
            {
                "data":null,
            },
            {
                "data": "orderCode",
            },
            {
                "data": "whseCode",
            },
            {
                "data": "whseName",
            },
            {
                "data": "orderType",
                render: function (data, type, row, meta) {

                    return (data == 1?"组装":"拆零");
                }
            },
            {
                "data": "productCount",
            },
            {
                "data": "productUnit",
            },
            {
                "data": "deadline",
                render: function (data, type, row, meta) {
                    if (data != null) {
                        return new Date(Date.parse(data)).Format("yyyy-MM-dd hh:mm:ss");
                    }
                    return null;
                }

            },
            {
                "data": "productCode",
            },
            {
                "data": "productName",
            },
            {
                "data": "syncDate",
                render: function (data, type, row, meta) {
                    if (data != null) {
                        return new Date(Date.parse(data)).Format("yyyy-MM-dd hh:mm:ss");
                    }
                    return null;
                }

            },
            {
                "data": "finishDate",
                render: function (data, type, row, meta) {
                    if (data != null) {
                        return new Date(Date.parse(data)).Format("yyyy-MM-dd hh:mm:ss");
                    }
                    return null;
                }

            },
            {
                "data": "closerName",
            },
            {
                "data": "closeDate",
                render: function (data, type, row, meta) {
                    if (data != null) {
                        return new Date(Date.parse(data)).Format("yyyy-MM-dd hh:mm:ss");
                    }
                    return null;
                }
            },
            {
                "data": "pluginStatus",
                render: function (data, type, row, meta) {
                    var str ='';
                    if(data == 1){
                        str="未分配";
                    }else if(data == 2){
                        str="分配中";
                    }else if(data == 3){
                        str="分配完";
                    }else if(data == 4){
                        str="已完成";
                    }
                    return str;
                }
            },
            {
                "data": null,
                defaultContent:"",
                className : "td-operation"

            },
            {
                "data": "id",
                "sClass": "center",
                "visible":false
            }
        ],
        columnDefs : [ {
            targets : 0,
            "orderable" : false
        } ,
            {
                targets : 15,
                "orderable" : false
            }
        ],
        "order" : [ [ 1, 'desc' ],[14,'asc']],
        "createdRow": function ( row, data, index ) {
            var $btnEdit = $('<button type="button" class="btn btn-small btn-primary btn-edit">编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;辑</button>');
            $('td', row).eq(15).append($btnEdit);
            var $btnDel = $('<button type="button" class="btn btn-small btn-danger btn-del">删&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;除</button>');
            $('td', row).eq(15).append($btnEdit).append($btnDel);
            var $btnDistribute = $('<button type="button" class="btn btn-small btn-primary btn-distribute">分配任务</button>');
            $('td', row).eq(15).append($btnEdit).append($btnDel).append($btnDistribute);

        },
        "drawCallback": function( settings ) {
            //第一列序号
            this.api().column(0).nodes().each(function(cell,i){cell.innerHTML=i+1;})
        }
    })).api();

    $(".datetime").datetimepicker({
        format: "yyyy-mm-dd hh:ii:ss",//设置时间格式，默认值: 'mm/dd/yyyy'
        // todayBtn : true,//是否在底部显示“今天”按钮
        autoclose : true,//当选择一个日期之后是否立即关闭此日期时间选择器
        todayHighlight : true,//是否高亮当前时间
        keyboardNavigation : true,//是否允许键盘选择时间
        language : 'zh-CN',//选择语言，前提是该语言已导入
        forceParse : true//当选择器关闭的时候，是否强制解析输入框中的值。也就是说，当用户在输入框中输入了不正确的日期，选择器将会尽量解析输入的值，并将解析后的正确值按照给定的格式format设置到输入框中
    });
    //显示隐藏的小框
    $('#showcol').click(function () {
        $('.showul').toggle();

    })
    //显示隐藏列
    $('.toggle-vis').on('change', function (e) {
        e.preventDefault();
        console.log($(this).attr('data-column'));
        var column = _table.column($(this).attr('data-column'));
        column.visible(!column.visible());
    });

    $("#btn-add").click(function(){
        counterManage.addItemInit();
    });
//模糊查询
    $("#btn-simple-search").click(function(){
        counterManage.fuzzySearch = true;

        //reload效果与draw(true)或者draw()类似,draw(false)则可在获取新数据的同时停留在当前页码,可自行试验
     _table.ajax.reload();
//      _table.draw(false);
//         _table.draw();
    });
//高级查询
    $("#btn-advanced-search").click(function(){
        counterManage.fuzzySearch = false;
        _table.ajax.reload();
        // _table.draw();
    });

    $("#btn-save-add").click(function(){
        counterManage.addItemSubmit();
    });

    $("#btn-save-edit").click(function(){
        counterManage.editItemSubmit();
    });

    $("#btn-save-distribute").click(function(){
        counterManage.distributeItemSubmit();
    });
    //行点击事件
    $("tbody",$table).on("click","tr",function(event) {
        $(this).addClass("active").siblings().removeClass("active");
        //获取该行对应的数据
        var item = _table.row($(this).closest('tr')).data();
        counterManage.currentItem = item;
        // counterManage.showItemDetail(item);
    });

    $table.on("change",":checkbox",function() {
        if ($(this).is("[name='cb-check-all']")) {
            //全选
            $(":checkbox",$table).prop("checked",$(this).prop("checked"));
        }else{
            //一般复选
            var checkbox = $("tbody :checkbox",$table);
            $(":checkbox[name='cb-check-all']",$table).prop('checked', checkbox.length == checkbox.filter(':checked').length);
        }
    }).on("click",".td-checkbox",function(event) {
        //点击单元格即点击复选框
        !$(event.target).is(":checkbox") && $(":checkbox",this).trigger("click");
    }).on("click",".btn-edit",function() {
        //点击编辑按钮
        var item = _table.row($(this).closest('tr')).data();
        $(this).closest('tr').addClass("active").siblings().removeClass("active");
        counterManage.currentItem = item;
        counterManage.editItemInit(item);
    }).on("click",".btn-del",function() {
        //点击删除按钮
        var item = _table.row($(this).closest('tr')).data();
        $(this).closest('tr').addClass("active").siblings().removeClass("active");
        counterManage.deleteItem([item]);
    }).on("click",".btn-distribute",function() {
        //点击任务分配按钮
        var item = _table.row($(this).closest('tr')).data();
        $(this).closest('tr').addClass("active").siblings().removeClass("active");
        counterManage.currentItem = item;
        counterManage.distributeItemInit(item);
    });

    $("#toggle-advanced-search").click(function(){
        $("i",this).toggleClass("fa-angle-double-down fa-angle-double-up");
        $("#div-advanced-search").slideToggle("fast");
    });

    $("#btn-info-content-collapse").click(function(){
        $("i",this).toggleClass("fa-minus fa-plus");
        $("span",this).toggle();
        $("#user-view .info-content").slideToggle("fast");
    });

    $("#btn-view-edit").click(function(){
        counterManage.editItemInit(counterManage.currentItem);
    });

    $(".btn-cancel").click(function(){
        // counterManage.showItemDetail(counterManage.currentItem);
        $("#user-add").hide();
        $("#user-edit").hide();
        $("#user-distribute").hide();
    });

};
var counterManage = {
    currentItem : null,
    fuzzySearch : true,
    getQueryCondition : function(data) {
        var param = {};
        //组装排序参数
        if (data.order&&data.order.length&&data.order[0]) {
            switch (data.order[0].column) {
                case 1:
                    param.orderColumn = "orderCode";
                    break;
                case 2:
                    param.orderColumn = "whseCode";
                    break;
                case 3:
                    param.orderColumn = "whseName";
                    break;
                case 4:
                    param.orderColumn = "orderType";
                    break;
                case 5:
                    param.orderColumn = "productCount";
                    break;
                case 6:
                    param.orderColumn = "productUnit";
                    break;
                case 7:
                    param.orderColumn = "deadline";
                    break;
                case 8:
                    param.orderColumn = "productCode";
                    break;
                case 8:
                    param.orderColumn = "productName";
                    break;
                case 8:
                    param.orderColumn = "syncDate";
                    break;
                case 8:
                    param.orderColumn = "finishDate";
                    break;
                case 8:
                    param.orderColumn = "closerName";
                    break;
                case 8:
                    param.orderColumn = "closeDate";
                    break;
                default:
                    param.orderColumn = "pluginStatus";
                    break;
            }
            param.orderDir = data.order[0].dir;
        }
        //组装查询参数
        param.fuzzySearch = counterManage.fuzzySearch;
        if (counterManage.fuzzySearch) {
            param.fuzzy = $("#fuzzy-search").val();
            param.fuzzySearch=true;
        }else{
            param.orderCode = $("#orderCode-search").val();
            param.whseCode = $("#whseCode-search").val();
            param.productCode = $("#productCode-search").val();
            param.deadline=$("#deadline-search").val();
            param.orderType = $("#orderType-search").val();
            param.pluginStatus=$("#pluginStatus-search").val();
            param.fuzzySearch=false;
        }
        //组装分页参数
        param.startIndex = data.start;
        param.pageSize = data.length;
        param.username=$("#username").text();
        return param;
    },
    showItemDetail : function(item) {
        $("#order-distribute").show().siblings(".info-block").hide();
        if (!item) {
            $("#order-distribute .prop-value").text("");
            return;
        }
        // $("#name-view").text(item.name);
        // $("#position-view").text(item.position);
        // $("#salary-view").text(item.salary);
        // $("#start-date-view").text(item.start_date);
        // $("#office-view").text(item.office);
        // $("#extn-view").text(item.extn);
        // $("#role-view").text(item.role?"管理员":"操作员");
        // $("#status-view").text(item.status?"在线":"离线");
    },
    addItemInit : function() {
        $("#form-add")[0].reset();

        $("#user-add").show().siblings(".info-block").hide();
    },
    editItemInit : function(item) {
        if (!item) {
            return;
        }
        $("#form-edit")[0].reset();
        $("#id_edit").val(item.id);
        $("#orderCode-edit").val(item.orderCode);
        $("#productCount-edit").val(item.productCount);
        $("#productCode-edit").val(item.productCode);
        $("#productUnit-edit").val(item.productUnit);
        $("#productName-edit").val(item.productName);
        $("#deadline-edit").val(new Date(Date.parse(item.deadline)).Format("yyyy-MM-dd hh:mm:ss"));
        $("#whseCode_edit").val(item.whseCode);
        $("#orderType-edit").val(item.orderType);
        $("#user-edit").show().siblings(".info-block").hide();
    },
    distributeItemInit : function(item) {
        if (!item) {
            return;
        }
        $("#form-distribute")[0].reset();
        $("#id_distribute").val(item.id);
        $("#orderCode-distribute").val(item.orderCode);
        $("#productCount-distribute").val(item.productCount);
        $("#productCode-distribute").val(item.productCode);
        $("#productUnit-distribute").val(item.productUnit);
        $("#productName-distribute").val(item.productName);
        $("#deadline-distribute").val(new Date(Date.parse(item.deadline)).Format("yyyy-MM-dd hh:mm:ss"));
        $("#whseCode_distribute").val(item.whseCode);
        $("#orderType-distribute").val(item.orderType);
        // 查询仓库的柜台，并按照柜台来平均分配
        var param = {}
        param.whseCode=item.whseCode;
        param.orderDir = "counterCode";
        $("#table-order-task").dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION,{
            "ajax":function(data,callback,settings){
                $.ajax({
                    url: "/order/getWhseCoutners",
                    data: param,
                    type: "POST",
                    dataType: "json",
                    success: function (result) {
                        if(result.code == 0){
                            console.log("test")
                            return;
                        }
                        var returnData = {};
                        returnData.data = result.data;
                        callback(returnData);
                        $("#user-distribute").show().siblings(".info-block").hide();
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        alert("查询失败");
                    }
                })
            },
            "columns": [
                {
                    "data":"id",
                    "sWidth":"3%",
                    class:"td-checkbox",
                    "visible":false
                },
                {
                    "data":null,
                    "sWidth":"5%"
                },
                {
                    "data": "whseCode",
                    "sWidth":"20%"

                },
                {
                    "data": "counterCode",
                    "sWidth":"20%"

                },
                {
                    "data": "counterName",
                    "sWidth":"20%"
                },
                {
                    "data": "counterStatus",
                    "sWidth":"10%",
                    render: function (data, type, row, meta) {
                        return (data == 1?"空闲":"<i class=\"fa fa-male\"></i> 使用中...");
                    }
                },
                {
                    "data": null,
                    "sWidth":"20%",
                    "defaultContent":"<input class='taskCount' min='0' type='number'/>",

                },
                {
                    "data": "id",
                    "sClass": "center",
                    "visible":false,
                    class:"td-counter-id",
                }

            ],
            columnDefs : [ {
                targets : 0,
                "orderable" : false,
                "render": function (data, type, row, meta) {
                    return '<input type="checkbox" value="'+ data + '" name="id"/>';
                }},
                {
                targets : 1,
                "orderable" : false
                }
            ],
             "order" : [ [ 1, 'asc' ],[3,'asc']],
            "drawCallback": function( settings ) {
                //渲染完毕后的回调
                //清空全选状态
                 $(":checkbox[name='allChecked']",$("#table-container")).prop('checked', false);
                //第一列序号
                this.api().column(1).nodes().each(function(cell,i){cell.innerHTML=i+1;})
                //将生成数量平均分配给柜台，不在使用中的柜台
                // $(".taskCount").val(500);
            }
        })).api();

        // var  $table=$("#table-order-task");
        // $table.on("change",":checkbox",function() {
        //     if ($(this).is("[name='allChecked']")) {
        //         //全选
        //         $(":checkbox",$table).prop("checked",$(this).prop("checked"));
        //
        //
        //
        //     }else{
        //         //一般复选
        //         var checkbox = $("tbody :checkbox",$table);
        //         $(":checkbox[name='allChecked']",$table).prop('checked', checkbox.length == checkbox.filter(':checked').length);
        //         // var selectRecorder = $table.rows(".selected").data();
        //     }
        // }).on("click",".td-checkbox",function(event) {
        //     //点击单元格即点击复选框
        //     !$(event.target).is(":checkbox") && $(":checkbox",this).trigger("click");
        // })

    },
    addItemSubmit : function() {
        var param = {};
        param.whseCode = $("#whseCode_add").val();
        param.orderCode = $("#orderCode-add").val();
        param.productCode = $("#productCode-add").val();
        param.productCount = $("#productCount-add").val();
        param.productName = $("#productName-add").val();
        param.productUnit = $("#productUnit-add").val();
        param.deadline = $("#deadline-add").val();
        param.orderType = $("#orderType-add").val();
        param.pluginStatus = 1;
        //如若柜台编码和仓库编码为空，则提示必填
        if(param.orderCode == "" || $.trim(param.orderCode).length == 0 ||
            param.whseCode== "" || $.trim(param.whseCode).length == 0   ||
            param.productCode== "" || $.trim(param.productCode).length == 0   ||
            param.productCount== "" || $.trim(param.productCount).length == 0   ||
            param.productUnit== "" || $.trim(param.productUnit).length == 0
        ){
            alert("*内容不能为空！");
        }else{
             $.ajax({
                 url:"/order/save",
                 data:param,
                 type:"POST",
                 success:function(data){
                     // alert("保存成功，刷新表格");
                     $('#table-counter').DataTable({"bRetrieve": true}).ajax.reload();
                 }
             })
        }

    },
    editItemSubmit : function() {
        // $.dialog.tips('保存当前编辑用户');
        var param = {};
        param.id = $("#id_edit").val();
        param.whseCode = $("#whseCode_edit").val();
        param.orderCode = $("#orderCode-edit").val();
        param.productCode = $("#productCode-edit").val();
        param.productCount = $("#productCount-edit").val();
        param.productName = $("#productName-edit").val();
        param.productUnit = $("#productUnit-edit").val();
        param.deadline = $("#deadline-edit").val();
        param.orderType = $("#orderType-edit").val();
        //如若柜台编码和仓库编码为空，则提示必填
        if(param.orderCode == "" || $.trim(param.orderCode).length == 0 ||
            param.whseCode== "" || $.trim(param.whseCode).length == 0   ||
            param.productCode== "" || $.trim(param.productCode).length == 0   ||
            param.productCount== "" || $.trim(param.productCount).length == 0   ||
            param.productUnit== "" || $.trim(param.productUnit).length == 0){
            alert("*内容不能为空！");
        }else{
            $.ajax({
                url:"/order/save",
                data:param,
                type:"POST",
                success:function(data){
                    // alert("保存成功，刷新表格");
                    $('#table-counter').DataTable({"bRetrieve": true}).ajax.reload();
                }
            })
        }
    },
    distributeItemSubmit: function(){
        // alert("text")
        var param = {};
        var sum = 0;
        var taskCounts = $(".taskCount");
        var taskIds =$(".td-counter-id");
        var objArray  = [];
        var count = $("#productCount-distribute").val();
        param.id = $("#id_distribute").val();
        param.orderCode = $("#orderCode-distribute").val();
        param.productCount = count;
        param.productCode = $("#productCode-distribute").val();
        param.productUnit = $("#productUnit-distribute").val();
        param.productName = $("#productName-distribute").val();
        param.deadline = $("#deadline-distribute").val();
        param.whseCode = $("#whseCode_distribute").val();
        param.orderType = $("#orderType-distribute").val();
        for (var k=0;k<taskCounts.size();k++) {
            var whseCode =$("#table-order-task")[0].rows[k+1].cells[2].innerHTML;
            var value = taskCounts[k].value;
            var obj = { key : whseCode,value:value };
            objArray.push(obj);
            sum +=Number(value);
        }
        param.recorders = objArray;

        if(sum <Number(count)){
            $.dialog({
                title:'说明',
                content:'订单任务需要一次分配完成，请重新分配！'
            })

        }else if(sum > Number(count)){
            $.dialog({
                title:'错误',
                content:'分配任务数超过订单数量，请重新分配！'
            })
        }else{
            $.ajax({
                url:"/order/generateTask",
                data:param,
                type:"POST",
                success:function(result){
                    if(result.code == 1){
                        $.dialog({
                            title:'成功',
                            content:'保存成功！'
                        })
                        $("#user-distribute").hide();
                        $('#table-counter').DataTable({"bRetrieve": true}).ajax.reload();
                    }
                }
            })
        }

    },
    deleteItem : function(selectedItems) {
        var param = {};
        param.id = selectedItems[0].id;
        var message;
        if (selectedItems&&selectedItems.length) {
            if (selectedItems.length == 1) {
                message = "确定要删除订单 '"+selectedItems[0].whseCode+"' 吗?";

            }else{
                message = "确定要删除选中的"+selectedItems.length+"项记录吗?";
            }
            $.confirm({
                title: '警告',
                content:message,
                buttons: {
                    formSubmit: {
                        text: '确认',
                        btnClass: 'btn-red',
                        action: function () {
                            $.ajax({
                                url:"/order/delete",
                                data:param,
                                type:"POST",
                                success:function(data){
                                    // alert("保存成功，刷新表格");
                                    $('#table-counter').DataTable({"bRetrieve": true}).ajax.reload();
                                }
                            })
                        }
                    },
                    cancel: {
                        text: '取消',
                        btnClass: 'btn-blue'
                    }
                }

            });
        }else{
            $.alert({
                title: '提示',
                content: '请先选中要操作的行!'
            });
        }
    }
};
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,
        //月份
        "d+": this.getDate(),
        //日
        "h+": this.getHours(),
        //小时
        "m+": this.getMinutes(),
        //分
        "s+": this.getSeconds(),
        //秒
        "q+": Math.floor((this.getMonth() + 3) / 3),
        //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}

/**
 *  仓库编码(名称)
 */
function getWhseCode() {
    $.ajax({
        async: true,
        url: "/counter/getCurUserWhseCode",
        data: {
            "username": $("#username").text()
        },
        type: "POST",
        success: function (data) {
            data.unshift('');
            for (var i = 0; i < data.length; i++) {
                if (i == 0) {
                    str = " <option value='' selected='true'></option> ";
                } else {

                    str += " <option value='" + data[i].whseCode + "' >" + data[i].whseCode + "(" + data[i].whseName + ")</option> ";
                }
            }
            $(".whseCode").html(str);
        }

    });
}

