/*
Name: Ryan Koblentz
Date Modified: 6/29/2026
Purpose: A program to help users organize what devices are online or offline on their home network.
*/

import java.util.Scanner;
public class NetworkDeviceManager {
  public static void main(String args[]) 
  {
      Scanner keyboard=new Scanner(System.in);
      //***VARIABLE INITIALIZATION AREA***
      //Arrays used to store information for each network device
      String[] deviceNames=new String[10];
      String[] ipAddresses=new String[10];
      char[] statuses=new char[10];
      //Keeps track of the # of devices currently stored
      int deviceCount=0;
      int choice=0;
      
      //call the welcome banner
      welcomeBanner();
      
      while (choice!=5)
      {     
          do
          {
            showMenu();
            System.out.println("Enter your choice: ");
            choice=keyboard.nextInt();
          //make sure the user enters a valid input, otherwise send them back to the top
            if ((choice<1)||(choice>5))
            {
                System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
          }while((choice<1)||(choice>5));
          //prompt the user for their choice based on the menu

          
          switch (choice)
          {
              case 1:
                  deviceCount=addDevice(keyboard,deviceNames,ipAddresses,statuses,deviceCount);
                  //ask for device name
                  //ask for IP address
                  //save device information
                  break;
              case 2:
                  viewDevices(deviceNames,ipAddresses,statuses,deviceCount);
                  //print all saved devices
                  break;
              case 3:
                  searchDevice(keyboard,deviceNames,ipAddresses,statuses,deviceCount);
                  //ask for a device name
                  //search through saved devices
                  break;
              case 4:
                  changeDeviceStatus(keyboard,deviceNames,statuses,deviceCount);
                  //select device
                  //change online/offline status
                  break;
              case 5:
                  //call the closing banner
                  closingBanner();
                  break;
             default: //not needed, but ill leave it in as a backup 
                  System.out.println("Invalid choice. Please enter a number from 1 to 5.");


            
                  
          }
      }
      
    
    
  }
  public static void showMenu()
  {
    System.out.println();
    System.out.println("----- Network Device Manager -----");
    System.out.println("1. Add Device");
    System.out.println("2. View Devices");
    System.out.println("3. Search Device");
    System.out.println("4. Change Status");
    System.out.println("5. Exit");
  }
  //Method to add a new device to the arrays
  //Returns the updated number of devices currently stored
  public static int addDevice(Scanner keyboard, String [] deviceNames, String [] ipAddresses, char [] statuses, int deviceCount)
  {
      //gotta make sure theres enough room in the arrays before adding a new device
      if (deviceCount<deviceNames.length)
      {
        //clear leftover newline from nextInt()
        keyboard.nextLine();

        //ask the user for the device name and store it in the array
        System.out.print("Enter device name: ");
        deviceNames[deviceCount]=keyboard.nextLine();

        //ask the user for the IP address to store in the array
        System.out.println("Enter the device's IP address: ");
        ipAddresses[deviceCount]=keyboard.nextLine();

        //ask whether the device is online or offline
        //store only the first character (Y or N)
        //maybe come back and add a validation loop for Y or N here (done!)
        do
        {
            System.out.println("Is the device online? (Enter Y or N): ");
            statuses[deviceCount]=keyboard.next().toUpperCase().charAt(0);

            if((statuses[deviceCount]!='Y')&&(statuses[deviceCount]!='N'))
            {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        }while((statuses[deviceCount]!='Y')&&(statuses[deviceCount]!='N'));


        //increase # of saved devices
        deviceCount++;

        System.out.println("Device added successfully.");

      }
      else
      {
        System.out.println("The device list is full.");
        
      }
      //return updated list of devices
      return deviceCount;
  }
  public static void viewDevices(String [] deviceNames,String [] ipAddresses, char [] statuses, int deviceCount)
  {
      //check if there are no devices saved first
      if (deviceCount==0)
      {
        System.out.println("No devices have been added yet.");
      }
      else
      {
        System.out.println();
        System.out.println("----- Saved Devices -----");
        //loop through only devices that are already added
        for (int i=0;i<deviceCount;i++)
        {
            System.out.println("Device #" + (i+1));
            System.out.println("Name: " + deviceNames[i]);
            System.out.println("IP Address: " + ipAddresses[i]);

            if(statuses[i]=='Y')
            {
                System.out.println("Status: Online");
            }
            else
            {
                System.out.println("Status: Offline");
            }
        }
      }
  }
  public static void searchDevice(Scanner keyboard, String [] deviceNames, String [] ipAddresses, char [] statuses, int deviceCount)
  {
      String searchName;
      char deviceFound='N';
      //clear leftover newline
      keyboard.nextLine();

      //ask user which device they want to search for
      System.out.println("Enter the device name you would like to search for: ");
      searchName=keyboard.nextLine();
      //search through all the saved devices
      for(int i=0;i<deviceCount;i++)
      {
        //check if the device name matches the one the user searched for
        if(deviceNames[i].equalsIgnoreCase(searchName))
        {
            System.out.println("----- Device Found! -----");
            System.out.println("Device Name: " + deviceNames[i]);
            System.out.println("IP Address: " + ipAddresses[i]);

            //now lets print if the selected device is online or offline
            if(statuses[i]=='Y')
            {
                System.out.println("Status: Online");
            }
            else
            {
                System.out.println("Status: Offline");
            }
            //update variable to show a matching device was found
            deviceFound='Y';
        }
      }
      //if device was not found, tell the user
      if (deviceFound=='N')
      {
        System.out.println("Device not found.");
      }

  }
  public static void changeDeviceStatus(Scanner keyboard,String [] deviceNames, char [] statuses, int deviceCount)
  {
      String searchName;
      char deviceFound='N';

      //clear leftover newline
      keyboard.nextLine();
      //ask the user which device they want to update
      System.out.println("Enter the device name you would like to update: ");
      searchName=keyboard.nextLine();

      //search through all of the saved devices
      for(int i=0;i<deviceCount;i++)
      {
        //check if the current device name matches what the user searched for
        if (deviceNames[i].equalsIgnoreCase(searchName))
        {
            System.out.println("Device found: " + deviceNames[i]);
            //ask the user for the updated status
            //added the validation loop here too and replace [deviceCount] with [i]
            do
            {
                System.out.println("Enter the new status. Is the device online? (Y or N): ");
                statuses[i]=keyboard.next().toUpperCase().charAt(0);
                
                if((statuses[i]!='Y')&&(statuses[i]!='N'))
                {
                    System.out.println("Invalid input. Please enter Y or N.");
                }
            }while((statuses[i]!='Y')&&(statuses[i]!='N'));
            System.out.println("Device status updated.");

            //update the variable to show a matching device was found
            deviceFound='Y';
        }
      }
      //if a matching device wasnt found, tell the user
      if(deviceFound=='N')
      {
        System.out.println("Device not found.");
      }

  }
  public static void welcomeBanner()
  {
    System.out.println("----------------------------------------");
    System.out.println("            Welcome to the ");
    System.out.println("   Network Device Manager Application");
    System.out.println("----------------------------------------");
  }
  public static void closingBanner()
  {
    System.out.println("----------------------------------------");
    System.out.println("       Thank you for using the ");
    System.out.println("    Network Device Manager Program");
    System.out.println("              Goodbye! :)");
    System.out.println("----------------------------------------");
  }
}