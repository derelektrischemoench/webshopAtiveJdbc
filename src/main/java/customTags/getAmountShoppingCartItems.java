package customTags;

import model.Record;
import model.Shoppingcart;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class getAmountShoppingCartItems extends SimpleTagSupport {
    int shoppingCartId;
    
    @Override
    public void doTag() throws JspException, IOException {
        
        try {
            Shoppingcart s = Shoppingcart.findById(this.shoppingCartId);
            int numRecords = s.getAll(Record.class).size();
            
            final JspWriter out = getJspContext().getOut();
            out.println(numRecords);
        } catch (NullPointerException e) {
            //TODO: this is probably fishy; atm this is thrown when the admin clicks on artists in his backend and a user has a session this is caused by the getnumrecordsinshoppingcart tag
            e.printStackTrace();
        }
    }
    
    public int getShoppingCartId() {
        return shoppingCartId;
    }
    
    public void setShoppingCartId(int shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }
}
