var canvas = document.getElementById("canvas");
var ctx = canvas.getContext("2d");
var raceBColor = "green"
canvas.style.background = raceBColor;

var players = new Array();
var horsesInRace = new Array();
var currentBets = new Array();
var numPlayers = 0;
var winningHorse = "";
var heightOfLane;
var refreshIntervalId;
//put everything within this that way ensures all assests loaded before executing
$(window).load(function() {
    var dialog, form,
        // From http://www.whatwg.org/specs/web-apps/current-work/multipage/states-of-the-type-attribute.html#e-mail-state-%28type=email%29
        walletRegex = /^\d+\.\d{1,2}$/,
        name = $("#name"),
        wallet = $("#wallet"),
        allFields = $([]).add(name).add(wallet),
        tips = $(".validateTips"),
        betMenu = $("#bet-menu"),
        raceTrack = $("#race-track"),
        playerTable = $("#users-contain"),
        horsesTable = $('#horses-contain'),
        bettingNames = $("#tabs"),
        addPlayrButton = $("#create-user"),
        oneNote = new Image(),
        twentyNote = new Image(),
        fiftyNote = new Image(),
        hundredNote = new Image(),
        //this kinda dumb name but convention compells me
        etheriumNote = new Image(),
        //this is used to determine value of money that is being dropped based off the name of the image
        moneyValueMap = new Map();
    moneyArr = [
        [oneNote, "oneNote"],
        [twentyNote, "twentyNote"],
        [fiftyNote, "fiftyNote"],
        [hundredNote, "hundredNote"],
        [etheriumNote, "etheriumNote"]
    ];
    horseImgArr = [
        [horseOne = new Image(), 0],
        [horseTwo = new Image(), 0],
        [horseThree = new Image(), 0],
        [horseFour = new Image(), 0],
        [horseFive = new Image(), 0],
        [horseSix = new Image(), 0],
        [horseSeven = new Image(), 0],
        [horseEight = new Image(), 0]
    ]
    moneyValueMap.set("oneNote", 1);
    moneyValueMap.set("twentyNote", 20);
    moneyValueMap.set("fiftyNote", 50);
    moneyValueMap.set("hundredNote", 100);
    moneyValueMap.set("etheriumNote", 500);

    oneNote.src = "images/oneNote.jpg";
    twentyNote.src = "images/twentyNote.jpg";
    fiftyNote.src = "images/fiftyNote.jpg";
    hundredNote.src = "images/hundredNote.jpg";
    etheriumNote.src = "images/etheriumNote.jpg";

    horseOne.src = "images/horse1.png";
    horseTwo.src = "images/horse2.png";
    horseThree.src = "images/horse3.png";
    horseFour.src = "images/horse4.png";
    horseFive.src = "images/horse5.png";
    horseSix.src = "images/horse6.png";
    horseSeven.src = "images/horse7.png";
    horseEight.src = "images/horse8.png";






    //this is for the multi tab betting area
    $(function() {
        bettingNames.tabs();
        $("#bet-menu").droppable({
            accept: ".draggable",
            drop: function(event, ui) {
                var dropped = ui.draggable.attr('id');
                //TODO ERRROR IN FINDING THE ACTIVE TAB
                //var temp= $("ul>.ui-tabs-active").attr('aria-controls');
                var temp = $("#tabs .ui-tabs-active").text();
                var indexPlayer = $("#tabs ul .ui-tabs-active").index();

                console.log(moneyValueMap.get(dropped) + "" + temp);

                //TODO CHECK TO MAKE SURE THAT CAN BET MORE THAN HAVE AND THAT YOU CAN SELECT WHICH HORSE TO BET ON
                var indexOfHorseBet = updatePlayerBets(temp, moneyValueMap.get(dropped), indexPlayer);
                //updatePlayerWallet(temp, moneyValueMap.get(dropped), indexOfHorseBet);
                updatePlayerDisplay();

            }
        });

    });

    //init with all the starting stuff and init forms etc
    bettingNames.hide();
    $("#main-toggle").hide();
    fillRace();
    updateInRaceList();
    betMenu.hide();
    raceTrack.hide();
    initTabs();
    $('#refreshTabs').hide();
    $('#betExplination').hide();
    $("#alert").hide();
    //setTimeout(followMyGitHub,1);

    //plays music
    var audio = new Audio('style/theme.mp3');
    audio.play();
    //creates sound effect to be used
    var cashNoise = new Audio('style/cash.mp3');




    function followMyGitHub(){
      popUp("Having Fun? Consider supporting the creator <a href=\"https://github.com/drossos\">Daniel Rossos' Github</a> . Thank you from the whole team!")
    }

    function player() {
        name: "DEFAULT_NAME";
        wallet: 0;
    }

    function initTabs() {
        $("#tabs").append("<div id=\"tabs-money\">" + displayMoney() + "</div>");
        $("#tabs").append("<div id=\"horse-options\">" + horseOptions() + "</div>");
        makeDraggable();
        makeSelectMenu();
        $("#tabs-money").hide();
    }

    //TODOD MAKE SO CAN SWITCH TABS AND THEN COMEBACK WITH SAVED BET ARRAY
    //this contains each player with three slots to bet on a horse
    function initCurrentBets() {
        currentBets = new Array();
        for (i = 0; i < players.length; i++) {
            currentBets.push(new Array(new Array("", 0), new Array("", 0), new Array("", 0)));
        }
    }

    function updatePlayerBets(playerName, moneyValue, index) {
        var horseSelct = $('#betHorse').find(":selected").text();
        if (players[index][1] - moneyValue > -1) {
            // for (i=0; i < currentBets.length;i++){
            for (j = 0; j < currentBets[index].length; j++) {
                if (currentBets[index][j][0] == horseSelct) {
                    currentBets[index][j][1] += moneyValue;
                    players[index][1] -= moneyValue;
                    cashNoise.play();
                    return j;

                } else if (currentBets[index][j][0] == "") {
                    currentBets[index][j][0] += horseSelct;
                    currentBets[index][j][1] += moneyValue;
                    players[index][1] -= moneyValue;
                    cashNoise.play();
                    return j;
                } else if (j == currentBets[index].length - 1) {

                    popUp("You have already bet on 3 horses within the next race");

                    // window.alert("You have already bet on 3 horses within the next race");
                }
            }
        } else {
           
                popUp("Do not have enough money to make that bet!!");
               
            //window.alert("Do not have enough money to make that bet!!");
            //}
        }
    }

    /*function updatePlayerWallet(name, money, indexOfHorseBet) {

        for (i = 0; i < players.length; i++) {
            if (players[i][0] == name) {
                players[i][indexOfHorseBet] += money;
                players[i][1] -= money;
            }
        }
    }*/

    function updateTips(t) {
        tips
            .text(t)
            .addClass("ui-state-highlight");
        setTimeout(function() {
            tips.removeClass("ui-state-highlight", 1500);
        }, 500);
    }

    //check if valid string
    function checkRegexp(o, regexp, n) {
        if (!(regexp.test(o.val()))) {
            o.addClass("ui-state-error");
            updateTips(n);
            return false;
        } else {
            return true;
        }
    }

    //checks if valid then adds new user
    function addUser() {
        var valid = true;
        allFields.removeClass("ui-state-error");


        valid = checkName(name);

        if (valid) {
            $("#users tbody").append("<tr>" +
                "<td>" + name.val() + "</td>" +
                "<td> $100 </td>" + "<td> $0 </td>" +
                "<td> $0 </td>" + "<td> $0 </td>" +
                "</tr>");
            dialog.dialog("close");
        }

        //p sure this works
        clearBettingNamesTable();
        updateBettingNamesTabs();
        addCurrentBets();
        return valid;
    }

    function popUp(message) {
        $(function() {
            $("#alert p").html(message);
            $("#alert").dialog();
        });
    }

    //checks if name is not already in the list
    function checkName(name) {

        for (i = 0; i < players.length; i++) {
            //only single quote because they are not the same type so tripple quotes would return false always
            if (players[i][0] == name.val()) {
                popUp("Do not enter the same player name twice");
                return false;
            }
        }
        if (name.val() < 1) {
            popUp("Please enter a name");
            return false;
        }
        players.push(new Array(name.val(), 100, 0));
        return true;
    }

    dialog = $("#dialog-form").dialog({
        autoOpen: false,
        height: 400,
        width: 350,
        modal: true,
        buttons: {
            "Create an account": addUser,
            Cancel: function() {
                dialog.dialog("close");
            }
        },
        close: function() {
            form[0].reset();
            allFields.removeClass("ui-state-error");
        }
    });

    form = dialog.find("form").on("submit", function(event) {
        event.preventDefault();
        addUser();
    });

    //bring up modol to add user
    $("#create-user").button().on("click", function() {
        dialog.dialog("open");
    });

    //bring up betting window
    $("#booth-toggle").button().on("click", function() {
        openBetMenu();
    });

    $("#main-toggle").button().on("click", function() {
        openMain();
    });

    $('#race-toggle').button().on("click", function() {
        openRace();
    });

    $('#startRace').button().on('click', function() {
        race();
    });

    
    /*$('#refreshTabs').button().on('click', function() {
        clearBettingNamesTable();
        updateBettingNamesTabs();
        addCurrentBets();
    });*/


    function addCurrentBets() {
        currentBets.push(new Array(new Array("", 0), new Array("", 0), new Array("", 0)));
    }

    //the different elements to be hidden and show between each screen
    function openBetMenu() {
        raceTrack.hide();
        betMenu.show();
        bettingNames.show();
        playerTable.show();
        clearBettingNamesTable();
        updateBettingNamesTabs();
        initCurrentBets();
        $("#booth-toggle").hide();
        $("#main-toggle").show();
        $("#race-toggle").show();
        $('#refreshTabs').show();
        $('#intro').hide();
        $("#create-user").show();
        $("#betExplination").show();

    }

    function openMain() {
        raceTrack.hide();
        playerTable.show();
        betMenu.hide();
        bettingNames.hide();
        $("#booth-toggle").show();
        $("#main-toggle").hide();
        $("#race-toggle").show();
        $('#intro').show();
        $('#refreshTabs').hide();
        $("#create-user").show();
        $("#betExplination").hide();

    }

    function openRace() {
        raceTrack.show();
        playerTable.show();
        betMenu.hide();
        bettingNames.hide();
        $("#booth-toggle").show();
        $("#main-toggle").show();
        $("#race-toggle").hide();
        clearTrack();
        drawLanes(canvas.height / horsesInRace.length);
        $('#intro').hide();
        $('#refreshTabs').hide();
        $("#create-user").hide();
        $("#betExplination").hide();
    }

    //picks random number and then pushes this new horse and it's odds to the horses next race array
    function fillRace() {
        var numHorses = Math.floor(Math.random() * (8 - 6) + 6);
        horsesInRace = new Array();
        for (i = 0; i <= numHorses; i++) {


            var temp = horses[Math.floor((Math.random() * horses.length))];
            if (horseNotInRace(temp)) {
                horsesInRace.push(new Array([temp], ["idk"]));
            }

        }

    }

    //make sure no duplicates in race
    function horseNotInRace(temp) {
        for (j = 0; j < horsesInRace.length; j++) {
            if (horsesInRace[j][0] == temp) {
                return false;
            }
        }
        return true;
    }

    //adds all horses that are in the race array to the html table to be displayed
    function updateInRaceList() {
        $("#horsesIR tbody").html("");
        for (i = 0; i < horsesInRace.length; i++) {
            $("#horsesIR tbody").append("<tr>" +
                "<td>" + (i + 1) + ". " + horsesInRace[i][0] + "</td>");
        }
    }

    //TODO NEED TO MAKE IT SO THE TABS ARE JQUERY TABS
    function updateBettingNamesTabs() {
        if (players.length == 0) {
            $("#tabs ul").append("<li>NO PLAYERS IN LIST</li>");
        }

        for (i = 0; i < players.length; i++) {
            $("#tabs-money").show();
            $("#tabs ul").append("<li><a href=\"tabs-money\">" + players[i][0] + "</a></li>");



        }


        bettingNames.tabs("refresh");

        if ($("#tabs .ui-tabs-active").index() == -1) {
            $("#tabs").tabs({
                active: 0
            })
        }
    }

    //this ensures that names are not written twice or never deleted when swtiching tabs
    function clearBettingNamesTable() {
        document.getElementById("bettingTabs").innerHTML = "";
    }

    function horseOptions() {
        var temp = "<label for=\"betHorse\">Select a horse to bet on (up to 3)</label><select name=\"betHorse\" id=\"betHorse\">";

        for (l = 0; l < horsesInRace.length; l++) {
            if (l === 0) {
                temp += "<option selected = \"selected\">" + horsesInRace[l][0] + "</option>";
            } else {
                temp += "<option>" + horsesInRace[l][0] + "</option>";
            }

        }
        return temp;
    }

    //updates the horses in the select menu
    //very similar to horse options, but as this point was just easier to make it this way
    function updateHorseOptions() {
        var temp = "<label for=\"betHorse\">Select a horse to bet on (up to 3)</label><select name=\"betHorse\" id=\"betHorse\">";

        for (l = 0; l < horsesInRace.length; l++) {
            if (l === 0) {
                temp += "<option selected = \"selected\">" + horsesInRace[l][0] + "</option>";
            } else {
                temp += "<option>" + horsesInRace[l][0] + "</option>";
            }

        }
        $("#horse-options").html(temp);
        makeSelectMenu();
    }

    //birngs up the money that is too be used to bet with
    function displayMoney() {
        var temp = "";

        for (j = 0; j < moneyArr.length; j++) {
            temp += "<img class = \"draggable\" id = \"" + moneyArr[j][1] + "\" src = \"" + moneyArr[j][0].src + "\" + style=\"width:165px;height:115px;padding-right:80px\">";
        }

        return temp;
    }

    function makeSelectMenu() {

        $(function() {
            $("#betHorse").selectmenu();
        });

    }

    function makeDraggable() {
        for (j = 0; j < moneyArr.length; j++) {
            $(function() {
                $("#" + moneyArr[j][1] + "," + i).draggable({
                    revert: true
                });
            });
        }
    }


    function updatePlayerDisplay() {
        var table = document.getElementById("users");

        for (i = 0; i < players.length; i++) {
            table.rows[i + 1].cells[1].innerHTML = "$" + players[i][1];
            table.rows[i + 1].cells[2].innerHTML = "$" + players[i][2];
            for (j = 0; j < currentBets[i].length; j++) {
                table.rows[i + 1].cells[2 + j].innerHTML = "$" + currentBets[i][j][1] + " on " + currentBets[i][j][0];
            }
        }
    }

    //TODO MAKE IT SO IT POSSIBLE TO SEE WHOLE ANIMATION
    function race() {

        //so race doesn't keep getting layered over
        ctx.beginPath();
        ctx.rect(0, 0, canvas.width, canvas.height);
        ctx.fillStyle = raceBColor;
        ctx.fill();
        ctx.closePath();
        drawLanes();
        var raceDone = false;
        winningHorse = "";
        refreshIntervalId = setInterval(animate, 500);
        animate(raceDone, refreshIntervalId);



    }

    function animate(raceDone) {

        //while (winningHorse == "") {
        heightOfLane = canvas.height / horsesInRace.length;
        drawHorses(raceDone, heightOfLane);
        drawLanes(heightOfLane);
        //}

        if (winningHorse != "") {
            payoutBets(winningHorse);
            initCurrentBets();
            updatePlayerDisplay();
            updateInRaceList();
            fillRace()
            updateInRaceList();
            updateHorseOptions();
            clearHorseDistances();
            clearInterval(refreshIntervalId);
        }
    }

    function clearHorseDistances() {
        for (i = 0; i < horseImgArr.length; i++) {
            horseImgArr[i][1] = 0;
        }
    }

    function drawLanes(heightOfLane) {
        for (i = 0; i < horsesInRace.length; i++) {
            ctx.beginPath();
            ctx.rect(0, heightOfLane + heightOfLane * i, canvas.width, 10);
            ctx.fillStyle = "#FFFFFF";
            ctx.fill();
            ctx.closePath();

            ctx.beginPath();
            ctx.rect(canvas.width - canvas.width / 100, 0, canvas.width / 100, canvas.height);
            ctx.fillStyle = "#DC0000";
            ctx.fill();
            ctx.closePath();
        }
    }

    function clearTrack(){
      ctx.beginPath();
        ctx.rect(0, 0, canvas.width, canvas.height);
        ctx.fillStyle = raceBColor;
        ctx.fill();
        ctx.closePath();
    }

    function drawHorses(raceDone, heightOfLane) {
      var horseSpriteSize = heightOfLane / 1.12;
        for (i = 0; i < horsesInRace.length; i++) {
            ctx.beginPath();
            ctx.rect(horseImgArr[i][1], (heightOfLane / 2) + heightOfLane * i - heightOfLane / 3, horseSpriteSize, horseSpriteSize);
            ctx.fillStyle = raceBColor;
            ctx.fill();
            ctx.closePath();

            horseImgArr[i][1] += Math.floor(Math.random() * (100 - 20) + 20);
            ctx.beginPath();
            ctx.drawImage(horseImgArr[i][0], horseImgArr[i][1], (heightOfLane / 2) + heightOfLane * i - heightOfLane / 3, horseSpriteSize, horseSpriteSize);
            ctx.closePath();
            if (horseImgArr[i][1] >= canvas.width - horseSpriteSize) {

                winningHorse = horsesInRace[i][0];
            }
            temp = horseImgArr[i][1];
        }


    }


    function payoutBets(winningHorse) {
        var winnerMessage = "";
        var prefixMessage = "";
        var hasWinners = false;
        for (i = 0; i < currentBets.length; i++) {
            for (j = 0; j < currentBets[i].length; j++) {
                if (currentBets[i][j][0] == winningHorse) {
                    hasWinners = true;
                    players[i][1] += currentBets[i][j][1] * 2;
                    winnerMessage += players[i][0] + " has won " + currentBets[i][j][1];
                }
            }
        }

        if (hasWinners) {
            prefixMessage += "The winning horse this round is " + winningHorse + "Congratualtions to the winners! <br><br>";
            popUp(prefixMessage + " $" + winnerMessage);
            cashNoise.play();
            setTimeout(followMyGitHub,6000);

        } else {
            popUp("The winning horse this round is " + winningHorse + "<br><br>There were no winning bets this round. Try harder next time.");
        }

    }

});