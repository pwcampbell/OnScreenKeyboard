const keyboard = new Map();
const keyboardChars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890'
const numColumns = 6;

function createKeyboardPath (inputText){
	if(inputText === null || inputText === "") return "!! The input string is empty or null";
	
	inputText = inputText.toUpperCase();

	var cursor = [0,0];
	var keyPath = "";

	for(var i = 0; i < inputText.length; i++){
		if(inputText[i] == ' '){
			keyPath = keyPath + 'S' + ',';
		}
		else{
			var coordPair = keyboard.get(inputText[i]);
			
			if(coordPair == undefined) return "!! Contains a character that is not on the keyboard";

			var deltaX = coordPair[0] - cursor[0];
			if(deltaX > 0){
				keyPath = addDirectionCharsToPath(keyPath, 'R', Math.abs(deltaX));
			}
			else if(deltaX < 0){
				keyPath = addDirectionCharsToPath(keyPath, 'L', Math.abs(deltaX));
			}
			
			var deltaY = coordPair[1] - cursor[1];
			if(deltaY > 0){
				keyPath = addDirectionCharsToPath(keyPath, 'D', Math.abs(deltaY));
			}
			else if(deltaY < 0){
				keyPath = addDirectionCharsToPath(keyPath, 'U', Math.abs(deltaY));
			}
		}
		keyPath += '#';
		
		if(i < inputText.length - 1){
			keyPath += ',';
		}
		
		cursor = coordPair;
	}

	return keyPath;
}

function setUpKeyboard () {
	for(var i = 0; i < keyboardChars.length; i++){
		keyboard.set(keyboardChars[i], [i % numColumns, Math.trunc(i/numColumns)]);
	}
}

function addDirectionCharsToPath(pathString, charToAdd, numOfMoves){
	for (var i = 0; i < numOfMoves; i++) {
		pathString = pathString + charToAdd + ',';
    }
	return pathString;
}

function verifyOutput (pathString){
	if(pathString[0] === '!' && pathString[1] === '!') return "cannot verify";
	var verificationCursor = [0,0];
	var verificationString = "";
	var addSpace = false;
	
	var verificationKeyboard = [
	['A', 'B', 'C', 'D', 'E', 'F'],
	['G', 'H', 'I', 'J', 'K', 'L'],
	['M', 'N', 'O', 'P', 'Q', 'R'],
	['S', 'T', 'U', 'V', 'W', 'X'],
	['Y', 'Z', '1', '2', '3', '4'],
	['5', '6', '7', '8', '9', '0']	
	];
	
	var arrayPath = pathString.split(',');
	for(var i = 0; i < arrayPath.length; i++){
		if(arrayPath[i] == 'R'){
			verificationCursor[1] += 1;
		}
		else if(arrayPath[i] == 'L'){
			verificationCursor[1] -= 1;
		}
		else if(arrayPath[i] == 'U'){
			verificationCursor[0] -= 1;
		}
		else if(arrayPath[i] == 'D'){
			verificationCursor[0] += 1;
		}
		else if(arrayPath[i] == 'S'){
			addSpace = true;
		}
		else if(arrayPath[i] == '#'){
			if(addSpace){
				verificationString += ' ';
				addSpace = false;
			}
			else{
				verificationString += verificationKeyboard[verificationCursor[0]][verificationCursor[1]];
			}
		}	
	}
	return verificationString;
}

function driveFromFileInput(input){
	setUpKeyboard();
	
	let file = input.files[0];
	let reader = new FileReader();
	reader.readAsText(file);
	reader.onload = function(){
		var lines = reader.result.split("\r\n");
		var mainContent = document.getElementById("mainContent");
		for(var i = 0; i < lines.length; i++){
			var fileLineDiv = document.createElement("div");
			fileLineDiv.setAttribute("id", "fileLine"+i);
			fileLineDiv.appendChild(document.createTextNode("input line: " + lines[i]));
			mainContent.appendChild(fileLineDiv);
			
			var path = createKeyboardPath(lines[i]);
			var pathDiv = document.createElement("div");
			pathDiv.setAttribute("id", "path"+i);
			pathDiv.appendChild(document.createTextNode("path: " + path));
			mainContent.appendChild(pathDiv);
			
			var verifyDiv = document.createElement("div");
			verifyDiv.setAttribute("id", "verification"+i);
			verifyDiv.appendChild(document.createTextNode("verify: " + verifyOutput(path)));
			mainContent.appendChild(verifyDiv);
			
			mainContent.appendChild(document.createElement("hr"));
		}
	};
	reader.onerror = function(){
		alert(reader.error);
	}
}

//uncomment the below code to run a manual test with output to the browser console
/*setUpKeyboard();
var path = createKeyboardPath("IT Crowd");
console.log("Path: ", path);
console.log("Verify: ", verifyOutput(path));

var path1 = createKeyboardPath("The Office");
console.log("Path: ", path1);
console.log("Verify: ", verifyOutput(path1));

var path2 = createKeyboardPath("Beverly Hills 90210");
console.log("Path: ", path2);
console.log("Verify: ", verifyOutput(path2));

var path3 = createKeyboardPath("My Brother & Me");
console.log("Path: ", path3);
console.log("Verify: ", verifyOutput(path3));

var path4 = createKeyboardPath("Tim and Eric Awesome Show Great Job");
console.log("Path: ", path4);
console.log("Verify: ", verifyOutput(path4));

var path5 = createKeyboardPath("Tim and Eric Awesome Show, Great Job!");
console.log("Path: ", path5);
console.log("Verify: ", verifyOutput(path5));

var path6 = createKeyboardPath("Westworld");
console.log("Path: ", path6);
console.log("Verify: ", verifyOutput(path6));

var path7 = createKeyboardPath("");
console.log("Path: ", path7);
console.log("Verify: ", verifyOutput(path7));

var path8 = createKeyboardPath(null);
console.log("Path: ", path8);
console.log("Verify: ", verifyOutput(path8));

var path9 = createKeyboardPath("South Park");
console.log("Path: ", path9);
console.log("Verify: ", verifyOutput(path9));*/