<%-- 
    Document   : response
    Created on : Oct 6, 2016, 1:25:24 PM
    Author     : sadievrenseker
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body bgcolor="DarkSeaGreen">
        <h1>SCraft</h1>
        <jsp:useBean id="userControl" scope="session" class="app.Process" />
        <jsp:setProperty name="userControl" property="username" />
        <jsp:setProperty name="userControl" property="passwd"  />
        <jsp:setProperty name="userControl" property="op"  />
        <jsp:setProperty name="userControl" property="uname"  />
        <jsp:setProperty name="userControl" property="newUserName"  />
        <jsp:setProperty name="userControl" property="newUserPasswd"  />
        <jsp:setProperty name="userControl" property="pwd"  />
        <%
            if(userControl.checkLogin()){
                if(!userControl.getOp().equals("modify")){
                    %>
                    <form action="response.jsp">      
                    Username: <input type="text" name="uname" width="8"
                                     disabled="disabled" value="<% out.print(userControl.getUname()); %>" readonly>
                    Password: <input type="text" name="newUserPasswd"  width="8"
                                     value="<% out.print(userControl.getPwd()); %>">
                    <input type="modifypassword" value="Save">  
                    <input type="hidden" name="op" value="update" />
                </form>

                <br>
                    <%
                        
                 }       
                   if(userControl.getOp().equals("update")){     
                        File file ;
                        int maxFileSize = 5000 * 1024;
                        int maxMemSize = 5000 * 1024;
                        String filePath = "";

                        String contentType = request.getContentType();
                        System.out.println("passed:"+contentType);
                        if ((contentType.indexOf("multipart/form-data") >= 0)) {
                            try{ 
                           DiskFileItemFactory factory = new DiskFileItemFactory();
                           factory.setSizeThreshold(maxMemSize);
                           factory.setRepository(new File(""));
                           ServletFileUpload upload = new ServletFileUpload(factory);
                            System.out.println("passed3");
                           upload.setSizeMax( maxFileSize );
                            System.out.println("passed4");
                           
                              List fileItems = upload.parseRequest(request);
                              Iterator i = fileItems.iterator();
                             System.out.println("passed");
                                System.out.println("passed5");
                              while ( i.hasNext () ) 
                              {
                                 FileItem fi = (FileItem)i.next();
                                 if ( !fi.isFormField () )  {
                                     String fieldName = fi.getFieldName();
                                     String fileName = fi.getName();
                                     boolean isInMemory = fi.isInMemory();
                                     long sizeInBytes = fi.getSize();
                                     file = new File( filePath + "yourFileName") ;
                                     fi.write( file ) ;
                                     out.println("Uploaded Filename: " + filePath + fileName + "<br>");
                                 }
                              }
                             
                           }catch(Exception ex) {
                              System.out.println(ex);
                           }
                        }else{
               
                           out.println("<p>No file uploaded</p>"); 
                          
                        }
                        
                        
                        
                        
                        userControl.modify();
                }
                if(userControl.getOp().equals("insert")){
                       userControl.insert();
                }
                if(userControl.getOp().equals("delete")){
                        userControl.delete();
                        
                 }
                if(userControl.getOp().equals("modify")){
                    %>
                    <form action="response.jsp" method="post" enctype="multipart/form-data" >
                       Uname: <input type="text" name="newUserName" width="8"
                                     disabled="disabled" value="<% out.print(userControl.getUname()); %>">
                       Passwd: <input type="text" name="newUserPasswd"  width="8"
                                      value="<% out.print(userControl.getPwd()); %>">
                       <input type="hidden" name="op" value="update" >
                        <input type="file" name="file" size="50" />
                        <input type="submit" value="Modify">

                    </form>
                    <br>
                    <%
                        //userControl.modify();
                 }
               java.util.ArrayList<app.User> al= userControl.getListofUsers();
               out.print("<table border=1><tr><th>User Name</th><th>Password</th><th>Bio</th></tr>");
               for(int i=0;i<al.size();i++){
                    out.print(al.get(i)+"");
               }
               out.print("</table>");
                
            }else{
                out.print("Login Failure");
            }       
        %>
    </body>
</html>
