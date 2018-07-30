$(function () {
    $(".closeThis").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });
});

function empty(str) {
    return !str || str == null || str === "";
}

//  删除
function del(url, id, objName) {
    layer.confirm('确定删除该' + objName + '?', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        $.ajax({
            url: url,
            type: 'post',
            cache: false,
            data: {"id": id},
            dataType: 'json',
            success: function (data) {
                layer.msg(data.msg);
                setTimeout(function () {
                    location.reload();
                }, 1000);
            }
        })
    });
};

//  时间格式化
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,                 //月
        "d+": this.getDate(),                    //日
        "H+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季
        "S": this.getMilliseconds()             //毫秒
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
};