package dev.umang.bookmyshowaug2024.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
public class Movie extends BaseModel {
    private String name;

    @ManyToMany
    private List<Actor> actors;
}

/*
1       M
Movie Actor = > M : M
M        M
 */