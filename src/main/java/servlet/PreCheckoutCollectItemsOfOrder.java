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
        HttpSession s = req.getSession();
        Shoppingcart shoppingcart = (Shoppingcart) s.getAttribute("shoppingCart");
        
        //create iterable data structure of records and amounts available
        //first element = record instance, second elemt (inner hashmap = amount required, amount available)
        HashMap<Record, HashMap<Integer, Integer>> recordsReqAv = new HashMap<>();
        
        while (recordIdsIter.hasNext()) {
            String recId = recordIdsIter.next();
            Record r = Record.findById(recId);
            int amountRequested = Integer.parseInt(amounts.get(counter));
            int amountAvailable = r.getInteger("amount_in_stock");
            
            HashMap<Integer, Integer> demandSupply = new HashMap<>();
            demandSupply.put(amountRequested, amountAvailable); //inner hashmap
            
            //populate final hashmap:
            recordsReqAv.put(r, demandSupply);    //Hashmap<Record, Hashmap<demand, supply>>
            
            
            //validate:
            for (Map.Entry<Record, HashMap<Integer, Integer>> entry : recordsReqAv.entrySet()) {
                Record recInCart = entry.getKey(); //k
                HashMap<Integer, Integer> demandSuppl = entry.getValue();//v
                System.out.println("Record: " + recInCart.getString("title"));
                System.out.println("demand supply");
                
                for (Map.Entry<Integer, Integer> innerShit : demandSuppl.entrySet()) {
                    int demand = innerShit.getKey();
                    int supply = innerShit.getValue();
                    System.out.println(" demand: " + demand + " supply: " + supply);
                    
                    if (demand > supply) {
                        String errorMessage = "You are requesting too much of " + r.getString("title");
                        missingRecordsErrorMsg.add(errorMessage);
                        itemsOutOfStock = true;
                    } else {
                        //todo:populate all the other shit and shit
                    }
                    
                }
                
            }
            
            counter++;
        }
        
        
        //adjust stock levels
        /*while (recordIdsIter.hasNext()) {
            /*String recordId = recordIdsIter.next();
            Record r = Record.findById(recordId);*/
        /*    int oldStockAmount = r.getInteger("amount_in_stock");
            int amount = Integer.parseInt(amounts.get(counter));
            int newStockAmount = oldStockAmount - amount;
            
            if (newStockAmount < 0) {
                System.out.println("not enough items in stock");
                String errormsg = "Not enough items of " + r.getString("title") + " in stock. Amount left: " + oldStockAmount;
                missingRecordsErrorMsg.add(errormsg);
                itemsOutOfStock = true;
            }
            
            recordsInShoppingcart.put(r, amount); //record-amount pairing
            //counter++;
        }*/
        
        //error, insufficient items in stock  render error message again
        if (itemsOutOfStock) {
            System.out.println("items out of stock, error");
            String queryString = "?shoppingCartId=" + shoppingcart.getInteger("id");
            
            s.setAttribute("missingRecordsErrorMsg", missingRecordsErrorMsg); //is an ArrayList
            
            String redirectString = req.getRequestURI() + queryString;
            System.out.println("redirecting to: " + redirectString);
            resp.sendRedirect("/webapp/shoppingCartDetail" + queryString);
            itemsOutOfStock = false;
            
        } else {
            //all items are in stock
            //SUCCESS
            System.out.println("all items are in stock");
            int shoppingCartId = shoppingcart.getInteger("id");
            
            for (Map.Entry<Record, Integer> entry : recordsInShoppingcart.entrySet()) {
                Record rec = entry.getKey();
                int recordID = rec.getInteger("id");
                int amount = entry.getValue();
                //update the number of records in the join table
                List<RecordsShoppingcarts> target = RecordsShoppingcarts.where(
                        "shoppingcart_id = ? and record_id=? ", shoppingCartId, recordID
                );
                
                RecordsShoppingcarts targetTuple = target.get(0);
                targetTuple.set("record_amount", amount).saveIt();
                
                //reduce stock amount:
                int amountInStock = rec.getInteger("amount_in_stock");
                
                rec.set("amount_in_stock", amountInStock - amount).saveIt();
                
            }
            
            RequestDispatcher rd = req.getRequestDispatcher("/orderConfirmCustomerCredentials.jsp");
            rd.forward(req, resp);
            
        }
        
    }
}
