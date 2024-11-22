package use_case.add_to_watchlist;

import entity.Movie;
import use_case.SearchDataAccessInterface;

import java.io.IOException;

/**
 * The add to watchlist Interactor.
 */
public class AddToWatchlistInteractor implements AddToWatchlistInputBoundary {
    private AddToWatchlistDataAccessInterface userDataAccessObject;
    private SearchDataAccessInterface searchDataAccessObject;
    private AddToWatchlistOutputBoundary presenter;

    public AddToWatchlistInteractor(AddToWatchlistDataAccessInterface userDataAccessObject,
                                    SearchDataAccessInterface searchDataAccessObject,
                                    AddToWatchlistOutputBoundary presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.searchDataAccessObject = searchDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(AddToWatchlistInputData inputData) {
        try {
            Movie movie = searchDataAccessObject.search(inputData.getMovie());
            userDataAccessObject.saveToWatchlist(inputData.getUsername(), movie);
            final AddToWatchlistOutputData outputData = new AddToWatchlistOutputData(inputData.getUsername(), false);
            presenter.prepareSuccessView(outputData);
        }
        catch (IOException exception) {
            presenter.prepareFailView("Error");
        }
    }
}
