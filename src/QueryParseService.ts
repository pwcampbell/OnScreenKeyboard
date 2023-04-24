import { Coordiantes, KEYBOARD, TOTAL_COLUMNS, TOTAL_ROWS } from "./KeyboardSettings";

class QueryParseService {

    getTargetCoordinates = (char: string): Coordiantes => {
        let targetRow = 0;
        let targetCol = 0;
        for (var row = 0; row < TOTAL_ROWS; row++) {
            let stringRow = KEYBOARD.substring(row * TOTAL_COLUMNS, (row * TOTAL_COLUMNS) + TOTAL_COLUMNS + 1)
            let col = stringRow.indexOf(char);
            if (col != -1) {
                targetRow = row;
                targetCol = col % TOTAL_COLUMNS;
                break;
            }
        }
        return { row: targetRow, column: targetCol } as Coordiantes
    }

    getCursorPath = (search: string): string => {
        let cursorPath = "";
        let curRow = 0;
        let curCol = 0;
        for (var c = 0; c < search.length; c++) {
            const curChar = search.toUpperCase().charAt(c)

            if (curChar == ' ') {
                cursorPath += "S,";
                continue;
            }

            const target = this.getTargetCoordinates(curChar)

            while (curRow > target.row) {
                cursorPath += "U,";
                curRow--;
            }

            while (curRow < target.row) {
                cursorPath += "D,";
                curRow++;
            }

            while (curCol > target.column) {
                cursorPath += "L,";
                curCol--;
            }

            while (curCol < target.column) {
                cursorPath += "R,";
                curCol++;
            }

            cursorPath += "#,";
        }

        return cursorPath.substring(0, cursorPath.length - 1)
    }
}

const QueryParseSvc = new QueryParseService()
export default QueryParseSvc