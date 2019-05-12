package com.example.alumini;

public class Database1 {
  private   String firstname;
    private  String Lastname;
    private  String Fathername;
    private  String Mothername;
    private  String Address;
    private String Mobileno;
    private String Dob;
    private String Gender;
    private  String Course;
private String Url;
    public Database1() {
    }

    public Database1(String firstname, String lastname, String fathername, String mothername, String address, String mobileno, String dob, String gender, String course, String url) {
        this.firstname = firstname;
        Lastname = lastname;
        Fathername = fathername;
        Mothername = mothername;
        Address = address;
        Mobileno = mobileno;
        Dob = dob;
        Gender = gender;
        Course = course;
        Url = url;
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

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public void setFathername(String fathername) {
        Fathername = fathername;
    }

    public void setMothername(String mothername) {
        Mothername = mothername;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setMobileno(String mobileno) {
        Mobileno = mobileno;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setCourse(String course) {
        Course = course;
    }
}
