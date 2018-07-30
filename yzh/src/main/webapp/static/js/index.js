$(function () {
    $('#myCarousel').carousel('cycle');

    $("#login-btn").click(function () {
        var form = $("#loginForm");
        var username = form.find("input[name='username']").val();
        var password = form.find("input[name='password']").val();
        if (!username || username === '') {
            alert("请输入用户名");
            return;
        }
        if (!password || password === '') {
            alert("请输入密码");
            return;
        }
        $.post(form.attr("action"), form.serialize(), function (data) {
            alert(data.msg);
            if (data.code == 200) {
                setTimeout(function () {
                    location.reload();
                }, 1000);
            }
        });
    });

    $("#register-btn").click(function () {
        var form = $("#registerForm");
        var username = form.find("input[name='username']").val();
        var password = form.find("input[name='password']").val();
        var rePassword = form.find("input[name='rePassword']").val();
        if (!username || username === '') {
            alert("请输入用户名");
            return;
        }
        if (!password || password === '') {
            alert("请输入密码");
            return;
        }
        if (!rePassword || rePassword === '') {
            alert("请重复密码");
            return;
        }
        if (password != rePassword) {
            alert("两次密码不一致");
            return;
        }
        $.post(form.attr("action"), form.serialize(), function (data) {
            alert(data.msg);
            if (data.code == 200) {
                setTimeout(function () {
                    location.reload();
                }, 1000);
            }
        });
    });

    $("#reset-btn").click(function () {
        var form = $("#resetForm");
        var oldPassword = form.find("input[name='oldPassword']").val();
        var password = form.find("input[name='password']").val();
        var rePassword = form.find("input[name='rePassword']").val();
        if (!oldPassword || oldPassword === '') {
            alert("请输入原始密码");
            return;
        }
        if (!password || password === '') {
            alert("请输入密码");
            return;
        }
        if (!rePassword || rePassword === '') {
            alert("请重复密码");
            return;
        }
        if (password != rePassword) {
            alert("两次密码不一致");
            return;
        }
        $.post(form.attr("action"), form.serialize(), function (data) {
            alert(data.msg);
            if (data.code == 200) {
                setTimeout(function () {
                    location.reload();
                }, 1000);
            }
        });
    });
    
});
