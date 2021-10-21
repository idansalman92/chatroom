package com.example.ex4.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface to define all the queries that will be available for the users in database
 */

public interface GetUsers extends JpaRepository<User, Long> {
    /**
     * Searches a user in the database by his username.
     * @param username The login string to search
     * @return A User object if found, null otherwise
     */

    User findByUsername(String username);

    /**
     * delete a user from the database by his id.
     * @param id The user id to delete
     */
    void deleteById(Long id);

    /**
     * Finds all users in database
     * @return list of users found
     */
    List<User> findAllByOrderByIdAsc();

    /**
     * Finds top user in database
     * @return top user object
     */
    User findTopByOrderByIdDesc();

    /**
     * Finds all users in database by id
     * @return list of users found by id
     */
    List<User> findAllById(User user);


}
