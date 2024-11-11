package interface_adapter.get_watchlist;

import use_case.get_watchlist.GetWatchlistInputBoundary;
import use_case.get_watchlist.GetWatchlistInputData;

public class GetWatchlistController {
    GetWatchlistInputBoundary interactor;

    public GetWatchlistController(GetWatchlistInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String username) {
        final GetWatchlistInputData inputData = new GetWatchlistInputData(username);
        interactor.execute(inputData);
    }
}
