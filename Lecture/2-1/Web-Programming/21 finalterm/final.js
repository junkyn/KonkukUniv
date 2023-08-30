$(document).ready(function(){
	addIMG();
	function addIMG(){
		var myImg = $("<img/>").attr("src","chitos.png");
		$("#container").prepend(myImg); // append
	}
	var imgs = $("#container img");

	imgs.eq(3).hide();
	imgs.eq(0).css("opacity","0.25");
	imgs.eq(2).css("opacity","0.25");
	imgs.eq(1).css("opacity","1");
	setInterval(imgCtrl, 2000);
	function imgCtrl(){
    	var tmp = imgs.eq(0).attr("src");
    	imgs.eq(0).attr("src", imgs.eq(1).attr("src"));
    	imgs.eq(1).attr("src", imgs.eq(2).attr("src"));
    	imgs.eq(2).attr("src", imgs.eq(3).attr("src"));
    	imgs.eq(3).attr("src", tmp);
	}

	$("nav div").on("mouseover", function(){
		$(this).css("cursor", "pointer");
	});
	$("nav div").eq(0).on("click", oath);
	function oath(){
		$("#content").html("나 <strong>최준규 (202211389)</strong>은<br>2021년 <strong>웹 프로그레밍 기말고사 온라인 시험을 수행</strong>함에 있어서<br>양심을 걸고 <strong>정직하고 올바른 방법으로 수행함을 약속</strong>합니다.");
		$("#content").css("textAlign","center");
	}
    $(".accordion").each(function(){
        	var dl = $(this);
        	var allDt = dl.find('dt');
        	var allDd = dl.find("dd");
        	function clossAll(){
        		allDd.hide();
        		allDt.hide();
        	}
        	clossAll();
        	function open(dt,dd){
        		dt.show();
        		dd.show();
        	}
        	$(".accordion dt").on("click", function(){
        		var thisdt = $(this);
        		var thisdd = thisdt.next("dd");
        		closeAll();
        		open(thisdt, thisdd);
        	});
        });
	$("nav div").eq(1).on("click",getNews);
	function getNews(){
		
	}

});