$.ajaxSetup({headers:{'X-CSRF-TOKEN':$("#csrf_token").attr("content")}});
$(function(){
    $("#counterInfo").click(function(){
        $.ajax({
            type:"POST",
            url:"/counter/getCounters",
            data:"username="+$("#username").text(),
            async:false,
            success:function(result) {
                    // $(location).prop('href','https://www.baidu.com');
                    window.location.href="/counter";
                    // $(location).prop('href','/counter');
            }
        })
    });
})
