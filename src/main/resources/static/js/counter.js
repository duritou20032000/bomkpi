$.ajaxSetup({headers:{'X-CSRF-TOKEN':$("#csrf_token").attr("content")}});

/**
 * 联动仓库编码和名称
 */
 $(function(){
     $("#couner").click();
    getWhseCode();
    getWhseName();
    $("#whseCode").change(function(){getWhseName();})

});

/**
 *  仓库编码
 */
function getWhseCode(){
    $.ajax({
        async:false,
        url:"/counter/getWhseCode",
        dataType:"TEXT",
        type:"POST",
        success:function(data){
           // alert("test for getWhseCode"+data);
            var str = "";
            var list = eval(data);
            list.unshift('');
            for(var i=0;i<list.length;i++){
                if(i==0){
                    str = " <option value='' selected='true'></option> ";
                }else{

                    str +=" <option value='"+list[i].whseCode+"' >"+list[i].whseCode+"</option> ";
                }
            }
            $("#whseCode").html(str);
        }

    });
}
function getWhseName(){
    var val = $("#whseCode").val();
    if(val == null || val == ''){
        var str = " <option value='' selected='true'></option> ";
        $("#whseName").html(str);
    }else{
        $.ajax({
            async:false,
            url:"/counter/getWhseNameByCode/"+val,
            dataType:"TEXT",
            data:{},
            type:"POST",
            success:function(data){
                // alert("test for getWhseCode"+data);
                var str = " <option value='"+data+"' >"+data+"</option> ";
                $("#whseName").html(str);
            }

        });
    }
}



