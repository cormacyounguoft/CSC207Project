package use_case.home;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class HomeInteractorTest {

    @Test
    void toLoginViewTest(){
        HomeOutputBoundary presenter = new HomeOutputBoundary() {
            @Override
            public void switchToLoginView() {
                assertTrue(true);
            }

            @Override
            public void switchToSignupView() {
                fail("switchToSignupView use case is unexpected.");
            }

            @Override
            public void switchToSearchView() {
                fail("switchToSearchView use case is unexpected.");
            }
        };

        HomeInputBoundary interactor = new HomeInteractor(presenter);
        interactor.switchToLoginView();

    }

    @Test
    void toSignUpViewTest(){
        HomeOutputBoundary presenter = new HomeOutputBoundary() {
            @Override
            public void switchToLoginView() {
                fail("switchToLoginView use case is unexpected.");
            }

            @Override
            public void switchToSignupView() {
                assertTrue(true);
            }

            @Override
            public void switchToSearchView() {
                fail("switchToSearchView use case is unexpected.");
            }
        };

        HomeInputBoundary interactor = new HomeInteractor(presenter);
        interactor.switchToSignupView();
    }

    @Test
    void toSearchViewTest(){
        HomeOutputBoundary presenter = new HomeOutputBoundary() {
            @Override
            public void switchToLoginView() {
                fail("switchToLoginView use case is unexpected.");
            }

            @Override
            public void switchToSignupView() {
                fail("switchToSignupView use case is unexpected.");
            }

            @Override
            public void switchToSearchView() {
                assertTrue(true);
            }
        };

        HomeInputBoundary interactor = new HomeInteractor(presenter);
        interactor.switchToSearchView();
    }
}
