package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.to_home_view.ToHomeViewController;

/**
 * The View for when the user is logging into the program.
 */
public class LoginView extends JPanel {
    private static final int INPUT_PANEL_GAP = 10;
    private static final int INPUT_PANEL_ROWS = 4;

    private final String viewName = "log in";
    private final LoginViewModel loginViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    private final JButton logIn;
    private final JButton cancel;
    private LoginController loginController;
    private ToHomeViewController toHomeViewController;

    public LoginView(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(evt -> {
            final LoginState state = (LoginState) evt.getNewValue();
            setFields(state);
            usernameErrorField.setText(state.getLoginError());
        });

        // Set layout and background
        this.setLayout(new BorderLayout(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));
        this.setBackground(new Color(Constants.COLOUR_R, Constants.COLOUR_G, Constants.COLOUR_B));
        this.setBorder(new EmptyBorder(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT,
                Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));

        final JLabel title = new JLabel("Login Screen", SwingConstants.CENTER);
        title.setFont(new Font(Constants.FONT_TYPE, Font.BOLD, Constants.FONT_LARGEST));
        title.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
        this.add(title, BorderLayout.NORTH);

        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(INPUT_PANEL_ROWS, 1, INPUT_PANEL_GAP, INPUT_PANEL_GAP));
        inputPanel.setOpaque(false);

        inputPanel.add(labelCreator("Username", usernameInputField, usernameErrorField));
        inputPanel.add(labelCreator("Password", passwordInputField, passwordErrorField));
        this.add(inputPanel, BorderLayout.CENTER);

        final JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        buttonsPanel.setOpaque(false);

        logIn = buttonFactory("Log In");
        cancel = buttonFactory("Cancel");

        buttonsPanel.add(logIn);
        buttonsPanel.add(cancel);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        logIn.addActionListener(evt -> {
            final LoginState currentState = loginViewModel.getState();
            loginController.execute(currentState.getUsername(), currentState.getPassword());
        });

        cancel.addActionListener(evt -> toHomeViewController.toHomeView());

        addDocumentListener(usernameInputField, text -> {
            final LoginState currentState = loginViewModel.getState();
            currentState.setUsername(text);
            loginViewModel.setState(currentState);
        });

        addDocumentListener(passwordInputField, text -> {
            final LoginState currentState = loginViewModel.getState();
            currentState.setPassword(text);
            loginViewModel.setState(currentState);
        });
    }

    /**
     * Creates a labeled field with an error message label underneath.
     * @param inputField input field for the label.
     * @param errorLabel label for error.
     * @param labelText text for label.
     * @return label that is created.
     */
    private JPanel labelCreator(String labelText, JTextField inputField, JLabel errorLabel) {
        final JPanel panel = new JPanel(new BorderLayout(Constants.LABEL_BORDER_LAYOUT, Constants.LABEL_BORDER_LAYOUT));
        panel.setOpaque(false);

        final JLabel label = new JLabel(labelText);
        label.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        label.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));

        inputField.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        inputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(Constants.LINE_BORDER_R, Constants.LINE_BORDER_G,
                        Constants.LINE_BORDER_B), 1),
                BorderFactory.createEmptyBorder(Constants.LABEL_BORDER_LAYOUT, Constants.LABEL_BORDER_LAYOUT,
                        Constants.LABEL_BORDER_LAYOUT, Constants.LABEL_BORDER_LAYOUT)
        ));

        errorLabel.setFont(new Font(Constants.FONT_TYPE, Font.ITALIC, Constants.FONT_SMALLEST));
        errorLabel.setForeground(Color.RED);

        panel.add(label, BorderLayout.NORTH);
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(errorLabel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Utility method to create a styled button.
     * @param text text to be visual in the button.
     * @return the styled button that is created.
     */
    private JButton buttonFactory(String text) {
        final JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.PLAIN, Constants.FONT_LARGER));
        button.setBackground(new Color(Constants.BACKGROUND_COLOUR_R, Constants.BACKGROUND_COLOUR_G,
                Constants.BACKGROUND_COLOUR_B));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(Constants.BORDER_COLOUR_R, Constants.BORDER_COLOUR_G,
                Constants.BORDER_COLOUR_B), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT));
        return button;
    }

    /**
     * Adds a document listener to a text field.
     * @param textField textField The text field to which the document listener is added.
     * @param callback Functional interface used to pass a method when a text changes.
     */
    private void addDocumentListener(JTextField textField, DocumentListenerCallback callback) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent event) {
                callback.update(textField.getText());
            }

            public void removeUpdate(DocumentEvent event) {
                callback.update(textField.getText());
            }

            public void changedUpdate(DocumentEvent event) {
                callback.update(textField.getText());
            }
        });
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
        passwordInputField.setText(state.getPassword());
    }

    public String getViewName() {
        return viewName;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void setToHomeViewController(ToHomeViewController toHomeViewController) {
        this.toHomeViewController = toHomeViewController;
    }

    /**
     * A callback interface for handling updates to a text field.
     */
    private interface DocumentListenerCallback {
        void update(String text);
    }
}
