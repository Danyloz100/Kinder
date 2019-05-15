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

@WebServlet(name = "RegestrationServlet", urlPatterns = {"/reg"})
public class RegestrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        RegestrationView regestrationView = new RegestrationView();
        if (session.getAttribute("existsEmail") != null) {
            out.println(regestrationView.getRegestrationPage().replace("<!-- EmailExist -->", (String) session.getAttribute("existsEmail")));
            session.setAttribute("existsEmail", null);
        } else if (session.getAttribute("PasswordInfo") != null) {
            out.println(regestrationView.getRegestrationPage().replace("<!-- PasswordInfo -->", (String) session.getAttribute("PasswordInfo")));
            session.setAttribute("PasswordInfo", null);
        } else
            out.println(regestrationView.getRegestrationPage());

        if (request.getParameter("name") != null &&
                request.getParameter("email") != null &&
                request.getParameter("password") != null &&
                request.getParameter("repeat-password") != null) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (!UserRepository.isUserRegisterated(email)) {
                if (!password.equals(request.getParameter("repeat-password"))) {

                    session.setAttribute("PasswordInfo", "<span class=\"label-input100\" style=\"color: red;\">Both passwords are not identical.</span>");
                    response.sendRedirect("/reg");
                } else {
                    UserRepository.setnewUser(name, email, password);
                    response.sendRedirect("/");
                }
            } else {
                session.setAttribute("existsEmail", "<span class=\"label-input100\" style=\"color: red;\">User with this email is already registered.</span>");
                response.sendRedirect("/reg");
            }


        }
    }
}
