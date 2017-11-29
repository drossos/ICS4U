var toggle = false;

function onClick(){
	var div = document.getElementById('body');
	var mikeM = "https:i.ytimg.com/vi/J7BOjllZTd0/hqdefault.jpg?sqp=-oaymwEWCKgBEF5IWvKriqkDCQgBFQAAiEIYAQ==&rs=AOn4CLCmQnq4fWwLLf1bJd5viT_DZwwp2g";
	var woody = "https://images-na.ssl-images-amazon.com/images/I/71A4-YuUrkL._AC_UL320_SR314,320_.jpg";

	if (!toggle){
		div.style.backgroundImage = mikeM;
		toggle = true;
	} else {
		div.style.backgroundImage = woody;
		toggle = false;
	}
}