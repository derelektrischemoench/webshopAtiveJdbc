package servlet.adminServlets;

import model.Artist;
import model.Record;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.javalite.activejdbc.Base;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

import java.util.*;


@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
//TODO: ! IMPORTANT : aparently one can specify the location of the image file here... try its
public class AddRecord extends HttpServlet {
    private static final String DATA_DIRECTORY = "uploadFiles/recordImages";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;
    private static final String DEBUGTAG = "CHRISDEBUG ";
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
        String artistId = req.getParameter("artist_id");
        Artist a = Artist.findFirst("id = ?", artistId);
        req.setAttribute("artist", a);
        RequestDispatcher rd = req.getRequestDispatcher("/addRecord.jsp");
        Base.close();
        rd.forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        request.setCharacterEncoding("UTF-8");
        
        if (!isMultipart) {
            return;
        }
        
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // Sets the size threshold beyond which files are written directly to// disk.
        factory.setSizeThreshold(MAX_MEMORY_SIZE);
        // Sets the directory used to temporarily store files that are larger
        // than the configured size threshold. We use temporary directory for
        // java
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        
        
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        String filename = "";
        //List of file items from the upload:
        String artistName = "";
        String recordName = "";
        String recordLabel = "";
        String artistId = "";
        String trackList = "";
        float recordPrice = 0;
        
        try {
            List<FileItem> items = upload.parseRequest(request);
            
            Iterator ItemIter = items.iterator();
            while (ItemIter.hasNext()) {
                FileItem i = (FileItem) ItemIter.next();
                
                if (i.isFormField()) {
                    if (i.getFieldName().equals("createRecord__recordName")) {
                        recordName = i.getString("UTF-8");
                    }
                    if (i.getFieldName().equals("createRecord__recordLabel")) {
                        recordLabel = i.getString("UTF-8");
                    }
                    if (i.getFieldName().equals("createRecord__artistId")) {
                        artistId = i.getString("UTF-8"); //THIS IS WHY WE CANT HAVE NICE THINGS... fucking ascii
                    }
                    if (i.getFieldName().equals("createRecord__tracklist")) {
                        trackList = i.getString("UTF-8");
                    }
                    
                    if (i.getFieldName().equals("createRecord__price")) {
                        
                        String formContent = i.getString();
                        if (formContent.contains(",")) {
                            formContent = formContent.replace(",", ".");
                        }
                        
                        recordPrice = Float.parseFloat(formContent);
                    }
                    
                    
                } else {
                    //this is the file
                    filename = i.getName();
                    
                    File uploadedFile = new File(getServletContext().getRealPath("uploadFiles/recordImages") + "/" + filename);
                    
                    try {
                        i.write(uploadedFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("error trying to write fule");
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        
        
        String embedurl = "/webapp/uploadFiles/recordImages/" + filename;
        
        try {
            //open db connection
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
            Artist a = Artist.findFirst("id = ?", artistId);
            
            //locate the id of the artist from the database to make the fk
            try {
                Record.createIt("artist_id", artistId,
                                "title", recordName,
                                "label", recordLabel,
                                "img_file_path", embedurl,
                                "price", recordPrice);
                
                
                //create Tracklist:
                List<String> tracklist = Arrays.asList(trackList.split(";"));
                
                Iterator tracklistIterator = tracklist.iterator();
    
                System.out.println("read the following tracks:");
                
                while (tracklistIterator.hasNext()) {
                    // create track objects
                    //TODO: finish this!!!!
                    String track = tracklistIterator.next().toString();
                    System.out.println(track);
                }
                
                
                request.setAttribute("recordName", recordName);
                System.out.println("added record with : " + a.get("artist_name") + " Record price:  " + recordPrice);
                
            } catch (Exception e) {
                //the corresponding artist couldn't be found
                String errormsg = "Artist not found";
                request.setAttribute("errormsg", errormsg);
                e.printStackTrace();
            }
            
            Base.close();
            
            // displays done.jsp page after upload finished
            getServletContext().getRequestDispatcher("/recordSuccessfullyAdded.jsp").forward(request, resp);
            
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
