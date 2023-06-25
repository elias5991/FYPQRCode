package com.example.fypqrcode.http.requests;

public class UserRequest {

        public Integer id;
        public String email;
        public Integer typeID;
        public String firstName;
        public String lastName;
        public Integer roleID;
        public String phoneNumber;

        public String password;

    public UserRequest(Integer id, String email, Integer typeID, String firstName, String lastName, Integer roleID, String phoneNumber, String password) {
        this.id = id;
        this.email = email;
        this.typeID = typeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleID = roleID;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
