package use_case.get_watched_list;

import entity.MovieList;

public interface GetWatchedListDataAccessInterface {

    MovieList getWatchedList(String username);
}
