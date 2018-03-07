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
        
        //System.out.println("apppath " + appPath); ///home/chris/IdeaProjects/webshop_javalite/target/webshop
        //System.out.println("savepath " + savePath); ///home/chris/IdeaProjects/webshop_javalite/target/webshop/uploadFiles
        
        //create fileDir if !exists:
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        
        //upload ze file:
        for (Part part : request.getParts()) {
            //also add an upload timestamp:
            Date date = new Date();
            String timestamp =  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String filename = timestamp + getFileName(part);
            System.out.println("timestamp on filename: " + filename);
            part.write(filename);
        }
    }
    
    private String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim()
                         .replace("\"", "").replace(" ", "");
            }
        }
        return "file";
    }
}

