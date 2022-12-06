package com.example.springoverview;

public class SuperUser extends User {
    private String admin;

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{id=" + this.getId() + ",name=" + this.getName() + ",admin=" + this.getAdmin() + "}";
    }
}
