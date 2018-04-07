package customTags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.PrintWriter;

public class germanVatCalc extends SimpleTagSupport {
    float inputAmount;
    
    @Override
    public void doTag() throws JspException, IOException {
        final JspWriter out = getJspContext().getOut();
        float includedTaxes = (this.inputAmount / 100) * 19;
        double includedTaxesTruncated = (double)Math.round(includedTaxes * 100)/100d;
        String thisShitAsString = Double.toString(includedTaxesTruncated);
        out.write(thisShitAsString);
    }
    
    public float getInputAmount() {
        return inputAmount;
    }
    
    public void setInputAmount(float inputAmount) {
        this.inputAmount = inputAmount;
    }
}
