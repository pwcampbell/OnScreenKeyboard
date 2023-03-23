using System.Text;

/// Find the full path of a search term with a given on screen keyboard
class CharacterPath
{
    private StringBuilder path;
    private Point currentLocation;
    private SortedDictionary<char, Point> keyboardMap;

    public CharacterPath(SortedDictionary<char, Point> kMap)
    {
        path = new StringBuilder();
        currentLocation = new Point(0, 0);
        keyboardMap = kMap;
    }

    /// Find the full traversal path of a search term
    public string FindPathTraversal(string searchTerm)
    {
        try
        {
            foreach (var character in searchTerm)
            {
                AppendCharacterPath(character);
            }

            // Instead of checking everywhere if the string builder is empty, always add a preceding comma and strip it
            // here, just before processing completes
            path.Remove(0,1);
            return path.ToString();
        }
        catch (Exception)
        {
            return "Search term contains an invalid character";
        }
    }

    /// Figure out how many path traversals and direction to add to the path stringBuilder
    private void AppendCharacterPath(char character)
    {
        if (character == ' ')
        {
            path.Append(",S");
            return;
        }

        var charKey = Char.ToUpper(character);
        if (!keyboardMap.ContainsKey(charKey))
        {
            throw new Exception();
        }

        var targetLocation = keyboardMap[charKey];

        if (currentLocation.Y < targetLocation.Y)
        {
            AppendToPath(targetLocation.Y - currentLocation.Y, ",D");
        }

        if (currentLocation.Y > targetLocation.Y)
        {
            AppendToPath(currentLocation.Y - targetLocation.Y, ",U");
        }

        if (currentLocation.X < targetLocation.X)
        {
            AppendToPath(targetLocation.X - currentLocation.X, ",R");
        }

        if (currentLocation.X > targetLocation.X)
        {
            AppendToPath(currentLocation.X - targetLocation.X, ",L");
        }

        currentLocation = targetLocation;
        path.Append(",#");
        return;
    }

    /// Helper function to put multiple commands on the path
    private void AppendToPath(int distance, string direction)
    {
        for (int i = 0; i < distance; i++)
        {
            path.Append(direction);
        }
    }
}
