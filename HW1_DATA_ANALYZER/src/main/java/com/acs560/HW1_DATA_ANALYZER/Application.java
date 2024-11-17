package com.acs560.HW1_DATA_ANALYZER;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

/**
 * Application for analyzing Netflix data from a CSV file. Provides functionalities 
 * to count movies vs TV shows, calculate the average number of movies per year, 
 * and count unique countries and their occurrences. Results are saved in separate 
 * text files for each analysis.
 */
public class Application {
    static final String FILE_NAME = "netflix_data.csv";
    static final String COUNT_FILE = "count_movies_vs_tv_shows.txt";
    static final String AVG_FILE = "average_movies_per_year.txt";
    static final String COUNTRIES_FILE = "unique_countries_counts.txt";

    private static final String[] MENU_OPTIONS = {
        "1. Count of Movies vs TV Shows",
        "2. Average number of Movies per year",
        "3. Unique Countries and their Counts",
        "4. Exit"
    };

    private static final String EXIT_MESSAGE = "Exiting the application. Goodbye!";
    private static final String INVALID_CHOICE_MESSAGE = "Invalid choice. Please try again.\n";
    private static final String WELCOME_MESSAGE = "Welcome! To the Netflix Analyzer.";

    /**
     * Main entry point for the application. Displays a menu for the user to select 
     * different data analysis options.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(WELCOME_MESSAGE);

        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = getUserChoice(scanner);

            if (choice == -1) {
                System.out.println(INVALID_CHOICE_MESSAGE);
                continue;
            }

            List<String[]> allData = readCSV(FILE_NAME);
            exit = processChoice(choice, allData);
        }
        scanner.close();
    }

    /**
     * Displays the menu options to the user.
     */
    private static void printMenu() {
        System.out.println("Here are few things I can help you with:");
        for (String option : MENU_OPTIONS) {
            System.out.println(option);
        }
        System.out.print("Please enter your choice: ");
    }

    /**
     * Retrieves the user's choice from the console input.
     * 
     * @param scanner Scanner object for reading user input.
     * @return The user's choice as an integer, or -1 if input is invalid.
     */
    private static int getUserChoice(Scanner scanner) {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            scanner.nextLine(); // consume invalid input
            return -1; // invalid choice
        }
    }

    /**
     * Processes the user's choice and executes the corresponding analysis.
     * 
     * @param choice  The user's choice from the menu.
     * @param allData List of CSV rows where each row represents a data entry.
     * @return True if the user chose to exit, false otherwise.
     */
    private static boolean processChoice(int choice, List<String[]> allData) {
        switch (choice) {
            case 1:
                countMoviesVsTVShows(allData);
                break;
            case 2:
                avgMovies(allData);
                break;
            case 3:
                countUniqueCountries(allData);
                break;
            case 4:
                System.out.println(EXIT_MESSAGE);
                return true;
            default:
                return false;
        }
        return false;
    }

    /**
     * Counts the number of movies and TV shows from the provided data and writes
     * the result to a text file.
     * 
     * @param allData List of CSV rows where each row represents a data entry. The
     *                type of media is expected to be in column index 1.
     */
    public static void countMoviesVsTVShows(List<String[]> allData) {
        int movieCount = 0;
        int tvShowCount = 0;

        for (String[] row : allData) {
            String type = row[1];
            if (type.equalsIgnoreCase("Movie")) {
                movieCount++;
            } else if (type.equalsIgnoreCase("TV Show")) {
                tvShowCount++;
            }
        }
        StringBuilder textResult = new StringBuilder("=== Count of Movies vs TV Shows ===\n");
        textResult.append(String.format("%-25s %s%n", "Type", "Count"));
        textResult.append("==============================\n");
        textResult.append(String.format("%-25s %d%n", "Movies", movieCount));
        textResult.append(String.format("%-25s %d%n", "TV Shows", tvShowCount));
        writeToTextFile(COUNT_FILE, textResult.toString());
    }

    /**
     * Calculates the average number of movies released per year and writes the
     * result to a text file.
     * 
     * @param allData List of CSV rows where each row represents a data entry. The
     *                type of media is expected to be in column index 1, and the
     *                release year is expected to be in column index 7.
     */
    public static void avgMovies(List<String[]> allData) {
        Map<String, Integer> moviesPerYear = new HashMap<>();
        int totalMovies = 0;

        for (String[] row : allData) {
            String type = row[1];
            String releaseYear = row[7];

            if (type != null && type.equalsIgnoreCase("Movie")) {
                totalMovies++;
                moviesPerYear.put(releaseYear, moviesPerYear.getOrDefault(releaseYear, 0) + 1);
            }
        }

        StringBuilder textResult = new StringBuilder("=== Average Movies per Year ===\n");
        textResult.append(String.format("%-25s %s%n", "Total Movies:", "Average Movies per Year"));
        textResult.append("==============================\n");

        if (moviesPerYear.size() > 0) {
            double average = (double) totalMovies / moviesPerYear.size();
            textResult.append(String.format("%-25d %.2f%n", totalMovies, average));
        } else {
            textResult.append(String.format("%-25s %s%n", "No movies found.", ""));
        }

        writeToTextFile(AVG_FILE, textResult.toString());
    }

    /**
     * Counts the occurrences of movies and TV shows by country and writes the
     * result to a text file.
     * 
     * @param allData List of CSV rows where each row represents a data entry. The
     *                countries are expected to be in column index 5.
     */
    public static void countUniqueCountries(List<String[]> allData) {
        Map<String, Integer> countryCounts = new HashMap<>();

        for (String[] row : allData) {
            String countries = row[5];

            if (countries != null && !countries.trim().isEmpty()) {
                String[] countryList = countries.split(",");
                for (String country : countryList) {
                    country = country.trim();
                    countryCounts.put(country, countryCounts.getOrDefault(country, 0) + 1);
                }
            }
        }

        StringBuilder textResult = new StringBuilder("=== Unique Countries and their Counts ===\n");
        textResult.append(String.format("%-25s %s%n", "Country", "Count"));
        textResult.append("==============================\n");
        for (Map.Entry<String, Integer> entry : countryCounts.entrySet()) {
            textResult.append(String.format("%-25s %d%n", entry.getKey(), entry.getValue()));
        }

        writeToTextFile(COUNTRIES_FILE, textResult.toString());
    }

    /**
     * Reads data from a CSV file, skipping the header row.
     * 
     * @param file The path to the CSV file to be read.
     * @return List of CSV rows excluding the header row.
     */
    public static List<String[]> readCSV(String file) {
        List<String[]> allData = null;

        try {
            FileReader fileReader = new FileReader(file);
            CSVReader reader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
            allData = reader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allData;
    }

    /**
     * Writes the specified content to a text file.
     * 
     * @param fileName The name of the file to which the content will be written.
     * @param content  The content to be written to the file.
     */
    static void writeToTextFile(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
            System.out.println("Results written to " + fileName + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
