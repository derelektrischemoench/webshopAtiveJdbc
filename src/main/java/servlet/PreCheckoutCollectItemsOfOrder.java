package servlet;

import model.Record;
import model.RecordsShoppingcarts;
import model.Shoppingcart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class PreCheckoutCollectItemsOfOrder extends HttpServlet {
    /*this collect the items of the order and the amount of records*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> RecordIds = Arrays.asList(req.getParameterValues("recordId"));
        List<String> amounts = Arrays.asList(req.getParameterValues("amount"));
        HashMap<Record, Integer> recordsInShoppingcart = new HashMap<>();
        Iterator<String> recordIdsIter = RecordIds.iterator();
        int counter = 0;
        ArrayList<String> missingRecordsErrorMsg = new ArrayList<>();
        boolean itemsOutOfStock = false;
        
        //adjust stock levels
        while (recordIdsIter.hasNext()) {
            String recordId = recordIdsIter.next();
            Record r = Record.findById(recordId);
            int oldStockAmount = r.getInteger("amount_in_stock");
            int amount = Integer.parseInt(amounts.get(counter));
            int newStockAmount = oldStockAmount - amount;
            
            if (newStockAmount < 0) {
                System.out.println("not enough items in stock");
                String errormsg = "Not enough items of " + r.getString("title") + " in stock. Amount left: " + oldStockAmount;
                missingRecordsErrorMsg.add(errormsg);
                itemsOutOfStock = true;
            }
            
            recordsInShoppingcart.put(r,amount); //record-amount pairing
            counter++;
        }
        
        //error, items missing
        if (itemsOutOfStock) {
            RequestDispatcher rd = req.getRequestDispatcher("/shoppingcartDetail.jsp");
            req.setAttribute("recordsInShoppingcart", recordsInShoppingcart);
            req.setAttribute("errorMsgArray", missingRecordsErrorMsg);
            rd.forward(req, resp);
            return;
        } else {
            //all items are in stock
            //SUCCESS
            HttpSession session = req.getSession();
            Shoppingcart s = (Shoppingcart)session.getAttribute("shoppingCart");
            int shoppingCartId = s.getInteger("id");
     
            for (Map.Entry<Record, Integer> entry : recordsInShoppingcart.entrySet()) {
                Record rec = entry.getKey();
                int amount = entry.getValue();
                //update the number of records in the join table
                System.out.println("value of shoppingcartid: " + shoppingCartId);
                List<RecordsShoppingcarts> target = RecordsShoppingcarts.where("shoppingcart_id = ?", shoppingCartId);
    
                System.out.println("target length: " + target.size());
                RecordsShoppingcarts entr = target.get(0);
                System.out.println(entr.getInteger("shoppingCart_id"));
                entr.set("record_amount", amount).saveIt();
            }
            
            RequestDispatcher rd = req.getRequestDispatcher("/orderConfirmCustomerCredentials.jsp");
            rd.forward(req, resp);
        }
        
    }
}
