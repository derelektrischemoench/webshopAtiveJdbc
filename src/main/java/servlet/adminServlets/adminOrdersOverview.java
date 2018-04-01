package servlet.adminServlets;

import com.sun.org.apache.xpath.internal.operations.Or;
import model.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class adminOrdersOverview extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> allOrders = Order.findAll();
        req.setAttribute("allOrders", allOrders);
        RequestDispatcher rd = req.getRequestDispatcher("/adminOrders.jsp");
        rd.forward(req, resp);
    }
}
