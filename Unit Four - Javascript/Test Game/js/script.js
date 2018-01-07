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
var linkFrontAnim = new Image();
var linkAttackFront = new Image();
var firstMapSeq = 't t t t t t t e e t t t t t t t t t t t oldManCave t t e e t t t t t t t t t t t e e e e e t t t t t t t t t t e e e e e e t t t t t t t t t e e e e e e e t t t t t t t e e e e e e e e e e e e e e e e t t e e e e e e e e e e e e t t t t e e e e e e e e e e e e t t t t e e e e e e e e e e e e t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t';
var oldManCaveSeq='t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t e e e e e e e e e e e e t t t t e e e e e e e e e e e e t t t t e e e e e e e e e e e e t t t t e e e e e e e e e e e e t t t t e e e e e e e e e e e e t t t t e e e e e e e e e e e e t t t t e e e e e e e e e e e e t t t t t t t t t e e t t t t t t t t t t t t t t firstScreen firstScreen t t t t t t t';
var firstMapArr = firstMapSeq.split(" ");
var oldManCaveArr = oldManCaveSeq.split(" ");

var roomsMap = new Map();
roomsMap.set('firstScreen',firstMapArr);
roomsMap.set('oldManCave',oldManCaveArr);
//{'firstScreen':firstMapArr, 'oldManCave':oldManCaveArr};
//16x11 level grids
var movementGrid = [];
var currentPos = {
    xIndex: 7,
    yIndex: 6
};


linkFront.src = "images/link-front.png";
linkFrontAnim.src = "images/link-front-anim.png";
linkAttackFront.src = "images/link-attack-front.png";


document.addEventListener("keydown", keyDownHandler, false);
document.addEventListener("keyup", keyUpHandler, false);
canvas.style.backgroundImage = "url(images/first-screen.png)";


initScreen(firstMapArr);

function initScreen(currMap) {
    var indexCount = 0;
    for (i = 0; i < 11; i++) {
        movementGrid[i] = [];
        for (j = 0; j < 16; j++) {
            if (currMap[indexCount] === 't') {
                movementGrid[i][j] = {
                    type: "out-bounds"
                };
            } else if (currMap[indexCount] === 'e') {
                movementGrid[i][j] = {
                    type: "empty"
                };
            } else {
                var tempBk;
                var xTemp;
                var yTemp;
                if (currMap[indexCount] === 'oldManCave'){
                        tempBk = "url(images/old-man-cave.png)";
                        xTemp = 7;
                        yTemp = 9;
                    } else {
                       tempBk = "url(images/first-screen.png)";
                       xTemp = 4;
                       yTemp = 1;
                    }
                movementGrid[i][j] = {
                    type: 'door',
                    to : roomsMap.get(currMap[indexCount]),
                    //find better way to do this
                    bkgrndImg: tempBk,
                    initX: xTemp,
                    initY: yTemp
                }
            }
            indexCount++;
        }
    }


}

function draw() {
    //ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawLink();
    checkMovement();
    drawLink();
   
}

function drawLink() {
    drawLinkAnimation();
    ctx.drawImage(linkFront, x, y, linkWidth, linkHeight);
    
}

function drawLinkAnimation(){
    ctx.drawImage(linkFrontAnim, x, y, linkWidth, linkHeight);
}

function checkMovement() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    if (rightPressed && x < canvas.width - linkWidth && movementGrid[currentPos.yIndex][currentPos.xIndex+1].type !== "out-bounds") {
        currentPos.xIndex++;
        x += xMovmentLink;
    } else if (leftPressed && x > 0 && movementGrid[currentPos.yIndex][currentPos.xIndex-1].type !== "out-bounds") {
        currentPos.xIndex--;
        x -= xMovmentLink;
    } else if (upPressed && y > 0 && movementGrid[currentPos.yIndex-1][currentPos.xIndex].type !== "out-bounds") {
        currentPos.yIndex--;
        y -= yMovmentLink;
    } else if (downPressed && y < canvas.height - linkHeight && movementGrid[currentPos.yIndex+1][currentPos.xIndex].type !== "out-bounds") {
        currentPos.yIndex++;
        y += yMovmentLink;
    }

    if (movementGrid[currentPos.yIndex][currentPos.xIndex].type === 'door'){
        console.log("door is responisve");
        canvas.style.backgroundImage = movementGrid[currentPos.yIndex][currentPos.xIndex].bkgrndImg;
         //remember for both cordinate systems it starts at 0
        var currX = currentPos.xIndex;
        var currY = currentPos.yIndex;

        //TODO NOT WORKING INIT POS ON NEW SCREEN
        currentPos.xIndex = movementGrid[currY][currX].initX;
        currentPos.yIndex = movementGrid[currY][currX].initY;
        x = (canvas.width / 16) * movementGrid[currY][currX].initX;
        y = (canvas.height / 11) * movementGrid[currY][currX].initY;
        initScreen(movementGrid[currY][currX].to);
       
        

        
    }
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
    } else if (e.keyCode == 32){
        attackSword();
    }
    drawLinkAnimation();
}

function attackSword(){
    drawLinkAttack();
}

function drawLinkAttack(){

    ctx.drawImage(linkAttackFront, x, y, linkWidth, linkHeight);
    var currentTime = new Date().getTime();
    var lim  = currentTime + 80;
    while (currentTime < lim){
        console.log("timerworking");
        currentTime++;
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


setInterval(draw, 100);