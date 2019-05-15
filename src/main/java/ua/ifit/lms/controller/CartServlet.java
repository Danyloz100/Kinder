package ua.ifit.lms.controller;

import ua.ifit.lms.dao.entity.Good;
import ua.ifit.lms.dao.entity.User;
import ua.ifit.lms.dao.repository.GoodRepository;
import ua.ifit.lms.view.CartView;
import ua.ifit.lms.view.HeaderView;
import ua.ifit.lms.view.IndexSingletonView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart/*"})
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        CartView cartView = new CartView();
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        User user = (User) session.getAttribute("user");
        String cartPage = cartView.getCartPage();
        if (user != null) {
            out.println(HeaderView.getLoggedHeader(user.getName()));
            for(Good each: GoodRepository.getGoodsByUserID(user.getId())) {
                cartPage = cartPage.replace("<!-- item -->", indexSingletonView.getCart_item())
                        .replace("<!-- name -->", each.getGood_name())
                        .replace("<!-- description -->", each.getDescription())
                        .replace("<!-- price -->", each.getPrice().toString())
                        .replace("<!-- picture -->", each.getPicture_file_name())
                        .replace("<!-- address -->", "/deletefromcart/" + each.getIdGood());
            }
            out.println(cartPage);
        }
        else
            response.sendRedirect("/login");


    }

    }

