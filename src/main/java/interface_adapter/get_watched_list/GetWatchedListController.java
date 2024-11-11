package interface_adapter.get_watched_list;

import use_case.get_watched_list.GetWatchedListInputBoundary;
import use_case.get_watched_list.GetWatchedListInputData;

public class GetWatchedListController {
    GetWatchedListInputBoundary interactor;

    public GetWatchedListController(GetWatchedListInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String username) {
        final GetWatchedListInputData inputData = new GetWatchedListInputData(username);
        interactor.execute(inputData);
    }
}
