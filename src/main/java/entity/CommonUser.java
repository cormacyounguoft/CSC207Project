package entity;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private String password;
    private final MovieList watchList;
    private final MovieList watchedList;
    private final UserRating ratings;

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.watchList = new MovieList();
        this.watchedList = new MovieList();
        this.ratings = new UserRating();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public MovieList getWatchList() {
        return this.watchList;
    }

    @Override
    public MovieList getWatchedList() {
        return this.watchedList;
    }

    @Override
    public UserRating getUserRatings() {
        return this.ratings;
    }

    @Override
    public void setUserPassword(String password) {
        this.password= password;
    }

}
