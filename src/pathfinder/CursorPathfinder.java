package pathfinder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface CursorPathfinder {
	static final String UP = "U,";
	static final String DOWN = "D,";
	static final String LEFT = "L,";
	static final String RIGHT = "R,";
	static final String SELECT = "#,";
	static final String SPACE = "S,";
	
	public List<String> buildPaths(InputStream file) throws IOException;
	public List<String> buildPaths(String file);
	public void setCursorPosition(int x, int y) throws IllegalArgumentException;
}
