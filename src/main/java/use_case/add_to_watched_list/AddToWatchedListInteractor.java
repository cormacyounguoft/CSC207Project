package use_case.add_to_watched_list;

import entity.Movie;
import use_case.SearchDataAccessInterface;

import java.io.IOException;

/**
 * The add to watchlist Interactor.
 */
public class AddToWatchedListInteractor implements AddToWatchedListInputBoundary {
    private AddToWatchedListDataAccessInterface userDataAccessObject;
    private SearchDataAccessInterface searchDataAccessObject;
    private AddToWatchedListOutputBoundary presenter;

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
            Movie movie = searchDataAccessObject.search(inputData.getMovie());
            userDataAccessObject.saveToWatchedList(inputData.getUsername(), movie);
            final AddToWatchedListOutputData outputData = new AddToWatchedListOutputData(inputData.getUsername(), false);
            presenter.prepareSuccessView(outputData);
        }
        catch (IOException exception) {
            presenter.prepareFailView("Error");
        }
    }
}
