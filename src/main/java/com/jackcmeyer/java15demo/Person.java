package com.jackcmeyer.java15demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person {

    public Person(String name, String occupation) {
        this.name = name;
        this.occupation = occupation;
    }

    public Person() {
        // jpa
    }

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String occupation;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }
}
