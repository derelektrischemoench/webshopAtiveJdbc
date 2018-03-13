package customTags;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class getArtistForRecord extends SimpleTagSupport {
    private String inputArtist;
    
    public void doTag() throws JspException, IOException {
        System.out.println("tag called....");
        final JspWriter out = getJspContext().getOut();
        out.println("custom tag yey");
        
    }
    
    
   public String getInputArtist() {
        return inputArtist;
    }
    
    public void setInputArtist(String inputArtist) {
        this.inputArtist = inputArtist;
    }
}
