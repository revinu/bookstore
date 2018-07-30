$(function () {
    tmpDate = new Date();
    date = tmpDate.getDate();
    month = tmpDate.getMonth() + 1;
    year = tmpDate.getYear() + 1900;

    myArray = new Array();
    myArray.push("周日");
    myArray.push("周一");
    myArray.push("周二");
    myArray.push("周三");
    myArray.push("周四");
    myArray.push("周五");
    myArray.push("周六");
    $("time").html(year + "年" + month + "月" + date + "日/" + myArray[tmpDate.getDay()]);
});