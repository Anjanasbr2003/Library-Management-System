package com.uicomponents;

import com.dbconnection.DbOperations;
import com.dbconnection.Dbconnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Userui {

    private JLabel userID;
    private JButton logOutButton;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JPanel userUi;
    private JTable AllBooksTable;
    private JButton showAllBooksAvailableButton;
    private JTable ReservationTable;
    private JButton showMyReservationsButton;
    private JTextField AddreservationTextField;
    private JButton AddReservationButton;

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
        showMyReservationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelreservation = (DefaultTableModel) ReservationTable.getModel();
                modelreservation.setRowCount(0);
                modelreservation.setColumnIdentifiers(new Object[]{"ID", "Book Name"});

                String sql = "select reservation.B_id,book.B_name FROM reservation INNER JOIN users ON reservation.U_id=users.U_id INNER JOIN book ON book.B_id=reservation.B_id WHERE users.u_id=?";

                try {
                    PreparedStatement ps = Dbconnection.dbconnection().prepareStatement(sql);
                    ps.setString(1, userID.getText());
                    ResultSet rs = ps.executeQuery();

                    boolean hasData = false;
                    while (rs.next()) {
                        hasData = true;
                        modelreservation.addRow(new  Object[]{rs.getString("B_id"), rs.getString("B_name")});
                    }
                    if (!hasData) {
                        JOptionPane.showMessageDialog(null, "No data found");
                    }

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        AddReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!AddreservationTextField.getText().equals("")) {
                    if(DbOperations.reservationBookIdCheck(AddreservationTextField.getText())) {
                        JOptionPane.showMessageDialog(null, "Book already Reserved");
                    }else if( !(new DbOperations().bookCheck(AddreservationTextField.getText()))){
                        JOptionPane.showMessageDialog(null, "Enter A Existing Book ID This is Not in OUR library.");
                    }

                    else{
                        String sql = "insert into reservation(B_id,U_id) values(?,?)";
                        try {
                            PreparedStatement ps = Dbconnection.dbconnection().prepareStatement(sql);
                            ps.setString(1, AddreservationTextField.getText());
                            ps.setString(2, userID.getText());
                           int affectedrows =  ps.executeUpdate();
                           if (affectedrows > 0) {
                               JOptionPane.showMessageDialog(null, "Book Reserved");
                           }

                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }

                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Please Enter the Book ID..........\nTo get The Book ID Go To All Books Section");
                }



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
