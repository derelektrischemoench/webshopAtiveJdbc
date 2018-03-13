package tags;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.*;
import java.io.IOException;
import java.io.StringWriter;

public class getArtistForRecord extends SimpleTagSupport {
    private String inputArtist;
    
    public void doTag(String arg) throws JspException, IOException {
        JspFragment body = this.getJspBody();
        if(body != null) {
            this.getJspContext().setAttribute(inputArtist, "hello World");
            body.invoke(null);
        }
    }
    
    public String getInputArtist() {
        return inputArtist;
    }
    
    public void setInputArtist(String inputArtist) {
        this.inputArtist = inputArtist;
    }
}
