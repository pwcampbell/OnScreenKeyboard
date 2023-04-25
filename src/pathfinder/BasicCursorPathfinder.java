package pathfinder;

import java.io.IOException;
import java.io.InputStream;
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
	 * 
	 */
	public BasicCursorPathfinder() {
		keyboard = new char[][] { { 'A', 'B', 'C', 'D', 'E', 'F' }, { 'G', 'H', 'I', 'J', 'K', 'L' },
				{ 'M', 'N', 'O', 'P', 'Q', 'R' }, { 'S', 'T', 'U', 'V', 'W', 'X' }, { 'Y', 'Z', '1', '2', '3', '4' },
				{ '5', '6', '7', '8', '9', '0' } };
		positionMap = new HashMap<Character, int[]>();

		for (int i = 0; i < keyboard.length; i++)
			for (int j = 0; j < keyboard[i].length; j++)
				positionMap.put(keyboard[i][j], new int[] { i, j });
	}

	public List<String> buildPaths(InputStream file) throws IOException {
		StringBuilder sb = new StringBuilder();
		List<String> paths = new ArrayList<String>();
		int fileByte;

		do {
			fileByte = file.read();
			char c = (char) fileByte;
			if (fileByte != -1 && c != '\r' && c != '\n')
				sb.append(c);
			if (c == '\n') {
				paths.addAll(buildPaths(sb.toString()));
				sb.setLength(0);
			}
		} while (fileByte != -1);
		
		if(sb.length() > 0)
			paths.addAll(buildPaths(sb.toString()));
		
		return paths;
	}

	public List<String> buildPaths(String file) {
		List<String> paths = new ArrayList<String>();
		String[] names = file.split("\n");
		for (int i = 0; i < names.length; i++) {
			StringBuilder sb = new StringBuilder();
			for (char c : names[i].toCharArray()) {
				if (c == ' ')
					sb.append("S,");
				else {
					int[] dest = positionMap.get(c);

					while (curY < dest[0]) {
						sb.append("D,");
						curY++;
					}
					while (curY > dest[0]) {
						sb.append("U,");
						curY--;
					}
					while (curX < dest[1]) {
						sb.append("R,");
						curX++;
					}
					while (curX > dest[1]) {
						sb.append("L,");
						curX--;
					}
					sb.append("#,");
				}
			}
			sb.deleteCharAt(sb.length() - 1);
			paths.add(sb.toString());
		}
		return paths;
	}

	public void setCursorPosition(int x, int y) throws IllegalArgumentException {
		if (y < 0 || y >= keyboard.length || x < 0 || x >= keyboard[y].length)
			throw new IllegalArgumentException("Cursor position set out of keyboard bounds");
		curX = x;
		curY = y;
	}

}
