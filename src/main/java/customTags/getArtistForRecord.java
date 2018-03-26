package customTags;
import model.Artist;
import model.Record;
import org.javalite.activejdbc.Base;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class getArtistForRecord extends SimpleTagSupport {
    String inputArtistId;
    
    public void doTag() throws JspException, IOException {
        //Parameter sind implizit vorhanden können über this.inputArtist accessed werden
        //SET:        this.inputArtist = "daskdja";
        
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
        
        Artist a = Artist.findById(this.inputArtistId);
        final JspWriter out = getJspContext().getOut();
        
        if(a != null) {
            String artistName = a.getString("artist_name");
            out.println(artistName);
        } else {
            out.println("couldnt find artist");
        }
        
        Base.close();
        
    }
    
    public void setInputArtist(String inputArtistId) {
        this.inputArtistId = inputArtistId;
    }
    
    public String getInputRecord() {
        return inputArtistId;
    }
}
