package servlet;

import model.Record;
import model.Shoppingcart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteFromShoppingcart extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String recordId = req.getParameter("recordId");
        System.out.println("recordId " + recordId);
        
        Shoppingcart s = (Shoppingcart) session.getAttribute("shoppingCart");
        if(s==null) {
            throw new NullPointerException("shoppingcart does not exist");
        } else {
            Record r = Record.findById(recordId);
            s.remove(r);
            System.out.println("called delete from shoppingkart with: " + recordId);
        }
    }
}
