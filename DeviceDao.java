/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
   public List<Device> getDevices(){ 
      List<Device> deviceList = null; 
      System.out.println("In DeviceDao");
      try { 
         File file = new File("Device_details.dat"); 
         if (!file.exists()) {
             Device dev1 = new Device(1, "Router1", "10.0.0.1", "dev1", "dev1"); 
             Device dev2 = new Device(2, "Router2", "10.0.0.2", "dev2", "dev2"); 
             deviceList = new ArrayList<Device>(); 
              System.out.println("after arraylist");
             deviceList.add(dev1);
             deviceList.add(dev2);
             saveDeviceList(deviceList);   
         } 
         else{ 
            FileInputStream fis = new FileInputStream(file); 
            ObjectInputStream ois = new ObjectInputStream(fis); 
            deviceList = (List<Device>) ois.readObject(); 
            ois.close(); 
         }
      } catch (IOException e) { 
         e.printStackTrace(); 
      } catch (ClassNotFoundException e) { 
         e.printStackTrace(); 
      }   
      return deviceList; 
   }  
   public Device getDevice(int device_id){ 
      List<Device> devices = getDevices();  
      for(Device dev: devices){ 
         if(dev.getDeviceId() == device_id){ 
            return dev; 
         } 
      } 
      return null; 
   } 
   
   public Device getDevice_based_on_ip(String ip_add){ 
	      List<Device> devices = getDevices();  
	      for(Device dev: devices){ 
	         if(dev.getIPaddress().equals(ip_add)){ 
	            return dev; 
	         } 
	      } 
	      return null; 
	   } 
	   
   public int argumentCheck(Device device)
   {
    if(device.getDeviceId() == 0 || device.getDeviceName() == null || device.getIPaddress() == null || device.getLoginUsername() == null || device.getPassword() == null) {   
    return 0;
    }
    else{
    return 1;
    }
    }
   
   
   
   
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
         return 1; 
      } 
      return 0; 
   }
 
    /**
     *
     * @param pDevice
     * @return
     */
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

