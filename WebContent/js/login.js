/**
 * Created by Administrator on 2016/1/24.
 */


//public static final String U_P_R_NULL = "4";
//public static final String PASSWORD_MISTAKE = "5";

$(document).ready(function(){
    $('#mistake').text("");
    var result = $('#result').val();
    if (result == 4) {
        $('#mistake').text("用户名或密码或身份信息不能为空");
    }else if (result == 5) {
        $('#mistake').text("用户名或者密码错误，请核对后重新输入");
    }
});