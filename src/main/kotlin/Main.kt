import com.epicstar.OnScreenKeyboard
import java.io.File
import java.lang.Exception

fun main(args: Array<String>) {
    try {
        val keyboard = OnScreenKeyboard(listOf(
            listOf('A', 'B', 'C', 'D', 'E', 'F'),
            listOf('G', 'H', 'I', 'J', 'K', 'L'),
            listOf('M', 'N', 'O', 'P', 'Q', 'R'),
            listOf('S', 'T', 'U', 'V', 'W', 'X'),
            listOf('Y', 'Z', '1', '2', '3', '4'),
            listOf('5', '6', '7', '8', '9', '0'))
        )
        val file = File(args[0])

        file.forEachLine { searchString ->
            println(keyboard.createInputPath(searchString).joinToString(","))
        }
    } catch (ex: Exception) {
        println("""
            Usage: java -jar OnScreenKeyboard-<version>.jar <filename>
        """.trimIndent())
        throw ex
    }
}