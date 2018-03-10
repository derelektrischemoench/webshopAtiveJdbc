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
import javax.servlet.http.Part;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//TODO: NOTE: files are save in domains/domain1/generated/jsp/webshop

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class AddRecord extends HttpServlet {
    private static final String DATA_DIRECTORY = "uploadFiles/recordImages";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;
    private static final String DEBUGTAG = "CHRISDEBUG ";
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doget addrecord");
        RequestDispatcher rd = req.getRequestDispatcher("/addRecord.jsp");
        rd.forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        
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
        
        // constructs the folder where uploaded file will be stored
        String uploadFolder = getServletContext().getRealPath("") + File.separator + DATA_DIRECTORY;
        System.out.println(DEBUGTAG + "getservletcontext.getrealpath : " + getServletContext().getRealPath(""));
        System.out.println(DEBUGTAG + "location of upload folder: " + uploadFolder);
        System.out.println(DEBUGTAG + "location of data dir uploadFiles/artistImages (FINAL): " + DATA_DIRECTORY);
        System.out.println(DEBUGTAG + "other available dirs (contextpath: " + getServletContext().getContextPath() );
        
        
        
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        // Set overall request size constraint
        upload.setSizeMax(MAX_REQUEST_SIZE);
        String imageFilePath = "";
        
        //initialize the string values for the record properties
        String artistAlias = "";
        String recordName = "";
        String recordLabel = "";
        
        
        try {
            // Parse the request
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
           
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (item.isFormField()) {
                    System.out.println(item.getFieldName() + " " + item.getString());
                    
                    String fieldname = item.getFieldName();
                    
                    if (fieldname.equals("createRecord__artistName")) {
                        artistAlias = item.getString();
                    } else if (fieldname.equals("createRecord__recordName")) {
                        recordName = item.getString();
                    } else if (fieldname.equals("createRecord__recordLabel")) {
                        recordLabel = item.getString();
                    }
                }
                
                if (!item.isFormField()) {
                    //the input here is a file and not a reqular form field
                    
                    String fileName = new File(item.getName()).getName();
                    String filenameSpacesTruncated = fileName.replace(" ", "");
                    String filePath = uploadFolder + File.separator + filenameSpacesTruncated;
                    File uploadedFile = new File(filePath);
                    System.out.println(DEBUGTAG + "Filepath of uploaded file: " + filePath);
                    // saves the file to upload directory
                    item.write(uploadedFile);
                    imageFilePath = uploadedFile.getPath();
                    //DATA_DIRECTORY = uploadFiles/artistImages
                    String embedurl = request.getContextPath() + File.separator + DATA_DIRECTORY + File.separator + fileName;
                    System.out.println(DEBUGTAG + "final constructed embed path: " + embedurl);
                }
            }
            
            if (imageFilePath.length() < 1) {
                throw new Exception("something went wrong, the imagepath is na");
            }
            
            //open db connection
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
            
            Record.createIt("title", recordName,
                            "label", recordLabel,
                            "img_file_path", imageFilePath);
            
            Base.close();
            request.setAttribute("recordName", recordName);
            System.out.println("added record with : " + artistAlias + " " + recordLabel + " " + recordName);
            
            // displays done.jsp page after upload finished
            getServletContext().getRequestDispatcher("/done.jsp").forward(
                    request, resp);
            
        } catch (FileUploadException ex) {
            throw new ServletException(ex);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
