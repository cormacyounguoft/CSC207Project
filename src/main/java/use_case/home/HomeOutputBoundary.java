package use_case.home;

/**
 * The output boundary for the Home Use Case.
 */
public interface HomeOutputBoundary {

    /**
     * Switches to the Login View.
     */
    void switchToLoginView();

    /**
     * Switches to the Signup View.
     */
    void switchToSignupView();

    /**
     * Switches to the Search View.
     */
    void switchToSearchView();
}
