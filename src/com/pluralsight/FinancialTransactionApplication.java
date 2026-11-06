package com.pluralsight;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.LocalDate;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;



public class FinancialTransactionApplication {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        ArrayList<Transaction> transaction = new ArrayList<>();
        char choice = ' ';

        while (choice != 'X') {

            menu.displayMainMenu();
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
                    String transactions = formattedDate + "|" + formattedTime + "|" + description + "|" + vendor + "|" + deposit;

                    try
                            (FileWriter writer = new FileWriter("transactions.csv", true)) {
                        writer.write(transactions + "\n");
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

                        menu.displayLedgerMenu();
                        menuChoice = scanner.next().charAt(0);

                        switch (menuChoice) {

                            case 'A' -> {
                                try {
                                    FileReader allTransactions = new FileReader("transactions.csv");
                                    BufferedReader transHistory = new BufferedReader(allTransactions);

                                    ArrayList<String> allEntries  = new ArrayList<>();

                                    String line = transHistory.readLine();

                                    while (line != null) {
                                        allEntries.add(line);
                                        line = transHistory.readLine();
                                    }
                                    transHistory.close();

                                    for (int i = allEntries.size() -1; i >= 0; i--) {
                                        System.out.println(allEntries.get(i));

                                    }

                                } catch (IOException e) {
                                    System.out.println("Unable to read transaction.");
                                }
                            }
                            case 'D' -> {
                                try {
                                    FileReader deposits = new FileReader("transactions.csv");
                                    BufferedReader depHistory = new BufferedReader(deposits);

                                    ArrayList<String> allDeposits = new ArrayList<>();

                                    String transactions = depHistory.readLine();

                                    while (transactions != null) {

                                        String[] parts = transactions.split("\\|");

                                        if (parts.length ==5) {
                                            double amount = Double.parseDouble(parts[4]);

                                            if (amount > 0) {
                                                allDeposits.add(transactions);
                                            }
                                        }
                                        transactions = depHistory.readLine();

                                        for (int i = allDeposits.size() - 1; i >= 0; i--) {
                                            System.out.println(allDeposits.get(i));
                                        }
                                    }

                                } catch (IOException e) {
                                    System.out.println("Unable to read transactions");
                                }
                            }

                            case 'P' -> {
                                try {
                                    FileReader payments = new FileReader("transactions.csv");
                                    BufferedReader payHistory = new BufferedReader(payments);

                                    ArrayList<String> allPayments = new ArrayList<>();

                                    String transactions = payHistory.readLine();

                                    while (transactions != null) {
                                        String[] parts = transactions.split("\\|");

                                        if (parts.length == 5) {
                                            double amount = Double.parseDouble(parts[4]);

                                            if (amount < 0) {
                                                allPayments.add(transactions);
                                            }
                                        }
                                        transactions = payHistory.readLine();
                                    }

                                        for (int i = allPayments.size() - 1; i >= 0; i--) {
                                            System.out.println(allPayments.get(i));
                                        }

                                } catch (IOException exception) {
                                    System.out.println("Unable to read transaction");
                                }
                            }
                            case 'R' -> {

                                try {
                                    FileReader files = new FileReader("transactions.csv");
                                    BufferedReader pulledFiles = new BufferedReader(files);

                                    String filesPulled = pulledFiles.readLine();

                                    while (filesPulled != null) {

                                        String[] parts = filesPulled.split("\\|");

                                        if (parts.length == 5) {

                                            Transaction transactions = new Transaction(parts[0], parts[2], parts[1], parts[3], Double.parseDouble(parts[4])
                                            );

                                            transaction.add(transactions);
                                        }

                                        filesPulled = pulledFiles.readLine();

                                    }

                                } catch (IOException e) {
                                    System.out.println("Unable to read transactions");
                                }
                                char reportChoice = ' ';


                                while (reportChoice != '0') {
                                    menu.displayReportsMenu();
                                    reportChoice = scanner.next().charAt(0);

                                    if (reportChoice == '1') {

                                      LocalDate today = LocalDate.now();

                                      for (Transaction item : transaction) {

                                          LocalDate date = LocalDate.parse(item.getDate());

                                          if (date.getMonth() == today.getMonth() && date.getYear() == today.getYear()) {
                                              System.out.println(item.getDate() + "|" + item.getDescription() + "|" + item.getVendor() + "|" + item.getAmount());
                                          }
                                      }
                                    } else if (reportChoice == '2') {

                                        LocalDate today = LocalDate.now();
                                        LocalDate previousMonth = today.minusMonths(1);

                                        for (Transaction item : transaction) {

                                            LocalDate date = LocalDate.parse(item.getDate());

                                            if (date.getMonth() == previousMonth.getMonth()
                                                    && date.getYear() == previousMonth.getYear()) {

                                                System.out.println(item.getDate() + " | "
                                                        + item.getDescription() + " | "
                                                        + item.getVendor() + " | "
                                                        + item.getAmount());
                                            }
                                        }
                                    } else if (reportChoice == '3') {
                                        LocalDate today = LocalDate.now();

                                        for (Transaction item : transaction) {

                                            LocalDate date = LocalDate.parse(item.getDate());

                                            if (date.getYear() == today.getYear()
                                                    && !date.isAfter(today)) {

                                                System.out.println(item.getDate() + " | "
                                                        + item.getDescription() + " | "
                                                        + item.getVendor() + " | "
                                                        + item.getAmount());
                                            }
                                        }
                                    } else if (reportChoice == '4') {
                                        LocalDate today = LocalDate.now();

                                        int previousYear = today.getYear() - 1;

                                        for (Transaction item : transaction) {

                                            LocalDate date = LocalDate.parse(item.getDate());

                                            if (date.getYear() == previousYear) {

                                                System.out.println(item.getDate() + " | "

                                                        + item.getDescription() + " | "

                                                        + item.getVendor() + " | "

                                                        + item.getAmount());

                                            }

                                        }
                                    } else if (reportChoice == '5') {

                                        scanner.nextLine();

                                        System.out.println("Enter vendor name:");
                                        String searchVendor = scanner.nextLine();

                                        for (Transaction item : transaction) {

                                            if (item.getVendor().equalsIgnoreCase(searchVendor)) {

                                                System.out.println(item.getDate() + " | "
                                                        + item.getDescription() + " | "
                                                        + item.getVendor() + " | "
                                                        + item.getAmount());
                                            }
                                        }

                                    } else if (reportChoice != '0') {
                                        System.out.println("Invalid selection");


                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
















