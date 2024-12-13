package ru.itis.contralwork.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.contralwork.entity.Gift;
import ru.itis.contralwork.service.GiftService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/")
public class IndexServlet extends HttpServlet {

    private GiftService giftService;

    @Override
    public void init(ServletConfig config) {
        giftService = (GiftService) config.getServletContext().getAttribute("giftService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Gift> gifts = giftService.findAllGifts();
        req.setAttribute("gifts", gifts);
        req.getRequestDispatcher("/WEB-INF/templates/gifts.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Optional<Gift> gift = giftService.findById(id);
        if (gift.isPresent()) {
            req.setAttribute("gifts", List.of(gift.get()));
        } else {
            req.setAttribute("gifts", List.of());
        }
        req.getRequestDispatcher("/WEB-INF/templates/gifts.jsp").forward(req, resp);
    }
}
