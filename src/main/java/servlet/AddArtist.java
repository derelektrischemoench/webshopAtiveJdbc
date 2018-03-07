package servlet;

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
        
        String savePath = "/";
      
        //create fileDir if !exists:
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        
        String filename = null;
        
        //we can get the single image like so:
        //todo: optimize this below. by getting only the image part of the request. right now it contains the empty form fields as well and creates an empty file for them
        Part artistImage = request.getPart("createArtist__artistImage");
        
        for (Part part : request.getParts()) {
            //also add an upload timestamp:
            System.out.println("name of the request part: " + part.getName());
            String timestamp =  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            filename = timestamp + getFileName(part);
            part.write(filename);
        }
        
        String file = saveDir.getAbsolutePath();
        System.out.println("savedir for file " + file);
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

