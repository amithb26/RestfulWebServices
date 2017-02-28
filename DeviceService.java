/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud_quotient;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/DeviceService")
public class DeviceService {
DeviceDao deviceDao = new DeviceDao();
private static final String SUCCESS_RESULT="<result>success</result>";
private static final String FAILURE_RESULT="<result>failure</result>";
private static final String INSUFFICIENT_DATA="<result>Please enter all the fields</result>";
@GET
@Path("/devicedetails")  
@Produces(MediaType.APPLICATION_JSON)
public List<Device> getDevice_details(){
	System.out.println("inGET");
return deviceDao.getDevices();
}

@GET
@Path("/get_device_id/{device_id}")
@Produces(MediaType.APPLICATION_JSON)
public Device getDevice(@PathParam("device_id") int device_id){
return deviceDao.getDevice(device_id);
}

@GET
@Path("/get_device_ip/{ip_add}")
@Produces(MediaType.APPLICATION_JSON)
public Device getDevice(@PathParam("ip_add") String ip_add){
return deviceDao.getDevice_based_on_ip(ip_add);
}


@PUT
@Path("/device_details")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public String createDevice(@FormParam("device_id") int device_id,
@FormParam("device_name") String device_name,
@FormParam("ip_add") String ip_add,
@FormParam("login_username") String login_username,
@FormParam("password") String password,
@Context HttpServletResponse servletResponse) throws IOException{
Device device = new Device(device_id, device_name, ip_add, login_username, password);

int result = deviceDao.argumentCheck(device);
if (result == 1){
int result1 = deviceDao.addDevice(device);
if(result1 == 1){
return SUCCESS_RESULT;
}
return FAILURE_RESULT;
}
else
{
    return INSUFFICIENT_DATA;
}
}

@POST
@Path("/device_details")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public String updateDeviceDetails(@FormParam("device_id") int device_id,
@FormParam("device_name") String device_name,
@FormParam("ip_add") String ip_add,
@FormParam("login_username") String login_username,
@FormParam("password") String password,
@Context HttpServletResponse servletResponse) throws IOException{

Device device = new Device(device_id, device_name, ip_add, login_username, password);
System.out.println("in update details");
int result = deviceDao.updateDeviceDetails(device);

if(result == 1){
return SUCCESS_RESULT;
}
return FAILURE_RESULT;
}

@DELETE
@Path("/device_details/{device_id}")
@Produces(MediaType.APPLICATION_XML)
public String deleteUser(@PathParam("device_id") int device_id){
int result = deviceDao.deleteDeviceDetails(device_id);
if(result == 1){
return SUCCESS_RESULT;
}
return FAILURE_RESULT;
}

@OPTIONS
@Path("/device_details")
@Produces(MediaType.APPLICATION_XML)
public String getSupportedOperations(){
return "<operations>GET, PUT, POST, DELETE</operations>";
}
}
