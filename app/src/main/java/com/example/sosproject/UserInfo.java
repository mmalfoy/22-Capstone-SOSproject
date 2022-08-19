package com.example.sosproject;

import com.google.gson.annotations.SerializedName;

// UserInfo
public class UserInfo {
    private String id;

    @SerializedName("age")
        private String age;

    @SerializedName("income_grade")
    private String income_grade;

    @SerializedName("total_fare")
    private String total_fare;

    public UserInfo(String id, String age, String income_grade, String total_fare) {
        super();
        this.id = id;
        this.age = age;
        this.income_grade = income_grade;
        this.total_fare = total_fare;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getIncome_grade() {
        return income_grade;
    }
    public void setIncome_grade(String income_grade) {
        this.income_grade = income_grade;
    }
    public String getTotal_fare() {
        return total_fare;
    }
    public void setTotal_fare(String total_fare) {
        this.total_fare = total_fare;
    }


    @Override
    public String toString(){
        return "UserInfo{" + "id='" + id + '\'' + ", age='" + age + '\'' + "income_grade='" + income_grade +'\'' + "total_fare='" + total_fare +'}';
    }
}
