package com.example.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInfo implements Parcelable{

	
	private String name;
	private String adress;
	private int  age;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 * @param adress the adress to set
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(name);
		dest.writeString(adress);
		dest.writeInt(age);
	}
   
	public static final Parcelable.Creator<UserInfo> CREATOR=new Creator<UserInfo>() {

		@Override
		public UserInfo createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			UserInfo userInfo=new UserInfo();
			userInfo.setName(source.readString());
			userInfo.setAdress(source.readString());
			userInfo.setAge(source.readInt());
			return userInfo;
		}

		@Override
		public UserInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new UserInfo[size];
		}
	};
}
