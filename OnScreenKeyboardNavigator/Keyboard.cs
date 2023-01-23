using System.Diagnostics;
using System.Diagnostics.Metrics;
using System.IO;

namespace OnScreenKeyboardNavigator
{
    public class Keyboard
    {
        private Dictionary<char, int[]> Keys = new Dictionary<char, int[]>();
        private const string KEYBOARD_CONFIG_FILE_NAME = "keyboard.config";
        private const string DEFAULT_KEYBOARD_LAYOUT = "ABCDEF\nGHIJKL\nMNOPQR\nSTUVWX\nYZ1234\n567890";

        public Keyboard()
        {
            if (!File.Exists(KEYBOARD_CONFIG_FILE_NAME))
            {
                using (StreamWriter sw = File.AppendText(KEYBOARD_CONFIG_FILE_NAME))
                {
                    sw.WriteLine(DEFAULT_KEYBOARD_LAYOUT);
                }
            }

            GenerateKeys();
        }

        public int[] GetKey(char c)
        {
            if (Keys.ContainsKey(c))
            {
                return Keys[c];
            }
            else
            {
                throw new Exception("Character not found in keyboard config: " + c);
            }
        }

        private void GenerateKeys()
        {
            int y = 0;
            foreach (string s in File.ReadLines(KEYBOARD_CONFIG_FILE_NAME))
            {
                int x = 0;
                foreach (char c in s)
                {
                    var key = char.ToUpper(c);

                    if (Keys.TryGetValue(key, out _))
                    {
                        throw new Exception("Duplicate character in keyboard config: " + key);
                    }
                    Keys.Add(key, new int[] { x++, y });
                }

                y++;
            }
        }
    }
}
