/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileUploadServlet;



import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException; 
import javax.servlet.annotation.MultipartConfig; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet { 
  
  public FileUploadServlet() { 
    super(); 
  }

  protected void doGet(HttpServletRequest request, 
      HttpServletResponse response) throws ServletException, IOException { 
    for (Part part : request.getParts()) { 
      
      InputStream is = request.getPart(part.getName()).getInputStream(); 
      int i = is.available(); 
      byte[] b  = new byte[i]; 
      is.read(b); 
      //logger.info("Length : " + b.length); 
      String fileName = getFileName(part); 
      String username = request.getParameter("username");
      //logger.info("File name : " + fileName); 
      FileOutputStream os = new FileOutputStream("/Users/dbohara/Desktop/Fall16/AdvancedProgramming/SCraft/build/web/data" + username+".jpg"); 
      os.write(b); 
      is.close(); 
    }
     response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.print("Your image loaded");
    out.print("<a href='response.jsp'>go back</a>");

  }

  private String getFileName(Part part) { 
    String partHeader = part.getHeader("content-disposition"); 
    //logger.info("Part Header = " + partHeader); 
    for (String cd : part.getHeader("content-disposition").split(";")) { 
      if (cd.trim().startsWith("filename")) { 
        return cd.substring(cd.indexOf('=') + 1).trim() 
            .replace("\"", ""); 
      } 
      
    } 
    return null;

  }

  private String getUserName(Part part) { 
    String partHeader = part.getHeader("content-disposition"); 
    //logger.info("Part Header = " + partHeader); 
    for (String cd : part.getHeader("content-disposition").split(";")) { 
        if (cd.trim().startsWith("username")) { 
            return cd.substring(cd.indexOf('=') + 1).trim() 
            .replace("\"", ""); 
      } 
      
    } 
    return null;

  }
  
  protected void doPost(HttpServletRequest request, 
      HttpServletResponse response) throws ServletException, IOException { 
    doGet(request, response); 
  }

}
