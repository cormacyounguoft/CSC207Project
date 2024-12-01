package use_case;

import entity.Movie;
import entity.MovieList;
import entity.User;
import entity.UserRating;
import use_case.add_to_watched_list.AddToWatchedListDataAccessInterface;
import use_case.add_to_watchlist.AddToWatchlistDataAccessInterface;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.dashboard.DashboardDataAccessInterface;
import use_case.get_rated_list.GetRatedListDataAccessInterface;
import use_case.get_watched_list.GetWatchedListDataAccessInterface;
import use_case.get_watchlist.GetWatchlistDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.rate.RateUserDataAccessInterface;
import use_case.rated_list.RatedListDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.watched_list_remove.WatchedListRemoveDataAccessInterface;
import use_case.watchlist_remove.WatchlistRemoveDataAccessInterface;
import use_case.export_watchlist.ExportWatchlistDataAccessInterface;
import use_case.export_watchedlist.ExportWatchedListDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockDataAccessObject implements
        SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        DashboardDataAccessInterface,
        LogoutUserDataAccessInterface,
        AddToWatchlistDataAccessInterface,
        AddToWatchedListDataAccessInterface,
        RateUserDataAccessInterface,
        GetWatchlistDataAccessInterface,
        GetWatchedListDataAccessInterface,
        GetRatedListDataAccessInterface,
        SearchDataAccessInterface,
        WatchedListRemoveDataAccessInterface,
        RatedListDataAccessInterface,
        WatchlistRemoveDataAccessInterface,
        ExportWatchlistDataAccessInterface,
        ExportWatchedListDataAccessInterface
{

    private final Map<String, User> users = new HashMap<>();

    private String currentUsername;

    @Override
    public Movie search(String title) throws IOException {
        Movie result = new Movie();
        if (title.equalsIgnoreCase("movie")) {
            result.setTitle("title");
            result.setReleaseDate("date");
            result.setDescription("description");
            result.setRottenTomatoes(0);
            result.setRuntime(0);
            result.setGenre(List.of("genre"));
            result.setActors(List.of("actor"));
            result.setDirector(List.of("director"));
            result.setPosterLink("url");
        }
        else if (title.equalsIgnoreCase("blank")) {
            result.setTitle("title");
            result.setReleaseDate("");
            result.setDescription("");
            result.setRottenTomatoes(-1);
            result.setRuntime(-1);
            result.setGenre(new ArrayList<String>());
            result.setActors(new ArrayList<String>());
            result.setDirector(new ArrayList<String>());
            result.setPosterLink("");
        }
        else {
            throw new IOException("Movie not found!");
        }
        return result;
    }

    @Override
    public void saveToWatchedList(String username, Movie movie) {
        this.get(username).getWatchedList().addMovie(movie);
    }

    @Override
    public void saveToWatchlist(String username, Movie movie) {
        this.get(username).getWatchList().addMovie(movie);
    }

    @Override
    public void changePassword(String username, String password) {
        User user = this.get(username);
        user.setUserPassword(password);
    }
    @Override
    public void removeFromWatchlist(String username, String movieTitle) {
        MovieList movieList = this.get(username).getWatchList();
        Movie movie = movieList.findMovieByTitle(movieTitle);
        this.get(username).getWatchList().removeMovie(movie);
    }

    @Override
    public Map<String, List<String>> getUserRating(String username) {
        User user = this.get(username);
        Map<String, Integer> ratings = user.getUserRatings().getMovieToRating();
        MovieList movieList = user.getWatchedList();

        Map<String, List<String>> result = new HashMap<>();
        ratings.forEach((title, rating) -> {
            String poster = movieList.getPoster(title);
            result.put(title, Arrays.asList(String.valueOf(rating), poster));
        });
        return result;
    }

    @Override
    public MovieList getWatchedList(String username) {
        return this.get(username).getWatchedList();
    }

    @Override
    public MovieList getWatchlist(String username) {
        return this.get(username).getWatchList();
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    @Override
    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }

    @Override
    public void saveUserRating(String username, String title, int rating) {
        MovieList list = this.get(username).getWatchedList();
        Movie movie = list.findMovieByTitle(title);
        this.get(username).getUserRatings().addRating(movie, rating);
    }

    @Override
    public boolean existsByName(String username) {
        return users.containsKey(username);
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public void removeFromWatchedlist(String username, String title) {
        MovieList list = this.get(username).getWatchedList();
        Movie movie = list.findMovieByTitle(title);
        this.get(username).getWatchedList().removeMovie(movie);
        this.removeUserRating(username, title);
    }

    @Override
    public void removeUserRating(String username, String title) {
        final UserRating userRating = this.get(username).getUserRatings();
        userRating.remove(title);

    }

    @Override
    public MovieList getWatchedMovies(String username) {
        return this.get(username).getWatchedList();
    }

    @Override
    public UserRating getUserRatings(String username) {
        return this.get(username).getUserRatings();
    }
}
