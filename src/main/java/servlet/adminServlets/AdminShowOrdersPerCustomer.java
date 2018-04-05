package servlet.adminServlets;

import com.sun.org.apache.xpath.internal.operations.Or;
import model.Account;
import model.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminShowOrdersPerCustomer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int accountId = Integer.parseInt(req.getParameter("customerId"));
        Account a = Account.findById(accountId);
        List<Order> ordersForCustomer = Order.where("account_id = ?", accountId);
    
        System.out.println("found orders for customer: " + a.getString("user_name"));
        
        for(Order o : ordersForCustomer) {
            System.out.println(o.getString("first_name") + " " + o.getString("address"));
        }
        
        req.setAttribute("customer", a);
        req.setAttribute("orders", ordersForCustomer);
    
        RequestDispatcher rd = req.getRequestDispatcher("/adminOrdersPerCustomer.jsp");
        rd.forward(req, resp);
    }
}
