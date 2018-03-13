package customTags;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class getArtistForRecord extends SimpleTagSupport {
    String inputArtist = "";
    
    public void doTag(String inputArtist) throws JspException, IOException {
        //TODO: as soon as we pass a string here the tag fucks up
        System.out.println("tag called with " + inputArtist);
        //System.out.println("asdasd with: " + inputArtist);
        final JspWriter out = getJspContext().getOut();
        out.println("custom tag yey");
        
    }
    
    public void setInputArtist(String artistName) {
        this.inputArtist = artistName;
    }
    
    public String getInputArtist() {
        return inputArtist;
    }
}
