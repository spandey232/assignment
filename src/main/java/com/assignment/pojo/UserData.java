package com.assignment.pojo;

import lombok.Getter;

@Getter
public class UserData implements Comparable{
    String id;
    String firstName;
    String lastName;
    String userName;
    String email;
    String avatar;
    String gender;
    String dob;
    String address;

    public UserData(String id, String firstName, String lastName, String userName, String email, String avatar, String gender, String dob, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.avatar = avatar;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
    }

    @Override
    public String toString() {
        return userName;
    }

    @Override
    public int compareTo(Object o) {
        UserData comparingTo=(UserData)o;
        int difference= this.toString().compareTo(comparingTo.toString());
        return difference;
    }
}
