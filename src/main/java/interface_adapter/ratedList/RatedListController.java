package interface_adapter.ratedList;

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

    public void execute(String username, String title) {
        final RatedListInputData inputData = new RatedListInputData(username, title);
        interactor.execute(inputData);
    }

}
