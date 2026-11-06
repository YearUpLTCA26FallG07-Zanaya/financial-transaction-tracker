package com.pluralsight;

public class Menu {

        // Main Menu
        public void displayMainMenu() {
            System.out.println("1. Add Deposit: (D)");
            System.out.println("2. Make Payment: (P)");
            System.out.println("3. Ledger: (L)");
            System.out.println("4. Exit: (X)");
            System.out.println("Please select an option:");
        }

        // Ledger Menu
        public void displayLedgerMenu() {
            System.out.println("Ledger");
            System.out.println("All transactions (A)");
            System.out.println("Deposits (D)");
            System.out.println("Payments (P)");
            System.out.println("Reports (R)");
            System.out.println("Home (H)");
            System.out.println("Please select an option:");
        }

        // Reports Menu
        public void displayReportsMenu() {
            System.out.println("Reports");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.println("Please select an option:");
        }
    }


