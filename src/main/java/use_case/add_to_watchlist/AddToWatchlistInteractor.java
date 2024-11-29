package use_case.add_to_watchlist;

import java.io.IOException;

import entity.Movie;
import use_case.SearchDataAccessInterface;

/**
 * The add to watchlist Interactor.
 */
public class AddToWatchlistInteractor implements AddToWatchlistInputBoundary {

    private final AddToWatchlistDataAccessInterface userDataAccessObject;
    private final SearchDataAccessInterface searchDataAccessObject;
    private final AddToWatchlistOutputBoundary presenter;

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
            final Movie movie = searchDataAccessObject.search(inputData.getMovie());
            userDataAccessObject.saveToWatchlist(inputData.getUsername(), movie);
            final AddToWatchlistOutputData outputData =
                    new AddToWatchlistOutputData(inputData.getUsername(), false);
            presenter.prepareSuccessView(outputData);
        }
        catch (IOException exception) {
            presenter.prepareFailView("Error");
        }
    }
}
