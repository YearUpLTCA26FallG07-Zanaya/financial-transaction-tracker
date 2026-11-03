package com.pluralsight;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.LocalDate;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;



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


            //Deposit menu

            switch (choice) {
                case 'D' -> {
                    scanner.nextLine();
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

                    try
                            (FileWriter writer = new FileWriter("transactions.csv", true)) {
                        writer.write(transaction + "\n");
                    } catch (IOException e) {
                        System.out.println("Error saving transaction ");
                    }
                }

                //Payment Menu

                case 'P' -> {
                    scanner.nextLine();
                    System.out.println("What is this payment for?");
                    String paymentFor = scanner.nextLine();

                    System.out.println("Who is the payment going to? ");
                    String paymentGoing = scanner.nextLine();

                    System.out.println("Enter payment amount:");
                    double paymentAmount = scanner.nextDouble();
                    paymentAmount = paymentAmount * -1;

                    LocalDate date = LocalDate.now();
                    LocalTime time = LocalTime.now();
                    String formattedTime = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                    String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    String paymentTrans = formattedDate + "|" + formattedTime + "|" + paymentFor + "|" + paymentGoing + "|" + paymentAmount;


                    try
                            (FileWriter writer = new FileWriter("transactions.csv", true)) {
                        writer.write(paymentTrans + "\n");
                    } catch (IOException e) {
                        System.out.println("Error saving transaction ");
                    }

                }


                case 'L' -> {

                    char menuChoice = ' ';

                    while (menuChoice != 'H') {

                        System.out.println("Ledger");
                        System.out.println("All transactions (A)");
                        System.out.println("Deposits (D)");
                        System.out.println("Payments (P)");
                        System.out.println("Reports (R)");
                        System.out.println("Home (H)");

                        menuChoice = scanner.next().charAt(0);

                        switch (menuChoice) {

                            case 'A' -> {
                                try {
                                    FileReader allTransactions = new FileReader("transactions.csv");
                                    BufferedReader transHistory = new BufferedReader(allTransactions);
                                    String line = transHistory.readLine();

                                    while (line != null) {
                                        System.out.println(line);
                                        line = transHistory.readLine();
                                    }

                                } catch (IOException e) {
                                    System.out.println("Unable to read transaction.");
                                }
                            }
                                case 'D' -> {
                                    try {
                                        FileReader deposits = new FileReader("transactions.csv");
                                        BufferedReader depHistory = new BufferedReader(deposits);
                                        String transactions = depHistory.readLine();

                                        while (transactions != null) {

                                            String[] parts = transactions.split("\\|");
                                            double amount = Double.parseDouble(parts[4]);

                                            if (amount > 0) {
                                                System.out.println(transactions);
                                            }
                                            transactions = depHistory.readLine();
                                        }

                                    } catch (IOException e) {
                                        System.out.println("Unable to read transactions");
                                    }


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
        }
    }
}



