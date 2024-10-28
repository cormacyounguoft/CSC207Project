package use_case.search_result;

/**
 * The Search Result Interactor.
 */
public class SearchResultInteractor implements SearchResultInputBoundary {

    @Override
    public void execute(SearchResultInputData searchResultInputData) {
        System.out.println(searchResultInputData.getMovie());
    }
}
