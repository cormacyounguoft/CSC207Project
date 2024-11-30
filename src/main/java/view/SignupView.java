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

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.to_home_view.ToHomeViewController;

/**
 * View for Signup.
 */
public class SignupView extends JPanel {

    private final String viewName = "sign up";

    private final SignupViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final JLabel errorField = new JLabel();

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
            }
            else if (state.getPasswordError() != null) {
                errorField.setText(state.getPasswordError());
            }
            else if (state.getRepeatPasswordError() != null) {
                errorField.setText(state.getRepeatPasswordError());
            }
            else {
                errorField.setText("");
            }
        });

        // Set layout and background
        this.setLayout(new BorderLayout(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));
        this.setBackground(new Color(Constants.COLOUR_R, Constants.COLOUR_G, Constants.COLOUR_B));
        this.setBorder(new EmptyBorder(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT,
                Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));

        // Title Label
        final JLabel title = new JLabel("Signup Screen", SwingConstants.CENTER);
        title.setFont(new Font(Constants.FONT_TYPE, Font.BOLD, Constants.FONT_LARGEST));
        title.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
        this.add(title, BorderLayout.NORTH);

        // Input Fields Panel
        final JPanel inputPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        inputPanel.setOpaque(false);

        inputPanel.add(createLabeledField("Username", usernameInputField));
        inputPanel.add(createLabeledField("Password", passwordInputField));
        inputPanel.add(createLabeledField("Repeat Password", repeatPasswordInputField));

        // Add error field below all inputs
        errorField.setFont(new Font(Constants.FONT_TYPE, Font.ITALIC, Constants.FONT_SMALLEST));
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
     *
     * @param labelText  the text to display as the label for the input field.
     * @param inputField inputField the input field where the user enters text.
     * @return a JPanel containing the label, input field, and error message label.
     */
    private JPanel createLabeledField(String labelText, JTextField inputField) {
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

        panel.add(label, BorderLayout.NORTH);
        panel.add(inputField, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Utility method to create a styled button.
     *
     * @param text the text to be displayed in the styled button.
     * @return the styled button created.
     */
    private JButton createStyledButton(String text) {
        final JButton button = new JButton(text);
        button.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_LARGER));
        button.setBackground(new Color(Constants.BACKGROUND_COLOUR_R, Constants.BACKGROUND_COLOUR_G,
                Constants.BACKGROUND_COLOUR_B));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(Constants.BORDER_COLOUR_R,
                Constants.BORDER_COLOUR_G, Constants.BORDER_COLOUR_B), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT));
        return button;
    }

    /**
     * Adds a document listener to a text field.
     *
     * @param textField textField the text field to which the document listener is added
     * @param callback  the callback to handle updates to the text field's content
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

    /**
     * A callback interface for handling updates to a text field.
     */
    private interface DocumentListenerCallback {
        void update(String text);

    }
}
