package com.mrman.RickandmortyApiInt.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String url;

    public Location() {
    }

    public Location(String name, String url) {
        this.name = name;
        this.url = url;
    }

}
