//以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
        $.validator.setDefaults({
            highlight: function (element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            success: function (element) {
                element.closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            errorElement: "span",
            errorPlacement: function (error, element) {
                if (element.is(":radio") || element.is(":checkbox")) {
                    //error.appendTo(element.parent().parent().parent());
                    error.appendTo(element.parent().parent());
                } else {
                    error.appendTo(element.parent().parent());
                }
            },
            errorClass: "help-block m-b-none",
            validClass: "help-block m-b-none"


        });

        var config = {};

        var v_rules = {};
        var v_messages = {};

        function test(){
            $('input,select,textarea').each(function(){
                v_rules[$(this).attr('name')] = $(this).attr('validate');
                // alert(rules['firstname']);
                // rules = $.parseJSON(v_rules);
                v_messages[$(this).attr('name')] = $(this).attr('warning');

                // alert(v_messages['firstname']);
            });

        }






        //以下为官方示例
        $().ready(function () {
            // validate the comment form when it is submitted
            $("#commentForm").validate();

            // validate signup form on keyup and submit
            var icon = "<i class='fa fa-times-circle'></i> ";

            // $('input,select,textarea').each(function(){

            //     var v = null, vm = null;

            //      eval("v = " + $(this).attr("validate"));
            //      if(v)
            //      {
            //         v_rules[$(this).attr('name')] = v;
            //      }

            //       eval("vm = " + $(this).attr("validateMessage"));

            //       if(vm)
            //       {
            //         v_messages[$(this).attr('name')]  = vm;
            //       }

            //     // v_rules[$(this).attr('name')] = $(this).attr('validate');
            //     // alert(rules['firstname']);
            //     // rules = $.parseJSON(v_rules);
            //     // v_messages[$(this).attr('name')] = $(this).attr('warning');

            //     // alert(v_messages['firstname']);
            // });

            // // config['rules'] = v_rules;
            // // config['messages'] =  v_messages;
            // // config = JSON.stringify(config);
            // // config = JSON.parse(config);
            // $("#signupForm").validate({
            //     rules:v_rules,
            //     messages:v_messages

            // });

            $("#signupForm").validate({
                rules: {
                    firstname: "required",
                    lastname: "required",
                    username: {
                        required: true,
                        minlength: 2
                    },
                    password: {
                        required: true,
                        minlength: 5
                    },
                    confirm_password: {
                        required: true,
                        minlength: 5,
                        equalTo: "#password"
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    topic: {
                        required: "#newsletter:checked",
                        minlength: 2
                    },
                    agree: "required"
                },
                messages: {
                    firstname: icon + "请输入你的姓",
                    lastname: icon + "请输入您的名字",
                    username: {
                        required: icon + "请输入您的用户名",
                        minlength: icon + "用户名必须两个字符以上"
                    },
                    password: {
                        required: icon + "请输入您的密码",
                        minlength: icon + "密码必须5个字符以上"
                    },
                    confirm_password: {
                        required: icon + "请再次输入密码",
                        minlength: icon + "密码必须5个字符以上",
                        equalTo: icon + "两次输入的密码不一致"
                    },
                    email: icon + "请输入您的E-mail",
                    agree: {
                        required: icon + "必须同意协议后才能注册",
                        element: '#agree-error'
                    }
                }
            });

            // propose username by combining first- and lastname
            $("#username").focus(function () {
                var firstname = $("#firstname").val();
                var lastname = $("#lastname").val();
                if (firstname && lastname && !this.value) {
                    this.value = firstname + "." + lastname;
                }
            });
        });
