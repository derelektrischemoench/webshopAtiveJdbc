package customTags;

import org.sqlite.util.StringUtils;

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
            pathPart = pathPart.split("\\?")[0];
            String reqPiece = pathPartsArrayList.subList(1, counter).toString();
            
            String modified = reqPiece.replace(",", "/")
                                      .replace(" ", "")
                                      .replace("[", "")
                                      .replace("]", "");
            
            String[] parts = modified.split("/");
            List<String> partsArrayList = new ArrayList<>(Arrays.asList(parts));
            partsArrayList.remove(0);
            
            StringBuilder strbuild = new StringBuilder();
            int size = partsArrayList.size();
            int count=0;
            for (String s : partsArrayList) {
                
                strbuild.append(s);
                count++;
                
                if(count >= size){
                    break; //prevent adding trailing slashes
                }
                strbuild.append("/");
            }
            
            String constructedPath = strbuild.toString();
            out.write("<a href=http://" + constructedPath + "> " + pathPart + " </a><i class=\"material-icons\">keyboard_arrow_right</i>");
            if (counter > pathPartsArrayList.size() - 1) {
                break;
            } else {
                counter++;
            }
        }
        
    }
    
    public String getRequestPath() {
        return requestPath;
    }
    
    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }
}
