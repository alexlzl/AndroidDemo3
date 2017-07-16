package com.example.aidlservicedemo.domain;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateUtils;
import android.util.Log;

public class Message implements Parcelable {

	private int id;
	private String msgText;
	private String fromName;
	private String date;

	public Message() {
		super();

	}

	public Message(int id, String msgText, String fromName, String date) {
		super();
		this.id = id;
		this.msgText = msgText;
		this.fromName = fromName;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMsgText() {
		return msgText;
	}

	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public String toString() {
		return "信息内容=" + msgText + ", 发件人="
				+ fromName + ", 时间=" + date ;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		Log.i("main", "客户端Message被序列化");
		dest.writeInt(id);
		dest.writeString(msgText);
		dest.writeString(fromName);
		dest.writeString(date);
	}

	public static final Parcelable.Creator<Message> CREATOR = new Creator<Message>() {

		@Override
		public Message[] newArray(int size) {
			return new Message[size];
		}

		@Override
		public Message createFromParcel(Parcel source) {
			Log.i("main", "客户端Message被反序列化");
			return new Message(source.readInt(), source.readString(),
					source.readString(), source.readString());
		}
	};
}
