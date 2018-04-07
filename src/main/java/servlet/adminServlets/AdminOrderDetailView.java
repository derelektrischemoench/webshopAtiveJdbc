package servlet.adminServlets;

import model.Order;
import model.Record;
import model.RecordsShoppingcarts;
import model.Shoppingcart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AdminOrderDetailView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        Order o = Order.findById(orderId);
        Shoppingcart s = Shoppingcart.findById(o.getInteger("shoppingcart_id"));
        String shoppingCartId = s.getString("id");
        List <RecordsShoppingcarts> rs = RecordsShoppingcarts.where("shoppingcart_id = ?", shoppingCartId);
    
        HashMap<Record, Integer> recordAndAmount = new HashMap<>();
    
        Iterator rsIter = rs.iterator();
        while (rsIter.hasNext()) {
            RecordsShoppingcarts r = (RecordsShoppingcarts)rsIter.next();
            Record rec = Record.findById(r.getInteger("record_id"));
            int amount = r.getInteger("record_amount");
            recordAndAmount.put(rec, amount);
        }
    
        System.out.println("verify the hashmap:");
        Iterator<Map.Entry<Record, Integer>> iter = recordAndAmount.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Record, Integer> entrySet = iter.next();
            System.out.println("record: " + entrySet.getKey().getString("title") + "amount: " + entrySet.getValue());
        }
        
        req.setAttribute("order", o);
        req.setAttribute("recordAndAmount", recordAndAmount);
        
        
        RequestDispatcher rd = req.getRequestDispatcher("/adminOrderDetail.jsp");
        rd.forward(req, resp);
    }
}
