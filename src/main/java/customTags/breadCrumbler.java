package customTags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class breadCrumbler extends SimpleTagSupport {
    /*This shall render breadcrumbs*/
    String requestPath;
    
    @Override
    public void doTag() throws JspException, IOException {
        System.out.println("Breadcrumbler path : " + this.requestPath);
        String[] pathParts = this.requestPath.split("/");
        ArrayList<String> pathPartsArrayList = new ArrayList<>(Arrays.asList(pathParts));
        
        //truncate the first four elements from the list since they are garbage
        
        JspWriter out = getJspContext().getOut();
        Iterator<String> pathPartIter = pathPartsArrayList.iterator();
        
        int counter = 1;
        
        while (pathPartIter.hasNext()) {
            String pathPart = pathPartIter.next();
            String reqPiece = pathPartsArrayList.subList(0,counter).toString();
            System.out.println("reqpiece: " + reqPiece);
            
            String modified = reqPiece.replace(",", "/")
                                      .replace(" ", "")
                                      .replace("[", "")
                                      .replace("]", "");
            System.out.println("modified: " + modified);
            
            out.write("<a href='" + modified + "'>' "+modified+" '</a>");
            counter++;
        }
        
    }
    
    public String getRequestPath() {
        return requestPath;
    }
    
    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }
}
