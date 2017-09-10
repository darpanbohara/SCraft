/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//create a database, named userDatabase, a table named users
package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author sadievrenseker
 */
public class DBAbstraction {
    Connection conn;
    String url="jdbc:derby://localhost:1527/userDatabase";
    String uname ="a";
    String passwd = "1";
    void connect(){
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(url,uname,passwd);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    boolean userCheck(String userName, String password){
        connect();
        try {
            Statement stmt = conn.createStatement();
            String query = "select password from users where userName='"+
                    userName+"'";

            
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            return rs.getString(1).equals(password);
          /*  while(rs.next()){
                System.out.println(""+ rs.getString(1) + " ***** "+ rs.getString(2));
            }*/
        }catch (Exception e){
           // e.printStackTrace();
            return false;
        }
    }
     public ArrayList<User> listUser(){
        connect();
        try {
            Statement stmt = conn.createStatement();
            String query = "select * from users";
            ResultSet rs = stmt.executeQuery(query);
            //rs.next();
            ArrayList<User> al = new ArrayList<User>();
            while(rs.next()){
                //System.out.println(""+ rs.getString(1) + " ***** "+ rs.getString(2));
                al.add(new User(rs.getString(1),rs.getString(2), rs.getString(3))); //get the bio printed
            }
            return al;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public void delete(String userName){
        connect();
        try {
            Statement stmt = conn.createStatement(); 
            String query = "delete from users where userName='"+
                    userName+"'";
            System.out.println(query);
            stmt.execute(query);
        }catch (Exception e){
           e.printStackTrace();
            
        }
    }
    public void insert(String newUserName,String newPasswd){
        connect();
        try {
            Statement stmt = conn.createStatement();
            String query = "insert into users(userName,Password) values "
                    + " ('"+newUserName + "','"+newPasswd+"')";
              
            System.out.println(query);
            stmt.execute(query);
        }catch (Exception e){
           e.printStackTrace();
            
        } 
            
    }
    public void modify(String username,String newPasswd){
        connect();
        try {
            Statement stmt = conn.createStatement();
            String query = "update users set password = '"+newPasswd+"' where"
                    + " username='"+username+"'";
              
            System.out.println(query);
            stmt.execute(query);
        }catch (Exception e){
           e.printStackTrace();
            
        } 
            
    }
    public static void main(String args[]){
        DBAbstraction dba = new DBAbstraction();
        dba.connect();
        System.out.println(dba.userCheck("", "1234"));
        dba.delete("xyz");
        dba.modify("pgarcia", "000000");
    }
    
}
