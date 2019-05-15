package ua.ifit.lms.controller;

import ua.ifit.lms.dao.entity.Good;
import ua.ifit.lms.dao.entity.User;
import ua.ifit.lms.dao.repository.GoodRepository;
import ua.ifit.lms.view.HeaderView;
import ua.ifit.lms.view.IndexSingletonView;
import ua.ifit.lms.view.ItemView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ItemServlet", urlPatterns = {"/item/*"}, loadOnStartup = 1)
public class ItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ItemView itemView = new ItemView();
        PrintWriter out = response.getWriter();
        User user = (User) request.getSession().getAttribute("user");
        String idFromPath = request.getPathInfo().substring(request.getPathInfo().lastIndexOf('/') + 1);
        Good good = GoodRepository.getGoodByID(Long.parseLong(idFromPath));
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        if (user != null) {
            out.println(HeaderView.getLoggedHeader(user.getName()));
        }
        else
            out.println(indexSingletonView.getMenu());
        out.println(itemView.getItemPage().replace("<!-- price -->", good.getPrice().toString())
                .replace("<!-- name -->", good.getGood_name())
                .replace("<!-- picture -->", "../" + good.getPicture_file_name())
                .replace("<!-- description -->", good.getDescription())
                .replace("<!-- ref -->", "/addtocart/" + idFromPath));
    }
}
