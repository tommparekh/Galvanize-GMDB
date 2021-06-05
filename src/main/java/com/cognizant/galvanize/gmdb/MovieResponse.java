package com.cognizant.galvanize.gmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_EMPTY)
//@Data
//@NoArgsConstructor
//@EqualsAndHashCode
@Data
@Getter
@Setter
public class MovieResponse {
    public static final String MESSAGE_EMPTY = "no movies found";
    public static final String MESSAGE_MULTIPLE_VALUES_FOUND = "Request Not Successfull : Multiple Values Found";
    public static final String MESSAGE_RATING_REQUIRED = "Message Rating is Required";
    @JsonProperty
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @JsonProperty
    List<Movie> movieList;
}
