// 配置参数
var config = {
    base_path: 'http://127.0.0.1:8080/cs_core/',   // 项目默认路径
    ws_path: '127.0.0.1:8080/cs_core/',
    dlg: {			// 默认对话框属性
        width: '800px',
        height: '480px',
        shade: 0.7,
    },

    thumbnail: {  // 缩略图属性
        width: "100px",
        height: "80px",
    },
    pic_path: 'http://127.0.0.1:8080/cs_core/',
    window: {
        height: 960,
        width: 1280
    }
};

// 验证字符串是否以指定的str开头
String.prototype.startWith = function (str) {
    var reg = new RegExp("^" + str);
    return reg.test(this);
}

// 验证字符串是否以指定的str结尾
String.prototype.endWith = function (str) {
    var reg = new RegExp(str + "$");
    reg.IgnoreCase;
    return reg.test(this);
}

// 验证是否是图片url
function isPicUrl(url) {
    if ('undefined' == url) return false;

    if (url.endWith('.BMP')
        || url.endWith('.bmp')
        || url.endWith('.png')
        || url.endWith('.PNG')
        || url.endWith('.png')
        || url.endWith('.GIF')
        || url.endWith('.jpg')
        || url.endWith('.JPG')
        || url.endWith('.jpeg')
        || url.endWith('.JPEG')) {
        return true;
    }
    return false;
}

function toJSON(_obj) {
    return JSON.parse(JSON.stringify(_obj));
}

$.fn.stringify = function () {
    return JSON.stringify(this);
}


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


$.fn.extend({
    form_validate: function () {
        var _obj = $(this);
        if (!_obj.is('form')) {
            return;
        }

        var v_rules = {}, v_messages = {};

        $('input,select,textarea').each(function () {

            var v = null, vm = null;

            eval("v = " + $(this).attr("validate"));
            if (v) {
                v_rules[$(this).attr('name')] = v;
            }

            eval("vm = " + $(this).attr("validateMessage"));

            if (vm) {
                v_messages[$(this).attr('name')] = vm;
            }

        });

        _obj.validate({
            rules: v_rules,
            messages: v_messages,
            submitHandler: function (form) {
//            	form.submit();
                $(form).ajaxSubmit({
                    type: "post",
                    dataType: "json",
                    success: function (json) {

                        var index = parent.layer.getFrameIndex(window.name);
                        //            	parent.location.reload();

                        $(parent.document.getElementsByTagName('table')).each(function () {
                            _id = $(this).attr('id');
                            if (_id != '' && _id != undefined) {
                                parent.reflush(_id);
                            }
                        });
                        parent.layer.close(index);
                    },
                    error: function (data) {
                        var responseText = data.responseText;
                        if (responseText == 'common/ajax_return') {
                            alert("error");
                        } else {
                            alert(data.responseText);
                        }
                    }
                });
            }
        });
    }

});


$.fn.onlyNum = function () {
    $(this).keypress(function (event) {
        var eventObj = event || e;
        var keyCode = eventObj.keyCode || eventObj.which;
        if ((keyCode >= 48 && keyCode <= 57))
            return true;
        else
            return false;
    }).focus(function () {
        // 禁用输入法
        this.style.imeMode = 'disabled';
    }).bind("paste", function () {
        // 获取剪切板的内容
        var clipboard = window.clipboardData.getData("Text");
        if (/^\d+$/.test(clipboard))
            return true;
        else
            return false;
    });
};

// <summary>
// 限制只能输入字母
// </summary>
// ----------------------------------------------------------------------
$.fn.onlyAlpha = function () {
    $(this).keypress(
        function (event) {
            var eventObj = event || e;
            var keyCode = eventObj.keyCode || eventObj.which;
            if ((keyCode >= 65 && keyCode <= 90)
                || (keyCode >= 97 && keyCode <= 122))
                return true;
            else
                return false;
        }).focus(function () {
        this.style.imeMode = 'disabled';
    }).bind("paste", function () {
        var clipboard = window.clipboardData.getData("Text");
        if (/^[a-zA-Z]+$/.test(clipboard))
            return true;
        else
            return false;
    });
};

// ----------------------------------------------------------------------
// <summary>
// 限制只能输入数字和字母
// </summary>
// ----------------------------------------------------------------------
$.fn.onlyNumAlpha = function () {
    $(this).keypress(
        function (event) {
            var eventObj = event || e;
            var keyCode = eventObj.keyCode || eventObj.which;
            if ((keyCode >= 48 && keyCode <= 57)
                || (keyCode >= 65 && keyCode <= 90)
                || (keyCode >= 97 && keyCode <= 122))
                return true;
            else
                return false;
        }).focus(function () {
        this.style.imeMode = 'disabled';
    }).bind("paste", function () {
        var clipboard = window.clipboardData.getData("Text");
        if (/^(\d|[a-zA-Z])+$/.test(clipboard))
            return true;
        else
            return false;
    });
};


$.extend({
    // 再次确认
    confirm: function (_tips, _submit, _cancle) {
        layer.confirm(_tips, {
                btn: ['确定', '取消'] //按钮
            },
            function (index) {
                if (typeof(_submit) != 'undefined') {
                    _submit();
                }
                layer.close(index);
            },
            function (index) {
                if (typeof(_cancle) != 'undefined') {
                    _cancle();
                }
                layer.close(index);
            }
        )
    },
    tips: function (_tips) {  // 提示信息
        layer.msg(_tips);
    },
    loading: function () {
        layer.msg('加载中', {icon: 4});
    },
    closeAll: function () {  // 关闭所有layer弹出框
        layer.closeAll();
    },
    closeDlg: function (obj) { // 关闭当前打开的dlg
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    },
    showPic: function (url) {    // 图片预览
        var img = new Image();
        img.src = url;  // 预加载图片，计算弹出框的width、height
        if (img.width > config.window.width) {
            img.width = config.window.width;
        }
        if (img.height > config.window.height) {
            img.height = config.window.height;
        }
        img.onload = function () {
            var index = layer.open({
                type: 2,
                title: false,  // 去掉标题
                shadeClose: true,
                fixed: true, //不固定
                skin: 'layui-layer-nobg', //没有背景色
                shade: 0.7,
                area: [img.width + 'px', img.height + 'px'],
                content: url  //pic 路径
            });
        }

        img.onerror = function () {
            $.tips('图片不存在');
        }
    },
    openDlg: function (options) {

        if ('undefined' == typeof(options.width)) {
            options.width = config.dlg.width;
        }

        if ('undefined' == typeof(options.height)) {
            options.height = config.dlg.height;
        }

        if ('undefined' == typeof(options.title)) {
            options.title = false;
        }

        if (isPicUrl(options.url)) {
            $.showPic(options.url);
            return;
        }

        var index = layer.open({
            type: 2,
            area: [options.width, options.height],
            fixed: false, //不固定
            resize: true,
            maxmin: true,
            shade: 0.6,
            title: options.title,
            content: options.url,
        });
//		layer.iframeAuto(index);
    }

});

function queryParams(params) {
    var page = params.offset / params.limit + 1;
    return {page: page, pageSize: params.limit, search: params.search, sort: params.sort, order: params.order};
}

function getSelectedRow(table_id) {
    var data = $('#' + table_id).bootstrapTable('getSelections');
    if (typeof(data) == 'undefined' || data.length < 1) {
        $.tips('未选中行');
        return null;
    } else if (data.length > 1) {
        $.tips('只能选中一行');
        return null;
    }
    return data[0];
}

/**
 * 刷新table數據
 * @param table_id
 */
function reflush(table_id) {
    $('#' + table_id).bootstrapTable('refresh');
}


/**
 * 图片路径 - 暂定
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function defParsePic(value, row, index) {
    return '<img src="' + config.base_path + value + '" width="' + config.thumbnail.width + '" height="' + config.thumbnail.height + '" style="cursor:pointer" onclick="javascript:$.showPic(this.src)" />';
}


/**
 * 关闭当前窗口
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function closeCurrentDlg() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}


var NotifyMsg = function (type, data, url) {

}


function format_time(value, row, index) {
    var date = new Date();
    date.setTime(value);
    Y = date.getFullYear() + '-';
    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' ';
    h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
    m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
    s = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
    return Y + M + D + h + m + s;
}
function format_date(value, row, index) {
    var date = new Date();
    date.setTime(value);
    Y = date.getFullYear() + '-';
    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' ';
    return Y + M + D;
}

