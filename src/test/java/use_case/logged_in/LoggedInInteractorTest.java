package use_case.logged_in;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoggedInInteractorTest {

    @Test
    public void toChangePasswordViewTest() {
        LoggedInInputData inputData = new LoggedInInputData("Username");

        LoggedInOutputBoundary presenter = new LoggedInOutputBoundary() {
            @Override
            public void switchToChangePasswordView(LoggedInOutputData loggedInOutputData) {
                assertEquals("Username", loggedInOutputData.getUsername());
                assertFalse(loggedInOutputData.isUseCaseFailed());
            }

            @Override
            public void switchToLoggedInSearchView(LoggedInOutputData loggedInOutputData) {
                fail("switchToLoggedInSearchView use case is unexpected.");
            }
        };

        LoggedInInputBoundary interactor = new LoggedInInteractor(presenter);
        interactor.switchToChangePasswordView(inputData);

    }

    @Test
    public void toLoggedInSearchViewTest() {
        LoggedInInputData inputData = new LoggedInInputData("Username");

        LoggedInOutputBoundary presenter = new LoggedInOutputBoundary() {
            @Override
            public void switchToChangePasswordView(LoggedInOutputData loggedInOutputData) {
                fail("switchToChangePasswordView use case is unexpected.");
            }

            @Override
            public void switchToLoggedInSearchView(LoggedInOutputData loggedInOutputData) {
                assertEquals("Username", loggedInOutputData.getUsername());
                assertFalse(loggedInOutputData.isUseCaseFailed());
            }
        };

        LoggedInInputBoundary interactor = new LoggedInInteractor(presenter);
        interactor.switchToLoggedInSearchView(inputData);

    }
}
