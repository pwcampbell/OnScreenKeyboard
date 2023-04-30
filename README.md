# On Screen Keyboard

## Description

This is a simple on-screen keyboard written in Kotlin.

Code can be found in [src/main/kotlin/com/epicstar/OnScreenKeyboard.kt](./src/main/kotlin/com/epicstar/OnScreenKeyboard.kt).

For usage of the `OnScreenKeyboard`, see:
1. The jar: [Main.kt](./src/main/kotlin/Main.kt)
2. Unit tests: [OnScreenKeyboardTest.kt](./src/test/kotlin/com/epicstar/OnScreenKeyboardTest.kt)

I used IntelliJ Community Edition, but you're free to use any text editor of IDE of choice as long as
you use `./gradlew` to build or run. However, you may not have the same linter defined in IntelliJ.

See below for how to use.

## Usage

### Prerequisites

1. Java JDK 17+:
   1. [Windows Installation using Choco](https://community.chocolatey.org/packages/oracle17jdk)
   2. Ubuntu: `apt install openjdk-17-jdk`
   3. [MacOS using Homebrew](https://formulae.brew.sh/formula/openjdk@17)
2. Text file of search strings. NOTE: You can simply use [./samples/sample.txt](./samples/sample.txt)

### Build the JAR

```bash
./gradlew jar
```

The jar will save to `./build/libs`.

### Run the JAR

Find the jar file
```bash
cd build/libs
```

Now run the jar with [./samples/sample.txt](./samples/sample.txt):
```bash
java -jar OnScreenKeyboard-1.0.0.jar ../../samples/sample.txt
```

Or you can use your own txt file!

### Run the unit tests

```bash
./gradlew test
```


## The Problem

On screen keyboards are the bane of DVR users. To help alleviate the pain, one local company is asking you to implement part of a voice to text search for their DVR by developing an algorithm to script the on-screen keyboard.
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
