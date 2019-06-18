package ua.ifit.lms.controller;

import ua.ifit.lms.dao.entity.User;
import ua.ifit.lms.dao.repository.UserRepository;
import ua.ifit.lms.view.LoginView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "StartServlet", urlPatterns = {"/login/*"})
public class StartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        LoginView loginView = new LoginView();
        String path = request.getPathInfo();
        System.out.println(path);
        if(path != null && path.substring(path.lastIndexOf("/") + 1).equals("logout"))
            session.setAttribute("user", null);

        if (request.getParameter("email") != null &&
                request.getParameter("password") != null) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // test repository
            User user = UserRepository.getUserByEmailByPassword(email, password);
            // check if a user successfully logged in
            if (user != null) {
                session.setAttribute("user", user);
                if(session.getAttribute("GoodID") != null) {
                    String address = "/cart/addtocart/" + session.getAttribute("GoodID");
                    session.setAttribute("GoodID", null);
                    response.sendRedirect(address);
                }
                else
                response.sendRedirect("/");
            }
            else {
                if (!UserRepository.isUserRegisterated(email)) {
                    session.setAttribute("LoginInfo", "<a class=\"label-input100 text-danger\"  href=\"/reg\">There's no any user with this email.(Click on message)</a>");
                } else {
                    session.setAttribute("LoginInfo", "<span class=\"label-input100 text-danger\"> You've typed incorrect password.</span>");
                }
                response.sendRedirect("/login");
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
