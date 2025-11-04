package com.sandbox.Episteme.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"Resources\"")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="resource_id")
    private Long id;

    @Column
    public String name;

    @Column(length=2000)
    public String description;

    @Column
    @Enumerated(EnumType.STRING)
    private ResourceCategory category;


    @ManyToMany
    @JoinTable(name = "\"Resource_Domains\"",
            joinColumns = @JoinColumn(name = "resource_id"),
            inverseJoinColumns = @JoinColumn(name = "domain_id")
    )
    private List<Domain> domains = new ArrayList<>();


    public Resource() {
    }
    public Resource(String name, String description) {
        this.name = name;
        this.description=description;
    }

}
