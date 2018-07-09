$.ajaxSetup({headers:{'X-CSRF-TOKEN':$("#csrf_token").attr("content")}});

 $(function(){
    getWhseCode();
    $("#search").change(search());
});

/**
 *  仓库编码(名称)
 */
function getWhseCode(){
    $.ajax({
        async:true,
        url:"/counter/getCurUserWhseCode",
        data:{
            "username":$("#username").text()
             },
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
function clearSelect(){
    // console.log("test");
    $("#whseCode").val("");
    window.location.reload("/counter");

}
function search(){
    // console.log("search");
    $.ajax({
        async:true,
        url:"/counter/search",
        data:{
            "whseCode":$("#search option:selected").val()
        },
        type:"POST",
        success:function(data){
            alert("Test search");
        }

    });

}

