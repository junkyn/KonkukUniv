$(document).ready(function(){
    $("div.out").on("mouseover", function () {
        $(".out p:first").text("mouse over");
        $(".out p:last").text(parseInt($(".out p:last").text()) + 1);
      });
    
      $(".out").on("mouseout", function () {
        $(".out p:first").text("mouse out");
      });
    
      $("#b1").on(
        "click",
        {
          url: "http://www.google.com",
          winattributes: "resize=1, scrollbars=1, status=1",
        },
        max_open
      );
    $("#getText").click(function(){
      $("#textbox").text("글자 입력 테스트");
      var req = $.ajax("data.txt");
      console.log("hi");
      req.done(function(data,status){
        console.log("hi");
        var students = JSON.parse(data);
        for(var i = 0; i<students.length;i++){
          var str = "<br>"+students[i].name;
          $("#textbox").append(str);
        }
      });
    });
      function max_open(event) {
        var maxwindow = window.open(event.data.url, "", event.data.winattributes);
        maxwindow.moveTo(0, 0);
        maxwindow.resizeTo(screen.availWidth, screen.availHeight);
      }
    
      function flash() {
        $("#off_test").show().fadeOut("slow");
      }
    
      $("#bind").on("click", function () {
        $("body").on("click", "#theone", flash).find("#theone").text("Can Click!");
      });
    
      $("#unbind").on("click", function () {
        $("body")
          .off("click", "#theone", flash)
          .find("#theone")
          .text("Does nothing...");
      });
    
      $("#trigger_test button:first").on("click", function () {
        update($("#trigger_test span:first"));
      });
    
      $("#trigger_test button:last").on("click", function () {
        $("#trigger_test button:first").trigger("click");
        update($("#trigger_test span:last"));
      });
    
      function update(j) {
        var n = parseInt(j.text(), 10);
        j.text(n + 1);
      }
    
      $("#image").on("click", function () {
        $("#image").attr(
          "src",
          $("#image").attr("src") === "img1.jpg"
            ? "img3.jpg"
            : "img1.jpg"
        );
      });
    
      $(".main-menu").on("mouseover", function () {
        $(this).css({ "font-size": "20px", "background-color": "green" });
      });
    
      $(".main-menu").on("mouseout", function () {
        $(this).css({ "font-size": "unset", background: "none" });
      });
    
      var imgArray = [
        "img1.jpg",
        "img2.jpg",
        "img3.jpg",
        "img4.jpg",
        "img5.jpg",
      ];
      var imgIndex = 0;
      $("#imgAlbum").attr("src", imgArray[imgIndex++]);
      $("#imgAlbum").on("click", function () {
        console.log("from " + imgIndex);
        $("#imgAlbum").attr("src", imgArray[imgIndex++]);
        console.log("to1 " + imgIndex);
        if (imgIndex >= imgArray.length) imgIndex = 0;
        console.log("to2 " + imgIndex);
      });
    
      function change_position(obj) {
        var l = ($(window).width() - obj.width()) / 2;
        var t = ($(window).height() - obj.height()) / 2;
        obj.css({ top: t, left: l });
      }
    
      $("#add_img").on("click", function () {
        $("#note_form").addClass("popup");
        change_position($("#note_form"));
        $("#note_form").slideDown("slow");
      });
    
      $(window).on("resize", function () {
        change_position($(".popup"));
      });
    
      $("#add_note").on("click", function () {
        $("#note").html(
          $("#note").html() +
            `${$("#note_title").val()}, ${$("#note_date").val()}, ${$(
              "#note_content"
            ).val()}<br />`
        );
        $("#note_form").slideUp("slow");
        $("#note_title").val("");
        $("#note_date").val("");
        $("#note_content").val("");
      });
    
      $("#moving_button").on("click", function () {
        $("#moving_box").animate({
          top: 0,
          right: 0,
          width: "+=50px",
          height: "+=50px",
        });
    
        $("#animation_test").animate({
          height: "+=50px",
        });

      });

      $(".accordion").each(function(){
        var dl = $(this);
        var allDt = dl.find('dt');
        var allDd = dl.find("dd");
        function closeAll(){
          allDd.addClass("closed");
          allDt.addClass("closed");
        }
        closeAll();
        function open(dt,dd){
          dt.removeClass("closed");
          dd.removeClass("closed");
        }
        $(".accordion dt").on("click",function(){
          var thisdt = $(this);
          var thisdd = thisdt.next("dd");
          closeAll();
          open(thisdt, thisdd);
        });
      });
      var interval = 4000;
      $('.slideshow').each(function(){
        var container = $(this);
        var timer;


        function switchImg(){
          var imgs = container.find('img');
          var first = imgs.eq(0);
          var second = imgs.eq(1);
          first.appendTo(container).fadeOut(2000);
          second.fadeIn(2000);

        }

        function startTimer(){
          timer = setInterval(switchImg,interval);
        }
        function stopTimer(){
          clearInterval(timer);
        }
        startTimer();
        container.hover(stopTimer,startTimer);
      });
    });