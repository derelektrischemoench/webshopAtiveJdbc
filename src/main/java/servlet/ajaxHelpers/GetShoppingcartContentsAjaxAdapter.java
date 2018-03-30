package servlet.ajaxHelpers;

import model.Artist;
import model.Record;
import model.Shoppingcart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetShoppingcartContentsAjaxAdapter extends HttpServlet {
    
    /*this shall look up a shoppingcart, get its contents and deliever them to an ajax function rendering them live
    * into the shoppingcart*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        int shoppingCartId = Integer.parseInt(request.getParameter("shoppingCartId"));
        
        String uri = request.getScheme() + "://" +
             request.getServerName() +
             ("http".equals(request.getScheme()) && request.getServerPort() == 80 || "https".equals(request.getScheme()) && request.getServerPort() == 443 ? "" : ":" + request.getServerPort() ) +
             request.getRequestURI() +
            (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        
    
        Shoppingcart s = Shoppingcart.findById(shoppingCartId);
        if(s!=null) {
            System.out.println("found shoppingcart: " + s.getId());
        }
        
        //get shoppingcartcontents:
        List<Record> recordsInShoppingcart = s.getAll(Record.class).include(Artist.class);
        StringBuilder recordsAsJson = new StringBuilder();
        
        for(Record r : recordsInShoppingcart) {
            String objectAsJson = r.toJson(true, "title", "price");
            recordsAsJson.append(objectAsJson);
        }
        
        //String jsonBegin = "data:"
    
        String recordsAsJsonString = recordsAsJson.toString();
        
        //returnContents(recordsAsJson.toString());
        PrintWriter out = resp.getWriter();
        out.write(recordsAsJsonString);
        
    }
  
    public GetShoppingcartContentsAjaxAdapter() {
        super();
    }
}
