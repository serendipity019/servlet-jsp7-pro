package gr.aueb.cf.helloapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/date") //Here listening this controller
public class DateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LocalDateTime currentDAteTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MM yyyy HH:mm:ss");
        String currentDateTimeStr = currentDAteTime.format(formatter);

        req.setAttribute("currentDateTimeStr", currentDateTimeStr);
        req.getRequestDispatcher("WEB-INF/jsp/date.jsp").forward(req, resp); // We use this to show it where to find our "page" that it will return.


    }
}
