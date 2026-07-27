package com.uicomponents;

import com.dbconnection.DbOperations;
import com.modules.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.dbconnection.Dbconnection.dbconnection;
import static com.modules.Emailcheck.isValidEmail;

public class AdminUi extends JFrame {

    private JLabel adminId;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane3;
    private JTabbedPane tabbedPane4;
    private JPanel adminUiPane;
    private JLabel adminid;
    private JTextField AdduserId;
    private JPasswordField Adduserpassword;
    private JTextField addusername;
    private JComboBox Addusertypecombo;
    private JTextField Addusertelephone;
    private JTextField Adduseremail;
    private JTextField Adduseraddress;
    private JButton addUserButton;
    private JTextField deleteuserid;
    private JButton deleteUserButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JPasswordField passwordField1;
    private JTextField textField4;
    private JPasswordField passwordField2;
    private JTextField textField5;
    private JButton UPDATEButton;
    private JTextField textField6;

    AdminUi() {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(adminUiPane);


        frame.setVisible(true);
        frame.setTitle("Admin UI");

        ImageIcon icon = new ImageIcon("src/main/resources/icon.png");
        frame.setIconImage(icon.getImage());


        Addusertypecombo.addItem("admin");
        Addusertypecombo.addItem("librarian");
        Addusertypecombo.addItem("member");


        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(AdduserId.getText().equals("")||
                        Adduserpassword.getText().equals("")||
                        Addusertelephone.getText().equals("")||
                        Adduseraddress.getText().equals("")||
                        addusername.getText().equals("")||
                        Adduseremail.getText().equals("")||
                        Addusertypecombo.getSelectedItem().equals(""))
                {
                    JOptionPane.showMessageDialog(frame,"<html><body style='color:red'>Fields Are Empty</body></html>","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(!(AdduserId.getText().substring(0,2).equals("AD")||AdduserId.getText().substring(0,2).equals("LB")||AdduserId.getText().substring(0,2).equals("ME"))){
                    JOptionPane.showMessageDialog(frame,"<html><body style='color:red'>User Id Must Contain Prefix 'AD' or 'LB' or 'ME'</body></html>"  ,"Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(AdduserId.getText().length()>5){
                    JOptionPane.showMessageDialog(frame,"<html><body style='color:red'>User Id Must Contain Only 5 characters</body></html>"  ,"Error",JOptionPane.ERROR_MESSAGE);

                }
                else if(Adduserpassword.getText().length()<=8){
                    JOptionPane.showMessageDialog(frame,"<html><body style='color:red'>Password Must Conatin 8 Characters</body></html>","Error",JOptionPane.ERROR_MESSAGE);

                }else if(addusername.getText().length()>100){
                    JOptionPane.showMessageDialog(frame,"<html><body style='color:red'>Username should be less than 100 Characters</body></html>","Error",JOptionPane.ERROR_MESSAGE);

                }else if(Addusertelephone.getText().length()<10||Addusertelephone.getText().length()>10){
                    JOptionPane.showMessageDialog(frame,"<html><body style='color:red'>Mobile number Must Conatin 10 Characters</body></html>","Error",JOptionPane.ERROR_MESSAGE);

                }else if(!(isValidEmail(Adduseremail.getText())) ){
                    JOptionPane.showMessageDialog(frame,"<html><body style='color:red'>Not a valid email address</body></html>","Error",JOptionPane.ERROR_MESSAGE);

                }else if(Adduseraddress.getText().length()>200){
                    JOptionPane.showMessageDialog(frame,"<html><body style='color:red'>Address should less than 200 words</body></html>","Error",JOptionPane.ERROR_MESSAGE);

                }else{
                    User newuser = new User();
                    newuser.setUserid(AdduserId.getText());
                    newuser.setPassword(Adduserpassword.getText());
                    newuser.setPhonenumber(Addusertelephone.getText());
                    newuser.setAddress(Adduseraddress.getText());
                    newuser.setEmail(Adduseremail.getText());
                    newuser.setUsername(addusername.getText());
                    newuser.setUsertype(Addusertypecombo.getSelectedItem().toString());

                    try {
                        Connection conn=dbconnection();
                        String sql ="INSERT INTO users VALUES (?,?,?,?,?,?,?)";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, newuser.getUserid());
                        ps.setString(2, newuser.getUsername());
                        ps.setString(3,newuser.getUsertype());
                        ps.setString(4,newuser.getPassword());
                        ps.setString(5,newuser.getPhonenumber());
                        ps.setString(6,newuser.getEmail());
                        ps.setString(7,newuser.getAddress());

                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(frame,"User Added Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("User created");


                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }

                }
            }
        });
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String duid=deleteuserid.getText();

                if(duid.equals("")){
                    JOptionPane.showMessageDialog(frame,"Enter userid","Error",JOptionPane.ERROR_MESSAGE);
                }else if(new DbOperations().userExistionCheckAndDeletion(duid)){
                    JOptionPane.showMessageDialog(frame,"User Deleted Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(frame,"User Not Found","Error",JOptionPane.ERROR_MESSAGE);
                }


            }
        });
        AdduserId.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                String prefix = null;


                try {
                    prefix = AdduserId.getText().substring(0,2);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }


                try {
                    switch(prefix){
                        case "AD":
                            Addusertypecombo.setSelectedIndex(0);
                            break;
                        case "LB":
                            Addusertypecombo.setSelectedIndex(1);
                            break;
                        case "ME":
                            Addusertypecombo.setSelectedIndex(2);
                            break;
                        default:

                    }


                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });
    }


    public void setAdminId(String adminId) {
        String tempadminId = adminId;
        this.adminId.setText(tempadminId);
    }


}
