using System.Collections;

if (args.Length != 1)
{
    Console.WriteLine("No arguments, please specific a flat input file name");
}

var keyboardMap = KeyboardMap.BuildKeyboardMap("keyboard.txt");

var searchTerms = GetSearchTerms(args[0]);

foreach (var searchTerm in searchTerms)
{
    var characterPath = new CharacterPath(keyboardMap);
    string path = characterPath.FindPathTraversal((string)searchTerm);

    Console.WriteLine($"{searchTerm}: {path}");
}

/// Read the file to find all searchTerms
ArrayList GetSearchTerms(string filename)
{
    var searchTerms = new ArrayList();
    using (StreamReader sr = new StreamReader(filename))
    {
        while (sr.Peek() >= 0)
        {
            searchTerms.Add(sr.ReadLine());
        }
    }

    return searchTerms;
}
