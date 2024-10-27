package entity;

/**
 * Factory for creating a user's movie rating.
 */
public class UserRatingFactory {
    UserRating create(Movie movie, User user, int rating) {
        return new UserRating(movie, user, rating);
    }
}
