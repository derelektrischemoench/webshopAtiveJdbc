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

public class getShoppingcartContents extends SimpleTagSupport {
    HttpSession session;
    
    @Override
    public void doTag() throws JspException, IOException {
        HttpSession s = this.session;
        final JspWriter out = getJspContext().getOut();
        
        if (s.getAttribute("shoppingCart") != null) {
            try {
                Shoppingcart shopCart = (Shoppingcart) s.getAttribute("shoppingCart");
                Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
                Iterator<Record> rIter = shopCart.getAll(Record.class).iterator();
                
                if (rIter.hasNext()) {
                    while (rIter.hasNext()) {
                        Record r = rIter.next();
                        Artist a = r.parent(Artist.class);
                        out.write("Your cart contains:");
                        out.write(a.getString("artist_name") + " - " + r.getString("title") + "<br />");
                    }
                } else {
                    out.write("Your shoppingcart is empty");
                }
                Base.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //The current user has no shoppingcart in his session:
            out.write("Your shopping cart is empty");
        }
    }
    
    public HttpSession getSession() {
        return session;
    }
    
    public void setSession(HttpSession session) {
        this.session = session;
    }
}
