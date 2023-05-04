const keyboard = new Map();
const keyboardChars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890'
const numColumns = 6;

function createKeyboardPath (inputText){
	//if inputText is empty or null, just end
	if(inputText === null || inputText === "") return "!! The input string is empty or null";
	
	//first, make the case uniform
	inputText = inputText.toUpperCase();

	//set the starting coordinate to be [0,0] (top left)
	var cursor = [0,0];
	var keyPath = "";

	//if inputText is not empty then lets start walking
	for(var i = 0; i < inputText.length; i++){
		//if it's a space just add it, otherwise:
		if(inputText[i] == ' '){
			keyPath = keyPath + 'S' + ',';
		}
		else{
			//for each character, look it up in the map.
			//the map will have the coordinate of each character
			var coordPair = keyboard.get(inputText[i]);
			
			if(coordPair == undefined) return "!! Contains a character that is not on the keyboard";
			
			//now that we have the coordinate, we can calculate the difference in the coordinates
			//first the x delta
			var deltaX = coordPair[0] - cursor[0];
			//if charX - currX > 0 then we are moving right
			//if charX - currX < 0 then we are moving left
			//we need to add as many Rs or Ls as the deltaX
			if(deltaX > 0){
				keyPath = addDirectionCharsToPath(keyPath, 'R', Math.abs(deltaX));
			}
			else if(deltaX < 0){
				keyPath = addDirectionCharsToPath(keyPath, 'L', Math.abs(deltaX));
			}
			//if it is equal to zero then we aren't moving left or right, only up and down. so we will add nothing here.
			
			//then the y delta
			var deltaY = coordPair[1] - cursor[1];
			//if charY - currY > 0 then we are moving down
			//if charY - currY < 0 then we are moving up
			//we need to add as many Rs or Ls as the deltaX
			if(deltaY > 0){
				keyPath = addDirectionCharsToPath(keyPath, 'D', Math.abs(deltaY));
			}
			else if(deltaY < 0){
				keyPath = addDirectionCharsToPath(keyPath, 'U', Math.abs(deltaY));
			}
			//if it is equal to zero then we aren't moving up and down, only left and right. so we will add nothing here.
		}
		//when we have added all the directions, we select the character using #
		keyPath += '#';
		
		//if we aren't on the last iteration, we need to add in a comma here to make a proper string
		if(i < inputText.length - 1){
			keyPath += ',';
		}
		
		//make sure the cursor is set to the coordinate of the character we just selected before we continue
		cursor = coordPair;
	}

	return keyPath;
}

function setUpKeyboard () {
	//seems like this is the most important part of the problem

	//first things first we can make a coordinate map for each letter like:
	// A: [0,0]
	// B: [0,1]
	// and so forth. like setting up a vector graph
	//But how many columns are there? Can we set this up in a timely manner?

	//do something like i % numColumns (for us that's 6)
	//so first is 0/6 r= 0 for x
	//second is 1/6 r= 1 for x
	//third is 2/6 r= 2 for x
	//sixth is 6/6 r= 0 for x
	//seventh is 7/6 r= 1 for x
	//this creates the LR coordinates

	//what about y? for the above example we need it to be like [0,0] [1,0] [2,0]
	//something like the mod function above but only changes after we reach 6
	//0/6 = 0
	//1/6 = 0.16
	//2/6 = 0.33
	//6/6 = 1
	//7/6 = 1.16
	//so just the same division as the modulo but this is truncating the decimal
	
	//does this allow us to do this with a single pass along the characters?

	for(var i = 0; i < keyboardChars.length; i++){
		keyboard.set(keyboardChars[i], [i % numColumns, Math.trunc(i/numColumns)]);
	}
}

function addDirectionCharsToPath(pathString, charToAdd, numOfMoves){
	//this function doesn't exist in my comments-only file because I didn't realize I would want/need it until I began implementing and realized it would be easier
	for (var i = 0; i < numOfMoves; i++) {
		pathString = pathString + charToAdd + ',';
    }
	return pathString;
}

function verifyOutput (pathString){
	//this function is also not in my comments-only file because I only started doing this after I finished the main problem when I was
	//thinking about how I could reasonably verify that all this is working the way I think it is.
	//I couldn't think of a good way to unravel the map I created for the keyboard when I was doing this. I'm sure a way exists but I was
	//mostly doing this just as a sanity check and test given the paramters of the problem. If we change the keyboard layout in the main
	//code, we will also need to change the keyboard layout here in the verifier.
	
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

	//due to the difference between a map and indexing a 2d array like this, I have to flip the X and Y to make this work
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
				//add a space
				verificationString += ' ';
				addSpace = false;
			}
			else{
				//add whatever is at the cursor
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