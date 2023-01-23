const keyboard = new Map();
const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890';

// Generate coordinates for each character
function initializeKeyboard() {
  for (var i = 0; i < chars.length; i++) {
    keyboard.set(chars[i], [i % 6, Math.floor(i / 6)]);
  }
}

function pushToArray(array, char, amount) {
  for (var i = 0; i < amount; i++) {
    array.push(char);
  }
}

function generatePath(text) {
  text = text.toUpperCase();

  var path = [];
  var cursor = [0, 0];

  for (var i = 0; i < text.length; i++) {
    if (text[i] == ' ') {
      path.push('S');
      continue;
    }

    var charCoord = keyboard.get(text[i]);

    // Calculate y-axis change
    var vertDiff = charCoord[1] - cursor[1];
    if (vertDiff > 0) {
      pushToArray(path, 'D', Math.abs(vertDiff));
    } else if (vertDiff < 0) {
      pushToArray(path, 'U', Math.abs(vertDiff));
    }

    // Calculate x-axis change
    var horizDiff = charCoord[0] - cursor[0];
    if (horizDiff > 0) {
      pushToArray(path, 'R', Math.abs(horizDiff));
    } else if (horizDiff < 0) {
      pushToArray(path, 'L', Math.abs(horizDiff));
    }

    path.push('#');

    cursor = charCoord;
  }

  return path.join(',');
}

initializeKeyboard();

var path = generatePath('IT Crowd');
console.log(path == 'D,R,R,#,D,D,L,#,S,U,U,U,R,#,D,D,R,R,R,#,L,L,L,#,D,R,R,#,U,U,U,L,#');
