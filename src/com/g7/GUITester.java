package com.g7;

import java.util.Scanner;

/*
    Used to test GUI pages you make within the WindowManager class
    Edit method in the WindowManager class entitled "ShowTest"
    Then just run it
 */

public class GUITester {

    public static void main(String[] args) throws Exception{

        WindowManager windowManager = new WindowManager();

        Scanner keyboard = new Scanner(System.in);

        //Write the WindowManager function here
       // windowManager.ShowTest();

        //Enter anything in console to quit or just exit on JFrame
        keyboard.next();
        System.exit(0);
    }
}