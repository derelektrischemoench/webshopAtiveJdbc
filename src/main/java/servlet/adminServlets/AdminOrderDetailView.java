package servlet.adminServlets;

import model.Order;
import model.Record;
import model.Shoppingcart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminOrderDetailView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        Order o = Order.findById(orderId);
    
        Shoppingcart s = Shoppingcart.findById(o.getInteger("shoppingcart_id"));
        List<Record> records = s.getAll(Record.class);
        System.out.println("numrecords in ordder: " + records.size());
        
        req.setAttribute("order", o);
        req.setAttribute("records", records);
        
        
        RequestDispatcher rd = req.getRequestDispatcher("/adminOrderDetail.jsp");
        rd.forward(req, resp);
    }
}
