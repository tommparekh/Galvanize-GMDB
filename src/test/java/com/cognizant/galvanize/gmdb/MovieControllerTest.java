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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void getMoviesList() throws Exception {
        RequestBuilder requestBuiler = get("/movies")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuiler).andExpect(status().isOk())
                .andExpect(content().string("{\"message\":\"no movies found\"}"));


    }

}
