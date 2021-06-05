package com.cognizant.galvanize.gmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@JsonIgnoreProperties
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Movie {


    @JsonProperty
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonProperty
    private String title;
    @JsonProperty
    private String director;
    @JsonProperty
    private String actors;
    @JsonProperty
    private String release;
    @JsonProperty
    private String description;
    @JsonProperty
    private int rating;

}
