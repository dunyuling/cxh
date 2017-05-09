$(document).ready(function () {
    $("p[id^=need_]").each(function () {
        var type = $(this).html();
        type = formatType(type);
        $(this).html("需求：" + type);
    })
});

function formatType(type) {
    switch (type) {
        case "IT_0":
            return "交强险";
        case "IT_1":
            return "机动车辆损失险";
        case "IT_2":
            return "第三者责任险";
        case "IT_3":
            return "盗抢险";
        case "IT_4":
            return "车上人员责任险";
        case "IT_5":
            return "车身划痕损失险";
        case "IT_6":
            return "玻璃单独破碎险";
        case "IT_7":
            return "自燃损失险";
        case "IT_8":
            return "发动机特别损失险";
        case "IT_9":
            return "不计免赔率特约损失险";
    }
}