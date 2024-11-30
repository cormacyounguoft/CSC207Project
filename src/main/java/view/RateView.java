package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import interface_adapter.rate.RateController;
import interface_adapter.rate.RateState;
import interface_adapter.rate.RateViewModel;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;

/**
 * View for the Rate Use Case.
 */
public class RateView extends JPanel implements PropertyChangeListener {
    private static final int RATE_MAIN_BORDER_LAYOUT = 10;
    private static final int RATE_LABEL_FIELD_WIDTH = 150;
    private static final int RATE_LABEL_FIELD_HEIGHT = 30;

    private final String viewName = "rate";
    private final RateViewModel rateViewModel;

    private RateController rateController;
    private ToLoggedInViewController toLoggedInViewController;

    private JButton rateButton;
    private JButton cancelButton;

    private final JTextField rateInputField = new JTextField(15);
    private final JLabel usernameLabel = new JLabel();
    private final JLabel movieLabel = new JLabel();

    public RateView(RateViewModel rateViewModel) {
        this.rateViewModel = rateViewModel;
        this.rateViewModel.addPropertyChangeListener(this);

        setupMainLayout();
        setupTopPanel();
        setupButtonsPanel();
    }

    private void setupMainLayout() {
        this.setLayout(new BorderLayout(RATE_MAIN_BORDER_LAYOUT, RATE_MAIN_BORDER_LAYOUT));
        this.setBackground(new Color(Constants.COLOUR_R, Constants.COLOUR_G, Constants.COLOUR_B));
        this.setBorder(new EmptyBorder(RATE_MAIN_BORDER_LAYOUT, RATE_MAIN_BORDER_LAYOUT,
                RATE_MAIN_BORDER_LAYOUT, RATE_MAIN_BORDER_LAYOUT));
    }

    private void setupTopPanel() {
        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);

        final JLabel title = new JLabel(rateViewModel.TITLE, SwingConstants.CENTER);
        styleTitleLabel(title);
        topPanel.add(title);

        styleInfoLabel(usernameLabel);
        topPanel.add(usernameLabel);

        styleInfoLabel(movieLabel);
        topPanel.add(movieLabel);

        topPanel.add(createLabeledField("Rate (0-5)", rateInputField));
        this.add(topPanel, BorderLayout.NORTH);
    }

    private void styleTitleLabel(JLabel label) {
        label.setFont(new Font(Constants.FONT_TYPE, Font.BOLD, Constants.FONT_LARGEST));
        label.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void styleInfoLabel(JLabel label) {
        label.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        label.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void setupButtonsPanel() {
        final JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        buttonsPanel.setOpaque(false);

        rateButton = createStyledButton(rateViewModel.RATE_BUTTON);
        cancelButton = createStyledButton(rateViewModel.CANCEL_BUTTON);

        rateButton.addActionListener(evt -> handleRateAction());
        cancelButton.addActionListener(evt -> handleCancelAction());

        buttonsPanel.add(rateButton);
        buttonsPanel.add(cancelButton);

        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    private JPanel createLabeledField(String labelText, JTextField inputField) {
        final JPanel panel = new JPanel(new BorderLayout(Constants.LABEL_BORDER_LAYOUT, Constants.LABEL_BORDER_LAYOUT));
        panel.setOpaque(false);

        final JLabel label = new JLabel(labelText, SwingConstants.LEFT);
        label.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        label.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));

        inputField.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        inputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(Constants.LINE_BORDER_R, Constants.LINE_BORDER_G,
                        Constants.LINE_BORDER_B), 1),
                BorderFactory.createEmptyBorder(Constants.LABEL_BORDER_LAYOUT, Constants.LABEL_BORDER_LAYOUT,
                        Constants.LABEL_BORDER_LAYOUT, Constants.LABEL_BORDER_LAYOUT)
        ));
        inputField.setPreferredSize(new Dimension(RATE_LABEL_FIELD_WIDTH, RATE_LABEL_FIELD_HEIGHT));

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
        button.setBorder(BorderFactory.createLineBorder(new Color(Constants.BORDER_COLOUR_R,
                Constants.BORDER_COLOUR_G, Constants.BORDER_COLOUR_B), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT));
        return button;
    }

    private void handleRateAction() {
        final RateState currentState = rateViewModel.getState();
        try {
            final int rating = Integer.parseInt(rateInputField.getText());
            rateController.execute(currentState.getUsername(), currentState.getTitle(), rating);

            if (currentState.getRateError() != null) {
                JOptionPane.showMessageDialog(this, currentState.getRateError());
            }
            else {
                JOptionPane.showMessageDialog(this, "The rating of "
                        + rating + " out of 5 for \"" + currentState.getTitle() + "\" has been saved.");
            }
        }
        catch (NumberFormatException event) {
            JOptionPane.showMessageDialog(this, "The rating must be an integer between 0 and 5.");
        }
        rateInputField.setText("");
    }

    private void handleCancelAction() {
        final RateState currentState = rateViewModel.getState();
        toLoggedInViewController.toLoggedInView(currentState.getUsername());
        rateInputField.setText("");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final RateState state = (RateState) evt.getNewValue();
        usernameLabel.setText("Username: " + state.getUsername());
        movieLabel.setText("Movie: " + state.getTitle());
    }

    public String getViewName() {
        return viewName;
    }

    public void setRateController(RateController rateController) {
        this.rateController = rateController;
    }

    public void setToLoggedInViewController(ToLoggedInViewController toLoggedInViewController) {
        this.toLoggedInViewController = toLoggedInViewController;
    }
}

