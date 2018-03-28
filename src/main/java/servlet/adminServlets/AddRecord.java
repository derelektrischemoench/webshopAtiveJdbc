package servlet.adminServlets;

import Controllers.RecordCrud;
import model.Artist;
import model.Record;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
        //This renders the add record form
        String artistId = req.getParameter("artist_id");
        Artist a = Artist.findFirst("id = ?", artistId);
        req.setAttribute("artist", a);
        RequestDispatcher rd = req.getRequestDispatcher("/addRecord.jsp");
        rd.forward(req, resp);
        
        //TODO: the form rendering of the edit has to be done by a different servlet
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        request.setCharacterEncoding("UTF-8");
        
        if (!isMultipart) {
            return;
        }
        
        //in case this is a new record:
        
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MAX_MEMORY_SIZE);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        try {
            List<FileItem> items = upload.parseRequest(request);
            ServletContext sc = getServletContext();
            //init a crudder, this automatically saves imaegson creation
            RecordCrud crudder = new RecordCrud(sc, items);
            crudder.createRecord();
            
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        
        
    }
}