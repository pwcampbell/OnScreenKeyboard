using OnScreenKeyboardManager;
var lines = File.ReadLines(args[0]);
foreach (var line in lines)
{
    Console.WriteLine(KeyboardManager.Instance.GenerateScript(line));
}
