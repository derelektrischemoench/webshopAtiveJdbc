package Controllers;

import com.sun.corba.se.spi.ior.IORTemplate;
import model.Record;
import model.Track;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.bouncycastle.jcajce.provider.symmetric.XSalsa20;
import org.javalite.activejdbc.Base;
import org.javalite.http.FormField;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystem;
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
        
        /*This takes the form field values and assigns them to the crudder object for later record assignment*/
        
        this.servletContext = servletContext;
        this.extractedTextStrings = new ArrayList<>();
        
        for (FileItem f : formData) {
            
            if (f.isFormField()) {
                if (f.getFieldName().equals("createRecord__recordName")) {
                    this.recordName = f.getString("UTF-8");
                }
                if (f.getFieldName().equals("createRecord__recordLabel")) {
                    this.recordLabel = f.getString("UTF-8");
                }
                if (f.getFieldName().equals("createRecord__artistId")) {
                    this.artistId = f.getString("UTF-8");
                    
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
                }
                
                if (f.getFieldName().equals("editRecordId")) {
                    this.recordID = f.getString("UTF-8");
                }
                
                if (f.getFieldName().equals("isEdit")) {
                    String isEditString = f.getString();
                    if (isEditString.equals("true")) {
                        this.isEdit = true;
                    }
                }
            } else {
                this.imageFile = f;
            }
        }
    }
    
    public void createRecord() {
        if (!this.isEdit) {
            //this creates a new record
            System.out.println("crudder creates new record.");
            writeImage(this.imageFile); //writes the image currently present in the crudder to disk and assigns a path
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
            Record  r = Record.create(
                    "artist_id", this.artistId,
                    "title", this.recordName,
                    "label", this.recordLabel,
                    "img_file_path", this.embedUrl,
                    "price", this.recordPrice
            );
            r.saveIt();
            
            Base.close();
            
            //reopen to be able to get the record that was created;
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
            
            try {
                //Record re = Record.findById(this.recordID);
                System.out.println("found record for track insertion: " + r.get("title") + "with id: " + r.get("id"));
                
                
                long id = (long)r.get("id");
                
                for (String s : this.trackList.split(";")) {
                    Track.createIt("name", s, "record_id", id);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error on creating the tracks");
            }
            
            
        } else {
            //this updates an existing record
            System.out.println("Crudder update situation");
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
            Record r = Record.findById(this.recordID);
            Base.close();
            System.out.println("crudder updater record update results: " + r.getString("title")); //FUCKING HELL YEAS OO FTW BIATCH!
            
            //todo: check which fields have changed and modify only those in the db
            //if the image has changed, reupload and adjust url
            writeImage(this.imageFile);
            
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
            
            r.set("artist_id", this.artistId,
                  "title", this.recordName,
                  "label", this.recordLabel,
                  "img_file_path", this.embedUrl,
                  "price", this.recordPrice).saveIt();
            Base.close();
        }
        
    }
    
    public void writeImage(FileItem f) {
        //TODO: somewhere in here is a bug that prvents some images from working e.g. the things falling apart cant
        //TODO: be uploaded..... what the fuck, java
        String filename = f.getName();
        System.out.println("Filename in writeImage " + filename);
        
        //filename = filename.replace(" ", "");
        //filename = filename.replace("-", "");
        
        
        System.out.println("servlet context realpath: " + this.servletContext.getRealPath("uploadFiles/recordImages/"));
        File uploadedImage = new File(this.servletContext.getRealPath(("uploadFiles/recordImages/") + filename));
        if (!uploadedImage.exists()) {
            uploadedImage.mkdir();
        }
        this.embedUrl = "/webapp/uploadFiles/recordImages/" + filename;
        try {
            System.out.println("4");
            f.write(uploadedImage);
        } catch (Exception e) {
            System.out.println("5");
            e.printStackTrace();
            System.out.println("error trying to write image");
        }
        
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
    

