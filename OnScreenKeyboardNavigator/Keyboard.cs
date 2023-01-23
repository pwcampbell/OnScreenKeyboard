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
            // Defaults to generic keyboard configuration if keyboard.config is not found.
            if (!File.Exists(KEYBOARD_CONFIG_FILE_NAME))
            {
                using (StreamWriter sw = File.AppendText(KEYBOARD_CONFIG_FILE_NAME))
                {
                    sw.WriteLine(DEFAULT_KEYBOARD_LAYOUT);
                }
            }

            GenerateKeys();
        }

        /// <summary>
        /// Get location of specific character on the keyboard.
        /// </summary>
        /// <param name="c">Character to retrieve</param>
        /// <returns>Int array containting x and y coordinates of character</returns>
        /// <exception cref="Exception"></exception>
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

        /// <summary>
        /// Generates the Keys dictionary from file.
        /// </summary>
        /// <exception cref="Exception"></exception>
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
