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
    boolean isEdit = false;
    FileItem imageFile;
    
    
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
                    System.out.println("recordcrud contructor recordID: " + this.recordID);
                }
                
                if (f.getFieldName().equals("isEdit")) {
                    String isEditString = f.getString();
                    if (isEditString.equals("true")) {
                        this.isEdit = true;
                    }
                }
            } else {
                System.out.println("assigned image file item to crudder");
                this.imageFile = f;
                System.out.println("crudder wrote image on creation");
            }
        }
    }
    
    
    public void createRecord() {
        if (!this.isEdit) {
            
            System.out.println("crudder creates new record.");
            writeImage(this.imageFile); //writes the image currently present in the crudder to disk and assigns a path
            
            Record.createIt(
                    "artist_id", this.artistId,
                    "title", this.recordName,
                    "label", this.recordLabel,
                    "img_file_path", this.embedUrl,
                    "price", this.recordPrice
            );
        } else {
            System.out.println("Crudder update situation");
            Record r = Record.findById(this.recordID);
            System.out.println("crudder updater record update results: " + r.getString("title")); //FUCKING HELL YEAS OO FTW BIATCH!
            
            //todo: check which fields have changed and modify only those in the db
            //if the image has changed, reupload and adjust url
            writeImage(this.imageFile);
            
            
            r.set("artist_id", this.artistId,
                  "title", this.recordName,
                  "label", this.recordLabel,
                  "img_file_path", this.embedUrl,
                  "price", this.recordPrice).saveIt();
            
        }
        
    }
    
    public void writeImage(FileItem f) {
        String filename = f.getName();
        System.out.println("Filename in writeImage " + filename);
        System.out.println("servlet context realpath: " + this.servletContext.getRealPath("uploadFiles/recordImages"));
        File uploadedImage = new File(this.servletContext.getRealPath(("uploadFiles/recordImages") + "/" + filename));
        
        this.embedUrl = "/webapp/uploadFiles/recordImages/" + filename;
        System.out.println("wrote this path for the image into crudder " + this.embedUrl);
        
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
    

