package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import interface_adapter.home.HomeController;
import interface_adapter.home.HomeViewModel;

/**
 * The View for Home. This screen has 3 buttons: Go to log in, Go to sign up, and Go to search.
 */
public class HomeView extends JPanel implements ActionListener, PropertyChangeListener {
    private static final int GRID_HOME_GAP = 15;
    private static final int GRID_HOME_ROWS = 3;
    private static final int HOME_FONT = 26;
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 40;

    private final String viewName = "home";

    private final HomeViewModel homeViewModel;
    private HomeController homeController;

    private final JButton toSignup;
    private final JButton toLogin;
    private final JButton toSearch;

    public HomeView(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;

        // Set layout and background
        this.setLayout(new BorderLayout(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));
        this.setBackground(new Color(Constants.COLOUR_R, Constants.COLOUR_G, Constants.COLOUR_B));
        this.setBorder(new EmptyBorder(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT,
                Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));

        // Title Label
        final JLabel title = new JLabel(homeViewModel.TITLE, SwingConstants.CENTER);

        title.setFont(new Font(Constants.FONT_TYPE, Font.BOLD, Constants.FONT_LARGEST));
        title.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
        this.add(title, BorderLayout.NORTH);

        // Buttons Panel
        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(GRID_HOME_ROWS, 1, GRID_HOME_GAP, GRID_HOME_GAP));
        buttonsPanel.setOpaque(false);

        // Create buttons with a uniform pastel green color
        toLogin = buttonFactory(HomeViewModel.TO_LOGIN_BUTTON);
        toSignup = buttonFactory(HomeViewModel.TO_SIGNUP_BUTTON);
        toSearch = buttonFactory(HomeViewModel.TO_SEARCH_BUTTON);

        buttonsPanel.add(toLogin);
        buttonsPanel.add(toSignup);
        buttonsPanel.add(toSearch);

        // Add some padding around the buttons panel
        final JPanel centeredButtonsPanel = new JPanel(new BorderLayout());
        centeredButtonsPanel.setOpaque(false);
        centeredButtonsPanel.setBorder(new EmptyBorder(Constants.CENTERED_Y_AXIS, Constants.CENTERED_X_AXIS,
                Constants.CENTERED_Y_AXIS, Constants.CENTERED_X_AXIS));
        centeredButtonsPanel.add(buttonsPanel, BorderLayout.CENTER);

        this.add(centeredButtonsPanel, BorderLayout.CENTER);

        // Add Action Listeners
        toLogin.addActionListener(
                evt -> homeController.switchToLoginView()
        );
        toSignup.addActionListener(
                evt -> homeController.switchToSignupView()
        );
        toSearch.addActionListener(
                evt -> homeController.switchToSearchView()
        );
    }

    /**
     * Method to create a styled button.
     * @param text the text in the button.
     * @return returns the styled button created.
     */
    private JButton buttonFactory(String text) {
        final JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.PLAIN, HOME_FONT));
        button.setBackground(new Color(Constants.BACKGROUND_COLOUR_R,
                Constants.BACKGROUND_COLOUR_G, Constants.BACKGROUND_COLOUR_B));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(Constants.BORDER_COLOUR_R,
                Constants.BORDER_COLOUR_G, Constants.BORDER_COLOUR_B), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
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
