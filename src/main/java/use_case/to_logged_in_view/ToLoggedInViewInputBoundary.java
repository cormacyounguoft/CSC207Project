package use_case.to_logged_in_view;

import use_case.login.LoginInputData;

public interface ToLoggedInViewInputBoundary {

    /**
     * Executes the login use case.
     * @param toLoggedInViewInputData the input data
     */
    void toLoggedInView(ToLoggedInViewInputData toLoggedInViewInputData);
}
