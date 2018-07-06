$.ajaxSetup({headers:{'X-CSRF-TOKEN':$("#csrf_token").attr("content")}});
var username = $("#username").text();
$(function(){
    $("#counterInfo").click(function(){
        $.ajax({
            type:"get",
            url:"/counter/getCounters",
            data:"username="+username,
            async:false,
            success:function(result) {
                    // $(location).prop('href','https://www.baidu.com');
                    window.location.href="/counter";
                    // $(location).prop('href','/counter');
            }
        })
    });
})
