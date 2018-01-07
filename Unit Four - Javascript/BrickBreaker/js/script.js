var canvas = document.getElementById("myCanvas");
var ctx = canvas.getContext("2d");

var x = canvas.width / 2;
var y = canvas.height - 30;
var dx = 2;
var dy = -2;
var ballRadius = 10;
var colorArr = ['#0006FF', '#FF00C6', '#32D500', '#42FF00'];
var color = colorArr[Math.floor(Math.random() * colorArr.length)];
var paddleHeight = 10;
var paddleWidth = 75;
var paddleX = (canvas.width - paddleWidth) / 2;
var rightPressed = false;
var leftPressed = false;
var brickRowCount = 3;
var brickColumnCount = 5;
var brickWidth = 75;
var brickHeight = 20;
var brickPadding = 10;
var brickOffsetTop = 30;
var brickOffsetLeft = 30;
var score = 0;
var lives = 3;
var bricks = [];
var fallingPowers = [];
var lasersShot = [];
var biggerPaddle = new Image();
var fastBall = new Image();
var lasers = new Image();
var ballSpeed = 2;
var laserEnabled = false;

fastBall.src = "images/fast-ball.png";
biggerPaddle.src = "images/long-paddle.png";
lasers.src = "images/laser.png";

var powerUps = [
    ['fastBall', fastBall],
    ['biggerPaddle', biggerPaddle],
    ['lasers', lasers]
];
var maxPowerUps = (brickColumnCount * brickRowCount) / 4;

for (c = 0; c < brickColumnCount; c++) {
    var powerUpCount = 0
    bricks[c] = [];
    for (r = 0; r < brickRowCount; r++) {
        if (Math.floor(Math.random() * 4) === 1) {
            powerUpCount++;
            bricks[c][r] = {
                x: 0,
                y: 0,
                status: 1,
                powerUp: powerUps[Math.floor(Math.random() * powerUps.length)][0]
            };
        } else {
            bricks[c][r] = {
                x: 0,
                y: 0,
                status: 1,
                powerUp: null
            };
        }
    }
}

document.addEventListener("keydown", keyDownHandler, false);
document.addEventListener("keyup", keyUpHandler, false);
document.addEventListener("mousemove", mouseMoveHandler, false);


function drawPaddle() {
    ctx.beginPath();
    ctx.rect(paddleX, canvas.height - paddleHeight, paddleWidth, paddleHeight);
    ctx.fillStyle = "#0095DD";
    ctx.fill();
    ctx.closePath();
}

function drawLives() {
    ctx.font = "16px Arial";
    ctx.fillStyle = "#0095DD";
    ctx.fillText("Lives: " + lives, canvas.width - 65, 20);
}

function drawBall() {
    ctx.beginPath();
    ctx.arc(x, y, ballRadius, 0, Math.PI * 2);
    ctx.fill();
    ctx.closePath();
}

function drawBricks() {
    for (c = 0; c < brickColumnCount; c++) {
        for (r = 0; r < brickRowCount; r++) {
            if (bricks[c][r].status == 1) {
                var brickX = (c * (brickWidth + brickPadding)) + brickOffsetLeft;
                var brickY = (r * (brickHeight + brickPadding)) + brickOffsetTop;
                bricks[c][r].x = brickX;
                bricks[c][r].y = brickY;
                ctx.beginPath();
                ctx.rect(brickX, brickY, brickWidth, brickHeight);
                ctx.fillStyle = "#0095DD";
                ctx.fill();
                ctx.closePath();
            }
        }
    }
}

function drawScore() {
    ctx.font = "16px Arial";
    ctx.fillStyle = "#0095DD";
    ctx.fillText("Score: " + score, 8, 20);
}

function drawLives() {
    ctx.font = "16px Arial";
    ctx.fillStyle = "#0095DD";
    ctx.fillText("Lives: " + lives, canvas.width - 65, 20);
}

function draw() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawBricks();
    drawBall();
    collisionDetection();
    drawPaddle();
    drawFallingPowers();
    checkPowerGrab();
    drawLasers();
    drawScore();
    drawLives();
    canvas.style.backgroundColor = color;

    if (x + dx > canvas.width - ballRadius || x + dx < ballRadius) {
        dx = -dx;
        color = colorArr[Math.floor(Math.random() * 4)];
    }
    if (y + dy < ballRadius) {
        dy = -dy;
        color = colorArr[Math.floor(Math.random() * 4)];
    } else if (y + dy > canvas.height - ballRadius) {
        if (x > paddleX && x < paddleX + paddleWidth) {
            dy = -dy;
        } else {
            lives--;
            if (!lives) {
                alert("UR BAD");
                document.location.reload();
            } else {
                x = canvas.width / 2;
                y = canvas.height - 30;
                dx = ballSpeed;
                dy = -ballSpeed;
                paddleX = (canvas.width - paddleWidth) / 2;
            }
        }
    }

    if (rightPressed && paddleX < canvas.width - paddleWidth) {
        paddleX += 7;
    } else if (leftPressed && paddleX > 0) {
        paddleX -= 7;
    }

    x += dx;
    y += dy;
}

function drawLasers(){
    for (i =0 ; i < lasersShot.length; i++){
        ctx.beginPath();
        ctx.rect(lasersShot[i].x, lasersShot[i].y, 5, 10);
        ctx.fillStyle = "#00FFF0";
        ctx.fill();
        ctx.closePath();
        lasersShot[i].y--;
    }
}

function checkPowerGrab(){
    for (i = 0; i < fallingPowers.length;i++){
        if (fallingPowers[i].x > paddleX - paddleWidth && fallingPowers[i].x < paddleX+paddleWidth && fallingPowers[i].y === canvas.height-40){
            fallingPowers[i].y += canvas.height;
            applyPowerUp(fallingPowers[i].name);
        }
    }
}

function applyPowerUp(powerUp){
    if (powerUp === 'fastBall'){
        ballSpeed += 5;
    } else if (powerUp === "biggerPaddle"){
        paddleWidth += 30;
    } else if (powerUp === "lasers"){
        laserEnabled = true;
    }
}
function drawFallingPowers(){
    for (i = 0; i < fallingPowers.length;i++){
        ctx.drawImage(fallingPowers[i].img,fallingPowers[i].x,fallingPowers[i].y,40,40);
        fallingPowers[i].y++;
    }
}
function keyDownHandler(e) {
    if (e.keyCode == 39) {
        rightPressed = true;
    } else if (e.keyCode == 37) {
        leftPressed = true;
    } else if (e.keyCode == 32 && laserEnabled){
        lasersShot[lasersShot.length] = {
            x: paddleX + paddleWidth/2  ,
            y: canvas.height
        }
    }
}

function keyUpHandler(e) {
    if (e.keyCode == 39) {
        rightPressed = false;
    } else if (e.keyCode == 37) {
        leftPressed = false;
    }
}

function mouseMoveHandler(e) {
    var relativeX = e.clientX - canvas.offsetLeft;
    if (relativeX > 0 && relativeX < canvas.width) {
        paddleX = relativeX - paddleWidth / 2;
    }
}

function collisionDetection() {
    for (c = 0; c < brickColumnCount; c++) {
        for (r = 0; r < brickRowCount; r++) {
            var b = bricks[c][r];
            if (b.status == 1) {
                if (x > b.x && x < b.x + brickWidth && y > b.y && y < b.y + brickHeight) {
                    dy = -dy;
                    b.status = 0;
                    score++;
                    if (b.powerUp != null) {
                        checkPowerUp(b.powerUp, b);
                    }
                    if (score == brickRowCount * brickColumnCount) {
                        alert("YOU WIN, CONGRATULATIONS!");
                        document.location.reload();
                    }
                }
            }
        }
    }
}

function checkPowerUp(powerUp, brick) {
    for (i = 0; i < powerUps.length; i++) {
        if (powerUp === powerUps[i][0]) {
            fallingPowers[fallingPowers.length] = {
                x: brick.x,
                y: brick.y,
                img: powerUps[i][1],
                name: powerUps[i][0]
            }
        }
    }
}
setInterval(draw, 10);