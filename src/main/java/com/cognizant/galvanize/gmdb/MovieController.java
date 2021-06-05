package com.cognizant.galvanize.gmdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/movies")
    public MovieResponse getMovies(){
        List<Movie> movieList = new ArrayList<>();
        movieRepository.findAll().forEach(movieList::add);
        return convertToMovieResponse(movieList);
    }

    private MovieResponse convertToMovieResponse(List<Movie> movieList) {
        //List<MovieResponse> movieResponses = new ArrayList<>();
        MovieResponse movieResponse = new MovieResponse();
        if (movieList == null|| movieList.size()<=0)
        {
            movieResponse.setMessage(MovieResponse.MESSAGE_EMPTY);
             }
        else
        {
            movieResponse.setMovieList(movieList);
        }
        return movieResponse;
    }

    @PostMapping("/movie")
    public MovieResponse post
}
