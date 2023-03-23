/// Build an on screen keyboard coordinate map
class KeyboardMap
{
    /// Create an on screen keyboard character lookup, where each key is the displayed character and the value is the
    /// X, Y coordinate tuple.
    static public SortedDictionary<char, Point> BuildKeyboardMap(string keyboardFilename)
    {
        var keyboardMap = new SortedDictionary<char, Point>();

        using (StreamReader sr = new StreamReader(keyboardFilename))
        {
            int x = 0;
            int y = 0;
            while (sr.Peek() >= 0)
            {
                var character = (char)sr.Read();
                if (character == '\n')
                {
                    x = 0;
                    y++;
                    continue;
                }

                keyboardMap.Add(character, new Point(x, y));
                x++;
            }
        }

        return keyboardMap;
    }
}
