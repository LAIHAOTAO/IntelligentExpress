/**
 * Created by ERIC_LAI on 15/11/28.
 */

function startTime() {
    var today = new Date();
    var y = today.getYear() + 1900;
    var mon = today.getMonth() + 1;
    var d = today.getDate();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    var day = today.getDay();
    var dayName = ["星期天","星期一","星期二","星期三","星期四","星期五","星期六"];
    // add a zero in front of numbers<10
    m = checkTime(m);
    s = checkTime(s);
    $('#showtime').text(y + "年" + mon + "月" + d + "日" + "  ");
    $('#showtime').append(h + ":" + m + ":" + s + "  " + dayName[day]);
    t = setTimeout('startTime()', 1000);
}

function getToday(){
    var today = new Date();

}

function checkTime(i) {
    if (i<10) {
        i="0" + i;
    }
    return i;
}
