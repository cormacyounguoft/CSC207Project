package use_case.to_logged_in_view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToLoggedInViewInteractorTest {

    @Test
    void successTest() {
        ToLoggedInViewInputData inputData = new ToLoggedInViewInputData("Username");
        ToLoggedInViewOutputBoundary presenter = new ToLoggedInViewOutputBoundary() {
            @Override
            public void prepareSuccessView(ToLoggedInViewOutputData outputData) {
                assertEquals("Username", outputData.getUsername());
            }
        };
        ToLoggedInViewInputBoundary interactor = new ToLoggedInViewInteractor(presenter);
        interactor.toLoggedInView(inputData);
    }
}
