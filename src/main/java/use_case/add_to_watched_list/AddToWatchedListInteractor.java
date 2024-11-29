package use_case.add_to_watched_list;

import java.io.IOException;

import entity.Movie;
import use_case.SearchDataAccessInterface;

/**
 * The add to watchlist Interactor.
 */
public class AddToWatchedListInteractor implements AddToWatchedListInputBoundary {
    private final AddToWatchedListDataAccessInterface userDataAccessObject;
    private final SearchDataAccessInterface searchDataAccessObject;
    private final AddToWatchedListOutputBoundary presenter;

    public AddToWatchedListInteractor(AddToWatchedListDataAccessInterface userDataAccessObject,
                                      SearchDataAccessInterface searchDataAccessObject,
                                      AddToWatchedListOutputBoundary presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.searchDataAccessObject = searchDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(AddToWatchedListInputData inputData) {
        try {
            final Movie movie = searchDataAccessObject.search(inputData.getMovie());
            userDataAccessObject.saveToWatchedList(inputData.getUsername(), movie);
            final AddToWatchedListOutputData outputData = new AddToWatchedListOutputData(inputData.getUsername(),
                    false);
            presenter.prepareSuccessView(outputData);
        }
        catch (IOException exception) {
            presenter.prepareFailView("Error");
        }
    }
}
