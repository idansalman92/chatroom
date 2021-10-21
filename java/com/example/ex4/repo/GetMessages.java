package com.example.ex4.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface to define all the queries that will be available for the messages in database
 */
public interface GetMessages extends JpaRepository<Message, Long> {
    /**
     * Searches messages in the database by username
     * @param username The username string to search by
     * @return A Message object if found, null otherwise
     */
    Message findByusername(String username);

    /**
     * Searches message in the database by string
     * @param msg The msg string to search by
     * @return A Message object if found, null otherwise
     */
    Message findByMessage(String msg);

    /**
     * Searches all messages in the database by string
     * @param msg The msg string to search by
     * @return A list of Messages found by string, null otherwise
     */
    List<Message> findAllByMessage(String msg);

    /**
     * Finds the top 5 messages
     * @return The top 5 messages
     */
    List<Message> findTop5ByOrderByIdDesc();

    /**
     * Finds all messages
     * @return list of all The messages
     */
    List<Message> findAll();

    /**
     * Finds all messages by user id
     * @return list of all The messages found by id
     */
    List<Message> findAllById(long id);
}
