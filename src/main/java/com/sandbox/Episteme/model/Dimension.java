package com.sandbox.Episteme.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"Dimensions\"")
public class Dimension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dimension_id")
    private Long id;

    @Column
    public String name;

    @Column(length=2000)
    public String description;

    @Column
    @Enumerated(EnumType.STRING)
    private DimensionCategory category;

    @Column
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "dimension")
    //@JoinColumn(name = "domain_id")
    private List<Domain> domains = new ArrayList<>();

    public void addDomains(Domain dom) {
        this.domains.add(dom);
    }

    public Dimension() {
    }
    public Dimension(String name, DimensionCategory categ,  String description) {
        this.name = name;
        this.description=description;
        this.category=categ;
    }

}
