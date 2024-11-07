package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import entity.Movie;
import interface_adapter.search_result.SearchResultController;
import interface_adapter.search_result.SearchResultState;
import interface_adapter.search_result.SearchResultViewModel;

/**
 * The View for the Search Result Use Case.
 */
public class SearchResultView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "search result";
    private final SearchResultViewModel searchResultViewModel;

    private final JButton toHome;
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

    private SearchResultController searchResultController;

    public SearchResultView(SearchResultViewModel searchResultViewModel) {
        this.searchResultViewModel = searchResultViewModel;
        this.searchResultViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(SearchResultViewModel.TITLE_LABEL);
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
        toHome = new JButton(SearchResultViewModel.TO_HOME_BUTTON_LABEL);
        buttons.add(toHome);

        toHome.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        searchResultController.switchToHomeView();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(movie);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final SearchResultState state = (SearchResultState) evt.getNewValue();
            final Movie movie = state.getResult();

            setMovieTitle(movie);
            setMovieReleaseDate(movie);
            setMovieDescription(movie);
            setMovieRottenTomatoes(movie);
            setMovieRuntime(movie);
            setMovieGenre(movie);
            setMovieActors(movie);
            setMovieDirector(movie);
            setMoviePoster(movie);
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setSearchResultController(SearchResultController controller) {
        this.searchResultController = controller;
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
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
