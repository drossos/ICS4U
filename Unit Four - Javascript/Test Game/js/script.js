var canvas = document.getElementById("myCanvas");
var ctx = canvas.getContext("2d");

var x = canvas.width / 2;
var y = canvas.height / 2;
var dx = 2;
var dy = -2;
var ballRadius = 10;
var movmentLink = 1;
var linkHeight = 1;
var linkWidth = 1;
var rightPressed = false;
var leftPressed = false;
var upPressed = false;
var downPressed = false;
var linkFront = new Image();
var borders = [];
linkFront.src = "images/link-front.png";


document.addEventListener("keydown", keyDownHandler, false);
document.addEventListener("keyup", keyUpHandler, false);
canvas.style.backgroundImage = "url(images/first-screen.png)";

function draw(){
    drawLink();
    checkMovement();   
    drawLink();
}

function drawLink(){
    ctx.drawImage(linkFront, x,y,linkWidth,linkHeight);
}

function checkMovement(){
    if (rightPressed && x < canvas.width - linkWidth){
        clearLink();
        x+= movmentLink;
    } else if (leftPressed && x > 0){
        clearLink();
        x -= movmentLink;
    } else if (upPressed && y > 0){
        clearLink();
        y -= movmentLink;
    } else if (downPressed && y < canvas.height -linkHeight){
        clearLink();
        y += movmentLink;
    }
}
function clearLink(){
    ctx.beginPath();
    ctx.rect(x-1,y,linkWidth+5,linkHeight+5);
    ctx.fillStyle = "#f7d8a5";
    ctx.fill();
    ctx.closePath();
}

function keyDownHandler(e) {
    if (e.keyCode == 39) {
        rightPressed = true;
    } else if (e.keyCode == 37) {
        leftPressed = true;
    } else if (e.keyCode == 38){
        upPressed = true;
    } else if (e.keyCode == 40){
        downPressed = true;
    }
}

function keyUpHandler(e) {
    if (e.keyCode == 39) {
        rightPressed = false;
    } else if (e.keyCode == 37) {
        leftPressed = false;
    } else if (e.keyCode == 38){
        upPressed = false;
    } else if (e.keyCode == 40){
        downPressed = false;
    }
}

function border (startX,startY,length,width){
    this.startX = startX;
    this.startY = startY;
    this.length = length;
    this.width = width;

    //to see if the point is within this border
    //TODO check to see if this works
    this.containsPoint(x,y){
        return (x < startX+width && x > startX && y < startY+length && y > startY)
    }
}

setInterval(draw,20);

