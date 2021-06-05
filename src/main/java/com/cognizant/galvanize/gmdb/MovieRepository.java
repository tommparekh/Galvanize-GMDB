package com.cognizant.galvanize.gmdb;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie,Integer> {
    public Movie findByTitle(String title);
}
