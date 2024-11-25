package use_case.logged_in_search_result;

import interface_adapter.logged_in_search_result.LoggedInSearchResultPresenter;
import org.junit.jupiter.api.Test;
import use_case.logged_in_search.LoggedInSearchInputBoundary;
import use_case.logged_in_search.LoggedInSearchInputData;
import use_case.logged_in_search.LoggedInSearchInteractor;
import use_case.logged_in_search.LoggedInSearchOutputBoundary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class LoggedInSearchResultInteractorTest {
    @Test
    void successTest() {
        LoggedInSearchResultInputData inputData = new LoggedInSearchResultInputData("username", "frozen");
        LoggedInSearchResultOutputBoundary presenter = new LoggedInSearchResultOutputBoundary() {

            @Override
            public void prepareSuccessView(LoggedInSearchResultOutputData outputData) {
                assertEquals("frozen", outputData.getMovie());
                assertEquals("username", outputData.getUsername());
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

