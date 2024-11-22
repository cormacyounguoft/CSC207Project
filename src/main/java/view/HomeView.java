package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomeViewModel;

/**
 * The View for Home. This screen has 3 buttons: Go to log in, Go to sign up, and Go to search.
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

        // Set layout and background
        this.setLayout(new BorderLayout(20, 20));
        this.setBackground(new Color(240, 248, 255)); // Light blue background
        this.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding around the panel

        // Title Label
        final JLabel title = new JLabel(homeViewModel.TITLE_LABEL, SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(0, 51, 102)); // Dark blue
        this.add(title, BorderLayout.NORTH);

        // Buttons Panel
        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 1, 15, 15)); // 3 rows, spacing between buttons
        buttonsPanel.setOpaque(false); // Blend with background

        // Create buttons with a uniform pastel green color
        toLogin = buttonFactory(HomeViewModel.TO_LOGIN_BUTTON_LABEL);
        toSignup = buttonFactory(HomeViewModel.TO_SIGNUP_BUTTON_LABEL);
        toSearch = buttonFactory(HomeViewModel.TO_SEARCH_BUTTON_LABEL);

        buttonsPanel.add(toLogin);
        buttonsPanel.add(toSignup);
        buttonsPanel.add(toSearch);

        // Add some padding around the buttons panel
        JPanel centeredButtonsPanel = new JPanel(new BorderLayout());
        centeredButtonsPanel.setOpaque(false);
        centeredButtonsPanel.setBorder(new EmptyBorder(10, 50, 10, 50)); // Padding inside the panel
        centeredButtonsPanel.add(buttonsPanel, BorderLayout.CENTER);

        this.add(centeredButtonsPanel, BorderLayout.CENTER);

        // Add Action Listeners
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
    }

    /**
     * Method to create a styled button.
     */
    private JButton buttonFactory(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.PLAIN, 26));
        button.setBackground(new Color(93, 186, 255)); // Pastel green
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(124, 183, 205), 2)); // Slightly darker border
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(100, 40)); // Set a fixed size for smaller buttons
        return button;
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
