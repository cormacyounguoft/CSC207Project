package use_case.go_to_rate;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GoRateInteractorTest {
    @Test
    void successTest(){
        GoRateInputData inputData = new GoRateInputData("Username", "Movie");

        GoRateOutputBoundary presenter = new GoRateOutputBoundary() {
            @Override
            public void switchToRateView(GoRateOutputData rateOutputData) {
                assertEquals("Movie", rateOutputData.getTitle());
                assertEquals("Username", rateOutputData.getUsername());
                assertFalse(rateOutputData.isUseCaseFailed());
            }
        };

        GoRateInputBoundary interactor = new GoRateInteractor(presenter);
        interactor.switchToRateView(inputData);
    }
}
