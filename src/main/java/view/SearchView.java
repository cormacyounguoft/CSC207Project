package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;

/**
 * The View for the Search Use Case.
 */
public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "search";
    private final SearchViewModel searchViewModel;

    private final JTextField searchQueryInputField = new JTextField(15);
    private final JLabel searchQueryErrorField = new JLabel();

    private final JButton search;
    private SearchController searchController;

    public SearchView(SearchViewModel searchViewModel) {

        this.searchViewModel = searchViewModel;
        this.searchViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(searchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel searchBox = new LabelTextPanel(
                new JLabel(searchViewModel.SEARCH_LABEL), searchQueryInputField);

        final JPanel buttons = new JPanel();
        search = new JButton(searchViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);

        search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            final SearchState currentState = searchViewModel.getState();

                            searchController.execute(currentState.getSearchQuery());
                        }
                    }
                }
        );

        searchQueryInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SearchState currentState = searchViewModel.getState();
                currentState.setSearchQuery(searchQueryInputField.getText());
                searchViewModel.setState(currentState);
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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(searchBox);
        this.add(searchQueryErrorField);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SearchState state = (SearchState) evt.getNewValue();
        setFields(state);
        searchQueryErrorField.setText(state.getSearchError());
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
}
