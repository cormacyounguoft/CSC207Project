package interface_adapter.rate;

import interface_adapter.ViewModel;

/**
 * The view model for the Rate Use Case.
 */
public class RateViewModel extends ViewModel<RateState> {
    public static final String TITLE_LABEL = "Rate View";
    public static final String RATE_LABEL = "Movie rating out of 5:";
    public static final String RATE_BUTTON_LABEL = "Save rating";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public RateViewModel() {
        super("rate");
        setState(new RateState());
    }
}
