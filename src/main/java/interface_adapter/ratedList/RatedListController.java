package interface_adapter.ratedList;

import use_case.rated_list.RatedListInputBoundary;
import use_case.rated_list.RatedListInputData;

/**
 * The controller for the rated list use case.
 */
public class RatedListController {

    private final RatedListInputBoundary interactor;

    public RatedListController(RatedListInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Execute the rated list controller.
     * @param username The username of the user.
     * @param title The title of the movie.
     */
    public void execute(String username, String title) {
        final RatedListInputData inputData = new RatedListInputData(username, title);
        interactor.execute(inputData);
    }
}
