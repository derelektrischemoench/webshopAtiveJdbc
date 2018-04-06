package servlet;

import model.Record;
import model.Shoppingcart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShoppingCartDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int shoppingCartId = Integer.parseInt(req.getParameter("shoppingCartId"));
        System.out.println("shkart id from get param:  " + shoppingCartId);
        
        Shoppingcart s = Shoppingcart.findById(shoppingCartId);
        
        if(s != null) {
            System.out.println("found shkart");
        } else {
            System.out.println("fail");
        }
    
        List<Record> recordsInShoppingCart = s.getAll(Record.class);
    
        System.out.println("records in shoppingcart: ");
        for(Record r : recordsInShoppingCart) {
            System.out.println(r.getString("title"));
        }
        
        req.setAttribute("recordsInShoppingcart", recordsInShoppingCart);
        RequestDispatcher rd = req.getRequestDispatcher("shoppingcartDetail.jsp");
        rd.forward(req, resp);
        
    }
}
