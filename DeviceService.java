/*
This define resource classes and methods, using parameter annotations (@path, @GET, @POST, ...@produces etc) to access user-defined values, 
and subresources to consume specific resource methods.
 */
package com.cloud_quotient; //Creating the class under same package as that of class Device

import java.io.IOException; // 
import java.util.List;
import javax.servlet.http.HttpServletResponse;
// Following are the jersey packages that define methods for implementing java annoatations that are used to create RESTful service resources. 
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

@Path("/DeviceService")    // Defines the path to base URI       
public class DeviceService {
DeviceDao deviceDao = new DeviceDao();
private static final String SUCCESS_RESULT="<result>success</result>";
private static final String FAILURE_RESULT="<result>failure</result>";
private static final String INSUFFICIENT_DATA="<result>Please enter all the fields</result>";

//Get all device details	
@GET  					 // Indicates that method defined under this will answer to HTTP GET request 
@Path("/devicedetails")  		 //Defines the path
@Produces(MediaType.APPLICATION_JSON)    //Defines the media type(s) that the methods of a resource class or MessageBodyWriter can produce.
public List<Device> getDevice_details(){
	//System.out.println("inGET");
return deviceDao.getDevices();
}

//Get details of specific device with id = {device_id}	
@GET
@Path("/get_device_id/{device_id}")
@Produces(MediaType.APPLICATION_JSON)
public Device getDevice(@PathParam("device_id") int device_id){
return deviceDao.getDevice(device_id);
}

//Get details of specific device with IP address = {ip_add}
@GET
@Path("/get_device_ip/{ip_add}")
@Produces(MediaType.APPLICATION_JSON)
public Device getDevice(@PathParam("ip_add") String ip_add){
return deviceDao.getDeviceBasedOnIP(ip_add);
}

// Insert device details
@PUT   				 		      // Indicates that method defined under this will answer to HTTP PUT request 
@Path("/device_details")         		      //  specifies the Path  
@Produces(MediaType.APPLICATION_JSON)		      //Defines the media type(s) that the methods of a resource class or MessageBodyWriter can produce.
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)      //Defines which MIME type is consumed by this method
public String createDevice(@FormParam("device_id") int device_id,
@FormParam("device_name") String device_name, 	     //@FormParam -injects single parameters into method invocation 
@FormParam("ip_add") String ip_add,
@FormParam("login_username") String login_username,
@FormParam("password") String password,
@Context HttpServletResponse servletResponse) throws IOException{
Device device = new Device(device_id, device_name, ip_add, login_username, password);
// To checkif insufficient data is passed in request body
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

@POST     					  // Indicates that method defined under this will answer to HTTP PUT request  
@Path("/device_details")			  // Specifies the path that has to be specified in URI in order to reach method that delivers the functionality of POST request
@Produces(MediaType.APPLICATION_XML)		  //Defines the media type(s) that the methods of a resource class or MessageBodyWriter can produce.
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)  //Defines which MIME type is consumed by this method
public String updateDeviceDetails(@FormParam("device_id") int device_id,
@FormParam("device_name") String device_name,
@FormParam("ip_add") String ip_add,
@FormParam("login_username") String login_username,
@FormParam("password") String password,
@Context HttpServletResponse servletResponse) throws IOException{

Device device = new Device(device_id, device_name, ip_add, login_username, password);
//System.out.println("in update details");
int result = deviceDao.updateDeviceDetails(device);

if(result == 1){
return SUCCESS_RESULT;
}
return FAILURE_RESULT;
}

@DELETE						// Indicates that method defined under this will answer to HTTP DELETE request  
@Path("/device_details/{device_id}")		// Specifies the path that has to be specified in URI in order to reach method that delivers the functionality of POST request
@Produces(MediaType.APPLICATION_XML)		//Defines the media type(s) that the methods of a resource class or MessageBodyWriter can produce.
public String deleteUser(@PathParam("device_id") int device_id){  //@PathParam- To map URI path fragments into your method call
int result = deviceDao.deleteDeviceDetails(device_id);- 
if(result == 1){
return SUCCESS_RESULT;
}
return FAILURE_RESULT;
}

@OPTIONS   					// Indicates that method defined under this will answer to HTTP OPTIONS request				
@Path("/device_details")			// Specifies the path that has to be specified in URI in order to reach method that delivers the functionality of POST request
@Produces(MediaType.APPLICATION_XML)		//Defines the media type(s) that the methods of a resource class or MessageBodyWriter can produce
public String getSupportedOperations(){
return "<operations>GET, PUT, POST, DELETE</operations>";
}
}
