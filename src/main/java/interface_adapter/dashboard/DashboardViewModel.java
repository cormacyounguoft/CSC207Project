package interface_adapter.dashboard;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Dashboard View.
 */
public class DashboardViewModel extends ViewModel<DashboardState> {

    public static final String TITLE = "Movie Dashboard";
    public static final String CANCEL = "Cancel";
    public static final String USERNAME = "Username: ";
    public static final String TOTAL_TIME_WATCHED = "Total Time Watched: ";
    public static final String FAVOURITE_MOVIE = "Favourite Movie: ";
    public static final String FAVOURITE_GENRE = "Favourite Genre: ";
    public static final String LONGEST_MOVIE_WATCHED = "Longest Movie Watched: ";
    public static final String AVERAGE_RATING = "Average Rating: ";

    public DashboardViewModel() {
        super("dashboard");
        setState(new DashboardState());
    }
}
