import QueryParseSvc from "./QueryParseService";
import SearchImportSvc from "./SearchImportService";


SearchImportSvc.getKeyboardQueries().then((queryList) => {
    queryList.forEach(search => {
        console.log(QueryParseSvc.getCursorPath(search))
    });
})

