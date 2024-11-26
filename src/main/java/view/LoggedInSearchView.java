package view;

import interface_adapter.logged_in_search.LoggedInSearchController;
import interface_adapter.logged_in_search.LoggedInSearchState;
import interface_adapter.logged_in_search.LoggedInSearchViewModel;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedInSearchView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in search";
    private final LoggedInSearchViewModel loggedInSearchViewModel;
    private LoggedInSearchController loggedInSearchController;
    private ToLoggedInViewController toLoggedInViewController;

    private final JTextField searchQueryInputField = new JTextField(15);
    private final JLabel searchQueryErrorField = new JLabel();
    private final JLabel username = new JLabel();

    private final JButton search;
    private final JButton cancel;

    public LoggedInSearchView(LoggedInSearchViewModel loggedInSearchViewModel) {
        this.loggedInSearchViewModel = loggedInSearchViewModel;
        this.loggedInSearchViewModel.addPropertyChangeListener(this);

        // Set layout and background
        this.setLayout(new BorderLayout(20, 20));
        this.setBackground(new Color(240, 248, 255)); // Light blue background
        this.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding around the panel

        // Title Section
        final JLabel title = new JLabel("Logged-In Search", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(0, 51, 102)); // Dark blue
        this.add(title, BorderLayout.NORTH);

        // Input Field Panel
        final JPanel inputPanel = new JPanel(new GridLayout(2, 1, 10, 10)); // Input field and error
        inputPanel.setOpaque(false);
        inputPanel.add(labelCreator("Search Query:", searchQueryInputField, searchQueryErrorField));
        this.add(inputPanel, BorderLayout.CENTER);

        // Buttons Panel
        final JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 15, 0)); // Side-by-side buttons
        buttonsPanel.setOpaque(false);

        search = buttonFactory("Search");
        cancel = buttonFactory("Cancel");
        buttonsPanel.add(search);
        buttonsPanel.add(cancel);

        this.add(buttonsPanel, BorderLayout.SOUTH);

        // Add Action Listeners
        search.addActionListener(evt -> {
            final LoggedInSearchState currentState = loggedInSearchViewModel.getState();
            loggedInSearchController.execute(currentState.getSearchQuery(), currentState.getUsername());
        });

        cancel.addActionListener(evt -> {
            final LoggedInSearchState currentState = loggedInSearchViewModel.getState();
            toLoggedInViewController.toLoggedInView(currentState.getUsername());
        });

        // Attach Document Listener
        addDocumentListener(searchQueryInputField, text -> {
            final LoggedInSearchState currentState = loggedInSearchViewModel.getState();
            currentState.setSearchQuery(text);
            loggedInSearchViewModel.setState(currentState);
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
        button.setForeground(Color.BLACK);
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInSearchState state = (LoggedInSearchState) evt.getNewValue();
            username.setText("Username: " + state.getUsername());

            if (state.getSearchError() != null) {
                JOptionPane.showMessageDialog(this, state.getSearchError());
                state.setSearchError(null);
            }
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setLoggedInSearchController(LoggedInSearchController controller) {
        this.loggedInSearchController = controller;
    }

    public void setToLoggedInViewController(ToLoggedInViewController controller) {
        this.toLoggedInViewController = controller;
    }
}
