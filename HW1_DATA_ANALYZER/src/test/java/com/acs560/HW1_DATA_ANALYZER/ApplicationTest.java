package com.acs560.HW1_DATA_ANALYZER;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.List;

class ApplicationTest {

	private List<String[]> sampleData;

	@BeforeEach
	public void setUp() {
		sampleData = List.of(new String[] { "1", "Movie", "Title", "Director", "cast", "Country3", "Rating", "2020" },
				new String[] { "1", "Movie", "Title", "Director", "cast", "Country2", "Rating", "2021" },
				new String[] { "1", "TV Show", "Title", "Director", "cast", "Country1", "Rating", "2020" });
	}

	private String readFileContent(String fileName) throws IOException {
		return Files.readString(new File(fileName).toPath());
	}

	@Test
	public void testCountMoviesVsTVShows() throws IOException {
		Application.countMoviesVsTVShows(sampleData);

		String expectedContent = """
				=== Count of Movies vs TV Shows ===
				Type                      Count
				==============================
				Movies                    2
				TV Shows                  1
				""";

		String actualContent = readFileContent(Application.COUNT_FILE);

		expectedContent = expectedContent.strip().replaceAll("\\s+", "").replace("\r\n", "\n");
		actualContent = actualContent.strip().replaceAll("\\s+", "").replace("\r\n", "\n");

		assertEquals(expectedContent, actualContent);
	}

	@Test
	public void testCountUniqueCountries() throws IOException {
		Application.countUniqueCountries(sampleData);

		String expectedContent = """
				=== Unique Countries and their Counts ===
				Country                  Count
				==============================
				Country3                 1
				Country2                 1
				Country1                 1
				""";

		String actualContent = readFileContent(Application.COUNTRIES_FILE);

		expectedContent = expectedContent.strip().replaceAll("\\s+", "").replace("\r\n", "\n");
		actualContent = actualContent.strip().replaceAll("\\s+", "").replace("\r\n", "\n");

		assertEquals(expectedContent, actualContent);
	}

	@Test
	public void testAvgMovies() throws IOException {
		Application.avgMovies(sampleData);
		String expectedContent = """
				=== Average Movies per Year ===
				Total Movies:             Average Movies per Year
				==============================
				2                         1.00
				""";

		String actualContent = readFileContent(Application.AVG_FILE);

		expectedContent = expectedContent.strip().replaceAll("\\s+", "").replace("\r\n", "\n");
		actualContent = actualContent.strip().replaceAll("\\s+", "").replace("\r\n", "\n");

		assertEquals(expectedContent, actualContent);
	}

	@Test
	public void testReadCSV() {
		List<String[]> data = Application.readCSV(Application.FILE_NAME);
		assertTrue(data != null && !data.isEmpty());
	}

	@Test
	public void testWriteToTextFile() throws IOException {
		String content = "Sample content for file writing test.";
		Application.writeToTextFile("test_output.txt", content);

		String actualContent = readFileContent("test_output.txt");
		assertEquals(content, actualContent);
	}

	@AfterEach
	public void cleanUp() {
		new File(Application.COUNT_FILE).delete();
		new File(Application.AVG_FILE).delete();
		new File(Application.COUNTRIES_FILE).delete();
	}
	
	@Test
    public void testMain() throws IOException {
		
		Application.countUniqueCountries(sampleData);
		
        String simulatedInput = "1\n2\n3\n4\n";
        ByteArrayInputStream inStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inStream);

        // Capture the output from System.out
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));

        // Run the main method
        Application.main(new String[0]);

        // Normalize the newlines and strip unnecessary whitespaces from the captured output
        String output = outStream.toString().replaceAll("\\r\\n", "\n").strip();

        // Expected output that matches the sequence of inputs and file-writing messages
        String expectedOutput = """
            Welcome! To the Netflix Analyzer.
            Here are few things I can help you with:
            1. Count of Movies vs TV Shows
            2. Average number of Movies per year
            3. Unique Countries and their Counts
            4. Exit
            Please enter your choice: Results written to count_movies_vs_tv_shows.txt

            Here are few things I can help you with:
            1. Count of Movies vs TV Shows
            2. Average number of Movies per year
            3. Unique Countries and their Counts
            4. Exit
            Please enter your choice: Results written to average_movies_per_year.txt

            Here are few things I can help you with:
            1. Count of Movies vs TV Shows
            2. Average number of Movies per year
            3. Unique Countries and their Counts
            4. Exit
            Please enter your choice: Results written to unique_countries_counts.txt

            Here are few things I can help you with:
            1. Count of Movies vs TV Shows
            2. Average number of Movies per year
            3. Unique Countries and their Counts
            4. Exit
            Please enter your choice: Exiting the application. Goodbye!
            """.strip();

        // Assert that the actual output matches the expected output
        assertEquals(expectedOutput, output);
    }

}
