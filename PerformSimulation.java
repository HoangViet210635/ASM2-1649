package fgw.Assignment_2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PerformSimulation {
    public static void main(String[] args) {
        try {
            int option;
            Scanner sc = new Scanner(System.in);
            CSystem systemA = new CSystem("System A");
            CSystem systemB = new CSystem("System B");
            CSystem systemC = new CSystem("System C");
            do {
                System.out.println("Select the system you want to use: ");
                System.out.println("1. " + systemA.getNameOfSystem());
                System.out.println("2. " + systemB.getNameOfSystem());
                System.out.println("3. " + systemC.getNameOfSystem());
                System.out.println("4. End the program");
                System.out.print("Enter your option: ");
                option = sc.nextInt();
                switch (option) {
                    case 1:
                        systemA.menu(systemB, systemC);
                        break;
                    case 2:
                        systemB.menu(systemA, systemC);
                        break;
                    case 3:
                        systemC.menu(systemA, systemB);
                        break;
                    case 4:
                        System.out.println("EXIT");
                        break;
                    default:
                        System.out.println("ERROR !!! JUST TYPE FROM 1 --> 4. TRY AGAIN");
                        break;
                }
            } while (option != 4);
        } catch (InputMismatchException e) {
            System.out.println("Error: Only enter numbers, not letters");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
