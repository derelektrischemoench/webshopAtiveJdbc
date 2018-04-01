package customTags;

import model.Record;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class getRecordNameById extends SimpleTagSupport {
    String inputRecordId;
    
    @Override
    public void doTag() throws JspException, IOException {
        Record r = Record.findById(this.inputRecordId);
        String recordName = r.getString("title");
        final JspWriter out = getJspContext().getOut();
        out.write(recordName);
    }
    
    public String getInputRecordId() {
        return inputRecordId;
    }
    
    public void setInputRecordId(String inputRecordId) {
        this.inputRecordId = inputRecordId;
    }
}
