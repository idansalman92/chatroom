package com.example.ex4.filters;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginValidator implements HandlerInterceptor {
    /**
     * Checks from the session if the user is logged in or not and filters every URI in the website:
     * "/request-login" and "/logout" - ignores
     * For the first visit of a user in the websites - creates a session for him
     * If the user is not logged in and trying to view a page - redirects to the login page
     * If the user is logged in and trying to view the login page - redirects to the chat page
     * @param request The request
     * @param response The response
     * @param handler Unused
     * @return true to pass the request to its handler, false otherwise
     * @throws Exception Thrown in case of an exception in redirecting the user
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.equals("/request-login") || requestURI.equals("/logout")) {
            // we don't want to validate on the those URIs
            return true;
        }
        HttpSession session = request.getSession();
        Boolean isLogged = (Boolean) session.getAttribute("isLogged");
        if (isLogged == null) {
            // the first time the user visiting the website
            session.setAttribute("isLogged", false);
            response.sendRedirect("/login");
            return false;
        }
        if (!isLogged && !requestURI.equals("/login")) {
            // the user is not logged in and trying to visit anything but the login page
            response.sendRedirect("/login");
            return false;
        }
        else if (isLogged && requestURI.equals("/login")) {
            // the user is logged in already and trying to visit the login page
            response.sendRedirect("/chat");
            return false;
        }
        return true;
    }
}
