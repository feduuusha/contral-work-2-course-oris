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

@WebServlet("/gifts")
public class GiftServlet extends HttpServlet {

    private GiftService giftService;

    @Override
    public void init(ServletConfig config) {
        giftService = (GiftService) config.getServletContext().getAttribute("giftService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null) {
            List<Gift> gifts = giftService.findAllGifts();
            req.setAttribute("gifts", gifts);
        } else {
            Long id = Long.parseLong(req.getParameter("id"));
            Optional<Gift> gift = giftService.findById(id);
            if (gift.isPresent()) {
                req.setAttribute("gifts", List.of(gift.get()));
            } else {
                req.setAttribute("gifts", List.of());
            }
        }
        req.getRequestDispatcher("/WEB-INF/templates/gifts.jsp").forward(req, resp);
    }
}
