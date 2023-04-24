import { readFile } from "fs/promises"
import { IMPORT_FILE_PATH } from "./KeyboardSettings"

class SearchImportService {
    getKeyboardQueries = async (): Promise<string[]> => {
        const rawQueries = await readFile(IMPORT_FILE_PATH)
        return rawQueries.toString().split('\n')
    }
}

const SearchImportSvc = new SearchImportService()
export default SearchImportSvc