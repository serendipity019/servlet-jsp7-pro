package gr.aueb.cf.helloapp.controller;

import gr.aueb.cf.helloapp.model.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/school-app/teachers")
public class TeachersViewController extends HttpServlet {

    List<Teacher> teachers = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        teachers = List.of(new Teacher(1L, "Alice", "W."),
                new Teacher(6L, "Bob", "D."),
                new Teacher(2L, "Costas", "A."),
                new Teacher(3L, "Dimimitris", "A."),
                new Teacher(4L, "Elon", "M."),
                new Teacher(5L, "Panagiotis", "P.")
                );

        //teachers = teacherService.getAllTEachers();

        String message = "";
        String filterId = req.getParameter("id");
        Long longFilterId = (filterId != null && !filterId.isEmpty()) ? Long.parseLong(filterId) : null;
        String filterFirstname = req.getParameter("firstname");
        String filterLastname = req.getParameter("lastname");

        try {
            var filteredTeachers = teachers.stream()
                    .filter(teacher -> longFilterId == null || teacher.getId().equals(longFilterId))
                    .filter(teacher -> filterFirstname == null || filterFirstname.isEmpty() ||
                            teacher.getFirstname().startsWith(filterFirstname))
                    .filter(teacher -> filterLastname == null || filterLastname.isEmpty() ||
                            teacher.getLastname().startsWith(filterLastname))
                    .collect(Collectors.toList());

            if (filteredTeachers.isEmpty()) req.setAttribute("message", "Didn't found teachers with these search criteria ");
            else req.setAttribute("teachers", filteredTeachers);

            req.getRequestDispatcher("/WEB-INF/jsp/teachers.jsp").forward(req, resp);
        } catch (Exception e) {
            message = e.getMessage();
            req.setAttribute("message", message);
            req.getRequestDispatcher("/WEB-INF/jsp/teachers.jsp").forward(req, resp);
        }
    }
}
