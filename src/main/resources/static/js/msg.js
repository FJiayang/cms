/*
/!*
 * the first time to call
 *!/
setTimeout(function () {
    Push();
//  alert("setTimeout called");
}, 200);

setInterval(function () {
    Push();
    //alert("setInterval called");
}, 3000);

//con.showMsg('登录成功');
function Push() {
    $.ajax({
        type: "POST",
        url: "../CheckLoginServlet?dt=" + new Date().getTime(),//why getTime and wont use
        data: {},
        beforeSend: function () {
        },
        success: function (data) {
            var obj = eval("(" + data + ")");//eval使用前要先加括号，才能得到完整的json数据
            if (obj.msg != 0) {
                con.showMsg(obj.msg);
            }
        }
    })
};*/