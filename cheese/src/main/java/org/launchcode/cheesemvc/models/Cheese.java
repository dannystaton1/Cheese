package org.launchcode.cheesemvc.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity // Tells spring boot I want to store this in a database and makes the connection
// Each one of the fields should be store in a table in this class
public class Cheese {

    // This is the primary key and it needs to be unique in the database
    // Doesn't need setter because it shouldn't be changed outside of this class
    @Id
    @GeneratedValue
    private int id;

    @NotNull // Built in Java validation not null when we are validating
    @Size(min=3,max =15) // This field has to be at least 3 char but no more then 15 char
    private String name; // Validate this field

    @NotNull
    @Size(min=1,message = "This field may not be empty")
    private String description; // Validate this field

    // Hibernate will create a column under the category ID and link the two data bases with each other
    @ManyToOne
    private Category category;

    @ManyToMany(mappedBy = "cheeses")
    private List<Menu> menus;

    public Cheese(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // This is the default constructor
    public Cheese() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}