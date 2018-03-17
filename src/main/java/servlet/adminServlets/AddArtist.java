package servlet.adminServlets;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Artist;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.javalite.activejdbc.Base;


public class AddArtist extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private static final String DATA_DIRECTORY = "uploadFiles/artistImages";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;
    private static final String DEBUGTAG = "CHRISDEBUG ";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/addArtist.jsp");
        rd.forward(req, resp);
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
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
        
        //initialize the string values for the artist proerties
        String artistAlias = "";
        String artistFirstName = "";
        String artistLastName = "";
        String artistLabel = "";
        
        try {
            // Parse the request
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
           
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (item.isFormField()) {
                    
                    
                    String fieldname = item.getFieldName();
                    
                    if (fieldname.equals("artistAlias")) {
                        artistAlias = item.getString();
                    } else if (fieldname.equals("artistFirstname")) {
                        artistFirstName = item.getString();
                    } else if (fieldname.equals("artistLastname")) {
                        artistLastName = item.getString();
                    } else if (fieldname.equals("artistLabel")) {
                        artistLabel = item.getString();
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
    
            System.out.println("creating artist: alias: " + artistAlias + " first_name: " + artistFirstName + " last name: " + artistLastName + " label: " + artistLabel);
            
            Artist.createIt("artist_name", artistAlias,
                            "first_name", artistFirstName,
                            "last_name", artistLastName,
                            "label", "placeholder in artistCreateIt in AddArtist",
                            "artist_img_path", imageFilePath);
            
            Base.close();
            request.setAttribute("artistName", artistAlias);
            
            // displays done.jsp page after upload finished
            getServletContext().getRequestDispatcher("/done.jsp").forward(
                    request, response);
            
        } catch (FileUploadException ex) {
            throw new ServletException(ex);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
        
    }
    
}