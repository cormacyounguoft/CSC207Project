package use_case.to_logged_in_view;

/**
 * Input boundary for to logged in view.
 */
public interface ToLoggedInViewInputBoundary {

    /**
     * Executes the login use case.
     * @param toLoggedInViewInputData the input data
     */
    void toLoggedInView(ToLoggedInViewInputData toLoggedInViewInputData);
}
