package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;

public class ChangePasswordView extends JPanel {

    private final String viewName = "change password";
    private final ChangePasswordViewModel changePasswordViewModel;

    private final JLabel passwordErrorField = new JLabel();
    private ChangePasswordController changePasswordController;
    private LogoutController logoutController;
    private ToLoggedInViewController toLoggedInViewController;

    private final JLabel usernameLabel = new JLabel();
    private final JTextField passwordInputField = new JTextField(15);

    private final JButton changePassword;
    private final JButton logOut;
    private final JButton cancel;

    public ChangePasswordView(ChangePasswordViewModel changePasswordViewModel) {
        this.changePasswordViewModel = changePasswordViewModel;
        this.changePasswordViewModel.addPropertyChangeListener(evt -> {
            final ChangePasswordState state = (ChangePasswordState) evt.getNewValue();
            usernameLabel.setText(state.getUsername());
            passwordErrorField.setText(state.getPasswordError());
        });

        // Set layout and background
        this.setLayout(new BorderLayout(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));
        this.setBackground(new Color(Constants.COLOUR_R, Constants.COLOUR_G, Constants.COLOUR_B)); // Light blue background
        this.setBorder(new EmptyBorder(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT)); // Padding around the panel

        // Title Section
        final JLabel title = new JLabel("Change Password Screen", SwingConstants.CENTER);
        title.setFont(new Font(Constants.FONT_TYPE, Font.BOLD, Constants.FONT_LARGEST));
        title.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B)); // Dark blue
        this.add(title, BorderLayout.NORTH);

        // Input Panel
        final JPanel inputPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        inputPanel.setOpaque(false);

        JLabel usernamePrompt = new JLabel("Currently logged in as: ");
        usernamePrompt.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        usernamePrompt.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));

        usernameLabel.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        usernameLabel.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));

        JPanel usernamePanel = new JPanel();
        usernamePanel.setOpaque(false);
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.Y_AXIS));
        usernamePrompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernamePanel.add(usernamePrompt);
        usernamePanel.add(usernameLabel);

        inputPanel.add(usernamePanel);
        inputPanel.add(createLabeledField("New Password", passwordInputField));
        passwordErrorField.setFont(new Font(Constants.FONT_TYPE, Font.ITALIC, Constants.FONT_SMALLEST));
        passwordErrorField.setForeground(Color.RED);
        inputPanel.add(passwordErrorField);

        this.add(inputPanel, BorderLayout.CENTER);

        // Buttons Panel
        final JPanel buttonsPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        buttonsPanel.setOpaque(false);

        logOut = createStyledButton("Log Out");
        changePassword = createStyledButton("Change Password");
        cancel = createStyledButton("Cancel");

        buttonsPanel.add(logOut);
        buttonsPanel.add(changePassword);
        buttonsPanel.add(cancel);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        // Add Action Listeners
        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final ChangePasswordState currentState = changePasswordViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
                changePasswordViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        changePassword.addActionListener(evt -> {
            final ChangePasswordState currentState = changePasswordViewModel.getState();
            changePasswordController.execute(currentState.getUsername(), currentState.getPassword());
            if (currentState.getPasswordError() == null) {
                JOptionPane.showMessageDialog(null, "The password has been changed.");
            }
            passwordInputField.setText(null);
        });

        logOut.addActionListener(evt -> {
            final ChangePasswordState currentState = changePasswordViewModel.getState();
            logoutController.execute(currentState.getUsername());
        });

        cancel.addActionListener(evt -> {
            final ChangePasswordState currentState = changePasswordViewModel.getState();
            toLoggedInViewController.toLoggedInView(currentState.getUsername());
            passwordInputField.setText(null);
        });
    }

    /**
     * Creates a labeled input field.
     */
    private JPanel createLabeledField(String labelText, JTextField inputField) {
        JPanel panel = new JPanel(new BorderLayout(Constants.LABEL_BORDER_LAYOUT, Constants.LABEL_BORDER_LAYOUT));
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        label.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));

        inputField.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        inputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(Constants.LINE_BORDER_R, Constants.LINE_BORDER_G, Constants.LINE_BORDER_B), Constants.LINE_BORDER_THICKNESS),
                BorderFactory.createEmptyBorder(Constants.LABEL_BORDER_LAYOUT, Constants.LABEL_BORDER_LAYOUT, Constants.LABEL_BORDER_LAYOUT, Constants.LABEL_BORDER_LAYOUT)
        ));

        panel.add(label, BorderLayout.NORTH);
        panel.add(inputField, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Creates a styled button.
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_LARGER));
        button.setBackground(new Color(Constants.BACKGROUND_COLOUR_R, Constants.BACKGROUND_COLOUR_G, Constants.BACKGROUND_COLOUR_B)); // Pastel blue
        button.setForeground(Color.BLACK); // Black text for visibility
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(Constants.BORDER_COLOUR_R, Constants.BORDER_COLOUR_G, Constants.BORDER_COLOUR_B), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT)); // Consistent size
        return button;
    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController controller) {
        this.changePasswordController = controller;
    }

    public void setLogoutController(LogoutController controller) {
        this.logoutController = controller;
    }

    public void setToLoggedInViewController(ToLoggedInViewController controller) {
        this.toLoggedInViewController = controller;
    }
}
