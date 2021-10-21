package com.example.ex4.repo;

import javax.persistence.*;

/**
 * An Entity class to save a message in the database.
 * For each message we'll save : id (auto generated), username, message.
 */
@Entity
@Table(name = "Messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "message")
    private String message;

    /**
     * Empty constructor
     */
    public Message() {}

    /**
     * Simple constructor
     * @param username The username of the user
     * @param message The message to the user
     */
    public Message(String username, String message) {
        this.username = username;
        this.message = message;
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
     * Returns login
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets login
     * @param username Parameter to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * returns message
     * @return massage as string
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message
     * @param message string to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
