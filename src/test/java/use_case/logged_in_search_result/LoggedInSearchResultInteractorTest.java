package use_case.logged_in_search_result;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class LoggedInSearchResultInteractorTest {
    @Test
    void successTest() {
        LoggedInSearchResultInputData inputData = new LoggedInSearchResultInputData("Username", "Movie");
        LoggedInSearchResultOutputBoundary presenter = new LoggedInSearchResultOutputBoundary() {

            @Override
            public void prepareSuccessView(LoggedInSearchResultOutputData outputData) {
                assertEquals("Movie", outputData.getMovie());
                assertEquals("Username", outputData.getUsername());
                assertFalse(outputData.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        LoggedInSearchResultInputBoundary interactor = new LoggedInSearchResultInteractor(presenter);
        interactor.execute(inputData);
    }
}

