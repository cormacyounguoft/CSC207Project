package view;

import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The View for the Search Use Case.
 */
public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "search";

    private final SearchViewModel searchViewModel;
    private final JTextField searchQueryInputField = new JTextField(15);
    private SearchController searchController;

    private final JButton search;

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

        addSearchListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(searchBox);
        this.add(buttons);
    }

    private void addSearchListener() {
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
        }
        );
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SearchState state = (SearchState) evt.getNewValue();
        if (state.getSearchError() != null) {
            JOptionPane.showMessageDialog(this, state.getSearchError());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setSearchController(SearchController controller) {
        this.searchController = controller;
    }
}
