export const TOTAL_ROWS = 6
export const TOTAL_COLUMNS = 6
export const KEYBOARD = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
export const IMPORT_FILE_PATH = './src/input.txt'
// solution assumes keyboard will remain in grid shape, 
// garbage chars could be added if irregular shape is used in future

export interface Coordiantes {
    row: number
    column: number
}