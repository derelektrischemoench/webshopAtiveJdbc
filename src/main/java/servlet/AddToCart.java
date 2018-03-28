package servlet;

import model.Record;
import model.Shoppingcart;
import org.glassfish.grizzly.asyncqueue.RecordReadResult;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.DB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class AddToCart extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int RecordId = Integer.parseInt(req.getParameter("recordId"));
        System.out.println(RecordId);
        HttpSession session = req.getSession();
        
        
        try {
            
            DB tagDb = new DB();
            tagDb.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
            Record r = Record.findById(RecordId);
            
            Shoppingcart s;
            //the user needs a new kart:
            
            if (session.getAttribute("shoppingCart") == null) {
                System.out.println("created new shoppingkart for user");
                s = Shoppingcart.createIt();
            } else {
                Shoppingcart sc = (Shoppingcart) session.getAttribute("shoppingCart");
                if (sc == null) {
                    System.out.println("sc was null on retrieval, created it");
                    s = new Shoppingcart();
                    session.setAttribute("shoppingCart", s);
                } else {
                    System.out.println("found old shoppingkart");
                    s = (Shoppingcart) session.getAttribute("shoppingCart");
                    System.out.println("Retrieved old shoppingkart of user");
                }
            }
            
            s.add(r);
            session.setAttribute("shoppingCart", s);
            
            RequestDispatcher rd = req.getRequestDispatcher("/recordDetailCustomer.jsp");
            req.setAttribute("successMessage", "assddasdd");
            rd.forward(req, resp);
            
        } finally {
            DB.closeAllConnections();
        }
        
        
    }
}
