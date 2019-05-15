package ua.ifit.lms.controller;

import ua.ifit.lms.dao.entity.User;
import ua.ifit.lms.dao.repository.OrderRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteFromCartServlet", urlPatterns = {"/deletefromcart/*"})
public class DeleteFromCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idFromPath = request.getPathInfo().substring(request.getPathInfo().lastIndexOf('/') + 1);
        User user = (User)request.getSession().getAttribute("user");
        OrderRepository.deleteOrderByUserIDByGoodID(user.getId(), Long.parseLong(idFromPath));
        response.sendRedirect("/cart");
    }
}
