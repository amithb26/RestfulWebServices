/*
This specially  constructed class(Javabean) Device defines a set of properties and methods specifying the data that is to be stored as a resource. 
 */
package com.cloud_quotient; //Creates User defined package called com.cloud_quotient that includes class Device


import java.io.Serializable; // importing standard package, this is an interface(serializable) that defines method to implement java serialization
import javax.xml.bind.annotation.XmlElement;         // Importing standard package javax, that defines annotations to map javabean object to XML
import javax.xml.bind.annotation.XmlRootElement;  
@XmlRootElement(name = "device")                     //map the class to XML element
public class Device implements Serializable {        //Declaration of class implementing interface serializable, inorder to be serializable 
private static final long serialVersionUID = 1L;     /*The serialVersionUID is a universal version identifier for a Serializable class. Deserialization uses this number 
                                                     to ensure that a loaded class corresponds exactly to a serialized object. 
                                                     If no match is found, then an InvalidClassException is thrown  */

//Properties defining the state of te object   
private int device_id;
private String device_name;
private String ip_add;
private String login_username;
private String password;

  
public Device(){}

//Initialization through constructor  
public Device(int device_id, String device_name, String ip_add, String login_username, String password){
this.device_id = device_id;
this.device_name = device_name;
this.ip_add = ip_add;
this.login_username = login_username;
this.password = password;
}
// Methods allowin an accesss to properties, either to get or set them  
public int getDeviceId() {
return device_id;
}
@XmlElement   //map the javabean property to an XML element derivedfrom property name
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

@Override  //@Override annotation informs the compiler that the element is meant to override an element declared in a superclass
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
