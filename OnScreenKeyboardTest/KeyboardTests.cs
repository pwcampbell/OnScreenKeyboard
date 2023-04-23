namespace OnScreenKeyboardTest;
using OnScreenKeyboardManager;

[TestClass]
public class KeyboardTests
{
    [TestMethod]
    public void SampleTest()
    {
        // Test the sample string
        string result = KeyboardManager.Instance.GenerateScript("IT CROWD");
        Assert.AreEqual("D,R,R,#,D,D,L,#,S,U,U,U,R,#,D,D,R,R,R,#,L,L,L,#,D,R,R,#,U,U,U,L,#", result);
    }

    [TestMethod]
    public void LowerCaseTest()
    {
        // Test the sample string, as lower case
        string result = KeyboardManager.Instance.GenerateScript("it crowd");
        Assert.AreEqual("D,R,R,#,D,D,L,#,S,U,U,U,R,#,D,D,R,R,R,#,L,L,L,#,D,R,R,#,U,U,U,L,#", result);
    }

    [TestMethod]
    public void NoMovementTest()
    {
        // Select characters at the default cursor position
        string result = KeyboardManager.Instance.GenerateScript("AAA");
        Assert.AreEqual("#,#,#", result);
    }

    [TestMethod]
    public void NumericTest()
    {
        // Test a string with numeric characters only
        string result = KeyboardManager.Instance.GenerateScript("15232");
        Assert.AreEqual("D,D,D,D,R,R,#,D,L,L,#,U,R,R,R,#,R,#,L,#", result);
    }

    [TestMethod]
    [ExpectedException(typeof(ArgumentException))]
    public void InvalidCharacterTest()
    {
        // Ensure that invalid characters throw an exception
        KeyboardManager.Instance.GenerateScript("@$%!");
    }
}