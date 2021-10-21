package com.example.ex4.controllers;

import com.example.ex4.beans.Manager;
import com.example.ex4.repo.Message;
import com.example.ex4.repo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ChatController {

    private List<Message> messages;
    @Resource(name = "getManager")
    private Manager repoManager;

    @RequestMapping("/chat")
    public String showLoginPage(HttpSession session, Model model) {
        messages = repoManager.findTop5ByOrderByIdDesc();
        List<User> users = repoManager.findAllByOrderByIdAsc();
        model.addAttribute("messages", messages);

        model.addAttribute("users", users);
        String username = (String) session.getAttribute("username");
        return "chat-room";
    }

    @RequestMapping("/chat-update")
    public String updateChat(HttpSession session, Model model) {
         return "redirect:/chat";
    }

    @RequestMapping("/chat-form")
    public String sendMessage(@ModelAttribute("message") String msg, HttpSession session) {
        if (!msg.equals("")) {
            // we don't want to add the database an empty user
            // input
            String username = (String) session.getAttribute("username");
            Message msg2 = new Message(username, msg);
            repoManager.addMessage(msg2);
        }
        return "redirect:/chat";
    }

    @RequestMapping("/chat-json")
    public List<Message> update(HttpSession session, Model model) {
        showLoginPage(session, model);
        return messages;
    }

    @RequestMapping("/back-to-chat")
    public String backToChat() {
        return "redirect:/chat";
    }

    @RequestMapping("/searchPage")
    public String redirectPage() {
        return "redirect:/search-login";
    }

    @RequestMapping(value="/event-count")
    public String getEventCount(ModelMap map) {
        map.addAttribute("msgs", messages);
        return "redirect:/chat";
    }

    @PostMapping("/get-messages")
    public String getMessages() {
        messages = repoManager.findTop5ByOrderByIdDesc();
        //return messages;
        return "/";
    }
}
