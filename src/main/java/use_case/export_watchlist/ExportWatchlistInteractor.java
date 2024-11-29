package use_case.export_watchlist;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;

import entity.Movie;
import entity.MovieList;

public class ExportWatchlistInteractor implements ExportWatchlistInputBoundary {
    private final ExportWatchlistOutputBoundary outputBoundary;
    private final ExportWatchlistDataAccessInterface dataAccess;

    public ExportWatchlistInteractor(ExportWatchlistOutputBoundary outputBoundary, ExportWatchlistDataAccessInterface dataAccess) {
        this.outputBoundary = outputBoundary;
        this.dataAccess = dataAccess;
    }

    @Override
    public void exportWatchlist(ExportWatchlistInputData inputData) {
        try {
            MovieList watchlist = dataAccess.getWatchlist(inputData.getUserId());
            List<Movie> movies = watchlist.getMovieList();

            StringBuilder content = new StringBuilder();

            content.append(inputData.getUserId().toUpperCase()).append("'S TO WATCH LIST\n\n");

            // Format each movie
            for (Movie movie : movies) {
                content.append(movie.getTitle())
                        .append(" - ")
                        .append("[")
                        .append(String.join(", ", movie.getGenre())) // Assuming genres are a List<String>
                        .append("] - ")
                        .append(movie.getReleaseDate())
                        .append("\n");
            }

            String filePath = "watchlist_" + inputData.getUserId() + ".txt";
            Files.writeString(Path.of(filePath), content.toString());

            outputBoundary.prepareSuccessView(new ExportWatchlistOutputData(true, filePath, inputData.getUserId()));
        } catch (IOException e) {
            outputBoundary.prepareFailView("Unable to export watchlist");
        }
    }
}
