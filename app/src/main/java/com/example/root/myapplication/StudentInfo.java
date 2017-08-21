package com.example.root.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class StudentInfo implements Parcelable {
    String sName;
    int sAge;
    String sGender;
    String sCity;

    public StudentInfo(String name, int age, String gender, String city){
        sName = name;
        sAge = age;
        sGender = gender;
        sCity = city;
    }

    public StudentInfo(Parcel in){
        super();
        readFromParcel(in);
    }

    public String getName(){
        return sName;
    }

    public int getAge(){
        return sAge;
    }

    public String getGender(){
        return sGender;
    }

    public  String getCity(){
        return sCity;
    }

    public static final Parcelable.Creator<StudentInfo> CREATOR = new Parcelable.Creator<StudentInfo>(){
        public StudentInfo createFromParcel(Parcel in){
            return new StudentInfo(in);
        }

        public StudentInfo[] newArray(int size){
            return new StudentInfo[size];
        }
    };

    public void readFromParcel(Parcel in){
        sName = in.readString();
        sAge = in.readInt();
        sGender = in.readString();
        sCity = in.readString();
    }

    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(sName);
        parcel.writeInt(sAge);
        parcel.writeString(sGender);
        parcel.writeString(sCity);
    }
}
