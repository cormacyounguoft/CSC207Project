package use_case.get_rated_list;

import entity.UserRating;

public interface GetRatedListDataAccessInterface {
    UserRating getUserRating(String username);
}
