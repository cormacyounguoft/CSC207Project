package use_case.export_watchlist;

import entity.Movie;
import entity.MovieFactory;
import entity.CommonUserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.MockDataAccessObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExportWatchlistInteractorTest {

    MockDataAccessObject dataAccessObject;
    ExportWatchlistInteractor interactor;

    @BeforeEach
    void setUp() {
        dataAccessObject = new MockDataAccessObject();
        dataAccessObject.save(new CommonUserFactory().create("TestUser", "Password123!"));

        MovieFactory movieFactory = new MovieFactory();

        // Placeholder movies
        Movie movie1 = movieFactory.create();
        movie1.setTitle("Inception");
        movie1.setGenre(List.of("Sci-Fi", "Thriller"));
        movie1.setReleaseDate("2010");
        dataAccessObject.saveToWatchlist("TestUser", movie1);

        Movie movie2 = movieFactory.create();
        movie2.setTitle("Interstellar");
        movie2.setGenre(List.of("Sci-Fi", "Drama"));
        movie2.setReleaseDate("2014");
        dataAccessObject.saveToWatchlist("TestUser", movie2);

        interactor = new ExportWatchlistInteractor(
                new ExportWatchlistOutputBoundary() {
                    @Override
                    public void prepareSuccessView(ExportWatchlistOutputData outputData) {
                        assertTrue(outputData.isSuccess());
                        assertEquals("watchlist_TestUser.txt", outputData.getFilePath());
                        assertEquals("TestUser", outputData.getUsername());
                    }

                    @Override
                    public void prepareFailView(String errorMessage) {
                        fail("Test should not fail: " + errorMessage);
                    }
                },
                dataAccessObject
        );
    }

    @Test
    void testExportWatchlistSuccess() {
        // Act
        ExportWatchlistInputData inputData = new ExportWatchlistInputData("TestUser");
        interactor.exportWatchlist(inputData);

        // Assert
        String filePath = "watchlist_TestUser.txt";
        String expectedContent = """
                TESTUSER'S TO WATCH LIST

                Inception - [Sci-Fi, Thriller] - 2010
                Interstellar - [Sci-Fi, Drama] - 2014
                """;

        Path path = Path.of(filePath);
        try {
            // Verify the exported file's content
            String actualContent = Files.readString(path);
            assertEquals(expectedContent, actualContent, "Exported file content should match expected content.");
        } catch (IOException e) {
            fail("Exported file could not be read.");
        } finally {
            // Cleanup: Delete the exported file after the test
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                fail("Failed to delete the exported file after the test.");
            }
        }
    }

    @Test
    void testExportWatchlistFailure_EmptyWatchlist() {
        dataAccessObject.save(new CommonUserFactory().create("EmptyUser", "Password123!"));
        interactor = new ExportWatchlistInteractor(
                new ExportWatchlistOutputBoundary() {
                    @Override
                    public void prepareSuccessView(ExportWatchlistOutputData outputData) {
                        fail("Test should not succeed for an empty watchlist.");
                    }

                    @Override
                    public void prepareFailView(String errorMessage) {
                        assertEquals("The watchlist is empty!", errorMessage);
                    }
                },
                dataAccessObject
        );

        ExportWatchlistInputData inputData = new ExportWatchlistInputData("EmptyUser");
        interactor.exportWatchlist(inputData);
    }

    @Test
    void testExportWatchlistFailure_FileWriteError() {
        ExportWatchlistInteractor interactorWithInvalidPath = new ExportWatchlistInteractor(
                new ExportWatchlistOutputBoundary() {
                    @Override
                    public void prepareSuccessView(ExportWatchlistOutputData outputData) {
                        fail("Test should not succeed if file writing fails.");
                    }

                    @Override
                    public void prepareFailView(String errorMessage) {
                        assertEquals("Unable to export watchlist", errorMessage);
                    }
                },
                dataAccessObject
        );

        final Path path = Path.of("watchlist_TestUser.txt");
        try {
            Files.createFile(path);
            path.toFile().setReadOnly();

            ExportWatchlistInputData inputData = new ExportWatchlistInputData("TestUser");
            interactorWithInvalidPath.exportWatchlist(inputData);

        } catch (IOException e) {
            fail("Setup for file-write error simulation failed.");
        } finally {
            try {
                if (path.toFile().exists()) {
                    path.toFile().setWritable(true);
                    Files.deleteIfExists(path);
                }
            } catch (IOException e) {
                fail("Failed to clean up after the test.");
            }
        }
    }
}
