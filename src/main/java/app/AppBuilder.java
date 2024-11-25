package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.InMemoryUserDataAccessObject;
import data_access.MovieAccessObject;
import entity.CommonUserFactory;
import entity.MovieFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_watched_list.AddToWatchedListController;
import interface_adapter.add_to_watched_list.AddToWatchedListPresenter;
import interface_adapter.add_to_watchlist.AddToWatchlistController;
import interface_adapter.add_to_watchlist.AddToWatchlistPresenter;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.get_rated_list.GetRatedListController;
import interface_adapter.get_rated_list.GetRatedListPresenter;
import interface_adapter.get_watched_list.GetWatchedListController;
import interface_adapter.get_watched_list.GetWatchedListPresenter;
import interface_adapter.get_watchlist.GetWatchlistController;
import interface_adapter.get_watchlist.GetWatchlistPresenter;
import interface_adapter.go_to_rate.GoRateController;
import interface_adapter.go_to_rate.GoRatePresenter;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomePresenter;
import interface_adapter.home.HomeViewModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.logged_in_search.LoggedInSearchController;
import interface_adapter.logged_in_search.LoggedInSearchPresenter;
import interface_adapter.logged_in_search.LoggedInSearchViewModel;
import interface_adapter.logged_in_search_result.LoggedInSearchResultController;
import interface_adapter.logged_in_search_result.LoggedInSearchResultPresenter;
import interface_adapter.logged_in_search_result.LoggedInSearchResultViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.rate.RateController;
import interface_adapter.rate.RatePresenter;
import interface_adapter.rate.RateViewModel;
import interface_adapter.ratedList.RatedListController;
import interface_adapter.ratedList.RatedListPresenter;
import interface_adapter.ratedList.RatedListViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search_result.SearchResultController;
import interface_adapter.search_result.SearchResultPresenter;
import interface_adapter.search_result.SearchResultViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;
import interface_adapter.to_logged_in_view.ToLoggedInViewPresenter;

import interface_adapter.to_home_view.ToHomeViewController;
import interface_adapter.to_home_view.ToHomeViewPresenter;

import interface_adapter.watched_list.WatchedListController;
import interface_adapter.watched_list.WatchedListPresenter;
import interface_adapter.watched_list.WatchedListViewModel;
import interface_adapter.watchlist.WatchlistController;
import interface_adapter.watchlist.WatchlistPresenter;
import interface_adapter.watchlist.WatchlistViewModel;
import use_case.add_to_watched_list.AddToWatchedListInputBoundary;
import use_case.add_to_watched_list.AddToWatchedListInteractor;
import use_case.add_to_watched_list.AddToWatchedListOutputBoundary;
import use_case.add_to_watchlist.AddToWatchlistInputBoundary;
import use_case.add_to_watchlist.AddToWatchlistInteractor;
import use_case.add_to_watchlist.AddToWatchlistOutputBoundary;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.get_rated_list.GetRateListInputBoundary;
import use_case.get_rated_list.GetRateListInteractor;
import use_case.get_rated_list.GetRateListOutputBoundary;
import use_case.get_watched_list.GetWatchedListInputBoundary;
import use_case.get_watched_list.GetWatchedListInteractor;
import use_case.get_watched_list.GetWatchedListOutputBoundary;
import use_case.get_watchlist.GetWatchlistInputBoundary;
import use_case.get_watchlist.GetWatchlistInteractor;
import use_case.get_watchlist.GetWatchlistOutputBoundary;
import use_case.go_to_rate.GoRateInputBoundary;
import use_case.go_to_rate.GoRateInteractor;
import use_case.go_to_rate.GoRateOutputBoundary;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInteractor;
import use_case.home.HomeOutputBoundary;
import use_case.logged_in.LoggedInInputBoundary;
import use_case.logged_in.LoggedInInteractor;
import use_case.logged_in.LoggedInOutputBoundary;
import use_case.logged_in_search.LoggedInSearchInputBoundary;
import use_case.logged_in_search.LoggedInSearchInteractor;
import use_case.logged_in_search.LoggedInSearchOutputBoundary;
import use_case.logged_in_search_result.LoggedInSearchResultInputBoundary;
import use_case.logged_in_search_result.LoggedInSearchResultInteractor;
import use_case.logged_in_search_result.LoggedInSearchResultOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.rate.RateInputBoundary;
import use_case.rate.RateInteractor;
import use_case.rate.RateOutputBoundary;
import use_case.rated_list.RatedListInputBoundary;
import use_case.rated_list.RatedListInteractor;
import use_case.rated_list.RatedListOutputBoundary;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputBoundary;
import use_case.search_result.SearchResultInputBoundary;
import use_case.search_result.SearchResultInteractor;
import use_case.search_result.SearchResultOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;

import use_case.to_logged_in_view.ToLoggedInViewInputBoundary;
import use_case.to_logged_in_view.ToLoggedInViewInteractor;
import use_case.to_logged_in_view.ToLoggedInViewOutputBoundary;

import use_case.to_home_view.ToHomeInteractor;
import use_case.to_home_view.ToHomeViewInputBoundary;
import use_case.to_home_view.ToHomeViewOutputBoundary;

import use_case.watched_list.WatchedListInputBoundary;
import use_case.watched_list.WatchedListInteractor;
import use_case.watched_list.WatchedListOutputBoundary;
import use_case.watchlist.WatchlistInputBoundary;
import use_case.watchlist.WatchlistInteractor;
import use_case.watchlist.WatchlistOutputBoundary;
import view.ChangePasswordView;
import view.HomeView;
import view.LoggedInSearchResultView;
import view.LoggedInSearchView;
import view.LoggedInView;
import view.LoginView;
import view.RateView;
import view.RatedListView;
import view.SearchResultView;
import view.SearchView;
import view.SignupView;
import view.ViewManager;
import view.WatchedListView;
import view.WatchlistView;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final UserFactory userFactory = new CommonUserFactory();
    private final MovieFactory movieFactory = new MovieFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
    private final MovieAccessObject movieAccessObject = new MovieAccessObject();

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private ChangePasswordViewModel changePasswordViewModel;
    private ChangePasswordView changePasswordView;
    private LoginView loginView;
    private HomeView homeView;
    private HomeViewModel homeViewModel;
    private SearchView searchView;
    private SearchViewModel searchViewModel;
    private SearchResultView searchResultView;
    private SearchResultViewModel searchResultViewModel;
    private LoggedInSearchResultViewModel loggedInSearchResultViewModel;
    private LoggedInSearchResultView loggedInSearchResultView;
    private LoggedInSearchViewModel loggedInSearchViewModel;
    private LoggedInSearchView loggedInSearchView;
    private LoggedInViewModel loggedInViewModel;
    private LoggedInView loggedInView;
    private RateViewModel rateViewModel;
    private RateView rateView;
    private RatedListView ratedListView;
    private RatedListViewModel ratedListViewModel;
    private WatchlistViewModel watchlistViewModel;
    private WatchlistView watchlistView;
    private WatchedListViewModel watchedListViewModel;
    private WatchedListView watchedListView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }


    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the Change Password View to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordView() {
        changePasswordViewModel = new ChangePasswordViewModel();
        changePasswordView = new ChangePasswordView(changePasswordViewModel);
        cardPanel.add(changePasswordView, changePasswordView.getViewName());
        return this;
    }

    /**
     * Adds the Home View to the application.
     * @return this builder
     */
    public AppBuilder addHomeView() {
        homeViewModel = new HomeViewModel();
        homeView = new HomeView(homeViewModel);
        cardPanel.add(homeView, homeView.getViewName());
        return this;
    }

    /**
     * Adds the Search View to the application.
     * @return this builder
     */
    public AppBuilder addSearchView() {
        searchViewModel = new SearchViewModel();
        searchView = new SearchView(searchViewModel);
        cardPanel.add(searchView, searchView.getViewName());
        return this;
    }

    /**
     * Adds the Search Result View to the application.
     * @return this builder
     */
    public AppBuilder addSearchResultView() {
        searchResultViewModel = new SearchResultViewModel();
        searchResultView = new SearchResultView(searchResultViewModel);
        cardPanel.add(searchResultView, searchResultView.getViewName());
        return this;
    }

    /**
     * Adds the Logged In View to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    /**
     * Adds the Logged In Search View to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInSearchView() {
        loggedInSearchViewModel = new LoggedInSearchViewModel();
        loggedInSearchView = new LoggedInSearchView(loggedInSearchViewModel);
        cardPanel.add(loggedInSearchView, loggedInSearchView.getViewName());
        return this;
    }

    /**
     * Adds the Logged In Search Result View to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInSearchResultView() {
        loggedInSearchResultViewModel = new LoggedInSearchResultViewModel();
        loggedInSearchResultView = new LoggedInSearchResultView(loggedInSearchResultViewModel);
        cardPanel.add(loggedInSearchResultView, loggedInSearchResultView.getViewName());
        return this;
    }

    /**
     * Adds the Rate View to the application.
     * @return this builder
     */
    public AppBuilder addRateView() {
        rateViewModel = new RateViewModel();
        rateView = new RateView(rateViewModel);
        cardPanel.add(rateView, rateView.getViewName());
        return this;
    }

    /**
     * Adds the Rated List View to the application.
     * @return this builder
     */
    public AppBuilder addRatedListView() {
        ratedListViewModel = new RatedListViewModel();
        ratedListView = new RatedListView(ratedListViewModel);
        cardPanel.add(ratedListView, ratedListView.getViewName());
        return this;
    }

    /**
     * Adds the Watched List View to the application.
     * @return this builder
     */
    public AppBuilder addWatchedListView() {
        watchedListViewModel = new WatchedListViewModel();
        watchedListView = new WatchedListView(watchedListViewModel);
        cardPanel.add(watchedListView, watchedListView.getViewName());
        return this;
    }

    /**
     * Adds the Watchlist View to the application.
     * @return this builder
     */
    public AppBuilder addWatchlistView() {
        watchlistViewModel = new WatchlistViewModel();
        watchlistView = new WatchlistView(watchlistViewModel);
        cardPanel.add(watchlistView, watchlistView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {

        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel, homeViewModel);

        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    public AppBuilder addToHomeViewUseCase(){
        final ToHomeViewOutputBoundary toHomeViewOutputBoundary = new ToHomeViewPresenter(viewManagerModel, homeViewModel, signupViewModel, loginViewModel, searchViewModel);

        final ToHomeViewInputBoundary toHomeViewInputBoundary = new ToHomeInteractor(toHomeViewOutputBoundary);
        final ToHomeViewController toHomeViewController = new ToHomeViewController(toHomeViewInputBoundary);
        signupView.setToHomeViewController(toHomeViewController);
        loginView.setToHomeViewController(toHomeViewController);
        searchView.setToHomeViewController(toHomeViewController);
        searchResultView.setToHomeViewController(toHomeViewController);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {

        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel, homeViewModel);

        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);

        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(changePasswordViewModel, loggedInViewModel, viewManagerModel);
        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);
        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        changePasswordView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {

        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                changePasswordViewModel, loginViewModel, signupViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);


        final LogoutController logoutController = new LogoutController(logoutInteractor);
        changePasswordView.setLogoutController(logoutController);
        loggedInView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Adds the Search Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSearchUseCase() {

        final SearchOutputBoundary searchOutputBoundary =
                new SearchPresenter(viewManagerModel, searchViewModel, searchResultViewModel);
        final SearchInputBoundary searchInteractor =
                new SearchInteractor(movieAccessObject, searchOutputBoundary, movieFactory);

        final SearchController controller = new SearchController(searchInteractor);
        searchView.setSearchController(controller);
        return this;
    }

    /**
     * Adds the Search Result Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSearchResultUseCase() {
        final SearchResultOutputBoundary searchResultOutputBoundary =
                new SearchResultPresenter(viewManagerModel, searchResultViewModel, homeViewModel);
        final SearchResultInputBoundary searchResultInteractor = new SearchResultInteractor(searchResultOutputBoundary);
        final SearchResultController controller = new SearchResultController(searchResultInteractor);
        searchResultView.setSearchResultController(controller);
        return this;
    }

    /**
     * Adds the Home Use Case to the application.
     * @return this builder
     */
    public AppBuilder addHomeUseCase() {
        final HomeOutputBoundary homeOutputBoundary =
                new HomePresenter(viewManagerModel, signupViewModel, loginViewModel, searchViewModel);
        final HomeInputBoundary homeInteractor = new HomeInteractor(homeOutputBoundary);
        final HomeController controller = new HomeController(homeInteractor);
        homeView.setHomeController(controller);
        return this;
    }

    /**
     * Adds the Logged In Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInUseCase() {
        final LoggedInOutputBoundary loggedInOutputBoundary = new LoggedInPresenter(
                viewManagerModel,
                changePasswordViewModel,
                loggedInSearchViewModel,
                watchlistViewModel,
                watchedListViewModel);
        final LoggedInInputBoundary loggedInInteractor = new LoggedInInteractor(loggedInOutputBoundary);
        final LoggedInController loggedInController = new LoggedInController(loggedInInteractor);
        loggedInView.setLoggedInController(loggedInController);
        return this;
    }

    /**
     * Adds the Logged In Search Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInSearchUseCase() {
        final LoggedInSearchOutputBoundary loggedInSearchOutputBoundary =
                new LoggedInSearchPresenter(viewManagerModel, loggedInSearchViewModel, loggedInSearchResultViewModel);
        final LoggedInSearchInputBoundary loggedInSearchInteractor =
                new LoggedInSearchInteractor(loggedInSearchOutputBoundary, movieAccessObject);
        final LoggedInSearchController loggedInSearchController =
                new LoggedInSearchController(loggedInSearchInteractor);
        loggedInSearchView.setLoggedInSearchController(loggedInSearchController);
        return this;
    }

    /**
     * Adds the Logged In Search Result Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInSearchResultUseCase() {
        final LoggedInSearchResultOutputBoundary loggedInSearchResultOutputBoundary =
                new LoggedInSearchResultPresenter(viewManagerModel, loggedInSearchResultViewModel);
        final LoggedInSearchResultInputBoundary loggedInSearchResultInteractor =
                new LoggedInSearchResultInteractor(loggedInSearchResultOutputBoundary);
        final LoggedInSearchResultController loggedInSearchResultController =
                new LoggedInSearchResultController(loggedInSearchResultInteractor);
        loggedInSearchResultView.setLoggedInSearchResultController(loggedInSearchResultController);
        return this;
    }

    /**
     * Adds the Rate Use Case to the application.
     * @return this builder
     */
    public AppBuilder addRateUseCase() {
        final RateOutputBoundary rateOutputBoundary =
                new RatePresenter(viewManagerModel, rateViewModel, loggedInViewModel);
        final RateInputBoundary rateInteractor =
                new RateInteractor(userDataAccessObject, rateOutputBoundary);
        final RateController rateController = new RateController(rateInteractor);
        rateView.setRateController(rateController);
        return this;
    }

    /**
     * Adds the Add To Watched List Use Case to the application.
     * @return this builder
     */
    public AppBuilder addAddToWatchedListUseCase() {
        final AddToWatchedListOutputBoundary addToWatchedListOutputBoundary =
                new AddToWatchedListPresenter(viewManagerModel, loggedInSearchResultViewModel, loggedInViewModel);
        final AddToWatchedListInputBoundary addToWatchedListInteractor =
                new AddToWatchedListInteractor(userDataAccessObject, movieAccessObject, addToWatchedListOutputBoundary);
        final AddToWatchedListController addToWatchedListController =
                new AddToWatchedListController(addToWatchedListInteractor);
        loggedInSearchResultView.setAddToWatchedListController(addToWatchedListController);
        return this;
    }

    /**
     * Adds the To Rate Use Case to the application.
     * @return this builder
     */
    public AppBuilder addGoToRateUseCase() {
        final GoRateOutputBoundary goToRateOutputBoundary = new GoRatePresenter(viewManagerModel, rateViewModel);
        final GoRateInputBoundary goRateInteractor = new GoRateInteractor(goToRateOutputBoundary);
        final GoRateController controller = new GoRateController(goRateInteractor);
        watchedListView.setGoToRateController(controller);
        return this;
    }

    /**
     * Adds the Add To Watchlist Use Case to the application.
     * @return this builder
     */
    public AppBuilder addAddToWatchlistUseCase() {
        final AddToWatchlistOutputBoundary addToWatchlistOutputBoundary =
                new AddToWatchlistPresenter(viewManagerModel, loggedInSearchResultViewModel, loggedInViewModel);
        final AddToWatchlistInputBoundary addToWatchlistInteractor =
                new AddToWatchlistInteractor(userDataAccessObject, movieAccessObject, addToWatchlistOutputBoundary);
        final AddToWatchlistController addToWatchlistController =
                new AddToWatchlistController(addToWatchlistInteractor);
        loggedInSearchResultView.setAddToWatchlistController(addToWatchlistController);
        return this;
    }

    /**
     * Adds the Watched List Use Case to the application.
     * @return this builder
     */
    public AppBuilder addWatchedListUseCase() {
        final WatchedListOutputBoundary watchedListOutputBoundary =
                new WatchedListPresenter(viewManagerModel, watchedListViewModel);
        final WatchedListInputBoundary watchedListInputBoundary = new WatchedListInteractor(watchedListOutputBoundary);
        final WatchedListController watchedListController = new WatchedListController(watchedListInputBoundary);
        watchedListView.setWatchedListController(watchedListController);
        return this;
    }

    /**
     * Adds the Watchlist Use Case to the application.
     * @return this builder
     */
    public AppBuilder addWatchlistUseCase() {
        final WatchlistOutputBoundary watchlistOutputBoundary =
                new WatchlistPresenter(viewManagerModel, watchlistViewModel);
        final WatchlistInputBoundary watchlistInputBoundary = new WatchlistInteractor(watchlistOutputBoundary);
        final WatchlistController watchlistController = new WatchlistController(watchlistInputBoundary);
        watchlistView.setWatchlistController(watchlistController);
        return this;
    }

    /**
     * Adds the Rated List Use Case to the application.
     * @return this builder
     */
    public AppBuilder addRatedListUseCase() {
        final RatedListOutputBoundary ratedListOutputBoundary =
                new RatedListPresenter(viewManagerModel, loggedInViewModel, ratedListViewModel);
        final RatedListInputBoundary ratedListInputBoundary = new RatedListInteractor(ratedListOutputBoundary, userDataAccessObject);
        final RatedListController ratedListController = new RatedListController(ratedListInputBoundary);
        ratedListView.setRatedListController(ratedListController);
        return this;
    }

    /**
     * Adds the Get Watched List Use Case to the application.
     * @return this builder
     */
    public AppBuilder addGetWatchedListUseCase() {
        final GetWatchedListOutputBoundary getWatchedListOutputBoundary =
                new GetWatchedListPresenter(viewManagerModel, loggedInViewModel, watchedListViewModel);
        final GetWatchedListInputBoundary getWatchedListInputBoundary =
                new GetWatchedListInteractor(getWatchedListOutputBoundary, userDataAccessObject);
        final GetWatchedListController getWatchedListController =
                new GetWatchedListController(getWatchedListInputBoundary);
        loggedInView.setWatchedListController(getWatchedListController);
        return this;
    }

    /**
     * Adds the Get Rated List Use Case to the application.
     * @return this builder
     */
    public AppBuilder addGetRatedListUseCase() {
        final GetRateListOutputBoundary getRateListOutputBoundary =
                new GetRatedListPresenter(viewManagerModel, loggedInViewModel, ratedListViewModel);
        final GetRateListInputBoundary getRateListInputBoundary =
                new GetRateListInteractor(userDataAccessObject, getRateListOutputBoundary);
        final GetRatedListController getRatedListController = new GetRatedListController(getRateListInputBoundary);
        loggedInView.setGetRatedListController(getRatedListController);
        return this;
    }

    /**
     * Adds the Get Watchlist Use Case to the application.
     * @return this builder
     */
    public AppBuilder addGetWatchlistUseCase() {
        final GetWatchlistOutputBoundary getWatchlistOutputBoundary =
                new GetWatchlistPresenter(viewManagerModel, loggedInViewModel, watchlistViewModel);
        final GetWatchlistInputBoundary getWatchlistInputBoundary =
                new GetWatchlistInteractor(getWatchlistOutputBoundary, userDataAccessObject);
        final GetWatchlistController getWatchlistController = new GetWatchlistController(getWatchlistInputBoundary);
        loggedInView.setWatchlistController(getWatchlistController);
        return this;
    }

    /**
     * Adds the To Logged In View Use Case to the application.
     * @return this builder
     */
    public AppBuilder addToLoggedInViewUseCase() {
        final ToLoggedInViewOutputBoundary toLoggedInViewOutputBoundary =
                new ToLoggedInViewPresenter(viewManagerModel, loggedInViewModel);
        final ToLoggedInViewInputBoundary toLoggedInViewInputBoundary =
                new ToLoggedInViewInteractor(toLoggedInViewOutputBoundary);
        final ToLoggedInViewController toLoggedInViewController =
                new ToLoggedInViewController(toLoggedInViewInputBoundary);
        loggedInSearchView.setToLoggedInViewController(toLoggedInViewController);
        loggedInSearchResultView.setToLoggedInViewController(toLoggedInViewController);
        watchedListView.setGoToLoggedInViewController(toLoggedInViewController);
        watchlistView.setToLoggedInViewController(toLoggedInViewController);
        ratedListView.setToLoggedInViewController(toLoggedInViewController);
        changePasswordView.setToLoggedInViewController(toLoggedInViewController);
        rateView.setToLoggedInViewController(toLoggedInViewController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Movie Application");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(homeView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
