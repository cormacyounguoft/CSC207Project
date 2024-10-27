package entity;

/**
 * The representation of a user's rating of a movie in our program.
 */
public class UserRating {
    private final Movie movie;
    private final User user;
    private final int rating;

    public UserRating(Movie movie, User user, int rating) {
        this.movie = movie;
        this.user = user;
        this.rating = rating;
    }

    public Movie getMovie() {
        return movie;
    }

    public User getUser() {
        return user;
    }

    public int getRating() {
        return rating;
    }
}
