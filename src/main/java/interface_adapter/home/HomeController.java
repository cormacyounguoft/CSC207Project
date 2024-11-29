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

    /**
     * Switch to the login view.
     */
    public void switchToLoginView() {
        homeUseCaseInteractor.switchToLoginView();
    }

    /**
     * Switch to the signup view.
     */
    public void switchToSignupView() {
        homeUseCaseInteractor.switchToSignupView();
    }

    /**
     * Switch to the search view.
     */
    public void switchToSearchView() {
        homeUseCaseInteractor.switchToSearchView();
    }
}
