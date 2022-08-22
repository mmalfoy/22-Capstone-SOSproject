package com.example.sosproject;

public class AccountInfo {
    private int id;
    private String name;
    private String phone;
    private String birth;
    private int login;

    public AccountInfo(int id, String name, String phone, String birth, int login) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }
}
