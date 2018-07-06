$.ajaxSetup({headers:{'X-CSRF-TOKEN':$("#csrf_token").attr("content")}});

/**
 * 联动仓库编码和名称
 */
 $(function(){
    getWhseCode();
});

/**
 *  仓库编码(名称)
 */
function getWhseCode(){
    $.ajax({
        async:false,
        url:"/counter/getCurUserWhseCode",
        data:"username="+username,
        type:"POST",
        success:function(data){
            data.unshift('');
            for(var i=0;i<data.length;i++){
                if(i==0){
                    str = " <option value='' selected='true'></option> ";
                }else{

                    str +=" <option value='"+data[i].whseCode+"' >"+data[i].whseCode+"("+data[i].whseName+")</option> ";
                }
            }
            $("#whseCode").html(str);
        }

    });
}
function remove(){
    alert("test");
}

