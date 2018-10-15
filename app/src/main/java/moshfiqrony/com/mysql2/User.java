package moshfiqrony.com.mysql2;


import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String email;
    private String phone;
    private String passport;
    private String fname;
    private String address;
    private String bEmail;
    private String password;
    private String dob;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getbEmail() {
        return bEmail;
    }

    public void setbEmail(String bEmail) {
        this.bEmail = bEmail;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public User() {
    }

    public User(String userName, String email, String phone, String passport, String fname) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.passport = passport;
        this.fname = fname;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassport() {
        return passport;
    }

    public String getFname() {
        return fname;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }



}
