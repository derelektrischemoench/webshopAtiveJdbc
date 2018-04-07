package customTags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class priceSumFormatter extends SimpleTagSupport {
    float price;
    
    @Override
    public void doTag() throws JspException, IOException {
        System.out.println("dotag in priceSumFormatter");
        final JspWriter out = getJspContext().getOut();
        double retval = (double)Math.round(this.price * 100)/100d;
        String asString = Double.toString(retval);
        out.write(asString);
    }
    
    public float getPrice() {
        return price;
    }
    
    public void setPrice(float price) {
        this.price = price;
    }
}
