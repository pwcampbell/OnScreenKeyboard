package pathfinder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface CursorPathfinder {
	public List<String> buildPaths(InputStream file) throws IOException;
	public List<String> buildPaths(String file);
	public void setCursorPosition(int x, int y) throws IllegalArgumentException;
}
