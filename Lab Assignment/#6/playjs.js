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