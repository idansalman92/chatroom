package com.example.ex4.repo;

import javax.persistence.*;

/**
 * An Entity class to save a user in the database.
 * For each user we'll save his: id (auto generated), username.
 */
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    /**
     * Empty constructor
     */
    public User() {}

    /**
     * Simple constructor.
     * @param username The username of the user
     */
    public User(String username) {
        this.username = username;
    }

    /**
     * Returns ID
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Sets ID
     * @param id Parameter to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns username
     * @return username as string
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username
     * @param username username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
