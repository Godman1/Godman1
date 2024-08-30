package com.mrman.RickandmortyApiInt.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="origins")
public class Origin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String url;

    public Origin() {
    }

    public Origin(String name, String url) {
        this.name = name;
        this.url = url;
    }

}
