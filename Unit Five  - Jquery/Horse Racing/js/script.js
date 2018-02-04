var canvas = document.getElementById("canvas");
var ctx = canvas.getContext("2d");
canvas.style.background = "green";

var players = new Array();
var horsesInRace = new Array();
var currentBets = new Array();
var numPlayers = 0;

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
        twentyNote = new Image(),
        fiftyNote = new Image(),
        hundredNote = new Image(),
        //this kinda dumb name but convention compells me
        etheriumNote = new Image(),

        moneyArr = [[twentyNote, "twentyNote"],[fiftyNote,"fiftyNote"],[hundredNote,"hundredNote"],[etheriumNote,"etheriumNote"]];

        twentyNote.src = "images/twentyNote.jpg";
        fiftyNote.src = "images/fiftyNote.jpg";
        hundredNote.src = "images/hundredNote.jpg";
        etheriumNote.src = "images/etheriumNote.jpg";

    //this is for the multi tab betting area
    $(function() {
         bettingNames.tabs();
    });
   

    bettingNames.hide();
    $("#main-toggle").hide();
    fillRace();
    updateInRaceList();
    betMenu.hide();
    raceTrack.hide();


    function player() {
        name: "DEFAULT_NAME";
        wallet: 0;
    }

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


        valid = valid && checkRegexp(name, /^[a-z]([0-9a-z_\s])+$/i, "Username may consist of a-z, 0-9, underscores, spaces and must begin with a letter.");
        valid = checkName(name);

        if (valid) {
            $("#users tbody").append("<tr>" +
                "<td>" + name.val() + "</td>" +
                "<td> $100 </td>" +
                "</tr>");
            dialog.dialog("close");
        }
        return valid;
    }

    //checks if name is not already in the list
    function checkName(name) {
     
        for (i = 0; i < players.length; i++) {
          //only single quote because they are not the same type so tripple quotes would return false always
            if (players[i][0] == name.val()) {
                window.alert("Do not enter the same player name twice")
                return false;
            }
        }
      
        players.push(new Array([name.val()], [100]));
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



    function openBetMenu() {
        raceTrack.hide();
        playerTable.hide();
        betMenu.show();
        bettingNames.show();
        clearBettingNamesTable();
        updateBettingNamesTabs();
        $("#booth-toggle").hide();
        $("#main-toggle").show();
        $("race-toggle").show();

    }

    function openMain() {
        raceTrack.show();
        playerTable.show();
        betMenu.hide();
        bettingNames.hide();
        $("#booth-toggle").show();
        $("#main-toggle").hide();
        $("race-toggle").show();

    }

    function openRace() {
        raceTrack.show();
        playerTable.hide();
        betMenu.hide();
        bettingNames.hide();
        $("#booth-toggle").show();
        $("#main-toggle").show();
        $("#race-toggle").hide();
    }

    //picks random number and then pushes this new horse and it's odds to the horses next race array
    function fillRace() {
        var numHorses = Math.floor((Math.random() * 5) + 4);

        for (i = 0; i <= numHorses; i++) {
            horsesInRace.push(new Array([horses[Math.floor((Math.random() * horses.length))]], ["idk"]));

        }

    }


    //adds all horses that are in the race array to the html table to be displayed
    function updateInRaceList() {
        for (i = 0; i < horsesInRace.length; i++) {
            $("#horsesIR tbody").append("<tr>" +
                "<td>" + (i+1) + ". " + horsesInRace[i][0] + "</td>" +
                "<td>" + horsesInRace[i][1] + "</td>");
        }
    }

//TODO NEED TO MAKE IT SO THE TABS ARE JQUERY TABS
    function updateBettingNamesTabs(){
      if (players.length == 0){
        $("#tabs ul").append("<li>NO PLAYERS IN LIST</li>");
      }
      for (i=0; i < players.length; i++){
        $("#tabs ul").append("<li><a href=\"#tabs-" + (i+1) + "\">"+players[i][0]+"</a></li>");
        $("#tabs").append("<div id=\"tabs-" + (i+1) + "\">"+ displayMoney() +"</div>");
      }
      
      bettingNames.tabs("refresh");
    }

    //this ensures that names are not written twice or never deleted when swtiching tabs
    function clearBettingNamesTable(){
    document.getElementById("bettingTabs").innerHTML = "";
    }

    function displayMoney(){
      var temp = "";

      for (i=0; i < moneyArr.length; i++){
        temp+="<img id = \""+ moneyArr[i][1] +"\" src = \"" + moneyArr[i][0].src + "\" + style=\"width:140px;height:115px;padding-right:80px\">";
      }

      return temp;
    }
});