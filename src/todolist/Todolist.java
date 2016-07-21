/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;

/**
 *
 * @author Administrator
 */
public class Todolist {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.InvalidKeyException
     * @throws java.security.spec.InvalidKeySpecException
     */
    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
        
        UserView userView = new UserView();
        UsersModel userModel = new UsersModel();
        UserController userController = new UserController(userView,userModel);
        
        TasksView taskView = new TasksView();
        TasksModel taskModel = new TasksModel();
        TasksController tasksController = new TasksController(taskView,taskModel);
        
    }
}
