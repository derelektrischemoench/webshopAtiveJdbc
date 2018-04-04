package customTags;

import model.Artist;
import model.Record;
import org.bouncycastle.jce.exception.ExtIOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.rmi.ServerError;
import java.util.Iterator;
import java.util.List;

public class getTenMostRecentRecordsForSlider extends SimpleTagSupport {
    List<Record> records;
    
    @Override
    public void doTag() throws JspException, IOException {
        this.records = Record.findAll().limit(10).orderBy("id desc");
        final JspWriter out = getJspContext().getOut();
        
        Iterator<Record> recIter = this.records.iterator();
        while (recIter.hasNext()) {
            Record record = recIter.next();
            Artist a  = Artist.findById(record.get("artist_id"));
            String artistName = a.getString("artist_name");
            String RecordTitle = record.getString("title");
            
            out.write(
                    "<div class=\"slider-card mdl-card mdl-shadow--2dp\"\n" +
                            "                 style=\"background-image: url('" + record.get("img_file_path") +  "')\">\n" +
                            "                <div class=\"mdl-card__title\">\n" +
                            "                    <h2 class=\"mdl-card__title-text flex-column\">\n" +
                            "                        <span class=\"cardArtist\">\n" +
                                                        artistName +
                            "                        </span>\n" +
                            "                        <br>\n" +
                            "                        <span>"+  RecordTitle  +"</span>\n" +
                            "                    </h2>\n" +
                            "                </div>\n" +
                            "            </div>"
            );
            
        }
    }
    
    
    public List<Record> getRecords() {
        return records;
    }
    
    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
