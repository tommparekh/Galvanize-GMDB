package com.cognizant.galvanize.gmdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/movies")
    public MovieResponse getMovies() {
        List<Movie> movieList = new ArrayList<>();
        movieRepository.findAll().forEach(movieList::add);
        return convertToMovieResponse(movieList);
    }

    @GetMapping("/movies/title")
    public MovieResponse getMovies(@RequestParam String title) {
        //Movie movie = movieRepository.findByTitle(title);
        Iterable<Movie> moviesFound = movieRepository.findAllByTitle(title);

        List<Movie> movieList = new ArrayList<>();
        moviesFound.forEach(movieList::add);

        return convertToMovieResponse(movieList);
    }

    private MovieResponse convertToMovieResponse(List<Movie> movieList) {
        //List<MovieResponse> movieResponses = new ArrayList<>();
        MovieResponse movieResponse = new MovieResponse();
        if (movieList == null || movieList.size() <= 0) {
            movieResponse.setMessage(MovieResponse.MESSAGE_EMPTY);
        } else {
            for (Movie movie : movieList) {
                if (movie == null) {
                    movieResponse.setMessage(MovieResponse.MESSAGE_EMPTY);
                    break;
                } else {
                    movieResponse.setMovieList(movieList);
                }
            }
        }
        if (movieResponse.getMessage()
                .equalsIgnoreCase(MovieResponse.MESSAGE_EMPTY)) {
            movieResponse.setMovieList(null);
        }
        return movieResponse;
    }

    @PostMapping("/movie")
    public MovieResponse postMovies(@RequestBody Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        return convertToMovieResponse(Arrays.asList(savedMovie));
    }
}
