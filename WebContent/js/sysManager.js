/**
 * Created by ERIC_LAI on 15/11/27.
 */

$(document).ready(function(){

    //按下登出按钮
    $('#logout').click(function(){
       var confirm = window.confirm("您确定要登出吗?");
       if (confirm == true) {
           $.get("login/logout", returnLoginPage);
       }
    });

    //返回登录页面的回调
    function returnLoginPage(response){
        if (response.status == "success") {
            //TODO:实现登出的服务器端清除session操作
            window.location.href = "login";
        } else {
            alert("登出失败,为了您的信息安全请重试.");
        }
    }

    //按下添加邮递员按钮
    $('#addPostman').click(function(){
        $('#manageBox').hide();
        $('#addBox').show();
    });

    //按下管理邮递员按钮
    $('#managePostman').click(function(){
        $('#addBox').hide();
        $('#manageBox').show();
    });

});