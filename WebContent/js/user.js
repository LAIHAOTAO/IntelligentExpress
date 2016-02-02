/**
 * Created by ERIC_LAI on 15/11/29.
 */

$(document).ready(function(){
    var packageStatus = ["等待寄出","运送途中","等待收件","已经收件"];
    var barList = ["packageQuery", "receiveQuery", "sendQuery", "modifyInfo", "modifyLogPw", "addressManage"];
    var checkMethod = "packageId";

    // 获取快件ID，填入选择框当中
    $.get("/user",function(data){
        var key;
        $('#packageNo').html("");
        for (key in data) {
            $('#packageNo').append("<option value="+data[key]+">"+data[key]+"</option>");
        }
    });
    $('#queryResult,#sendPhone,#receivePhone').hide();
    $('#modifyBox').hide();

    // 按下 按快件编号查询按键
    $('#packageQuery').css('color', "red").click(function() {
        checkMethod = "packageId";
        $('#sendPhone,#receivePhone').hide();
        $('#queryResult').hide();
        $('#modifyBox').hide();
        $('#queryBox').show();
        $('#record').html("");
        $('#receiveQuery,#sendQuery,#modifyInfo').css('color', "lightseagreen");
        $('#packageId').show();
        $(this).css('color', "red");
    });

    //按下 按收件人查询按键
    $('#receiveQuery').click(function() {
        checkMethod = "receivePhone";
        $('#packageId,#sendPhone').hide();
        $('#queryResult').hide();
        $('#modifyBox').hide();
        $('#queryBox').show();
        $('#record').html("");
        $('#packageQuery,#sendQuery,#modifyInfo').css('color', "lightseagreen");
        $('#receivePhone').show();
        $(this).css('color', "red");
    });

    //按下 按寄件人查询按钮
    $('#sendQuery').click(function() {
        checkMethod = "sendPhone";
        $('#packageId,#receivePhone').hide();
        $('#queryResult').hide();
        $('#modifyBox').hide();
        $('#queryBox').show();
        $('#record').html("");
        $('#packageQuery,#receiveQuery,#modifyInfo').css('color', "lightseagreen");
        $('#sendPhone').show();
        $(this).css('color', "red");
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
                    var pacId = data.queryDto[i].pacId;
                    var senderName = data.queryDto[i].senderName;
                    var sendPhone = data.queryDto[i].sendPhone;
                    var recverName = data.queryDto[i].recverName;
                    var recverPhone = data.queryDto[i].recverPhone;
                    var posterName = data.queryDto[i].posterName;
                    var posterPhone = data.queryDto[i].posterPhone;
                    var pacStatus = data.queryDto[i].pacStatus;
                    $('#record').append("<tr>" +
                        "<td>"+pacId+"</td>" +
                        "<td>"+senderName+"</td>" +
                        "<td>"+sendPhone+"</td>" +
                        "<td>"+recverName+"</td>" +
                        "<td>"+recverPhone+"</td>" +
                        "<td>"+posterName+"</td>" +
                        "<td>"+posterPhone+"</td>" +
                        "<td>"+packageStatus[pacStatus]+"</td>" +
                        "</tr>");
                }
            }else {
                $('#resultMsg').text("没有查询到相关的数据");
            }
            $('#queryResult').show();
        });
    });

    //按下 修改个人信息按键
    $('#modifyInfo').click(function(){
        $('#queryBox').hide();
        $('#modifyBox').show();
        $('#packageQuery,#sendQuery,#receiveQuery').css('color', "lightseagreen");
        $(this).css('color', "red");
        $.get("/user/modify",function(data){
            var personId = data.personId;
            var personNm = data.name;
            var personPh = data.phone;
            var personLogNm = data.logNm;
            var personLogPw = data.logPw;
            $('#personId').attr("value", personId);
            $('#personNm').attr("value", personNm);
            $('#phone').attr("value", personPh);
            $('#logNm').attr("value", personLogNm);
        });
    });

    function highLightBar(){
        for (var x in barList) {
            var id = "#" + x;
            $(id).css('color', "lightseagreen");
        }
    }

});