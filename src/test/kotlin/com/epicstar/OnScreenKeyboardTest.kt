package com.epicstar

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class OnScreenKeyboardTest {
    private val keyboard = OnScreenKeyboard(listOf(
        listOf('A', 'B', 'C', 'D', 'E', 'F'),
        listOf('G', 'H', 'I', 'J', 'K', 'L'),
        listOf('M', 'N', 'O', 'P', 'Q', 'R'),
        listOf('S', 'T', 'U', 'V', 'W', 'X'),
        listOf('Y', 'Z', '1', '2', '3', '4'),
        listOf('5', '6', '7', '8', '9', '0')
    ))

    private val asymmetricalKeyboard = OnScreenKeyboard(listOf(
        listOf('A', 'B', 'C'),
        listOf('G', 'H'),
    ))

    private val symmetricalKeyboardWithBlankKeys = OnScreenKeyboard(listOf(
        listOf('A', 'B', 'C', 'D'),
        listOf('G', Char.MIN_VALUE, 'H', Char.MIN_VALUE),
    ))

    private val blankKeyboard = OnScreenKeyboard(emptyList())

    @Test
    fun `verify A has no movement, only selection`() = validCreateInputPath(
        "A",
        listOf('#')
    )

    @Test
    fun `verify move right`() = validCreateInputPath(
        "AC",
        listOf('#', 'R', 'R', '#')
    )

    @Test
    fun `verify move left`() = validCreateInputPath(
        "ACB",
        listOf('#', 'R', 'R', '#', 'L', '#')
    )

    @Test
    fun `verify move down`() = validCreateInputPath(
        " M",
        listOf('S', 'D', 'D', '#')
    )

    @Test
    fun `verify move up`() = validCreateInputPath(
        " MG",
        listOf('S', 'D', 'D', '#', 'U', '#')
    )

    @Test
    fun `verify 'IT Crowd' gets the correct path`() = validCreateInputPath(
        "IT CROWD",
        listOf(
            'D', 'R', 'R', '#', 'D', 'D', 'L', '#', 'S', 'U', 'U', 'U', 'R', '#', 'D', 'D', 'R',
            'R', 'R', '#', 'L', 'L', 'L', '#', 'D', 'R', 'R', '#', 'U', 'U', 'U', 'L', '#'
        )
    )

    @Test
    fun `verify 'A1B 3' can get to numbers on the keyboard`() = validCreateInputPath(
        "A1B 3",
        listOf(
            '#',
            'D', 'D', 'D', 'D', 'R', 'R', '#',
            'U', 'U', 'U', 'U', 'L', '#',
            'S',
            'D', 'D', 'D', 'D', 'R', 'R', 'R', '#'
        )
    )



    @Test
    fun `verify empty string returns an empty path`() = validCreateInputPath(
        "",
        emptyList()
    )

    @Test
    fun `verify asymmetrical down`() = validCreateInputPath(
        "CH",
        listOf('R', 'R', '#', 'D', '#'),
        asymmetricalKeyboard
    )


    @Test
    fun `verify asymmetrical keyboard up`() = validCreateInputPath(
        "CH A",
        listOf(
            'R', 'R', '#',
            'D', '#', 'S',
            'U', 'L', '#'
        ),
        asymmetricalKeyboard
    )

    @Test
    fun `verify correct movement around blank characters`() = validCreateInputPath(
        "H",
        listOf('D', 'R', 'R', '#'),
        symmetricalKeyboardWithBlankKeys
    )

    @Test
    fun `verify no moves on blank keyboard with blank input`() = validCreateInputPath(
        "",
        emptyList(),
        blankKeyboard
    )

    // region invalid cases
    @Test
    fun `verify that we cannot currently handle letter cases`() = invalidCreateInputPath(
        "A 1b C",
        'b'
    )

    @Test
    fun `verify that we can never handle missing characters`() = invalidCreateInputPath(
        "MISSING_CHARACTER",
        '_'
    )

    @Test
    fun `verify that an exception is thrown despite no keys`() = invalidCreateInputPath(
        "HI",
        'H',
        blankKeyboard
    )
    // endregion invalid cases

    private fun validCreateInputPath(input: String, expected: List<Char>, k: OnScreenKeyboard = keyboard) {
        assertEquals(expected, k.createInputPath(input))
    }

    private fun invalidCreateInputPath(invalidInput: String, expectedMissingCharacter: Char, k: OnScreenKeyboard = keyboard) {
        val exception = assertFailsWith<IllegalArgumentException> {
            k.createInputPath(invalidInput)
        }

        assertEquals("'$expectedMissingCharacter' not in \"$invalidInput\"", exception.message)
    }
}