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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
        this.setLayout(new BorderLayout(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));
        this.setBackground(new Color(Constants.COLOUR_R, Constants.COLOUR_G, Constants.COLOUR_B));
        this.setBorder(new EmptyBorder(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT,
                Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));

        // Title Label
        final JLabel title = new JLabel("Search Screen", SwingConstants.CENTER);
        title.setFont(new Font(Constants.FONT_TYPE, Font.BOLD, Constants.FONT_LARGEST));
        title.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
        this.add(title, BorderLayout.NORTH);

        // Input Field Panel
        final JPanel inputPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        inputPanel.setOpaque(false);

        inputPanel.add(labelCreator("Search Query", searchQueryInputField, searchQueryErrorField));
        this.add(inputPanel, BorderLayout.CENTER);

        // Buttons Panel
        final JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 15, 0));
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
     * @param labelText the text to display as the label for the input field
     * @param inputField the input field where the user enters text
     * @param errorLabel the label to display error messages related to the input field
     * @return a JPanel containing the label, input field, and error message label
     */
    private JPanel labelCreator(String labelText, JTextField inputField, JLabel errorLabel) {
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

        errorLabel.setFont(new Font(Constants.FONT_TYPE, Font.ITALIC, Constants.FONT_SMALLEST));
        errorLabel.setForeground(Color.RED);

        panel.add(label, BorderLayout.NORTH);
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(errorLabel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Utility method to create a styled button.
     * @param text the text to be displayed in the styled button.
     * @return the styled button that is created.
     */
    private JButton buttonFactory(String text) {
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
     * @param textField the text field to which the document listener is added
     * @param callback the callback to handle updates to the text field's content
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

    /**
     * A callback interface for handling updates to a text field.
     */
    private interface DocumentListenerCallback {
        void update(String text);
    }
}
