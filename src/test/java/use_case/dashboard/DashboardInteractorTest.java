package use_case.dashboard;

import entity.CommonUserFactory;
import entity.Movie;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.MockDataAccessObject;

import java.util.List;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DashboardInteractorTest {

    private MockDataAccessObject dataAccessObject;

    @BeforeEach
    void setUp() {
        dataAccessObject = new MockDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Username", "Password123!");
        dataAccessObject.save(user);

        Movie movie1 = new Movie();
        movie1.setTitle("Movie1");
        movie1.setRuntime(120);
        movie1.setGenre(List.of("Action"));
        dataAccessObject.saveToWatchedList("Username", movie1);
        dataAccessObject.saveUserRating("Username", "Movie1", 5);

        Movie movie2 = new Movie();
        movie2.setTitle("Movie2");
        movie2.setRuntime(150);
        movie2.setGenre(List.of("Drama"));
        dataAccessObject.saveToWatchedList("Username", movie2);
        dataAccessObject.saveUserRating("Username", "Movie2", 4);

        Movie movie10 = new Movie();
        movie10.setTitle("Movie10");
        movie10.setRuntime(1);
        movie10.setGenre(List.of("Comedy"));
        dataAccessObject.saveToWatchedList("Username", movie10);
        dataAccessObject.saveUserRating("Username", "Movie10", 4);
    }

    @Test
    void successTest() {
        DashboardInputData inputData = new DashboardInputData("Username");

        DashboardOutputBoundary presenter = new DashboardOutputBoundary() {
            @Override
            public void prepareSuccessView(DashboardOutputData outputData) {
                assertEquals("Username", outputData.getUsername());
                assertEquals("4 hours and 31 minutes", outputData.getTotalHoursWatched());
                assertEquals("Movie1", outputData.getFavoriteMovie());
                assertEquals("[Action]", outputData.getFavoriteGenre());
                assertEquals(4.33, outputData.getAverageRating(), 0.01);
                assertEquals("Movie2", outputData.getLongestMovie());
            }
        };

        DashboardInputBoundary interactor = new DashboardInteractor(dataAccessObject, presenter);

        interactor.execute(inputData);
    }

    @Test
    void noMoviesWatchedTest() {
        UserFactory factory = new CommonUserFactory();
        User newUser = factory.create("NewUser", "Password123!");
        dataAccessObject.save(newUser);
        DashboardInputData inputData = new DashboardInputData("NewUser");

        DashboardOutputBoundary presenter = new DashboardOutputBoundary() {
            @Override
            public void prepareSuccessView(DashboardOutputData outputData) {
                assertEquals("NewUser", outputData.getUsername());
                assertEquals("No time watched", outputData.getTotalHoursWatched());
                assertEquals("No favourite movie", outputData.getFavoriteMovie());
                assertEquals("No favourite genre", outputData.getFavoriteGenre());
                assertEquals(0, outputData.getAverageRating());
                assertEquals("No movies watched", outputData.getLongestMovie());
            }
        };

        DashboardInputBoundary interactor = new DashboardInteractor(dataAccessObject, presenter);

        interactor.execute(inputData);
    }

    @Test
    void tiedRatingsTest() {
        dataAccessObject.saveUserRating("Username", "Movie2", 5); // Tie in ratings

        DashboardInputData inputData = new DashboardInputData("Username");

        DashboardOutputBoundary presenter = new DashboardOutputBoundary() {
            @Override
            public void prepareSuccessView(DashboardOutputData outputData) {
                assertEquals("Movie1", outputData.getFavoriteMovie()); // Expected: first movie with highest rating
            }
        };

        DashboardInputBoundary interactor = new DashboardInteractor(dataAccessObject, presenter);

        interactor.execute(inputData);
    }

    @Test
    void extremeValuesTest() {
        Movie movie3 = new Movie();
        movie3.setTitle("Epic Movie");
        movie3.setRuntime(10000); // Extreme runtime
        movie3.setGenre(List.of("Fantasy"));
        dataAccessObject.saveToWatchedList("Username", movie3);
        dataAccessObject.saveUserRating("Username", "Epic Movie", 1);

        DashboardInputData inputData = new DashboardInputData("Username");

        DashboardOutputBoundary presenter = new DashboardOutputBoundary() {
            @Override
            public void prepareSuccessView(DashboardOutputData outputData) {
                assertEquals("Epic Movie", outputData.getLongestMovie());
                assertEquals(3.5, outputData.getAverageRating(), 0.01); // Average considering all ratings
            }
        };

        DashboardInputBoundary interactor = new DashboardInteractor(dataAccessObject, presenter);

        interactor.execute(inputData);
    }

    @Test
    void userWithNoRatingsTest() {
        // Add a new user and movies, but do not add ratings
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("RatinglessUser", "NoRatings123!");
        dataAccessObject.save(user);

        Movie movie1 = new Movie();
        movie1.setTitle("Unrated Movie");
        movie1.setRuntime(90);
        movie1.setGenre(List.of("Comedy"));
        dataAccessObject.saveToWatchedList("RatinglessUser", movie1);

        DashboardInputData inputData = new DashboardInputData("RatinglessUser");

        DashboardOutputBoundary presenter = new DashboardOutputBoundary() {
            @Override
            public void prepareSuccessView(DashboardOutputData outputData) {
                assertEquals("RatinglessUser", outputData.getUsername());
                assertEquals("1 hour and 30 minutes", outputData.getTotalHoursWatched());
                assertEquals("No favourite movie", outputData.getFavoriteMovie());
                assertEquals("No favourite genre", outputData.getFavoriteGenre());
                assertEquals(0, outputData.getAverageRating());
                assertEquals("Unrated Movie", outputData.getLongestMovie());
            }
        };

        DashboardInputBoundary interactor = new DashboardInteractor(dataAccessObject, presenter);
        interactor.execute(inputData);
    }

    @Test
    void oneHourTest() {
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Username1", "Pass123!");
        dataAccessObject.save(user);

        Movie movie = new Movie();
        movie.setTitle("Movie");
        movie.setRuntime(60);
        dataAccessObject.saveToWatchedList("Username1", movie);
        dataAccessObject.saveUserRating("Username1", "Movie", 5);
        DashboardInputData inputData = new DashboardInputData("Username1");

        DashboardOutputBoundary presenter = new DashboardOutputBoundary() {
            @Override
            public void prepareSuccessView(DashboardOutputData outputData) {
                assertEquals("1 hour", outputData.getTotalHoursWatched());
            }
        };

        DashboardInputBoundary interactor = new DashboardInteractor(dataAccessObject, presenter);
        interactor.execute(inputData);
    }

    @Test
    void oneMinuteTest() {
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Username2", "Pass123!");
        dataAccessObject.save(user);

        Movie movie = new Movie();
        movie.setTitle("Movie");
        movie.setRuntime(1);
        dataAccessObject.saveToWatchedList("Username2", movie);
        dataAccessObject.saveUserRating("Username2", "Movie", 5);
        DashboardInputData inputData = new DashboardInputData("Username2");

        DashboardOutputBoundary presenter = new DashboardOutputBoundary() {
            @Override
            public void prepareSuccessView(DashboardOutputData outputData) {
                assertEquals("1 minute", outputData.getTotalHoursWatched());
            }
        };

        DashboardInputBoundary interactor = new DashboardInteractor(dataAccessObject, presenter);
        interactor.execute(inputData);
    }

    @Test
    void oneHourAndOneMinuteTest() {
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Username3", "Pass123!");
        dataAccessObject.save(user);

        Movie movie = new Movie();
        movie.setTitle("Movie");
        movie.setRuntime(61);
        dataAccessObject.saveToWatchedList("Username3", movie);
        dataAccessObject.saveUserRating("Username3", "Movie", 5);
        DashboardInputData inputData = new DashboardInputData("Username3");

        DashboardOutputBoundary presenter = new DashboardOutputBoundary() {
            @Override
            public void prepareSuccessView(DashboardOutputData outputData) {
                assertEquals("1 hour and 1 minute", outputData.getTotalHoursWatched());
            }
        };

        DashboardInputBoundary interactor = new DashboardInteractor(dataAccessObject, presenter);
        interactor.execute(inputData);
    }

    @Test
    void twoHoursAndFiveMinutesTest() {
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Username4", "Pass123!");
        dataAccessObject.save(user);

        Movie movie = new Movie();
        movie.setTitle("Movie");
        movie.setRuntime(125);
        dataAccessObject.saveToWatchedList("Username4", movie);
        dataAccessObject.saveUserRating("Username4", "Movie", 5);
        DashboardInputData inputData = new DashboardInputData("Username4");

        DashboardOutputBoundary presenter = new DashboardOutputBoundary() {
            @Override
            public void prepareSuccessView(DashboardOutputData outputData) {
                assertEquals("2 hours and 5 minutes", outputData.getTotalHoursWatched());
            }
        };

        DashboardInputBoundary interactor = new DashboardInteractor(dataAccessObject, presenter);
        interactor.execute(inputData);
    }

    @Test
    void fortyFiveMinutesTest() {
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Username5", "Pass123!");
        dataAccessObject.save(user);

        Movie movie = new Movie();
        movie.setTitle("Movie");
        movie.setRuntime(45);
        dataAccessObject.saveToWatchedList("Username5", movie);
        dataAccessObject.saveUserRating("Username5", "Movie", 5);
        DashboardInputData inputData = new DashboardInputData("Username5");

        DashboardOutputBoundary presenter = new DashboardOutputBoundary() {
            @Override
            public void prepareSuccessView(DashboardOutputData outputData) {
                assertEquals("45 minutes", outputData.getTotalHoursWatched());
            }
        };

        DashboardInputBoundary interactor = new DashboardInteractor(dataAccessObject, presenter);
        interactor.execute(inputData);
    }

    @Test
    void oneHourAndTwoMinutesTest() {
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Username6", "Pass123!");
        dataAccessObject.save(user);

        Movie movie = new Movie();
        movie.setTitle("Movie");
        movie.setRuntime(62);
        dataAccessObject.saveToWatchedList("Username6", movie);
        dataAccessObject.saveUserRating("Username6", "Movie", 5);
        DashboardInputData inputData = new DashboardInputData("Username6");

        DashboardOutputBoundary presenter = new DashboardOutputBoundary() {
            @Override
            public void prepareSuccessView(DashboardOutputData outputData) {
                assertEquals("1 hour and 2 minutes", outputData.getTotalHoursWatched());
            }
        };

        DashboardInputBoundary interactor = new DashboardInteractor(dataAccessObject, presenter);
        interactor.execute(inputData);
    }

    @Test
    void twoHoursAndOneMinuteTest() {
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Username7", "Pass123!");
        dataAccessObject.save(user);

        Movie movie = new Movie();
        movie.setTitle("Movie");
        movie.setRuntime(121);
        dataAccessObject.saveToWatchedList("Username7", movie);
        dataAccessObject.saveUserRating("Username7", "Movie", 5);
        DashboardInputData inputData = new DashboardInputData("Username7");

        DashboardOutputBoundary presenter = new DashboardOutputBoundary() {
            @Override
            public void prepareSuccessView(DashboardOutputData outputData) {
                assertEquals("2 hours and 1 minute", outputData.getTotalHoursWatched());
            }
        };

        DashboardInputBoundary interactor = new DashboardInteractor(dataAccessObject, presenter);
        interactor.execute(inputData);
    }

    @Test
    void validUserWithMoviesTest() {
        DashboardInputData inputData = new DashboardInputData("Username");

        DashboardOutputBoundary presenter = new DashboardOutputBoundary() {
            @Override
            public void prepareSuccessView(DashboardOutputData outputData) {
                assertEquals("4 hours and 31 minutes", outputData.getTotalHoursWatched());
                assertEquals("Movie1", outputData.getFavoriteMovie());
                assertEquals("[Action]", outputData.getFavoriteGenre());
                assertEquals("Movie2", outputData.getLongestMovie());
            }

            public void prepareFailView(String error) {
                fail("Should not reach fail view for a valid user.");
            }
        };

        DashboardInputBoundary interactor = new DashboardInteractor(dataAccessObject, presenter);

        interactor.execute(inputData);
    }

    @Test
    void userWithEmptyWatchedListTest() {
        UserFactory factory = new CommonUserFactory();
        User emptyUser = factory.create("EmptyUser", "Password123!");
        dataAccessObject.save(emptyUser);

        DashboardInputData inputData = new DashboardInputData("EmptyUser");

        DashboardOutputBoundary presenter = new DashboardOutputBoundary() {
            @Override
            public void prepareSuccessView(DashboardOutputData outputData) {
                assertEquals("No time watched", outputData.getTotalHoursWatched());
                assertEquals("No favourite movie", outputData.getFavoriteMovie());
                assertEquals("No favourite genre", outputData.getFavoriteGenre());
                assertEquals("No movies watched", outputData.getLongestMovie());
            }

            public void prepareFailView(String error) {
                fail("Should not reach fail view for an empty watched list.");
            }
        };

        DashboardInputBoundary interactor = new DashboardInteractor(dataAccessObject, presenter);

        interactor.execute(inputData);
    }
}

