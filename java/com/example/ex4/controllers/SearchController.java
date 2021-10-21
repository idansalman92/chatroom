package com.example.ex4.controllers;

import com.example.ex4.beans.Manager;
import com.example.ex4.repo.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Resource(name = "getManager")
    private Manager repoManager;
    List<Message> allMessages;
    ArrayList<Message> allMsgsByUser;
    List<Message> allMsg;

    @RequestMapping("/search-login")
    public String showSearchByMessage(HttpSession session, Model model) {
        return "search-page";
    }

    @RequestMapping("/choose-search")
    public String chooseSearchType(@ModelAttribute("type") String select,Model model) {
        model.addAttribute("isMessage", true);

        if(select.equals("User"))
             model.addAttribute("isMessage", false);
        model.addAttribute("size", 0);
        // else if(select.equals("Message"))
           //  model.addAttribute("isMessage", true);
        return "search-page";
    }

    @RequestMapping("/searchByUser")
    public String showSearchByUser(@ModelAttribute("search") String search,HttpSession session, Model model) {
        allMsg = repoManager.findAll();
        allMsgsByUser = new ArrayList<Message>();
        for(int i=0;i<allMsg.size();i++)
        {
            if(allMsg.get(i).getUsername().equals(search))
                allMsgsByUser.add(allMsg.get(i));
        }
        model.addAttribute("size", allMsgsByUser.size());
        model.addAttribute("allMsgsByUser", allMsgsByUser);
        model.addAttribute("isMessage", false);
        return "search-page";
    }

    @RequestMapping("/searchByMessage")
    public String showSearchByMessage(@ModelAttribute("search") String search,HttpSession session, Model model) {
        allMessages = repoManager.findAllByMessage(search);
        model.addAttribute("size", allMessages.size());
        model.addAttribute("allMsgs", allMessages);
        model.addAttribute("isMessage", true);
        return "search-page";
    }
}
