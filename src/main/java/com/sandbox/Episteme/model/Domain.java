package com.sandbox.Episteme.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"Domains\"")
public class Domain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="domain_id")
    private Long id;

    @Column
    private String name;

    @Column(length=2000)
    public String description;

    @Column
    @Enumerated(EnumType.STRING)
    private DomainCategory category;

    @Column
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "parentDomain")
    //@JoinColumn(name = "domain_id")
    private List<Domain> subDomains = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Domain parentDomain;


    @ManyToOne(fetch = FetchType.LAZY)
    private Dimension dimension;

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
        if (dimension!=null)
            dimension.addDomains(this);
    }
    public Dimension getDimension() {
        return dimension;
    }


    @ManyToMany(mappedBy = "domains")
    private List<Resource> resources = new ArrayList<>();


    public Domain() {
    }
    public Domain(String name, DomainCategory categ, String description) {
        this.name = name;
        this.description=description;
        this.category=categ;
    }

}
