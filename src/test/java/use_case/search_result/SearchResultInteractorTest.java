package use_case.search_result;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class SearchResultInteractorTest {
    @Test
    void successTest() {
        SearchResultOutputBoundary presenter = new SearchResultOutputBoundary() {

            @Override
            public void prepareSuccessView(SearchResultOutputData outputData) {
                assertFalse(outputData.isUseCaseFailed());
            }
        };

        SearchResultInputBoundary interactor = new SearchResultInteractor(presenter);
        interactor.execute();
    }
}
