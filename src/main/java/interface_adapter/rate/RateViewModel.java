package interface_adapter.rate;

import interface_adapter.ViewModel;

/**
 * The view model for the Rate Use Case.
 */
public class RateViewModel extends ViewModel<RateState> {

    public static final String TITLE = "Rate View";
    public static final String RATE = "Movie rating out of 5:";
    public static final String RATE_BUTTON = "Save rating";
    public static final String CANCEL_BUTTON = "Cancel";

    public RateViewModel() {
        super("rate");
        setState(new RateState());
    }
}
