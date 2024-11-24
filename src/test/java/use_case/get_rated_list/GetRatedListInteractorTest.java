package use_case.get_rated_list;

import data_access.InMemoryUserDataAccessObject;
import data_access.MovieAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;
import use_case.search.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GetRatedListInteractorTest {
    @Test
    void successTest() {
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "Password1!");
        Movie movie = new Movie();
        userRepository.save(user);
        movie.setTitle("Frozen");
        movie.setPosterLink("https://m.media-amazon.com/images/M/MV5BMTQ1MjQwMTE5OF5BMl5BanBnXkFtZTgwNjk3MTcyMDE@._V1_SX300.jpg");
        userRepository.saveToWatchedList("Paul", movie);
        userRepository.saveUserRating("Paul", "Frozen", 5);
        GetRateListInputData inputData = new GetRateListInputData("Paul");
        LoggedOutSearchDataAccessInterface loggedOutSearchDataAccessInterface = new MovieAccessObject();


        GetRateListOutputBoundary presenter = new GetRateListOutputBoundary() {
            @Override
            public void prepareSuccessView(GetRateListOutputData getRateListOutputData) {
                assertEquals(getRateListOutputData.getUsername(), "Paul");
                Map<String, List<String>> rating = new HashMap<>();
                rating.put("Frozen", Arrays.asList("5", "https://m.media-amazon.com/images/M/MV5BMTQ1MjQwMTE5OF5BMl5BanBnXkFtZTgwNjk3MTcyMDE@._V1_SX300.jpg"));
                assertEquals(getRateListOutputData.getUserRating(), rating);
            }
        } ;


        GetRateListInputBoundary interactor = new GetRateListInteractor(userRepository, presenter);


        interactor.execute(inputData);

    }
}
