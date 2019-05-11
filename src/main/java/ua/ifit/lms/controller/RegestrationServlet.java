package ua.ifit.lms.controller;

import ua.ifit.lms.dao.repository.UserRepository;
import ua.ifit.lms.view.RegestrationView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegestrationServlet", urlPatterns = {"/reg"})
public class RegestrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        RegestrationView regestrationView = new RegestrationView();
        out.println(regestrationView.getRegestrationPage());
        if (request.getParameter("name") != null &&
                request.getParameter("email") != null &&
                request.getParameter("password") != null) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            UserRepository userRepository = new UserRepository();
            userRepository.setUserByEmailByPassword(name, email, password);


        }
    }
}
