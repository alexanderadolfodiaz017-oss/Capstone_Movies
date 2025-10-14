package com.netflix;

import java.util.Scanner;

public class Menu {

    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        MovieList list = new MovieList();
        list.loadFromCsv("movies.csv"); // ✅ Load movies at start
        boolean running = true;

        while (running) {
            System.out.println("\n🎬 Welcome to your Movie Watchlist!");
            System.out.println("1. Add a movie");
            System.out.println("2. Display all movies");
            System.out.println("3. Rate a movie");
            System.out.println("4. Show how many movies");
            System.out.println("5. Exit");
            System.out.print("Pick here: ");

            // 🧠 FIX #1: Prevent InputMismatchException
            if (!scanner.hasNextInt()) {
                System.out.println("⚠️ Please enter a number between 1 and 5!");
                scanner.nextLine(); // clear the wrong input
                continue; // go back to top of loop
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1 -> addMovie(list, scanner);
                case 2 -> list.displayAll();
                case 3 -> rateMovie(list, scanner);
                case 4 -> list.showCount();
                case 5 -> {
                    list.saveToCsv("movies.csv");
                    System.out.println("💾 Changes saved. Goodbye!");
                    running = false;
                }
                default -> System.out.println("⚠️ Invalid choice, try again!");
            }
        }
    }

    private static void addMovie(MovieList list, Scanner scanner) {
        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();
        System.out.print("Enter rating (0–5): ");
        while (!scanner.hasNextDouble()) {
            System.out.print("⚠️ Please enter a number (0–5): ");
            scanner.next(); // clear invalid input
        }
        double rating = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        list.addMovie(title, rating);
        list.saveToCsv("movies.csv");
    }

    private static void rateMovie(MovieList list, Scanner scanner) {
        System.out.print("Enter movie title to rate: ");
        String title = scanner.nextLine();
        System.out.print("Enter new rating (0–5): ");
        while (!scanner.hasNextDouble()) {
            System.out.print("⚠️ Please enter a number (0–5): ");
            scanner.next(); // clear invalid input
        }
        double rating = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        list.updateRating(title, rating);
        list.saveToCsv("movies.csv");
    }
}
