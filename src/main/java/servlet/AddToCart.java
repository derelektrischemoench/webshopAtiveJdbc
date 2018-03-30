package servlet;

import model.Artist;
import model.Record;
import model.Shoppingcart;
import org.glassfish.grizzly.asyncqueue.RecordReadResult;
import org.javalite.activejdbc.DB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class AddToCart extends HttpServlet {
    //TODO: this has to be done via session because otherwise the shoppingcart doesnt work
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int RecordId = Integer.parseInt(req.getParameter("recordId"));
        HttpSession session = req.getSession();
        Record r = Record.findById(RecordId);
        
        Shoppingcart s;
        if (session.getAttribute("shoppingCart") == null) {
            s = Shoppingcart.createIt();
        } else {
            Shoppingcart sc = (Shoppingcart) session.getAttribute("shoppingCart");
            if (sc == null) {
                s = new Shoppingcart();
                session.setAttribute("shoppingCart", s);
            } else {
                s = (Shoppingcart) session.getAttribute("shoppingCart");
            }
        }
        
        //add the record to the cart
        s.add(r);
        
        session.setAttribute("shoppingCart", s);
    }
}
