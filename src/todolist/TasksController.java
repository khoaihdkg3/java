/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Administrator
 */
public class TasksController {
    private TasksModel model = null;
    private TasksView view = null;
    public TasksController(TasksView view, TasksModel model){
        this.view = view;
        this.model = model;
        
        view.add_bLogoutListener(bLogoutListener());
    }
    
    public final ActionListener bLogoutListener(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        };
    }
}
