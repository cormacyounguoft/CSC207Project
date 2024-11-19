package app;

import javax.swing.*;

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
        // TODO: add the Logout Use Case to the app using the appBuilder
        final JFrame application = appBuilder
                .addHomeView()
                .addSearchView()
                .addLoginView()
                .addSignupView()
                .addChangePasswordView()
                .addSearchResultView()
                .addLoggedInView()
                .addLoggedInSearchView()
                .addLoggedInSearchResultView()
                .addRateView()
                .addWatchedListView()
                .addWatchlistView()
                .addRatedListView()
                .addHomeUseCase()
                .addSearchUseCase()
                .addSignupUseCase()
                .addLoginUseCase()
                .addChangePasswordUseCase()
                .addLogoutUseCase()
                .addSearchResultUseCase()
                .addLoggedInUseCase()
                .addLoggedInSearchUseCase()
                .addLoggedInSearchResultUseCase()
                .addRateUseCase()
                .addAddToWatchedListUseCase()
                .addAddToWatchlistUseCase()
                .addWatchedListUseCase()
                .addWatchlistUseCase()
                .addGetWatchedListUseCase()
                .addGetWatchlistUseCase()
                .addRate()
                .addGetRatedListUseCase()
                .addRatedListUseCase()
                .addToLoggedInViewUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
