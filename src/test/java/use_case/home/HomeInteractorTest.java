package use_case.home;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeInteractorTest {

    @Test
    void successTest(){
        HomeOutputBoundary presenter = new HomeOutputBoundary() {
            @Override
            public void switchToLoginView() {
                assertTrue(true);
            }

            @Override
            public void switchToSignupView() {
                assertTrue(true);
            }

            @Override
            public void switchToSearchView() {
                assertTrue(true);
            }
        };

        HomeInputBoundary interactor = new HomeInteractor(presenter);
        interactor.switchToLoginView();
        interactor.switchToSearchView();
        interactor.switchToSignupView();
    }
}
