/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class TasksView extends JFrame{
    private JButton bAdd,bDelete,bEdit,bFilter,bSetting,bLogout;
    private JTextField tFilter;
    private JTable tableData;
    private JPanel pDataButton,pAccount;
    private JScrollPane pDataTable;
    private final String[] ColumnNames = {"#",
                                    "Task",
                                    "Details",
                                    "Date"};
    private final DefaultTableModel defaultTableModel = new DefaultTableModel(ColumnNames, 0);
    public TasksView(){
        inits();
    }
    public final  void inits(){
        
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        addPanel();
        addComponents();

        setLocationRelativeTo(null);
        
    }
    public void addPanel(){
        pDataButton = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        pDataButton.setLocation(5, 5);
        pDataButton.setSize(480,70);
        //pDataButton.setBackground(Color.red);
        add(pDataButton);
        
        pAccount = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        pAccount.setLocation(490,5);
        pAccount.setSize(100, 70);
        //pAccount.setBackground(Color.YELLOW);
        add(pAccount);
        
        pDataTable = new JScrollPane();
        pDataTable.setLocation(5,80);
        
        pDataTable.setSize(585, 290);
        add(pDataTable);
    }
    public void addComponents(){
        //############################################
        bAdd = new JButton("Add task");
        bAdd.setPreferredSize(new Dimension(110, 25));
        
        bEdit = new JButton("Edit task");
        bEdit.setPreferredSize(new Dimension(110, 25));
        
        bDelete = new JButton("Delete Task");
        bDelete.setPreferredSize(new Dimension(110, 25));
        
        bFilter = new JButton("Apply Filter");
        bFilter.setPreferredSize(new Dimension(110, 25));
        
        tFilter = new JTextField();
        tFilter.setPreferredSize(new Dimension(455, 25));
        //add into panel
        pDataButton.add(bAdd);
        pDataButton.add(bEdit);
        pDataButton.add(bDelete);
        pDataButton.add(bFilter);
        pDataButton.add(tFilter);
        
        //############################################
        bSetting = new JButton("Setting");
        bSetting.setPreferredSize(new Dimension(90, 25));
        
        bLogout = new JButton("Logout");
        bLogout.setPreferredSize(new Dimension(90, 25));
        //add into panel
        pAccount.add(bSetting);
        pAccount.add(bLogout);
        
        //############################################
        tableData = new JTable(defaultTableModel){
                @Override
                public boolean isCellEditable(int row,int column){
                return this.getColumnModel().getColumn(column).getHeaderValue() != "#";
                }    
        };
        tableData.setPreferredSize(new Dimension(580, 265));
        tableData.setPreferredScrollableViewportSize(new Dimension(300, 600));
        tableData.getColumnModel().getColumn(2).setPreferredWidth(400);
        //add into panel
        pDataTable.setViewportView(tableData);
    }
    public void add_bLogoutListener(ActionListener listener){
        bLogout.addActionListener(listener);
    }
    public void addDataTable(String[][] data){
        for(String[] row : data)
            defaultTableModel.addRow(row);
    }
    @Override
    public void hide(){
        deleteAllRow();
        this.setVisible(false);
    }
    public void show(String UserUName){
        setTitle(UserUName+" ToDoList");
        setVisible(true);
    }
    public void deleteAllRow(){
        int CountRow = defaultTableModel.getRowCount();
        for(int i=0; i < CountRow; i++)
            defaultTableModel.removeRow(i);
    }
}
