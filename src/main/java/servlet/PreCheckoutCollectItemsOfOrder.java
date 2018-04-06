package servlet;

import model.Record;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PreCheckoutCollectItemsOfOrder extends HttpServlet {
    /*this collect the items of the order and the amount of records*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("dopost in precheckoutcollectitemsoforder");
        
        List<String> RecordIds = Arrays.asList(req.getParameterValues("recordId"));
        List<String> amounts = Arrays.asList(req.getParameterValues("amount"));
        ArrayList<Record> recordsInShoppingcart = new ArrayList<>();
        Iterator<String> recordIdsIter = RecordIds.iterator();
        int counter = 0;
        ArrayList<String> missingRecordsErrorMsg = new ArrayList<>();
        boolean itemsOutOfStock = false;
        
        //adjust stock levels
        while (recordIdsIter.hasNext()) {
            String recordId = recordIdsIter.next();
            Record r = Record.findById(recordId);
            int oldStockAmount = r.getInteger("amount_in_stock");
            System.out.println("Record:" + r.get("title"));
            int amount = Integer.parseInt(amounts.get(counter));
            System.out.println("amount of record in shoppingcart: " + amount);
            System.out.println("amount of record in stock: " + oldStockAmount);
            
            int newStockAmount = oldStockAmount - amount;
            
            if (newStockAmount < 0) {
                System.out.println("not enough items in stock");
                String errormsg = "Not enough items of " + r.getString("title") + " in stock. Amount left: " + oldStockAmount;
                missingRecordsErrorMsg.add(errormsg);
                itemsOutOfStock = true;
            }
            
            recordsInShoppingcart.add(r);
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
            RequestDispatcher rd = req.getRequestDispatcher("/orderConfirmCustomerCredentials.jsp");
            rd.forward(req, resp);
        }
        
        
    }
}
