package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.to_home_view.ToHomeViewController;

/**
 * The View for the Signup Use Case.
 */
public class SignupView extends JPanel {
    private final String viewName = "sign up";

    private final SignupViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final JLabel repeatPasswordErrorField = new JLabel();

    private final JButton signUp;
    private final JButton cancel;
    private final JButton toLogin;

    private SignupController signupController;
    private ToHomeViewController toHomeViewController;

    public SignupView(SignupViewModel signupViewModel) {
        this.signupViewModel = signupViewModel;
        signupViewModel.addPropertyChangeListener(evt -> {
            final SignupState state = (SignupState) evt.getNewValue();
            setFields(state);
        });

        // Set layout and background
        this.setLayout(new BorderLayout(20, 20));
        this.setBackground(new Color(240, 248, 255)); // Light blue background
        this.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding around the panel

        // Title Label
        final JLabel title = new JLabel("Signup Screen", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(0, 51, 102)); // Dark blue
        this.add(title, BorderLayout.NORTH);

        // Input Fields Panel
        final JPanel inputPanel = new JPanel(new GridLayout(6, 1, 10, 10)); // Fields and errors with spacing
        inputPanel.setOpaque(false);

        inputPanel.add(labelCreator("Username", usernameInputField, usernameErrorField));
        inputPanel.add(labelCreator("Password", passwordInputField, passwordErrorField));
        inputPanel.add(labelCreator("Repeat Password", repeatPasswordInputField, repeatPasswordErrorField));
        this.add(inputPanel, BorderLayout.CENTER);

        // Buttons Panel
        final JPanel buttonsPanel = new JPanel(new GridLayout(1, 3, 15, 0)); // Side-by-side buttons
        buttonsPanel.setOpaque(false);

        toLogin = buttonFactory("Go to Login");
        signUp = buttonFactory("Sign Up");
        cancel = buttonFactory("Cancel");

        buttonsPanel.add(toLogin);
        buttonsPanel.add(signUp);
        buttonsPanel.add(cancel);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        // Add Action Listeners
        signUp.addActionListener(evt -> {
            final SignupState currentState = signupViewModel.getState();
            signupController.execute(
                    currentState.getUsername(),
                    currentState.getPassword(),
                    currentState.getRepeatPassword()
            );
        });

        cancel.addActionListener(evt -> toHomeViewController.toHomeView());

        toLogin.addActionListener(evt -> signupController.switchToLoginView());

        // Attach Document Listeners
        addDocumentListener(usernameInputField, text -> {
            final SignupState currentState = signupViewModel.getState();
            currentState.setUsername(text);
            signupViewModel.setState(currentState);
        });

        addDocumentListener(passwordInputField, text -> {
            final SignupState currentState = signupViewModel.getState();
            currentState.setPassword(text);
            signupViewModel.setState(currentState);
        });

        addDocumentListener(repeatPasswordInputField, text -> {
            final SignupState currentState = signupViewModel.getState();
            currentState.setRepeatPassword(text);
            signupViewModel.setState(currentState);
        });
    }

    /**
     * Creates a labeled field with an error message label underneath.
     */
    private JPanel labelCreator(String labelText, JTextField inputField, JLabel errorLabel) {
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
    private JButton buttonFactory(String text) {
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

    private void setFields(SignupState state) {
        usernameInputField.setText(state.getUsername());
        passwordInputField.setText(state.getPassword());
        repeatPasswordInputField.setText(state.getRepeatPassword());
    }

    public String getViewName() {
        return viewName;
    }

    public void setSignupController(SignupController controller) {
        this.signupController = controller;
    }

    public void setToHomeViewController(ToHomeViewController toHomeViewController) {
        this.toHomeViewController = toHomeViewController;
    }
}
