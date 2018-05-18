var Login = function () {
    return {
        init: function () {
        	/*
        	 * 登陆Form 
        	 */
            $('.login-form').validate({
                errorElement: 'label', //default input error message container
                errorClass: 'help-inline', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    username: {
                        required: true
                    },
                    password: {
                        required: true
                    },
                    remember: {
                        required: false
                    }
                },
                messages: {
                    username: {
                        required: "请输入账号"
                    },
                    password: {
                        required: "请输入密码"
                    }
                },
                invalidHandler: function (event, validator) { //display error alert on form submit   
                    $('.alert-error', $('.login-form')).show();
                },
                highlight: function (element) { // hightlight error inputs
                    $(element).closest('.control-group').addClass('error'); // set error class to the control group
                },
                success: function (label) {
                    label.closest('.control-group').removeClass('error');
                    label.remove();
                },
                errorPlacement: function (error, element) {
                    error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
                },
                submitHandler: function (form) {
                	form.submit();
                }
            });
            $('.login-form input').keypress(function (e) {
                if (e.which == 13) {
                    if ($('.login-form').validate().form()) {
                    	$('.login-form').validate().form().submit();
                    }
                    return false;
                }
            });
            /*
             * 忘记密码校验区域 
             */
            $('.forget-form').validate({
                errorElement: 'label', //default input error message container
                errorClass: 'help-inline', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                    email: {
                        required: true,
                        email: true
                    }
                },
                messages: {
                    email: {
                        required: "Email is required."
                    }
                },
                invalidHandler: function (event, validator) { //display error alert on form submit   
                },
                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.control-group').addClass('error'); // set error class to the control group
                },
                success: function (label) {
                    label.closest('.control-group').removeClass('error');
                    label.remove();
                },
                errorPlacement: function (error, element) {
                    error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
                },
                submitHandler: function (form) {
                    window.location.href = "index.html";
                }
            });
            $('.forget-form input').keypress(function (e) {
                if (e.which == 13) {
                    if ($('.forget-form').validate().form()) {
                        window.location.href = "index.html";
                    }
                    return false;
                }
            });
            $('#forget-password').click(function () {
                jQuery('.login-form').hide();
                jQuery('.forget-form').show();
            });
            $('#back-btn').click(function () {
                jQuery('.login-form').show();
                jQuery('.forget-form').hide();
            });       
            $.backstretch([
                "/image/bg/1.jpg",
                "/image/bg/2.jpg",
                "/image/bg/3.jpg",
                "/image/bg/4.jpg"
            ], {
                fade: 1000,
                duration: 8000
            });
        }
    };
}();