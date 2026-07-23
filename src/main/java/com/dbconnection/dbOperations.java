package com.dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbOperations {


    dbconnection dbconnection = new dbconnection();

    Connection conn = dbconnection.dbconnection();



    public boolean authenticate(String username,String password){

        if(conn==null){
            System.out.println("Connection is null");
            return false;
        }

        try {
            String sql = "SELECT U_password FROM users WHERE U_id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                String pass = rs.getString("U_password");

                if(pass.equals(password)){
                    System.out.println("User Found");
                    return true;

                }

            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return false;
    }


}
