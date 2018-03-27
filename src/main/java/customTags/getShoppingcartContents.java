package customTags;

import model.Artist;
import model.Record;
import model.Shoppingcart;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.DB;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class getShoppingcartContents extends SimpleTagSupport{
    HttpSession session;
    
    @Override
    public void doTag() throws JspException, IOException {
        HttpSession s = this.session;
        
        //todo: do this on a separate thread to avoid this connection.base mess
        
        try {
            if (s.getAttribute("shoppingCart") != null) {
                Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
                Shoppingcart shopCart = (Shoppingcart) s.getAttribute("shoppingCart");
                Iterator<Record> rIter = shopCart.getAll(Record.class).iterator();
                
                
                final JspWriter out = getJspContext().getOut();
                
                while (rIter.hasNext()) {
                    Record r = rIter.next();
                    Artist a = r.parent(Artist.class);
                    System.out.println(r.getString("title"));
                    out.write(a.getString("artist_name") + " - " + r.getString("title"));
                }
                
                Base.close();
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error in shoppingcartRecodstag");
        }
    }
    
    public HttpSession getSession() {
        return session;
    }
    
    public void setSession(HttpSession session) {
        this.session = session;
    }
}
