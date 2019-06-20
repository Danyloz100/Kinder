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
import java.util.ArrayList;
import java.util.stream.Collectors;


/**
 * Основна сторінка магазину
*/
@WebServlet(name = "ShopServlet", urlPatterns = {"/*"}, loadOnStartup = 1)
public class ShopServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(); //Отримуємо обєкт сесії
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        ShopView shopView = new ShopView();
        String shopPage = shopView.getShopPage();
        ArrayList<Catalog> checkedCategories = new ArrayList(); // Створюємо ліст для категорій, які вибрав юзер
        User user = (User) session.getAttribute("user"); // Отримуємо обєкт юзера з атрибуту сесії

        out.println(indexSingletonView.getIndexHtml());
        out.println(HeaderView.getHeader(user)); // Формуємо хедер в залежності від того чи юзер залогінений чи ні
        ArrayList<Catalog> catalogList = CatalogRepository.getCatalogs();
        for(int i = 0; i < catalogList.size(); i++) { // Проходимося по всіх каталогах, які відповідають хоча б 1 товару
            Catalog each = catalogList.get(i);
            if((i>0 && !each.getName().equals(catalogList.get(i-1).getName())) || i == 0)
                shopPage = shopView.addGroupNameForCatalogs(shopPage, each.getName());
            String param_name = "check".concat(each.getId().toString());
            shopPage = shopView.addCategory(shopPage, // Формуємо каталог на сторінці
                    each,
                    (request.getParameter(param_name) != null && request.getParameter(param_name).equals("on")) ? "checked" : ""); // Перевіряємо чи юзер вибрав даний каталог
            if (request.getParameter(param_name) != null && request.getParameter(param_name).equals("on")) // Якщо юзер вибрав даний радіобатн - додаємо його до ліста описаного вище
                checkedCategories.add(each);
        }
        String searchValue = request.getParameter("search"); // Отримує значення, яке ввів юзер в строку пошуку
        ArrayList<Good> list = GoodRepository.getGoodsByCategories(checkedCategories); // Отримуємо ліст товарів, які відповідають каталогам, які вибрав юзер
        for(Good each: (searchValue == null || searchValue.equals("")) ? list : GoodRepository.getGoodsByDescription(searchValue).stream().filter(c -> list.contains(c)).collect(Collectors.toList())){ // Цикл проходиться по товарах, що шукає юзер
            shopPage = shopView.addItem(shopPage, each,
                    request.getPathInfo().concat("item/").concat(each.getIdGood().toString())); // Додаємо(формуємо) айтем на сторінку
        }
        out.println((searchValue != null && !searchValue.equals("")) ? shopView.addSearchValue(shopPage, searchValue) : shopPage); // Даємо браузеру готову сторінку
    }

    @Override
    public void init() throws ServletException { // Виклик методу, який викликається при ініціалізації сервлета
        super.init(); // Викликаємо метод батька HttpServlet
        String path = getServletContext().getRealPath("html/"); //отримує шлях до папки html
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance(); // Оголошуємо обєкт класу IndexSingletonView.
        indexSingletonView.setPath(path); //Передаємо шлях обєкту indexSingletonView
    }
}
