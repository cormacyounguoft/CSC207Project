package view;

import interface_adapter.logged_in_search.LoggedInSearchController;
import interface_adapter.logged_in_search.LoggedInSearchState;
import interface_adapter.logged_in_search.LoggedInSearchViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for the Logged In Search Use Case.
 */
public class LoggedInSearchView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "logged in search";
    private final LoggedInSearchViewModel loggedInSearchViewModel;
    public LoggedInSearchController loggedInSearchController;

    private final JTextField searchQueryInputField = new JTextField(15);
    private final JLabel searchQueryErrorField = new JLabel();
    final JLabel username;

    private final JButton search;
    private final JButton cancel;

    public LoggedInSearchView(LoggedInSearchViewModel loggedInSearchViewModel) {
        this.loggedInSearchViewModel = loggedInSearchViewModel;
        this.loggedInSearchViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(loggedInSearchViewModel.TITLE_LABEL);
        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel searchBox = new LabelTextPanel(
                new JLabel(loggedInSearchViewModel.SEARCH_LABEL), searchQueryInputField);

        final JPanel buttons = new JPanel();
        search = new JButton(loggedInSearchViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        cancel = new JButton(loggedInSearchViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            final LoggedInSearchState currentState = loggedInSearchViewModel.getState();
                            loggedInSearchController.execute(currentState.getSearchQuery(), currentState.getUsername());
                        }
                    }
                }
        );

        searchQueryInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoggedInSearchState currentState = loggedInSearchViewModel.getState();
                currentState.setSearchQuery(searchQueryInputField.getText());
                loggedInSearchViewModel.setState(currentState);
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

        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(cancel)) {
                            final LoggedInSearchState currentState = loggedInSearchViewModel.getState();
                            loggedInSearchController.switchToLoggedInView(currentState.getUsername());
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(username);
        this.add(searchBox);
        this.add(buttons);
        this.add(searchQueryErrorField);
    }

    public String getViewName() {
        return viewName;
    }

    public void setLoggedInSearchController(LoggedInSearchController loggedInSearchController) {
        this.loggedInSearchController = loggedInSearchController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInSearchState currentState = (LoggedInSearchState) evt.getNewValue();
            username.setText("username: " + currentState.getUsername());
        }

        final LoggedInSearchState state = (LoggedInSearchState) evt.getNewValue();
        if (state.getSearchError() != null) {
            JOptionPane.showMessageDialog(this, state.getSearchError());
            state.setSearchError(null);
        }
    }
}
