package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.to_home_view.ToHomeViewController;

/**
 * The View for the Search Use Case.
 */
public class SearchView extends JPanel {
    private final String viewName = "search";
    private final SearchViewModel searchViewModel;

    private final JTextField searchQueryInputField = new JTextField(15);
    private final JLabel searchQueryErrorField = new JLabel();

    private final JButton search;
    private final JButton cancel;
    private SearchController searchController;
    private ToHomeViewController toHomeViewController;

    public SearchView(SearchViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
        this.searchViewModel.addPropertyChangeListener(evt -> {
            final SearchState state = (SearchState) evt.getNewValue();
            setFields(state);
            searchQueryErrorField.setText(state.getSearchError());
        });

        // Set layout and background
        this.setLayout(new BorderLayout(20, 20));
        this.setBackground(new Color(240, 248, 255)); // Light blue background
        this.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding around the panel

        // Title Label
        final JLabel title = new JLabel("Search Screen", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(0, 51, 102)); // Dark blue
        this.add(title, BorderLayout.NORTH);

        // Input Field Panel
        final JPanel inputPanel = new JPanel(new GridLayout(2, 1, 10, 10)); // Input field and error with spacing
        inputPanel.setOpaque(false);

        inputPanel.add(labelCreator("Search Query", searchQueryInputField, searchQueryErrorField));
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
            final SearchState currentState = searchViewModel.getState();
            searchController.execute(currentState.getSearchQuery());
        });

        cancel.addActionListener(evt -> toHomeViewController.toHomeView());

        // Attach Document Listener
        addDocumentListener(searchQueryInputField, text -> {
            final SearchState currentState = searchViewModel.getState();
            currentState.setSearchQuery(text);
            searchViewModel.setState(currentState);
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

    private void setFields(SearchState state) {
        searchQueryInputField.setText(state.getSearchQuery());
    }

    public String getViewName() {
        return viewName;
    }

    public void setSearchController(SearchController searchController) {
        this.searchController = searchController;
    }

    public void setToHomeViewController(ToHomeViewController toHomeViewController) {
        this.toHomeViewController = toHomeViewController;
    }
}
