package servlet;

import model.Artist;
import org.javalite.activejdbc.Base;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        String artistName = request.getParameter("createArtist__artistName");
        String artistFirstName = request.getParameter("createArtist__artistFirstName");
        String artistLastName = request.getParameter("createArtist__artistLastName");
        
        //open database connection:
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
        
        String savePath = "/";
        //IMAGE SHIT
        //create fileDir if !exists:
        File uploadedImage = new File(savePath);
        /*if (!uploadedImage.exists()) {
            uploadedImage.mkdir();
        }*/
        
        String filename = null;
        
        //we can get the single image like so:
        //todo: optimize this below. by getting only the image part of the request. right now it contains the empty form fields as well and creates an empty file for them
       
        for (Part part : request.getParts()) {
            //also add an upload timestamp:
            System.out.println("part.getName" + part.getName()); //form field name
            System.out.println("part.getSubmittedFilename" + part.getSubmittedFileName()); //file name
            String timestamp =  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            filename = timestamp + getFileName(part);
            
            //check which part is named like the image and thenwrite this
            //apparently the image cant be written without the other part of the request for fucks sake
            part.write(filename);
    
            System.out.println("getNamecreateArtist__artistImage content " + part.getName());
            
        }
        
        //TODO: this should be the absolute path to the image
        System.out.println("url of thess image: " + uploadedImage.getAbsolutePath() + filename);
        String fileLocationForDB = uploadedImage.getAbsolutePath() + filename;
        
        //CREATE ARTIST
        Artist.createIt("artist_name", artistName,
                        "first_name", artistFirstName,
                        "last_name", artistLastName,
                        "label", "placeholder in artistCreateIt in AddArtist",
                        "artist_img_path", fileLocationForDB);
        System.out.println("artist object created");
        
        Base.close();
        System.out.println("database connection closed");
        
        
        
    }
    
    private String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim()
                         .replace("\"", "").replace(" ", "");
            }
        }
        return null;
    }
}

