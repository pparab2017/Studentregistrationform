package com.example.studentregistrationform;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pushparajparab on 9/6/16.
 */
public class Student implements Parcelable {

    private String name;
    private String emailAddress;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setAccountState(String accountState) {
        this.accountState = accountState;
    }

    public void setMood(double mood) {
        this.mood = mood;
    }

    private String department;
    private String accountState;
    private double mood;

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getDepartment() {
        return department;
    }

    public String getAccountState() {
        return accountState;
    }

    public double getMood() {
        return mood;
    }

    public Student( String name, String emailAddress, String department,String accountState,double mood) {
        this.mood = mood;
        this.accountState = accountState;
        this.department = department;
        this.emailAddress = emailAddress;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", department='" + department + '\'' +
                ", accountState='" + accountState + '\'' +
                ", mood=" + mood +
                '}';
    }

    public static final Parcelable.Creator<Student> CREATOR
            = new Parcelable.Creator<Student>() {
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    private Student(Parcel in) {
        this.name = in.readString();
        this.emailAddress = in.readString();
        this.department = in.readString();
        this.accountState = in.readString();
        this.mood = in.readDouble();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(emailAddress);
        dest.writeString(department);
        dest.writeString(accountState);
        dest.writeDouble(mood);

    }
}
