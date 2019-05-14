package ua.ifit.lms.controller;

import ua.ifit.lms.dao.entity.Good;
import ua.ifit.lms.dao.entity.User;
import ua.ifit.lms.dao.repository.GoodRepository;
import ua.ifit.lms.view.IndexSingletonView;
import ua.ifit.lms.view.ShopView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ShopServlet", urlPatterns = {"/"}, loadOnStartup = 1)
public class ShopServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        out.println(indexSingletonView.getIndexHtml());

        User user = (User) session.getAttribute("user");

        if (user != null) {
            out.println(indexSingletonView.getMenu()
                    .replace("<a class=\"nav-link\" href=\"/login\"> Login <span class=\"sr-only\">", "<a class=\"nav-link\" href=\"/logout\"> Log out <span class=\"sr-only\">")
                        .replace("<a class=\"nav-link\" href=\"/reg\"> SingUp </a>", "<a class=\"nav-link\" href=\"/cart\"> " + user.getName() + " </a>")
            );
        }
        else
            out.println(indexSingletonView.getMenu());
        ShopView shopView = new ShopView();
        String shopPage = shopView.getShopPage();
        for(Good each: GoodRepository.getGoods()) { // loop, which are checking all goods from database
            shopPage = shopPage.replace("<!-- item -->", indexSingletonView.getItem_element()
                    .replace("<!-- price -->", each.getPrice().toString())
                    .replace("<!-- name -->", each.getGood_name())
                    .replace("<!-- picture -->", each.getPicture_file_name())
                    .replace("<!-- description -->", each.getDescription()));
        }
        out.println(shopPage);
    }
    @Override
    public void init() throws ServletException {
        super.init();
        String path = getServletContext().getRealPath("html/");
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        indexSingletonView.setPath(path);
    }
}
