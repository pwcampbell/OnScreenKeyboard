package com.epicstar

/**
 * This is an on-screen keyboard implementation.
 *
 * Basically, this is a single level keyboard where rows and columns can be asymmetrical.
 * Can have blank columns/rows. Use [Char.MIN_VALUE]!
 *
 * Space complexity: O(N) where N is the number of characters in the layout.
 * Time complexity of [createInputPath]: O(N), where N is the number of possible moves, at least as much as the number of characters
 *
 * @author Jeremy Jao
 * @since 1.0
 */
class OnScreenKeyboard(val layout: List<List<Char>>) {

    /**
     * Internally, we do a reverse lookup from the [layout] by character, row, and column location.
     * Not all rows will be created equally, so we have to account for that.
     *
     * In the future, consider adding levels.
     */
    private data class Key(
        /**
         * The character of the key in the [layout]
         */
        val character: Char,
        /**
         * The row of the key in the [layout]
         */
        val row: Int,
        /**
         * the column of the key in the [layout]
         */
        val column: Int
    )

    /**
     * The keyboard layout in reverse-lookup fashion.
     * On initialization, this will be made.
     */
    private val keys: Map<Char, Key> = layout.flatMapIndexed { rowIndex: Int, chars: List<Char> ->
        chars.mapIndexed { columnIndex, c ->
            c to Key(c, rowIndex, columnIndex)
        }.filter { it.first != Char.MIN_VALUE } // remove blank keys in reverse lookup
    }.toMap()

    /**
     * Create an input path as if we have an onscreen keyboard where the cursor starts at the top-most
     * left hand corner of the keyboard.
     *
     * O(N) Time complexity -> Where N is the number of total moves in the keyboard.
     *
     * Possible actions:
     * - `U`: cursor moves up
     * - `D` cursor moves down
     * - `L`: cursor moves left
     * - `R`: cursor moves right
     * - `S`: space
     * - `#`: keyboard key where the cursor exists is selected
     *
     * @param search the search string that was inputted
     *
     * @since 1.0
     * @throws IllegalArgumentException if the
     * @return The list of moves that the cursor makes
     */
    fun createInputPath(search: String): List<Char> {
        val path = mutableListOf<Char>()

        // the prompt says start at A (0, 0) every time
        var rowPosition = 0
        var columnPosition = 0
        for (c in search) {
            if (c == ' ') {
                path.add(SPACE)
                continue
            }
            val key = keys[c] ?: throw IllegalArgumentException("'$c' not in \"$search\"")
            /*
            This logic currently considers:
            1. columns may not symmetrical. See minOf on the column position, move down/up to the same column index;
               if at the border, move to the smallest of the row last index or column position
            2. different

            currently does not consider:
            1. different character cases: keyboards have Shift cases, and different levels of layouts. Not handling that
               for now!

            Consider multi-level keyboards (different cases, etc.)
             */
            createInputPath(path, rowPosition, key.row, VERTICAL_MOVES)
            createInputPath(path, minOf(columnPosition, layout[key.row].lastIndex), key.column, HORIZONTAL_MOVES)
            path.add(SELECT)
            rowPosition = key.row
            columnPosition = key.column
        }
        return path
    }

    /**
     * Helper function (and overload) that takes the path, and adds how to move the cursor to
     * the key's position.
     *
     * @param path list of path actions to the key; this is added to
     * @param cursorPosition where the cursor position is
     * @param keyPosition where the position of the key is
     * @param options possible directions to take
     */
    private fun createInputPath(path: MutableList<Char>, cursorPosition: Int, keyPosition: Int, options: Pair<Char, Char>) {
        var diff = cursorPosition - keyPosition
        while (diff > 0) {
            diff -= 1
            path.add(options.first)
        }

        while (diff < 0) {
            diff += 1
            path.add(options.second)
        }

    }

    companion object {
        const val SELECT = '#'
        const val SPACE = 'S'
        // lower -> higher
        val HORIZONTAL_MOVES = 'L' to 'R'
        val VERTICAL_MOVES = 'U' to 'D'
    }
}