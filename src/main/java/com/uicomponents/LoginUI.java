package com.uicomponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame {

    private JPanel jpane;
    private JLabel uiimage;
    private JTextField usernametextfield;
    private JTextField passwordtextfield;
    private JButton logInButton;
    private JLabel loginuitheading;
    private JLabel usernamelabel;
    private JLabel passwordlabel;

    LoginUI(){

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Login");
        frame.setResizable(false);
        frame.setLocationRelativeTo(jpane);
        frame.setSize(800,600);
        frame.add(jpane);

        ImageIcon icon = new ImageIcon("src/main/resources/icon.png");
        frame.setIconImage(icon.getImage());

        ImageIcon icon2 = new ImageIcon("src/main/resources/image.jpg");
        Image scaledimage = icon2.getImage().getScaledInstance(300,600,Image.SCALE_SMOOTH);
        ImageIcon largeicon = new ImageIcon(scaledimage);
        uiimage.setIcon(largeicon);

        loginuitheading.setFont(new Font("Times New Roman",Font.BOLD,23));
        usernamelabel.setFont(new Font("Times New Roman",Font.BOLD,18));
        passwordlabel.setFont(new Font("Times New Roman",Font.BOLD,18));
        logInButton.setFont(new Font("Times New Roman",Font.BOLD,18));



        jpane.setBackground(Color.white);



        frame.setVisible(true);


        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String username = usernametextfield.getText();
                String password = passwordtextfield.getText();
                if(username.equals("") || password.equals("")){
                    JOptionPane.showMessageDialog(null,"Please fill all the fields");
                }

            }
        });
    }

    static void main(String[] args) {
        new LoginUI();
    }

}
