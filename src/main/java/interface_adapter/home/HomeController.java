package interface_adapter.home;

import use_case.home.HomeInputBoundary;

/**
 * Controller for the Home Use Case.
 */
public class HomeController {
    private final HomeInputBoundary homeUseCaseInteractor;

    public HomeController(HomeInputBoundary homeUseCaseInteractor) {
        this.homeUseCaseInteractor = homeUseCaseInteractor;
    }

    public void switchToLoginView() {
        homeUseCaseInteractor.switchToLoginView();
    }

    public void switchToSignupView() {
        homeUseCaseInteractor.switchToSignupView();
    }

    public void switchToSearchView() {
        homeUseCaseInteractor.switchToSearchView();
    }
}
