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
        return convertToMovieResponse(movieList, MovieResponse.MESSAGE_EMPTY);
    }

    @GetMapping("/movies/title")
    public MovieResponse getMovies(@RequestParam String title) {
        Iterable<Movie> moviesFound = movieRepository.findAllByTitle(title);

        List<Movie> movieList = new ArrayList<>();
        moviesFound.forEach(movieList::add);

        return convertToMovieResponse(movieList, MovieResponse.MESSAGE_EMPTY);
    }

    private MovieResponse convertToMovieResponse(List<Movie> movieList, String message) {
        //List<MovieResponse> movieResponses = new ArrayList<>();
        MovieResponse movieResponse = new MovieResponse();
        if (movieList == null || movieList.size() <= 0) {
            movieResponse.setMessage(message);
        } else {
            for (Movie movie : movieList) {
                if (movie == null) {
                    movieResponse.setMessage(message);
                    break;
                } else {
                    movieResponse.setMovieList(movieList);
                }
            }
        }
        if (movieResponse.getMessage() != null && movieResponse.getMessage()
                .equalsIgnoreCase(message)) {
            movieResponse.setMovieList(null);
        }
        return movieResponse;
    }

    @PostMapping("/movie")
    public MovieResponse postMovies(@RequestBody Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        return convertToMovieResponse(Arrays.asList(savedMovie), MovieResponse.MESSAGE_EMPTY);
    }

    @PatchMapping("/movie/rating")
    public MovieResponse addRating(@RequestParam String title, @RequestBody Movie movie) {

        Iterable<Movie> moviesFound = movieRepository.findAllByTitle(title);
        List<Movie> movieList = new ArrayList<>();
        moviesFound.forEach(movieList::add);
        Movie movieFound = null;
        if (movieList == null) {
            return convertToMovieResponse(null, MovieResponse.MESSAGE_EMPTY);
        }
        if (movieList.size() > 1) {
            return convertToMovieResponse(null, MovieResponse.MESSAGE_MULTIPLE_VALUES_FOUND);

        }
        movieFound = movieList.get(0);

//        Movie movieFound = movieRepository.findByTitle(title);
        if (movieFound != null) {
            int rating = 0;

            if (movie.getRating() != null) {
                rating = Integer.valueOf(movie.getRating());

            }

            if (movieFound.getRating() != null) {
                rating += Integer.valueOf(movieFound.getRating());
                rating /= 2;
            }


            movieFound.setRating(String.valueOf(rating));


        }
        movieRepository.save(movieFound);

        return convertToMovieResponse(Arrays.asList(movieFound), MovieResponse.MESSAGE_EMPTY);
    }
}
