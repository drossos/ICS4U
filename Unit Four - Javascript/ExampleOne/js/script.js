function hoverAction(){
	//console.log("hi");
	//alert("bye");

	var el = document.getElementById('sample'); //grabs element by id
	el.style.color = 'green';					//each element hasa style property which 
												//is a map with key as the css property
	var arr = arrayPractise();
	
	var answer = bigMathFunction(1,20);
	var body = document.getElementById('body');
	body.style.backgroundImage= "url(https:i.ytimg.com/vi/J7BOjllZTd0/hqdefault.jpg?sqp=-oaymwEWCKgBEF5IWvKriqkDCQgBFQAAiEIYAQ==&rs=AOn4CLCmQnq4fWwLLf1bJd5viT_DZwwp2g)";											
	
	var mapMe = mapPractise();
	var elements = document.querySelectorAll('h1'); //returns an array - same rules as css
	
	var i;
	for (i=0; i < elements.length;i++){
		elements[i].style.color = mapMe.color;
	}

	var mapMe2 = mapPractise2();


}


/* We can return values from a fucntion but we do not need to specify the type */
/* We can accpet arguments but again we do not have to specify the type */
function bigMathFunction(i,j){
	var count;
	var sum = 0;
	for (count  = 0; count <= j; count++){
		sum += count;
	}

	/*var evenOdd = (sum%2==0) ? 'even' : 'odd';


	console.log(evendOdd);*/
	console.log('This is the sum' + sum);
	return sum;
}

var fn = function(){
	alert(1);
}

function arrayPractise(){
	var arr = [];

	arr[0] = 'hi';
	arr[1] = 6;
	arr[8] = true;
	arr[7] = fn;

	var i;
	for (i =0; i< arr.length; i++){
		if (arr[i] !== undefined){
			console.log(arr[i]);
		}
	}
	return arr;
}

function mapPractise(){
	var obj = {};
	obj.color = 'red';
	obj['color'] = 'blue';

	var ex = 'color';
	obj[ex] = 'yellow';

	return obj;
}

function mapPractise2(){
	var objArr = {};

	var i;
	for (i =0; i < 10; i++){
		objArr[i] = 'h1';
	}
	return objArr;
}