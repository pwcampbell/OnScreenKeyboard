package pathfinder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class BasicCursorPathfinder implements CursorPathfinder {
	protected char[][] keyboard;
	protected Map<Character, int[]> positionMap;
	protected int curX = 0;
	protected int curY = 0;
	
	public BasicCursorPathfinder() {
		keyboard = new char[][] {
			{'A','B','C','D','E','F'},
			{'G','H','I','J','K','L'},
			{'M','N','O','P','Q','R'},
			{'S','T','U','V','W','X'},
			{'Y','Z','1','2','3','4'},
			{'5','6','7','8','9','0'}
		};
		positionMap = new HashMap<Character, int[]>();
		
		for(int i = 0; i < keyboard.length; i++)
			for(int j = 0; j < keyboard[i].length; j++)
				positionMap.put(keyboard[i][j], new int[]{i,j});
	}
	
	public String buildPaths(InputStream file) throws IOException{
		StringBuilder sb = new StringBuilder();
		int fileByte;
		
		do {
			fileByte = file.read();
			char c = (char)fileByte;
			if(fileByte != -1 && c != '\r')
				sb.append(c);
		} while (fileByte != -1);
		
		return buildPaths(sb.toString());
	}
	
	public String buildPaths(String file) {
		StringBuilder sb = new StringBuilder();
		String[] names = file.split("\n");
		for(int i = 0; i < names.length; i++) {
			for(char c : names[i].toCharArray()) {
				if(c == ' ')
					sb.append("S,");
				else {
					int[] dest = positionMap.get(c);
					
					while(curY < dest[0]) {
						sb.append("D,");
						curY++;
					}
					while(curY > dest[0]) {
						sb.append("U,");
						curY--;
					}
					while(curX < dest[1]) {
						sb.append("R,");
						curX++;
					}
					while(curX > dest[1]) {
						sb.append("L,");
						curX--;
					}
					sb.append("#,");
				}
			}
			sb.deleteCharAt(sb.length() - 1);
			if(i < names.length - 1)
				sb.append('\n');
		}
		return sb.toString();
	}

	public void setCursorPosition(int x, int y) throws IllegalArgumentException {
		curX = x;
		curY = y;
	}

}
