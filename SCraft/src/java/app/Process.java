/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.ArrayList;

/**
 *
 * @author sadievrenseker
 */
public class Process {
    String username;
    String passwd;
    String op;
    String uname;
    String newUserName;
    String newUserPasswd;
    String pwd;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNewUserName() {
        return newUserName;
    }

    public void setNewUserName(String newUserName) {
        this.newUserName = newUserName;
    }

    public String getNewPasswd() {
        return newUserPasswd;
    }

    public void setNewUserPasswd(String newUserPasswd) {
        this.newUserPasswd = newUserPasswd;
    }


    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public boolean checkLogin(){
        DBAbstraction dba = new DBAbstraction();
        return dba.userCheck(username, passwd);
    }
    public ArrayList<User>  getListofUsers(){
        DBAbstraction dba = new DBAbstraction();
        return dba.listUser();
    }
    public void delete(){
       // System.out.println("passed delete in app layer");
        DBAbstraction dba = new DBAbstraction();
        dba.delete(uname);
    }
    
    public void insert(){
        DBAbstraction dba = new DBAbstraction();
        dba.insert(newUserName,newUserPasswd);
    }
    
    public void modify(){
        DBAbstraction dba = new DBAbstraction();
        dba.modify(uname,newUserPasswd);
    }
     
    
}
