package app;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {

    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addHomeView()
                .addSearchView()
                .addLoginView()
                .addSignupView()
                .addChangePasswordView()
                .addDashboardView()
                .addSearchResultView()
                .addLoggedInView()
                .addLoggedInSearchView()
                .addLoggedInSearchResultView()
                .addRateView()
                .addWatchedListView()
                .addWatchlistView()
                .addRatedListView()
                .addDashboardView()
                .addHomeUseCase()
                .addSearchUseCase()
                .addSignupUseCase()
                .addLoginUseCase()
                .addChangePasswordUseCase()
                .addDashboardUseCase()
                .addLogoutUseCase()
                .addSearchResultUseCase()
                .addLoggedInUseCase()
                .addLoggedInSearchUseCase()
                .addLoggedInSearchResultUseCase()
                .addRateUseCase()
                .addAddToWatchedListUseCase()
                .addAddToWatchlistUseCase()
                .addWatchedListRemoveUseCase()
                .addWatchListRemoveUseCase()
                .addGetWatchedListUseCase()
                .addGetWatchlistUseCase()
                .addGoToRateUseCase()
                .addGetRatedListUseCase()
                .addRatedListUseCase()
                .addToHomeViewUseCase()
                .addToLoggedInViewUseCase()
                .addDashboardUseCase()
                .addExportWatchlistUseCase()
                .addExportWatchedListUseCase()
                .build();

        // Commented out to keep consistent size
        // application.pack();
        application.setVisible(true);
    }
}
