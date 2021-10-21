package com.example.ex4.beans;

import com.example.ex4.repo.GetMessages;
import com.example.ex4.repo.GetUsers;
import com.example.ex4.repo.Message;
import com.example.ex4.repo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.List;

/**
 * A class to manage the repository.
 */
@Component
public class Manager implements Serializable {
    @Autowired
    private GetMessages messages;
    @Autowired
    private GetUsers users;

    /**
     * Adds a message to the database.
     * @param msg The message to add
     */

    public void addMessage(Message msg) {
            messages.save(msg);
    }

    /**
     * Adds a user to the database.
     * @param user The user to add
     */
    public void addUser(User user) {
         users.save(user);
    }

    public List<Message> findTop5ByOrderByIdDesc() {
        return messages.findTop5ByOrderByIdDesc();
    }

    public List<User> findAllByOrderByIdAsc(){ return users.findAllByOrderByIdAsc();};

    public void deleteById(Long id){users.deleteById(id);}

    public User findTopByOrderByIdDesc(){return users.findTopByOrderByIdDesc();}

    public List<Message> findAllByMessage(String msg) {return messages.findAllByMessage(msg);}

    public List<User> findAllByUser(User user){return users.findAllById(user);}

    public List<Message> findAllById(long id){return messages.findAllById(id);}

    public List<Message> findAll(){return messages.findAll();}

}
