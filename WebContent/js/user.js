/**
 * Created by ERIC_LAI on 15/11/29.
 */

$(document).ready(function(){
    var packageStatus = ["等待寄出","运送途中","等待收件","已经收件"];
    var genderStatus = ["男","女"];
    var checkMethod = "packageId";

    window.history.pushState(null, null, "/user");

    // 获取快件ID，填入选择框当中
    $.get("/user/pacId",function(data){
        var key;
        $('#packageNo').html("");
        for (key in data) {
            $('#packageNo').append("<option value="+data[key]+">"+data[key]+"</option>");
        }
    });

    //隐藏 查询表格 寄件手机查询 收件手机查询 修改个人资料 修改登录密码 个人地址管理
    $('#queryResult,#sendPhone,#receivePhone,#modifyBox, #modifyPw, #addressBox, #addAddress').hide();

    // 按下 按快件编号查询按键
    $('#packageQuery').css('color', "red").click(function() {
        checkMethod = "packageId";
        // 隐藏无关元素
        $('.query, #modifyBox, #modifyPw, #addressBox, #queryResult, #addAddress').hide();
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
        $('.query, #modifyBox, #modifyPw, #addressBox, #queryResult, #addAddress').hide();
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
        $('.query, #modifyBox, #modifyPw, #addressBox, #queryResult, #addAddress').hide();
        // 显示本身
        $('#queryBox').show();
        $('#sendPhone').show();
        // 清空表格 bar颜色重置
        reSetColor(this);
    });

    //按下 修改个人信息按键
    $('#modifyInfo').click(function() {
        $('#queryBox, #modifyPw, #addressBox, #queryResult, #addAddress').hide();
        $('#modifyBox').show();
        reSetColor(this);
        $.get("/user/modify",function(data) {
            $('#personId').attr("value", data.personId);
            $('#personNm').attr("value", data.name);
            $('#phone').attr("value", data.phone);
            $('#logNm').attr("value", data.logNm);
            var gender = data.gender;
            if (gender == "0") {
                $('#male').attr("checked","checked");
            }else {
                $('#female').attr("checked","checked");
            }

        });
    });

    //按下 修改登录密码按键
    $('#modifyLogPw').click(function() {
        $('#queryBox, #modifyBox, #addressBox, #queryResult, #addAddress').hide();
        $('#modifyPw').show();
        reSetColor(this);
    });



    //按下 个人地址管理按键
    $('#addressManage').click(function() {
        $('#queryBox, #modifyBox, #modifyPw, #queryResult, #addAddress').hide();
        $('#addressBox').show();
        reSetColor(this);
        var personId = $('#personId').val();
        var data = {personId: personId};
        var count = 0;
        $.get("/user/address", data, function (data) {
            $('#addressRecord').html("");
            if (data.result == "success") {
                var len = data.address.length;
                for (var i = 0; i < len; i++) {
                    count++;
                    $('#addressRecord').append("<tr>"
                        +"<td>"+count+"</td>"
                        +"<td>"+data.address[i].addrInfo+"</td>"
                        +"<td>"+'<a href="JavaScript:void(0);" class="delete">'+"<input type='hidden' name='addrId'"+"value="+data.address[i].addrId+">"+"删除"+'</a>'+"</td>"+
                        "</tr>"
                    );
                }
            }
        });
    });

    //按下 删除地址按键
    $(document).on('click', '.delete', function() {
        var addrId = $($(this).children()).attr("value");
        var choseRow = $(this).parents("tr");
        var data = {addrId: addrId};
        layer.confirm('您确定要删除这个地址吗？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $.post("/user/addrDelete", data, function(data) {
                if (data.result == "success") {
                    layer.alert('地址删除成功');
                    $(choseRow).remove();
                } else {
                    layer.alert('地址删除失败, 请重新尝试');
                }
            });
        }, function(){});
    });

    //按下 确认修改信息按键
    $('#sureModify').click(function(){
        //询问框
        layer.confirm('您确定要修改个人信息吗？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            var personId = $('#personId').val();
            var name = $('#personNm').val();
            var phone = $('#phone').val();
            var logNm = $('#logNm').val();
            var gender = $('[name="gender"]:checked').val();
            var data = {personId:personId, name:name, phone:phone, logNm:logNm, gender:gender};
            $.post("user/modifyInfo", data, function(data){
                if (data.result == "success") {
                    layer.alert('个人信息修改成功');
                } else {
                    layer.alert('信息修改失败, 请重新尝试');
                }
            });
        }, function(){});
    });

    //按下 确认修改密码按键
    $('#changePw').click(function() {
        var personId = $('#personId').val();
        var oldPw = $("#oldPw").val();
        var password = $("#newPw").val();
        var againPw = $("#againNewPw").val();
        if (oldPw != null || password != null || againPw != null) {
            //询问框
            layer.confirm('您确定要修改登录密码吗？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                if (password == againPw) {
                    var data = {personId:personId, oldPw:oldPw, newPw: password};
                    $.post("user/modifyPw", data, function (data) {
                        if (data.result == "success") {
                            layer.alert('登录密码修改成功');
                        } else if (data.result == "oldFalse") {
                            layer.alert('旧密码输入错误, 请核对后重新输入');
                        } else {
                            layer.alert('密码修改失败, 请重新尝试');
                        }
                    });
                } else {
                    layer.alert("两次输入的新密码不一致, 请核对后重新输入");
                    $('#newPw, #againNewPw').attr("value", "");
                }
            }, function () {
            });
        } else {
            layer.alert("以上三个输入框不可以为空, 请核对后重新输入");
        }
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