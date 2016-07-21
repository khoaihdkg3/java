/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

/**
 *
 * @author Administrator
 */
public class UserView extends JFrame{
    private JButton bLogin,bCreateAccount;
    private JLabel lUser,lPass;
    private JTextField tUser;
    private JPasswordField tPass;
    private JCheckBox cbRememberLogin;
    private JPanel pLogin;
    boolean isRemember = false;
    public UserView(){
        inits();
    }
    private void inits(){
        setTitle("Login ToDoList");
        setSize(230, 160);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        addPanel();
        addComponents();     
        
        setLocationRelativeTo(null);
        
    }
    private void addPanel(){
        pLogin = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        pLogin.setLocation(5,5);
        pLogin.setSize(215, 120);
        //pLogin.setBackground(Color.red);
        
        add(pLogin);
    }
    private void addComponents(){
       lUser = new JLabel("Username");
       lUser.setPreferredSize(new Dimension(60, 25));
       tUser = new JTextField();
       tUser.setPreferredSize(new Dimension(120, 25));
       
       lPass = new JLabel("Password");
       lPass.setPreferredSize(new Dimension(60, 25));
       tPass = new JPasswordField();
       tPass.setPreferredSize(new Dimension(120, 25));
       
       bLogin = new JButton("Login");
       bCreateAccount = new JButton("Create");
       
       cbRememberLogin = new JCheckBox("Remember");
       cbRememberLogin.addItemListener(new ItemListener() {
           @Override
           public void itemStateChanged(ItemEvent e) {
               isRemember = e.getStateChange()==1;
               System.out.println("isRemember: "+isRemember);
           }
       });
       pLogin.add(lUser);
       pLogin.add(tUser);
       pLogin.add(lPass);
       pLogin.add(tPass);
       pLogin.add(bLogin);
       pLogin.add(bCreateAccount);
       pLogin.add(cbRememberLogin);
       
       
    }
    public void add_bLoginListener(ActionListener listener){
        bLogin.addActionListener(listener);
    }
    public void add_bCreateAccountListener(ActionListener listener){
        bCreateAccount.addActionListener(listener);
    }
    @Override
    public void show(){
        setVisible(true);
    }
    @Override
    public void hide(){
        setVisible(false);
    }
    public boolean isRemember(){
        return isRemember;
    }
    public String getUsername(){
        return tUser.getText();
    }
    public String getPassword(){
        return new String(tPass.getPassword());
    }
    public void showMessage(String message){
        JOptionPane.showConfirmDialog(this, message,"Login ToDoList",JOptionPane.CLOSED_OPTION);
    }
}
