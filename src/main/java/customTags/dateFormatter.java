package customTags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.time.*;

public class dateFormatter extends SimpleTagSupport {
    /*this takes a java.time date, cuts of the hours and separates the years months and days*/
    
    
    String dateString;
    
    @Override
    public void doTag() throws JspException, IOException {
        String[] truncatedDate = this.dateString.substring(0,10).split("-");
        
        String year = truncatedDate[0];
        String month = truncatedDate[1];
        String day = truncatedDate[2];
        
        if(month.startsWith("0")) {
            month = month.replace("0", "");
        }
        
        if(day.startsWith("0")) {
            day = day.replace("0", "");
        }
        
        
        final JspWriter out = getJspContext().getOut();
        
        out.write(day + "." + month + "." + year);
    }
    
    public String getDateString() {
        return dateString;
    }
    
    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
}
