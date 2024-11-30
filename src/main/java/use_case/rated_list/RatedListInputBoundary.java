package use_case.rated_list;

/**
 * The input boundary for the rated list use case.
 */
public interface RatedListInputBoundary {

    /**
     * Executes the rated list input data.
     * @param ratedListInputData the input data for the rated list.
     */
    void execute(RatedListInputData ratedListInputData);
}
