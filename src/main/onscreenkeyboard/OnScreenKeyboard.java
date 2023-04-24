package main.onscreenkeyboard;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import pathfinder.BasicCursorPathfinder;

public class OnScreenKeyboard {
	private static final String resourceDir = "resources/";
	
	private static String[] testInput = new String[] {
			"test1.txt",
			"test2.txt",
			"test3.txt",
			"test4.txt"
	};
	
	private static String[] testOutput = new String[] {
			"D,R,R,#,D,D,L,#,S,U,U,U,R,#,D,D,R,R,R,#,L,L,L,#,D,R,R,#,U,U,U,L,#",
			"D,D,D,R,#,U,U,#,U,R,R,R,#,S,D,D,L,L,#,U,U,R,R,R,#,#,D,L,L,L,#,U,#,R,R,#",
			"#,#,#,#\n#,R,#,L,#,R,#,L,#",
			"R,R,R,R,R,#,D,D,#,U,L,L,L,#,U,R,R,#,D,D,L,L,L,#,U,U,R,R,#,D,D,D,L,L,L,#\nU,U,U,R,R,#,D,D,D,L,L,#,U,U,R,R,#"
	};
	
	public static void main(String[] args) {
		OnScreenKeyboard osk = new OnScreenKeyboard();
		osk.runTests();
	}
	
	public void runTests() {
		int testCount = 0;
		System.out.println("Running tests...");
		BasicCursorPathfinder bcp;
		try {
			for(int i = 0; i < testInput.length; i++) {
				testCount++;
				
				ClassLoader classLoader = getClass().getClassLoader();
				InputStream input = classLoader.getResourceAsStream(resourceDir + testInput[i]);
				bcp = new BasicCursorPathfinder();
				
				String expected = testOutput[i];
				String actual = bcp.buildPaths(input);
				
				assertEquals(expected, actual);
				
				System.out.println("Test " + testCount + " passed");
			}
		} catch(AssertionError | IOException e) {
			System.out.println("Test " + testCount + " failed");
			e.printStackTrace();
		} finally {
			System.out.println(testCount + " of " + testInput.length + " tests run");
		}
	}
}
