/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist;

import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class ToDoListModel {
    private int UserID = 0;
    private String UserUName = null;
    private String UserPWord = null;
    private CipherDES DES = null;
    
    public boolean login(String UserUName, String UserPWord) throws SQLException{
        this.UserID = ToDoListDatabase.loginUser(UserUName, UserPWord);
        if(UserID!=0){
            this.UserUName = UserUName;
            this.UserPWord = UserPWord;
            DES = new CipherDES(UserPWord);
        return true;
        }
        return false;
    }
    public String[][] getTasks(){
        if(UserID==0) return null;
        String[][] tasks = ToDoListDatabase.getTasks(UserID);
        for(int i = 0;i<tasks.length;i++){
            for(int j=0;j<tasks[i].length;j++)
                tasks[i][j]=DES.decrypt(tasks[i][j]);
        }
        return tasks;
    }
    public boolean createUser(String UserUName,String UserPWord) throws SQLException{
        return ToDoListDatabase.createUser(UserUName, UserPWord);
    }
    public boolean editUser(String UserPWord){
        return ToDoListDatabase.editUser(UserID, UserPWord);
    }
    public void logout(){
        UserID=0;
        UserUName=null;
        UserPWord=null;
        DES=null;
    }
}
