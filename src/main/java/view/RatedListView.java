package view;

import interface_adapter.ratedList.RatedListController;
import interface_adapter.ratedList.RatedListState;
import interface_adapter.ratedList.RatedListViewModel;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

public class RatedListView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "rated list";
    private final RatedListViewModel ratedListViewModel;
    private RatedListController ratedListController;
    private ToLoggedInViewController toLoggedInViewController;

    private final JButton cancel;

    private final JLabel username;
    private final JPanel ratedList;
    private final JScrollPane scroller;

    public RatedListView(RatedListViewModel ratedListViewModel){
        this.ratedListViewModel = ratedListViewModel;
        this.ratedListViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(ratedListViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        username = new JLabel();
        ratedList = new JPanel();
        scroller = new JScrollPane(ratedList);

        final JPanel buttons = new JPanel();
        cancel = new JButton(ratedListViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final RatedListState currentState = ratedListViewModel.getState();
                        toLoggedInViewController.toLoggedInView(currentState.getUsername());
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(username);
        this.add(scroller);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final RatedListState state = (RatedListState) evt.getNewValue();
            final Map<String, Integer> ratings = state.getRating();
            ratedList.removeAll();

            for (Map.Entry<String, Integer> entry : ratings.entrySet()) {
                String key = entry.getKey();
                JButton rating = new JButton(key + ": " + entry.getValue());
                ratedList.add(rating);
                }

            username.setText("Username: " + state.getUsername());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setRatedListController(RatedListController ratedListController){
        this.ratedListController = ratedListController;
    }

    public void setToLoggedInViewController(ToLoggedInViewController toLoggedInViewController) {
        this.toLoggedInViewController = toLoggedInViewController;
    }
}
