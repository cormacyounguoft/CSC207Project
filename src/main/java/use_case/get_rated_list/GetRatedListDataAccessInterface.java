package use_case.get_rated_list;

import java.util.List;
import java.util.Map;

public interface GetRatedListDataAccessInterface {
    Map<String, List<String>> getUserRating(String username);
}
