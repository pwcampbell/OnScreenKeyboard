const keyboard = new Map();
const keyboardChars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890'

function createKeyboardPath (inputText){
	//if inputText is empty or null, just end
	if(inputText === null || inputText === "") return "";
	
	//first, make the case uniform
	inputText = inputText.toUpperCase();

	//set the starting coordinate to be [0,0] (top left)
	var cursor = [0,0];
	var keyPath = "";

	//if inputText is not empty then lets start walking
	for(var i = 0; i < inputText.length; i++){
		//if it's a space just add it, otherwise:
		
		//for each character, look it up in the map.
		//the map will have the coordinate of each character
		
		//now that we have the coordinate, we can calculate the difference in the coordinates
		//first the x delta
		  //if charX - currX > 0 then we are moving right
		  //if charX - currX < 0 then we are moving left
		  //we need to add as many Rs or Ls as the deltaX
		  //if it is equal to zero then we aren't moving left or right, only up and down. so we will add nothing here.
		
		//then the y delta
		  //if charY - currY > 0 then we are moving down
		  //if charY - currY < 0 then we are moving up
		  //we need to add as many Us or Ds as the deltaY
		  //if it is equal to zero then we aren't moving up and down, only left and right. so we will add nothing here.
		  
		//when we have added all the directions, we select the character using #
		//if we aren't on the last iteration, we need to add in a comma here to make a proper string
		//make sure the cursor is set to the coordinate of the character we just selected before we continue
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
}