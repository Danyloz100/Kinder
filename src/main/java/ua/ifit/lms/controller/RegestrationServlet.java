package ua.ifit.lms.controller;

import ua.ifit.lms.dao.repository.UserRepository;
import ua.ifit.lms.view.RegestrationView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLOutput;

@WebServlet(name = "RegestrationServlet", urlPatterns = {"/reg"})
public class RegestrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        RegestrationView regestrationView = new RegestrationView();

        if(session.getAttribute("existsEmail") != null) {
            session.setAttribute("existsEmail", null);
            out.println(regestrationView.getRegestrationPage().replace("<!-- EmailExist -->", "User with this email is already registered"));
        }
        else
            out.println(regestrationView.getRegestrationPage());
        if (request.getParameter("name") != null &&
                request.getParameter("email") != null &&
                request.getParameter("password") != null) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            UserRepository userRepository = new UserRepository();
            if(!userRepository.isUserRegistrated(email)) {
                userRepository.setUserByEmailByPassword(name, email, password);
                response.sendRedirect("/");
            }
            else {
                session.setAttribute("existsEmail", "exist");
                response.sendRedirect("/reg");
            }
        }
    }
}
