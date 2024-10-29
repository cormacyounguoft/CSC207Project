package view;

import interface_adapter.search_result.SearchResultController;
import interface_adapter.search_result.SearchResultState;
import interface_adapter.search_result.SearchResultViewModel;

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
 * The View for the Search Result Use Case.
 */
public class SearchResultView extends JPanel implements PropertyChangeListener{
    private final String viewName = "search result";

    private final SearchResultViewModel searchResultViewModel;

    private SearchResultController searchResultController;

    final JLabel movie;

    public SearchResultView(SearchResultViewModel searchResultViewModel) {
        this.searchResultViewModel = searchResultViewModel;

        final JLabel title = new JLabel(SearchResultViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final SearchResultState currentState = searchResultViewModel.getState();

        movie = new JLabel(String.valueOf(currentState.getResult()));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(movie);
    }

    public String getViewName() {
        return viewName;
    }

    public void setSearchResultController(SearchResultController controller) {
        this.searchResultController = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            SearchResultState state = (SearchResultState) evt.getNewValue();
            String result = state.getResult().toString();
            movie.setText(result);
        }
    }
}
