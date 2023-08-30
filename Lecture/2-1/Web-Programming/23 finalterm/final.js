$(document).ready(function(){
	addName();
	function addName(){
		$("header").append("최 준 규");
		setInterval(changeColor, 2000);
		function changeColor(){
			var r = Math.floor(Math.random()*256);
			var g = Math.floor(Math.random()*256);
			var b = Math.floor(Math.random()*256);
			var color = "rgb("+r+","+g+","+b+")";
			$("header").css("color",color);
			$("header").css("borderColor",color);			
		}	
	}
	$(".menu").on("mouseover", function(){
			$(this).addClass("hover");
	});
	$(".menu").on("mouseout", function(){
		$(this).removeClass("hover");
	});
	$(".content").eq(0).show();
	$(".content").eq(1).hide();
	$(".content").eq(2).hide();
	$(".menu").eq(0).on("click",function(){
		$(".content").eq(0).show();
		$(".content").eq(1).hide();
		$(".content").eq(2).hide();
	});
	$(".menu").eq(1).on("click",function(){
		$(".content").eq(1).show();
		$(".content").eq(0).hide();
		$(".content").eq(2).hide();
	});	
	$(".menu").eq(2).on("click",function(){
		$(".content").eq(2).show();
		$(".content").eq(1).hide();
		$(".content").eq(0).hide();
	});

	var items;
	var newsIdx=0;
	getNews();
	function getNews(){
		var req=$.ajax({url: "news.txt",dataType:'xml'});
		req.done(function(data){

			items = $(data).find("item");
			console.log(items);
			if(newsIdx*5+5 > items.length){
				showList(newsIdx*5, items.length);
			}
			else{
				showList(newsIdx*5,newsIdx*5+5);
			}
	});

	}

	function showList(start,stop){
			var showitems = items.slice(start,stop);
			var dlTag=$("<dl/>");
			showitems.each(function(){
				var item=$(this);
				var lk = item.find("link").text();
				var title = item.find("title").text();
				var desc = item.find("description").text();
				var dtTag = $("<dt/>").text(title);
				var ddTag = $("<dd/>");
				var aTag = $("<a/>").attr({
					"href":lk,"target":"_blank"}).html("<br>[기사 보기]");
				ddTag.append(desc).append(aTag)
				dlTag.append(dtTag);
				dlTag.append(ddTag);
			});
			$("#news").html(dlTag);
			dlTag.addClass("accordion");
	$(".accordion").each(function(){
		var dl =$(this);
		var allDd = dl.find("dd");
		var allDt = dl.find("dt");

		function closeAll(){
			allDd.addClass("closed");
		}
		function open(dd){
			dd.removeClass("closed")
		}
		closeAll();
		allDt.click(function(){
			var dt = $(this);
			var dd = dt.next();
			closeAll();
			open(dd);

		});
	});
	}
	$(".button").eq(0).on("click",clickPrev);
	$(".button").eq(1).on("click",clickNext);
	
	function clickPrev(){
		if(newsIdx>0){
			newsIdx--;
			showList(newsIdx*5,newsIdx*5+5);
		}
	}
	function clickNext(){
		if(newsIdx*5+5<items.length){
			newsIdx++;
			if(newsIdx*5+5 > items.length){
				showList(newsIdx*5, items.length);
			}
			else{
				showList(newsIdx*5,newsIdx*5+5);
			}
		}
	}

})