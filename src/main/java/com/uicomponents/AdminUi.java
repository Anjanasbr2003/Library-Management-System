package com.uicomponents;

import com.dbconnection.DbOperations;
import com.modules.User;

import javax.swing.*;
import java.awt.event.*;
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
    private JTextField NewUid;
    private JTextField Newname;
    private JTextField Newphonenumber;

    private JTextField Newaddress;
    private JButton UPDATEButton;
    private JTextField UpdateUid;
    private JComboBox NewUpdateCombo;
    private JTextField Newpasswordfield;
    private JTextField Newemailaddress;
    private JTextField textField1;
    private JTable userdetailtable;

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

        NewUpdateCombo.addItem("admin");
        NewUpdateCombo.addItem("librarian");
        NewUpdateCombo.addItem("member");
        NewUpdateCombo.setEditable(false);




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
                        Adduseremail.setText("");
                        Adduserpassword.setText("");
                        Addusertelephone.setText("");
                        Adduseraddress.setText("");
                        Addusertypecombo.setSelectedIndex(0);
                        AdduserId.setText("");
                        Adduserpassword.setText("");

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
                    deleteuserid.setText("");
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
        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean idstatus = true;
                boolean namestatus = true;
                boolean emailstatus = true;
                boolean phonenumberstatus = true;
                boolean addresstatus = true;
                boolean PasswordStatus = true;
                boolean usertypestatus = true;



                Connection con = dbconnection();




                if(!(new DbOperations().userIdCheck(UpdateUid.getText()))){
                    JOptionPane.showMessageDialog(frame,"User does not exist","Error",JOptionPane.ERROR_MESSAGE);
                }else{



                    //Addres Update section
                    if(!(Newaddress.getText().equals("")||Newaddress.getText()==null)){
                        if(Newaddress.getText().length()>100){
                            JOptionPane.showMessageDialog(frame,"<html><body style='color:red'>Address should less than 100 words</body></html>","Error",JOptionPane.ERROR_MESSAGE);

                        }else{
                            String sql="UPDATE users SET U_address=? WHERE U_id=?";
                            try {
                                PreparedStatement ps=con.prepareStatement(sql);
                                ps.setString(1,Newaddress.getText());
                                ps.setString(2,UpdateUid.getText());
                                int affectedrows=ps.executeUpdate();
                                if(affectedrows>0){
                                    addresstatus=true;
                                }else{
                                    addresstatus=false;
                                }
                                ps.close();
                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                    }

                    //Email Update section
                    if(!(Newemailaddress.getText().equals("")||Newemailaddress.getText()==null)){
                        if(!(isValidEmail(Newemailaddress.getText()))){
                            JOptionPane.showMessageDialog(frame,"<html><body style='color:red'>Not a valid email address</body></html>","Error",JOptionPane.ERROR_MESSAGE);

                        }else{
                            String sql = "UPDATE users SET U_email=? WHERE U_id=?";
                            try {
                                PreparedStatement ps = con.prepareStatement(sql);
                                ps.setString(1,Newemailaddress.getText());
                                ps.setString(2,UpdateUid.getText());
                                int affectedrows = ps.executeUpdate();
                                if(affectedrows > 0){
                                  emailstatus=true;
                                }else{
                                    emailstatus=false;
                                }
                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                    }

                    //Phone Number Update section
                    if(!(Newphonenumber.getText().equals("")||Newphonenumber.getText()==null)){
                        if(Newphonenumber.getText().length()<10||Newphonenumber.getText().length()>10){
                            JOptionPane.showMessageDialog(frame,"<html><body style='color:red'>Mobile number Must Conatin 10 Characters</body></html>","Error",JOptionPane.ERROR_MESSAGE);

                        }else{
                            String sql="UPDATE users SET U_phone=? WHERE U_id=?";
                            try {
                                PreparedStatement ps=con.prepareStatement(sql);
                                ps.setString(1,Newphonenumber.getText());
                                ps.setString(2,UpdateUid.getText());
                                int affectedrows=ps.executeUpdate();
                                if(affectedrows>0){
                                    phonenumberstatus=true;
                                }else{
                                    phonenumberstatus=false;
                                }
                                ps.close();
                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                    }

                    //Update password section
                    if(!(Newpasswordfield.getText().equals("")||Newpasswordfield.getText()==null)){
                        if(Newpasswordfield.getText().length()<8){
                            JOptionPane.showMessageDialog(frame,"<html><body style='color:red'>Password Must Conatin 8 Characters</body></html>","Error",JOptionPane.ERROR_MESSAGE);

                        }else{
                            String sql = "UPDATE users SET U_Password=? WHERE U_id=?";
                            try {
                                PreparedStatement ps = con.prepareStatement(sql);
                                ps.setString(1,Newpasswordfield.getText());
                                ps.setString(2,UpdateUid.getText());
                                int effectedrows=ps.executeUpdate();
                                if(effectedrows>0){
                                    PasswordStatus=true;
                                }else{
                                    PasswordStatus=false;

                                }
                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }

                        }
                    }

                    //Username Update section
                    if(!(Newname.getText().equals("")||Newname.getText()==null)){
                        if(Newname.getText().length()>100){
                            JOptionPane.showMessageDialog(frame,"Name should less than 100 Characters","Error",JOptionPane.ERROR_MESSAGE);
                        }else{
                            String sql ="UPDATE users SET U_name=? WHERE U_id=?";
                            try {
                                PreparedStatement ps=con.prepareStatement(sql);
                                ps.setString(1,Newname.getText());
                                ps.setString(2,UpdateUid.getText());
                                int rowsaffected= ps.executeUpdate();
                                ps.close();

                                if(rowsaffected>0){
                                    namestatus=true;

                                }else{
                                    namestatus=false;
                                }
                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                    }

                    //User Id update section
                    if(!(NewUid.getText().equals("")||NewUid.getText()==null)){
                        if(!(NewUid.getText().substring(0,2).equals("AD")||NewUid.getText().substring(0,2).equals("ME")||NewUid.getText().substring(0,2).equals("LB"))){
                            JOptionPane.showMessageDialog(frame,"<html><body style='color:red'>User Id Must Contain Prefix 'AD' or 'LB' or 'ME' <br>Example: ADxxx / LBxxx / MExxx<br></body></html>"  ,"Error",JOptionPane.ERROR_MESSAGE);

                        }else if (new DbOperations().userIdCheck(NewUid.getText())){
                            JOptionPane.showMessageDialog(frame,"User ID Already Exist","Error",JOptionPane.ERROR_MESSAGE);
                        }else if(!(new DbOperations().userIdCheck(NewUid.getText()))){
                            String sql ="UPDATE users SET U_id=? WHERE U_id=?";
                            try {
                                PreparedStatement ps = con.prepareStatement(sql);
                                ps.setString(1, NewUid.getText());
                                ps.setString(2, UpdateUid.getText());


                               //Updating The usertype using the UserId prefic Automatically
                                String sql2="UPDATE users SET U_type=? WHERE U_id=?";
                                PreparedStatement ps2 = con.prepareStatement(sql2);
                                ps2.setString(1,NewUpdateCombo.getSelectedItem().toString());
                                ps2.setString(2,UpdateUid.getText());
                               int affc= ps2.executeUpdate();
                               int afectedrows= ps.executeUpdate();

                               if(affc>0){
                                   usertypestatus=true;
                               }else{
                                   usertypestatus=false;
                               }

                               if(afectedrows>0){
                                   idstatus=true;

                               }else{
                                   idstatus=false;

                               }
                                ps.close();
                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }

                        }

                    }


                    if(idstatus && namestatus && PasswordStatus && emailstatus && phonenumberstatus && usertypestatus && addresstatus){
                        JOptionPane.showMessageDialog(frame,"User Updated Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        if(!idstatus){
                            JOptionPane.showMessageDialog(frame,"User ID Update Error","Error",JOptionPane.ERROR_MESSAGE);
                        }else if(!namestatus){
                            JOptionPane.showMessageDialog(frame,"User Name Update Error","Error",JOptionPane.ERROR_MESSAGE);
                        }else if(!PasswordStatus){
                            JOptionPane.showMessageDialog(frame,"User Password Update Error","Error",JOptionPane.ERROR_MESSAGE);
                        }else if(!usertypestatus){
                            JOptionPane.showMessageDialog(frame,"User Type Update Error","Error",JOptionPane.ERROR_MESSAGE);
                        }else if(!emailstatus){
                            JOptionPane.showMessageDialog(frame,"User Email Update Error","Error",JOptionPane.ERROR_MESSAGE);
                        }else if(!phonenumberstatus){
                            JOptionPane.showMessageDialog(frame,"User Phone Number Update Error","Error",JOptionPane.ERROR_MESSAGE);
                        }else if (!namestatus){
                            JOptionPane.showMessageDialog(frame,"Name Update Error","Error",JOptionPane.ERROR_MESSAGE);
                        }


                    }

                }







            }
        });


        UpdateUid.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                String pre = null;
                try {
                    pre = UpdateUid.getText().substring(0,2);


                    if(pre.equals("AD")){
                        NewUpdateCombo.setSelectedIndex(0);
                    }else if(pre.equals("LB")){
                        NewUpdateCombo.setSelectedIndex(1);
                    }else if(pre.equals("ME")){
                        NewUpdateCombo.setSelectedIndex(2);
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

    static void main(String[] args) {
        new AdminUi();
    }


}
