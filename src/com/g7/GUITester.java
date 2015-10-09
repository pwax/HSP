package com.g7;

import java.util.Scanner;

/*
    Used to test GUI pages you make within the WindowManager class
    Edit method in the WindowManager class entitled "ShowTest"
    Then just run it
 */

public class GUITester {

    public static void main(String[] args) throws Exception{

        BackEndManager.sharedManager()


        WindowManager windowManager = new WindowManager();

        Scanner keyboard = new Scanner(System.in);

        //Write the WindowManager function here
       // windowManager.ShowTest();

        //Enter anything in console to quit or just exit on JFrame
        keyboard.next();
        System.exit(0);




    }
}



/*Different test code, ignore this
        String input;
        do {
            input = keyboard.nextLine();

            switch (input){
                case "1":
                    windowManager.ShowLogin();
                    break;
                case "2":
                    windowManager.ShowRegister();
                    break;
                case "Q":
                    System.out.print("Exiting");
                    break;
                default:
                    System.out.print("Not a command...\n");
                    break;
            }
        }while(input.compareTo("Q") != 0);
        */