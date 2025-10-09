package com.netflix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MovieWatchlist {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        // Start with an empty watchlist (no sample data)
        ArrayList<String> watchlist = new ArrayList<>();
        HashMap<String, Double> ratings = new HashMap<>();
        boolean running = true;

        System.out.println(" Welcome to your Movie Watchlist!");
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a movie");
            System.out.println("2. Display all the movies");
            System.out.println("3. Give a movie a rating");
            System.out.print("> ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // clear ENTER
            switch (choice) {
                case 1:
                    System.out.print("Enter movie title: ");
                    String movie = scanner.nextLine();
                    watchlist.add(movie);
                    System.out.println(movie + " added to your watchlist!");
                    break;
                case 2:

                    if (watchlist.isEmpty()) {
                        System.out.println("No movies yet —add some first!");
                    } else {
                        for (String movie1 : watchlist) {
                            String rating;

                            if (ratings.containsKey(movie1)) {
                                rating = "⭐" + ratings.get(movie1);
                            } else {
                                rating = "Not rated yet";
                            }

                            System.out.println("-" + movie1 + " (" + rating + ")");
                        }for (String movie_title : watchlist) {
                            String rating;

                            if (ratings.containsKey(movie_title)) {
                                rating = "⭐" + ratings.get(movie_title);
                            } else {
                                rating = "Not rated yet";
                            }

                            System.out.println("-" + movie_title + " (" + rating + ")");
                        }
                        System.out.println("Total movies: " + watchlist.size());
                    }
                    break;
                case 3:
                    System.out.print("Enter movie to rate: ");
                    String rateMovie = scanner.nextLine();
                    if (watchlist.contains(rateMovie)) {
                        System.out.print("Enter rating (0-10): ");
                        double rate = scanner.nextDouble();
                        scanner.nextLine();
                        ratings.put(rateMovie, rate);
                        System.out.println(rateMovie + " rated " + rate + "⭐!");
                    } else {
                        System.out.println("Movie not found in your watchlist.");
                    }
                    break;
                case 4:
                    running = false;
                    System.out.println(" Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");

                    ArrayList<Student> allStudents = new ArrayList<>();
                    Student s1 = new Student();
                    s1.name = "Bilal";
                    allStudents.add(s1);
            }
        }
    }
}