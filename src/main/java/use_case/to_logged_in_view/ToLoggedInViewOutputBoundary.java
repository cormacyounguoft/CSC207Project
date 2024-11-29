package use_case.to_logged_in_view;

/**
 * Output boundary for to logged in view.
 */
public interface ToLoggedInViewOutputBoundary {

    /**
     * Prepares the success view for the Login Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ToLoggedInViewOutputData outputData);
}
