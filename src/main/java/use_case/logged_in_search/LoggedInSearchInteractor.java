package use_case.logged_in_search;

import entity.Movie;

import java.io.IOException;

/**
 * The Logged In Search Interactor.
 */
public class LoggedInSearchInteractor implements LoggedInSearchInputBoundary {
    private final LoggedInSearchOutputBoundary presenter;
    private final LoggedInSearchDataAccessInterface loggedInSearchDataAccessObject;

    public LoggedInSearchInteractor(LoggedInSearchOutputBoundary loggedInSearchOutputBoundary,
                                    LoggedInSearchDataAccessInterface loggedInSearchDataAccessObject) {
        this.presenter = loggedInSearchOutputBoundary;
        this.loggedInSearchDataAccessObject = loggedInSearchDataAccessObject;
    }

    @Override
    public void execute(LoggedInSearchInputData loggedInSearchInputData) {
        try {
            final Movie movie = loggedInSearchDataAccessObject.search(loggedInSearchInputData.getSearchQuery());
            final String username = loggedInSearchInputData.getUsername();
            final LoggedInSearchOutputData loggedInSearchOutputData = new LoggedInSearchOutputData(username, movie,
                    false);
            presenter.prepareSuccessView(loggedInSearchOutputData);
        }
        catch (IOException exception) {
            presenter.prepareFailView("Movie not found!");
        }
    }

    @Override
    public void switchToLoggedInView(LoggedInSearchInputData loggedInSearchInputData) {
        final LoggedInSearchOutputData loggedInSearchOutputData = new LoggedInSearchOutputData(
                loggedInSearchInputData.getUsername(),
                new Movie(), false);
        presenter.switchToLoggedInView(loggedInSearchOutputData);
    }
}
