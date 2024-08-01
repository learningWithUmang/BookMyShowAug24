package dev.umang.bookmyshowaug2024.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Theatre extends BaseModel{
    private String name;

    @OneToMany
    private List<Screen> screens;

    @ManyToOne
    private City city;
}

/*
City Theatre
1     M
1     1 => 1 : M
1       M
Theatre Screen
M         1
 */
