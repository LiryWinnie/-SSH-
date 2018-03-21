$(function() {
    //设置时间倒计时
    setCountDown_time();
})
/*时间倒计时*/
var sec = 60,
    min = 10;
var idt;
var format = function(str) {
    if(parseInt(str) < 10) {
        return "0" + str;
    }
    return str;
};
function setCountDown_time() {
    idt = window.setInterval("ls();", 1000);
}
function ls() {
    sec--;
    if(sec == 0) {
        if(parseInt(min) == 0 && parseInt(sec) == 0) {
            document.getElementById("countdown_time").innerHTML = format(min) + ":" + format(sec);
            window.clearInterval(idt);
            alert('考试时间已到，试卷已提交，感谢您的使用！');
        } else {
            min--;
            sec = 59;
        }
    }
    document.getElementById("countdown_time").innerHTML = format(min) + ":" + format(sec);
}
//以上JS修改完成后