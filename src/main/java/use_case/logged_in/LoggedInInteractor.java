package use_case.logged_in;

/**
 * The Logged In Interactor.
 */
public class LoggedInInteractor implements LoggedInInputBoundary {
    private final LoggedInOutputBoundary presenter;

    public LoggedInInteractor(LoggedInOutputBoundary loggedInOutputBoundary) {
        this.presenter = loggedInOutputBoundary;
    }

    @Override
    public void switchToLoggedInSearchView(LoggedInInputData loggedInInputData) {
        final LoggedInOutputData loggedInOutputData = new LoggedInOutputData(loggedInInputData.getUsername(),
                false);
        presenter.switchToLoggedInSearchView(loggedInOutputData);
    }

    @Override
    public void switchToChangePasswordView(LoggedInInputData loggedInInputData) {
        final LoggedInOutputData loggedInOutputData = new LoggedInOutputData(loggedInInputData.getUsername(),
                false);
        presenter.switchToChangePasswordView(loggedInOutputData);
    }
}
