/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Database {
    private Connection c = null;
    private Statement stmt = null;
    private MessageDigest md = null;
    private static Database Instance;
    
    private void inits(){
        try {
            md = MessageDigest.getInstance("MD5");
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:todolist.s3db");
            stmt = c.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public static Database getInstance(){
        if(Instance==null){
            Instance=new Database();
            Instance.inits();
        }
         return Instance;
     }
     public ResultSet Query(String sql){
         System.out.println("Query: "+sql);
        try {
            return stmt.executeQuery(sql);
        } catch (Exception ex) {
            return null;
        }
         
     }
     public boolean Update(String sql){
         System.out.println("Update: "+sql);
        try {
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            return false;
        }
     }
     public String calcHashMD5(String str){
         StringBuffer sb = new StringBuffer();
         for(byte b : md.digest(str.getBytes()))
             sb.append(Integer.toString((b&0xff)+0x100,16).substring(1));
         return sb.toString();
     }
     
     public void close() throws SQLException{
         stmt.close();
         c.close();
     }
}
