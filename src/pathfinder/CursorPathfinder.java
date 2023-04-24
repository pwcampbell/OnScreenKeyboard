package pathfinder;

public interface CursorPathfinder {
	public String buildPaths(String file);
	public void setCursorPosition(int x, int y) throws IllegalArgumentException;
}
