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

public class AdminInvoiceView extends HttpServlet {
    /*generates an invoice for the admin to behold.... */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Admininvoice view");
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        Order o = Order.findById(orderId);
        Shoppingcart s = Shoppingcart.findById(o.get("shoppingcart_id"));
    
        List<Record> recordsInOrder = s.getAll(Record.class);
        req.setAttribute("recordsInOrder", recordsInOrder);
    
        RequestDispatcher rd = req.getRequestDispatcher("/adminInvoiceView.jsp");
        rd.forward(req, resp);
    }
}
