package gr.aueb.cf.helloapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
@WebServlet("inspect-request")
public class InspectRequestController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        HttpSession session = req.getSession(false); //false: get current session (if exists) else return null
        if (username != null && username.equals("panagiotis") && password != null && password.equals("12345")) {
            session.setAttribute("username", username);
            session.setAttribute("role", "TEACHER");

        }

        resp.sendRedirect("/school-app/teachers");
        //return;

        resp.getWriter().write("Session id: " + req.getSession() + "\n");

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for(Cookie cookie : cookies) {
                resp.getWriter().write("Cookie name: " + cookie.getName() + ", Cookie value: " + cookie.getValue() + "\n");
            }
        }

        resp.getWriter().write("Request URI: " + req.getRequestURI() + "\n");
        resp.getWriter().write("Request Context Path: " + req.getContextPath() + "\n");
        resp.getWriter().write("Request Servlet Path: " + req.getServletPath() + "\n");
    }
}
