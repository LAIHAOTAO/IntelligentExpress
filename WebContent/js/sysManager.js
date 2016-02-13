/**
 * Created by ERIC_LAI on 15/11/27.
 */

$(document).ready(function(){

    $('#addBox, #manageBox').hide();
    $('#addBox').show();

    //按下添加邮递员按钮
    $('#addPostman').click(function(){
        $('#manageBox').hide();
        $('#addBox').show();
        var name = $('#posterNm').val();
        var phone = $('#posterPh').val();
        var logNm = $('#posterLogNm').val();
        var logPw = $('#posterLogPw').val();
        var gender = $('[name="gender"]:checked').val();
        if (name != null && phone != null && logNm != null && logPw != null && gender != null) {
            var data = {name: name, phone: phone, logNm: logNm, logPw: logPw, gender: gender};
            $.post("/sysManager/addPostman", data, function(data){
                if (data.result == "success") {
                    layer.alert('邮递员添加成功');
                } else {
                    layer.alert('邮递员添加失败, 请重新尝试');
                }
            });
        }else {
            layer.alert("请填完以上的必填信息");
        }
    });

    //按下管理邮递员按钮
    $('#managePostman').click(function(){
        $('#addBox').hide();
        $('#manageBox').show();
    });

});