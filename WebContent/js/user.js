/**
 * Created by ERIC_LAI on 15/11/29.
 */

$(document).ready(function(){
    var packageStatus = ["等待寄出","运送途中","等待收件","已经收件"];
    var checkMethod = "packageId";

    // 获取快件ID，填入选择框当中
    $.get("/user",function(data){
        var key;
        $('#packageNo').html("");
        for (key in data) {
            $('#packageNo').append("<option value="+data[key]+">"+data[key]+"</option>");
        }
    });
    //隐藏 查询表格 寄件手机查询 收件手机查询 修改个人资料 修改登录密码
    $('#queryResult,#sendPhone,#receivePhone,#modifyBox, #modifyPw').hide();

    // 按下 按快件编号查询按键
    $('#packageQuery').css('color', "red").click(function() {
        checkMethod = "packageId";
        // 隐藏无关元素
        $('.query, #modifyBox, #modifyPw').hide();
        // 显示本身
        $('#queryBox').show();
        $('#packageId').show();
        // 清空表格 bar颜色重置
        reSetColor(this);
    });

    //按下 按收件人查询按键
    $('#receiveQuery').click(function() {
        checkMethod = "receivePhone";
        // 隐藏无关元素
        $('.query, #modifyBox, #modifyPw').hide();
        // 显示本身
        $('#queryBox').show();
        $('#receivePhone').show();
        // 清空表格 bar颜色重置
        reSetColor(this);
    });

    //按下 按寄件人查询按钮
    $('#sendQuery').click(function() {
        checkMethod = "sendPhone";
        // 隐藏无关元素
        $('.query, #modifyBox, #modifyPw').hide();
        // 显示本身
        $('#queryBox').show();
        $('#sendPhone').show();
        // 清空表格 bar颜色重置
        reSetColor(this);
    });

    //按下 修改个人信息按键
    $('#modifyInfo').click(function(){
        $('#queryBox').hide();
        $('#modifyBox').show();
        reSetColor(this);
        $.get("/user/modify",function(data){
            $('#personId').attr("value", data.personId);
            $('#personNm').attr("value", data.name);
            $('#phone').attr("value", data.phone);
            $('#logNm').attr("value", data.logNm);
        });
    });

    //按下 修改登录密码按键
    $('#modifyLogPw').click(function(){
        $('#queryBox, #modifyBox').hide();
        $('#modifyPw').show();
        reSetColor(this);

    });

    //按下 确认修改信息按键
    $('#sureModify').click(function(){


    });

    //按下 确认修改密码按键
    $('#changePw').click(function(){


    });

    //按下 开始查询按键
    $('#queryButton').click(function() {
        $('#record').html("");
        if (checkMethod == "packageId") {
            var packageNo = $('#packageNo').val();
            var data = {packageNo: packageNo,checkMethod: checkMethod};
        } else if (checkMethod == "sendPhone") {
            var sendPhone = $('[name=sendPhone]').attr("value");
            var data = {sendPhone: sendPhone,receivePhone: null,checkMethod: checkMethod};
        }else if (checkMethod == "receivePhone") {
            var receivePhone = $('[name=receivePhone]').attr("value");
            var data = {receivePhone: receivePhone, sendPhone: null,checkMethod: checkMethod};
        }
        $.post("/user/query", data, function(data){
            if (data.result == "success") {
                var len = data.queryDto.length;
                for (var i = 0; i < len; i++) {
                    $('#record').append("<tr>" +
                        "<td>"+data.queryDto[i].pacId+"</td>" +
                        "<td>"+data.queryDto[i].senderName+"</td>" +
                        "<td>"+data.queryDto[i].sendPhone+"</td>" +
                        "<td>"+data.queryDto[i].recverName+"</td>" +
                        "<td>"+data.queryDto[i].recverPhone+"</td>" +
                        "<td>"+data.queryDto[i].posterName+"</td>" +
                        "<td>"+data.queryDto[i].posterPhone+"</td>" +
                        "<td>"+packageStatus[data.queryDto[i].pacStatus]+"</td>" +
                        "</tr>");
                }
            }else {
                $('#resultMsg').text("没有查询到相关的数据");
            }
            $('#queryResult').show();
        });
    });
    function reSetColor(selector) {
        $('#record').html("");
        $('.show_finger').css('color', "lightseagreen");
        $(selector).css('color', "red");
    }
});