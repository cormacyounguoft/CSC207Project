package use_case.home;

/**
 * Input Boundary for actions which are related to the home view.
 */
public interface HomeInputBoundary {
    /**
     * Executes the switch to login view use case.
     */
    void switchToLoginView();

    /**
     * Executes the switch to signup view use case.
     */
    void switchToSignupView();

    /**
     * Executes the switch to search view use case.
     */
    void switchToSearchView();
}
