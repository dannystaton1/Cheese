package org.launchcode.cheesemvc.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity // Maps the class to the database
public class Category {
    // Relationships
    @OneToMany // There can be many types of cheeses, but can only have one category
    @JoinColumn(name="category_id") // We are telling Hibernate that we are joining the columns on the category id
    private List<Cheese> cheeses = new ArrayList<>();

    // Fields

    // This is the key in the database and it will auto increment
    @Id
    @GeneratedValue
    private int id;

    // Basic Validation
    @NotNull
    @Size(min = 3,max = 15)
    private String name;

    // Constructors

    public Category(String name) {
        this.name = name;
    }

    // Constructor required for Spring Boot
    // This is the default constructor
    public Category() { }

    // Setters and Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }

}
