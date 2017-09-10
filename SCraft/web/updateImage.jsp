<%-- 
    Document   : updateImage
    Created on : Dec 7, 2016, 2:00:29 PM
    Author     : dbohara
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    
    
    %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Upload your photo</h1>
        <% out.print(request.getParameter("username")); %>
        <form action="upload" method="post" enctype="multipart/form-data">
    <input type="hidden" name="username" value='<% out.print(request.getParameter("username")); %>'/>
    <input type="file" name="file" />
    <input type="submit" />
</form>

    </body>
</html>
