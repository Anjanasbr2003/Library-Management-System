package com.uicomponents;

import com.dbconnection.Dbconnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Userui {

    private JLabel userID;
    private JButton logOutButton;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JPanel userUi;
    private JTable AllBooksTable;
    private JButton showAllBooksAvailableButton;

    Userui() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("User UI");
        frame.add(userUi);


        showAllBooksAvailableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) AllBooksTable.getModel();
                model.setRowCount(0);
                model.setColumnIdentifiers(new Object[]{"ID", "Book Name", "Author", "Books Left"});

                String sql = "select * from Book where count>0";

                try {
                    PreparedStatement ps = Dbconnection.dbconnection().prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        model.addRow(new Object[]{rs.getString("B_id"), rs.getString("B_name"), rs.getString("Author"),rs.getString("count")});
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LoginUI().setVisible(true);
            }
        });
    }

    public void setUserID(String  userID) {
        this.userID.setText(userID);
    }

    static void main(String[] args) {
        new Userui();
    }
}
//User Ge Reservations hadana thana nawaththuwe