/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class UserController{
    private UserView view = null;
    private UsersModel model = null;
    public UserController(UserView view, UsersModel model){
        this.view = view;
        this.model = model;
        view.add_bLoginListener(bLoginListener());
        view.add_bCreateAccountListener(bCreateAccountListener());
        view.show();
    }
    public final ActionListener bLoginListener(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String Username = view.getUsername();
                    String Password = view.getPassword();
                    
                    if(model.loginUser(Username, Password)){
                        //view.showMessage("Đã đăng nhập!");
                        view.hide();
                    }
                    else
                        view.showMessage("Password hoặc Username không đúng!");
                } catch (SQLException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                    view.showMessage("SQLException : "+ex);
                }
            }
        };
    }
    public final ActionListener bCreateAccountListener(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(model.createUser(view.getUsername(), view.getPassword()))
                        view.showMessage("Tài khoản "+view.getUsername()+" đã được tạo!");
                    else
                        view.showMessage("Tài khoản "+view.getUsername()+" không thể tạo!");
                } catch (SQLException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                    view.showMessage("SQLException : "+ex);
                }
            }
        };
    }
}
