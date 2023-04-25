# On Screen Keyboard

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

Answer:

My assumption here is that we would add some sort of keyboard pagination (i.e. like selecting international or special characters from a mobile keyboard).  I would modify my algorithm as follows:
1) Add a page character page dictionary that maps a character to which character page it appears in (i.e. 'A' -> 0, ';' -> 1, etc)
2) Add coordinates for a page toggle button and a cyclical iterator that tells us which page we are currently on.
3) The updated algorithm would then identify which page the target character is on and (assuming we aren't already on that page) path to the page toggle button and press it until we are on the correct page.
4) Pathfinding from then on would commence normally.

Otherwise, instead of having a single button to toggle character pages, we could store the coordinates of a character page button in a list structure and navigate to that button to set the correct page as described in step 3.

This list would store int arrays corresponding to the coordinates of that page's button location (i.e. {1,1}, {2,1}, {3,1} means that the first page's button is at position {1,1}, the second is at {2,1}, etc).

- What if the interface to get the string changed from a file to stream?

Answer:

My current implementation takes either Strings or InputStreams as input.  Any type of Java stream object should be able to provide input (not necessarily from a file, either) so long as it can be abstracted into an InputStream.
