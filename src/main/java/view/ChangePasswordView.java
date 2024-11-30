package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;

/**
 * The view for the Change Password Use Case.
 */
public class ChangePasswordView extends JPanel {

    private final String viewName = "change password";
    private final ChangePasswordViewModel changePasswordViewModel;

    private final JLabel passwordErrorField = new JLabel();
    private ChangePasswordController changePasswordController;
    private LogoutController logoutController;
    private ToLoggedInViewController toLoggedInViewController;

    private final JLabel usernameLabel = new JLabel();
    private final JTextField passwordInputField = new JTextField(15);

    private final JButton changePassword = createStyledButton("Change Password");
    private final JButton logOut = createStyledButton("Log Out");
    private final JButton cancel = createStyledButton("Cancel");

    public ChangePasswordView(ChangePasswordViewModel changePasswordViewModel) {
        this.changePasswordViewModel = changePasswordViewModel;
        configureViewModel();
        setupLayout();
        setupInputPanel();
        setupButtonsPanel();
        addDocumentListeners();
        addActionListeners();
    }

    private void configureViewModel() {
        this.changePasswordViewModel.addPropertyChangeListener(evt -> {
            final ChangePasswordState state = (ChangePasswordState) evt.getNewValue();
            usernameLabel.setText(state.getUsername());
            passwordErrorField.setText(state.getPasswordError());
        });
    }

    private void setupLayout() {
        this.setLayout(new BorderLayout(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));
        this.setBackground(new Color(Constants.COLOUR_R, Constants.COLOUR_G, Constants.COLOUR_B));
        this.setBorder(new EmptyBorder(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT,
                Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));

        final JLabel title = createTitleLabel();
        this.add(title, BorderLayout.NORTH);
    }

    private JLabel createTitleLabel() {
        final JLabel title = new JLabel("Change Password Screen", SwingConstants.CENTER);
        title.setFont(new Font(Constants.FONT_TYPE, Font.BOLD, Constants.FONT_LARGEST));
        title.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
        return title;
    }

    private void setupInputPanel() {
        final JPanel inputPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        inputPanel.setOpaque(false);

        final JPanel usernamePanel = createUsernamePanel();
        inputPanel.add(usernamePanel);
        inputPanel.add(createLabeledField("New Password", passwordInputField));
        passwordErrorField.setFont(new Font(Constants.FONT_TYPE, Font.ITALIC, Constants.FONT_SMALLEST));
        passwordErrorField.setForeground(Color.RED);
        inputPanel.add(passwordErrorField);

        this.add(inputPanel, BorderLayout.CENTER);
    }

    private JPanel createUsernamePanel() {
        final JPanel usernamePanel = new JPanel();
        usernamePanel.setOpaque(false);
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.Y_AXIS));

        final JLabel usernamePrompt = new JLabel("Currently logged in as: ");
        usernamePrompt.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        usernamePrompt.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G,
                Constants.FONT_COLOUR_B));
        usernamePrompt.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernameLabel.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        usernameLabel.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G,
                Constants.FONT_COLOUR_B));
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernamePanel.add(usernamePrompt);
        usernamePanel.add(usernameLabel);
        return usernamePanel;
    }

    private void setupButtonsPanel() {
        final JPanel buttonsPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        buttonsPanel.setOpaque(false);

        buttonsPanel.add(logOut);
        buttonsPanel.add(changePassword);
        buttonsPanel.add(cancel);

        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void addDocumentListeners() {
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
    }

    private void addActionListeners() {
        changePassword.addActionListener(evt -> handleChangePassword());
        logOut.addActionListener(evt -> handleLogOut());
        cancel.addActionListener(evt -> handleCancel());
    }

    private void handleChangePassword() {
        final ChangePasswordState currentState = changePasswordViewModel.getState();
        changePasswordController.execute(currentState.getUsername(), currentState.getPassword());
        if (currentState.getPasswordError() == null) {
            JOptionPane.showMessageDialog(null, "The password has been changed.");
        }
        passwordInputField.setText(null);
    }

    private void handleLogOut() {
        final ChangePasswordState currentState = changePasswordViewModel.getState();
        logoutController.execute(currentState.getUsername());
    }

    private void handleCancel() {
        final ChangePasswordState currentState = changePasswordViewModel.getState();
        toLoggedInViewController.toLoggedInView(currentState.getUsername());
        passwordInputField.setText(null);
    }

    private JPanel createLabeledField(String labelText, JTextField inputField) {
        final JPanel panel = new JPanel(new BorderLayout(Constants.LABEL_BORDER_LAYOUT, Constants.LABEL_BORDER_LAYOUT));
        panel.setOpaque(false);

        final JLabel label = new JLabel(labelText);
        label.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        label.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));

        inputField.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        inputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(Constants.LINE_BORDER_R, Constants.LINE_BORDER_G,
                        Constants.LINE_BORDER_B), Constants.LINE_BORDER_THICKNESS),
                BorderFactory.createEmptyBorder(Constants.LABEL_BORDER_LAYOUT, Constants.LABEL_BORDER_LAYOUT,
                        Constants.LABEL_BORDER_LAYOUT, Constants.LABEL_BORDER_LAYOUT)
        ));

        panel.add(label, BorderLayout.NORTH);
        panel.add(inputField, BorderLayout.CENTER);
        return panel;
    }

    private JButton createStyledButton(String text) {
        final JButton button = new JButton(text);
        button.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_LARGER));
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
