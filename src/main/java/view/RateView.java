package view;

import interface_adapter.rate.RateController;
import interface_adapter.rate.RateState;
import interface_adapter.rate.RateViewModel;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RateView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "rate";

    private final RateViewModel rateViewModel;
    private RateController rateController;
    private ToLoggedInViewController toLoggedInViewController;

    private final JButton rate;
    private final JButton cancel;

    private final JTextField rateInputField = new JTextField(15);
    private final JLabel username;
    private final JLabel movie;

    public RateView(RateViewModel rateViewModel) {
        this.rateViewModel = rateViewModel;
        this.rateViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(rateViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel searchBox = new LabelTextPanel(
                new JLabel(rateViewModel.RATE_LABEL), rateInputField);

        username = new JLabel();
        movie = new JLabel();

        final JPanel buttons = new JPanel();
        rate = new JButton(rateViewModel.RATE_BUTTON_LABEL);
        buttons.add(rate);
        cancel = new JButton(rateViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        rate.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(rate)) {
                            final RateState currentState = rateViewModel.getState();
                            try {
                                int rating = Integer.parseInt(rateInputField.getText());
                                rateController.execute(currentState.getUsername(), currentState.getTitle(), rating);
                                if (currentState.getRateError() != null) {
                                    JOptionPane.showMessageDialog(null, currentState.getRateError());
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "The rating of " +
                                            rating + " out of 5 for \"" + currentState.getTitle() +
                                            "\" has been saved to your account.");
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "The rating must be a number between 0 and 5.");
                            }

                        }
                        rateInputField.setText("");
                    }
                }
        );

        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(cancel)) {
                            final RateState currentState = rateViewModel.getState();
                            toLoggedInViewController.toLoggedInView(currentState.getUsername());
                        }
                        rateInputField.setText("");
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(username);
        this.add(movie);
        this.add(searchBox);
        this.add(buttons);
    }

    public String getViewName() {
        return viewName;
    }

    public void setRateController(RateController rateController) {
        this.rateController = rateController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final RateState state = (RateState) evt.getNewValue();
        final String username = state.getUsername();
        final String movie = state.getTitle();

        this.movie.setText("Movie: " + movie);
        this.username.setText("Username: " + username);
    }

    public void setToLoggedInViewController(ToLoggedInViewController toLoggedInViewController) {
        this.toLoggedInViewController = toLoggedInViewController;
    }
}
