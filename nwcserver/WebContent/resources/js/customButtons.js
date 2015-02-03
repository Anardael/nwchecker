//

var buttons = [];

	/** EXAMPLE OF ADDING CUSTOM BUTTON 
	var anyUniqueNameStartingOnLetter = {
		name: "nameOfButton",      //will be used as button's class for customization
		title: "buttonTitle",
		location: "printPanel",    //to what panel button will be attached
		onclick: "buttonAction()"  //function that will be called after button clicking
	};
	
	buttons.push(anyUniqueNameStartingOnLetter);
	
	function buttonAction() {
		alert("hello world!");     //Code invoked on button click
	}
	** END OF EXAMPLE*/

function getButtons() {
	return buttons;
}