/**
 * Created by ERIC_LAI on 15/11/27.
 */

$(document).ready(function(){

    $('#addBox, #manageBox').hide();
    $('#addBox').show();
    window.history.pushState(null, null, "/sysManager");

    //按下添加邮递员按钮
    $('#addPostman').click(function(){
        $('#manageBox').hide();
        $("[type=text]").val("");
        $('[type=radio]').removeAttr("checked");
        $('#addBox').show();
    });

    //按下新添加邮递员按钮
    $('#addNewPostman').click(function(){
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
                    $("[type=text]").val("");
                    $('[type=radio]').removeAttr("checked");
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
        var data = null;
        $.get("/sysManager/managePostman", data, function (data) {
            $('#postmanRecord').html("");
            if (data.result == "success") {
                var len = data.postman.length;
                for (var i = 0; i < len; i++) {
                    $('#postmanRecord').append("<tr>"
                        +"<td>"+data.postman[i].personId+"</td>"
                        +"<td>"+data.postman[i].name+"</td>"
                        +"<td>"+data.postman[i].phone+"</td>"
                        +"<td>"+'<a href="JavaScript:void(0);" class="delete">'+"<input type='hidden' name='personId'"+"value="+data.postman[i].personId+">"+"删除"+'</a>'+"</td>"
                        +"</tr>"
                    );
                }
            }
        });
    });

    //按下 删除按键
    $(document).on('click', '.delete', function() {
        var personId = $($(this).children()).attr("value");
        var choseRow = $(this).parents("tr");
        var data = {personId: personId};
        layer.confirm('您确定要删除这个邮递员吗？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $.get("/sysManager/deletePostman", data, function(data) {
                if (data.result == "success") {
                    layer.alert('邮递员删除成功');
                    $(choseRow).remove();
                } else {
                    layer.alert('邮递员删除失败, 请重新尝试');
                }
            });
        }, function(){});
    });
});