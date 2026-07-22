package com.uicomponents;

import javax.swing.*;
import java.awt.*;

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
        frame.setLocationRelativeTo(null);
        frame.setSize(800,600);
        frame.add(jpane);

        ImageIcon icon = new ImageIcon("src/main/resources/images.png");
        frame.setIconImage(icon.getImage());

        ImageIcon icon2 = new ImageIcon("src/main/resources/images.png");
        Image scaledimage = icon2.getImage().getScaledInstance(300,600,Image.SCALE_DEFAULT);
        ImageIcon largeicon = new ImageIcon(scaledimage);
        uiimage.setIcon(largeicon);

        loginuitheading.setFont(new Font("Times New Roman",Font.BOLD,23));
        usernamelabel.setFont(new Font("Times New Roman",Font.BOLD,18));
        passwordlabel.setFont(new Font("Times New Roman",Font.BOLD,18));
        logInButton.setFont(new Font("Times New Roman",Font.BOLD,18));



        jpane.setBackground(Color.white);



        frame.setVisible(true);




    }

    static void main(String[] args) {
        new LoginUI();
    }

}
