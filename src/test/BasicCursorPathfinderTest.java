package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import pathfinder.BasicCursorPathfinder;

public class BasicCursorPathfinderTest {
	private static final String resourceDir = "resources/";

	@Test
	public void basicStreamTest() {
		int testCount = 0;

		final String[] testInput = new String[] { "test1.txt", "test2.txt", "test3.txt", "test4.txt" };

		final String[][] testOutput = new String[][] {
				{ "D,R,R,#,D,D,L,#,S,U,U,U,R,#,D,D,R,R,R,#,L,L,L,#,D,R,R,#,U,U,U,L,#" },
				{ "D,D,D,R,#,U,U,#,U,R,R,R,#,S,D,D,L,L,#,U,U,R,R,R,#,#,D,L,L,L,#,U,#,R,R,#" },
				{ "#,#,#,#", "#,R,#,L,#,R,#,L,#" },
				{ "R,R,R,R,R,#,D,D,#,U,L,L,L,#,U,R,R,#,D,D,L,L,L,#,U,U,R,R,#,D,D,D,L,L,L,#",
						"U,U,U,R,R,#,D,D,D,L,L,#,U,U,R,R,#" } };

		System.out.println("Running tests...");
		BasicCursorPathfinder bcp;
		try {
			for (int i = 0; i < testInput.length; i++) {
				testCount++;

				ClassLoader classLoader = getClass().getClassLoader();
				InputStream input = classLoader.getResourceAsStream(resourceDir + testInput[i]);
				bcp = new BasicCursorPathfinder();

				List<String> expected = Arrays.asList(testOutput[i]);
				List<String> actual = bcp.buildPaths(input);

				assertEquals(expected, actual);

				System.out.println("Test " + testCount + " passed");
			}
		} catch (AssertionError | IOException e) {
			System.out.println("Test " + testCount + " failed");
			e.printStackTrace();
		} finally {
			System.out.println(testCount + " of " + testInput.length + " tests run");
		}
	}

	@Test
	public void cursorTest() {
		final int[][] testInput = { { 0, 0 }, { 5, 5 } };
		final int[][] testInputException = { { 1, -1 }, { 0, 30 }, { -1, 2 }, { 21, 4 } };
		
		BasicCursorPathfinder bcp = new BasicCursorPathfinder();

		for (int[] pair : testInput)
			bcp.setCursorPosition(pair[0], pair[1]);

		for (int[] pair : testInputException) {
			assertThrows(IllegalArgumentException.class, () -> {
				bcp.setCursorPosition(pair[0], pair[1]);
			});
		}
	}
}
