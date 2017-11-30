var canvas = document.getElementById("myCanvas");
var ctx = canvas.getContext("2d");

var x = (canvas.width / 16) * 7;
var y = (canvas.height / 11) * 6;
var dx = 2;
var dy = -2;
var ballRadius = 10;
var xMovmentLink = canvas.width / 16;
var yMovmentLink = canvas.height / 11;
var linkHeight = 50;
var linkWidth = 45;
var rightPressed = false;
var leftPressed = false;
var upPressed = false;
var downPressed = false;
var linkFront = new Image();
var firstMapSeq = "t t t t t t t e e t t t t t t t t t t t d t t e e t t t t t t t t t t t e e e e e t t t t t t t t t t e e e e e e t t t t t t t t t e e e e e e e t t t t t t t e e e e e e e e e e e e e e e e t t e e e e e e e e e e e e t t t t e e e e e e e e e e e e t t t t e e e e e e e e e e e e t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t";
var firstMapArr = firstMapSeq.split(" ");
//16x11 level grids
var movementGrid = [];
var currentPos = {
    xIndex: 7,
    yIndex: 6
};
linkFront.src = "images/link-front.png";


document.addEventListener("keydown", keyDownHandler, false);
document.addEventListener("keyup", keyUpHandler, false);
canvas.style.backgroundImage = "url(images/first-screen.png)";


initScreen();

function initScreen() {
    var indexCount = 0;
    for (i = 0; i < 11; i++) {
        movementGrid[i] = [];
        for (j = 0; j < 16; j++) {
            if (firstMapArr[indexCount] === 't') {
                movementGrid[i][j] = {
                    type: "out-bounds"
                };
            } else if (firstMapArr[indexCount] === 'e') {
                movementGrid[i][j] = {
                    type: "empty"
                };
            }
            indexCount++;
        }
    }
}

function draw() {
    drawLink();
    checkMovement();
    drawLink();
}

function drawLink() {
    ctx.drawImage(linkFront, x, y, linkWidth, linkHeight);
}

function checkMovement() {
    if (rightPressed && x < canvas.width - linkWidth && movementGrid[currentPos.yIndex][currentPos.xIndex+1].type !== "out-bounds") {
        clearLink();
        currentPos.xIndex++;
        x += xMovmentLink;
    } else if (leftPressed && x > 0 && movementGrid[currentPos.yIndex][currentPos.xIndex-1].type !== "out-bounds") {
        clearLink();
        currentPos.xIndex--;
        x -= xMovmentLink;
    } else if (upPressed && y > 0 && movementGrid[currentPos.yIndex-1][currentPos.xIndex].type !== "out-bounds") {
        clearLink();
        currentPos.yIndex--;
        y -= yMovmentLink;
    } else if (downPressed && y < canvas.height - linkHeight && movementGrid[currentPos.yIndex+1][currentPos.xIndex].type !== "out-bounds") {
        clearLink();
        currentPos.yIndex++;
        y += yMovmentLink;
    }
}

function clearLink() {
    ctx.beginPath();
    ctx.rect(x - 1, y, linkWidth + 5, linkHeight + 5);
    ctx.fillStyle = "#f7d8a5";
    ctx.fill();
    ctx.closePath();
}

function keyDownHandler(e) {
    if (e.keyCode == 39) {
        rightPressed = true;
    } else if (e.keyCode == 37) {
        leftPressed = true;
    } else if (e.keyCode == 38) {
        upPressed = true;
    } else if (e.keyCode == 40) {
        downPressed = true;
    }
}

function keyUpHandler(e) {
    if (e.keyCode == 39) {
        rightPressed = false;
    } else if (e.keyCode == 37) {
        leftPressed = false;
    } else if (e.keyCode == 38) {
        upPressed = false;
    } else if (e.keyCode == 40) {
        downPressed = false;
    }
}

function border(startX, startY, length, width) {
    this.startX = startX;
    this.startY = startY;
    this.length = length;
    this.width = width;

    //to see if the point is within this border
    //TODO check to see if this works
    /*this.containsPoint = fucntion (x,y){
        return (x < startX+width && x > startX && y < startY+length && y > startY)
    };*/
}

setInterval(draw, 80);