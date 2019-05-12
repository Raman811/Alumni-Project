package com.example.alumini;

public class Database {
    private String firstname;
    private String Lastname;
    private String Fathername;
    private  String Mothername;
    private String Address;
    private String Mobileno;
    private String Dob;
   private String Gender;
   private String Course;

    public Database() {


    }
    public Database(String firstname, String lastname, String fathername, String mothername, String address, String mobileno, String dob, String gender, String course) {
        this.firstname = firstname;
        Lastname = lastname;
        Fathername = fathername;
        Mothername = mothername;
        Address = address;
        Mobileno = mobileno;
        Dob = dob;
        Gender = gender;
        Course = course;
    }



    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public String getFathername() {
        return Fathername;
    }

    public String getMothername() {
        return Mothername;
    }

    public String getAddress() {
        return Address;
    }

    public String getMobileno() {
        return Mobileno;
    }

    public String getDob() {
        return Dob;
    }

    public String getGender() {
        return Gender;
    }

    public String getCourse() {
        return Course;
    }

}
