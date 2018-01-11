/*
This is a comment just reminding you that you said it would be fine if my code had hard coded garbage
*/

//this portion is just an attempt to gain more github folllowers
var popMesg = "Are you gonna follow the creator's github? github.com/drossos";

function followMyGithub() {
    var followed = false;
    while (!followed) {
        if (confirm(popMesg) == true) {
            followed = true;
        } else {
            popMesg = "No you are going to follow it. github.com/drossos";
        }
    }
}
followMyGithub();
var canvas = document.getElementById("canvas");
var ctx = canvas.getContext("2d");

var firstNodeMid = new Node();
var firstNodeTop = new Node();
var firstNodeBot = new Node();
var secondNodeMid = new Node();
var thirdNodeMid = new Node();
var secondNodeTop = new Node();
var secondNodeBot = new Node();
var stepCount = 0;
var nodeSize = canvas.height / 20;
var unFinalizedCol = "#E63946";
var backgroundColor = "#4C88AD";
var finalizedCol = "#60992D";
var defLineColor = "black";
canvas.style.background = backgroundColor;
var nodes = [];
//the inital set of edges where first value is weight, second is x value of middle of edge and thrid is the y component
//the index of the edge is the identifier for when changes are made
var edgeValues = [
    [5, 0, 0],
    [2, 0, 0],
    [10, 0, 0],
    [7, 0, 0],
    [4, 0, 0],
    [6, 0, 0],
    [1, 0, 0],
    [11, 0, 0],
    [13, 0, 0],
    [9, 0, 0],
    [3, 0, 0],
    [5, 0, 0],
    [10, 0, 0]

    /*
    [5, (firstNodeMid.xCord + firstNodeTop.xCord) / 2, (firstNodeMid.yCord + firstNodeTop.yCord) / 2],
    [2, (firstNodeMid.xCord + firstNodeBot.xCord) / 2, (firstNodeMid.yCord + firstNodeBot.yCord) / 2],
    [10, (firstNodeMid.xCord + secondNodeMid.xCord) / 2, (firstNodeMid.yCord + secondNodeMid.yCord) / 2],
    [7, (firstNodeTop.xCord + secondNodeTop.xCord) / 2, (firstNodeTop.yCord + secondNodeTop.yCord) / 2],
    [4, (firstNodeBot.xCord + secondNodeBot.xCord) / 2, (firstNodeBot.yCord + secondNodeBot.yCord) / 2],
    [7, (firstNodeTop.xCord + secondNodeMid.xCord) / 2, (firstNodeTop.yCord + secondNodeMid.yCord) / 2],
    [5, (firstNodeBot.xCord + secondNodeMid.xCord) / 2, (firstNodeBot.yCord + secondNodeMid.yCord) / 2],
    [6, (secondNodeMid.xCord + secondNodeTop.xCord) / 2, (secondNodeMid.yCord + secondNodeTop.yCord) / 2],
    [3, (secondNodeMid.xCord + thirdNodeMid.xCord) / 2, (secondNodeMid.yCord + thirdNodeMid.yCord)/2],
    [11, (secondNodeMid.xCord + secondNodeBot.xCord) / 2, (secondNodeMid.yCord + secondNodeBot.yCord) / 2],
    [13, (secondNodeTop.xCord + thirdNodeMid.xCord) / 2, (secondNodeTop.yCord + thirdNodeMid.yCord) / 2],
    [9, (secondNodeBot.xCord + thirdNodeMid.xCord) / 2, (secondNodeBot.xCord + thirdNodeMid.xCord) / 2]*/
];
var edges = [];
initializeEdges();
//an array containg which nodes and values are updated on each step, can go both ways
//steps[] === step number
//stepd[][] === node or edge (0 or 1)
//step [][][] === index of node or edge (with edges thought the last value in array denotes the colour of the circle surronding them)

document.addEventListener("keydown", keyDownHandler, false);
//once to fill all values then twice to make them all in realtion to eachother
instanciateNode();
instanciateNode();
initializeEdgesValues();
//contains all the nodes and the index of them is the indentifier when changes are made
nodes = [firstNodeMid, firstNodeTop, firstNodeBot, secondNodeMid, secondNodeTop, secondNodeBot, thirdNodeMid];
drawEdges();
drawNodes();
//contains all explinations for each steps, the index of each string is the indentifier to access it later when the steps are updated
var stepsExplinations = [
        "We start out with a weighted graph. All the nodes have a value that indicates the distance to get to that point from the starting node. Starting out before we pick a starting node we consider all of them to have a value of infinity",
        "For this example we chose the left most node to be our starting point. The starting point has a value of zero since it is the start node. This value is the smallest distance possible, so we consider this node to be in the finalized set so it's value will not change. We then look at all un-finalized nodes that are connected to this newly added node.",
        "From here we compare the value of the un-finalized node with the value of the finalized node plus the weight of the edge to get to that un-finalized node. The lower of these two values becomes the value of the un-finalized node. In this step we can see all the node values that were considered are updated.",
        "Now we select the un-finalized node with the lowest node value and the edge to get there from a finalized node. This edge can be seen in the step above",
        "This lowest un-finalized node is now considered to be the lowest value to get to that node so we will now consider part of the finalized set (as seen by the change in colour) and the edge to get to that node will be considered part of the optimized map. This greedy approach was proven by Dijkstra when he developed this algorithm to be true in determining the fastest way to a node.",
        "We now consider all the edges that connect the newest finalized node to an un-finalized node.",
        "The connected nodes have their values checked and then updated.",
        "The lowest un-finalized node and the edge that connects it is now considered. (Note how the lowest un-finalized node that is chosen doesn't have to be connected to newest finalized node)",
        "This node is now finalized and the edge that connects it is now part of the optimized graph.",
        "All the edges that connect the newest finalized node to an un-finalized node are considered.",
        "The connecting un-finalized nodes now have their values considered and potentially updated.",
        "The lowest un-finalized node and the edge that connects are now added to the finalized set. Notice how in this case there are two nodes that are tied with the lowest value. It does not matter which of these nodes you choose to finalize. You will end up with the same solution in the end.",
        "All the edges that connect the newest finalized node to an un-finalized node are considered.",
        "The connecting un-finalized nodes now have their values considered and potentially updated.",
        "The lowest un-finalized node and the edge that connects it is now considered.",
        "The node now is considered finalized.",
        "The connecting un-finalized nodes now have their values considered and potentially updated.",
        "The lowest un-finalized node and the edge that connects it is now considered.",
        "The node now is considered finalized.",
        "We start out with a weighted graph. All the nodes have a value that indicates the distance to get to that point from the starting node. Starting out before we pick a starting node we consider all of them to have a value of infinity",
        "The lowest un-finalized node and the edge that connects it is now considered.",
        "The node now is considered finalized. Now that there are not longer nodes in the un-finalized set and all the nodes are now in the finalized set the algorithm is finished and you are left with an optimized graph and the minimum distance to get to any node from the start node.",





    ]
    //this is just the changes in each step
    //each step there are two arrays, the first array contains the information about the change in any nodes[which node, color, value of node]
    //the second array contains information of the change in any edges [which edge, color of edge]
var steps = [
    [
        [
            [0, unFinalizedCol, "∞"]
        ],
        [
            [0, defLineColor],
            [2, defLineColor],
            [1, defLineColor]
        ]
    ],
    [
        [
            [0, finalizedCol, "0"],
            [2, unFinalizedCol, "∞"],
            [1, unFinalizedCol, "∞"],
            [3, unFinalizedCol, "∞"]
        ],
        [
            [0, finalizedCol],
            [2, finalizedCol],
            [1, finalizedCol]
        ]
    ],
    [
        [
            [2, unFinalizedCol, "2"],
            [1, unFinalizedCol, "5"],
            [3, unFinalizedCol, "10"]
        ],
        [
            [0, finalizedCol],
            [2, finalizedCol],
            [1, finalizedCol]
        ]
    ],

    [
        [],
        [
            [0, defLineColor],
            [2, defLineColor],
            [1, finalizedCol],
            [3, defLineColor]
        ]
    ],

    [
        [
            [2, finalizedCol, "2"]

        ],
        [
            [1, finalizedCol],
            [3, defLineColor],
            [5, defLineColor],
            [6, defLineColor],
            [4, defLineColor]

        ]
    ],

    [
        [
            [5, unFinalizedCol, "∞"],
            [3, unFinalizedCol, "10"]
        ],
        [
            [6, finalizedCol],
            [4, finalizedCol],
            [0, defLineColor]
        ]
    ],
    [
        [
            [5, unFinalizedCol, "6"],
            [3, unFinalizedCol, "7"],
            [1, unFinalizedCol, "5"]

        ],
        [
            [6, defLineColor],
            [4, defLineColor],
            [0, defLineColor]
        ]
    ],
    [
        [
            [1, unFinalizedCol, "5"]

        ],
        [
            [0, finalizedCol]
        ]
    ],

    [
        [
            [1, finalizedCol, "5"]
        ],
        [
            [3, defLineColor],
            [5, defLineColor]
        ]


    ],
    [
        [
            [3, unFinalizedCol, "7"],
            [4, unFinalizedCol, "∞"]
        ],
        [
            [3, finalizedCol],
            [5, finalizedCol]
        ]

    ],

    [
        [
            [3, unFinalizedCol, "6"],
            [4, unFinalizedCol, "12"]
        ],
        [
            [3, finalizedCol],
            [5, finalizedCol]
        ]

    ],

    [
        [
            [3, finalizedCol, "6"],
        ],
        [
            [3, defLineColor],
            [7, defLineColor],
            [8, defLineColor],
            [9, defLineColor]
        ]

    ],

    [
        [
            [4, unFinalizedCol, "12"],
            [6, unFinalizedCol, "∞"],
            [5, unFinalizedCol, "6"]
        ],
        [
            [7, finalizedCol],
            [8, finalizedCol],
            [9, finalizedCol]
        ]

    ],

    [
        [
            [6, unFinalizedCol, "9"]
        ],
        [
            [4, defLineColor]
        ]

    ],

    [
        [
            [5, unFinalizedCol, "6"]
        ],
        [
            [7, defLineColor],
            [8, defLineColor],
            [9, defLineColor],
            [4, finalizedCol]
        ]

    ],

    [
        [
            [5, finalizedCol, "6"]
        ],
        [
            [11, defLineColor]
        ]

    ],

    [
        [

        ],
        [
            [11, finalizedCol],
            [9, defLineColor]
        ]

    ],

    [
        [
            [6, unFinalizedCol, "9"]
        ],
        [
            [8, finalizedCol],
            [11, defLineColor]
        ]

    ],

    [
        [
            [6, finalizedCol, "9"]
        ],
        [
            [10, defLineColor]
        ]

    ],

    [
        [

        ],
        [
            [10, finalizedCol],
            [7, defLineColor]
        ]

    ],

    [
        [
            [4, unFinalizedCol, "12"]
        ],
        [
            [10, defLineColor],
            [7, finalizedCol]
        ]

    ],

    [
        [
            [4, finalizedCol, "12"]
        ],
        [

        ]

    ]

];

/*//outter index is all the steps, next step down is the step and inside that 2d array with one inex being the 
//0 index is initial state and 1 index is is final state
var steps = new Map();
steps.set(1, function (){
	firstNodeMid.colour = "black";
	drawNodes();
});*/



function Node() {
    xCord: 0;
    yCord: 0;
    connected: [

    ];
    value: "∞";
    colour: unFinalizedCol;
}

//the default build of each node, this creates each node where in each step only properites of the node are changed
function instanciateNode() {

    firstNodeMid = {
        xCord: nodeSize * 2,
        yCord: canvas.height / 2,
        connected: [
            [firstNodeTop.xCord, firstNodeTop.yCord],
            [firstNodeBot.xCord, firstNodeBot.yCord],
            [secondNodeMid.xCord, secondNodeMid.yCord]
        ],
        value: "∞",
        colour: unFinalizedCol

    };

    secondNodeMid = {
        xCord: canvas.width / 2 + nodeSize,
        yCord: canvas.height / 2,
        connected: [
            [firstNodeMid.xCord, firstNodeMid.yCord],
            [firstNodeTop.xCord, firstNodeTop.yCord],
            [firstNodeBot.xCord, firstNodeBot.yCord],
            [thirdNodeMid.xCord, thirdNodeMid.yCord],
            [secondNodeTop.xCord, secondNodeTop.yCord],
            [secondNodeBot.xCord, secondNodeBot.yCord]
        ],
        value: "∞",
        colour: unFinalizedCol

    };

    thirdNodeMid = {
        xCord: canvas.width - nodeSize * 2,
        yCord: canvas.height / 2,
        connected: [
            [secondNodeBot.xCord, secondNodeBot.yCord],
            [secondNodeTop.xCord, secondNodeTop.yCord],
            [secondNodeMid.xCord, secondNodeMid.yCord]
        ],
        value: "∞",
        colour: unFinalizedCol

    };

    firstNodeTop = {
        xCord: nodeSize * 11,
        yCord: canvas.height / 4.5,
        connected: [
            [secondNodeMid.xCord, secondNodeMid.yCord],
            [firstNodeMid.xCord, firstNodeMid.yCord],
            [secondNodeTop.xCord, secondNodeTop.yCord]
        ],
        value: "∞",
        colour: unFinalizedCol

    };

    secondNodeTop = {
        xCord: canvas.width - nodeSize * 9,
        yCord: canvas.height / 4.5,
        connected: [
            [secondNodeMid.xCord, secondNodeMid.yCord],
            [thirdNodeMid.xCord, thirdNodeMid.yCord],
            [firstNodeTop.xCord, firstNodeTop.yCord]
        ],
        value: "∞",
        colour: unFinalizedCol

    };

    firstNodeBot = {
        xCord: nodeSize * 11,
        yCord: canvas.height / 1.25,
        connected: [
            [secondNodeMid.xCord, secondNodeMid.yCord],
            [firstNodeMid.xCord, firstNodeMid.yCord],
            [secondNodeBot.xCord, secondNodeBot.yCord]
        ],
        value: "∞",
        colour: unFinalizedCol

    };

    secondNodeBot = {
        xCord: canvas.width - nodeSize * 9,
        yCord: canvas.height / 1.25,
        connected: [
            [secondNodeMid.xCord, secondNodeMid.yCord],
            [thirdNodeMid.xCord, thirdNodeMid.yCord],
            [secondNodeBot.xCord, secondNodeBot.yCord]
        ],
        value: "∞",
        colour: unFinalizedCol

    };

}

//goes through and draws each edge
function drawEdges() {

    for (i = 0; i < nodes.length; i++) {
        for (j = 0; j < nodes[i].connected.length; j++) {
            ctx.moveTo(nodes[i].xCord, nodes[i].yCord);
            ctx.lineTo(nodes[i].connected[j][0], nodes[i].connected[j][1]);
            ctx.stroke();
        }
    }
    for (i = 0; i < edgeValues.length; i++) {
        ctx.fillStyle = "black";
        ctx.font = "20px Arial";
        ctx.fillText(edgeValues[i][0], edgeValues[i][1] - 5, edgeValues[i][2] - 15);
    }
    /*var totEdgeCount = 0;
    for (i = 0; i < nodes.length; i++) {
        for (j = 0; j < nodes[i].connected.length; j++) {
            ctx.moveTo(nodes[i].xCord, nodes[i].yCord);
            ctx.lineTo(nodes[i].connected[j][0], nodes[i].connected[j][1]);
            ctx.stroke();

            if (i == 0 || i == 3 && totEdgeCount < edgeValues.length) {
                //ctx.moveTo((nodes[i].xCord + nodes[i].connected[j][0]) /2 , (nodes[i].yCord + nodes[i].connected[j][1])/2);
                ctx.fillStyle = "black";
                ctx.font = "20px Arial";
                ctx.fillText(edgeValues[totEdgeCount][0], (nodes[i].xCord + nodes[i].connected[j][0]) / 2 - 5, (nodes[i].yCord + nodes[i].connected[j][1]) / 2 - 15);
                edgeValues[totEdgeCount][1] = (nodes[i].xCord + nodes[i].connected[j][0]) / 2;
                edgeValues[totEdgeCount][2] = (nodes[i].yCord + nodes[i].connected[j][1]) / 2;
                totEdgeCount++;
            }
        }
    }*/
}

//this starts each edge with it's x and y cordinates
function initializeEdgesValues() {
    edgeValues = [
        [5, (firstNodeMid.xCord + firstNodeTop.xCord) / 2, (firstNodeMid.yCord + firstNodeTop.yCord) / 2],
        [2, (firstNodeMid.xCord + firstNodeBot.xCord) / 2, (firstNodeMid.yCord + firstNodeBot.yCord) / 2],
        [10, (firstNodeMid.xCord + secondNodeMid.xCord) / 2, (firstNodeMid.yCord + secondNodeMid.yCord) / 2],
        [7, (firstNodeTop.xCord + secondNodeTop.xCord) / 2, (firstNodeTop.yCord + secondNodeTop.yCord) / 2],
        [4, (firstNodeBot.xCord + secondNodeBot.xCord) / 2, (firstNodeBot.yCord + secondNodeBot.yCord) / 2],
        [1, (firstNodeTop.xCord + secondNodeMid.xCord) / 2, (firstNodeTop.yCord + secondNodeMid.yCord) / 2],
        [5, (firstNodeBot.xCord + secondNodeMid.xCord) / 2, (firstNodeBot.yCord + secondNodeMid.yCord) / 2],
        [6, (secondNodeMid.xCord + secondNodeTop.xCord) / 2, (secondNodeMid.yCord + secondNodeTop.yCord) / 2],
        [3, (secondNodeMid.xCord + thirdNodeMid.xCord) / 2, (secondNodeMid.yCord + thirdNodeMid.yCord) / 2],
        [11, (secondNodeMid.xCord + secondNodeBot.xCord) / 2, (secondNodeMid.yCord + secondNodeBot.yCord) / 2],
        [13, (secondNodeTop.xCord + thirdNodeMid.xCord) / 2, (secondNodeTop.yCord + thirdNodeMid.yCord) / 2],
        [9, (secondNodeBot.xCord + thirdNodeMid.xCord) / 2, (secondNodeBot.yCord + thirdNodeMid.yCord) / 2]
    ];
}

//this array contains which edges connect to which nodes [value of edge, first node it connected to, second node it connected to]
function initializeEdges() {
    edges = [
        [5, 0, 1],
        [2, 0, 2],
        [10, 0, 3],
        [7, 1, 4],
        [4, 2, 5],
        [1, 1, 3],
        [5, 2, 3],
        [6, 3, 4],
        [3, 3, 6],
        [11, 3, 5],
        [13, 4, 6],
        [9, 5, 6]
    ]
}

//drwas the nodes based off their properties
function drawNodes() {
    for (i = 0; i < nodes.length; i++) {
        ctx.beginPath();
        ctx.arc(nodes[i].xCord, nodes[i].yCord, canvas.height / 20, 0, Math.PI * 2);

        ctx.fillStyle = nodes[i].colour;
        ctx.fill();
        ctx.closePath();

        ctx.fillStyle = "black";
        ctx.font = "35px Arial";
        if (nodes[i].value.length == 1)
            ctx.fillText(nodes[i].value, nodes[i].xCord - (canvas.height / 20) / 2, nodes[i].yCord + (canvas.height / 20) / 2);
        else
            ctx.fillText(nodes[i].value, nodes[i].xCord - (canvas.height / 20) / 1.25, nodes[i].yCord + (canvas.height / 20) / 2);

    }
}

//updates the screen when the right or left arrow key is pressed 
function keyDownHandler(e) {
    if (e.keyCode === 39) {
        if (stepCount + 1 < steps.length) {
            stepCount++;
            for (i = 0; i < steps[stepCount][1].length; i++) {
                ctx.beginPath();
                ctx.moveTo(nodes[edges[steps[stepCount][1][i][0]][1]].xCord, nodes[edges[steps[stepCount][1][i][0]][1]].yCord);
                ctx.lineTo(nodes[edges[steps[stepCount][1][i][0]][2]].xCord, nodes[edges[steps[stepCount][1][i][0]][2]].yCord);
                ctx.strokeStyle = steps[stepCount][1][i][1];
                ctx.stroke();
                //three times to make up for bug in canvas that leaves some pixilation 
                ctx.stroke();
                ctx.stroke();
                ctx.stroke();
                ctx.closePath();
            }

            for (i = 0; i < steps[stepCount][0].length; i++) {
                nodes[steps[stepCount][0][i][0]].colour = steps[stepCount][0][i][1];
                nodes[steps[stepCount][0][i][0]].value = steps[stepCount][0][i][2];
            }
            drawNodes();



        }
    } else if (e.keyCode === 37) {
        if (stepCount - 1 >= 0) {
            stepCount--;

            for (i = 0; i < steps[stepCount][1].length; i++) {
                ctx.beginPath();
                ctx.moveTo(nodes[edges[steps[stepCount][1][i][0]][1]].xCord, nodes[edges[steps[stepCount][1][i][0]][1]].yCord);
                ctx.lineTo(nodes[edges[steps[stepCount][1][i][0]][2]].xCord, nodes[edges[steps[stepCount][1][i][0]][2]].yCord);
                ctx.strokeStyle = steps[stepCount][1][i][1];
                ctx.stroke();
                //three times to make up for bug in canvas that leaves some pixilation 
                ctx.stroke();
                ctx.stroke();
                ctx.stroke();
                ctx.closePath();
            }
            for (i = 0; i < steps[stepCount][0].length; i++) {
                nodes[steps[stepCount][0][i][0]].colour = steps[stepCount][0][i][1];
                nodes[steps[stepCount][0][i][0]].value = steps[stepCount][0][i][2];
            }
            drawNodes();



        }
    }
    //after every step change get the corresponding explination and display it to screen
    document.getElementById("descriptions").innerHTML = stepsExplinations[stepCount];
}