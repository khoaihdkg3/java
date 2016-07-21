/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ToDoListDatabase {
    private static Database database = Database.getInstance();
    
    private static int countID(int UserID) throws SQLException{
        ResultSet rs = database.Query("select COUNT(ID) from Tasks_ where ID="+UserID+";");
        return rs.getInt("COUNT(ID)");
    }
    private static int countID() throws SQLException{
        ResultSet rs = database.Query("SELECT COUNT(ID) FROM USers;");
        return rs.getInt("COUNT(ID)");
    }
    public static int loginUser(String UName, String PWord) throws SQLException{
            ResultSet rs = database.Query("SELECT * FROM USERS WHERE UName='"+database.calcHashMD5(UName)+
                                                    "' and PWord='"+database.calcHashMD5(PWord)+"'");
            //before if --> rs.next()=true
            if(rs.next()){
            //after if --> rs.next()=false
                return rs.getInt("ID");
            }
            return 0;
    }
    public static boolean createUser(String UName, String PWord) throws SQLException{
        if(UName.length()==0||PWord.length()==0) return false;
        int countID=countID()+1;
        
        return database.Update("INSERT INTO USERS (ID,UName,PWord) values ("+countID+",'"+
                                                                database.calcHashMD5(UName)+"','"+
                                                                database.calcHashMD5(PWord)+"');");
    }
    public static boolean editUser(int UserID, String PWord){
        if(UserID==0) return false;
        return database.Update("UPDATE USERS SET PWord='"+
                database.calcHashMD5(PWord)+
                "' WHERE ID="+UserID+";");
    }
    public static boolean editTask(int UserID, 
            int offset, 
            String newTask, 
            String newDetails, 
            String newDate){
        ResultSet rs = database.Query("select * from Tasks_ where ID="+
                UserID+" limit 1 offset "+
                offset+";");
        
    }
    public static String[][] getTasks(int UserID){
        try {
            int i = 0;
            int taskCount = countID(UserID);
            String[][] result = new String[taskCount][4];
            ResultSet rs = database.Query("select * from Tasks_ where ID="+UserID+";");
            while(rs.next()){
                //Test get Task from database
                /*
                System.out.println("Task : "+rs.getString("Task"));
                System.out.println("Details : "+rs.getString("Details"));
                System.out.println("Date : "+rs.getString("Date"));
                */
                result[i][0] = Integer.toString(i+1);
                result[i][1] = rs.getString("Task");
                result[i][2] = rs.getString("Details");
                result[i][3] = rs.getString("Date");
                i++;
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(ToDoListDatabase.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
