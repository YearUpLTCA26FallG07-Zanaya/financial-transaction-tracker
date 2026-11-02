package com.pluralsight;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.LocalDate;
import java.io.IOException;
import java.io.FileWriter;

public class FinancialTransactionApplication {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        char choice = ' ';

        while (choice != 'X') {

            System.out.println("1.Add Deposit: (D)");
            System.out.println("2.Make Payment: (P)");
            System.out.println("3.Ledger: (L)");
            System.out.println("4.Exit: (X)");
            System.out.println("Please select an option: ");

            choice = scanner.next().charAt(0);

            switch (choice) {
                case 'D' -> {
                    System.out.println("Enter deposit description:");
                    String description = scanner.nextLine();
                    System.out.println("Enter vendor:");
                    String vendor = scanner.nextLine();
                    System.out.println("Enter deposit amount:");
                    double deposit = scanner.nextDouble();
                    LocalDate date = LocalDate.now();
                    LocalTime time = LocalTime.now();
                    String formattedTime = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                    String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    String transaction = formattedDate + "|" + formattedTime + "|" + description + "|" + vendor + "|" + deposit;
                    try {
                        FileWriter writer = new FileWriter("transactions.csv", true);
                        writer.write(transaction + "\n");
                    } catch (IOException e) {
                        System.out.println("Error saving transaction ");
                    }
                }



                case 'P' -> {
                    System.out.println("Payment selected");
                }



                case 'L' -> {
                    System.out.println("Ledger selected");
                }



                case 'X' -> {
                    System.out.println("Exiting application");
                }


                default -> {
                    System.out.println("Invalid Selection, Please try again.");
                }




            }
        }

    }

}
