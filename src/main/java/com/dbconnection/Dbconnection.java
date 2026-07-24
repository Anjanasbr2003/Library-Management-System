package com.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {




    public Connection dbconnection(){

        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/lmsys";
        String user = "root";
        String password = "1234";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println( e.getMessage() );
        }

        System.out.println("DB Connection Successful");
        return con;
    }

}
