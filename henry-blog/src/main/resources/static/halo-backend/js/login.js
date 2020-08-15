var halo = new $.halo();
$(document).ready(function () {
    $("#login-name").val(localStorage.getItem("loginName"));
});

function btn_login() {
    $('#btn-login').button('loading');
    var name = $("#login-name").val();
    var pwd = $("#login-pwd").val();
    if (name == "" || pwd == "") {
        tipsByQiao("用户名和密码为空！", 'danger', 2000);
        $('#btn-login').button('reset');
    } else {
        $.ajax({
            type: 'POST',
            url: '/admin/getLogin',
            async: false,
            data: {
                'loginName': name,
                'loginPwd': pwd
            },
            success: function (data) {
                localStorage.setItem('loginName', $("#login-name").val());
                if (data.code == 1) {
                    halo.showMsgAndRedirect(data.msg, 'success', 1000, '/admin');
                } else {
                    tipsByQiao(data.msg, 'danger', 2000);
                    $('#btn-login').button('reset');
                }
            }
        })
    };
};

$(document).keydown(function (event) {
    if (event.keyCode == 13) {
        btn_login();
    };
});