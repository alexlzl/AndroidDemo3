package com.example.entity;

import java.util.HashMap;
import java.util.Map;


public class Contact {
	private String name;
	private String phone;
	public Contact(){}
	public Contact(String name,String phone){
		this.name=name;
		this.phone=phone;
	}
    public Map<String, String>parseToMap(){
    	Map<String, String>map=new HashMap<String, String>();
    	map.put("name", name);
    	map.put("phone", phone);
    	return map;
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
	
}
