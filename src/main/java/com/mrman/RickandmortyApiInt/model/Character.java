package com.mrman.RickandmortyApiInt.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Entity
@Table(name="characters")
@Data
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chr_id")
    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "origin_id")
    private Origin origin;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;
    private String image;
    @Lob
    @Column(length = 10000)
    private List<String> episode;
    private String url;
    private String created;

}
