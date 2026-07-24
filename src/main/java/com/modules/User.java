package com.modules;

public class User {

private String username;
private String password;
private String userid;
private String usertype;
private String phonenumber;
private String email;
private String address;

    public String getUsername() {
        return username;
    }

    public String getUserid() {
        return userid;
    }

    public String getPassword() {
        return password;
    }

    public String getUsertype() {
        return usertype;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
