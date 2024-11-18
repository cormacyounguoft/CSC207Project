package interface_adapter.RatedList;

import entity.MovieList;
import use_case.rated_list.RatedListInputBoundary;
import use_case.rated_list.RatedListInputData;


import java.util.HashMap;
import java.util.Map;

public class RatedListController {
    private final RatedListInputBoundary interactor;

    public RatedListController(RatedListInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String username, Map<String, Integer> rating) {
        final RatedListInputData inputData = new RatedListInputData(username, rating);
        interactor.execute(inputData);
    }

    public void switchToLoggedInView(String username) {
        Map<String, Integer> c = new HashMap<String, Integer>();
        final RatedListInputData inputData = new RatedListInputData(username, c);
        interactor.switchToLoggedInView(inputData);
    }
}
