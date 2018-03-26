package Controllers;

import com.sun.corba.se.spi.ior.IORTemplate;
import model.Record;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.javalite.http.FormField;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecordCrud {
    DiskFileItem dfi;
    ArrayList<DiskFileItem> extractedTextStrings;
    List<FileItem> formData;
    ServletContext servletContext;
    String artistName;
    String recordLabel;
    String artistId;
    String recordID;
    String trackList;
    String embedUrl;
    String recordName;
    float recordPrice;
    boolean isEdit;
    
    
    public void separateData() throws UnsupportedEncodingException {
        //Separate form files from text data
        Iterator itemIterator = this.formData.iterator();
        System.out.println("form datda length: " + this.formData.size()); //data is av
        
        while (itemIterator.hasNext()) { //all instances of apache.commons.DiskFileItem
            DiskFileItem dfi = (DiskFileItem) itemIterator.next();
            System.out.println(dfi.isFormField());
            
            if (dfi.isFormField()) {
                System.out.println(dfi.getString("UTF-8"));
                this.extractedTextStrings.add(dfi);
            } else {
                this.dfi = dfi;
            }
        }
    }
    
    
    public RecordCrud(ServletContext servletContext, List<FileItem> formData) throws UnsupportedEncodingException {
        this.servletContext = servletContext;
        this.extractedTextStrings = new ArrayList<>();
        
        for (FileItem f : formData) {
            
            if (f.isFormField()) {
                if (f.getFieldName().equals("createRecord__recordName")) {
                    this.recordName = f.getString("UTF-8");
                    System.out.println("rcordcrud constructor recordname: " + this.recordName);
                }
                if (f.getFieldName().equals("createRecord__recordLabel")) {
                    this.recordLabel = f.getString("UTF-8");
                    System.out.println("recordcrud constructor recordlabel: " + this.recordLabel);
                }
                if (f.getFieldName().equals("createRecord__artistId")) {
                    this.artistId = f.getString("UTF-8");
                    System.out.println("recordcrud constructor artistId: " + this.artistId);
                    
                }
                if (f.getFieldName().equals("createRecord__tracklist")) {
                    this.trackList = f.getString("UTF-8");
                }
                
                if (f.getFieldName().equals("createRecord__price")) {
                    String formContent = f.getString();
                    if (formContent.contains(",")) {
                        formContent = formContent.replace(",", ".");
                    }
                    this.recordPrice = Float.parseFloat(formContent);
                    System.out.println("record price in recordcrudder: " + this.recordPrice);
                }
                
                if (f.getFieldName().equals("editRecordId")) {
                    this.recordID = f.getString("UTF-8");
                    System.out.println("recordcrud contructor recordID: " +  this.recordID);
                }
                
                if (f.getFieldName().equals("isEdit")) {
                    String isEditString = f.getString();
                    if (isEditString.equals("true")) {
                        this.isEdit = true;
                    }
                }
            } else {
                this.writeImage(f);
                System.out.println("crudder wrote image on creation");
            }
        }
    }
    
    
    public void createRecord() {
        Record.createIt(
                "artist_id", this.artistId,
                "title", this.recordName,
                "label", this.recordLabel,
                "img_file_path", this.embedUrl,
                "price", this.recordPrice
        );
    }
    
    public void writeImage(FileItem f) {
        String filename = f.getName();
        System.out.println("Filename in writeImage " + filename);
        System.out.println("servlet context realpath: " + this.servletContext.getRealPath("uploadFiles/recordImages"));
        File uploadedImage = new File(this.servletContext.getRealPath(("uploadFiles/recordImages") + "/" + filename));
        
        this.embedUrl = "/webapp/uploadFiles/recordImages/" + filename;
        System.out.println("wrote this path for the image into crudder " + this.embedUrl );
        
        try {
            f.write(uploadedImage);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error trying to write image");
        }
        
        System.out.println("abspath incl filename" + uploadedImage.getAbsolutePath());
        
    }
    
    
    
    
    public String getArtistName() {
        return artistName;
    }
    
    public String getRecordLabel() {
        return recordLabel;
    }
    
    public String getArtistId() {
        return artistId;
    }
    
    public String getRecordID() {
        return recordID;
    }
    
    public String getTrackList() {
        return trackList;
    }
    
    public String getEmbedUrl() {
        return embedUrl;
    }
    
    public String getRecordName() {
        return recordName;
    }
    
    public float getRecordPrice() {
        return recordPrice;
    }
    
    public boolean isEdit() {
        return isEdit;
    }
    
}
    

