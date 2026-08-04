package com.uicomponents;

import com.dbconnection.DbOperations;
import com.modules.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.dbconnection.DbOperations.*;
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
    private JTextField FinduserTextfield;
    private JButton Finduserbtn;
    private JTable FindUserTable;
    private JScrollPane jscrollpane;
    private JTable AlluserDetailsTable;
    private JButton showDataButton;
    private JButton showButton;
    private JTable AllbooksTable;
    private JTextField SearchBookID;
    private JButton SearchBookButton;
    private JTable SelectedBookTable;
    private JTextField NewBookId;
    private JTextField NewAuthor;
    private JTextField NewBookName;
    private JTextField NewIsbn;
    private JTextField NewBookCount;
    private JTextField NewShelfNumber;
    private JButton addBookButton;
    private JTextField DeleteBookTextField;
    private JButton deleteBookButton;
    private JTextField ToBeUpdatedBookID;
    private JTextField UpdateBookId;
    private JTextField UpdateBookName;
    private JTextField UpdateBookAthor;
    private JTextField UpdateBookIsbn;
    private JTextField UpdateShelfNo;
    private JButton updateBookButton;
    private JTextField UpdateBookCount;
    private JButton checkButton;
    private JTable ReservationTable;
    private JButton viewAllReservationsButton;
    private JTextField ReservationUserId;
    private JTextField ReservationBookId;
    private JButton addReservationButton;
    private JTabbedPane tabbedPane5;
    private JTable FineTable;
    private JButton seeFinesButton;
    private JTextField DeleteReservationTextField;
    private JButton DeleteReservationButton;

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
                            JOptionPane.showMessageDialog(frame,"Changed Data of "+UpdateUid.getText(),"Information",JOptionPane.INFORMATION_MESSAGE);
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

                    if(!(new DbOperations().userIdCheck(UpdateUid.getText()))){
                        JOptionPane.showMessageDialog(frame,"User Id Not Exist","Error",JOptionPane.ERROR_MESSAGE);

                    }else{
                        if(new DbOperations().userIdCheck(UpdateUid.getText())){
                            String sql ="SELECT * FROM users WHERE U_id=?";
                            PreparedStatement ps = dbconnection().prepareStatement(sql);
                            ps.setString(1,UpdateUid.getText());
                            ResultSet rs = ps.executeQuery();
                            while(rs.next()){
                                NewUid.setText(rs.getString("U_id"));
                                Newname.setText(rs.getString("U_name"));
                                Newpasswordfield.setText(rs.getString("U_password"));
                                Newphonenumber.setText(rs.getString("U_phone"));
                                Newemailaddress.setText(rs.getString("U_email"));
                                Newaddress.setText(rs.getString("U_address"));
                            }
                        }


                        if(pre.equals("AD")){
                            NewUpdateCombo.setSelectedIndex(0);
                        }else if(pre.equals("LB")){
                            NewUpdateCombo.setSelectedIndex(1);
                        }else if(pre.equals("ME")){
                            NewUpdateCombo.setSelectedIndex(2);
                        }
                    }


                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        DefaultTableModel model = (DefaultTableModel) FindUserTable.getModel();
        model.setColumnIdentifiers(new Object[]{"User ID","Name","Type","Phone Number","Email","Address"});

        Finduserbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(new DbOperations().userIdCheck(FinduserTextfield.getText()))){
                    JOptionPane.showMessageDialog(frame,"User Id Not Exist","Error",JOptionPane.ERROR_MESSAGE);
                }else{
                    model.setRowCount(0);
                    Connection co=dbconnection();

                    try {
                        String sql = "SELECT * FROM users WHERE U_id=?";
                        PreparedStatement ps = co.prepareStatement(sql);
                        ps.setString(1,FinduserTextfield.getText());
                        ResultSet rs = ps.executeQuery();

                        while(rs.next()){
                            model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(7)});
                            System.out.println("Executed");
                        }



                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });


        showDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model2  = (DefaultTableModel) AlluserDetailsTable.getModel();
                model2.setColumnIdentifiers(new Object[]{"User ID","Name","Type","Phone Number","Email","Address"});

                Connection con =dbconnection();
                model2.setRowCount(0);

                String sql = "SELECT * FROM users";
                try {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        model2.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(7)});

                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });


        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model3  = (DefaultTableModel) AllbooksTable.getModel();
                model3.setColumnIdentifiers(new Object[]{"Book ID","Name","Author","ISBN","Shelf Number","Count"});
                model3.setRowCount(0);
                Connection con =dbconnection();
                String sql = "SELECT * FROM book";
                try {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        model3.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });
        SearchBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model4  = (DefaultTableModel) SelectedBookTable.getModel();
                model4.setColumnIdentifiers(new Object[]{"Book ID","Name","Author","ISBN","Shelf Number","Count"});

                if(!(new DbOperations().bookCheck(SearchBookID.getText()))){
                    JOptionPane.showMessageDialog(frame,"Book Id Not Exist","Error",JOptionPane.ERROR_MESSAGE);
                }else{
                    String sql = "SELECT * FROM book where B_ID=?";
                    Connection con =dbconnection();
                    model4.setRowCount(0);
                    try {
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1,SearchBookID.getText());
                        ResultSet rs = ps.executeQuery();
                        while(rs.next()){
                            model4.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});

                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(NewBookId.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"Book ID Empty","Error",JOptionPane.ERROR_MESSAGE);
                }else if(NewBookId.getText().charAt(0) != 'B'){
                    JOptionPane.showMessageDialog(frame,"Book Name Must Start With Bxxx In this Order","Error",JOptionPane.ERROR_MESSAGE);
                }else if(new DbOperations().bookCheck(NewBookId.getText())){
                    JOptionPane.showMessageDialog(frame,"Book ID Already Exist","Error",JOptionPane.ERROR_MESSAGE);
                }else if(NewBookName.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"Book Name Empty","Error",JOptionPane.ERROR_MESSAGE);
                }else if(NewBookName.getText().length()>100){
                    JOptionPane.showMessageDialog(frame,"Book Name Too Long","Error",JOptionPane.ERROR_MESSAGE);
                }else if(NewAuthor.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"Book Author Empty","Error",JOptionPane.ERROR_MESSAGE);
                }else if(NewAuthor.getText().length()>50){
                    JOptionPane.showMessageDialog(frame,"Book Author Too Long","Error",JOptionPane.ERROR_MESSAGE);
                }else if(NewIsbn.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"Book ISBN Empty","Error",JOptionPane.ERROR_MESSAGE);
                }else if(isbnCheck(NewIsbn.getText())){
                    JOptionPane.showMessageDialog(frame,"Book ISBN Already Exist","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(NewIsbn.getText().length()>13||NewIsbn.getText().length()<13){
                    JOptionPane.showMessageDialog(frame,"Book ISBN Contains 13 Digits","Error",JOptionPane.ERROR_MESSAGE);
                }else if(NewShelfNumber.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"Book Shelf Number Empty","Error",JOptionPane.ERROR_MESSAGE);
                }else if(NewBookCount.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"Book Count Empty","Error",JOptionPane.ERROR_MESSAGE);
                }else{
                    Connection con =dbconnection();
                    String sql = "INSERT INTO book VALUES(?,?,?,?,?,?)";
                    try {
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1,NewBookId.getText());
                        ps.setString(2,NewBookName.getText());
                        ps.setString(3,NewAuthor.getText());
                        ps.setString(4,NewIsbn.getText());
                        ps.setString(5,NewShelfNumber.getText());
                        ps.setString(6,NewBookCount.getText());
                        int affectedrow =  ps.executeUpdate();

                        if(affectedrow>0){
                            JOptionPane.showMessageDialog(frame,"Book Inserted Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                            NewBookId.setText("");
                            NewBookName.setText("");
                            NewAuthor.setText("");
                            NewIsbn.setText("");
                            NewShelfNumber.setText("");
                            NewBookCount.setText("");
                        }else{
                            JOptionPane.showMessageDialog(frame,"Book Inserted Failed","Error",JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

            }
        });
        deleteBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(new DbOperations().bookCheck(DeleteBookTextField.getText())){
                    Connection con = dbconnection();
                    String sql = "DELETE FROM book WHERE B_ID=?";
                    try {
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1,DeleteBookTextField.getText());
                        int affectedrow = ps.executeUpdate();
                        if(affectedrow>0){
                            JOptionPane.showMessageDialog(frame,"Book Deleted Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                            DeleteBookTextField.setText("");
                        }else{
                            JOptionPane.showMessageDialog(frame,"Book Deleted Failed","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }else{
                    JOptionPane.showMessageDialog(frame,"Book Id Not Exist","Error",JOptionPane.ERROR_MESSAGE);
                }


            }
        });


        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ToBeUpdatedBookID.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"Book ID Empty","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if (!(new DbOperations().bookCheck(ToBeUpdatedBookID.getText()))) {
                    JOptionPane.showMessageDialog(frame, "Book ID Not Exist", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String sql = "SELECT * FROM book WHERE B_ID=?";
                    try {
                        PreparedStatement ps = dbconnection().prepareStatement(sql);
                        ps.setString(1, ToBeUpdatedBookID.getText());
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {

                            UpdateBookName.setText(rs.getString("B_Name"));
                            UpdateBookAthor.setText(rs.getString("Author"));
                            UpdateBookIsbn.setText(rs.getString("isbn"));
                            UpdateShelfNo.setText(String.valueOf(rs.getInt("Shelf_no")));
                            UpdateBookCount.setText(String.valueOf(rs.getInt("count")));

                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });
        updateBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean shelfNumberCheck = true;
                boolean countCheck = true;
                boolean IsbnCheck = true;
                boolean Authorcheck=true;
                boolean nameCheck=true;

                //Shelf Number Update
                if(!(UpdateShelfNo.getText().isEmpty())){
                    String sql = "UPDATE book SET shelf_no=? WHERE B_ID=?";
                    try {
                        PreparedStatement ps = dbconnection().prepareStatement(sql);
                        ps.setInt(1, Integer.parseInt(UpdateShelfNo.getText()));
                        ps.setString(2, ToBeUpdatedBookID.getText());
                        int affectedrow = ps.executeUpdate();
                        if(affectedrow>0){
                            shelfNumberCheck = true;
                        }else{
                            shelfNumberCheck = false;
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

                //Book Count Update
                if(!(UpdateBookCount.getText().isEmpty())){
                    String sql = "UPDATE book SET count=? WHERE B_ID=?";
                    try {
                        PreparedStatement ps = dbconnection().prepareStatement(sql);
                        ps.setInt(1, Integer.parseInt(UpdateBookCount.getText()));
                        ps.setString(2, ToBeUpdatedBookID.getText());
                        int affectedrow = ps.executeUpdate();
                        if(affectedrow>0){
                            countCheck = true;
                        }else{
                            countCheck = false;
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

                //ISBN Check and Updtae
                if(!(UpdateBookIsbn.getText().isEmpty())){
                    if(DbOperations.isbnCheck(UpdateBookIsbn.getText())){
                        JOptionPane.showMessageDialog(frame,"Book ISBN Already Exist","Error",JOptionPane.ERROR_MESSAGE);
                    }else{
                        String sql = "UPDATE book SET isbn=? WHERE B_ID=?";
                        try {
                            PreparedStatement ps = dbconnection().prepareStatement(sql);
                            ps.setString(1, UpdateBookIsbn.getText());
                            ps.setString(2, ToBeUpdatedBookID.getText());
                            int affectedrow = ps.executeUpdate();
                            if(affectedrow>0){
                                IsbnCheck = true;
                            }else{
                                IsbnCheck = false;
                            }
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }

                //Author Update
                if(!(UpdateBookAthor.getText().isEmpty())){
                    String sql = "UPDATE book SET Author=? WHERE B_ID=?";
                    try {
                        PreparedStatement ps = dbconnection().prepareStatement(sql);
                        ps.setString(1, UpdateBookAthor.getText());
                        ps.setString(2, ToBeUpdatedBookID.getText());
                        int affectedrow = ps.executeUpdate();
                        if(affectedrow>0){
                            Authorcheck=true;
                        }else{
                            Authorcheck=false;
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                //Book Name Update
                if(!(UpdateBookName.getText().isEmpty())){
                    String sql = "UPDATE book SET B_name=? WHERE B_ID=?";
                    try {
                        PreparedStatement ps=dbconnection().prepareStatement(sql);
                        ps.setString(1, UpdateBookName.getText());
                        ps.setString(2, ToBeUpdatedBookID.getText());
                        int affectedrow = ps.executeUpdate();
                        if(affectedrow>0){
                            nameCheck=true;
                        }else{
                            nameCheck=false;
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                if(nameCheck && Authorcheck && IsbnCheck && countCheck && shelfNumberCheck){
                    JOptionPane.showMessageDialog(frame,"Book Updated","Success",JOptionPane.INFORMATION_MESSAGE);
                    UpdateBookName.setText("");
                    UpdateBookAthor.setText("");
                    UpdateBookIsbn.setText("");
                    UpdateShelfNo.setText("");
                    UpdateBookCount.setText("");
                }else{
                    JOptionPane.showMessageDialog(frame,"Book Updated Failed","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        viewAllReservationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) ReservationTable.getModel();
                model.setRowCount(0);

                model.setColumnIdentifiers(new Object[]{"Reservation ID","User ID","User Name","Book ID","Book Name"});

                String sql = "SELECT r.R_Id,u.U_id,U.U_name,b.B_id,b.B_name FROM reservation r INNER JOIN users u ON r.U_id=u.U_id INNER JOIN book b ON b.B_id=r.B_id;\n";

                try {
                    PreparedStatement ps = dbconnection().prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        model.addRow(new Object[]{rs.getString("R_id"),rs.getString("U_id"),rs.getString("U_name"),rs.getString("B_id"),rs.getString("B_name")});
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        addReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(ReservationBookId.getText().isEmpty()||ReservationUserId.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"Book ID or User ID Empty","Error",JOptionPane.ERROR_MESSAGE);
                }else {

                    if (!(new DbOperations().bookCheck(ReservationBookId.getText()))) {
                        JOptionPane.showMessageDialog(frame, "Book Not Exists", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (!(new DbOperations().userIdCheck(ReservationUserId.getText()))) {
                        JOptionPane.showMessageDialog(frame, "User Id Not Exist", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if ((ReservationCheck(ReservationBookId.getText(), ReservationUserId.getText()))) {
                        JOptionPane.showMessageDialog(frame, "Book Already Booked for this User", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        String sql = "INSERT INTO reservation (B_id,U_id) VALUES (?,?)";
                        try {
                            PreparedStatement ps = dbconnection().prepareStatement(sql);
                            ps.setString(1, ReservationBookId.getText());
                            ps.setString(2, ReservationUserId.getText());

                            int affectedrow = ps.executeUpdate();
                            if (affectedrow > 0) {
                                JOptionPane.showMessageDialog(frame, "Reservation Complete", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Reservation Not Completed", "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            }
        });
        seeFinesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) FineTable.getModel();
                model.setRowCount(0);
                model.setColumnIdentifiers(new Object[]{"Fine ID","Amount","Payment Status","Book ID","User ID"});

                String sql = "Select * from fine";
                try {
                    PreparedStatement ps = dbconnection().prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        model.addRow(new Object[]{rs.getString("F_id"),rs.getString("amount"),rs.getString("Payment_status"),rs.getString("B_id"),rs.getString("U_id")});
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        DeleteReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if(DeleteReservationTextField.getText().isEmpty()){
                        JOptionPane.showMessageDialog(frame,"Reservation ID is Empty","Error",JOptionPane.ERROR_MESSAGE);
                    }else if(!(reservationIdCheck(DeleteReservationTextField.getText()))){
                        JOptionPane.showMessageDialog(frame,"Reservation ID Not Exist","Error",JOptionPane.ERROR_MESSAGE);
                    }else{
                        String sql = "Delete from reservation WHERE R_id=?";
                        try {
                            PreparedStatement ps = dbconnection().prepareStatement(sql);
                            ps.setString(1,DeleteReservationTextField.getText());
                            int affectedrow = ps.executeUpdate();
                            if (affectedrow > 0) {
                                JOptionPane.showMessageDialog(frame, "Reservation Deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                JOptionPane.showMessageDialog(frame, "Reservation Not Deleted", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
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
