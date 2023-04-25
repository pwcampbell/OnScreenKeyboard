package pathfinder;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicCursorPathfinder implements CursorPathfinder {
	protected char[][] keyboard;
	protected Map<Character, int[]> positionMap;
	protected int curX = 0;
	protected int curY = 0;

	/**
	* Class constructor.
	*
	*/
	public BasicCursorPathfinder() {
		// Initialize a simple keyboard matrix.
		// TODO: Support injection of an arbitrary keyboard matrix.
		// TODO: Add pagination support.
		keyboard = new char[][] { { 'A', 'B', 'C', 'D', 'E', 'F' }, { 'G', 'H', 'I', 'J', 'K', 'L' },
				{ 'M', 'N', 'O', 'P', 'Q', 'R' }, { 'S', 'T', 'U', 'V', 'W', 'X' }, { 'Y', 'Z', '1', '2', '3', '4' },
				{ '5', '6', '7', '8', '9', '0' } };
	
		// Initialize a reverse-index map (i.e. keyboard[0][0] -> 'A' and positionMap.get('A') -> {0, 0}).
		positionMap = new HashMap<Character, int[]>();

		for (int i = 0; i < keyboard.length; i++)
			for (int j = 0; j < keyboard[i].length; j++)
				positionMap.put(keyboard[i][j], new int[] { i, j });
	}

	/**
	* Builds a list of cursor paths from the current cursor position for a stream of incoming text data.
	*
	* @param file  InputStream containing program names to search for.
	* 
	* @throws IOException  If an exception occurs while processing the "file" stream.
	*/
	public List<String> buildPaths(InputStream file) throws IOException {
		List<String> paths = new ArrayList<String>();
		// Create a buffered reader for the file input stream.
		BufferedReader reader = new BufferedReader(new InputStreamReader(file));
		
		// Read each line from the stream, construct a path for it, and append it to the paths list.
		while(reader.ready()) {
			paths.addAll(buildPaths(reader.readLine()));
		}
		
		return paths;
	}

	/**
	* Builds a list of cursor paths from the current cursor position for a flat block of new-line delimited text
	*
	* @param file  String containing program names to search for.
	* 
	*/
	public List<String> buildPaths(String file) {
		List<String> paths = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		// We assume that the "flat file" is delimited by newline characters.
		String[] names = file.split("\n");
		for (int i = 0; i < names.length; i++) {
			// Reset the buffer.
			sb.setLength(0);
			// Read each character in the current line.
			for (char c : names[i].toCharArray()) {
				if (c == ' ')
					sb.append(SPACE);
				else {
					// Lookup the coordinates of the current character.
					int[] dest = positionMap.get(c);

					// Shift the cursor down and add a D command while above the destination.
					while (curY < dest[0]) {
						sb.append(DOWN);
						curY++;
					}
					// Shift the cursor up and add a U command while below the destination.
					while (curY > dest[0]) {
						sb.append(UP);
						curY--;
					}
					// Shift the cursor right and add the R command while left of the destination.
					while (curX < dest[1]) {
						sb.append(RIGHT);
						curX++;
					}
					// Shift the cursor left and add the L command while right of the destination.
					while (curX > dest[1]) {
						sb.append(LEFT);
						curX--;
					}
					// Add the select command.
					sb.append(SELECT);
				}
			}
			// Cull the final, extraneous comma from the new command string.
			sb.deleteCharAt(sb.length() - 1);
			paths.add(sb.toString());
		}
		return paths;
	}

	/**
	 * Updates the internal cursor position
	 * 
	 * @param x  The new x coordinate
	 * @param y  The new y coordinate
	 * 
	 * @throws IllegalArgumentException  If the new coordinates are out of bounds of the internal keyboard matrix
	 */
	public void setCursorPosition(int x, int y) throws IllegalArgumentException {
		if (y < 0 || y >= keyboard.length || x < 0 || x >= keyboard[y].length)
			throw new IllegalArgumentException("Cursor position set out of keyboard bounds");
		curX = x;
		curY = y;
	}

}
