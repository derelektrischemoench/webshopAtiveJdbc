package servlet.ajaxHelpers;

import model.Record;
import model.Shoppingcart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.List;

public class GetShoppingcartContentsAjaxAdapter extends HttpServlet {
    
    /*this shall look up a shoppingcart, get its contents and deliever them to an ajax function rendering them live
    * into the shoppingcart*/
    //TODO: finish this
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        int shoppingCartId = Integer.parseInt(request.getParameter("shoppingCartId"));
        //TODO: this is receiving the wrong shoppingcartid
        System.out.println("doget parsed following id: " + shoppingCartId);
        
        String uri = request.getScheme() + "://" +
             request.getServerName() +
             ("http".equals(request.getScheme()) && request.getServerPort() == 80 || "https".equals(request.getScheme()) && request.getServerPort() == 443 ? "" : ":" + request.getServerPort() ) +
             request.getRequestURI() +
            (request.getQueryString() != null ? "?" + request.getQueryString() : "");
    
        System.out.println("called do get in getshoppingcartajaxhelper");
        System.out.println("uri of the request: " + uri);
    
        Shoppingcart s = Shoppingcart.findById(shoppingCartId);
        System.out.println("found the shoppingcart: " + s.get("id"));
        
        //get shoppingcartcontents:
        List<Record> recordsInShoppingcart = s.getAll(Record.class);
        
        StringBuilder recordsAsString = new StringBuilder();
    
        System.out.println("records in shoppingcart found by adapter: " + recordsInShoppingcart.size());
        for(Record r : recordsInShoppingcart) {
            System.out.println(r.get("title"));
            recordsAsString.append(r.get("title"));
            recordsAsString.append(";");
        }
        
        String records = recordsAsString.toString();
        System.out.println(records);
        
        returnContents(records);
        
        
    }
    
    protected String returnContents (String records) {
        return records;
    }
    
    public GetShoppingcartContentsAjaxAdapter() {
        super();
        System.out.println("constructed new adapter");
    }
}
