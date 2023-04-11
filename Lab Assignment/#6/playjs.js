window.onload = setCTime;

function calc(){
	var x = document.getElementById("x");
	var y = document.getElementById("y");
	var sum = document.getElementById("sum");
	sum.value = Number(x.value)+Number(y.value);
}
var computerNumber = 53;
var nGuesses=0;
function guess(){
	var ipNum = document.getElementById("user");
	var count = document.getElementById("guesses");
	var hint = document.getElementById("result");
	nGuesses++;
	count.value = nGuesses;
	if(ipNum.value>computerNumber){
		hint.value="낮습니다";
	}else if(ipNum.value<computerNumber){
		hint.value="높습니다.";
	}else{
		hint.value="정답!"
		nGuesses=0;
	}

}

function replay() {
	nGuesses = 0;
	computerNumber = Math.floor(Math.random() * 100 + 1);
	document.getElementById("user").value = " ";
	document.getElementById("guesses").value = nGuesses;
	document.getElementById("result").value = " ";
	document.getElementById("answer").value = computerNumber;
 }
 
 function setCTime() {
	var now = new Date();
	var mm;
	switch(now.getMonth()) {
	case 1:
	   mm = "Jan.";
	case 2:
	   mm = "Feb.";
	case 3:
	   mm = "Mar.";
	case 4:
	   mm = "Apr.";
	case 5:
	   mm = "May";
	case 6:
	   mm = "Jun.";
	case 7:
	   mm = "Jul.";
	case 8:
	   mm = "Aug.";
	case 9:
	   mm = "Sep.";
	case 10:
	   mm = "Oct.";
	case 11:
	   mm = "Nov.";
	case 12:
	   mm = "Dec.";
	}
	var s = mm + " " + now.getDate() + ". " + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
	document.getElementById("ctime").innerHTML = s;
	setTimeout('setCTime()', 1000);
 }
 


var POSSIBLE_WORDS = ["obdurate","verisimilitude","defenestrate",
"obsequious","dissonant","today","idempotent"];
var MAX_GUESSES = 6;
var guesses="";
var guessCount = MAX_GUESSES;
var word;
function newGame(){
	word = POSSIBLE_WORDS[Math.floor(Math.random()*POSSIBLE_WORDS.length)];
	guessCount = MAX_GUESSES;
	guesses = "";
	document.getElementById("guessbutton").disabled = false;
	updatePage();
}

function guessLetter() {
	var hguess = document.getElementById("hguess");
	var clue = document.getElementById("clue");
	var letter = hguess.value;
  
	if (
	  guessCount == 0 ||
	  clue.innerHTML.indexOf("_") < 0 ||
	  guesses.indexOf(letter) >= 0
	)
	  return;
  
	if (word.indexOf(letter) < 0) guessCount--;
	guesses += letter;
  
	updatePage();
  }

function updatePage(){
	document.getElementById("hangmanpic").src = "hangman"+guessCount+".gif";
	var clue = document.getElementById("clue");
	var guessstr = document.getElementById("guessstr");
  
	var clueStr = "";
	for (var i = 0; i < word.length; i++) {
		clueStr +=
		  guesses.indexOf(word.charAt(i)) >= 0 ? word.charAt(i) + " " : "_ ";
	}
	clue.innerHTML = clueStr;

	if (guessCount == 0) guessstr.innerHTML = "You Lose";
	else if (clueStr.indexOf("_") < 0) guessstr.innerHTML = "You Win";
	else guessstr.innerHTML = "Guesses: " + guesses;
}