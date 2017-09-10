/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author sadievrenseker
 */
public class User {
    String username;
    String password;
    String bio;
    public User (String username,String password, String bio){
        this.username= username;
        this.password = password;
        this.bio= bio;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    public String toString(){
        return "<tr><td><img src=/SCraft/data/"+username+".jpg></td>"
                + "<td> "+ username+"</td> <td bgcolor='red'> "+ password + "</td><td>" + bio 
                +"</td><td><a href='response.jsp?op=delete&uname="+username+"'> Delete</a>"
                + " </td><td><a href='response.jsp?op=modify&uname="+username+
                "&pwd="+password+"'> <img src=edit.png width=50 height=50> </a> </td><td>"
                + "<a href='updateImage.jsp?username="+username+"'>modify image</a></td></tr>";
    }
    
}
