package main.onscreenkeyboard;

import pathfinder.BasicCursorPathfinder;

public class OnScreenKeyboard {
	protected static final String DEMO_VALUE = "IT CROWD";
	
	public static void main(String[] args) {
		BasicCursorPathfinder bcp = new BasicCursorPathfinder();
		System.out.println("Running OnScreenKeyboard demo...");
		System.out.println("Building path for " + DEMO_VALUE);
		System.out.println(bcp.buildPaths(DEMO_VALUE));
	}
}
