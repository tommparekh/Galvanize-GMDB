package com.cognizant.galvanize.gmdb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    //IS Movie List is Empty
//    Given the GMDB is empty
//    Then I should see no movies
//    When I visit GMDB movies

    @Test
    @Transactional
    @Rollback
    public void testIfNoMoviesThenGetNoMovieFound() throws Exception {
        RequestBuilder requestBuiler = get("/movies")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuiler).andExpect(status().isOk())
                .andExpect(content().string("{\"message\":\"no movies found\"}"));
     }

//
//    Given a new movie has released
//    When I submit this new movie to GMDB movies
//    Then I should see that movie in GMDB movies

    @Test
    @Transactional
    @Rollback
    public void testIfNewMovieAddedThenSeeMovieDetailsInGetMovieOperation() throws Exception {
        String requestBody =
                "{\n" +
                "                \"title\": \"The Avengers\",\n" +
                "                \"director\": \"Joss Whedon\",\n" +
                "                \"actors\": \"Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth\",\n" +
                "                \"release\": \"2012\",\n" +
                "                \"description\": \"Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.\",\n" +
                "                \"rating\": \"\"\n" +
                "}" ;

        RequestBuilder requestBuiler = post("/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        mockMvc.perform(requestBuiler).andExpect(status().isOk())
                .andExpect(jsonPath("$.movieList[0].title", is("The Avengers")));
    }


//    Given the GMDB has a movie
//    When I visit GMDB movies
//    Then I should see that movie in GMDB movies

    @Test
    @Transactional
    @Rollback
    public void testIfMovieIsPresentThenISeeMovie() throws Exception {
       //Arrange
        Movie movie = new Movie();
        movie.setTitle("The Avengers");
        movie.setDirector("Joss Whedon");
        movie.setActors("Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth");
        movie.setRelease("2012");
        movie.setDescription("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.");

        movieRepository.save(movie);

        RequestBuilder requestBuiler = get("/movies")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuiler).andExpect(status().isOk())
                .andExpect(jsonPath("$.movieList[0].title", is("The Avengers")));
    }

}
