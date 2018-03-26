package customTags;

import model.Record;
import model.Shoppingcart;
import org.javalite.activejdbc.Base;

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
        
        try {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
            HttpSession s = this.session;
            
            if (s.getAttribute("shoppingCart") != null) {
                Shoppingcart shopCart = (Shoppingcart) s.getAttribute("shoppingCart");
                final JspWriter out = getJspContext().getOut();
                
                Iterator<Record> a = shopCart.getAll(Record.class).iterator();
                while (a.hasNext()) {
                    Record r = a.next();
                    System.out.println(r.getString("title"));
                    out.write(r.getString("title"));
                }
            }
            
        } finally {
            Base.close();
        }
        
        
    }
    
    public HttpSession getSession() {
        return session;
    }
    
    public void setSession(HttpSession session) {
        this.session = session;
    }
}
