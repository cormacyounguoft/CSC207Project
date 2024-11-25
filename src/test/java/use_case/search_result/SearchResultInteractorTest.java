package use_case.search_result;

import org.junit.jupiter.api.Test;
import use_case.logged_in_search_result.LoggedInSearchResultInputBoundary;
import use_case.logged_in_search_result.LoggedInSearchResultInputData;
import use_case.logged_in_search_result.LoggedInSearchResultInteractor;
import use_case.logged_in_search_result.LoggedInSearchResultOutputBoundary;
import use_case.logged_in_search_result.LoggedInSearchResultOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class SearchResultInteractorTest {
    @Test
    void successTest() {
        SearchResultOutputBoundary presenter = new SearchResultOutputBoundary() {

            @Override
            public void prepareSuccessView(SearchResultOutputData outputData) {
                assertFalse(outputData.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        SearchResultInputBoundary interactor = new SearchResultInteractor(presenter);
        interactor.execute();
    }
}
