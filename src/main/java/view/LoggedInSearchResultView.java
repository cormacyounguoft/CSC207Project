package view;

import entity.Movie;
import interface_adapter.add_to_watched_list.AddToWatchedListController;
import interface_adapter.add_to_watchlist.AddToWatchlistController;
import interface_adapter.logged_in_search_result.LoggedInSearchResultController;
import interface_adapter.logged_in_search_result.LoggedInSearchResultState;
import interface_adapter.logged_in_search_result.LoggedInSearchResultViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;

public class LoggedInSearchResultView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "logged in search result";

    private final LoggedInSearchResultViewModel loggedInSearchResultViewModel;
    private LoggedInSearchResultController loggedInSearchResultController;
    private AddToWatchlistController addToWatchlistController;
    private AddToWatchedListController addToWatchedListController;

    private final JButton addToWatchlist;
    private final JButton addToWatchedList;
    private final JButton toRate;
    private final JButton cancel;

    private final JPanel movie;
    private final JLabel movieTitle;
    private final JLabel movieReleaseDate;
    private final JLabel movieDescription;
    private final JLabel movieRottenTomatoes;
    private final JLabel movieRuntime;
    private final JLabel movieGenre;
    private final JLabel movieActors;
    private final JLabel movieDirector;
    private final JLabel moviePoster;

    private final JLabel username;

    public LoggedInSearchResultView(LoggedInSearchResultViewModel loggedInSearchResultViewModel) {
        this.loggedInSearchResultViewModel = loggedInSearchResultViewModel;
        this.loggedInSearchResultViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(loggedInSearchResultViewModel.TITLE_LABEL);
        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        movie = new JPanel();
        movieTitle = new JLabel();
        movieReleaseDate = new JLabel();
        movieDescription = new JLabel();
        movieRottenTomatoes = new JLabel();
        movieRuntime = new JLabel();
        movieGenre = new JLabel();
        movieActors = new JLabel();
        movieDirector = new JLabel();
        moviePoster = new JLabel();

        movie.add(movieTitle);
        movie.add(movieReleaseDate);
        movie.add(movieDescription);
        movie.add(movieRottenTomatoes);
        movie.add(movieRuntime);
        movie.add(movieGenre);
        movie.add(movieActors);
        movie.add(movieDirector);
        movie.add(moviePoster);

        movie.setLayout(new BoxLayout(movie, BoxLayout.Y_AXIS));

        final JPanel buttons = new JPanel();
        addToWatchlist = new JButton(loggedInSearchResultViewModel.ADD_TO_WATCHLIST_BUTTON_LABEL);
        buttons.add(addToWatchlist);
        addToWatchedList = new JButton(loggedInSearchResultViewModel.ADD_TO_WATCHED_LIST_BUTTON_LABEL);
        buttons.add(addToWatchedList);
        toRate = new JButton(loggedInSearchResultViewModel.RATE_BUTTON_LABEL);
        buttons.add(toRate);
        cancel = new JButton(loggedInSearchResultViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        addToWatchlist.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final LoggedInSearchResultState currentState = loggedInSearchResultViewModel.getState();
                        addToWatchlistController.execute(currentState.getUsername(), currentState.getMovie());
                        JOptionPane.showMessageDialog(null, "\"" +
                                currentState.getMovie().getTitle() + "\" has been added to your watchlist.");
                    }
                }
        );

        addToWatchedList.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final LoggedInSearchResultState currentState = loggedInSearchResultViewModel.getState();
                        addToWatchedListController.execute(currentState.getUsername(), currentState.getMovie());
                        JOptionPane.showMessageDialog(null, "\"" +
                                currentState.getMovie().getTitle() + "\" has been added to your watched list.");
                    }
                }
        );

        toRate.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final LoggedInSearchResultState currentState = loggedInSearchResultViewModel.getState();
                        loggedInSearchResultController.switchToRateView(currentState.getUsername(), currentState.getMovie());
                    }
                }
        );

        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final LoggedInSearchResultState currentState = loggedInSearchResultViewModel.getState();
                        loggedInSearchResultController.switchToLoggedInView(currentState.getUsername());
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(username);
        this.add(movie);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInSearchResultState state = (LoggedInSearchResultState) evt.getNewValue();
            final Movie movie = state.getMovie();

            setMovieTitle(movie);
            setMovieReleaseDate(movie);
            setMovieDescription(movie);
            setMovieRottenTomatoes(movie);
            setMovieRuntime(movie);
            setMovieGenre(movie);
            setMovieActors(movie);
            setMovieDirector(movie);
            setMoviePoster(movie);

            username.setText("username: " + state.getUsername());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setLoggedInSearchResultController(LoggedInSearchResultController loggedInSearchResultController) {
        this.loggedInSearchResultController = loggedInSearchResultController;
    }

    public void setAddToWatchlistController(AddToWatchlistController addToWatchlistController) {
        this.addToWatchlistController = addToWatchlistController;
    }

    public void setAddToWatchedListController(AddToWatchedListController addToWatchedListController) {
        this.addToWatchedListController = addToWatchedListController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void setMovieTitle(Movie movie) {
        movieTitle.setText("Title: " + movie.getTitle());
    }

    public void setMovieReleaseDate(Movie movie) {
        if (movie.getReleaseDate().isEmpty()) {
            movieReleaseDate.setText("Release date not available.");
        }
        else {
            movieReleaseDate.setText("Release Date: " + movie.getReleaseDate());
        }
    }

    public void setMovieDescription(Movie movie) {
        if (movie.getDescription().isEmpty()) {
            movieDescription.setText("Description not available.");
        }
        else {
            movieDescription.setText("Description: " + movie.getDescription());
        }
    }

    public void setMovieRottenTomatoes(Movie movie) {
        if (movie.getRottenTomatoes() == -1) {
            movieRottenTomatoes.setText("Rotten Tomatoes not available.");
        }
        else {
            movieRottenTomatoes.setText("Rotten Tomatoes: " + String.valueOf(movie.getRottenTomatoes()));
        }
    }

    public void setMovieGenre(Movie movie) {
        if (movie.getGenre().isEmpty()) {
            movieGenre.setText("Genres not available.");
        }
        else {
            movieGenre.setText("Genres: " + movie.getGenre().toString());
        }
    }

    public void setMovieActors(Movie movie) {
        if (movie.getActors().isEmpty()) {
            movieActors.setText("Actors not available.");
        }
        else {
            movieActors.setText("Actors: " + movie.getActors().toString());
        }
    }

    public void setMovieDirector(Movie movie) {
        if (movie.getDirector().isEmpty()) {
            movieDirector.setText("Directors not available.");
        }
        else {
            movieDirector.setText("Directors: " + movie.getDirector().toString());
        }
    }

    public void setMovieRuntime(Movie movie) {
        if (movie.getRuntime() == -1) {
            movieRuntime.setText("Runtime not available.");
        }
        else {
            movieRuntime.setText("Runtime: " + String.valueOf(movie.getRuntime()));
        }
    }

    public void setMoviePoster(Movie movie) {
        if (movie.getPosterLink().isEmpty()) {
            moviePoster.setText("Poster not available.");
        }
        else {
            try {
                URL url = new URL(movie.getPosterLink());
                BufferedImage image = ImageIO.read(url);
                moviePoster.setIcon(new ImageIcon(image));

            } catch (IOException e) {
                System.out.println("Poster not available");
            }
        }
    }
}
