var context;
var velocity;
var angle;
var ballVx;
var ballVy;
var ballX = 10;
var ballY = 250;
var ballRadius = 10;
var score = 0;
var image = new Image();
image.src = "lawn.png";
var backimage = new Image();
backimage.src = "net.png";
var timer;

function init(){
    ballX = 10;
    ballY = 250;
    ballRadius = 10;
    context = document.getElementById('canvas').getContext('2d');
    draw();
}
function draw(){
    context.clearRect(0,0,500,300);
    drawBall();
    drawBackground();
}
function drawBall(){
    context.beginPath();
    context.arc(ballX,ballY,ballRadius,0,2.0*Math.PI,true);
    context.fillStyle="red";
    context.fill();
}
function drawBackground(){
    context.drawImage(image,0,270);
    context.drawImage(backimage,450,60);
}
function start(){
    init();
    velocity = Number(document.getElementById("velocity").value);
    angle  = Number(document.getElementById("angle").value);
    var angleR = angle* Math.PI/180;
    ballVx = velocity * Math.cos(angleR);
    ballVy = -velocity*Math.sin(angleR);
    timer = setInterval(calculate,100);
    return false;
}
function calculate(){
    ballVy = ballVy + 1.98;
    ballX = ballX + ballVx;
    ballY = ballY+ballVy;
    if((ballX>=450)&& (ballX<=480)&& (ballY>=60)&&(ballY<=210)){
        score++;
        document.getElementById("score").innerHTML = "점수="+score;
        clearInterval(timer);
    }
    if(ballY>=300 || ballY<0){
        clearInterval(timer);
    }
    draw();
}