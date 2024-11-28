package use_case.to_home_view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ToHomeViewInteractorTest {

    @Test
    void successTest() {
        ToHomeViewOutputBoundary presenter = new ToHomeViewOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                assertTrue(true);
            }
        };
        ToHomeViewInputBoundary interactor = new ToHomeInteractor(presenter);
        interactor.toHomeView();
    }
}