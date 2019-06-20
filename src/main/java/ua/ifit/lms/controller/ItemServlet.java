package ua.ifit.lms.controller;

import ua.ifit.lms.dao.entity.Good;
import ua.ifit.lms.dao.entity.User;
import ua.ifit.lms.dao.repository.GoodRepository;
import ua.ifit.lms.view.HeaderView;
import ua.ifit.lms.view.ItemView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сторінка товару
 */

@WebServlet(name = "ItemServlet", urlPatterns = {"/item/*"}, loadOnStartup = 1)
public class ItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ItemView itemView = new ItemView();
        PrintWriter out = response.getWriter();
        User user = (User) request.getSession().getAttribute("user"); // Отримуємо обєкт юзера з сесії
        String idFromPath = request.getPathInfo().substring(request.getPathInfo().lastIndexOf('/') + 1); // Отримуємо айді товару з адреси
        Good good = GoodRepository.getGoodByID(Long.parseLong(idFromPath)); // Отримуємо обєкт товару за допомогою айді товару
        out.println(HeaderView.getHeader(user)); // Формуємо хедер сторінки
        out.println(itemView.addItem(itemView.getItemPage(), good) /* Додаємо інформацію про товар на сторінку товару */); // Передаємо браузеру сформовану сторінку товару
    }
}
