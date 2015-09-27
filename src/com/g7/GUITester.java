package com.g7;

import java.util.Scanner;

public class GUITester {

    public static void main(String[] args) {
        WindowManager windowManager = new WindowManager();

        Scanner keyboard = new Scanner(System.in);
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
                default:
                    System.out.print("Not a command...\n");
                    break;
            }
        }while(input.compareTo("Q") != 0);
    }
}
