package use_case.to_logged_in_view;

import use_case.login.LoginOutputData;

public interface ToLoggedInViewOutputBoundary {

    /**
     * Prepares the success view for the Login Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ToLoggedInViewOutputData outputData);
}
