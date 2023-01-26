# On Screen Keyboard

## The Implementation

I briefly considered building this up as a full Rails project, but decided it would probably be easier to test as a stand-alone script.

1. Adds three methods to Ruby's String class:
   - `self.default_key_map()` - returns the 6x6 alphanumeric key map specified in the README.md
   - `self.initialize_key_map(key_map)` - builds class lookup dictionary based on key map specified
   - `to_key_jumps()` - converts any string to key jumps using the current key map

2. For speed and simplicity, this was implemented using a class variable under the String class that holds the initialized key location dictionary.

3. Includes three test cases when run as a script -- just to be sure:
   - one with the default key map
   - one with a 1x36 key map (only shows L and R movements)
   - one with a 1x3 key map (only shows L and R movements for the 3 keys found in this key map)

4. Looks for three different ways to input the strings:
   - one or more strings passed in as command line parameters
   - one or more flat files specified as command line parameters
   - reads from STDIN for piped data from other processes or terminal input


## Demonstation Output Using the Default Key Map:

### Input as one or more command line parameters:
```
$ ./OnScreenKeyboard.rb 'IT Crowd' 'The Office' 'Parks And Recreation'
```
```
IT Crowd -> D,R,R,#,D,D,L,#,S,U,U,U,R,#,D,D,R,R,R,#,L,L,L,#,D,R,R,#,U,U,U,L,#
The Office -> D,D,D,R,#,U,U,#,U,R,R,R,#,S,D,D,L,L,#,U,U,R,R,R,#,#,D,L,L,L,#,U,#,R,R,#
Parks And Recreation -> D,D,R,R,R,#,U,U,L,L,L,#,D,D,R,R,R,R,R,#,U,L,#,D,D,L,L,L,L,#,S,U,U,U,#,D,D,R,#,U,U,R,R,#,S,D,D,R,R,#,U,U,L,#,L,L,#,D,D,R,R,R,#,U,U,L,#,L,L,L,L,#,D,D,D,R,#,U,U,R,#,D,#,L,#
```

### Input from a flat file via the command line
```
$ ./OnScreenKeyboard.rb movie_list.txt
```
```
Gone With the Wind (1939) -> D,#,D,R,R,#,L,#,U,U,R,R,R,#,S,D,D,D,#,U,U,L,L,#,D,D,L,#,U,U,#,S,D,D,#,U,U,#,U,R,R,R,#,S,D,D,D,#,U,U,L,L,#,D,L,#,U,U,R,R,#,S,D,D,D,D,L,#,D,R,R,#,U,#,D,#
Star Wars: Episode IV - A New Hope (1977) -> D,D,D,#,R,#,U,U,U,L,#,D,D,R,R,R,R,R,#,S,D,L,#,U,U,U,L,L,L,L,#,D,D,R,R,R,R,R,#,D,L,L,L,L,L,#,S,U,U,U,R,R,R,R,#,D,D,L,#,U,L,#,D,D,L,L,#,U,R,R,#,U,U,R,#,R,#,S,D,L,L,#,D,D,R,#,S,S,U,U,U,L,L,L,#,S,D,D,R,#,U,U,R,R,R,#,D,D,D,#,S,U,U,L,L,L,#,D,R,#,R,#,U,U,R,#,S,D,D,D,D,L,L,#,D,R,R,#,L,L,#,#
The Sound of Music (1965) -> D,D,D,R,#,U,U,#,U,R,R,R,#,S,D,D,D,L,L,L,L,#,U,R,R,#,D,#,U,L,#,U,U,R,R,#,S,D,D,L,#,U,U,R,R,R,#,S,D,D,L,L,L,L,L,#,D,R,R,#,L,L,#,U,U,R,R,#,U,#,S,D,D,D,D,#,D,R,R,#,L,L,L,#,L,#
E. T. The Extra-Terrestrial (1982) -> R,R,R,R,#,S,D,D,D,L,L,L,#,S,#,U,U,#,U,R,R,R,#,S,#,D,D,D,R,#,L,L,L,L,#,U,R,R,R,R,#,U,U,L,L,L,L,L,#,D,D,D,R,#,U,U,U,R,R,R,#,D,D,R,#,#,U,U,L,#,D,D,D,L,L,L,L,#,R,#,U,R,R,R,R,#,U,L,L,L,#,U,L,L,#,D,R,R,R,R,R,#,S,D,D,D,L,L,L,#,D,R,R,#,L,#,U,#
Titanic (1997) -> D,D,D,R,#,U,U,R,#,D,D,L,#,U,U,U,L,#,D,D,R,#,U,R,#,U,#,S,D,D,D,D,#,D,R,R,#,#,L,L,#
The Ten Commandments (1956) -> D,D,D,R,#,U,U,#,U,R,R,R,#,S,D,D,D,L,L,L,#,U,U,U,R,R,R,#,D,D,L,L,L,#,S,U,U,R,#,D,D,#,L,L,#,#,U,U,#,D,D,R,#,U,U,R,R,#,D,D,L,L,L,#,U,U,R,R,R,R,#,D,D,L,L,L,#,D,#,L,#,S,D,R,R,#,D,R,R,#,L,L,L,L,#,R,#
Jaws (1975) -> D,R,R,R,#,U,L,L,L,#,D,D,D,R,R,R,R,#,L,L,L,L,#,S,D,R,R,#,D,R,R,#,L,L,#,L,L,#
Doctor Zhivago (1965) -> R,R,R,#,D,D,L,#,U,U,#,D,D,D,L,#,U,R,#,R,R,R,#,S,D,D,L,L,L,L,#,U,U,U,#,R,#,D,D,R,#,U,U,U,L,L,L,#,D,#,D,R,R,#,S,D,D,#,D,R,R,#,L,L,L,#,L,#
The Exorcist (1973) -> D,D,D,R,#,U,U,#,U,R,R,R,#,S,#,D,D,D,R,#,U,L,L,L,#,R,R,R,#,U,U,L,L,L,#,D,#,D,D,L,L,#,R,#,S,D,R,#,D,R,R,#,L,L,#,U,R,R,#
Snow White and the Seven Dwarfs (1937) -> D,D,D,#,U,R,#,R,#,D,R,R,#,S,#,U,U,L,L,L,#,R,#,D,D,L,#,U,U,U,R,R,R,#,S,L,L,L,L,#,D,D,R,#,U,U,R,R,#,S,D,D,D,L,L,#,U,U,#,U,R,R,R,#,S,D,D,D,L,L,L,L,#,U,U,U,R,R,R,R,#,D,D,D,L,#,U,U,U,R,#,D,D,L,L,L,#,S,U,U,R,R,#,D,D,D,R,#,U,U,U,L,L,L,L,#,D,D,R,R,R,R,R,#,U,U,#,D,D,D,L,L,L,L,L,#,S,D,R,R,#,D,R,R,#,U,#,D,L,L,#
```

### Input from a pipe (stream)
```
$ head -3 movie_list.txt | ./OnScreenKeyboard.rb 
```
```
Gone With the Wind (1939) -> D,#,D,R,R,#,L,#,U,U,R,R,R,#,S,D,D,D,#,U,U,L,L,#,D,D,L,#,U,U,#,S,D,D,#,U,U,#,U,R,R,R,#,S,D,D,D,#,U,U,L,L,#,D,L,#,U,U,R,R,#,S,D,D,D,D,L,#,D,R,R,#,U,#,D,#
Star Wars: Episode IV - A New Hope (1977) -> D,D,D,#,R,#,U,U,U,L,#,D,D,R,R,R,R,R,#,S,D,L,#,U,U,U,L,L,L,L,#,D,D,R,R,R,R,R,#,D,L,L,L,L,L,#,S,U,U,U,R,R,R,R,#,D,D,L,#,U,L,#,D,D,L,L,#,U,R,R,#,U,U,R,#,R,#,S,D,L,L,#,D,D,R,#,S,S,U,U,U,L,L,L,#,S,D,D,R,#,U,U,R,R,R,#,D,D,D,#,S,U,U,L,L,L,#,D,R,#,R,#,U,U,R,#,S,D,D,D,D,L,L,#,D,R,R,#,L,L,#,#
The Sound of Music (1965) -> D,D,D,R,#,U,U,#,U,R,R,R,#,S,D,D,D,L,L,L,L,#,U,R,R,#,D,#,U,L,#,U,U,R,R,#,S,D,D,L,#,U,U,R,R,R,#,S,D,D,L,L,L,L,L,#,D,R,R,#,L,L,#,U,U,R,R,#,U,#,S,D,D,D,D,#,D,R,R,#,L,L,L,#,L,#
```

### Input via STDIN from the terminal, closed with a new line followed by a ctrl-D
```
$ ./OnScreenKeyboard.rb 
interactive
terminal
input
[ctrl-D]
```
```
interactive -> D,R,R,#,D,L,#,D,#,U,U,U,R,R,R,#,D,D,R,#,U,U,L,L,L,L,L,#,R,R,#,D,D,D,L,#,U,U,R,#,D,D,R,#,U,U,U,R,#
terminal -> D,D,D,R,#,U,U,U,R,R,R,#,D,D,R,#,L,L,L,L,L,#,U,R,R,#,D,L,#,U,U,L,#,D,R,R,R,R,R,#
input -> D,R,R,#,D,L,#,R,R,#,D,L,#,L,#
```

---
---

## The Problem

On screen keyboards are the bane of DVR users. To help alleviate the pain, one local company is asking you to implement part of a voice to text search for their DVR by developing an algorithm to script the on screen keyboard.
The keyboard is laid out as follows:

```
ABCDEF
GHIJKL
MNOPQR
STUVWX
YZ1234
567890
```

Please write a program which scripts the path of the cursor on the keyboard. The program should:

1. Accept a flat file as input.
   1. Each new line will contain a search term
2. Output the path for the DVR to execute for each line
   1. Assume the cursor will always start on the A
   2. Use the following characters to make up the path
      1. U = up
      2. D = down
      3. L = left
      4. R = right
      5. S = space
      6. \# = select
3. Comma delimit the result

## Sample Input

IT Crowd

## Sample Output

D,R,R,#,D,D,L,#,S,U,U,U,R,#,D,D,R,R,R,#,L,L,L,#,D,R,R,#,U,U,U,L,#

## The Fine Print

Please use whatever technology and techniques you feel are applicable to solve the problem. We suggest that you approach this exercise as if this code was part of a larger system. The end result should be representative of your abilities and style.

Please fork this repository. When you have completed your solution, please issue a pull request to notify us that you are ready.

Have fun.

## Things To Consider

Here are a couple of thoughts about the domain that could influence your response:

- There is no guarantee that the keyboard layout will continue to be alphanumeric. How might you plan for this in your code?
- What if the interface to get the string changed from a file to stream?