package servlet;

import model.Record;
import model.Shoppingcart;
import org.glassfish.grizzly.asyncqueue.RecordReadResult;
import org.javalite.activejdbc.Base;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AddToCart extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int RecordId = Integer.parseInt(req.getParameter("recordId"));
        System.out.println(RecordId);
        
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
    
        Record r = Record.findById(RecordId);
        
        Shoppingcart s = Shoppingcart.createIt();
        s.add(r);
    
        HttpSession session = req.getSession();
        System.out.println("added shoppingcart to session");
        session.setAttribute("shoppingCart", s);
    
        System.out.println("added records to shoppingcart");
        
        List<Record> recordsInShoppingCart = s.getAll(Record.class);
        System.out.println("size of records in shoppingkarts: " + recordsInShoppingCart.size());
    
        Iterator<Record> shiter = recordsInShoppingCart.iterator();
        while (shiter.hasNext()) {
            Record rec = shiter.next();
            System.out.println("Record in scarkt: " + rec.getString("title"));
        }
        
        Base.close();

    }
}
