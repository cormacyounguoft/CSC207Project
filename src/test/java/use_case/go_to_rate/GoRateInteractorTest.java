package go_to_rate;


import org.junit.jupiter.api.Test;
import use_case.go_to_rate.*;

import static org.junit.jupiter.api.Assertions.*;

public class GoRateInteractorTest {

    @Test
    void successTest(){
        GoRateInputData inputData = new GoRateInputData("Paul", "Frozen");
        GoRateOutputBoundary presenter = new GoRateOutputBoundary() {
            @Override
            public void switchToRateView(GoRateOutputData rateOutputData) {
                assertEquals(rateOutputData.getTitle(), "Frozen");
                assertEquals(rateOutputData.getUsername(), "Paul");
                assertFalse(rateOutputData.isUseCaseFailed());
            }

        };
        GoRateInputBoundary interactor = new GoRateInteractor(presenter);
        interactor.switchToRateView(inputData);
    }
}
