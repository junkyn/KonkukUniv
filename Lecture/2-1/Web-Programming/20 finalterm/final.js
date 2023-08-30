$(document).ready(function(){
    oath();
    addIMG();
    setInterval(showDue,1000);
    changeBackground();
    function oath(){
        $("#prob1").html("나 <strong>최준규 (202211389)</strong>은 2020년 <strong>웹 프로그레밍 기말고사 온라인 시험을 수행</strong>함에 있어서<br>양심을 걸고 <strong>정직하고 올바른 방법으로 수행함을 약속</strong>합니다.<br>");
    }
    function addIMG(){
        var myimg = $("<img/>").attr("src", "img1.jpg");
        $("#prob1").append(myimg);
    }
    function showDue(){
        var now = new Date();
        var d = 15 - now.getDate();
        var h = d*24+20-now.getHours();
        var m = (now.getMinutes()>=30 ? 90-now.getMinutes() : 30-now.getMinutes());
        var s = 60 - now.getSeconds();
        $("#ctime").html("Due: "+h+" : "+m+" : "+s);
    }
    function changeBackground(){
        var r = Math.floor(Math.random()*256);
        var g = Math.floor(Math.random()*256);
        var b = Math.floor(Math.random()*256);
        var color = "rgb("+r+","+g+","+b+")";
        $("#colorBar").css("background-color",color);
        $("#colorBar").html(color);
    }
    $("#colorButton").on("click", function(){
        changeBackground();
    });
});
