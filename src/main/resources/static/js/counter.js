/**
 * add counter
 */
function addCounter() {
    $.get("/counter/add",function(data,status){
        if(data == 1){
            alert("添加成功")
        }else{
            alert("添加失败")
        }
    })
}