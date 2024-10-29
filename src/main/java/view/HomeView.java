package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.home.HomeController;
import interface_adapter.home.HomeViewModel;

/**
 * The View for Home. This screen has 3 buttons, Go to log in, Go to sigh up, and Go to search.
 */
public class HomeView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "home";

    private final HomeViewModel homeViewModel;
    private HomeController homeController;

    private final JButton toSignup;
    private final JButton toLogin;
    private final JButton toSearch;

    public HomeView(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
        homeViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(homeViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        toLogin = new JButton(HomeViewModel.TO_LOGIN_BUTTON_LABEL);
        buttons.add(toLogin);
        toSignup = new JButton(HomeViewModel.TO_SIGNUP_BUTTON_LABEL);
        buttons.add(toSignup);
        toSearch = new JButton(HomeViewModel.TO_SEARCH_BUTTON_LABEL);
        buttons.add(toSearch);

        toLogin.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        homeController.switchToLoginView();
                    }
                }
        );

        toSignup.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        homeController.switchToSignupView();
                    }
                }
        );

        toSearch.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        homeController.switchToSearchView();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
    }

    public String getViewName() {
        return viewName;
    }

    public void setHomeController(HomeController controller) {
        this.homeController = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
