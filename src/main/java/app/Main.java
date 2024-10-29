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
        // TODO: add the Logout Use Case to the app using the appBuilder
        final JFrame application = appBuilder
                                            .addHomeView()
                                            .addSearchView()
                                            .addLoginView()
                                            .addSignupView()
                                            .addLoggedInView()
                                            .addSearchResultView()
                                            .addHomeUseCase()
                                            .addSearchUseCase()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addChangePasswordUseCase()
                                            .addLogoutUseCase()
                                            .addSearchResultUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
