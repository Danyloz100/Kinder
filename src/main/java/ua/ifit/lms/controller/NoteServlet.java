package ua.ifit.lms.controller;

import ua.ifit.lms.dao.entity.Note;
import ua.ifit.lms.dao.entity.User;
import ua.ifit.lms.dao.repository.NoteRepository;
import ua.ifit.lms.view.IndexSingletonView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "NoteServlet", urlPatterns = {"/notes/*"})
public class NoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("/");
        }

        NoteRepository noteRepository = new NoteRepository();
        List<Note> notes = noteRepository.getNotesByUserid(user.getId());

        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        out.println(indexSingletonView.getIndexHtml()
                .replace("<!--### insert html here ### -->", "<h1>Hello " + user.getName() + "</h1>" +
                        notes.stream()
                                .map(e -> "<div>" + e.getText() + "</div>")
                                .collect(Collectors.joining(" "))));
    }
}
