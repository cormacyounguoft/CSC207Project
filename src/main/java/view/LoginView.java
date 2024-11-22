package view;

import java.awt.*;
import javax.swing.*;
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
        this.setLayout(new BorderLayout(20, 20));
        this.setBackground(new Color(240, 248, 255)); // Light blue background
        this.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding around the panel

        // Title Label
        final JLabel title = new JLabel("Login Screen", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(0, 51, 102)); // Dark blue
        this.add(title, BorderLayout.NORTH);

        // Input Fields Panel
        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 1, 10, 10)); // Fields and errors with spacing
        inputPanel.setOpaque(false);

        inputPanel.add(createLabeledField("Username", usernameInputField, usernameErrorField));
        inputPanel.add(createLabeledField("Password", passwordInputField, passwordErrorField));
        this.add(inputPanel, BorderLayout.CENTER);

        // Buttons Panel
        final JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 15, 0)); // Side-by-side buttons
        buttonsPanel.setOpaque(false);

        logIn = createStyledButton("Log In");
        cancel = createStyledButton("Cancel");

        buttonsPanel.add(logIn);
        buttonsPanel.add(cancel);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        // Add Action Listeners
        logIn.addActionListener(evt -> {
            final LoginState currentState = loginViewModel.getState();
            loginController.execute(currentState.getUsername(), currentState.getPassword());
        });

        cancel.addActionListener(evt -> toHomeViewController.toHomeView());

        // Attach Document Listeners
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
     */
    private JPanel createLabeledField(String labelText, JTextField inputField, JLabel errorLabel) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.PLAIN, 16));
        label.setForeground(new Color(0, 51, 102));

        inputField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        inputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(173, 216, 230), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        errorLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        errorLabel.setForeground(Color.RED);

        panel.add(label, BorderLayout.NORTH);
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(errorLabel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Utility method to create a styled button.
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.PLAIN, 22));
        button.setBackground(new Color(93, 186, 255)); // Pastel blue
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(124, 183, 205), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 50)); // Consistent size
        return button;
    }

    /**
     * Adds a document listener to a text field.
     */
    private void addDocumentListener(JTextField textField, DocumentListenerCallback callback) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                callback.update(textField.getText());
            }

            public void removeUpdate(DocumentEvent e) {
                callback.update(textField.getText());
            }

            public void changedUpdate(DocumentEvent e) {
                callback.update(textField.getText());
            }
        });
    }

    private interface DocumentListenerCallback {
        void update(String text);
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
}
