package org.launchcode.cheesemvc.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
    // Fields
    @NotNull
    @Size(min = 1, max = 15)
    private String userName;

    private String email;

    @NotNull
    @Size(min=1, max = 15)
    private String password;

    //Constructors
    public User(String userName, String email, String password) {
        this();
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    //Default constructor, when thee object is create
    public User() {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
    //Methods

    //GET/SET

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
