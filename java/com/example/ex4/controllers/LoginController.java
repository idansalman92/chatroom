package com.example.ex4.controllers;

import com.example.ex4.beans.Manager;
import com.example.ex4.repo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Resource(name = "getManager")
    private Manager repoManager;

    @RequestMapping("/login")
    public String showLoginPage(HttpSession session) {
        if((boolean)session.getAttribute("isLogged"))
            return "redirect:/chat";
        return "login-user";
    }
    @RequestMapping("/request-login")
    public String processLogin(@ModelAttribute("username") String login, HttpSession session, Model model)
    {
        String username = login.trim();
        if (username.equals(""))
        {
            // session.setAttribute("isLogged", false);
            model.addAttribute("loginError", true); // Show error message
            return "login-user"; // stay at login page
        }

        session.setAttribute("isLogged", true);
        User user = new User(username);

        session.setAttribute("username", username);
        repoManager.addUser(user);
        User savedUser = repoManager.findTopByOrderByIdDesc();
        session.setAttribute("userId", savedUser.getId());

        return "redirect:/chat";
    }

    @RequestMapping("/")
    public String showIndex() {
        return "login-user";
    }



    @RequestMapping("/logout")
    public String logoutUser(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        repoManager.deleteById(userId);
        session.setAttribute("userId", null);
        session.setAttribute("isLogged", false);
        return "redirect:/login";
    }


}
