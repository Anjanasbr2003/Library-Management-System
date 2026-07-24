package com.uicomponents;

import javax.swing.*;

public class AdminUi extends JFrame {

    private JLabel adminId;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane3;
    private JTabbedPane tabbedPane4;
    private JPanel adminUiPane;

    AdminUi() {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(adminUiPane);


        frame.setVisible(true);
        frame.setTitle("Admin UI");

    }




}
