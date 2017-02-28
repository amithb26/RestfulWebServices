/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud_quotient;


import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "device")
public class Device implements Serializable {
private static final long serialVersionUID = 1L;
private int device_id;
private String device_name;
private String ip_add;
private String login_username;
private String password;

public Device(){}
public Device(int device_id, String device_name, String ip_add, String login_username, String password){
this.device_id = device_id;
this.device_name = device_name;
this.ip_add = ip_add;
this.login_username = login_username;
this.password = password;
}
public int getDeviceId() {
return device_id;
}
@XmlElement
public void setDeviceId(int device_id) {
this.device_id = device_id;
}
public String getDeviceName() {
return device_name;
}
@XmlElement
public void setDeviceName(String device_name) {
this.device_name = device_name;
}
public String getIPaddress() {
return ip_add;
}
@XmlElement
public void setIPaddress(String ip_add) {
this.ip_add = ip_add;
}
public String getLoginUsername() {
return login_username;
}
@XmlElement
public void setLoginUsername(String login_username) {
this.login_username = login_username;
}
public String getPassword() {
return password;
}
@XmlElement
public void setPassword(String password) {
this.password = password;
}

@Override
public boolean equals(Object object){
if(object == null){
return false;
}else if(!(object instanceof Device)){
return false;
}else {
Device device = (Device)object;
if(device_id == device.getDeviceId() && device_name.equals(device.getDeviceName()) && ip_add.equals(device.getIPaddress()) && login_username.equals(device.getLoginUsername()) && password.equals(device.getPassword()))
{
return true;
}
}
return false;
}
}
