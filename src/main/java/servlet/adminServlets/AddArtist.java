package servlet.adminServlets;

import model.Artist;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.bouncycastle.util.encoders.Base64;
import org.javalite.activejdbc.Base;
import org.javalite.http.Multipart;
import org.sqlite.util.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

//TODO: NOTE: files are save in domains/domain1/generated/jsp/webshop

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class AddArtist extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/addArtist.jsp");
        rd.forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost in addArtist");
        String artistName = request.getParameter("createArtist__artistName");
        String artistFirstName = request.getParameter("createArtist__artistFirstName");
        String artistLastName = request.getParameter("createArtist__artistLastName");
        //open database connection:
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
        
        try {
            List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            
            Iterator fileItemIterators = multiparts.iterator();
            while (fileItemIterators.hasNext()) {
                Part p = (Part) fileItemIterators.next();
                System.out.println("request item: " + p.getSubmittedFileName());
                System.out.println("request item name: " + p.getName());
            }
            
            Part file = request.getPart("createArtist__artistImage"); // this is the file
            System.out.println("file attrs: " + file.getSubmittedFileName() + " " + file.getSize());
    
//FFFFUUUUUUCK
            //CREATE ARTIST, save to db, yada yada
            Artist.createIt("artist_name", artistName,
                            "first_name", artistFirstName,
                            "last_name", artistLastName,
                            "label", "placeholder in artistCreateIt in AddArtist",
                            "artist_img_path", "asdasd");
            
            Base.close();
            request.setAttribute("artistName", artistName);
            RequestDispatcher rd = request.getRequestDispatcher("/artistSuccessfullyAdded.jsp");
            rd.forward(request, resp);
            
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        
        
    }
    
    public void saveImage(InputStream input, File file) {
        try {
            
            OutputStream output = null;
            try {
                output = new FileOutputStream(file);
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                try {
                    final byte[] buffer = new byte[1024];
                    int read;
                    
                    while ((read = input.read(buffer)) != -1)
                        output.write(buffer, 0, read);
                    
                    output.flush();
                } finally {
                    output.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
        
        }
    }
    
}

