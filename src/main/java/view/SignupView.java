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

public class SignupView extends JPanel {

    private final String viewName = "sign up";

    private final SignupViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final JLabel errorField = new JLabel(); // Single error label for all errors

    private final JButton signUp;
    private final JButton cancel;
    private final JButton toLogin;

    private SignupController signupController;
    private ToHomeViewController toHomeViewController;

    public SignupView(SignupViewModel signupViewModel) {
        this.signupViewModel = signupViewModel;
        this.signupViewModel.addPropertyChangeListener(evt -> {
            final SignupState state = (SignupState) evt.getNewValue();
            setFields(state);

            // Set the error message to the single error label
            if (state.getUsernameError() != null) {
                errorField.setText(state.getUsernameError());
            } else if (state.getPasswordError() != null) {
                errorField.setText(state.getPasswordError());
            } else if (state.getRepeatPasswordError() != null) {
                errorField.setText(state.getRepeatPasswordError());
            } else {
                errorField.setText(""); // Clear the error message if no errors
            }
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
        final JPanel inputPanel = new JPanel(new GridLayout(4, 1, 10, 10)); // 3 inputs + 1 error label
        inputPanel.setOpaque(false);

        inputPanel.add(createLabeledField("Username", usernameInputField));
        inputPanel.add(createLabeledField("Password", passwordInputField));
        inputPanel.add(createLabeledField("Repeat Password", repeatPasswordInputField));

        // Add error field below all inputs
        errorField.setFont(new Font("SansSerif", Font.ITALIC, 12));
        errorField.setForeground(Color.RED);
        errorField.setHorizontalAlignment(SwingConstants.CENTER);
        inputPanel.add(errorField);

        this.add(inputPanel, BorderLayout.CENTER);

        // Buttons Panel
        final JPanel buttonsPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        buttonsPanel.setOpaque(false);

        toLogin = createStyledButton("Go to Login");
        signUp = createStyledButton("Sign Up");
        cancel = createStyledButton("Cancel");

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
     * Creates a labeled field.
     */
    private JPanel createLabeledField(String labelText, JTextField inputField) {
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

        panel.add(label, BorderLayout.NORTH);
        panel.add(inputField, BorderLayout.CENTER);

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

    public void setToHomeViewController(ToHomeViewController controller) {
        this.toHomeViewController = controller;
    }
}
