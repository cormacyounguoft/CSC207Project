package view;

import interface_adapter.get_rated_list.GetRatedListController;
import interface_adapter.get_watched_list.GetWatchedListController;
import interface_adapter.get_watchlist.GetWatchlistController;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.logout.LogoutController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is Logged In.
 */
public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "logged in";

    private final LoggedInViewModel loggedInViewModel;
    private LoggedInController loggedInController;
    private LogoutController logoutController;
    private GetWatchedListController watchedListController;
    private GetWatchlistController watchlistController;
    private GetRatedListController getRatedListController;

    private final JButton toSearch;
    private final JButton toWatchList;
    private final JButton toWatchedList;
    private final JButton toChangePassword;
    private final JButton logout;
    private final JButton toRatedList;
    private final JButton toDashboard; // New Dashboard Button
    final JLabel username;

    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(loggedInViewModel.TITLE_LABEL);
        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        toSearch = new JButton(loggedInViewModel.TO_SEARCH_BUTTON_LABEL);
        buttons.add(toSearch);
        toWatchList = new JButton(loggedInViewModel.TO_WATCHLIST_BUTTON_LABEL);
        buttons.add(toWatchList);
        toWatchedList = new JButton(loggedInViewModel.TO_WATCHED_LIST_BUTTON_LABEL);
        buttons.add(toWatchedList);
        toChangePassword = new JButton(loggedInViewModel.TO_CHANGE_PASSWORD_BUTTON_LABEL);
        buttons.add(toChangePassword);
        logout = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logout);
        toRatedList = new JButton("Go Rated List");
        buttons.add(toRatedList);
        toDashboard = new JButton("Go Dashboard");
        buttons.add(toDashboard);

        toSearch.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final LoggedInState currentState = loggedInViewModel.getState();
                        loggedInController.switchToLoggedInSearchView(currentState.getUsername());
                    }
                }
        );

        toWatchList.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final LoggedInState currentState = loggedInViewModel.getState();
                        watchlistController.execute(currentState.getUsername());
                    }
                }
        );

        toRatedList.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final LoggedInState currentState = loggedInViewModel.getState();
                        getRatedListController.execute(currentState.getUsername());
                    }
                }
        );

        toWatchedList.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final LoggedInState currentState = loggedInViewModel.getState();
                        watchedListController.execute(currentState.getUsername());
                    }
                }
        );

        toChangePassword.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final LoggedInState currentState = loggedInViewModel.getState();
                        loggedInController.switchToChangePasswordView(currentState.getUsername());
                    }
                }
        );

        logout.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final LoggedInState currentState = loggedInViewModel.getState();
                        logoutController.execute(currentState.getUsername());
                    }
                }
        );

        toDashboard.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final LoggedInState currentState = loggedInViewModel.getState();
                        loggedInController.switchToDashboardView(currentState.getUsername());
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(username);
        this.add(buttons);
    }

    public String getViewName() {
        return viewName;
    }

    public void setLoggedInController(LoggedInController loggedInController) {
        this.loggedInController = loggedInController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setWatchedListController(GetWatchedListController watchedListController) {
        this.watchedListController = watchedListController;
    }

    public void setWatchlistController(GetWatchlistController watchlistController) {
        this.watchlistController = watchlistController;
    }

    public void setGetRatedListController(GetRatedListController getRatedListController) {
        this.getRatedListController = getRatedListController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText("username: " + state.getUsername());
        }
    }
}
