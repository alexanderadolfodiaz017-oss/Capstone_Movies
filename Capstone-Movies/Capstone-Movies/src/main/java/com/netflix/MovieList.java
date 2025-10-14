package com.netflix;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class MovieList {
    private final List<Movie> movies = new ArrayList<>();

    public void addMovie(String title, double rating) {
        if (findMovie(title) != null) {
            System.out.println("ℹ️ Movie already exists: " + title);
            return;
        }
        movies.add(new Movie(title.trim(), rating));
        System.out.println("✅ Added movie: " + title + " (⭐ " + rating + ")");
    }

    public void displayAll() {
        if (movies.isEmpty()) {
            System.out.println("📭 Your movie list is empty.");
            return;
        }
        movies.forEach(System.out::println);
    }

    public Movie findMovie(String title) {
        return movies.stream()
                .filter(m -> m.getTitle().equalsIgnoreCase(title.trim()))
                .findFirst()
                .orElse(null);
    }

    public void updateRating(String title, double newRating) {
        Movie m = findMovie(title);
        if (m == null) {
            System.out.println("❌ Movie not found: " + title);
            return;
        }
        m.setRating(newRating);
        System.out.println("⭐ Updated " + m.getTitle() + " to " + newRating + " stars.");
    }

    public void showCount() {
        System.out.println("📊 Total movies: " + movies.size());
    }

    public void saveToCsv(String fileName) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(fileName))) {
            for (Movie m : movies)
                w.write(m.getTitle() + "," + m.getRating() + "\n");
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void loadFromCsv(String fileName) {
        movies.clear();
        try (BufferedReader r = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] p = line.split(",", 2);
                if (p.length == 2)
                    movies.add(new Movie(p[0], Double.parseDouble(p[1])));
            }
        } catch (IOException ignored) { }
    }

    /* ------------------------------------------------------------------
       🌊 STREAMING EXAMPLES
       ------------------------------------------------------------------ */

    // 🔹 1. Show all movies above a rating threshold
    public void displayTopRated(double minRating) {
        System.out.println("🎯 Movies rated " + minRating + "+:");
        movies.stream()
                .filter(m -> m.getRating() >= minRating)
                .forEach(System.out::println);
    }

    // 🔹 2. Show average rating of all movies
    public void showAverageRating() {
        double avg = movies.stream()
                .mapToDouble(Movie::getRating)
                .average()
                .orElse(0);
        System.out.printf("📈 Average rating: %.2f%n", avg);
    }

    // 🔹 3. Sort movies by rating (highest first)
    public void displaySortedByRating() {
        System.out.println("🏆 Movies sorted by rating:");
        movies.stream()
                .sorted(Comparator.comparingDouble(Movie::getRating).reversed())
                .forEach(System.out::println);
    }

    // 🔹 4. Get a list of titles only (as Strings)
    public List<String> getTitles() {
        return movies.stream()
                .map(Movie::getTitle)
                .collect(Collectors.toList());
    }
}
