package ua.ifit.lms.controller;

import ua.ifit.lms.dao.entity.User;
import ua.ifit.lms.dao.repository.UserRepository;
import ua.ifit.lms.view.IndexSingletonView;
import ua.ifit.lms.view.LoginView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "StartServlet", urlPatterns = {"/login"})
public class StartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();


        // get user credentials
        LoginView loginView = new LoginView();
        if (request.getParameter("email") != null &&
                request.getParameter("password") != null) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // test repository
            UserRepository userRepository = new UserRepository();
            User user = userRepository.getUserByEmailByPassword(email, password);
            // check if a user successfully logged in
            if (user != null) {
                session.setAttribute("user", user);
                response.sendRedirect("/shop");
            }
            else {
                if (userRepository.isUserRegisterated(email) == false) {
                    session.setAttribute("LoginInfo", "<a class=\"label-input100\" style=\"color: red;\" href=\"/reg\">There's no any user with this email.(Click on message)</a>");
                } else {
                    session.setAttribute("LoginInfo", "<a class=\"label-input100\" style=\"color: red;\" href=\"/reg\">You've typed incorrect password.(Click on message)</a>");
                }
                response.sendRedirect("/");
            }
                out.println(loginView.getloginPage());
        } else {
            if(session.getAttribute("LoginInfo") != null)
            {
                out.println(loginView.getloginPage().replace("<!-- Login message -->", (String) session.getAttribute("LoginInfo")));
                session.setAttribute("LoginInfo", null);
            }
            else
            out.println(loginView.getloginPage());
        }

    }
}
