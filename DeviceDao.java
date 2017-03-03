/*
Defines the actual implementation of methods which details what should be done specific resource is to be accessed using http methods..
 */
package com.cloud_quotient;  

import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.ObjectInputStream; 
import java.io.ObjectOutputStream; 
import java.util.ArrayList; 
import java.util.List;  
import java.io.*;

public class DeviceDao { 
	
// Get Details of all devices	
   public List<Device> getDevices(){ 
      List<Device> deviceList = null; 
      System.out.println("In DeviceDao");
      try { 
         File file = new File("Device_details.dat");       // Create a new file 
         if (!file.exists()) {				   // Check if file already exists
             Device dev1 = new Device(1, "Router1", "10.0.0.1", "dev1", "dev1"); // Create object
             Device dev2 = new Device(2, "Router2", "10.0.0.2", "dev2", "dev2"); 
             deviceList = new ArrayList<Device>(); 		//cast the device type to ARRAYLIST and store it in variable deviceList
              //System.out.println("after arraylist");
             deviceList.add(dev1);				//Add details of dev1 to the file 
             deviceList.add(dev2);				//Add details of dev2 to the file
             saveDeviceList(deviceList);   			//Save the file ( write details to output stream)
         }
	 // If file with name already exists then perform the following     
         else{ 
            FileInputStream fis = new FileInputStream(file);    //Create an instance of FileInputStream 
            ObjectInputStream ois = new ObjectInputStream(fis);  //Create an instance of ObjectInputStream
            deviceList = (List<Device>) ois.readObject(); 	 // Read object 
            ois.close(); 					// Close
         }
      } catch (IOException e) { 				// catch exception IOException raised in try block
         e.printStackTrace(); 
      } catch (ClassNotFoundException e) { 			// catch exception ClassNotFound raised in try block
         e.printStackTrace(); 
      }   
      return deviceList; 
   }
	
 // Get the details of device with device_id = device_id 
   public Device getDevice(int device_id){ 
      List<Device> devices = getDevices();  
      for(Device dev: devices){ 
         if(dev.getDeviceId() == device_id){ 
            return dev; 
         } 
      } 
      return null; 
   } 
   // Get the details of device with ip_address = ip_add 
   public Device getDeviceBasedOnIP(String ip_add){ 
	      List<Device> devices = getDevices();  
	      for(Device dev: devices){ 
	         if(dev.getIPaddress().equals(ip_add)){ 
	            return dev; 
	         } 
	      } 
	      return null; 
	   } 
  //Check if any parameter specified in the request is null   
   public int argumentCheck(Device device)
   {
    if(device.getDeviceId() == 0 || device.getDeviceName() == null || device.getIPaddress() == null || device.getLoginUsername() == null || device.getPassword() == null) {   
    return 0;
    }
    else{
    return 1;
    }
    }
	
   // Add devicedetails to the output stream
   public int addDevice(Device pDevice) 
   { 
      List<Device> deviceList = getDevices(); 
      boolean deviceExists = false; 
      for(Device dev: deviceList){ 
         if(dev.getDeviceId() == pDevice.getDeviceId()|| dev.getDeviceName() .equals (pDevice.getDeviceName()) || dev.getIPaddress().equals (pDevice.getIPaddress()) ){ 
            deviceExists = true; 
            break; 
         } 
      }   
      if(!deviceExists){
         deviceList.add(pDevice); 
         saveDeviceList(deviceList); 
         return 1;  t
      } 
      return 0; 
   }
 
    /**
     *
     * @param pDevice
     * @return
     */
    // Update device details
    public int updateDeviceDetails(Device pDevice){ 
 
      List<Device> deviceList = getDevices();
      
      for(Device dev: deviceList){
          
         if(dev.getDeviceId() == pDevice.getDeviceId() || dev.getDeviceName().equals (pDevice.getDeviceName()) || dev.getIPaddress().equals (pDevice.getIPaddress())){
             System.out.println("------------------------");
             System.out.println(dev);
            int index = deviceList.indexOf(dev);
            if(pDevice.getDeviceId()==0){
                pDevice.setDeviceId(dev.getDeviceId());
            }
            if(pDevice.getDeviceName()==null){
                pDevice.setDeviceName(dev.getDeviceName());
            }if(pDevice.getIPaddress()==null){
                pDevice.setIPaddress(dev.getIPaddress());
            }if(pDevice.getLoginUsername()==null){
                pDevice.setLoginUsername(dev.getLoginUsername());
            }if(pDevice.getPassword()==null){
                pDevice.setPassword(dev.getPassword());
            }
            deviceList.set(index,pDevice);
            
            saveDeviceList(deviceList); 
            return 1; 
         } 
      }   
      return 0; 
   } 
    // Delete Device_details	
   public int deleteDeviceDetails(int device_id){ 
      List<Device> deviceList = getDevices(); 
      List<Device> deviceList1 = null;
      deviceList1 = new ArrayList<Device>(); 
      for(Device dev: deviceList){ 
         if(dev.getDeviceId() == device_id){ 
         }
         else{
        	 deviceList1.add(dev);
         } 
      }
         saveDeviceList(deviceList1); 
         return 1;
   }  
	
    // Save details to the output stream	
   private void saveDeviceList(List<Device> deviceList){ 
      try { 
         File file = new File("Device_details.dat"); 
         FileOutputStream fos;  
         fos = new FileOutputStream(file);
         ObjectOutputStream oos = new ObjectOutputStream(fos);   
         oos.writeObject(deviceList); 
         oos.close(); 
      } catch (FileNotFoundException e) { 
         e.printStackTrace(); 
      } catch (IOException e) { 
         e.printStackTrace(); 
      } 
   } 
}

