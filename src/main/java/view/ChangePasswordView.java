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
        this.setLayout(new BorderLayout(20, 20));
        this.setBackground(new Color(240, 248, 255)); // Light blue background
        this.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding around the panel

        // Title Section
        final JLabel title = new JLabel("Change Password Screen", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(0, 51, 102)); // Dark blue
        this.add(title, BorderLayout.NORTH);

        // Input Panel
        final JPanel inputPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        inputPanel.setOpaque(false);

        JLabel usernamePrompt = new JLabel("Currently logged in as: ");
        usernamePrompt.setFont(new Font("SansSerif", Font.PLAIN, 16));
        usernamePrompt.setForeground(new Color(0, 51, 102));

        usernameLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        usernameLabel.setForeground(new Color(0, 51, 102));

        JPanel usernamePanel = new JPanel();
        usernamePanel.setOpaque(false);
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.Y_AXIS));
        usernamePrompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernamePanel.add(usernamePrompt);
        usernamePanel.add(usernameLabel);

        inputPanel.add(usernamePanel);
        inputPanel.add(createLabeledField("New Password", passwordInputField));
        passwordErrorField.setFont(new Font("SansSerif", Font.ITALIC, 12));
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
            JOptionPane.showMessageDialog(null, "The password has been changed.");
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
     * Creates a styled button.
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.PLAIN, 22));
        button.setBackground(new Color(93, 186, 255)); // Pastel blue
        button.setForeground(Color.BLACK); // Black text for visibility
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(124, 183, 205), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 50)); // Consistent size
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
