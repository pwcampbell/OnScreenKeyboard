namespace OnScreenKeyboardManager;

// Represents the position of the cursor in the on-screen keyboard
internal struct KeyCoordinates
{
    internal int Row { get; set; }
    internal int Column { get; set; }
    internal KeyCoordinates(int row, int column)
    {
        Row = row;
        Column = column;
    }
}

// Represents an on-screen keyboard and provides methods for scrpting user input
public sealed class KeyboardManager
{
    // String representations of navigation and selection commands
    const string COMMAND_UP = "U";
    const string COMMAND_DOWN = "D";
    const string COMMAND_LEFT = "L";
    const string COMMAND_RIGHT = "R";
    const string COMMAND_SPACE = "S";
    const string COMMAND_SELECT = "#";

    private static readonly Lazy<KeyboardManager> _lazy = new Lazy<KeyboardManager>(() => new KeyboardManager());
 
    // Returns a singleton instance of KeyboardManager
    public static KeyboardManager Instance
    {
        get { return _lazy.Value; }
    }

    // Maintain mappings between characters and their coordinate positions
    private Dictionary<char, KeyCoordinates> _keyCoordinates = new Dictionary<char, KeyCoordinates>();

    // The current position of the cursot
    private KeyCoordinates _currentPosition;

    // List of navigation commands for the current input string
    private List<string> _navigationCommands = new List<string>();

    // Array representing the layout of the on-screen keyboard
    static char[][] _defaultLayout =
    {
        new char[] {'A', 'B', 'C', 'D', 'E', 'F'},
        new char[] {'G', 'H', 'I', 'J', 'K', 'L'},
        new char[] {'M', 'N', 'O', 'P', 'Q', 'R'},
        new char[] {'S', 'T', 'U', 'V', 'W', 'X'},
        new char[] {'Y', 'Z', '1', '2', '3', '4'},
        new char[] {'5', '6', '7', '8', '9', '0'}
    };

    // Private constructor for the singleton instance
    private KeyboardManager()
    {
        // Map the keyboard characters to their coordinate positions
        for (int i = 0; i < _defaultLayout.Length; i++)
        {
            var row = _defaultLayout[i];
            for (int j = 0; j < row.Length; j++)
            {
                var c = _defaultLayout[i][j];
                _keyCoordinates.Add(c, new KeyCoordinates(i, j));
            }
        }

        // Reset cursor position and commands
        Reset();
    }
 
    // Reset the cursor position and clear the array of navigation commands
    public void Reset()
    {
        _currentPosition = new KeyCoordinates(0, 0);
        _navigationCommands.Clear();
    }

    // Returns a string of scripting commands given an input string
    public string GenerateScript(string input)
    {
        Reset();
        foreach (char c in input.ToUpper())
        {
            NavigateToCharacter(c);
        }
        return string.Join(',', _navigationCommands);
    }

    // Process an individual character of the input string
    private void NavigateToCharacter(char c)
    {
        // For spaces, simply emit a command
        if (c == ' ')
        {
            _navigationCommands.Add(COMMAND_SPACE);
            return;
        }
        
        // Throw an exception if the character is invalid
        if (!_keyCoordinates.ContainsKey(c))
        {
            throw new ArgumentException(string.Format("Non-alphanumeric character detected: {0}", c));
        }

        // Lookup the coordinates of the character and compute the offsets from the current position
        KeyCoordinates next = _keyCoordinates[c];
        int rowDelta = next.Row - _currentPosition.Row;
        int columnDelta = next.Column - _currentPosition.Column;

        // Emit up/down command(s) for row changes
        if (rowDelta != 0)
        {
            var cmd = (rowDelta > 0) ? COMMAND_DOWN : COMMAND_UP;
            var n = (rowDelta > 0) ? rowDelta : -rowDelta;
            while (n-- > 0)
            {
                _navigationCommands.Add(cmd);
            }
        }
        
        // Emit left/right command(s) for column changes
        if (columnDelta != 0)
        {
            var cmd = (columnDelta > 0) ? COMMAND_RIGHT : COMMAND_LEFT;
            var n = (columnDelta > 0) ? columnDelta : -columnDelta;
            while (n-- > 0)
            {
                _navigationCommands.Add(cmd);
            }
        }

        // Select the character and update the current position
        _navigationCommands.Add(COMMAND_SELECT);
        _currentPosition = next;
    }
}
