package com.dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.dbconnection.Dbconnection.dbconnection;

public class DbOperations {


    Dbconnection dbconnection = new Dbconnection();

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


    public boolean userExistionCheckAndDeletion(String userid){

        Connection co=dbconnection();

        if(conn==null){
            System.out.println("Connection is null");
            return false;
        }


        try {
            String sql="DELETE FROM users WHERE U_id=?";
            PreparedStatement ps=co.prepareStatement(sql);

            String sql2="SELECT U_id FROM users WHERE U_id=?";
            PreparedStatement ps2=co.prepareStatement(sql2);



            ps2.setString(1,userid);
            ps.setString(1,userid);

            ResultSet rs2=ps2.executeQuery();

            while(rs2.next()){
                if(rs2.getString("U_id").equals(userid)){

                   ps.executeUpdate();
                    return true;
                }
            }





        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  false;
    }

    //Check a user id and return true is its in the database
    public boolean userIdCheck(String userid){
        Connection co=dbconnection();
        if(conn==null){
            System.out.println("Connection is null");
        }

        try {
            String sql="SELECT U_id FROM users";
            PreparedStatement ps = co.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getString("U_id").equals(userid)){
                    return true;
                }
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean bookCheck(String bookid){
        Connection co=dbconnection();
        if(conn==null){
            System.out.println("Connection is null");
        }
        try {
            String sql="SELECT B_id FROM book";
            PreparedStatement ps = co.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getString("B_id").equals(bookid)){
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    public static boolean isbnCheck(String bookid){
        Connection co=dbconnection();

        String sql = "SELECT isbn FROM book";
        try {
            PreparedStatement ps = co.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                if(rs.getString("isbn").equals(bookid)){
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;

    }



}
