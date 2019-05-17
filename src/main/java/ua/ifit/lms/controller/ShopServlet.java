package ua.ifit.lms.controller;

import ua.ifit.lms.dao.entity.Catalog;
import ua.ifit.lms.dao.entity.Good;
import ua.ifit.lms.dao.entity.User;
import ua.ifit.lms.dao.repository.CatalogRepository;
import ua.ifit.lms.dao.repository.GoodRepository;
import ua.ifit.lms.view.HeaderView;
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

@WebServlet(name = "ShopServlet", urlPatterns = {"/*"}, loadOnStartup = 1)
public class ShopServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String query = "SELECT idGood, Count_of_goods, Good_name, Picture_file_name, Price, Description FROM Good" +
                " LEFT JOIN good_has_catalog ON idGood = good_idGood" +
                " WHERE Count_of_goods > 0  AND(";
        int query_length = query.length();
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        out.println(indexSingletonView.getIndexHtml());

        User user = (User) session.getAttribute("user");

        if (user != null) {
            out.println(HeaderView.getLoggedHeader(user.getName()));
        }
        else
            out.println(indexSingletonView.getMenu());
        ShopView shopView = new ShopView();
        String shopPage = shopView.getShopPage();
        for(Catalog each: CatalogRepository.getCatalogs()) {
            String param_name = "check".concat(each.getId().toString());
            shopPage = shopPage.replace("<!-- element -->", indexSingletonView.getCatalog_item())
                    .replace("<!-- name -->", each.getDescription())
                    .replace("<!-- id -->", each.getId().toString())
                    .replace("<!-- check -->", (request.getParameter(param_name) != null && request.getParameter(param_name).equals("on")) ? "checked" : "");
            if(request.getParameter(param_name) != null && request.getParameter(param_name).equals("on")) query = query.concat(" catalog_id = " + each.getId() + " OR ");
        }
        query = query.substring(0, query.length() - 4) + ((query.length() > query_length) ? ");" : ";");
        System.out.println(query);
        //Проходимося по всіх товарах. Перевіряємо чи товар є у наявності, якщо є - формуємо його на сторінку магазину
        for(Good each: GoodRepository.getGoodsByQuery(query)){ // loop, which are checking all goods from database
                shopPage = shopPage.replace("<!-- item -->", indexSingletonView.getItem_element()
                        .replace("<!-- price -->", each.getPrice().toString())
                        .replace("<!-- name -->", each.getGood_name())
                        .replace("<!-- picture -->", each.getPicture_file_name())
                        .replace("<!-- description -->", each.getDescription())
                        .replace("<!-- address -->", request.getPathInfo().concat("item/").concat(each.getIdGood().toString())));
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
