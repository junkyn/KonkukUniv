$(document).ready(function(){
    function flash(){
        $("#off_test").show().fadeOut("slow");
    }
    $("#bind").click(function(){
        $("body")
        .on("click","#theone",flash)
        .find("#theone")
        .text("Can Click!");
    });
    $("#unbind").click(function(){
        $("body")
        .off("click","#theone",flash)
        .find("#theone")
        .text("Does nothing...");
    });
    
    $("#b1").on("click", {url:"http://www.google.com", winattributes:"resize=1, scrollbars=1, status=1"}, max_open);
    function max_open(event) {
        var maxwindow = window.open(event.data.url, "", event.data.winattributes);
        maxwindow.moveTo(0, 0);
        maxwindow.resizeTo(screen.availWidth, screen.availHeight);
    }
    $("#FirstBtn").click(function(){
        update($("span:first"));
    });
    $("#SecondBtn").click(function(){
        $("#FirstBtn").trigger("click");
        update($("span:last"));
    });
    function update(j){
        var n = parseInt(j.text(),10);
        j.text(n+1);
    }

});

