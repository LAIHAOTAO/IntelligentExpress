/**
 * Created by ERIC_LAI on 15/11/29.
 */

$(document).ready(function(){
    var packageStatus = ["等待寄出","运送途中","等待收件","已经收件"];
    var checkMethod = "packageId";
    $.get("/user",function(data){
        var key;
        $('#packageNo').html("");
        for (key in data) {
            $('#packageNo').append("<option value="+data[key]+">"+data[key]+"</option>");
        }
    });
    $('#queryResult,#sendPhone,#receivePhone').hide();
    $('#packageQuery').css('color', "red").click(function() {
        checkMethod = "packageId";
        $('#sendPhone,#receivePhone').hide();
        $('#queryResult').hide();
        $('#record').html("");
        $('#receiveQuery,#sendQuery').css('color', "lightseagreen");
        $('#packageId').show();
        $(this).css('color', "red");
    });
    $('#receiveQuery').click(function() {
        checkMethod = "receivePhone";
        $('#packageId,#sendPhone').hide();
        $('#queryResult').hide();
        $('#record').html("");
        $('#packageQuery,#sendQuery').css('color', "lightseagreen");
        $('#receivePhone').show();
        $(this).css('color', "red");
    });
    $('#sendQuery').click(function() {
        checkMethod = "sendPhone";
        $('#packageId,#receivePhone').hide();
        $('#queryResult').hide();
        $('#record').html("");
        $('#packageQuery,#receiveQuery').css('color', "lightseagreen");
        $('#sendPhone').show();
        $(this).css('color', "red");
    });
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
        });
        $('#queryResult').show();
    });

});